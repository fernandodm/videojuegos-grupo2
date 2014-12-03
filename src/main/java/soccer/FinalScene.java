package soccer;

import java.awt.Color;
import java.awt.Font;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.appearances.Label;
import com.uqbar.vainilla.events.constants.Key;

public class FinalScene extends GameComponent<GanasteOPerdisteScene> {

	public FinalScene(String msj, String resultado) {
		super(new Label(new Font("verdana",  Font.BOLD, 18), Color.BLUE, msj,"", resultado), 0, 0);
	}
	
	@Override
	public void update(DeltaState deltaState) {
		if(deltaState.isKeyPressed(Key.N)) {
			((SoccerGame) this.getGame()).setUpScenes();
		}
		super.update(deltaState);
	}

}
