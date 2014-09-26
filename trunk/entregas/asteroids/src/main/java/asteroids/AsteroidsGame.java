package asteroids;

import java.awt.Dimension;

import com.uqbar.vainilla.DesktopGameLauncher;
import com.uqbar.vainilla.Game;

public class AsteroidsGame  extends Game{

	@Override
	protected void initializeResources() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void setUpScenes() {

		AsteroidsFondo astFondo = new AsteroidsFondo("fondo.jpg", 0, 0);
		Nave nave = new Nave("nave.png", 268, 198);
		AsteroidsScene scene = new AsteroidsScene();
		scene.setAsteroidFondo(astFondo);
		scene.setNave(nave);
		this.setCurrentScene(scene);
	}

	@Override
	public Dimension getDisplaySize() {
		return new Dimension(640, 480);
	}

	@Override
	public String getTitle() {
		return "Asteroids";
	}
	
	public static void main(String[] args) {
		new DesktopGameLauncher(new AsteroidsGame()).launch();

	}

}
