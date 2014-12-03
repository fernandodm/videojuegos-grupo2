package soccer;

import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.colissions.CollisionDetector;

public class Colision {
	
	public static boolean mustApply(GameComponent<?> gameComponent, GameComponent<?> pelota, Vector nuevaPosicion) {

		return colisiona(gameComponent, pelota, nuevaPosicion);
	}

	public static boolean colisiona(GameComponent<?> gameComponent, GameComponent<?> gameComponent2, Vector nuevaPosicion) {
		return CollisionDetector.INSTANCE.collidesCircleAgainstRect(
			   nuevaPosicion.getX(), nuevaPosicion.getY(), gameComponent2
			   .getAppearance().getWidth() / 2, gameComponent.getX(),
				gameComponent.getY(), gameComponent.getAppearance().getWidth(), gameComponent
			   .getAppearance().getHeight());
	}
}
