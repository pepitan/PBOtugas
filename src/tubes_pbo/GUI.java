/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tubes_pbo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Wahyu Priyo
 */
public class GUI extends javax.swing.JFrame {

    String alamatStokmkn,alamatStokmnm;
    String pemesan, hrg, jml, makanan;
    File fileStok1, fileStok2;
    BufferedReader baca, bacamnm;
    BufferedWriter tulisStokmkn, tulisStokmnm;
    DefaultTableModel mknTabel;
    DefaultTableModel mnmTabel;
    DefaultTableModel troli;
    String[] data, keranjang;

    public GUI() {
        initComponents();
        mknTabel = (DefaultTableModel) tabelMakanan.getModel();
        mnmTabel = (DefaultTableModel) tabelMinuman.getModel();
        troli = (DefaultTableModel) tabelPesan.getModel();
        pemesan = pemesanText.getText();
        makanan = hargaText.getText();
        jml = jumlahText.getText();
//        jml=jumlah.getText();
        loadData();
    }

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

    public void tableMknClick() {
        int larikmkn = tabelMakanan.getSelectedRow();
        makananText1.setText("" + mknTabel.getValueAt(larikmkn, 0).toString());
        hargaText.setText("" + mknTabel.getValueAt(larikmkn, 1).toString());
    }

    public void tableMnmClick() {
        int larikmnm = tabelMinuman.getSelectedRow();
        minumanText1.setText("" + mnmTabel.getValueAt(larikmnm, 0).toString());
        hargaText.setText("" + mnmTabel.getValueAt(larikmnm, 1).toString());
    }

