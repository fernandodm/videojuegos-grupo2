package escenas;

import com.uqbar.vainilla.GameScene;

public class GanasteOPerdisteScene extends GameScene {

	@SuppressWarnings("unchecked")
	public GanasteOPerdisteScene( double x, double y, String msj) {
		super(new FinalScene(x, y, msj));
	}

}
