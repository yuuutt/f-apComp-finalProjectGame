import java.util.ArrayList;
import processing.core.PApplet;


public class Map {
	public PApplet p;
	public ArrayList<Player> player;
	private ArrayList<Enemy> enemies;
	private int w, h;
	private int score;
	
	public Map(PApplet p, int w, int h){
		this.p = p;
		this.w = w;
		this.h = h;
		enemies = new ArrayList<Enemy>();
		player = new ArrayList<Player>();
		
		
	}
	
//	public boolean isGameOver(){
//		if(exists) 
//		return !player.exists();
//	}

	public int getWidth(){
		return w;
	}

	public int getHeight(){
		return h;
	}

	public void add(Enemy e){
		enemies.add(e);
	}
	
//	public void setPlayer(PApplet p, Location l, Map m){
//		player = new Player(p, l, m);
//	}

	public void remove(Entity e){
		enemies.remove(e);
	}
}
