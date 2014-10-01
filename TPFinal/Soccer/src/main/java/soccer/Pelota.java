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
	int estado = 0;
	@Override
	public void update(DeltaState deltaState) {
		List<Jugador> jugadores = super.getScene().getJugadores(); 
	
		for (Jugador jugador : jugadores) {
			Vector nuevaPosicion = new Vector(jugador.getX(), jugador.getY());
				if(Colision.mustApply(this, jugador, nuevaPosicion) && jugador.isEstaSeleccionado()){
					ejecutarMovimiento(deltaState, jugador.getX(), jugador.getY());
					
				}
			}

		super.update(deltaState);
	}

	public void ejecutarMovimiento(DeltaState deltaState, double x, double y) {
		if(deltaState.isKeyBeingHold(Key.UP)){
			this.setY(y-14);
			this.setX(x);
			this.ejecutarSpritePelota();
			
			if(this.getScene().getCancha().getY() !=0){
			this.getScene().getCancha().setY(this.getScene().getCancha().getY() + 4/3);
			}
		}
		if(deltaState.isKeyBeingHold(Key.DOWN)){
			this.setY(y+26);
			this.setX(x);
			this.ejecutarSpritePelota();
			if(this.getScene().getCancha().getY() > -930){
			this.getScene().getCancha().setY(this.getScene().getCancha().getY() - 4/3);
			}
		}
		if(deltaState.isKeyBeingHold(Key.LEFT)){
			this.setX(x-15);
			this.setY(y+4);
			this.ejecutarSpritePelota();
		}
		if(deltaState.isKeyBeingHold(Key.RIGHT)){
			this.setX(x+25);
			this.setY(y+9);
			this.ejecutarSpritePelota();
		}
		
	}

	private void ejecutarSpritePelota() {
		if(this.time == 7){
			this.setAppearance(this.listSprites.get(this.estado));
			this.estado = (this.estado+1)%this.listSprites.size();
			this.time=0;
		}
		this.time++;
		
	}
}
