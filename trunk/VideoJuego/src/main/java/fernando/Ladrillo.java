package fernando;

import java.awt.Color;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.GameScene;
import com.uqbar.vainilla.appearances.Rectangle;
import com.uqbar.vainilla.colissions.CollisionDetector;

public class Ladrillo extends GameComponent<ArkanoidScene>{
	
	private double anguloMayor = Math.PI / 3;
	private double anguloMenor = -Math.PI / 3;
	private Pelota pelota;
	private Marcador marcador;
	
	public Ladrillo(Color color, int ancho, int alto, double x, double y, Pelota pelota, Marcador marcador){
		super(new Rectangle(color, ancho, alto), x * 50, y * 20);
		this.pelota = pelota;
		this.marcador = marcador;
	}
	
	
	@Override
	public void update(DeltaState deltaState) {
		Vector nuevaPosicion = pelota
				.getDireccion()
				.producto(
						pelota.getVelocidad()
								* deltaState.getDelta())
				.suma(new Vector(pelota.getX(), pelota.getY()));
		if (colisiona(pelota, nuevaPosicion)) {
			this.getScene().removeComponent(this);
			apply(pelota,nuevaPosicion,this.getScene());
			sumarPts(100);
		}
	}

	private void sumarPts(int i) {
		this.marcador.setValue(this.marcador.getValue() + 50);
		
	}


	public void apply(Pelota pelota, Vector nuevaPosicion,
			GameScene scene) {
		double puntoDeColision = getPuntoColision(pelota,
				nuevaPosicion);

		double signoY = Math.signum(pelota.getDireccion()
				.getY());

		double anguloNuevo = ((anguloMayor - anguloMenor) / this
				.getAppearance().getWidth())
				* puntoDeColision
				+ anguloMenor;
		// aprovecho e invierto el signo que traia Y con el
		// truquito de
		// multiplicarlo por -1
		pelota.setDireccion(new Vector(Math.sin(anguloNuevo),
				(-1) * signoY * Math.cos(anguloNuevo)));

		// pelota.setX(nuevaPosicion.getX());
		pelota.setY(signoY > 0 ? this.getY()
				- pelota.getAppearance().getHeight() - 1 : this
				.getY() + this.getAppearance().getHeight() + 1);

	}

	private double getPuntoColision(Pelota pelota,Vector nuevaPosicion) {
		if (pelota.getX() > this.getX()
				&& pelota.getX()
						+ pelota.getAppearance().getWidth() < this
						.getX()
						+ this.getAppearance().getWidth()) {
			double xCentroPelota = nuevaPosicion.getX()
					+ pelota.getAppearance().getWidth() / 2;

			return xCentroPelota - this.getX();
		} else if (pelota.getX() < this.getX()) {
			return 0;
		} else {
			return this.getAppearance().getWidth();
		}
	}

	public boolean colisiona(Pelota pelota,
			Vector nuevaPosicion) {
		return CollisionDetector.INSTANCE
				.collidesCircleAgainstRect(
						nuevaPosicion.getX(), nuevaPosicion
								.getY(), pelota.getAppearance()
								.getWidth() / 2, this
								.getX(), this.getY(),
						this.getAppearance().getWidth(),
						this.getAppearance().getHeight());
	}

}
