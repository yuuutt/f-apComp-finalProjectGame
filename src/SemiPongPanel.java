import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import javax.swing.JApplet;


public class SemiPongPanel extends JApplet implements BallHandler {
     
    Ball ball;
    private int width;
    private int height;
    private Ball[] balls;
    private BallPhysics physics;

    public SemiPongPanel(int width, int height) {
        this.width = width;
        this.height = height;
        setBackground(Color.white);
        setPreferredSize (new Dimension(width, height));

        balls = new Ball[20];
        for (int i = 0; i<20; i++){
            balls[i] = makeBall();
        }

        physics = new BallPhysics (this,this,10);
        handleTick();
        physics.setBallHandler(this);

    }

    public void paintComponent (Graphics g) {

         
        super.paintComponents(g);
        Rectangle bounds = getBounds();
        g.setColor(Color.lightGray);
        g.fillRect(0,0,bounds.width,bounds.height);

       for (Ball ball: balls) {
            ball.draw(g);

        }

        g.setColor(Color.black);

        g.drawString("Semi-Pong Part III by M. Lorenzana",10,15);

    }

    private Ball makeBall() {

        return new Ball(50,100,10,randomRange(10, 100),randomRange(-100, 100),Color.red); 

    }

    private double randomRange(int low, int high) {

        Random rand = new Random();

        return rand.nextInt(high-low+1) + low;  

    }


    @Override

    public void handleTick()   {

        physics = new BallPhysics (this,this,10);

        for (Ball ball : balls) {

            physics.addBall(ball);

        }

    }

	@Override
	public void hitLeftWall(Ball B) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hitRightWall(Ball B) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void collided(Ball b1, Ball b2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handleBall(Ball B) {
		// TODO Auto-generated method stub
		
	}

}
