package fernando;

import java.awt.Color;

import javax.swing.plaf.SliderUI;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.appearances.Circle;
import com.uqbar.vainilla.colissions.CollisionDetector;
import com.uqbar.vainilla.events.constants.Key;

public class Pelota extends GameComponent<ArkanoidScene> {
	
	private Vector direccion;
	private double velocidad;
	private Paleta raqueta;
	private boolean flag = false;
	private int vidas = 2;
	
	
	public Pelota(int radio, double xInicial, double yInicial, Vector direccionInicial, double velocidadInicial) {
		super(new Circle(Color.BLUE, radio), xInicial, xInicial);
		this.direccion = direccionInicial.asVersor();
		this.velocidad = velocidadInicial;

	}

	public Pelota(int radio, double xInicial, double yInicial, Vector direccionInicial, double velocidadInicial, Paleta raqueta) {
		super(new Circle(Color.BLUE, radio), xInicial, yInicial);
		this.direccion = direccionInicial.asVersor();
		this.velocidad = velocidadInicial;
		this.raqueta = raqueta;
	}

	@Override
	public void update(DeltaState deltaState) {
		
		if(deltaState.isKeyPressed(Key.A) || this.isFlag()){
			this.setFlag(true);
		//para q se mueva
		Vector nuevaPosicion = this.direccion.producto(velocidad*deltaState.getDelta()).suma(new Vector(this.getX(), this.getY()));
		this.setX(nuevaPosicion.getX());
		this.setY(nuevaPosicion.getY());
		
		//choques	
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
		if(nuevaPosicion.getY() + getAppearance().getHeight() >= getGame().getDisplayHeight() ){
			if(this.vidas==0){
			super.getScene().fin();
			}else{
				this.vidas--;
				this.setFlag(false);
				super.getScene().reiniciar();
			}
		}
		
		if(this.raqueta.mustApply(this, nuevaPosicion)){
			this.raqueta.apply(this, nuevaPosicion);
		}}
		super.update(deltaState);
	}
	
	public void setDireccion(Vector vector2d) {
		this.direccion = vector2d.asVersor();
	}

	public Vector getDireccion() {
		return this.direccion;
	}
	
	public double getVelocidad(){
		return velocidad;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	
	
}
