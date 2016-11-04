/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author v1n1c1u5
 */
public class Subscribe implements java.io.Serializable{

    private int id;
    private int codus;
    private int codsub;

    public Subscribe(int id, int codus, int codsub) {
        this.id = id;
        this.codus = codus;
        this.codsub = codsub;
    }
    
    public Subscribe(int codus, int codsub) {
        this.codus = codus;
        this.codsub = codsub;
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

    public int getCodsub() {
        return codsub;
    }

    public void setCodsub(int codsub) {
        this.codsub = codsub;
    }
}
