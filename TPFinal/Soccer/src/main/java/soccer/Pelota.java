package soccer;

import java.util.List;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.appearances.Sprite;

public class Pelota extends GameComponent<SoccerScene>{
	
	private Vector direccion;
	private double velocidad;
	
	public Pelota(String pelotaPath, Vector vector, double velocidad){
		
		super(Sprite.fromImage(pelotaPath).scaleTo(16,16), 625,750);
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
//		List<Jugador> jugadores = super.getScene().getJugadores(); 
//	
//		Vector nuevaPosicion = this.direccion.producto(velocidad*deltaState.getDelta()).suma(new Vector(this.getX(), this.getY()));
//		this.setX(nuevaPosicion.getX());
//		this.setY(nuevaPosicion.getY());
	
//		for (Jugador jugador : jugadores) {
//				if(Colision.mustApply(jugador, this, nuevaPosicion)){
//					this.setX(this.getX());
//					this.setY(this.getY()-1);
//				}
//			}
//
//		super.update(deltaState);
	}

}
