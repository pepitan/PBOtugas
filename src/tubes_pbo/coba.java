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
public class coba {
    
    public void ut(){
        
    }
        public static void main(String[] args) throws InterruptedException {
        GUI gui = new GUI();
        gui.setVisible(true);
        gui.jLabel3.setText("Pemesan");
        gui.jLabel5.setText("Makanan");
        gui.jLabel4.setText("Minuman");
        gui.jLabel6.setText("Harga");
        gui.jLabel2.setText("Jumlah");
        gui.btnDelete.setText("Delete");
        gui.btnAdd.setText("Add");
        gui.tabMenu.setTitleAt(0, "Makanan");
        gui.tabMenu.setTitleAt(1, "Minuman");
        
//        for(int i=0; i<=100; i++){
//            Thread.sleep(40);
//            splash.setVisible(true);
//            splash.jLabel1.setText("Loading . .");
//            splash.jProgressBar1.setValue(i);
//            
//            if(i==100){
//                splash.dispose();
//            }
//        }
    }
}
