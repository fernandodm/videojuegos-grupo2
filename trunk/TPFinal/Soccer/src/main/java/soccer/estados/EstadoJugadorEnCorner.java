package soccer.estados;

import soccer.Jugador;

import com.uqbar.vainilla.DeltaState;

public class EstadoJugadorEnCorner extends EstadoJugador {

	
	public EstadoJugadorEnCorner(Jugador jugador) {
		this.setJugador(jugador);
	}
	
	@Override
	public void update(DeltaState deltaState) {
		// TODO Auto-generated method stub
		
	}

}
