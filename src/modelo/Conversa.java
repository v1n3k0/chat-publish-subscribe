/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author v1n1c1u5
 */
public class Conversa implements java.io.Serializable{
    private int id;
    String nome;
    private int codus;
    private String texto;
    
    public Conversa (int id, int codus, String nome, String texto){
        this.id = id;
        this.codus = codus;
        this.nome = nome;
        this.texto = texto;
    }
    public Conversa (int id, int codus, String texto){
        this.id = id;
        this.codus = codus;
        this.texto = texto;
    }
    
    public Conversa (int codus, String nome, String texto){
        this.codus = codus;
        this.nome = nome;
        this.texto = texto;
    }
    
    public Conversa (int codus, String texto){
        this.codus = codus;
        this.texto = texto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCodus() {
        return codus;
    }

    public void setCodus(int codus) {
        this.codus = codus;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }
}
