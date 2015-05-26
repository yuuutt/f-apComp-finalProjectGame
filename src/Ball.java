import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;


public class Ball {
	private double x;
	private double y;
	private double radius;
	private Color color;
    private double velocityX;
    private double velocityY;
	private int low;
	private int high;

	public Ball () {
	
	}
	    
	public Ball (double x, double y, double radius, double velocityX, double velocityY, Color color) {
		
		this.x=x;
	    this.y=y;
	    this.radius=radius;
	    this.color=color;
	    this.velocityX = velocityX;
	    this.velocityY = velocityY;
	
	}
	
	public Ball (double x, double y, double radius, Color color) {
	
	    this.x=x;
	    this.y=y;
	    this.radius=radius;
	    this.color=color;
	    this.velocityX =0;
	    this.velocityY = 0;
	
	}
	
	public void draw (Graphics g) {
	    
		g.setColor(color);
	    g.fillOval((int)(x-radius),(int)(y-radius),(int)(radius*2),(int)(radius*2));
	
	}
	
	public double getX() {
	
	    return x;
	
	}
	
	public void setX(double x) {
	
		this.x = x;

	}

	public double getY() {

	    return y;
	
	}
	
	public void setY(double y) {

	    this.y = y;
	
	}

	public double getRadius() {
	
	    return radius;

	}

	public void setRadius(double radius) {

	    this.radius = radius;
	
	}
	
	public Color getColor() {
	
	    return color;

	}

	public void setColor(Color color) {

	    this.color = color;

	}

	public double getVelocityX() {
	
	    return velocityX;
	
	}
	
	public void setVelocityX(double velocityX) {
	
	    this.velocityX = velocityX;
	
	}
	
	public double getVelocityY() {
	
	    return velocityY;
	
	}
	
	public void setVelocityY(double velocityY) {
	
	    this.velocityY = velocityY;
	
	}
	
	public void setCollided(){
	
	    setColor(Color.RED);
	
	}
	 
	
	public boolean intersectsBall(Ball B) {
	
	    if (Math.pow(this.getX()-B.getX(),2)+Math.pow(this.getY()-B.getY(),2) < Math.pow(this.getRadius()+B.getRadius(),2)) {
	
	        return true;
	
	    }
	
	       else {
	
	           return false;
	
	       }
	
	         
	
	}
}


