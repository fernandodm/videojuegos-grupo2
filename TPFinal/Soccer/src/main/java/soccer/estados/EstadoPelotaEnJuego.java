package soccer.estados;

import java.util.List;

import soccer.Colision;
import soccer.Direccion;
import soccer.Jugador;
import soccer.JugadorLocal;
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
				this.ejecutarMovimiento(deltaState, jugador);
				
				if(this.getPelota().getJugador() instanceof JugadorLocal)
					this.getPelota().setEnRemate(false);
				
				jugador.flag = true;
				if(this.getPelota().getJugador() instanceof JugadorLocal)
					this.getPelota().activarRemate(deltaState);
			} else {
				jugador.flag = false;
			}
		}

		this.getPelota().moverPelotaPorRemate(deltaState);
		this.verificarCorner();
		this.verificarLateral();
	}
	
	public void ejecutarMovimiento(DeltaState deltaState, Jugador jugador) {

		if(getPelota().getJugador() instanceof JugadorLocal){
			int direccion = Direccion.obtenerDireccion(deltaState);
			switch (direccion) {
			case Direccion.UP:
				this.getPelota().setUltimaDireccion(Direccion.UP); 
				this.getPelota().setY(jugador.getY()-14);
				this.getPelota().setX(jugador.getX() + 3);
				this.getPelota().ejecutarSpritePelota();
				break;
			case Direccion.DOWN:
				this.getPelota().setUltimaDireccion(Direccion.DOWN); 
				this.getPelota().setY(jugador.getY()+31);
				this.getPelota().setX(jugador.getX());
				this.getPelota().ejecutarSpritePelota();
				break;
			case Direccion.LEFT:
				this.getPelota().setUltimaDireccion(Direccion.LEFT); 
				this.getPelota().setX(jugador.getX()-14);
				this.getPelota().setY(jugador.getY()+4);
				this.getPelota().ejecutarSpritePelota();
				break;
			case Direccion.RIGHT:
				this.getPelota().setUltimaDireccion(Direccion.RIGHT); 
				this.getPelota().setX(jugador.getX()+31);
				this.getPelota().setY(jugador.getY()+9);
				this.getPelota().ejecutarSpritePelota();
				break;
			case Direccion.UPRIGHT:
				this.getPelota().setUltimaDireccion(Direccion.UPRIGHT); 
				this.getPelota().setX(jugador.getX() + 25);
				this.getPelota().setY(jugador.getY() - 10);
				this.getPelota().ejecutarSpritePelota();
				break;
			case Direccion.UPLEFT:
				this.getPelota().setUltimaDireccion(Direccion.UPLEFT); 
				this.getPelota().setX(jugador.getX() - 8);
				this.getPelota().setY(jugador.getY()- 10);
				this.getPelota().ejecutarSpritePelota();
				break;
			case Direccion.DOWNRIGHT:
				this.getPelota().setUltimaDireccion(Direccion.DOWNRIGHT); 
				this.getPelota().setX(jugador.getX() + 30);
				this.getPelota().setY(jugador.getY() + 23);
				this.getPelota().ejecutarSpritePelota();
				break;
			case Direccion.DOWNLEFT:
				this.getPelota().setUltimaDireccion(Direccion.DOWNLEFT); 
				this.getPelota().setX(jugador.getX() - 13);
				this.getPelota().setY(jugador.getY() + 21);
				this.getPelota().ejecutarSpritePelota();
				break;
			}
		}
	}

	private void verificarCorner() {
		boolean gol = !(this.getPelota().getX()>550 && this.getPelota().getX()<700 );
		if((this.getPelota().getY() < 50 && gol && this.getPelota().getScene().getCancha().getY() > -7 ) 
		|| this.getPelota().getY() > 528 && gol && this.getPelota().getScene().getCancha().getY() < -930){
						
			if(this.fueCorner()){
				this.seleccionarJugadorAlCorner();
			}else{
				this.saqueDeArco();
			}
			this.frenarTiempo();
		}
	}
	
	private void saqueDeArco() {
		this.getPelota().setEnRemate(false);
		this.getPelota().setPotencia(2);
		Jugador jugador = this.getPelota().getJugador();
		
		if(jugador instanceof JugadorLocal){
			this.getPelota().setEstadoPelota(new EstadoPelotaEnLateralOSaqueArcoCPU(this.getPelota()));
			Jugador jugav = this.getPelota().getScene().getEquipoVisitante().getJugadores().get(5);
			this.acercarJugadorAlArquero(jugav, 500, 150);
		}else{
			Jugador jugal = this.getPelota().getScene().getEquipoLocal().getJugadores().get(5);
			this.acercarJugadorAlArquero(jugal, 500, 400);
			this.getPelota().setEstadoPelota(new EstadoPelotaEnLateralOSaqueArco(this.getPelota()));

		}
		
		for(Jugador jug: this.getPelota().getScene().getJugadores()){
			jug.setFlag(false);
			jug.setEstaSeleccionado(false);
			jug.setEstadoParaPelotaFueraDeJuego();
		}
		
		List<Jugador> juagdores = jugador.equipoContrario().getJugadores();
		Jugador arquero = juagdores.get(7);
		arquero.setFlag(true);
		arquero.setEstaSeleccionado(true);
		this.getPelota().setJugador(arquero);
		
		arquero.setEstadoArqueroSaqueDeArco();
		setearAparienciaArquero(arquero);
		this.posicionarSaqueDeArco(arquero);
		
	}

	private void acercarJugadorAlArquero(Jugador jugador, double x, double y) {
		
		jugador.setY(y);
		jugador.setX(x);
			
	}

	public void setearAparienciaArquero(Jugador arquero) {
		if(arquero instanceof JugadorLocal){
			arquero.setAppearance(arquero.getImages().get(Direccion.UP).get(3));
		}else{
			arquero.setAppearance(arquero.getImages().get(Direccion.DOWN).get(3));
		}
		
	}

	private void posicionarSaqueDeArco(Jugador arquero) {
		if(this.getPelota().getY() < 50){
			arquero.setX(550);
			arquero.setY(80);
			this.getPelota().setY(105);
			this.getPelota().setX(550);
			arquero.moverLabel(arquero.getX() - 10, arquero.getY() + 6);
		}else{
			arquero.setX(545);
			arquero.setY(485);
			this.getPelota().setY(470);
			this.getPelota().setX(550);
			arquero.moverLabel(arquero.getX() + 25, arquero.getY() + 10);
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
		this.getPelota().setEnRemate(false);
		this.getPelota().setPotencia(2);
		Jugador jugador = this.getPelota().getJugador();

		if(jugador instanceof JugadorLocal){
			this.getPelota().setEstadoPelota(new EstadoPelotaEnCornerCPU(this.getPelota()));
		}else{
			this.getPelota().setEstadoPelota(new EstadoPelotaCorner(this.getPelota()));
			for(Jugador jug: this.getPelota().getScene().getJugadores()){
				jug.setFlag(false);
				jug.setEstaSeleccionado(false);
				jug.setEstadoParaPelotaFueraDeJuego();
			}
		}
	
		
		List<Jugador> jugadores = jugador.equipoContrario().getJugadores();
		Jugador jugadorAlCorner = jugadores.get(5);
		
		Jugador jugadorCercanoAlCorner = jugadores.get(1);
		
		jugadorAlCorner.setFlag(true);
		jugadorAlCorner.setEstaSeleccionado(true);
		this.getPelota().setJugador(jugadorAlCorner);
		jugadorAlCorner.setEstadoAlCorner();
		this.posicionarJugadorCorner(jugadorAlCorner, jugadorCercanoAlCorner);
		
	}

	private void posicionarJugadorCorner(Jugador jugador, Jugador jugadorCercanoAlCorner) {
		
		if(this.getPelota().getY() < 50){
			jugador.setY(47);
			this.getPelota().setY(58);
			posicionarIzquierdaODerecha(jugador, jugadorCercanoAlCorner, 60);	
		}else{
			jugador.setY(518);
			this.getPelota().setY(515);
			posicionarIzquierdaODerecha(jugador, jugadorCercanoAlCorner, -60);	
			jugador.moverLabel(jugador.getX() + 25, jugador.getY() + 10);
		}
		
	}

	public void posicionarIzquierdaODerecha(Jugador jugador, Jugador jugadorCercanoAlCorner, int y) {
		
		
		if(this.getPelota().getX() < 640){
			jugador.setAppearance(jugador.getImages().get(Direccion.RIGHT).get(0).rotate(0.3));
			jugador.setX(145);
			this.getPelota().setX(174);
			jugador.moverLabel(jugador.getX() - 10, jugador.getY() + 6);
			
			jugadorCercanoAlCorner.setAppearance(jugador.getImages().get(Direccion.RIGHT).get(0));
			
			jugadorCercanoAlCorner.setX(this.getPelota().getX());
			jugadorCercanoAlCorner.setY(this.getPelota().getY() + y);
		}else{
			jugador.setAppearance(jugador.getImages().get(Direccion.LEFT).get(3).rotate(0.3));
			jugador.setX(1100);
			this.getPelota().setX(1080);
			jugador.moverLabel(jugador.getX() + 27, jugador.getY() + 6);
			jugadorCercanoAlCorner.setAppearance(jugador.getImages().get(Direccion.LEFT).get(2));
			
			jugadorCercanoAlCorner.setX(this.getPelota().getX() - 15);
			jugadorCercanoAlCorner.setY(this.getPelota().getY() + y);
		}
	}

	private void verificarLateral() {
		if(this.getPelota().getX() < 162 || this.getPelota().getX() > 1090){
			this.frenarTiempo();
			seleccionarJugadorAlLateral();
		}
	}

	public void seleccionarJugadorAlLateral() {
		Jugador jugador = this.getPelota().getJugador();
	
		if(jugador instanceof JugadorLocal){
			this.getPelota().setEstadoPelota(new EstadoPelotaEnLateralOSaqueArcoCPU(this.getPelota()));
		}else{
			this.getPelota().setEstadoPelota(new EstadoPelotaEnLateralOSaqueArco(this.getPelota()));
			for(Jugador jug: this.getPelota().getScene().getJugadores()){
				jug.setFlag(false);
				jug.setEstaSeleccionado(false);
				jug.setEstadoParaPelotaFueraDeJuego();
			}
		}
		/*Si el jugador esta muy cerca del lateral y se va con la pelota
		 * lo corro para abajo al jugador q la tiro para q no colisionen 
		 * dos jugadores con la pelota*/
		if(jugador.getX() < 202 || jugador.getX() > 1050)
			jugador.setY(jugador.getY() + 200);
		
		List<Jugador> juagdores = jugador.equipoContrario().getJugadores();
		Jugador jugadorAlLateral = juagdores.get(4);
		
		jugadorAlLateral.setFlag(true);
		jugadorAlLateral.setEstaSeleccionado(true);
		this.getPelota().setJugador(jugadorAlLateral);
		jugadorAlLateral.setEstadoAlLateral();
		jugadorAlLateral.setAppearance(jugadorAlLateral.getImages().get(Direccion.RIGHT).get(0));
		this.posicionarJugadorLateral(jugadorAlLateral);
		this.getPelota().setEnRemate(false);
		this.getPelota().setPotencia(2);
		//acerco un jugador cerca del lateral
		this.acercarJugadorAlLateral(juagdores);
	}
	
	private void acercarJugadorAlLateral(List<Jugador> juagdores) {
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
			jugador.moverLabel(jugador.getX() - 10, jugador.getY() + 6);
		}else{
			jugador.setX(this.getPelota().getX()+10);
			jugador.setY(this.getPelota().getY()-3);
			jugador.moverLabel(jugador.getX() + 25, jugador.getY() + 10);
		}
	}
}