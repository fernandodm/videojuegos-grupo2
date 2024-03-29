package componentes;

import java.awt.Color;
import java.util.ArrayList;
import Arkanoid.Colision;
import Arkanoid.Vector;
import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.appearances.Circle;
import com.uqbar.vainilla.events.constants.Key;
import componentes.ladrillos.Ladrillo;
import escenas.ArkanoidScene;

public class Pelota extends GameComponent<ArkanoidScene> {
	
	private Vector direccion;
	private double velocidad;
	private Raqueta raqueta;
	private boolean flag = false;
	private Marcador marcador;


	public Pelota(int radio, double xInicial, double yInicial, Vector direccionInicial, double velocidadInicial, Raqueta raqueta, Marcador marcador) {
		super(new Circle(Color.BLUE, radio), xInicial, yInicial);
		this.direccion = direccionInicial.asVersor();
		this.velocidad = velocidadInicial;
		this.raqueta = raqueta;
		this.setMarcador(marcador);
	}

	public void setVelocidad(double velocidad) {
		this.velocidad = velocidad;
	}

	@Override
	public void update(DeltaState deltaState) {
		ArrayList<Ladrillo> ladrillos = super.getScene().getLadrillos(); 
	
		if(deltaState.isKeyPressed(Key.A) || this.isFlag()){
			this.setFlag(true);
			//para que no se mueva la pelota cuando deja de estar pegada
			this.raqueta.setFlag(false);
			//para q se mueva
			Vector nuevaPosicion = this.direccion.producto(velocidad*deltaState.getDelta()).suma(new Vector(this.getX(), this.getY()));
			this.setX(nuevaPosicion.getX());
			this.setY(nuevaPosicion.getY());
		
			this.verificarChoques(nuevaPosicion);
			this.verificarChoquePaleta(nuevaPosicion);
			
			ArrayList<Ladrillo> muertos = new ArrayList<Ladrillo>();
			for (Ladrillo ladrillo : ladrillos) {
				if(ladrillo.collision(deltaState, this,nuevaPosicion)){
					muertos.add(ladrillo);
				};
			}
			
			for (Ladrillo ladrillo : muertos) {
				ladrillos.remove(ladrillo);
			}
			if(ladrillos.isEmpty()){
				super.getScene().ganaste();
			}
		}
		super.update(deltaState);
	}
	
	private void verificarChoquePaleta(Vector nuevaPosicion) {
		if(this.raqueta.isPegajosa() && Colision.mustApply(this.raqueta, this, nuevaPosicion)){
			this.setFlag(false);
			this.raqueta.setFlag(true);
		}
		if(Colision.mustApply(this.raqueta, this, nuevaPosicion)){
			Colision.apply(this.raqueta,this, nuevaPosicion);
			this.raqueta.setPegajosa(false);
		}
		
	}

	private void verificarChoques(Vector nuevaPosicion) {
			
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
			if(this.getMarcador().getVidas()==0){
			super.getScene().fin();
			}else{
				this.getMarcador().setVidas(this.getMarcador().getVidas()-1);
				this.setFlag(false);
				super.getScene().reiniciar();
			}
		}
		//Si el puntaje llega a 2000 suma una vida
		//this.sumarVida();
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

	public Marcador getMarcador() {
		return marcador;
	}

	public void setMarcador(Marcador marcador) {
		this.marcador = marcador;
	}
	
	
}
