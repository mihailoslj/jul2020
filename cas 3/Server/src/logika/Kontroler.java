/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logika;

import baza.DBBroker;
import domen.Meteorolog;
import domen.Region;
import java.util.ArrayList;

/**
 *
 * @author USER
 */
public class Kontroler {

    private static Kontroler instance;
    private DBBroker dbb;

    private Kontroler() {
        dbb = new DBBroker();
    }

    public static Kontroler getInstance() {
        if (instance == null) {
            instance = new Kontroler();
        }
        return instance;
    }

    public Meteorolog login(String username, String password) {
        return dbb.login(username, password);
    }

    public ArrayList<Region> vratiRegione() {
        return dbb.vratiRegione();
    }

}
