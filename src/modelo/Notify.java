/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Edmar
 */
    public class Notify implements java.io.Serializable{
    private int codus;
    private int qtdmsg;
    
    public Notify (int codus, int qtdmsg){
        this.codus = codus;
        this.qtdmsg = qtdmsg;
    }
   
    public int getCodus() {
        return codus;
    }

    public void setCodus(int codus) {
        this.codus = codus;
    }

    public int getQtdmsg() {
        return qtdmsg;
    }

    public void setQtdmsg(int qtdmsg) {
        this.qtdmsg = qtdmsg;
    }
}

    

