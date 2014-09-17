package Arkanoid;

import java.util.ArrayList;

import com.uqbar.vainilla.GameScene;

public class ArkanoidScene extends GameScene {


	private Pelota pelota;
	private Paleta raqueta;
	private ArrayList<Ladrillo> ladrillos;

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
	
	public void setMarcador(Marcador marcador) {
		this.addComponent(marcador);
	}
	
	public void fin() {
		this.getGame().setCurrentScene(((ArkanoidGame)this.getGame()).buildScene("Perdiste!!! Presione N para volver a jugar."));		
	}

	public void reiniciar() {
		this.getPelota().setX(200);
		this.getPelota().setY(430);
		this.getPelota().setDireccion(new Vector(5, 5));
		this.getPelota().setFlag(false);
		this.getRaqueta().setX(200);
		this.getRaqueta().setY(450);
		this.getRaqueta().setFlag(true);
		this.getRaqueta().setPegajosa(false);
	}

	public ArrayList<Ladrillo> getLadrillos() {
		return ladrillos;
	}

	public void setLadrillos(ArrayList<Ladrillo> ladrillos) {
		this.ladrillos = ladrillos;
	}

	public void ganaste() {
		this.getGame().setCurrentScene(((ArkanoidGame)this.getGame()).buildScene("Ganaste!!! Presione N para volver a jugar."));
	}

	

}
