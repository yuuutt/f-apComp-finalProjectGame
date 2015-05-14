import processing.core.PApplet;
import processing.core.PImage;

public abstract class Entity extends PApplet{
	public PApplet p;
	public Map m;
	protected Location l; //current location
	private double radius; //used for hitboxes
	private boolean exists;
	
	
	public Entity(PApplet p, Location l, Map m){
		this.p = p;
		this.l = l;
		this.m = m;
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
