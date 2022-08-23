/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author abc
 */
public class Prognoza implements Serializable{
    private int prognozaID;
    private Date dan;
    private String opis;
    private Meteorolog meteorolog;
    private ArrayList<PrognozaRegion> listaPrognozaZaRegione; //"'Сачувај прогнозу за дан' клијент програм шаље захтев серверу да 
                                //                             сачува прогнозу за дан (подаци се чувају на страни серверског 
                                //                             програма у табелама Prognoza и PrognozaRegion"

    public Prognoza(int prognozaID, Date dan, String opis, Meteorolog meteorolog, ArrayList<PrognozaRegion> listaPrognozaZaRegione) {
        this.prognozaID = prognozaID;
        this.dan = dan;
        this.opis = opis;
        this.meteorolog = meteorolog;
        this.listaPrognozaZaRegione = listaPrognozaZaRegione;
    }
    
    public Prognoza() {
    }

    public int getPrognozaID() {
        return prognozaID;
    }

    public void setPrognozaID(int prognozaID) {
        this.prognozaID = prognozaID;
    }

    public Date getDan() {
        return dan;
    }

    public void setDan(Date dan) {
        this.dan = dan;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public Meteorolog getMeteorolog() {
        return meteorolog;
    }

    public void setMeteorolog(Meteorolog meteorolog) {
        this.meteorolog = meteorolog;
    }

    public ArrayList<PrognozaRegion> getListaPrognozaZaRegione() {
        return listaPrognozaZaRegione;
    }

    public void setListaPrognozaZaRegione(ArrayList<PrognozaRegion> listaPrognozaZaRegione) {
        this.listaPrognozaZaRegione = listaPrognozaZaRegione;
    }
}
