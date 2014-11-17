package soccer.estados;

import soccer.Direccion;
import soccer.Jugador;

import com.uqbar.vainilla.DeltaState;

public class EstadoJugadorNoSeleccionadoCPU extends EstadoJugador {
	
	public EstadoJugadorNoSeleccionadoCPU(Jugador jugador) {
		this.setJugador(jugador);
	}

	public void update(DeltaState deltaState) {
		if(Utils.distanciaConPelota(this.getJugador().getX(), this.getJugador().getY())> 150
				|| Utils.tienePelotaVisitante()){
			return;
		}
		int direccion = Utils.direccionPelota(this.getJugador().getX(),this.getJugador().getY());
		switch (direccion) {
		case Direccion.DOWN:
			this.getJugador().up(deltaState);
			this.getJugador().ejecutarSprite(deltaState, Direccion.UP,0);
			break;
		case Direccion.UP:
			this.getJugador().down(deltaState);
			this.getJugador().ejecutarSprite(deltaState, Direccion.DOWN,0);
			break;
		case Direccion.RIGHT:
			this.getJugador().left(deltaState);
			this.getJugador().ejecutarSprite(deltaState, Direccion.LEFT,0);
			break;
		case Direccion.LEFT:
			this.getJugador().right(deltaState);
			this.getJugador().ejecutarSprite(deltaState, Direccion.RIGHT,0);
			break;
		case Direccion.UPLEFT:
			this.getJugador().upRight(deltaState);
			this.getJugador().ejecutarSprite(deltaState, Direccion.UP,0.6);
			break;
		case Direccion.UPRIGHT:
			this.getJugador().upLeft(deltaState);
			this.getJugador().ejecutarSprite(deltaState, Direccion.UP,-0.6);
			break;
		case Direccion.DOWNLEFT:
			this.getJugador().downRight(deltaState);
			this.getJugador().ejecutarSprite(deltaState, Direccion.UP,2);
			break;
		case Direccion.DOWNRIGHT:
			this.getJugador().downLeft(deltaState);
			this.getJugador().ejecutarSprite(deltaState, Direccion.UP,-2);
			break;
//		}		
	}
	}

}
