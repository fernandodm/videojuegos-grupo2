package escenas;

import java.util.ArrayList;

import Arkanoid.ArkanoidGame;
import Arkanoid.Vector;

import com.uqbar.vainilla.GameScene;

import componentes.Marcador;
import componentes.Pelota;
import componentes.Raqueta;
import componentes.ladrillos.Ladrillo;

public class ArkanoidScene extends GameScene {


	private Pelota pelota;
	private Raqueta raqueta;
	private ArrayList<Ladrillo> ladrillos;

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
