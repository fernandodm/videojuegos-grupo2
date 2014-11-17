package soccer.estados;

import soccer.Direccion;
import soccer.Jugador;

import com.uqbar.vainilla.DeltaState;

public class EstadoJugadorSeleccionadoCPU extends EstadoJugador {
	
	public EstadoJugadorSeleccionadoCPU(Jugador jugador){
		this.setJugador(jugador);
	}

	@Override
	public void update(DeltaState deltaState) {
//		if(!(this.getScene().getPelota().getX() < 173) && !(this.getScene().getPelota().getX() > 1091) ){

	}
}
