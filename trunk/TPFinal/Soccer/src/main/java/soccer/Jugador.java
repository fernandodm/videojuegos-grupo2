package soccer;

import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.appearances.Sprite;

public class Jugador extends GameComponent<SoccerScene>{
	

	private boolean estaSeleccionado = false;	
	private Sprite image;
	private double velocity;
	Hashtable<Integer,List<Sprite>> images=new Hashtable<Integer, List<Sprite>>();
	Hashtable<Integer, Integer> estados=new Hashtable<Integer, Integer>();
	private int time=0;
	private LabelSeleccionado labelSeleccionado;
	public boolean flag=false;

	
	public Jugador(String imagePath, double vel, double x, double y, LabelSeleccionado label) {
		
		super(Sprite.fromImage(imagePath).crop(0,0,32,25), x, y);
		estados.put(Direccion.UP, 0);
		estados.put(Direccion.DOWN, 0);
		estados.put(Direccion.LEFT, 0);
		estados.put(Direccion.RIGHT, 0);
		
		this.image = Sprite.fromImage(imagePath);
		
		agregarSprite(Direccion.UP,1,8,32,0);
		agregarSprite(Direccion.DOWN,13,19,32,32);
		agregarSprite(Direccion.LEFT,9,16,32,64);
		agregarSprite(Direccion.RIGHT,16,19,32,0);
		
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
		if(direccion == Direccion.RIGHT){
			for (int i = 1; i < 4; i++) {
				imagesCorriendo.add(this.image.crop(32*i, 32, 32, 25));
			}
		}
			
		images.put(direccion, imagesCorriendo);
	}
	
	@Override
	public void update(DeltaState deltaState) {
		
		if(isEstaSeleccionado()){
		int direccion = Direccion.obtenerDireccion(deltaState);
			switch (direccion) {
			case Direccion.UP:
				this.up(deltaState);
				this.ejecutarSprite(deltaState, Direccion.UP,0);
				break;
			case Direccion.DOWN:
				this.down(deltaState);
				this.ejecutarSprite(deltaState, Direccion.DOWN,0);
				break;
			case Direccion.LEFT:
				this.left(deltaState);
				this.ejecutarSprite(deltaState, Direccion.LEFT,0);
				break;
			case Direccion.RIGHT:
				this.right(deltaState);
				this.ejecutarSprite(deltaState, Direccion.RIGHT,0);
				break;
			case Direccion.UPRIGHT:
				this.upRight(deltaState);
				this.ejecutarSprite(deltaState, Direccion.UP,0.6);
				break;
			case Direccion.UPLEFT:
				this.upLeft(deltaState);
				this.ejecutarSprite(deltaState, Direccion.UP,-0.6);
				break;
			case Direccion.DOWNRIGHT:
				this.downRight(deltaState);
				this.ejecutarSprite(deltaState, Direccion.UP,2);
				break;
			case Direccion.DOWNLEFT:
				this.downLeft(deltaState);
				this.ejecutarSprite(deltaState, Direccion.UP,-2);
				break;
			}			
		}
	}

	public void ejecutarSprite(DeltaState deltaState, int direccion,double rotacion){
		if(time == 95){
			int estado = estados.get(direccion);
			
			this.setAppearance(images.get(direccion).get(estado).rotate(rotacion));
			estados.put(direccion, estado + 1);
			estados.put(direccion,(estado +1)%images.get(direccion).size());
			time=0;
		}
		time++;		
	}
	
	private void downLeft(DeltaState deltaState) {
		double x = this.getX() - velocity * deltaState.getDelta();
		double y = this.getY() + velocity * deltaState.getDelta();
		if(!Desplazador.getInstance().hayQueDesplazarCamara()){
			this.setY(y);
			this.setX(x);
		}else{
			if(this.flag && this.getY() > 300){
				this.desplazarComponentes(-1,deltaState);
				this.setX(x);
			}else{
				this.setY(y);
				this.setX(x);
			}
		}
		
		this.labelSeleccionado.setX(x + 10);
		this.labelSeleccionado.setY(y - 10);
	}

	private void downRight(DeltaState deltaState) {
		double x = this.getX() + velocity * deltaState.getDelta();
		double y = this.getY() + velocity * deltaState.getDelta();
		
		if(!Desplazador.getInstance().hayQueDesplazarCamara() ){
			this.setY(y);
			this.setX(x);
		}else{
			if(this.flag && this.getY() > 300){
				this.desplazarComponentes(-1,deltaState);
				this.setX(x);
			}else{
				this.setY(y);
				this.setX(x);
			}
		}
		this.labelSeleccionado.setX(x + 10);
		this.labelSeleccionado.setY(y - 15);
	}
	
	private void down(DeltaState deltaState) {
		double y = this.getY() + velocity * deltaState.getDelta();
		if(!Desplazador.getInstance().hayQueDesplazarCamara()){
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
		if(!Desplazador.getInstance().hayQueDesplazarCamara()){
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
		if(!Desplazador.getInstance().hayQueDesplazarCamara()){
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
		if(!Desplazador.getInstance().hayQueDesplazarCamara()){
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
				x.setY(x.getY()+ (0.4*n));
			}
		}
		
		this.getScene().getArcos().get(0).setY(this.getScene().getArcos().get(0).getY()+ (0.4*n));
		this.getScene().getArcos().get(1).setY(this.getScene().getArcos().get(1).getY()+ (0.4*n));
		this.getScene().getCancha().setY(this.getScene().getCancha().getY()+ (0.4*n));
		
		
	}
	
	public LabelSeleccionado getLabelSeleccionado() {
		return labelSeleccionado;
	}

	public void setLabelSeleccionado(LabelSeleccionado labelSeleccionado) {
		this.labelSeleccionado = labelSeleccionado;
	}
	
	public boolean isEstaSeleccionado() {
		return estaSeleccionado;
	}

	public void setEstaSeleccionado(boolean estaSeleccionado) {
		this.estaSeleccionado = estaSeleccionado;
	}

}
