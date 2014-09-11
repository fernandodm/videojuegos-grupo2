package Arkanoid;

import com.uqbar.vainilla.GameScene;

public class FinalScene extends GameScene {

	@SuppressWarnings("unchecked")
	public FinalScene( double x, double y) {
		super(new PerdisteScene( x, y));
	}

}
