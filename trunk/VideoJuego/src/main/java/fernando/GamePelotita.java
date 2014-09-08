package fernando;

import java.awt.Color;
import java.awt.Dimension;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.DesktopGameLauncher;
import com.uqbar.vainilla.Game;
import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.GameScene;
import com.uqbar.vainilla.appearances.Rectangle;
import com.uqbar.vainilla.colissions.CollisionDetector;

public class GamePelotita extends Game {

	@Override
	protected void initializeResources() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void setUpScenes() {
		PelotitaScene pelotitaScene = new PelotitaScene();
		Pelota pelota = new Pelota(30, 300, 2, new Vector(5, -2), 900);
		((PelotitaScene) pelotitaScene).setPelota(pelota);
		renderElements(pelotitaScene, pelota);
		this.setCurrentScene(pelotitaScene);
	}

	public void renderElements(final GameScene scene, final Pelota pelota) {

		int length = 10;
		int width = 10;

		Color[] colors = { Color.red, Color.black, Color.blue, Color.DARK_GRAY,
				Color.orange, Color.white };

		for (int y = 0; y < width; y++) {
			for (int x = 0; x < length; x++) {
				Color color = colors[(int) (Math.random() * colors.length)];
				scene.addComponent(new GameComponent<GameScene>(new Rectangle(
						color, 48, 18), x * 50, y * 20) {

					@Override
					public void update(DeltaState deltaState) {
						Vector nuevaPosicion = pelota
								.getDireccion()
								.producto(
										pelota.getVelocidad()
												* deltaState.getDelta())
								.suma(new Vector(pelota.getX(), pelota.getY()));
						if (colisiona(this, pelota, nuevaPosicion)) {
							scene.removeComponent(this);

						}

					}

					public boolean colisiona(
							GameComponent<GameScene> rectangle, Pelota pelota,
							Vector nuevaPosicion) {
						return CollisionDetector.INSTANCE
								.collidesCircleAgainstRect(
										nuevaPosicion.getX(), nuevaPosicion
												.getY(), pelota.getAppearance()
												.getWidth() / 2, rectangle
												.getX(), rectangle.getY(),
										rectangle.getAppearance().getWidth(),
										rectangle.getAppearance().getHeight());
					}
				});
			}
		}

	}

	@Override
	public Dimension getDisplaySize() {
		return new Dimension(500, 500);
	}

	@Override
	public String getTitle() {
		return "Pelotita";
	}

	public static void main(String[] args) {

		new DesktopGameLauncher(new GamePelotita()).launch();

	}

}
