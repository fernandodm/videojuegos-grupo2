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

		
	private Sprite image2;
	private Sprite image1;
	private double velocity;
	Hashtable<Integer,List<Sprite>> images=new Hashtable<Integer, List<Sprite>>();
	final static int UP=1;
	final static int DOWN=2;
	final static int LEFT=3;
	final static int RIGHT=4;
	
	public Jugador(String imagePath1, String imagePath2, double vel, double x, double y) {
		
		super(Sprite.fromImage(imagePath1), x, y);

		this.image2 = Sprite.fromImage(imagePath2);
		this.image1 = this.image2.crop(32, 25);
			
		List<Sprite> imagesCorriendoArriba = new LinkedList<Sprite>();
		for (int i = 0; i < 8; i++) {
			imagesCorriendoArriba.add(this.image2.crop(32*i,0,32, 25));
		}
		images.put(Jugador.UP, imagesCorriendoArriba);
		
		List<Sprite> imagesCorriendoAbajo = new LinkedList<Sprite>();
		for (int i = 13; i < 19; i++) {
			imagesCorriendoAbajo.add(this.image2.crop(32*i,32,32, 25));
		}
		images.put(Jugador.DOWN, imagesCorriendoAbajo);
		
		List<Sprite> imagesCorriendoIzquierda = new LinkedList<Sprite>();
		for (int i = 9; i < 16; i++) {
			imagesCorriendoIzquierda.add(this.image2.crop(32*i,64,32, 25));
		}
		images.put(Jugador.LEFT, imagesCorriendoIzquierda);
		
		List<Sprite> imagesCorriendoDerecha = new LinkedList<Sprite>();
		for (int i = 16; i < 19; i++) {
			imagesCorriendoDerecha.add(this.image2.crop(32*i,0,32, 25));
		}
		images.put(Jugador.RIGHT, imagesCorriendoDerecha);
		
		velocity = vel;
		
	}
	

	int estadoUp=0;
	int estadoDown=0;
	int estadoLeft=0;
	int estadoRight=0;
	@Override
	public void update(DeltaState deltaState) {
		
		if (deltaState.isKeyBeingHold(upKey)) {
			this.up(deltaState);
			this.ejecutarSpriteUp(deltaState, Jugador.UP);
		} 
		if (deltaState.isKeyBeingHold(downKey)) {
			this.down(deltaState);
			this.ejecutarSpriteDown(deltaState, Jugador.DOWN);
		}
		
		if (deltaState.isKeyBeingHold(leftKey)) {
			this.left(deltaState);
			this.ejecutarSpriteLeft(deltaState, Jugador.LEFT);
		} 
		if (deltaState.isKeyBeingHold(rigthKey)) {
			this.right(deltaState);
			this.ejecutarSpriteRight(deltaState, Jugador.RIGHT);
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
/////////////////FEOOOOOOOOOOOOOOOO///////////////////////////
	public void ejecutarSpriteUp(DeltaState deltaState, int direccion){
		
		this.setAppearance(images.get(direccion).get(estadoUp));
		estadoUp++;
		estadoUp=(estadoUp +1)%images.get(direccion).size();
		Thread t = new Thread();
		try {
			t.sleep(85);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void ejecutarSpriteDown(DeltaState deltaState, int direccion){
		
		this.setAppearance(images.get(direccion).get(estadoDown));
		estadoDown++;
		estadoDown=(estadoDown +1)%images.get(direccion).size();
		Thread t = new Thread();
		try {
			t.sleep(85);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void ejecutarSpriteLeft(DeltaState deltaState, int direccion){
	
	this.setAppearance(images.get(direccion).get(estadoLeft));
	estadoLeft++;
	estadoLeft=(estadoLeft +1)%images.get(direccion).size();
	Thread t = new Thread();
	try {
		t.sleep(85);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}

	public void ejecutarSpriteRight(DeltaState deltaState, int direccion){
	
	this.setAppearance(images.get(direccion).get(estadoRight));
	estadoRight++;
	estadoRight=(estadoRight +1)%images.get(direccion).size();
	Thread t = new Thread();
	try {
		t.sleep(85);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}
////////////////////////////	//////////////////////////////////////
	private void up(DeltaState deltaState) {
		this.setY(this.getY() - velocity
				* deltaState.getDelta());
	}
	
	private void down(DeltaState deltaState) {
		this.setY(this.getY() + velocity
				* deltaState.getDelta());
	}

}
