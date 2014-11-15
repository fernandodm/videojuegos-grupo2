package soccer.estados;

import soccer.Direccion;
import soccer.Pelota;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.events.constants.Key;

public class EstadoPelotaEnLateral extends EstadoPelota {


	public EstadoPelotaEnLateral(Pelota pelota) {
		this.setPelota(pelota);
	
	}

	@Override
	public void update(DeltaState deltaState) {
		int direccion = Direccion.obtenerDireccion(deltaState);
		if(noApretoDireccion(direccion) || this.getPelota().isEnRemate()){
			if(deltaState.isKeyPressed(Key.ENTER)){
				this.getPelota().activarRemate(deltaState);
				this.getPelota().getJugador().setEstado(new EstadoJugadorSeleccionado(this.getPelota().getJugador()));
			}
			this.getPelota().moverPelotaPorRemate(deltaState);
		}
	}
	
	public boolean noApretoDireccion(int direccion){
		
		return Direccion.LEFT != direccion && Direccion.DOWN != direccion 
				&& Direccion.UP != direccion && Direccion.UP != direccion
				&& Direccion.DOWNLEFT != direccion && Direccion.UPLEFT != direccion
				&& Direccion.DOWNRIGHT != direccion && Direccion.UPRIGHT != direccion;
	}

	@Override
	public void cambiar(EstadoPelotaEnJuego estadoPelotaEnJuego) {
		this.getPelota().setEstadoPelota(estadoPelotaEnJuego);
		
	}

}
