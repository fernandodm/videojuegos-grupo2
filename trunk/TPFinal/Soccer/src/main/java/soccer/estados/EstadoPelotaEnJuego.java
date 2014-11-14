package soccer.estados;


import java.util.List;

import soccer.Colision;
import soccer.Direccion;
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
				
		for (Jugador jugador : this.getPelota().getScene().getJugadores()) {
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
		this.verificarCorner();
		this.verificarLateral();
	}

	private void verificarCorner() {
		if(this.getPelota().getY() < 50 || this.getPelota().getY()>528){
			this.getPelota().setEstadoPelota(new EstadoPelotaCorner(this.getPelota()));
		}
	}
	

	private void verificarLateral() {
		if(this.getPelota().getX() < 162 || this.getPelota().getX() > 1090){
			this.getPelota().setEstadoPelota(new EstadoPelotaFueraDeJuego(this.getPelota()));
			for (Jugador jugador : this.getPelota().getScene().getJugadores()) {
				if(jugador.isEstaSeleccionado()){
					jugador.setEstado(new EstadoJugadorEnLateral(jugador));
					jugador.setAppearance(jugador.getImages().get(Direccion.RIGHT).get(0));
					this.pocisionarJugadorLateral(jugador);
					this.getPelota().setEnRemate(false);
					break;
				}
			}
		}
	}

	private void pocisionarJugadorLateral(Jugador jugador) {
		if(this.getPelota().getX() < 162){
			jugador.setX(this.getPelota().getX()-25);
			jugador.setY(this.getPelota().getY()-3);
			jugador.getLabelSeleccionado().setX(jugador.getX() - 10);
			jugador.getLabelSeleccionado().setY(jugador.getY() + 6);
		}else{
			jugador.setX(this.getPelota().getX()+10);
			jugador.setY(this.getPelota().getY()-3);
			jugador.getLabelSeleccionado().setX(jugador.getX() + 25);
			jugador.getLabelSeleccionado().setY(jugador.getY() + 10);
		}
		
	}

	@Override
	public void cambiar(EstadoPelotaEnJuego estadoPelotaEnJuego) {
		// TODO Auto-generated method stub
		
	}

}
