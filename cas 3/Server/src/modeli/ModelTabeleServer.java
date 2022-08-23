/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeli;

import domen.StavkaIzvestaja;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

public class ModelTabeleServer extends AbstractTableModel {

    ArrayList<StavkaIzvestaja> lista;
    String[] kolone = {"Meteorolog", "Dan", "Prognoza za regione"};

    public ModelTabeleServer() {
        lista = new ArrayList<>();
    }

    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public String getColumnName(int i) {
        return kolone[i];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        StavkaIzvestaja si = lista.get(rowIndex);
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        
        switch (columnIndex) {
            case 0:
                return si.getMeteorolog();
            case 1:
                return sdf.format(si.getDan()); //sdf.parse -pretvara String u Date, sdf.format - formatira Date
            case 2:
                return si.getPrognozaZaRegione();

            default:
                return "return!";
        }
    }

    public void dodajizvestaj(ArrayList<StavkaIzvestaja> listaBaza) {
        lista = listaBaza;
        fireTableDataChanged();
    }

}
