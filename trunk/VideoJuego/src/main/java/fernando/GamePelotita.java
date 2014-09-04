package fernando;

import java.awt.Dimension;
import com.uqbar.vainilla.DesktopGameLauncher;
import com.uqbar.vainilla.Game;

public class GamePelotita extends Game {

	@Override
	protected void initializeResources() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void setUpScenes() {
		PelotitaScene pelotitaScene = new PelotitaScene();
		Pelota pelota = new Pelota(30, 400, 0, new Vector2D(5, -2), 500);
		((PelotitaScene) pelotitaScene).setPelota(pelota);
		this.setCurrentScene(pelotitaScene);
	}

	@Override
	public Dimension getDisplaySize() {
		return new Dimension(1000, 900);
	}

	@Override
	public String getTitle() {
		return "Pelotita";
	}
	
	public static void main(String[] args) {
		
		new DesktopGameLauncher(new GamePelotita()).launch();

	}

}
