package soccer.menu;

import java.awt.Dimension;

import soccer.SoccerGame;
import soccer.SoccerScene;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.DesktopGameLauncher;
import com.uqbar.vainilla.Game;
import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.appearances.Sprite;
import com.uqbar.vainilla.events.constants.Key;

public class MenuControl extends GameComponent<MenuScene>{
	
	public MenuControl(String imagen, double x, double y){
		super(Sprite.fromImage(imagen),x,y);
	}
	
	@Override
	public void update(DeltaState deltaState) {
		
		if(deltaState.isKeyPressed(Key.ENTER)){
			new DesktopGameLauncher(new SoccerGame()).launch();
//			SoccerGame g = new SoccerGame();
//			this.getGame().setCurrentScene(g.construirSoccerScene());
		}
	}

}
