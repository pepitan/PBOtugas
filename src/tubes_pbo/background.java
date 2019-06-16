package tubes_pbo;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.JPanel;
import javax.swing.ImageIcon;

public class background extends JPanel{
    Image gambar1;
    public background(){
        gambar1 = new ImageIcon(getClass().getResource("/gambar/bg_01.jpeg")).getImage();
//        gambar2 = new ImageIcon(getClass().getResource("/gambar/bg_02.jpeg")).getImage();
    }
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D gd = (Graphics2D)g.create();
        gd.drawImage(gambar1, 0, 0, getWidth(), getHeight(), null);
        gd.dispose();
    }
}
