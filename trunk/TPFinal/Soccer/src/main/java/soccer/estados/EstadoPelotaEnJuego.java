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
		boolean gol = !(this.getPelota().getX()>550 && this.getPelota().getX()<700 );
		if((this.getPelota().getY() < 50 && gol) 
		|| this.getPelota().getY() > 528 && gol){
						
			if(fueCorner()){
				this.getPelota().setEstadoPelota(new EstadoPelotaCorner(this.getPelota()));
				seleccionarJugadorAlCorner();
			}else{
				saqueDeArco();
			}
		}
	}
	

	private void saqueDeArco() {
		
		Jugador jugador = this.getPelota().getJugador();
		jugador.setFlag(false);
		jugador.setEstaSeleccionado(false);
		jugador.setEstado(new EstadoJugadorNoSeleccionado(jugador));

		
		List<Jugador> juagdores = jugador.equipoContrario().getJugadores();
		Jugador arquero = juagdores.get(7);
		arquero.setFlag(true);
		arquero.setEstaSeleccionado(true);
		this.getPelota().setJugador(arquero);
		arquero.setEstado(new EstadoJugadorSeleccionado(arquero));
		arquero.setAppearance(arquero.getImages().get(Direccion.UP).get(3));
		this.posicionarSaqueDeArco(arquero);
		this.getPelota().setEnRemate(false);
		
	}

	private void posicionarSaqueDeArco(Jugador arquero) {
		if(this.getPelota().getY() < 50){
			arquero.setX(550);
			arquero.setY(80);
			this.getPelota().setY(105);
			this.getPelota().setX(550);
			arquero.getLabelSeleccionado().setX(arquero.getX() - 10);
			arquero.getLabelSeleccionado().setY(arquero.getY() + 6);
		}else{
			arquero.setX(550);
			arquero.setY(490);
			this.getPelota().setY(470);
			this.getPelota().setX(550);
			arquero.getLabelSeleccionado().setX(arquero.getX() + 25);
			arquero.getLabelSeleccionado().setY(arquero.getY() + 10);
		}
		
	}

	private boolean fueCorner() {
		Jugador jugador = this.getPelota().getJugador();
		if(this.getPelota().getY() < 50){
			if(jugador.isLocal()){
				return false;
			}else{
				return true;
			}
		}else{
			if(jugador.isLocal()){
				return true;
			}else{
				return false;
			}
		}
	}

	private void seleccionarJugadorAlCorner() {
		
		Jugador jugador = this.getPelota().getJugador();
		jugador.setFlag(false);
		jugador.setEstaSeleccionado(false);
		jugador.setEstado(new EstadoJugadorNoSeleccionado(jugador));
//		/*Si el jugador esta muy cerca del lateral y se va con la pelota
//		 * lo corro para abajo al jugador q la tiro para q no colisionen 
//		 * dos jugadores con la pelota*/
//		if(jugador.getX() < 202 || jugador.getX() > 1050 && !jugador.isEstaSeleccionado())
//			jugador.setY(jugador.getY() + 80);
		
		List<Jugador> juagdores = jugador.equipoContrario().getJugadores();
		Jugador jugadorAlCorner = juagdores.get(5);
		jugadorAlCorner.setFlag(true);
		jugadorAlCorner.setEstaSeleccionado(true);
		this.getPelota().setJugador(jugadorAlCorner);
		jugadorAlCorner.setEstado(new EstadoJugadorEnCorner(jugadorAlCorner));
		this.posicionarJugadorCorner(jugadorAlCorner);
		this.getPelota().setEnRemate(false);
		
		//acerco un jugador cerca del lateral
//		this.acercarJugador(juagdores);
		
	}

	private void posicionarJugadorCorner(Jugador jugador) {
		
		if(this.getPelota().getY() < 50){
			jugador.setY(47);
			this.getPelota().setY(58);
			posicionarIzquierdaODerecha(jugador);	
		}else{
			jugador.setY(518);
			this.getPelota().setY(515);
			posicionarIzquierdaODerecha(jugador);	
			jugador.getLabelSeleccionado().setX(jugador.getX() + 25);
			jugador.getLabelSeleccionado().setY(jugador.getY() + 10);
		}
		
	}

	public void posicionarIzquierdaODerecha(Jugador jugador) {
		if(this.getPelota().getX() < 640){
			jugador.setAppearance(jugador.getImages().get(Direccion.RIGHT).get(0).rotate(0.3));
			jugador.setX(145);
			this.getPelota().setX(172);
			jugador.getLabelSeleccionado().setX(jugador.getX() - 10);
			jugador.getLabelSeleccionado().setY(jugador.getY() + 6);
		}else{
			jugador.setAppearance(jugador.getImages().get(Direccion.LEFT).get(3).rotate(0.3));
			jugador.setX(1100);
			this.getPelota().setX(1080);
			jugador.getLabelSeleccionado().setX(jugador.getX() + 27);
			jugador.getLabelSeleccionado().setY(jugador.getY() + 6);
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
		/*Si el jugador esta muy cerca del lateral y se va con la pelota
		 * lo corro para abajo al jugador q la tiro para q no colisionen 
		 * dos jugadores con la pelota*/
		if(jugador.getX() < 202 || jugador.getX() > 1050 && !jugador.isEstaSeleccionado())
			jugador.setY(jugador.getY() + 80);
		
		List<Jugador> juagdores = jugador.equipoContrario().getJugadores();
		Jugador jugadorAlLateral = juagdores.get(4);
		
		jugadorAlLateral.setFlag(true);
		jugadorAlLateral.setEstaSeleccionado(true);
		this.getPelota().setJugador(jugadorAlLateral);
		jugadorAlLateral.setEstado(new EstadoJugadorEnLateral(jugadorAlLateral));
		jugadorAlLateral.setAppearance(jugadorAlLateral.getImages().get(Direccion.RIGHT).get(0));
		this.posicionarJugadorLateral(jugadorAlLateral);
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

	private void posicionarJugadorLateral(Jugador jugador) {
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
