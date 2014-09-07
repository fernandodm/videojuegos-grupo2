package fernando;

import com.uqbar.vainilla.GameScene;

public class PelotitaScene extends GameScene {


	private Pelota pelota;
	
	public Pelota getPelota() {
		return pelota;
	}

	public void setPelota(Pelota pelota) {
		this.addComponent(pelota);
		this.pelota = pelota;
	}
}
