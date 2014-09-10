package fernando;



import java.awt.Color;
import java.awt.Component;
import java.awt.Font;


import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.GameScene;
import com.uqbar.vainilla.appearances.Label;
import com.uqbar.vainilla.events.constants.Key;

public class PerdisteScene extends GameComponent<FinalScene> {


	public PerdisteScene( double x, double y) {
		super(new Label(new Font("verdana",  Font.BOLD, 24), Color.BLUE, "", "Presione N para un juego nuevo"), x, y);
		// TODO Auto-generated constructor stub
	}
//	
//	@Override
	public void update(DeltaState deltaState) {
		if(deltaState.isKeyPressed(Key.N)) {
			this.getGame().setCurrentScene(((ArkanoidGame)this.getGame()).buildArkanoidScene());
		}
		super.update(deltaState);
	}

}