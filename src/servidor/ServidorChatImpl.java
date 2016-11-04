
import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServidorChatImpl extends java.rmi.server.UnicastRemoteObject implements ServidorChat {

    public ServidorChatImpl() throws RemoteException {
        super();

    }

    public Connection getConexao(String ip) {
        Connection conexao = null;

        try {
            Class.forName("org.postgresql.Driver");
            conexao = DriverManager.getConnection("jdbc:postgresql://" + ip + "/chat", "postgres", "root");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conexao;
    }

    public boolean Inserir(Usuario user, String ip) throws RemoteException {
        boolean op;
        try {
            Connection conexao = getConexao(ip);
            PreparedStatement pstm = conexao.prepareStatement("Insert into usuario(nome, senha)values(?,?)");
            pstm.setString(1, user.getNome());
            pstm.setString(2, user.getSenha());

            pstm.execute();
            pstm.close();
            conexao.close();
            op = true;
        } catch (Exception e) {
            op = false;
            e.printStackTrace();
        }
        return op;
    }

    public Usuario Login(Usuario login, String ip) throws RemoteException {

        Connection c = getConexao(ip);
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = c.prepareStatement("select * from usuario where  nome = ? and senha = ?");
            ps.setString(1, login.getNome());
            ps.setString(2, login.getSenha());

            rs = ps.executeQuery();

            if (rs.next()) {
                Usuario user = new Usuario(rs.getInt("id"), rs.getString("nome"));
                return user;
            }
            ps.close();
            c.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void Publish(Conversa conv, String ip) throws RemoteException {
        try {
            Connection conexao = getConexao(ip);
            PreparedStatement pstm = conexao.prepareStatement("Insert into conversa(codus, texto)values(?,?)");
            pstm.setInt(1, conv.getCodus());
            pstm.setString(2, conv.getTexto());
            pstm.execute();

            pstm = conexao.prepareStatement("update subscribe SET msg = msg + 1  where  codsub = ?");
            pstm.setInt(1, conv.getCodus());
            pstm.execute();

            pstm.close();
            conexao.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Usuario> Membros(String ip) throws RemoteException {
        List<Usuario> lista = new ArrayList<>();
        try {
            Connection c = getConexao(ip);
            PreparedStatement ps = null;
            ResultSet rs = null;

            ps = c.prepareStatement("select * from usuario");

            rs = ps.executeQuery();
            while (rs.next()) {
                Usuario user = new Usuario(rs.getInt("id"), rs.getString("nome"), rs.getString("ip"), rs.getString("senha"));
                lista.add(user);
            }
            ps.close();
            c.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e);
        }
        return lista;
    }

    public void Subscribe(Subscribe sub, String ip) throws RemoteException {
        try {
            Connection conexao = getConexao(ip);
            PreparedStatement pstm = conexao.prepareStatement("Insert into subscribe(codus, codsub, msg)values(?,?,0)");
            pstm.setInt(1, sub.getCodus());
            pstm.setInt(2, sub.getCodsub());

            pstm.execute();
            pstm.close();
            conexao.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Usuario> followMembros(Usuario usuario, String ip) throws RemoteException {
        List<Usuario> lista = new ArrayList<>();
        try {
            Connection c = getConexao(ip);
            PreparedStatement ps = null;
            ResultSet rs = null;

            ps = c.prepareStatement("select usuario.id, usuario.nome from usuario, subscribe where subscribe.codus = ? and usuario.id = subscribe.codsub");
            ps.setInt(1, usuario.getId());

            rs = ps.executeQuery();
            while (rs.next()) {
                Usuario user = new Usuario(rs.getInt("id"), rs.getString("nome"));
                lista.add(user);
            }
            ps.close();
            c.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e);
        }
        return lista;
    }

    public void Follow(Subscribe sub, String ip) throws RemoteException {
        try {
            Connection conexao = getConexao(ip);
            PreparedStatement pstm = conexao.prepareStatement("DELETE FROM subscribe where codus = ? and codsub = ?");
            pstm.setInt(1, sub.getCodus());
            pstm.setInt(2, sub.getCodsub());

            pstm.execute();
            pstm.close();
            conexao.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Notify> Notify(Usuario userLog, String ip) throws RemoteException {
        List<Notify> notify = new ArrayList<>();
        Connection c = getConexao(ip);
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean loop = true;

        while (loop) {
            try {
                ps = c.prepareStatement("select * from subscribe where codus = ? and msg > 0");
                ps.setInt(1, userLog.getId());
                rs = ps.executeQuery();

                while (rs.next()) {
                    Notify not = new Notify(rs.getInt("codsub"), rs.getInt("msg"));
                    notify.add(not);
                    loop = false;
                }
                ps.close();
                c.close();
                return notify;
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(e);
            }
        }
        return null;
    }

    public List<Conversa> Consumir(int codus, int codsub, int qtdmsg, String ip) throws RemoteException {
        List<Conversa> lista = new ArrayList<>();
        try {
            Connection c = getConexao(ip);
            PreparedStatement ps = null;
            ResultSet rs = null;

            ps = c.prepareStatement("SELECT * FROM conversa, usuario Where conversa.codus = ? and usuario.id = conversa.codus order by conversa.id DESC limit ?");
            ps.setInt(1, codus);
            ps.setInt(2, qtdmsg);
            rs = ps.executeQuery();
            while (rs.next()) {
                Conversa conv = new Conversa(rs.getInt("codus"), rs.getString("nome"), rs.getString("texto"));
                lista.add(conv);
            }

            ps = c.prepareStatement("update subscribe SET msg = msg - ?  where  codsub = ? and codus = ?");
            ps.setInt(1, qtdmsg);
            ps.setInt(2, codus);
            ps.setInt(3, codsub);
            ps.execute();

            ps.close();
            c.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e);
        }
        return lista;
    }
}
