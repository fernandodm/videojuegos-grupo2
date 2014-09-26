package asteroids;

import com.uqbar.vainilla.GameScene;

public class AsteroidsScene extends GameScene {
	
	private AsteroidsFondo asteroidFondo;
	private Nave nave;

	public AsteroidsFondo getAsteroidFondo() {
		return asteroidFondo;
	}

	public void setAsteroidFondo(AsteroidsFondo asteroidFondo) {
		this.asteroidFondo = asteroidFondo;
		this.addComponent(asteroidFondo);
	}

	public Nave getNave() {
		return nave;
	}

	public void setNave(Nave nave) {
		this.nave = nave;
		this.addComponent(nave);
	}

}
