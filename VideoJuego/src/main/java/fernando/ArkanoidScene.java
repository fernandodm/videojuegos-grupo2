package fernando;

import java.awt.Color;

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
	
	public void fin() {
		this.getGame().setCurrentScene(((ArkanoidGame)this.getGame()).buildEndScene());		
	}

	public void reiniciar() {
		this.getPelota().setX(200);
		this.getPelota().setY(430);
		this.getPelota().setDireccion(new Vector(5, 5));
		this.getRaqueta().setX(200);
		this.getRaqueta().setY(450);
		this.getPelota().setFlag(false);
		this.getRaqueta().setFlag(false);
	}

}
