package soccer.estados;

import soccer.Direccion;
import soccer.Jugador;
import soccer.Pelota;

import com.uqbar.vainilla.DeltaState;

public class EstadoArqueroCpu extends EstadoArquero{
	
	public EstadoArqueroCpu(Jugador jugador){
		this.setJugador(jugador);
	}

	@Override
	public void update(DeltaState deltaState) {
		Pelota pelota = this.getJugador().getScene().getPelota();
		double yCancha= this.getJugador().getScene().getCancha().getY() ;
		
		
		
		if(pelota.getY() < 217 && pelota.getY() > 67 && (yCancha > -10) ){
			this.achicar(deltaState);
		}else{
			this.resetPos(deltaState);
		}
	}

	public void moverseHaciaCostados(DeltaState deltaState) {
		if(this.getJugador().getScene().getPelota().getX() > 640){
			if(this.getJugador().getX() < 660){
			this.getJugador().right(deltaState);
			this.getJugador().ejecutarSprite(deltaState, Direccion.RIGHT,0);
			}
		}
		
		if(this.getJugador().getScene().getPelota().getX() < 640){
			if(this.getJugador().getX() > 590){
			this.getJugador().left(deltaState);
			this.getJugador().ejecutarSprite(deltaState, Direccion.LEFT,0);
			}
		}
	}
	
	public void achicar(DeltaState deltaState){
		Pelota pelota = this.getJugador().getScene().getPelota();
		boolean gol = (pelota.getX()>550 && pelota.getX()<700 );
		if( this.getJugador().getX() > pelota.getX() && gol ){
			this.getJugador().left(deltaState);
			this.getJugador().ejecutarSprite(deltaState, Direccion.LEFT,0);
			
			if(this.getJugador().getY() < pelota.getY()){
				this.getJugador().down(deltaState);
				this.getJugador().ejecutarSprite(deltaState, Direccion.DOWN,0);
			}else{
				this.getJugador().up(deltaState);
				this.getJugador().ejecutarSprite(deltaState, Direccion.UP,0);
			}
		}
		if( this.getJugador().getX() < pelota.getX() && gol ){
			this.getJugador().right(deltaState);
			this.getJugador().ejecutarSprite(deltaState, Direccion.RIGHT,0);
			
			if(this.getJugador().getY() < pelota.getY()){
				this.getJugador().down(deltaState);
				this.getJugador().ejecutarSprite(deltaState, Direccion.DOWN,0);
			}else{
				this.getJugador().up(deltaState);
				this.getJugador().ejecutarSprite(deltaState, Direccion.UP,0);
			}
		}

	}

	public void resetPos(DeltaState deltaState) {
		
		this.moverseHaciaCostados(deltaState);
		
		if(this.getJugador().getY() > 70 ){
			this.getJugador().up(deltaState);
			this.getJugador().ejecutarSprite(deltaState, Direccion.UP,0);
		}
	}
	
	public void resetHaciaCostados(DeltaState deltaState) {
			if(this.getJugador().getX() < 660){
			this.getJugador().right(deltaState);
			this.getJugador().ejecutarSprite(deltaState, Direccion.RIGHT,0);
			}
			if(this.getJugador().getX() > 590){
			this.getJugador().left(deltaState);
			this.getJugador().ejecutarSprite(deltaState, Direccion.LEFT,0);
			}
	}
	
}
