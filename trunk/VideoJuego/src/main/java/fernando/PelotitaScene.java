package fernando;

import java.awt.Color;

import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.GameScene;
import com.uqbar.vainilla.appearances.Rectangle;

public class PelotitaScene extends GameScene {


	private Pelota pelota;
	
	public Pelota getPelota() {
		return pelota;
	}

	public void setPelota(Pelota pelota) {
		this.addComponent(pelota);
		this.pelota = pelota;
	}

	
	public void fin() {
		//this.getGame().setCurrentScene(((Pong)this.getGame()).buildEndScene(this.marcadorComputer, this.marcadorPlayer));		
	}

}
