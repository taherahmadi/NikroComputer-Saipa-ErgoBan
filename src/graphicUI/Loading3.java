package graphicUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author Tahe
 */
public class Loading3 extends JDialog implements Runnable {

    private static final long serialVersionUID = 6018550646609037291L;
    public boolean loading = false;
    public JLabel circle;
    public JLabel message;

    public Loading3(JFrame owner) {
//		super(owner);
        this.setLayout(new BorderLayout());
        this.setSize(300, 300);
        this.setLocationRelativeTo(owner);
        this.setModal(true);

            try {
                circle= new JLabel(new ImageIcon(new URL("file:./Files/Images/loading/l2.gif")));
            } catch (MalformedURLException ex) {
                Logger.getLogger(Loading3.class.getName()).log(Level.SEVERE, null, ex);
            }
                this.add(circle,BorderLayout.CENTER);
//                
//                message = new JLabel("لطفا شکیبا باشید");
//                this.add(message,BorderLayout.SOUTH);
        this.setUndecorated(true);
        this.setBackground(new Color(0, 0, 0, 1));
//		setOpacity((float) 0.5);
    }

    public static void main(String[] args) {
        Loading3 l = new Loading3(null);
        new Thread(l).start();
        try {
            Thread.sleep(5000);
            
        } catch (InterruptedException ex) {

        }
        l.stop();
    }

    public void stop() {
        this.loading = false;
        this.dispose();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        try {
            g.drawImage(new ImageIcon(new URL("file:./Files/Images/loading/loading2.png"))
                    .getImage(), 0, 0, 300, 300, null);
//            g.drawImage(new ImageIcon(new URL("file:./Files/Images/loading/l2.gif"))
//                    .getImage(), 50, 40, 200, 200, null);
        } catch (MalformedURLException ex) {
            Logger.getLogger(Loading3.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void paintAll(Graphics g) {
//        super.paintAll(g); //To change body of generated methods, choose Tools | Templates.
        try {
            g.drawImage(new ImageIcon(new URL("file:./Files/Images/loading/loading.png"))
                    .getImage(), 0, 0, 300, 300, null);
//            g.drawImage(new ImageIcon(new URL("file:./Files/Images/loading/l2.gif"))
//                    .getImage(), 50, 40, 200, 200, null);
        } catch (MalformedURLException ex) {
            Logger.getLogger(Loading3.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void paintComponents(Graphics g) {
//        super.paintComponents(g); //To change body of generated methods, choose Tools | Templates.
    try {
            g.drawImage(new ImageIcon(new URL("file:./Files/Images/loading/loading2.png"))
                    .getImage(), 0, 0, 300, 300, null);
//            g.drawImage(new ImageIcon(new URL("file:./Files/Images/loading/l2.gif"))
//                    .getImage(), 50, 40, 200, 200, null);
        } catch (MalformedURLException ex) {
            Logger.getLogger(Loading3.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    

    @Override
    public void run() {
        this.setVisible(true);
    }

}
