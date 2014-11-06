package soccer.estados;


import soccer.Colision;
import soccer.Jugador;
import soccer.Pelota;
import soccer.Vector;

import com.uqbar.vainilla.DeltaState;

public class EstadoPelotaEnJuego extends EstadoPelota {

	public EstadoPelotaEnJuego(Pelota pelota) {
		this.setPelota(pelota);
	}

	@Override
	public void update(DeltaState deltaState) {
		for (Jugador jugador : this.getPelota().getScene().getEquipoLocal().getJugadores()) {
			Vector nuevaPosicion = new Vector(jugador.getX(),
					jugador.getY());
			if (Colision.mustApply(this.getPelota(), jugador, nuevaPosicion)
					&& jugador.isEstaSeleccionado()) {
				this.getPelota().setJugador(jugador);
				this.getPelota().ejecutarMovimiento(deltaState, jugador.getX(),
						jugador.getY());
				jugador.flag = true;
				this.getPelota().activarRemate(deltaState);
			} else {
				jugador.flag = false;
			}
		}

		this.getPelota().moverPelotaPorRemate(deltaState);
		
		if(this.getPelota().getX() < 170 ){
			this.getPelota().setEstadoPelota(new EstadoPelotaFueraDeJuego(this.getPelota()));
			for (Jugador jugador : this.getPelota().getScene().getEquipoLocal().getJugadores()) {
				if(jugador.isEstaSeleccionado()){
					jugador.setEstado(new EstadoJugadorEnLateral(jugador));
					break;
				}
			}
		}
		System.out.println(this.getPelota().getX());
	}

}
