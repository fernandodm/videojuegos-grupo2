package fernando;

import com.uqbar.vainilla.GameScene;

public class ArkanoidScene extends GameScene {


	private Pelota pelota;
	private Paleta raqueta;
	
	public Pelota getPelota() {
		return pelota;
	}

	public void setPelota(Pelota pelota) {
		this.addComponent(pelota);
		this.pelota = pelota;
	}
	
	public Paleta getRaqueta() {
		return raqueta;
	}

	public void setRaqueta(Paleta raqueta) {
		this.addComponent(raqueta);
		this.raqueta = raqueta;
	}

}
