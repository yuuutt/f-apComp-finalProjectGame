import java.awt.Color;

import javax.swing.JApplet;
import javax.swing.JFrame;


public class SemiPong extends JApplet {

    public static void main (String [] args) {

        SemiPongPanel panel = new SemiPongPanel(400,300);

    }

public void init() {

        JFrame pongframe = new JFrame("Semi-Pong");
        pongframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        SemiPongPanel panel = new SemiPongPanel(400,300);
        setBackground (Color.white);
        pongframe.getContentPane().add(panel);
        pongframe.pack();
        pongframe.setVisible(true);

   }

}
