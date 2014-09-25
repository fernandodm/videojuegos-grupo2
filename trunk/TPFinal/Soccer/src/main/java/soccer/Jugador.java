package soccer;

import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.appearances.Sprite;
import com.uqbar.vainilla.events.constants.Key;

public class Jugador extends GameComponent<SoccerScene>{
	
	private Key leftKey = Key.LEFT;
	private Key rigthKey = Key.RIGHT;
	private Key upKey = Key.UP;
	private Key downKey = Key.DOWN;

		
	private Sprite image;
	private double velocity;
	Hashtable<Integer,List<Sprite>> images=new Hashtable<Integer, List<Sprite>>();
	Hashtable<Integer, Integer> estados=new Hashtable<Integer, Integer>();
	final static int UP=1;
	final static int DOWN=2;
	final static int LEFT=3;
	final static int RIGHT=4;
	
	public Jugador(String imagePath, double vel, double x, double y) {
		
		super(Sprite.fromImage(imagePath).crop(0,0,32,25), x, y);
		
		estados.put(Jugador.UP, 0);
		estados.put(Jugador.DOWN, 0);
		estados.put(Jugador.LEFT, 0);
		estados.put(Jugador.RIGHT, 0);
		
		this.image = Sprite.fromImage(imagePath);
		
		agregarSprite(Jugador.UP,1,8,32,0);
		agregarSprite(Jugador.DOWN,13,19,32,32);
		agregarSprite(Jugador.LEFT,9,16,32,64);
		agregarSprite(Jugador.RIGHT,16,19,32,0);
		
		velocity = vel;
		
	}
	
	public void agregarSprite(int direccion,int principio,int fin,int x,int y){
		
		List<Sprite> imagesCorriendo = new LinkedList<Sprite>();
		for (int i = principio; i < fin; i++) {
			imagesCorriendo.add(this.image.crop(x*i, y, 32, 25));
		}
		images.put(direccion, imagesCorriendo);
		
	}
	
	@Override
	public void update(DeltaState deltaState) {
		
		if (deltaState.isKeyBeingHold(upKey)) {
			this.up(deltaState);
			this.ejecutarSprite(deltaState, Jugador.UP);
		} 
		if (deltaState.isKeyBeingHold(downKey)) {
			this.down(deltaState);
			this.ejecutarSprite(deltaState, Jugador.DOWN);
		}
		
		if (deltaState.isKeyBeingHold(leftKey)) {
			this.left(deltaState);
			this.ejecutarSprite(deltaState, Jugador.LEFT);
		} 
		if (deltaState.isKeyBeingHold(rigthKey)) {
			this.right(deltaState);
			this.ejecutarSprite(deltaState, Jugador.RIGHT);
		}

	}
	
	private void right(DeltaState deltaState) {
		this.setX(this.getX() + velocity
				* deltaState.getDelta());		
	}

	private void left(DeltaState deltaState) {
		this.setX(this.getX() - velocity
				* deltaState.getDelta());
		
	}
	
	public void ejecutarSprite(DeltaState deltaState, int direccion){
		
		int estado = estados.get(direccion);
		
		this.setAppearance(images.get(direccion).get(estado));
		estados.replace(direccion, estado + 1);
		estados.replace(direccion,(estado +1)%images.get(direccion).size());
		
		try {
			Thread.sleep(85);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private void up(DeltaState deltaState) {
		this.setY(this.getY() - velocity
				* deltaState.getDelta());
	}
	
	private void down(DeltaState deltaState) {
		this.setY(this.getY() + velocity
				* deltaState.getDelta());
	}

}
