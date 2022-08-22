/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeli;

import domen.PrognozaRegion;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

public class ModelTabeleKlijent extends AbstractTableModel {

    ArrayList<PrognozaRegion> lista;
    String[] kolone = {"Temperatura","Region", "Meteo alarm","Pojava"};

    public ModelTabeleKlijent() {
        lista = new ArrayList<>();
        //ovaj kontsrukor ne samo da inicijalizuje listu VEC I POKRECE OVE 4 METODE ISPOD
    }

    @Override
    public int getRowCount() { //vraca koliko redova ima u tabeli; tj koliko unosa; tj koliko ce elemenata da ima lista
        return lista.size();
    }

    @Override
    public int getColumnCount() { //vraca koliko ima atributa/kolona
        return kolone.length;
    }

    @Override
    public String getColumnName(int i) { //vraca naziv kolone po indeksu
        return kolone[i];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) { //postavlja vrednosti celija; tj popunjava tabelu
        PrognozaRegion pr = lista.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return pr.getTemperatura();
            case 1:
                return pr.getRegion();
            case 2:
                return pr.getMeteoAlarm();
            case 3:
                return pr.getPojava();
            default:
                return "return!";
        }
    }

    public void dodajPrognozu(PrognozaRegion pr) {
        lista.add(pr);
        fireTableDataChanged(); //ovo se uvek kuca kad treba da se update-uje tabela
    }

    public void obrisiPrognozu(int row) {
        lista.remove(row);
        fireTableDataChanged();
    }

   

}
