package soccer.estados;

import soccer.Direccion;
import soccer.Jugador;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.events.constants.Key;

public class EstadoJugadorEnCorner extends EstadoJugador {
	//variables para q se mueva en los corners
	int seMovioUp = 1;
	int seMovioDown = 0;
	boolean seMovio = false;
	
	public EstadoJugadorEnCorner(Jugador jugador) {
		this.setJugador(jugador);
	}
	
	@Override
	public void update(DeltaState deltaState) {

		if(this.getJugador().getScene().getPelota().getY() < 50){
			if(this.getJugador().getScene().getPelota().getX() < 640){
				this.ejecutar(Direccion.RIGHT, Direccion.DOWNRIGHT, Direccion.UPRIGHT, 3, 0.3, deltaState, 20, 22);
			}else{
				this.ejecutar(Direccion.LEFT, Direccion.DOWNLEFT, Direccion.UPLEFT, 3, -0.3, deltaState, -22, 20);
			}
		}else{
			if(this.getJugador().getScene().getPelota().getX() < 640){
				this.ejecutar(Direccion.RIGHT, Direccion.DOWNRIGHT, Direccion.UPRIGHT, 0, 0, deltaState, 20, 22);
			}else{
				this.ejecutar(Direccion.LEFT, Direccion.DOWNLEFT, Direccion.UPLEFT, 3, 0.3, deltaState, -22, -21);
			}
		}
	}
	
	private void ejecutar(int dir, int downright, int upright, int n, double radio, DeltaState deltaState, double sumX, double sumY) {
		int direccion = obtenerDireccion(deltaState);
		//por si no se apreto ninguna tecla
		if((direccion == 0 && !seMovio))
			this.getJugador().getScene().getPelota().setUltimaDireccion(dir);
		
		switch (direccion) {
		case Direccion.UP:
			
			if(seMovioUp == 1){
				this.getJugador().setAppearance(this.getJugador().getImages().get(Direccion.DOWN).get(n));
				this.getJugador().setY(this.getJugador().getY() - sumY);
				this.getJugador().setX(this.getJugador().getX() + sumX);
				this.getJugador().getLabelSeleccionado().setY(this.getJugador().getLabelSeleccionado().getY() - sumY);
				this.getJugador().getLabelSeleccionado().setX(this.getJugador().getLabelSeleccionado().getX() + 22);
				this.getJugador().getScene().getPelota().setUltimaDireccion(downright);
				seMovioUp--;
				seMovioDown++;
			}
			seMovio = true;
			break;
		case Direccion.DOWN:

			if(seMovioDown == 1){
				this.getJugador().setAppearance(this.getJugador().getImages().get(dir).get(n).rotate(radio));
				this.getJugador().setY(this.getJugador().getY() + sumY);
				this.getJugador().setX(this.getJugador().getX() - sumX);
				this.getJugador().getLabelSeleccionado().setY(this.getJugador().getLabelSeleccionado().getY() + sumY);
				this.getJugador().getLabelSeleccionado().setX(this.getJugador().getLabelSeleccionado().getX() - 22);
				this.getJugador().getScene().getPelota().setUltimaDireccion(upright);
				seMovioUp++;
				seMovioDown--;
			}
			seMovio = true;
			break;
		}
		
	}

	private int obtenerDireccion(DeltaState deltaState) {
		if (deltaState.isKeyPressed(Key.W)) {
			return Direccion.UP;
		} 
			
		if (deltaState.isKeyPressed(Key.S)) {
			return Direccion.DOWN;
		}
	
		return 0;
	}

}
