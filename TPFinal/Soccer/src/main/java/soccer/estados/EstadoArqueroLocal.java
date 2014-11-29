package soccer.estados;

import soccer.Direccion;
import soccer.Jugador;
import soccer.Pelota;

import com.uqbar.vainilla.DeltaState;

public class EstadoArqueroLocal extends EstadoArquero {
	
	public EstadoArqueroLocal(Jugador jugador) {
		this.setJugador(jugador);
	}

	@Override
	public void update(DeltaState deltaState) {
		Pelota pelota = this.getJugador().getScene().getPelota();
		
		if(pelota.getY() < 528 && pelota.getY() > 430){
			this.achicar(deltaState);
		}else{
			this.resetPos(deltaState);
		}		
	}

	@Override
	public void achicar(DeltaState deltaState) {
		Pelota pelota = this.getJugador().getScene().getPelota();
		boolean gol = (pelota.getX()>550 && pelota.getX()<700 );
		if( this.getJugador().getX() > pelota.getX() && gol ){
			this.getJugador().left(deltaState);
			this.getJugador().ejecutarSprite(deltaState, Direccion.LEFT,0);
			
			if(this.getJugador().getY() > pelota.getY()){
				this.getJugador().up(deltaState);
				this.getJugador().ejecutarSprite(deltaState, Direccion.UP,0);
			}else{
//				this.getJugador().up(deltaState);
//				this.getJugador().ejecutarSprite(deltaState, Direccion.UP,0);
			}
			
		}
			
		if( this.getJugador().getX() < pelota.getX() && gol ){
			this.getJugador().right(deltaState);
			this.getJugador().ejecutarSprite(deltaState, Direccion.RIGHT,0);
			
			if(this.getJugador().getY() > pelota.getY()){
				this.getJugador().up(deltaState);
				this.getJugador().ejecutarSprite(deltaState, Direccion.UP,0);
				}else{
//				this.getJugador().up(deltaState);
//				this.getJugador().ejecutarSprite(deltaState, Direccion.UP,0);
			}
			
		}
			
		
	}

	@Override
	public void resetPos(DeltaState deltaState) {
		
		if(this.getJugador().getX() > 625 && this.getJugador().getX() < 700 ){
			this.getJugador().left(deltaState);
			this.getJugador().ejecutarSprite(deltaState, Direccion.LEFT,0);
			
		}
		if(this.getJugador().getX() < 625 && this.getJugador().getX() > 500 ){
			this.getJugador().right(deltaState);
			this.getJugador().ejecutarSprite(deltaState, Direccion.RIGHT,0);
		}
		
		if(this.getJugador().getY() < 495 ){
			this.getJugador().down(deltaState);
			this.getJugador().ejecutarSprite(deltaState, Direccion.DOWN,0);
		}
	}

}
