/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tubes_pbo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import static tubes_pbo.GUI.hargaText;
import static tubes_pbo.GUI.jumlahText;
import static tubes_pbo.GUI.makananText1;
import static tubes_pbo.GUI.minumanText1;
import static tubes_pbo.GUI.tabelMakanan;
import static tubes_pbo.GUI.tabelMinuman;

/**
 *
 * @author Pepita Neysa
 */
public class buttonAdd {
    DefaultTableModel mknTabel;
    DefaultTableModel mnmTabel;
    DefaultTableModel troli;
    String alamatStokmkn,alamatStokmnm;
    File fileStok1, fileStok2;
    BufferedWriter tulisStokmkn, tulisStokmnm;

    public void buttonAddmkn(JTable tabelMakanan, JTable tabelPesan, int a){
        mknTabel = (DefaultTableModel) tabelMakanan.getModel();
        troli = (DefaultTableModel) tabelPesan.getModel();
        int jml = Integer.parseInt(jumlahText.getText());
        String namaProduk = "";
        String[] data = new String[4];//array untuk dimasukan ke jTabel2
        boolean ready = false;//status kode barang tersedia di jtble1
        int stok = 0;
        int baris = 0;
        int harga = 0;
        try {
            
        } catch (Exception e) {
        }
        for (int i = 0; i < tabelMakanan.getRowCount(); i++) {
            if (makananText1.getText().equals(mknTabel.getValueAt(i, 0))) {
                namaProduk = mknTabel.getValueAt(i, 0).toString();
                harga = Integer.parseInt(mknTabel.getValueAt(i, 1).toString());
                stok = Integer.parseInt(mknTabel.getValueAt(i, 3).toString());
                baris = i;//kode di temukan pada baris ke-i
                ready = true;//bernilai true karena kode tersedia di jtble1
                break;
            }
        }
        
        if (ready && stok >= jml) {
            stok -= jml;//stok = stok-jumlah
            mknTabel.setValueAt(stok, baris, 3);//nilai stok berubah pada jtable1
            mnmTabel.setValueAt(stok, baris, 3);
            //Update();//update notepad
//            loadData();//refresh jtable1

            data[0] = namaProduk;
            data[1] = "" + harga;
            data[2] = "" + jml;
            data[3] = "" + (harga * jml);
            //tabelPesan.setModel(troli);
            troli.addRow(data);
           //Total();
        } else {
           JOptionPane.showMessageDialog(null, "Stok Kosong");
        }
        minumanText1.setText("");
        makananText1.setText("");
        hargaText.setText("");
        jumlahText.setText("");
        }
    }