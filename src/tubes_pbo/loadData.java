/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tubes_pbo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Wahyu Priyo W
 */
public class loadData {
    DefaultTableModel mknTabel, mnmTabel;
    BufferedReader bacamkn, bacamnm;
    
    public void mknloadData(JTable tabelMakanan){
        mknTabel = (DefaultTableModel) tabelMakanan.getModel();
        mknTabel.getDataVector().removeAllElements();
        String path = "src/data/makanan.txt";
        File file = new File(path);
        try {
            bacamkn = new BufferedReader(new FileReader(file));
            Object[] dataBaris = bacamkn.lines().toArray();
            for (int i = 0; 1 < dataBaris.length; i++) {
                String baris = dataBaris[i].toString();
                String[] data = baris.split("/");
                mknTabel.addRow(data);
            }
        } catch (Exception e) {
        }
    }
    public void mnmloadData(JTable tabelMinuman){
        mnmTabel = (DefaultTableModel) tabelMinuman.getModel();
        mnmTabel.getDataVector().removeAllElements();
        String path1 = "src/data/minuman.txt";
        File file1 = new File(path1);
        try {
            bacamnm = new BufferedReader(new FileReader(file1));
            Object[] dataBarismnm = bacamnm.lines().toArray();
            for (int i = 0; 1 < dataBarismnm.length; i++) {
                String barismnm = dataBarismnm[i].toString();
                String[] datamnm = barismnm.split("/");
                mnmTabel.addRow(datamnm);
            }
        } catch (Exception e) {
        }
    }
}