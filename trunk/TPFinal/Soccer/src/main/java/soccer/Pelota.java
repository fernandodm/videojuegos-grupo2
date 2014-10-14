package soccer;

import java.util.ArrayList;
import java.util.List;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.appearances.Sprite;
import com.uqbar.vainilla.events.constants.Key;

public class Pelota extends GameComponent<SoccerScene>{
	
	private Vector direccion;
	private double velocidad;
	private Sprite image;
	private int time;
	int estado = 0;
	private ArrayList<Sprite> listSprites = new ArrayList<Sprite>();
	
	public Pelota(String pelotaPath, Vector vector, double velocidad){
		
		super((Sprite.fromImage(pelotaPath).crop(0,0,63,63).scaleTo(15,15)),650, 300);
		this.direccion = vector.asVersor();
		this.velocidad = velocidad;	
	
		this.image = Sprite.fromImage(pelotaPath);
		
		
		for (int i = 0 ; i < 2; i++) {
			for(int j = 0; j< 3; j++ ){
				listSprites.add(this.image.crop(63*i, 63*j,63,63).scaleTo(15, 15));
			}
		}
	}

	public Vector getDireccion() {
		return direccion;
	}

	public void setDireccion(Vector direccion) {
		this.direccion = direccion;
	}

	public double getVelocidad() {
		return velocidad;
	}

	public void setVelocidad(double velocidad) {
		this.velocidad = velocidad;
	}
	
	
	public boolean enRemate=false;
	private int direccionRemate;
	private double potencia;
	
	@Override
	public void update(DeltaState deltaState) {
		List<Jugador> jugadores = super.getScene().getJugadores(); 
	
		for (Jugador jugador : jugadores) {
			Vector nuevaPosicion = new Vector(jugador.getX(), jugador.getY());
				if(Colision.mustApply(this, jugador, nuevaPosicion) && jugador.isEstaSeleccionado()){
					ejecutarMovimiento(deltaState, jugador.getX(), jugador.getY());
					jugador.flag = true;

					activarRemate(deltaState);
					
				}else{
					jugador.flag = false;
				}
			}
		
		moverPelotaPorRemate(deltaState);
		
		

		super.update(deltaState);
	}

	private void moverPelotaPorRemate(DeltaState deltaState) {
		
		if(this.enRemate){
				if(Desplazador.getInstance().hayQueDesplazarCamara()){
					moverCamara(deltaState);
				}else{
					moverPelota();
				}
		}
	}
	
	private void moverCamara(DeltaState deltaState) {
		switch (direccionRemate) {
		case Direccion.UP:
			Desplazador.getInstance().desplazarComponentes(potencia, deltaState);
			break;
		case Direccion.UPRIGHT:
			Desplazador.getInstance().desplazarComponentes(potencia, deltaState);
			break;
		case Direccion.UPLEFT:
			Desplazador.getInstance().desplazarComponentes(potencia, deltaState);
			break;
		case Direccion.DOWN:
			Desplazador.getInstance().desplazarComponentes(potencia*-1, deltaState);
			break;
		case Direccion.DOWNRIGHT:
			Desplazador.getInstance().desplazarComponentes(potencia*-1, deltaState);
			break;
		case Direccion.DOWNLEFT:
			Desplazador.getInstance().desplazarComponentes(potencia*-1, deltaState);
			break;
		default:
			moverPelota();
			break;
		}
		this.ejecutarSpritePelota();
		potencia-=1;
		if(potencia < 0){
			enRemate=false;
		}
	}

	private void moverPelota() {
		//Si la pelota esta en remate
			switch (direccionRemate) {
			case Direccion.UP:
				this.setY(getY()-potencia);
				this.ejecutarSpritePelota();
				break;
			case Direccion.DOWN:
				this.setY(getY()+potencia);
				this.ejecutarSpritePelota();
				break;
			case Direccion.LEFT:
				this.setX(getX()-potencia);
				this.ejecutarSpritePelota();
				break;
			case Direccion.RIGHT:
				this.setX(getX()+potencia);
				this.ejecutarSpritePelota();
				break;
			}
			this.ejecutarSpritePelota();
			potencia-=1;
			if(potencia < 0){
				enRemate=false;
			}
	}
	
	

	private void activarRemate(DeltaState deltaState) {
		//Direccion del remate
		direccionRemate = Direccion.obtenerDireccion(deltaState);
		potencia = 22;
		//Si se apreta enter para pegarle a la pelota
		if(deltaState.isKeyBeingHold(Key.ENTER) && direccionRemate != 0){
			System.out.println(getScene().getCancha().getY());
			enRemate=true;
		}
	}

	public void ejecutarMovimiento(DeltaState deltaState, double x, double y) {
		int direccion = Direccion.obtenerDireccion(deltaState);
		switch (direccion) {
		case Direccion.UP:
			this.setY(y-14);
			this.setX(x + 3);
			this.ejecutarSpritePelota();
			break;
		case Direccion.DOWN:
			this.setY(y+26);
			this.setX(x);
			this.ejecutarSpritePelota();
			break;
		case Direccion.LEFT:
			this.setX(x-11);
			this.setY(y+4);
			this.ejecutarSpritePelota();
			break;
		case Direccion.RIGHT:
			this.setX(x+25);
			this.setY(y+9);
			this.ejecutarSpritePelota();
			break;
		case Direccion.UPRIGHT:
			this.setX(x + 20);
			this.setY(y - 5);
			this.ejecutarSpritePelota();
			break;
		case Direccion.UPLEFT:
			this.setX(x - 6);
			this.setY(y - 8);
			this.ejecutarSpritePelota();
			break;
		case Direccion.DOWNRIGHT:
			this.setX(x + 25);
			this.setY(y + 7);
			this.ejecutarSpritePelota();
			break;
		case Direccion.DOWNLEFT:
			this.setX(x - 5);
			this.setY(y + 15);
			this.ejecutarSpritePelota();
			break;
		}			
//		if(deltaState.isKeyBeingHold(Key.W) && b){
//			this.setY(y-14);
//			this.setX(x);
//			this.ejecutarSpritePelota();
////			if(this.getScene().getCancha().getY() !=0){
////			this.getScene().getCancha().setY(this.getScene().getCancha().getY() + 4/3);
////			}
//		}
//		if(deltaState.isKeyBeingHold(Key.S) && b){
//			this.setY(y+26);
//			this.setX(x);
//			this.ejecutarSpritePelota();
////			if(this.getScene().getCancha().getY() > -930){
////			this.getScene().getCancha().setY(this.getScene().getCancha().getY() - 4/3);
////			}
//		}
//		if(deltaState.isKeyBeingHold(Key.A)){
//			this.setX(x-11);
//			this.setY(y+4);
//			this.ejecutarSpritePelota();
////		}
//		if(deltaState.isKeyBeingHold(Key.D)){
//			this.setX(x+25);
//			this.setY(y+9);
//			this.ejecutarSpritePelota();
//		}		
	}

	private void ejecutarSpritePelota() {
		if(this.time == 12){
			this.setAppearance(this.listSprites.get(this.estado));
			this.estado = (this.estado+1)%this.listSprites.size();
			this.time=0;
		}
		this.time++;
		
	}
}
