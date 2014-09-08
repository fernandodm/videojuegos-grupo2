package fernando;

import java.awt.Color;
import java.awt.Dimension;



import com.uqbar.vainilla.DesktopGameLauncher;
import com.uqbar.vainilla.Game;
import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.GameScene;
import com.uqbar.vainilla.appearances.Circle;
import com.uqbar.vainilla.appearances.Rectangle;

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
		renderElements(pelotitaScene);
		this.setCurrentScene(pelotitaScene);
	}
	
	
	public void renderElements(GameScene scene){
		
		int length=10;
		int width =10;
		
		Color[] colors={Color.red,Color.black,Color.blue,Color.DARK_GRAY,Color.orange,Color.white};
		
		for (int y = 0; y < width; y++) {
			for (int x = 0; x < length; x++) {
				Color color=colors[(int)(Math.random()*colors.length)];
				scene.addComponent(new GameComponent<GameScene>(new Rectangle(color,48,18),x*50, y*20));
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
