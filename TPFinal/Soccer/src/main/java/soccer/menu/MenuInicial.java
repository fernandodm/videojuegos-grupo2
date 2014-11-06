package soccer.menu;

import java.awt.Dimension;

import com.uqbar.vainilla.DesktopGameLauncher;
import com.uqbar.vainilla.Game;

public class MenuInicial extends Game {

	@Override
	protected void initializeResources() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void setUpScenes() {
		//el sonido se lo dejo comentado para q no suene
		//se interpone con el otro sonido
		MenuScene scene = new MenuScene("SonidoMenu.wav");
		scene.addComponent(new MenuControl("Menu.jpg",0,0));
		this.setCurrentScene(scene);
	}
	Dimension dimension = new Dimension(800, 600);
	@Override
	public Dimension getDisplaySize() {
		return dimension;
	}

	@Override
	public String getTitle() {
		return "Fulbito 2014";
	}
	
	public static void main(String[] args) {
		new DesktopGameLauncher(new MenuInicial()).launch();

	}

}
