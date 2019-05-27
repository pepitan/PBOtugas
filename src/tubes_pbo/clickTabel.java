/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tubes_pbo;

import javax.swing.table.DefaultTableModel;
import static tubes_pbo.GUI.hargaText;
import static tubes_pbo.GUI.makananText1;
import static tubes_pbo.GUI.minumanText1;
import static tubes_pbo.GUI.tabelMakanan;
import static tubes_pbo.GUI.tabelMinuman;

/**
 *
 * @author Wahyu Priyo W
 */
public class clickTabel {
    DefaultTableModel mknTabel, mnmTabel;
    public void clicktbMkn(){
        mknTabel = (DefaultTableModel) tabelMakanan.getModel();
        int larikmkn = tabelMakanan.getSelectedRow();
        makananText1.setText("" + mknTabel.getValueAt(larikmkn, 0).toString());
        hargaText.setText("" + mknTabel.getValueAt(larikmkn, 1).toString());
    }
    public void clicktbMnm(){
        mnmTabel = (DefaultTableModel) tabelMinuman.getModel();
        int larikmnm = tabelMinuman.getSelectedRow();
        minumanText1.setText("" + mnmTabel.getValueAt(larikmnm, 0).toString());
        hargaText.setText("" + mnmTabel.getValueAt(larikmnm, 1).toString());
    }
}
