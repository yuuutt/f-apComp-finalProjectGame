import java.awt.Color;

import processing.core.PApplet;
import processing.core.PImage;

public abstract class Entity extends PApplet{
	public PApplet p;
	public Map m;
	protected Location l; //current location
	private double radius; //used for hitboxes
	private boolean exists;
	private Color color;
    private double velocityX;
    private double velocityY;
	
	public Entity(PApplet p, Location l, Map m, double radius, double velocityX, double velocityY, Color color){
		this.p = p;
		this.l = l;
		this.m = m;
	    this.radius=radius;
	    this.color=color;
	    this.velocityX = velocityX;
	    this.velocityY = velocityY;
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
	    } else {
	           return false;
	    }
	         
	
	}
	
	
	public double getRadius(){
		return radius;
	}
	
	public void setRadius(double radius){
		this.radius = radius;
	}
	
	public boolean exists(){
		return exists;
	}
	
	public void remove(){
		exists = false;
	}
	
	public Location getLoc(){
		return l;
	}
	
	public void setLoc(Location l){
		this.l = l;
	}
	
	public static boolean isColliding(Entity a, Entity b){
		if (Location.distance(a.getLoc(), b.getLoc()) <= a.radius + b.radius) return true;
		return false;
	}
	
	public void act(){
		
		move();
	}
	
	public void draw(){
		p.ellipse((float)l.getX(), (float)l.getY(), (float)radius, (float)radius);
	}
	
	public void move(){
//		setLoc(new Location(l.getX() + move.x, l.getY() + move.y));
	}
	
}
