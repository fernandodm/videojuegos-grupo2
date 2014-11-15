package soccer.estados;

import soccer.Direccion;
import soccer.Jugador;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.events.constants.Key;

public class EstadoJugadorEnLateral extends EstadoJugador {
	//variables para q se mueva en los laterales
	int seMovioUp = 1;
	int seMovioDown = 1;
	boolean seMovio = false;
	
	public EstadoJugadorEnLateral(Jugador jugador) {
		this.setJugador(jugador);
	}

	@Override
	public void update(DeltaState deltaState) {
		
		if(this.getJugador().getScene().getPelota().getX() < 162){
			this.ejecutar(Direccion.RIGHT, Direccion.DOWNRIGHT, Direccion.UPRIGHT, 0, 0.6, deltaState);
		}else{		
			this.ejecutar(Direccion.LEFT, Direccion.DOWNLEFT, Direccion.UPLEFT, 3, -0.6, deltaState);
		}

	}

	private void ejecutar(int dir, int downright, int upright, int n, double radio, DeltaState deltaState) {
		int direccion = obtenerDireccion(deltaState);
		//por si no se apreto ninguna tecla
		if((direccion == 0 && !seMovio))
			this.getJugador().getScene().getPelota().setUltimaDireccion(dir);
		
		switch (direccion) {
		case Direccion.UP:
			
			if(seMovioUp == 1){
				this.getJugador().setAppearance(this.getJugador().getImages().get(Direccion.DOWN).get(3).rotate(-radio));
				this.getJugador().setY(this.getJugador().getY() - 20);
				this.getJugador().getLabelSeleccionado().setY(this.getJugador().getLabelSeleccionado().getY() - 20);
				this.getJugador().getScene().getPelota().setUltimaDireccion(downright);
				seMovioUp--;
				seMovioDown++;
			}
			if(seMovioUp == 2){
				this.getJugador().setAppearance(this.getJugador().getImages().get(dir).get(n));
				this.getJugador().setY(this.getJugador().getY() - 20);
				this.getJugador().getLabelSeleccionado().setY(this.getJugador().getLabelSeleccionado().getY() - 20);
				seMovioUp--;
				seMovioDown++;
				this.getJugador().getScene().getPelota().setUltimaDireccion(dir);
			}
			seMovio = true;
			break;
		case Direccion.DOWN:

			if(seMovioDown == 1){
				this.getJugador().setAppearance(this.getJugador().getImages().get(Direccion.UP).get(3).rotate(radio));
				this.getJugador().setY(this.getJugador().getY() + 20);
				this.getJugador().getLabelSeleccionado().setY(this.getJugador().getLabelSeleccionado().getY() + 20);
				this.getJugador().getScene().getPelota().setUltimaDireccion(upright);
				seMovioUp++;
				seMovioDown--;
			}
			if(seMovioDown == 2){
				this.getJugador().setAppearance(this.getJugador().getImages().get(dir).get(n));
				this.getJugador().setY(this.getJugador().getY() + 20);
				this.getJugador().getLabelSeleccionado().setY(this.getJugador().getLabelSeleccionado().getY() + 20);
				this.getJugador().getScene().getPelota().setUltimaDireccion(dir);
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
