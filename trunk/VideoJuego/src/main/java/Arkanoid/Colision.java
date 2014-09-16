package Arkanoid;

import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.colissions.CollisionDetector;

public class Colision {
	
	private static double anguloMayor = Math.PI/3;
	private static double anguloMenor = -Math.PI/3;
	
	public static void apply(GameComponent<?> gameComponent, Pelota pelota, Vector nuevaPosicion) {
		
		double puntoDeColision = getPuntoColision(gameComponent, pelota,
				nuevaPosicion);

		double signoY = Math.signum(pelota.getDireccion().getY());

		double anguloNuevo = ((anguloMayor - anguloMenor) / gameComponent.getAppearance().getWidth()) * puntoDeColision + anguloMenor;
		// aprovecho e invierto el signo que traia Y con el truquito de
		// multiplicarlo por -1
		pelota.setDireccion(new Vector(Math.sin(anguloNuevo), (-1) * signoY	* Math.cos(anguloNuevo)));

		if(gameComponent.getClass().equals(Paleta.class))
			pelota.setY(signoY > 0 ? gameComponent.getY()
				- pelota.getAppearance().getHeight() - 1 : gameComponent.getY()
				+ gameComponent.getAppearance().getHeight() + 1);
		
		
//		double signoX = Math.signum(pelota.getDireccion().getX());

	}

	private static double getPuntoColision(GameComponent<?> gameComponent, Pelota pelota,
			Vector nuevaPosicion) {
		if (pelota.getX() > gameComponent.getX()
				&& pelota.getX() + pelota.getAppearance().getWidth() < gameComponent
						.getX() + gameComponent.getAppearance().getWidth()) {
			double xCentroPelota = nuevaPosicion.getX()
					+ pelota.getAppearance().getWidth() / 2;

			return xCentroPelota - gameComponent.getX();
		} else if (pelota.getX() < gameComponent.getX()) {
			return 0;
		} else {
			return gameComponent.getAppearance().getWidth();
		}
	}

	public static boolean mustApply(GameComponent<?> gameComponent, Pelota pelota, Vector nuevaPosicion) {

		return colisiona(gameComponent, pelota, nuevaPosicion);
	}

	public static boolean colisiona(GameComponent<?> gameComponent, Pelota pelota, Vector nuevaPosicion) {
		return CollisionDetector.INSTANCE.collidesCircleAgainstRect(
			   nuevaPosicion.getX(), nuevaPosicion.getY(), pelota
			   .getAppearance().getWidth() / 2, gameComponent.getX(),
				gameComponent.getY(), gameComponent.getAppearance().getWidth(), gameComponent
			   .getAppearance().getHeight());
	}

}
