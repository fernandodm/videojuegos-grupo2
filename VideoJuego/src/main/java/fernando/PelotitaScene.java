package fernando;

import yoni.Raqueta;

import com.uqbar.vainilla.GameScene;

public class PelotitaScene extends GameScene {


	private Pelota pelota;
	private Raqueta raqueta;
	
	public Pelota getPelota() {
		return pelota;
	}

	public void setPelota(Pelota pelota) {
		this.addComponent(pelota);
		this.pelota = pelota;
	}
	
	
	

	public Raqueta getRaqueta() {
		return raqueta;
	}

	public void setRaqueta(Raqueta raqueta) {
		this.raqueta = raqueta;
	}


}
