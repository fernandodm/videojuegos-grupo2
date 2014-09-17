package Arkanoid;

import java.awt.Color;
import java.util.ArrayList;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.appearances.Circle;
import com.uqbar.vainilla.appearances.Rectangle;
import com.uqbar.vainilla.events.constants.Key;

public class Paleta extends GameComponent<ArkanoidScene> {

	private Key leftKey = Key.LEFT;
	private Key rigthKey = Key.RIGHT;

	private double velocidad;

	private double xMin;
	private double xMax;
	private boolean flag = true;
	private boolean pegajosa = false;
	private boolean pistolera = false;

	public Paleta(double x, double y, int ancho, int alto, Color color,
			double velocidad, double xMin, double xMax) {
		super(new Rectangle(color, ancho, alto), x, y);
		this.setVelocidad(velocidad);
		this.setxMin(xMin);
		this.setxMax(xMax);
	}

	public void izquierda(double delta) {
		double x = Math.max(this.getX() - getVelocidad() * delta, getxMin());
		this.setX(x);
		//verifico el movimiento de la pelota cuando
		//esta pegada en la paleta
		verificarMovimientoPelota(x);
	}

	public void derecha(double delta) {
		double x = Math.min(getxMax() - this.getAppearance().getWidth(),
				             this.getX() + getVelocidad() * delta);
		this.setX(x);
		//verifico el movimiento de la pelota cuando
		//esta pegada en la paleta
		verificarMovimientoPelota(x);
		
	}

	private void verificarMovimientoPelota(double x) {
		if(this.isFlag()){
			super.getScene().getPelota().setX(x);
		}
	}

	@Override
	public void update(DeltaState deltaState) {
		this.update(this, deltaState);
		super.update(deltaState);
	}

	public void update(Paleta raqueta, DeltaState deltaState) {
		if (deltaState.isKeyBeingHold(rigthKey)) {
			raqueta.derecha(deltaState.getDelta());
		} else if (deltaState.isKeyBeingHold(leftKey)) {
			raqueta.izquierda(deltaState.getDelta());
		}
		if(deltaState.isKeyPressed(Key.SPACE) && this.isPistolera()){
			ArrayList<Ladrillo> ladrillos = super.getScene().getLadrillos();
			Bala bala = new Bala(this.getX(), this.getY(), ladrillos);
			super.getGame().getCurrentScene().addComponent(bala);
			
		}
	}

	public double getxMin() {
		return xMin;
	}

	public void setxMin(double xMin) {
		this.xMin = xMin;
	}

	public double getxMax() {
		return xMax;
	}

	public void setxMax(double xMax) {
		this.xMax = xMax;
	}

	public double getVelocidad() {
		return velocidad;
	}

	public void setVelocidad(double velocidad) {
		this.velocidad = velocidad;
	}

	public Key getLeftKey() {
		return leftKey;
	}

	public void setLeftKey(Key leftKey) {
		this.leftKey = leftKey;
	}

	public Key getRigthKey() {
		return rigthKey;
	}

	public void setRigthKey(Key rigthKey) {
		this.rigthKey = rigthKey;
	}

	public boolean isFlag() {
		return this.flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public boolean isPegajosa() {
		return pegajosa;
	}

	public void setPegajosa(boolean pegajosa) {
		this.pegajosa = pegajosa;
	}

	public boolean isPistolera() {
		return pistolera;
	}

	public void setPistolera(boolean pistolera) {
		this.pistolera = pistolera;
	}


}
