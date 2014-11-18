package soccer.estados;

import soccer.Direccion;
import soccer.Jugador;
import soccer.Pelota;

import com.uqbar.vainilla.DeltaState;

public class EstadoArqueroCpu extends EstadoJugador{
	
	public EstadoArqueroCpu(Jugador jugador){
		this.setJugador(jugador);
	}

	@Override
	public void update(DeltaState deltaState) {
		Pelota pelota = this.getJugador().getScene().getPelota();
		boolean gol = (pelota.getX()>550 && pelota.getX()<700 );
		
		if(pelota.getY() < 217 && pelota.getY() > 67){
			if( this.getJugador().getX() > pelota.getX() && gol ){
				this.getJugador().left(deltaState);
				this.getJugador().ejecutarSprite(deltaState, Direccion.LEFT,0);
			}
			if( this.getJugador().getX() < pelota.getX() && gol ){
				this.getJugador().right(deltaState);
				this.getJugador().ejecutarSprite(deltaState, Direccion.RIGHT,0);
			}
		}else{
			this.resetPos(deltaState);
		}	
	}

	private void resetPos(DeltaState deltaState) {
//		this.getJugador().
		if(this.getJugador().getX() > 625 && this.getJugador().getX() < 700 ){
			this.getJugador().left(deltaState);
			this.getJugador().ejecutarSprite(deltaState, Direccion.LEFT,0);
		}
		if(this.getJugador().getX() < 625 && this.getJugador().getX() > 500 ){
			this.getJugador().right(deltaState);
			this.getJugador().ejecutarSprite(deltaState, Direccion.RIGHT,0);
		}
	}
	
}
