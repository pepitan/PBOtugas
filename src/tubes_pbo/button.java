/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tubes_pbo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
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
public class button {

    DefaultTableModel mknTabel;
    DefaultTableModel mnmTabel;
    DefaultTableModel troli;
    String alamatStokmkn, alamatStokmnm;
    File fileStok1, fileStok2;
    BufferedWriter tulisStokmkn, tulisStokmnm;

    
    public void Update() {
        alamatStokmkn = "src/data/makanan.txt";
        alamatStokmnm = "src/data/minuman.txt";
        fileStok1 = new File(alamatStokmkn);
        fileStok2 = new File(alamatStokmnm);
        try {
            tulisStokmkn = new BufferedWriter(new FileWriter(fileStok1));
            for (int i = 0; i < tabelMakanan.getRowCount(); i++) {
                for (int j = 0; j < tabelMakanan.getColumnCount(); j++) {
                    if (j > 0) {
                        tulisStokmkn.write("/");
                    }
                    tulisStokmkn.write(tabelMakanan.getValueAt(i, j).toString());
                }
                tulisStokmkn.newLine();
            }
            tulisStokmkn.close();
            
            tulisStokmnm = new BufferedWriter(new FileWriter(fileStok2));
            for (int i = 0; i < tabelMinuman.getRowCount(); i++) {
                for (int j = 0; j < tabelMinuman.getColumnCount(); j++) {
                    if (j > 0) {
                        tulisStokmnm.write("/");
                    }
                    tulisStokmnm.write(tabelMinuman.getValueAt(i, j).toString());
                }
                tulisStokmnm.newLine();
            }
            tulisStokmnm.close();
        } catch (Exception e) {
        }
    }
    
    public void hapus(JTable tabelPesan) {
        troli = (DefaultTableModel) tabelPesan.getModel();
        int baris = tabelPesan.getSelectedRow();
        troli.removeRow(baris);
        Update();
        JOptionPane.showMessageDialog(null, "Data berhasil dihapus");
    }
    
    public void buttonAddmkn(JTable tabelMakanan, JTable tabelPesan, int a) {
        mknTabel = (DefaultTableModel) tabelMakanan.getModel();
        String nama = mknTabel.getValueAt(a, 0).toString();
        String harga = mknTabel.getValueAt(a, 1).toString();
        String jumlah = jumlahText.getText();
        //kurangi stok
        String strstok = mknTabel.getValueAt(a, 3).toString();
        int hrg = Integer.parseInt(harga);
        int stok = Integer.parseInt(strstok) - 1;
        int subtxt = Integer.parseInt(jumlah);
        int sub = subtxt * hrg;
        if (stok < 0) {
            JOptionPane.showMessageDialog(null, "Stok Habis");
        } else {
            mknTabel.setValueAt(Integer.toString(stok), a, 3);
            List data = new ArrayList<>();
            Update();
            data.add(nama);
            data.add(harga);
            data.add(jumlah);
            data.add(sub);

            //masuk keranjang
            DefaultTableModel mdl = (DefaultTableModel) tabelPesan.getModel();
            mdl.addRow(data.toArray());

            makananText1.setText("");
            hargaText.setText("");
            jumlahText.setText("");
        }
    }

    public void buttonAddmnm(JTable tabelMinuman, JTable tabelPesan, int a) {
        mnmTabel = (DefaultTableModel) tabelMinuman.getModel();
        String nama = mnmTabel.getValueAt(a, 0).toString();
        String harga = mnmTabel.getValueAt(a, 1).toString();
        String jumlah = jumlahText.getText();
        //kurangi stok
        String strstok = mnmTabel.getValueAt(a, 3).toString();
        int hrg = Integer.parseInt(harga);
        int stok = Integer.parseInt(strstok) - 1;
        int subtxt = Integer.parseInt(jumlah);
        int sub = subtxt * hrg;
        if (stok < 0) {
            JOptionPane.showMessageDialog(null, "Stok Habis");
        } else {
            mnmTabel.setValueAt(Integer.toString(stok), a, 3);
            List data1 = new ArrayList<>();
            Update();
            data1.add(nama);
            data1.add(harga);
            data1.add(jumlah);
            data1.add(sub);

            //masuk keranjang
            DefaultTableModel md2 = (DefaultTableModel) tabelPesan.getModel();
            md2.addRow(data1.toArray());

            minumanText1.setText("");
            hargaText.setText("");
            jumlahText.setText("");
        }
    }
}
