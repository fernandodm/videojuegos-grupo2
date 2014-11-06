package soccer;


import java.util.List;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.GameComponent;

public class EstadoPelotaEnJuego extends EstadoPelota {
//	List<Jugador> jugadores = super.getScene().getJugadores();
	

	public EstadoPelotaEnJuego(Pelota pelota) {
		this.setPelota(pelota);
	}

	@Override
	public void update(DeltaState deltaState) {
//		List<Jugador> jugadores = super.getScene().getJugadores(); 
//		
//		for (Jugador jugador : jugadores) {
//			Vector nuevaPosicion = new Vector(jugador.getX(), jugador.getY());
//				if(Colision.mustApply(this, jugador, nuevaPosicion) && jugador.isEstaSeleccionado()){
//					this.getPelota().setJugador(jugador);
//					this.getPelota().ejecutarMovimiento(deltaState, jugador.getX(), jugador.getY());
//					jugador.flag = true;
//
//					this.getPelota().activarRemate(deltaState);
//				
//				}else{
//					jugador.flag = false;
//				}
//		}
//		this.getPelota().moverPelotaPorRemate(deltaState);
	}

}
