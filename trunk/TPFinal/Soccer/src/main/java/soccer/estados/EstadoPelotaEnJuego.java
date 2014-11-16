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
		if(this.getPelota().getY() < 50 || this.getPelota().getY() > 528){
			this.getPelota().setEstadoPelota(new EstadoPelotaCorner(this.getPelota()));
		}
	}
	

	private void verificarLateral() {
		if(this.getPelota().getX() < 162 || this.getPelota().getX() > 1090){
			this.getPelota().setEstadoPelota(new EstadoPelotaEnLateral(this.getPelota()));
			seleccionarJugadorAlLateral();
		}
	}

	public void seleccionarJugadorAlLateral() {
		Jugador jugador = this.getPelota().getJugador();
		jugador.setFlag(false);
		jugador.setEstaSeleccionado(false);
		jugador.setEstado(new EstadoJugadorNoSeleccionado(jugador));
		if(jugador.getX() < 202 || jugador.getX() > 1050 && !jugador.isEstaSeleccionado())
			jugador.setY(jugador.getY() + 80);
		
		List<Jugador> juagdores = jugador.equipoContrario().getJugadores();
		Jugador jugadorAlLateral = juagdores.get(4);
		
		jugadorAlLateral.setFlag(true);
		jugadorAlLateral.setEstaSeleccionado(true);
		this.getPelota().setJugador(jugadorAlLateral);
		jugadorAlLateral.setEstado(new EstadoJugadorEnLateral(jugadorAlLateral));
		jugadorAlLateral.setAppearance(jugadorAlLateral.getImages().get(Direccion.RIGHT).get(0));
		this.pocisionarJugadorLateral(jugadorAlLateral);
		this.getPelota().setEnRemate(false);
		
		//acerco un jugador cerca del lateral
		this.acercarJugador(juagdores);
	}

	private void acercarJugador(List<Jugador> juagdores) {
		Jugador jugadorCandidatoAPase = juagdores.get(1);
		if(this.getPelota().getX() < 162){
			jugadorCandidatoAPase.setX(this.getPelota().getX() + 30);
			jugadorCandidatoAPase.setY(this.getPelota().getY() - 45);
		}else{
			jugadorCandidatoAPase.setX(this.getPelota().getX() - 30);
			jugadorCandidatoAPase.setY(this.getPelota().getY() - 45);
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
