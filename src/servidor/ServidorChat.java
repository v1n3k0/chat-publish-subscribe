
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface ServidorChat extends Remote {
    public boolean Inserir (Usuario user, String ip) throws RemoteException;
    public Usuario Login(Usuario login, String ip) throws RemoteException;
    public void Publish (Conversa conv, String ip) throws RemoteException;
    public List<Usuario> Membros(String ip) throws RemoteException;
    public void Subscribe(Subscribe sub, String ip) throws RemoteException;
    public List<Usuario> followMembros(Usuario usuario, String ip) throws RemoteException;
    public void Follow(Subscribe sub, String ip) throws RemoteException;
    public List<Notify> Notify(Usuario userLog, String ip) throws RemoteException;
    public List<Conversa> Consumir (int codus, int codsub, int qtdmsg, String ip) throws RemoteException;
}
