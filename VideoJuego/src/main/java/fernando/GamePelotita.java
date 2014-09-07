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
		Pelota pelota = new Pelota(30, 2, 2, new Vector(5, -2), 900);
		((PelotitaScene) pelotitaScene).setPelota(pelota);
		this.setCurrentScene(pelotitaScene);
	}

	@Override
	public Dimension getDisplaySize() {
		return new Dimension(200, 200);
	}

	@Override
	public String getTitle() {
		return "Pelotita";
	}
	
	public static void main(String[] args) {
		
		new DesktopGameLauncher(new GamePelotita()).launch();

	}

}
