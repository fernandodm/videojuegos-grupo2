package soccer;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import soccer.estados.EstadoJugador;
import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.appearances.Sprite;

public abstract class Jugador extends GameComponent<SoccerScene>{
	
	private EstadoJugador estado;	
	private boolean estaSeleccionado = false;	
	private Sprite image;
	private double velocity;
	Hashtable<Integer, Integer> estados=new Hashtable<Integer, Integer>();
	private int time=0;
	private LabelSeleccionado labelSeleccionado;
	//esto es para mantener bien el focus y saber si el jugador tiene la pelota
	public boolean flag=false;
	Hashtable<Integer,List<Sprite>> images=new Hashtable<Integer, List<Sprite>>();
	
	public Jugador(Sprite sprite, String imagePath, LabelSeleccionado label, double vel, double x, double y) {
		super(sprite, x, y);
		estados.put(Direccion.UP, 0);
		estados.put(Direccion.DOWN, 0);
		estados.put(Direccion.LEFT, 0);
		estados.put(Direccion.RIGHT, 0);
		this.setLabelSeleccionado(label);
		this.setVelocity(vel);
		this.setImage(Sprite.fromImage(imagePath));
	}
	
	public abstract void agregarSprite(int direccion,int principio,int fin,int x,int y);
	public abstract Equipo equipoContrario();
	
	public Sprite getImage() {
		return image;
	}

	public void setImage(Sprite image) {
		this.image = image;
	}

	public double getVelocity() {
		return velocity;
	}

	public void setVelocity(double velocity) {
		this.velocity = velocity;
	}

	public Hashtable<Integer, Integer> getEstados() {
		return estados;
	}

	public void setEstados(Hashtable<Integer, Integer> estados) {
		this.estados = estados;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	@Override
	public void update(DeltaState deltaState) {
		
		this.estado.update(deltaState);
		
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
	
	public void downLeft(DeltaState deltaState) {
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

	public void downRight(DeltaState deltaState) {
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
	
	public void down(DeltaState deltaState) {
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
	

	public void right(DeltaState deltaState) {
		double x = this.getX() + velocity * deltaState.getDelta(); 
		this.setX(x);
		this.labelSeleccionado.setX(x + 8);
	}

	public void left(DeltaState deltaState) {
		double x = this.getX() - velocity * deltaState.getDelta();
		this.setX(x);
		this.labelSeleccionado.setX(x + 8);
	}
	
	public void up(DeltaState deltaState) {
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
	
	public void upRight(DeltaState deltaState) {
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
	
	public void upLeft(DeltaState deltaState) {
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
		List<Jugador> jugadores = new ArrayList<Jugador>();
		jugadores.addAll(this.getScene().getJugadoresLocales());
		jugadores.addAll(this.getScene().getJugadoresVisitantes());
		for(Jugador x: jugadores){
			if(x !=this){
				x.setY(x.getY()+ (0.4*n));
			}
		}
		
		this.getScene().getArcos().get(0).setY(this.getScene().getArcos().get(0).getY()+ (0.4*n));
		this.getScene().getArcos().get(1).setY(this.getScene().getArcos().get(1).getY()+ (0.4*n));
		this.getScene().getCancha().setY(this.getScene().getCancha().getY()+ (0.4*n));
//		this.getScene().getTiempo().setY(this.getScene().getTiempo().getY());
		
	}
	

	public Equipo obtenerEquipoVisitante(){
		return this.getScene().getEquipoVisitante();
	}
	
	public Equipo obtenerEquipoLocal(){
		return this.getScene().getEquipoLocal();
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
	
	public EstadoJugador getEstado() {
		return estado;
	}

	public void setEstado(EstadoJugador estado) {
		this.estado = estado;
	}
	
	public Hashtable<Integer, List<Sprite>> getImages() {
		return images;
	}

	public void setImages(Hashtable<Integer, List<Sprite>> images) {
		this.images = images;
	}

	public abstract boolean isLocal();

}
