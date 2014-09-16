package Arkanoid;

import java.awt.Color;
import java.awt.Font;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.appearances.Label;
import com.uqbar.vainilla.events.constants.Key;

public class FinalScene extends GameComponent<GanasteOPerdisteScene> {

	public FinalScene(double x, double y, String msj) {
		super(new Label(new Font("verdana",  Font.BOLD, 18), Color.BLUE, msj), x, y);
	}
		
	@Override
	public void update(DeltaState deltaState) {
		if(deltaState.isKeyPressed(Key.N)) {
			this.getGame().setCurrentScene(((ArkanoidGame)this.getGame()).buildArkanoidScene());
		}
		super.update(deltaState);
	}

}
