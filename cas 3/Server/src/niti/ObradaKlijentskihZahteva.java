/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package niti;

import domen.Meteorolog;
import domen.Prognoza;
import domen.Region;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import konstante.Operacije;
import logika.Kontroler;
import transfer.KlijentskiZahtev;
import transfer.ServerskiOdgovor;

/**
 *
 * @author USER
 */
public class ObradaKlijentskihZahteva extends Thread {

    private Socket s;

    public ObradaKlijentskihZahteva(Socket s) {
        this.s = s;
    }

    @Override
    public void run() {
        while (true) {
            KlijentskiZahtev kz = primiZahtev();
            ServerskiOdgovor so = new ServerskiOdgovor();
            switch (kz.getOperacija()) {
                case Operacije.LOGIN:
                    HashMap<String, String> mapa = (HashMap<String, String>) kz.getParametar(); //sta primam od Klijenta
                    String username = mapa.get("username");
                    String password = mapa.get("password");
                    
                    Meteorolog meteorolog = Kontroler.getInstance().login(username, password); //sta prosledjujem i dobijam kao
                                                                                               //odgovor od servera
                    
                    so.setOdgovor(meteorolog);                                                 //sta radim sa dobijenim podacima
                                                                                               //tj sta saljem nazad
                    break;
                    
                case Operacije.VRATI_REGIONE:
                    ArrayList<Region> regioni = Kontroler.getInstance().vratiRegione();
                    so.setOdgovor(regioni);
                    break;
                case Operacije.SACUVAJ_PROGNOZU:
                    Prognoza p = (Prognoza) kz.getParametar();
                    boolean uspesno = Kontroler.getInstance().sacuvajPrognozu(p);
                    so.setOdgovor(uspesno);
                    break;
                case Operacije.DA_LI_POSTOJI_U_BAZI:
                    Date dan = (Date) kz.getParametar();
                    boolean postoji = Kontroler.getInstance().postojiUBazi(dan);
                    so.setOdgovor(postoji);
                    break;

            }
            posaljiOdgovor(so);
        }
    }

    private KlijentskiZahtev primiZahtev() {
        try {
            ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
            return (KlijentskiZahtev) ois.readObject();
        } catch (IOException | ClassNotFoundException ex) {
            System.out.println("KLIJENT SE ODVEZAO (UGASIO/LA SI KLIJENTSKU APLIKACIJU),"
                    + " ZATO SE DESIO OVAJ EXCEPTION, NE BRINI SE NISTA! ARI");
            Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private void posaljiOdgovor(ServerskiOdgovor so) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
            oos.writeObject(so);
            oos.flush();
        } catch (IOException ex) {
            Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
