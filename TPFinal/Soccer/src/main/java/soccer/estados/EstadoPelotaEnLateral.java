package soccer.estados;

import soccer.Direccion;
import soccer.Jugador;
import soccer.JugadorLocal;
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
			if(deltaState.isKeyPressed(Key.ENTER) && this.getPelota().getJugador() instanceof JugadorLocal){
				this.cambiarEstados();
				this.getPelota().activarRemate(deltaState);
				this.getPelota().getJugador().setEstado(new EstadoJugadorSeleccionado(this.getPelota().getJugador()));
			}
			this.getPelota().moverPelotaPorRemate(deltaState);
		}
	}
	
	private void cambiarEstados() {
		for(Jugador jug: this.getPelota().getScene().getJugadores()){
			jug.setEstadoNoSeleccionado();
		}
		
	}

	public boolean noApretoDireccion(int direccion){
		
		return Direccion.LEFT != direccion && Direccion.DOWN != direccion 
				&& Direccion.RIGHT != direccion && Direccion.UP != direccion
				&& Direccion.DOWNLEFT != direccion && Direccion.UPLEFT != direccion
				&& Direccion.DOWNRIGHT != direccion && Direccion.UPRIGHT != direccion;
	}

}
