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
		
		super(Sprite.fromImage(pelotaPath).scaleTo(16,16), 50,50);
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
//	
//		Vector nuevaPosicion = this.direccion.producto(velocidad*deltaState.getDelta()).suma(new Vector(this.getX(), this.getY()));
//		this.setX(nuevaPosicion.getX());
//		this.setY(nuevaPosicion.getY());
	
		for (Jugador jugador : jugadores) {
			Vector nuevaPosicion = new Vector(jugador.getX(), jugador.getY());
				if(Colision.mustApply(jugador, this, nuevaPosicion)){
					ejecutarMovimiento(deltaState, jugador.getX(), jugador.getY());
//					Colision.apply(jugador, this, nuevaPosicion);
				}
			}

		super.update(deltaState);
	}

//	las X e Y estan demas
	private void ejecutarMovimiento(DeltaState deltaState, double x, double y) {
		if(deltaState.isKeyBeingHold(Key.UP)){
			this.setY(y-15);
			this.setX(x);
			((Sprite) this.getAppearance()).flipVertically();
		}
		if(deltaState.isKeyBeingHold(Key.DOWN)){
			this.setY(y+28);
			this.setX(x);
		}
		if(deltaState.isKeyBeingHold(Key.LEFT)){
			this.setX(x-17);
			this.setY(y);
		}
		if(deltaState.isKeyBeingHold(Key.RIGHT)){
			this.setX(x+25);
			this.setY(y + 5);
		}
		
	}

	

}
