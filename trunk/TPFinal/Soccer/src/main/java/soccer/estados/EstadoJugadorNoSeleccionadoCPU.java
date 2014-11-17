package soccer.estados;

import soccer.Jugador;

import com.uqbar.vainilla.DeltaState;

public class EstadoJugadorNoSeleccionadoCPU extends EstadoJugador {
	
	public EstadoJugadorNoSeleccionadoCPU(Jugador jugador) {
		this.setJugador(jugador);
	}

	public void update(DeltaState deltaState) {

	}

}
