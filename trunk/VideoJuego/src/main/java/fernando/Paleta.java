package fernando;

import java.awt.Color;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.appearances.Rectangle;
import com.uqbar.vainilla.colissions.CollisionDetector;
import com.uqbar.vainilla.events.constants.Key;

public class Paleta extends GameComponent<ArkanoidScene> {

	private static double anguloMayor = Math.PI/3;
	private static double anguloMenor = -Math.PI/3;
	
	private Key leftKey = Key.LEFT;
	private Key rigthKey = Key.RIGHT;

	private double velocidad;

	private double xMin;
	private double xMax;

	public Paleta(double x, double y, int ancho, int alto, Color color,
			double velocidad, double xMin, double xMax) {
		super(new Rectangle(color, ancho, alto), x, y);
		this.setVelocidad(velocidad);
		this.setxMin(xMin);
		this.setxMax(xMax);
	}

	public void izquierda(double delta) {
		this.setX(Math.max(this.getX() - getVelocidad() * delta, getxMin()));
	}

	public void derecha(double delta) {
		this.setX(Math.min(getxMax() - this.getAppearance().getWidth(),
				this.getX() + getVelocidad() * delta));
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
	}

	// ///////////////////////////////////
	// ////CHEQUEO RAQUETA////////////////
	// //////////////////////////////////

	public void apply(Pelota pelota, Vector nuevaPosicion) {
		double puntoDeColision = getPuntoColision(pelota,
				nuevaPosicion);

		double signoY = Math.signum(pelota.getDireccion().getY());

		double anguloNuevo = ((anguloMayor - anguloMenor) / this.getAppearance().getWidth()) * puntoDeColision + anguloMenor;
		// aprovecho e invierto el signo que traia Y con el truquito de
		// multiplicarlo por -1
		pelota.setDireccion(new Vector(Math.sin(anguloNuevo), (-1) * signoY	* Math.cos(anguloNuevo)));

		pelota.setY(signoY > 0 ? this.getY()
				- pelota.getAppearance().getHeight() - 1 : this.getY()
				+ this.getAppearance().getHeight() + 1);

	}

	private double getPuntoColision(Pelota pelota,
			Vector nuevaPosicion) {
		if (pelota.getX() > this.getX()
				&& pelota.getX() + pelota.getAppearance().getWidth() < this
						.getX() + this.getAppearance().getWidth()) {
			double xCentroPelota = nuevaPosicion.getX()
					+ pelota.getAppearance().getWidth() / 2;

			return xCentroPelota - this.getX();
		} else if (pelota.getX() < this.getX()) {
			return 0;
		} else {
			return this.getAppearance().getWidth();
		}
	}

	public boolean mustApply(Pelota pelota, Vector nuevaPosicion) {

		return this.colisiona(pelota, nuevaPosicion);
	}

	private boolean colisiona(Pelota pelota, Vector nuevaPosicion) {
		return CollisionDetector.INSTANCE.collidesCircleAgainstRect(
				nuevaPosicion.getX(), nuevaPosicion.getY(), pelota
						.getAppearance().getWidth() / 2, this.getX(),
				this.getY(), this.getAppearance().getWidth(), this
						.getAppearance().getHeight());
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

}
