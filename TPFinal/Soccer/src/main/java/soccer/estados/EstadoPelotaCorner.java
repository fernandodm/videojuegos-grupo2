package soccer.estados;

import soccer.Direccion;
import soccer.Jugador;
import soccer.Pelota;
import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.events.constants.Key;

public class EstadoPelotaCorner extends EstadoPelota {

	public EstadoPelotaCorner(Pelota pelota) {
		this.setPelota(pelota);
	}

	@Override
	public void update(DeltaState deltaState) {
		int direccion = Direccion.obtenerDireccion(deltaState);
//		for(Jugador jugador : this.getPelota().getJugador().equipoContrario().getJugadores()){
//			if(jugador.isEstaSeleccionado()){
//				jugador.setEstaSeleccionado(false);
//				jugador.setEstado(new EstadoJugadorNoSeleccionado(jugador));
//				break;
//			}
//		}
		if(this.noApretoDireccion(direccion) || this.getPelota().isEnRemate()){
			if(deltaState.isKeyPressed(Key.ENTER)){
				this.getPelota().activarRemate(deltaState);
				this.getPelota().getJugador().setEstado(new EstadoJugadorSeleccionado(this.getPelota().getJugador()));
				this.getPelota().setRemateEnCorner(true);
			}
			this.getPelota().moverPelotaPorRemate(deltaState);
		}
	}
	
	public boolean noApretoDireccion(int direccion){
		return Direccion.LEFT != direccion && Direccion.DOWN != direccion 
				&& Direccion.RIGHT != direccion && Direccion.UP != direccion
				&& Direccion.DOWNLEFT != direccion && Direccion.UPLEFT != direccion
				&& Direccion.DOWNRIGHT != direccion && Direccion.UPRIGHT != direccion;
	}
}