    public void buttonClick() {
        int jml = Integer.parseInt(jumlahText.getText());
        String namaProduk = "";
        String[] data = new String[4];//array untuk dimasukan ke jTabel2
        boolean ready = false;//status kode barang tersedia di jtble1
        int stok = 0;
        int baris = 0;
        int harga = 0;
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
        
        for (int i = 0; i < tabelMinuman.getRowCount(); i++) {
            if (minumanText1.getText().equals(mnmTabel.getValueAt(i, 0))) {
                namaProduk = mnmTabel.getValueAt(i, 0).toString();
                harga = Integer.parseInt(mnmTabel.getValueAt(i, 1).toString());
                stok = Integer.parseInt(mnmTabel.getValueAt(i, 3).toString());
                baris = i;//kode di temukan pada baris ke-i
                ready = true;//bernilai true karena kode tersedia di jtble1
                break;
            }
        }
        
        if (ready && stok >= jml) {
            stok -= jml;//stok = stok-jumlah
            mknTabel.setValueAt(stok, baris, 3);//nilai stok berubah pada jtable1
            mnmTabel.setValueAt(stok, baris, 3);
            Update();//update notepad
            loadData();//refresh jtable1

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

    public void loadData() {
        mknTabel.getDataVector().removeAllElements();
        String path = "src/data/makanan.txt";
        File file = new File(path);
        try {
            baca = new BufferedReader(new FileReader(file));
            Object[] dataBaris = baca.lines().toArray();
            for (int i = 0; 1 < dataBaris.length; i++) {
                String baris = dataBaris[i].toString();
                String[] data = baris.split("/");
                mknTabel.addRow(data);
            }
        } catch (Exception e) {
        }
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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     *
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabelPesan = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jumlahText = new javax.swing.JTextField();
        pemesanText = new javax.swing.JTextField();
        hargaText = new javax.swing.JTextField();
        makananText1 = new javax.swing.JTextField();
        minumanText1 = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        ta1 = new javax.swing.JTextField();
        tabMenu = new javax.swing.JTabbedPane();
        jScrollPane3 = new javax.swing.JScrollPane();
        tabelMakanan = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        tabelMinuman = new javax.swing.JTable();
        btnDelete = new javax.swing.JButton();
        btnAdd = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(917, 630));
        getContentPane().setLayout(null);

        jPanel1.setBackground(new java.awt.Color(57, 57, 203));
        jPanel1.setMinimumSize(new java.awt.Dimension(900, 600));
        jPanel1.setPreferredSize(new java.awt.Dimension(900, 600));
        jPanel1.setLayout(null);

        tabelPesan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Makanan", "Harga", "Jumlah", "Subtotal"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tabelPesan);

        jPanel1.add(jScrollPane2);
        jScrollPane2.setBounds(430, 280, 452, 210);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(jLabel2);
        jLabel2.setBounds(40, 320, 90, 30);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(jLabel3);
        jLabel3.setBounds(40, 110, 90, 30);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(jLabel4);
        jLabel4.setBounds(40, 220, 90, 30);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(jLabel5);
        jLabel5.setBounds(40, 160, 90, 30);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(jLabel6);
        jLabel6.setBounds(40, 270, 90, 30);
        jPanel1.add(jumlahText);
        jumlahText.setBounds(150, 320, 210, 30);
        jPanel1.add(pemesanText);
        pemesanText.setBounds(150, 120, 210, 30);

        hargaText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hargaTextActionPerformed(evt);
            }
        });
        jPanel1.add(hargaText);
        hargaText.setBounds(150, 270, 210, 30);
        jPanel1.add(makananText1);
        makananText1.setBounds(150, 170, 210, 30);
        jPanel1.add(minumanText1);
        minumanText1.setBounds(150, 220, 210, 30);

        jButton3.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jButton3.setText("Bayar");
        jPanel1.add(jButton3);
        jButton3.setBounds(40, 490, 110, 50);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Total yang harus anda bayarkan");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(40, 390, 230, 30);

        ta1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ta1ActionPerformed(evt);
            }
        });
        jPanel1.add(ta1);
        ta1.setBounds(40, 430, 220, 30);

        tabMenu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabMenuMouseClicked(evt);
            }
        });

        tabelMakanan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Makanan", "Harga", "Ketersediaan", "Stock"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tabelMakanan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tabelMakananMouseReleased(evt);
            }
        });
        jScrollPane3.setViewportView(tabelMakanan);

        tabMenu.addTab("", jScrollPane3);

        tabelMinuman.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Minuman", "Harga", "Ketersediaan", "Stock"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tabelMinuman.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tabelMinumanMouseReleased(evt);
            }
        });
        jScrollPane4.setViewportView(tabelMinuman);

        tabMenu.addTab("", jScrollPane4);

        jPanel1.add(tabMenu);
        tabMenu.setBounds(430, 30, 450, 230);
        jPanel1.add(btnDelete);
        btnDelete.setBounds(150, 360, 80, 30);

        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });
        jPanel1.add(btnAdd);
        btnAdd.setBounds(270, 360, 90, 30);

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton1.setText("Total");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1);
        jButton1.setBounds(610, 510, 120, 40);

        getContentPane().add(jPanel1);
        jPanel1.setBounds(0, 0, 900, 600);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        buttonClick();
    }//GEN-LAST:event_btnAddActionPerformed

    private void hargaTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hargaTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_hargaTextActionPerformed

    private void tabelMinumanMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelMinumanMouseReleased
        tableMnmClick();
    }//GEN-LAST:event_tabelMinumanMouseReleased

    private void tabelMakananMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelMakananMouseReleased
        tableMknClick();
    }//GEN-LAST:event_tabelMakananMouseReleased

    private void tabMenuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabMenuMouseClicked
        makananText1.setText("");
        hargaText.setText("");
        minumanText1.setText("");
    }//GEN-LAST:event_tabMenuMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
         // TODO add your handling code here:
         int x = 0;
         for (int y = 0; y < tabelPesan.getRowCount(); y++) {
            x+=Integer.parseInt(tabelPesan.getValueAt(y, 3).toString());
        }
         ta1.setText(x+"");
    }//GEN-LAST:event_jButton1ActionPerformed

    private void ta1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ta1ActionPerformed
         // TODO add your handling code here:
    }//GEN-LAST:event_ta1ActionPerformed
    /**
     * @param args the command line arguments
     */
    //Disini
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new GUI().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JButton btnAdd;
    public static javax.swing.JButton btnDelete;
    public static javax.swing.JTextField hargaText;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    public static javax.swing.JLabel jLabel2;
    public static javax.swing.JLabel jLabel3;
    public static javax.swing.JLabel jLabel4;
    public static javax.swing.JLabel jLabel5;
    public static javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    public static javax.swing.JTextField jumlahText;
    public static javax.swing.JTextField makananText1;
    public static javax.swing.JTextField minumanText1;
    public static javax.swing.JTextField pemesanText;
    private javax.swing.JTextField ta1;
    public static javax.swing.JTabbedPane tabMenu;
    public static javax.swing.JTable tabelMakanan;
    public static javax.swing.JTable tabelMinuman;
    private javax.swing.JTable tabelPesan;
    // End of variables declaration//GEN-END:variables
}
