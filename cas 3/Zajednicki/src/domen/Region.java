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
public class Region implements Serializable{
    private int regionID;
    private String naziv;
    private String opis;

    @Override
    public String toString() {
        return naziv;
    }

    public Region(int regionID, String naziv, String opis) {
        this.regionID = regionID;
        this.naziv = naziv;
        this.opis = opis;
    }

    public Region() {
    }

    public int getRegionID() {
        return regionID;
    }

    public void setRegionID(int regionID) {
        this.regionID = regionID;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }
}
