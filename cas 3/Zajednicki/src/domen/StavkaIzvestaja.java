/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Mihailo
 */
public class StavkaIzvestaja implements Serializable{
    
    private String meteorolog;
    private Date dan;
    private String prognozaZaRegione;

    public StavkaIzvestaja(String meteorolog, Date dan, String prognozaZaRegione) {
        this.meteorolog = meteorolog;
        this.dan = dan;
        this.prognozaZaRegione = prognozaZaRegione;
    }

    public StavkaIzvestaja() {
    }
    

    public String getMeteorolog() {
        return meteorolog;
    }

    public void setMeteorolog(String meteorolog) {
        this.meteorolog = meteorolog;
    }

    public Date getDan() {
        return dan;
    }

    public void setDan(Date dan) {
        this.dan = dan;
    }

    public String getPrognozaZaRegione() {
        return prognozaZaRegione;
    }

    public void setPrognozaZaRegione(String prognozaZaRegione) {
        this.prognozaZaRegione = prognozaZaRegione;
    }
    
}
