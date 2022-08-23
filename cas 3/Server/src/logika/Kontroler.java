/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logika;

import baza.DBBroker;
import domen.Meteorolog;
import domen.Prognoza;
import domen.Region;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

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

    public boolean sacuvajPrognozu(Prognoza p) {//ova metoda u dbbrokeru moze da baci gresku (throws exception) te 
                                                //je ovaj ovde kod okruzujemo sa try-catch da mozemo da je uhvatimo
        try {
            return dbb.sacuvajPrognozu(p);
        } catch (SQLException ex) {
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

}
