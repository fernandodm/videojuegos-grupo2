package Arkanoid;

import java.awt.Color;
import java.awt.Dimension;

import com.uqbar.vainilla.DesktopGameLauncher;
import com.uqbar.vainilla.Game;
import com.uqbar.vainilla.GameScene;

public class ArkanoidGame extends Game {

	@Override
	protected void initializeResources() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void setUpScenes() {
		
		GameScene arkanoidScene = this.buildArkanoidScene();
		this.setCurrentScene(arkanoidScene);
		
	}
	
	public GameScene buildArkanoidScene() {
		
		Paleta raqueta = new Paleta(200, 450, 50, 5, Color.BLACK, 500, 0, this.getDisplayWidth());
		Marcador marcador = new Marcador(50, this.getDisplayHeight() - 35,Color.GREEN);
		Pelota pelota = new Pelota(20, 200, 430, new Vector(5, 5), 500, raqueta, marcador);
		ArkanoidScene arkanoidScene = new ArkanoidScene();
		arkanoidScene.setPelota(pelota);
		arkanoidScene.setRaqueta(raqueta);
		arkanoidScene.setMarcador(marcador);
		renderElements(arkanoidScene, pelota, marcador);
		return arkanoidScene;
	}

	public void renderElements(final GameScene scene, final Pelota pelota, Marcador marcador) {

		int length = 10;
		int width = 10;
		
		Color[] colors = { Color.red, Color.black, Color.blue, Color.DARK_GRAY,
				Color.orange, Color.white };

		for (int y = 0; y < width; y++) {
			for (int x = 0; x < length; x++) {
				Color color = colors[(int) (Math.random() * colors.length)];
				scene.addComponent(new Ladrillo(color, 48, 18, x, y, pelota, marcador));
			}
		}

	}

	@Override
	public Dimension getDisplaySize() {
		return new Dimension(500, 500);
	}

	@Override
	public String getTitle() {
		return "Arkanoid";
	}

	public static void main(String[] args) {

		new DesktopGameLauncher(new ArkanoidGame()).launch();

	}
	
	public FinalScene buildEndScene() {
		return new FinalScene( 100,100);
	}

}
