/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author v1n1c1u5
 */
public class Usuario implements java.io.Serializable {

    private int id;
    private String nome;
    private String ip;
    private String senha;

    public Usuario(int id, String nome, String ip, String senha) {
        this.id = id;
        this.nome = nome;
        this.ip = ip;
        this.senha = senha;
    }
    
    public Usuario(String nome, String ip, String senha) {
        this.nome = nome;
        this.ip = ip;
        this.senha = senha;
    }
    
    public Usuario(int id, String nome, String ip) {
        this.id = id;
        this.nome = nome;
        this.ip = ip;
    }
    
    public Usuario(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Usuario(String nome, String senha) {
        this.nome = nome;
        this.senha = senha;
    }
    
    public Usuario(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.ip = senha;
    }
    
    public String toString() {
        return this.nome;
    }
}
