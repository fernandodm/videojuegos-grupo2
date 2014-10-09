package soccer;

import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.appearances.Sprite;
import com.uqbar.vainilla.events.constants.Key;

public class Jugador extends GameComponent<SoccerScene>{
	
	private Key leftKey = Key.A;
	private Key rigthKey = Key.D;
	private Key upKey = Key.W;
	private Key downKey = Key.S;

	private boolean estaSeleccionado = false;
		
	private Sprite image;
	private double velocity;
	Hashtable<Integer,List<Sprite>> images=new Hashtable<Integer, List<Sprite>>();
	Hashtable<Integer, Integer> estados=new Hashtable<Integer, Integer>();
	final static int UP=1;
	final static int DOWN=2;
	final static int LEFT=3;
	final static int RIGHT=4;
	final static int UPRIGHT=5;
	final static int DOWNRIGHT=6;
	final static int UPLEFT=7;
	final static int DOWNLEFT=8;
	private int time=0;
	
	private LabelSeleccionado labelSeleccionado;
	public boolean flag=false;
	
	public LabelSeleccionado getLabelSeleccionado() {
		return labelSeleccionado;
	}

	public void setLabelSeleccionado(LabelSeleccionado labelSeleccionado) {
		this.labelSeleccionado = labelSeleccionado;
	}

	
	
	public Jugador(String imagePath, double vel, double x, double y, LabelSeleccionado label) {
		
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
		
		labelSeleccionado = label;
		velocity = vel;
	}
	
	public void agregarSprite(int direccion,int principio,int fin,int x,int y){
		
		List<Sprite> imagesCorriendo = new LinkedList<Sprite>();
		for (int i = principio; i < fin; i++) {
			imagesCorriendo.add(this.image.crop(x*i, y, 32, 25));
		}
		//este if esta xq el sprite para correr a la derecha
		//sigue en la segunda fila
		if(direccion == Jugador.RIGHT){
			for (int i = 1; i < 4; i++) {
				imagesCorriendo.add(this.image.crop(32*i, 32, 32, 25));
			}
		}
			
		images.put(direccion, imagesCorriendo);
	}
	
	@Override
	public void update(DeltaState deltaState) {
		
		if(isEstaSeleccionado()){
		int direccion = obtenerDireccion(deltaState);
			switch (direccion) {
			case UP:
				this.up(deltaState);
				this.ejecutarSprite(deltaState, Jugador.UP,0);
				break;
			case DOWN:
				this.down(deltaState);
				this.ejecutarSprite(deltaState, Jugador.DOWN,0);
				break;
			case LEFT:
				this.left(deltaState);
				this.ejecutarSprite(deltaState, Jugador.LEFT,0);
				break;
			case RIGHT:
				this.right(deltaState);
				this.ejecutarSprite(deltaState, Jugador.RIGHT,0);
				break;
			case UPRIGHT:
				this.upRight(deltaState);
				this.ejecutarSprite(deltaState, Jugador.UP,0.6);
				break;
			case UPLEFT:
				this.upLeft(deltaState);
				this.ejecutarSprite(deltaState, Jugador.UP,-0.6);
				break;
			case DOWNRIGHT:
				this.downRight(deltaState);
				this.ejecutarSprite(deltaState, Jugador.UP,2);
				break;
			case DOWNLEFT:
				this.downLeft(deltaState);
				this.ejecutarSprite(deltaState, Jugador.UP,-2);
				break;
			}			
		}
	}
	
	private int obtenerDireccion(DeltaState deltaState) {
		if (deltaState.isKeyBeingHold(upKey) && deltaState.isKeyBeingHold(rigthKey)) {
			return UPRIGHT;
		} 
		if (deltaState.isKeyBeingHold(upKey) && deltaState.isKeyBeingHold(leftKey)) {
			return UPLEFT;
		} 
		if (deltaState.isKeyBeingHold(downKey) && deltaState.isKeyBeingHold(rigthKey)) {
			return DOWNRIGHT;
		} 
		if (deltaState.isKeyBeingHold(downKey) && deltaState.isKeyBeingHold(leftKey)) {
			return DOWNLEFT;
		} 
		if (deltaState.isKeyBeingHold(upKey)) {
			return UP;
		} 
			
		if (deltaState.isKeyBeingHold(downKey)) {
			return DOWN;
		}
	
		if (deltaState.isKeyBeingHold(leftKey)) {
			return LEFT;
		} 
		if (deltaState.isKeyBeingHold(rigthKey)) {
			return RIGHT;
		}
		return 0;
	}

