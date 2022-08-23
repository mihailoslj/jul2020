/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baza;

import domen.Meteorolog;
import domen.Prognoza;
import domen.PrognozaRegion;
import domen.Region;
import domen.StavkaIzvestaja;
import java.sql.Date;
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

    public ArrayList<Object> vrati() { //ovo uvek kopiram kad radim SELECT upit, za sve ostalo kopiram iz cuvajIzmeniBrisi()
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
                if (meteorolog.getKorisnickoIme().equals(username)
                        && meteorolog.getLozinka().equals(password)) {
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

                Region r = new Region(rs.getInt(1), rs.getString(2), rs.getString(3));//mogu da se pisu i redni brojevi mesto                                                                                          //naziva kolona
                lista.add(r);

            }
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }

        return lista;
    }

    public boolean sacuvajPrognozu(Prognoza p) throws SQLException { //ovo thows Exception sam dodao jer mi lampica rekla
        //da se moze javiti SQL exception; ne pisem try-catch
        String upit = "INSERT INTO PROGNOZA VALUES (?,?,?,?)";
        int prognozaID = vratiPrognozuID();
        try {
            PreparedStatement ps = Konekcija.getInstance().getConnection().prepareStatement(upit); //sta ubacujemo u bazu
            ps.setInt(1, prognozaID); //prvi parametar na koje mesto(kolonu) ce da se upise, a drugo sta se upisuje
            ps.setDate(2, new Date(p.getDan().getTime())); //ovo sve jer moramo rucno da pretvorimo Date u tip iz sql.java
            ps.setString(3, p.getOpis());
            ps.setInt(4, p.getMeteorolog().getMeteorologID());

            p.setPrognozaID(prognozaID);

            ps.executeUpdate(); //ubacujemo u bazu

            //ako se desi greska kod ubacivanja prognoza za regione da ne moze da ubaci prognozu
            //"ne mozemo da unesemo racun a da ne unesemo stavke racuna"
            if (sacuvajPrognozeZaRegione(p)) {
                Konekcija.getInstance().getConnection().commit(); //potvrdjujemo izmenu
                return true;
            } else {
                Konekcija.getInstance().getConnection().rollback();
                return false;
            }

        } catch (SQLException ex) {
            Konekcija.getInstance().getConnection().rollback();
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    private int vratiPrognozuID() {
        String upit = "SELECT MAX(prognozaid) FROM prognoza";
        int id = 0;
        try {
            Statement st = Konekcija.getInstance().getConnection().createStatement();
            ResultSet rs = st.executeQuery(upit);
            while (rs.next()) { // nije morao while ovde, dobije se tabela velicine 1x1
                id = rs.getInt(1); //moglo je i rs.get("MAX[PROGNOZAID]") jer je tako naziv kolone u tabeli
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ++id;
    }

    private boolean sacuvajPrognozeZaRegione(Prognoza p) throws SQLException {
        String upit = "INSERT INTO prognozaregion VALUES (?,?,?,?,?,?)";
        try {
            PreparedStatement ps = Konekcija.getInstance().getConnection().prepareStatement(upit);

            int rb = 0;
            for (PrognozaRegion prog : p.getListaPrognozaZaRegione()) {
                ps.setInt(1, p.getPrognozaID());
                ps.setInt(2, ++rb);
                ps.setDouble(3, prog.getTemperatura());
                ps.setString(4, prog.getMeteoAlarm());
                ps.setString(5, prog.getPojava());
                ps.setInt(6, prog.getRegion().getRegionID());

                ps.addBatch(); //zato sto ps.executeUpdate() ubacuje samo jedan red u tablu pa je ovo nacin da se ubaci vise njih

            }

            ps.executeBatch(); //zamnenjeno je ps.executeUpdate()
            Konekcija.getInstance().getConnection().commit();

            return true;

        } catch (SQLException ex) {
            Konekcija.getInstance().getConnection().rollback();
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public ArrayList<StavkaIzvestaja> vratiIzvestaj(String dodatniUpit) {
        ArrayList<StavkaIzvestaja> lista = new ArrayList<>();
        String upit = "SELECT CONCAT(m.ime, ' ', m.prezime) AS meteorolog, p.dan AS dan, "
                + "GROUP_CONCAT(r.naziv, ': ', pzr.temperatura, ' (', pzr.meteoalarm, "
                + "', ', pzr.pojava, ')' SEPARATOR '; ') AS prognoza_za_regione "
                + "FROM meteorolog m JOIN prognoza p ON (m.meteorologid = p.meteorologid) "
                + "JOIN prognozaregion pzr ON (p.prognozaid = pzr.prognozaid) "
                + "JOIN region r ON (pzr.regionid = r.regionid) "
                + dodatniUpit
                + "GROUP BY meteorolog, dan";
        try {
            Statement st = Konekcija.getInstance().getConnection().createStatement();
            ResultSet rs = st.executeQuery(upit);
            while (rs.next()) {
                StavkaIzvestaja si = new StavkaIzvestaja(rs.getString(1), rs.getDate(2), rs.getString(3));
                lista.add(si);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }

        return lista;

    }

}
