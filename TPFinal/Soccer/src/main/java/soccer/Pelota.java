package soccer;

import java.util.List;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.appearances.Sprite;
import com.uqbar.vainilla.events.constants.Key;

public class Pelota extends GameComponent<SoccerScene>{
	
	private Vector direccion;
	private double velocidad;
	
	public Pelota(String pelotaPath, Vector vector, double velocidad){
		
		super(Sprite.fromImage(pelotaPath).scaleTo(16,16), 600,200);
		this.direccion = vector.asVersor();
		this.velocidad = velocidad;	
	
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
		}
		if(deltaState.isKeyBeingHold(Key.DOWN)){
			this.setY(y+26);
			this.setX(x);
		}
		if(deltaState.isKeyBeingHold(Key.LEFT)){
			this.setX(x-15);
			this.setY(y+4);
		}
		if(deltaState.isKeyBeingHold(Key.RIGHT)){
			this.setX(x+25);
			this.setY(y+9);
		}
		
	}
}