	public void ejecutarSprite(DeltaState deltaState, int direccion,double rotacion){
		if(time == 95){
			int estado = estados.get(direccion);
			
			this.setAppearance(images.get(direccion).get(estado).rotate(rotacion));
//			estados.replace(direccion, estado + 1);
//			estados.replace(direccion,(estado +1)%images.get(direccion).size());
			estados.put(direccion, estado + 1);
			estados.put(direccion,(estado +1)%images.get(direccion).size());
			time=0;
		}
		time++;		
	}
	
	private void downLeft(DeltaState deltaState) {
		double x = this.getX() - velocity * deltaState.getDelta();
		double y = this.getY() + velocity * deltaState.getDelta();
		if(this.getScene().getCancha().getY() == -930 ){
			this.setY(y);
			this.setX(x);
//			System.out.println(this.getY());
		}else{
//			this.setY(y);
				if(this.flag && this.getY() > 300){
					this.desplazarComponentes(-1,deltaState);
					this.setX(x);
				}else{
					this.setY(y);
					this.setX(x);
				}
		}
		this.labelSeleccionado.setX(x + 10);
		this.labelSeleccionado.setY(y + 30);
	}

	private void downRight(DeltaState deltaState) {
		double x = this.getX() + velocity * deltaState.getDelta();
		double y = this.getY() + velocity * deltaState.getDelta();
		
		if(this.getScene().getCancha().getY() == -930 ){
			this.setY(y);
			this.setX(x);
//			System.out.println(this.getY());
		}else{
//			this.setY(y);
				if(this.flag && this.getY() > 300){
					this.desplazarComponentes(-1,deltaState);
					this.setX(x);
				}else{
					this.setY(y);
					this.setX(x);
				}
		}
		this.labelSeleccionado.setX(x + 10);
		this.labelSeleccionado.setY(y + 30);
	}
	
	private void down(DeltaState deltaState) {
		double y = this.getY() + velocity * deltaState.getDelta();
		if(this.getScene().getCancha().getY() == -930 ){
			this.setY(y);
		}else{
				if(this.flag && this.getY() > 300){
					this.desplazarComponentes(-1,deltaState);
				}else{
					this.setY(y);
				}
		}
		this.labelSeleccionado.setY(y - 13);
	}
	

	private void right(DeltaState deltaState) {
		double x = this.getX() + velocity * deltaState.getDelta(); 
		this.setX(x);
		this.labelSeleccionado.setX(x + 8);
	}

	private void left(DeltaState deltaState) {
		double x = this.getX() - velocity * deltaState.getDelta();
		this.setX(x);
		this.labelSeleccionado.setX(x + 8);
	}
	
	private void up(DeltaState deltaState) {
		double y = this.getY() - velocity * deltaState.getDelta();
		if(this.getScene().getCancha().getY() == 0){
			this.setY(y);
		}else{
				if(this.flag && this.getY() < 300){
					this.desplazarComponentes(1,deltaState);
				}else{
					this.setY(y);
				}
		}
		this.labelSeleccionado.setY(y + 26);
		this.labelSeleccionado.setX(this.getX() + 6);
	}
	
	private void upRight(DeltaState deltaState) {
		double y = this.getY() - velocity* deltaState.getDelta();
		double x = this.getX() + velocity * deltaState.getDelta();
		if(this.getScene().getCancha().getY() == 0){
			this.setY(y);
			this.setX(x);
		}else{
				if(this.flag && this.getY() < 300){
					this.desplazarComponentes(1,deltaState);
					this.setX(x);
				}else{
					this.setY(y);
					this.setX(x);
				}
		}
		this.labelSeleccionado.setY(y + 26);
		this.labelSeleccionado.setX(this.getX() + 6);
	}
	
	private void upLeft(DeltaState deltaState) {
		double x = this.getX() - velocity * deltaState.getDelta();
		double y = this.getY() - velocity * deltaState.getDelta();
		if(this.getScene().getCancha().getY() == 0){
			this.setY(y);
			this.setX(x);
		}else{
				if(this.flag && this.getY() < 300){
					this.desplazarComponentes(1,deltaState);
					this.setX(x);
				}else{
					this.setY(y);
					this.setX(x);
				}
		}
		this.labelSeleccionado.setX(x + 10);
		this.labelSeleccionado.setY(y + 30);
	}
	
	private void desplazarComponentes(double n, DeltaState deltaState){
		for(Jugador x: this.getScene().getJugadores()){
			if(x !=this){
				x.setY(x.getY()+ (4/3*n));
			}
		}
		this.getScene().getArco().setY(this.getScene().getArco().getY()+ (4/3*n));
		
		this.getScene().getCancha().setY(this.getScene().getCancha().getY()+ (4/3*n));
		
		
	}
	
	
	public boolean isEstaSeleccionado() {
		return estaSeleccionado;
	}

	public void setEstaSeleccionado(boolean estaSeleccionado) {
		this.estaSeleccionado = estaSeleccionado;
	}

}
