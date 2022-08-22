/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.io.Serializable;

/**
 *
 * @author abc
 */
public class Meteorolog implements Serializable{
    private int meteorologID;
    private String ime;
    private String prezime;
    private String korisnickoIme;
    private String lozinka;

    public Meteorolog(int meteorologID, String ime, String prezime, String korisnickoIme, String lozinka) {
        this.meteorologID = meteorologID;
        this.ime = ime;
        this.prezime = prezime;
        this.korisnickoIme = korisnickoIme;
        this.lozinka = lozinka;
    }

    public Meteorolog() {
    }

    public int getMeteorologID() {
        return meteorologID;
    }

    public void setMeteorologID(int meteorologID) {
        this.meteorologID = meteorologID;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    public String getLozinka() {
        return lozinka;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }
}
