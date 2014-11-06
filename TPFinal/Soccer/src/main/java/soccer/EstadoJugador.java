package soccer;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.GameComponent;

public abstract class EstadoJugador extends GameComponent<SoccerScene> {
	
	private Jugador jugador;

	public Jugador getJugador() {
		return jugador;
	}

	public void setJugador(Jugador jugador) {
		this.jugador = jugador;
	}
	
	public abstract void update(DeltaState deltaState);

}
