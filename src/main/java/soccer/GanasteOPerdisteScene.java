package soccer;

import com.uqbar.vainilla.GameScene;

public class GanasteOPerdisteScene extends GameScene {

	@SuppressWarnings("unchecked")
	public GanasteOPerdisteScene(String msj, String resultado) {
		super(new FinalScene(msj, resultado));
	}

}
