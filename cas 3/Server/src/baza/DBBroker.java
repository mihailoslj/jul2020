/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baza;

import domen.Meteorolog;
import domen.Region;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author USER
 */
public class DBBroker {

    public ArrayList<Object> vrati() {
        ArrayList<Object> lista = new ArrayList<>();
        String upit = "";
        try {
            Statement st = Konekcija.getInstance().getConnection().createStatement();
            ResultSet rs = st.executeQuery(upit);
            while (rs.next()) {

            }
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }

        return lista;
    }

    public boolean cuvajIzmeniBrisi() throws Exception {
        String upit = "";
        try {
            PreparedStatement ps = Konekcija.getInstance().getConnection().prepareStatement(upit);

            ps.executeUpdate();
            Konekcija.getInstance().getConnection().commit();

            return true;

        } catch (SQLException ex) {
            Konekcija.getInstance().getConnection().rollback();
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public Meteorolog login(String username, String password) {
        String upit = "SELECT * FROM meteorolog";
        try {
            Statement st = Konekcija.getInstance().getConnection().createStatement();
            ResultSet rs = st.executeQuery(upit); //RosultSet je tabela u Javi
            while (rs.next()) {
                Meteorolog meteorolog = new Meteorolog(rs.getInt("MeteorologID"), 
                        rs.getString("Ime"), rs.getString("Prezime"), 
                        rs.getString("KorisnickoIme"), rs.getString("Lozinka"));
                
                //provera da li je ispravno korisnicko ime i lozinka
                if(meteorolog.getKorisnickoIme().equals(username)
                        && meteorolog.getLozinka().equals(password)){
                    return meteorolog;
                }

            }
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public ArrayList<Region> vratiRegione() {
        ArrayList<Region> lista = new ArrayList<>();
        String upit = "SELECT * FROM REGION";
        try {
            Statement st = Konekcija.getInstance().getConnection().createStatement();
            ResultSet rs = st.executeQuery(upit);
            while (rs.next()) {
                
                Region r = new Region(rs.getInt(1),rs.getString(2),rs.getString(3));//mogu da se pisu i redni brojevi mesto                                                                                          //naziva kolona
                lista.add(r);

            }
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }

        return lista;
    }

}
