package fernando;

import java.awt.Color;
import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.appearances.Circle;

public class Pelota extends GameComponent<PelotitaScene> {

	private Vector direccion;
	private double velocidad;
	
	public Pelota(int radio, double xInicial, double yInicial, Vector direccionInicial, double velocidadInicial) {
		super(new Circle(Color.BLUE, radio), xInicial, xInicial);
		this.direccion = direccionInicial.asVersor();
		this.velocidad = velocidadInicial;

	}

	@Override
	public void update(DeltaState deltaState) {
		//para q se mueva
		Vector nuevaPosicion = this.direccion.producto(velocidad*deltaState.getDelta()).suma(new Vector(this.getX(), this.getY()));
		this.setX(nuevaPosicion.getX());
		this.setY(nuevaPosicion.getY());
		//choques	
		if(nuevaPosicion.getY() + getAppearance().getHeight() >= getGame().getDisplayHeight() ){
			setDireccion(new Vector(getDireccion().getX(), -1*getDireccion().getY()));
			this.setY( getGame().getDisplayHeight()-getAppearance().getHeight());
			}
		if(nuevaPosicion.getY() < 0){
			setDireccion(new Vector(getDireccion().getX(), -1*getDireccion().getY()));
			this.setY(0);
			}
		
		if(nuevaPosicion.getX() + getAppearance().getWidth() >= getGame().getDisplayWidth() ){
			setDireccion(new Vector(-1*getDireccion().getX(), getDireccion().getY()));
			this.setX( getGame().getDisplayWidth()-getAppearance().getWidth());
			}
		if(nuevaPosicion.getX() < 0){
			setDireccion(new Vector(-1*getDireccion().getX(), getDireccion().getY()));
			this.setX(0);
			}
		
		
		super.update(deltaState);
	}
	
	public void setDireccion(Vector vector2d) {
		this.direccion = vector2d.asVersor();
	}

	public Vector getDireccion() {
		return this.direccion;
	}

}
