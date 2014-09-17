package Arkanoid;

import java.awt.Color;
import java.util.ArrayList;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.appearances.Circle;

public class Bala extends GameComponent<ArkanoidScene> {
	ArrayList<Ladrillo> ladrillos;
	public Bala(double x, double y, ArrayList<Ladrillo> ladrillos) {
		super(new Circle(Color.BLACK, 5),x,y);
		this.ladrillos = ladrillos;
	}

	public void update(DeltaState deltaState) {
			this.setY(this.getY()-0.3);
			
			ArrayList<Ladrillo> muertos = new ArrayList<Ladrillo>();
			Vector nuevaPosicion = new Vector(this.getX(), this.getY());
			Pelota pelota = new Pelota(2, 2, 2, nuevaPosicion, 2, null,null);
			for (Ladrillo ladrillo : ladrillos) {
				if(ladrillo.collision(deltaState, pelota,nuevaPosicion)){
					muertos.add(ladrillo);
					this.getScene().removeComponent(this);
				};
			}
			
			for (Ladrillo ladrillo : muertos) {
				ladrillos.remove(ladrillo);
			}	
	}
	
	public boolean collision(DeltaState deltaState, Pelota pelota, Vector nuevaPosicion) {
		boolean colisiona;
		if (colisiona=Colision.colisiona(this, pelota, nuevaPosicion)) {
			Colision.apply(this, pelota,nuevaPosicion);
		}
		return colisiona;
	}
	
}
