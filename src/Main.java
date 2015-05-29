import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.Map;

import javax.swing.JApplet;
import javax.swing.JComponent;
import processing.core.PApplet;

public class Main extends PApplet implements KeyListener, MouseMotionListener{
	Font bigFont;
	Color weirdColor;
    Color bgColor;
    Image offImage;
    Graphics offGraphics;
    Location loc;
    protected int currentMouseX, currentMouseY;
    protected int previousMouseX, previousMouseY;
    protected int xx=100,yy=450,w=400,l=30;
    double dir, trueing = 0;
    Map map;
    boolean gameStart = false;
    

    public void init() { 
    	setSize(600, 600);
    	addKeyListener(this);
        addMouseMotionListener(this);
       
//    	map = new Map(this, 600, 600);
    	bigFont = new Font("Times New Roman", Font.BOLD, 16);
    	bgColor = Color.white;
    	setBackground(bgColor);
    	
    }
    
    
	public void stop() {
    
    }

    public void paint(Graphics g) { 
    
    	 update(g);
    	 
    }
    
    public void update(Graphics g) {
    	if (offGraphics == null) {
    		offImage = createImage(800,600);
    		offGraphics = offImage.getGraphics();
    		
    	}

    	offGraphics.setColor(Color.WHITE);
    	offGraphics.fillRect(0,0,800,600);

    	Graphics2D g2d = (Graphics2D)offGraphics;
    	offGraphics.setColor(Color.BLACK);
    	g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
    	    
    	int rx = xx+ (w/2);
    	int ry = yy+ (l/2);
    	g2d.rotate(dir,rx,ry);
    	g2d.draw(new Rectangle(xx, yy, w, l));
    	
    	

    	offGraphics.setColor(getForeground());
    	g.drawImage(offImage, 0, 0, this);
    	
    }
    
    public void mouseMoved(MouseEvent e){
    	currentMouseX=e.getX();
        currentMouseY=e.getY();
        	
        dir = Math.toRadians(Math.atan2(previousMouseY-currentMouseY, previousMouseX-currentMouseY)/2*Math.PI);
        	
        this.repaint();  
        
        previousMouseX = currentMouseX;
        previousMouseY = currentMouseY;
       }
    
    public void mouseClicked(MouseEvent e) {
        if (!gameStart) {
            showStatus("game has started");
        	gameStart = true;
        } else {
        	
        }
        	
        this.repaint();
    }
	


}
