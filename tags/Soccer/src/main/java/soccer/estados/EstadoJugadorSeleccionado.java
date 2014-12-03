package soccer.estados;

import soccer.Direccion;
import soccer.Jugador;

import com.uqbar.vainilla.DeltaState;

public class EstadoJugadorSeleccionado extends EstadoJugador {
	
	public EstadoJugadorSeleccionado(Jugador jugador){
		this.setJugador(jugador);
	}

	@Override
	public void update(DeltaState deltaState) {
			int direccion = Direccion.obtenerDireccion(deltaState);
				switch (direccion) {
				case Direccion.UP:
					this.getJugador().up(deltaState);
					this.getJugador().ejecutarSprite(deltaState, Direccion.UP,0);
					break;
				case Direccion.DOWN:
					this.getJugador().down(deltaState);
					this.getJugador().ejecutarSprite(deltaState, Direccion.DOWN,0);
					break;
				case Direccion.LEFT:
					this.getJugador().left(deltaState);
					this.getJugador().ejecutarSprite(deltaState, Direccion.LEFT,0);
					break;
				case Direccion.RIGHT:
					this.getJugador().right(deltaState);
					this.getJugador().ejecutarSprite(deltaState, Direccion.RIGHT,0);
					break;
				case Direccion.UPRIGHT:
					this.getJugador().upRight(deltaState);
					this.getJugador().ejecutarSprite(deltaState, Direccion.UP,0.6);
					break;
				case Direccion.UPLEFT:
					this.getJugador().upLeft(deltaState);
					this.getJugador().ejecutarSprite(deltaState, Direccion.UP,-0.6);
					break;
				case Direccion.DOWNRIGHT:
					this.getJugador().downRight(deltaState);
					this.getJugador().ejecutarSprite(deltaState, Direccion.UP,2);
					break;
				case Direccion.DOWNLEFT:
					this.getJugador().downLeft(deltaState);
					this.getJugador().ejecutarSprite(deltaState, Direccion.UP,-2);
					break;
			}
		}
}
