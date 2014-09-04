package fernando;

import java.awt.Color;
import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.appearances.Circle;

public class Pelota extends GameComponent<PelotitaScene> {

	private Vector2D direccion;
	private double velocidad;
	
	public Pelota(int radio, double xInicial, double yInicial, Vector2D direccionInicial, double velocidadInicial) {
		super(new Circle(Color.BLUE, radio), xInicial, xInicial);
		this.direccion = direccionInicial.asVersor();
		this.velocidad = velocidadInicial;

	}

	@Override
	public void update(DeltaState deltaState) {
		//para q se mueva
		Vector2D nuevaPosicion = this.direccion.producto(velocidad*deltaState.getDelta()).suma(new Vector2D(this.getX(), this.getY()));
		this.setX(nuevaPosicion.getX());
		this.setY(nuevaPosicion.getY());
		//choques
		if(getGame().getDisplayWidth() <= nuevaPosicion.getX() + getAppearance().getWidth() || 
				nuevaPosicion.getX() <= 0){
			//setX(getGame().getDisplayWidth()-getAppearance().getWidth());
//			Vector2D vector = new Vector2D(getX()*-1,getY()*-1);
			setDireccion(new Vector2D(-1*getDireccion().getX(), getDireccion().getY()));
		}else{
			if(nuevaPosicion.getY() <= 0 || 
					getGame().getDisplayHeight() <= nuevaPosicion.getY() + getAppearance().getHeight()){
				setDireccion(new Vector2D(getDireccion().getX(), -1*getDireccion().getY()));
			}			
		}

		super.update(deltaState);
	}
	
	public void setDireccion(Vector2D vector2d) {
		this.direccion = vector2d.asVersor();
	}

	public Vector2D getDireccion() {
		return this.direccion;
	}

}
