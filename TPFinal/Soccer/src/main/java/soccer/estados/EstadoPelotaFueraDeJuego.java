package soccer.estados;

import soccer.Pelota;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.events.constants.Key;

public class EstadoPelotaFueraDeJuego extends EstadoPelota {


	public EstadoPelotaFueraDeJuego(Pelota pelota) {
		this.setPelota(pelota);
	
	}

	@Override
	public void update(DeltaState deltaState) {
		if(deltaState.isKeyPressed(Key.ENTER)){
			this.getPelota().activarRemate(deltaState);
			this.getPelota().getJugador().setEstado(new EstadoJugadorSeleccionado(this.getPelota().getJugador()));
		}
		this.getPelota().moverPelotaPorRemate(deltaState);
	}

	@Override
	public void cambiar(EstadoPelotaEnJuego estadoPelotaEnJuego) {
		this.getPelota().setEstadoPelota(estadoPelotaEnJuego);
		
	}

}
