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
public class PrognozaRegion implements Serializable{
    private Prognoza prognoza;
    private int rb;
    private double temperatura;
    private String meteoAlarm;
    private String pojava;
    private Region region;

    public PrognozaRegion(Prognoza prognoza, int rb, double temperatura, String meteoAlarm, String pojava, Region region) {
        this.prognoza = prognoza;
        this.rb = rb;
        this.temperatura = temperatura;
        this.meteoAlarm = meteoAlarm;
        this.pojava = pojava;
        this.region = region;
    }

    public PrognozaRegion() {
    }

    public Prognoza getPrognoza() {
        return prognoza;
    }

    public void setPrognoza(Prognoza prognoza) {
        this.prognoza = prognoza;
    }

    public int getRb() {
        return rb;
    }

    public void setRb(int rb) {
        this.rb = rb;
    }

    public double getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(double temperatura) {
        this.temperatura = temperatura;
    }

    public String getMeteoAlarm() {
        return meteoAlarm;
    }

    public void setMeteoAlarm(String meteoAlarm) {
        this.meteoAlarm = meteoAlarm;
    }

    public String getPojava() {
        return pojava;
    }

    public void setPojava(String pojava) {
        this.pojava = pojava;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }
}
