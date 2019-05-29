/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tubes_pbo;

/**
 *
 * @author Wahyu Priyo W
 */
public class main {

    public static void main(String[] args) throws InterruptedException {
        load ding = new load();
        for (int i = 0; i <= 100; i++) {
            Thread.sleep(25);
            ding.setVisible(true);
            ding.setLocationRelativeTo(null);
            ding.jLabel1.setText("Loading your menu, Please Wait");
            ding.jProgressBar1.setValue(i);

            if (i == 100) {
                GUI gui = new GUI();
                gui.setVisible(true);
                gui.setLocationRelativeTo(null);
                ding.dispose();
            }
        }

    }
}
