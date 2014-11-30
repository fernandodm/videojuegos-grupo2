package soccer.estados;

import soccer.ArqueroVisitante;
import soccer.Direccion;
import soccer.Jugador;

import com.uqbar.vainilla.DeltaState;

public class EstadoJugadorSeleccionadoCPU extends EstadoJugador {
	
	public EstadoJugadorSeleccionadoCPU(Jugador jugador) {
		this.setJugador(jugador);
	}

	@Override
	public void update(DeltaState deltaState) {
		
		if(this.getJugador() instanceof ArqueroVisitante){
			Utils.scene.getPelota().activarRemateCPU(deltaState,Utils.obtenerDireccionDeRemateVisitante());				
			this.getJugador().setEstadoArqueroSaqueDeArco();
			return;
		}
		

		if(Utils.distanciaConArcoLocal(this.getJugador().getX(), this.getJugador().getY())< 250){
				Utils.scene.getPelota().activarRemateCPU(deltaState,Utils.obtenerDireccionDeRemateVisitante());				
				this.getJugador().setEstado(new EstadoJugadorNoSeleccionadoCPU(this.getJugador()));
		}
		
		if(!Utils.tienePelotaVisitante()){
			this.getJugador().setFlag(false);
			this.getJugador().setEstaSeleccionado(false);
			this.getJugador().setEstado(new EstadoJugadorNoSeleccionadoCPU(this.getJugador()));
			return;
		}
		
		double distanciaConJugadorVisitanteMasCercano=Utils.distanciaConJugadorVisitanteMasCercano(this.getJugador().getX(), this.getJugador().getY());
		System.out.println(distanciaConJugadorVisitanteMasCercano);
		if(distanciaConJugadorVisitanteMasCercano < 50 
				&& distanciaConJugadorVisitanteMasCercano > 15 && Utils.hacerPase()){
			Utils.scene.getPelota().activarRemateCPU(deltaState,Utils.direccionJugadorVisitanteCercano(this.getJugador().getX(), this.getJugador().getY()));				
			this.getJugador().setEstado(new EstadoJugadorNoSeleccionadoCPU(this.getJugador()));
			return;
		}
		
		
		Jugador jugador = Utils.jugadorLocalMasCercano(
				this.getJugador().getX(), this.getJugador().getY());
		int direccion;
		
		if (jugador.distancia(this.getJugador()) < 85) {
			direccion = Utils.direccionEsquivar(jugador.getX(),
					jugador.getY());
		}else{
			direccion = Utils.direccionParaAtacar(jugador.getX(),
					jugador.getY());
		}
		
		switch (direccion) {
			case Direccion.DOWN:
			
				this.getJugador().getScene().getPelota().setUltimaDireccion(Direccion.DOWN); 
				this.getJugador().getScene().getPelota().setY(this.getJugador().getY()+31);
				this.getJugador().getScene().getPelota().setX(this.getJugador().getX());
				this.getJugador().getScene().getPelota().ejecutarSpritePelota();
			
				this.getJugador().down(deltaState);
				this.getJugador().ejecutarSprite(deltaState, Direccion.DOWN, 0);
				break;
			case Direccion.UP:
				this.getJugador().getScene().getPelota().setUltimaDireccion(Direccion.UP); 
				this.getJugador().getScene().getPelota().setY(this.getJugador().getY()-14);
				this.getJugador().getScene().getPelota().setX(this.getJugador().getX() + 3);
				this.getJugador().getScene().getPelota().ejecutarSpritePelota();
			
				this.getJugador().up(deltaState);
				this.getJugador().ejecutarSprite(deltaState, Direccion.UP, 0);
				break;
			case Direccion.RIGHT:
			
				this.getJugador().getScene().getPelota().setUltimaDireccion(Direccion.RIGHT); 
				this.getJugador().getScene().getPelota().setX(this.getJugador().getX()+31);
				this.getJugador().getScene().getPelota().setY(this.getJugador().getY()+9);
				this.getJugador().getScene().getPelota().ejecutarSpritePelota();
				
				this.getJugador().right(deltaState);
				this.getJugador().ejecutarSprite(deltaState, Direccion.RIGHT, 0);
				break;
			case Direccion.LEFT:
			
				this.getJugador().getScene().getPelota().setUltimaDireccion(Direccion.LEFT); 
				this.getJugador().getScene().getPelota().setX(this.getJugador().getX()-14);
				this.getJugador().getScene().getPelota().setY(this.getJugador().getY()+4);
				this.getJugador().getScene().getPelota().ejecutarSpritePelota();
			
				this.getJugador().left(deltaState);
				this.getJugador()
						.ejecutarSprite(deltaState, Direccion.LEFT, 0);
				break;
			case Direccion.UPLEFT:
			
				this.getJugador().getScene().getPelota().setUltimaDireccion(Direccion.UPLEFT); 
				this.getJugador().getScene().getPelota().setX(this.getJugador().getX() - 8);
				this.getJugador().getScene().getPelota().setY(this.getJugador().getY() - 10);
				this.getJugador().getScene().getPelota().ejecutarSpritePelota();
			
				this.getJugador().upLeft(deltaState);
				this.getJugador().ejecutarSprite(deltaState, Direccion.UP, -0.6);
				break;
			case Direccion.UPRIGHT:
			
				this.getJugador().getScene().getPelota().setUltimaDireccion(Direccion.UPRIGHT); 
				this.getJugador().getScene().getPelota().setX(this.getJugador().getX() + 25);
				this.getJugador().getScene().getPelota().setY(this.getJugador().getY() - 10);
				this.getJugador().getScene().getPelota().ejecutarSpritePelota();
			
				this.getJugador().upRight(deltaState);
				this.getJugador()
						.ejecutarSprite(deltaState, Direccion.UP, 0.6);
				break;
			case Direccion.DOWNLEFT:
			
				this.getJugador().getScene().getPelota().setUltimaDireccion(Direccion.DOWNLEFT); 
				this.getJugador().getScene().getPelota().setX(this.getJugador().getX() - 13);
				this.getJugador().getScene().getPelota().setY(this.getJugador().getY() + 21);
				this.getJugador().getScene().getPelota().ejecutarSpritePelota();
			
				this.getJugador().downLeft(deltaState);
				this.getJugador().ejecutarSprite(deltaState, Direccion.UP, -2);
				break;
			case Direccion.DOWNRIGHT:
			
				this.getJugador().getScene().getPelota().setUltimaDireccion(Direccion.DOWNRIGHT); 
				this.getJugador().getScene().getPelota().setX(this.getJugador().getX() + 30);
				this.getJugador().getScene().getPelota().setY(this.getJugador().getY() + 23);
				this.getJugador().getScene().getPelota().ejecutarSpritePelota();
			
				this.getJugador().downRight(deltaState);
				this.getJugador().ejecutarSprite(deltaState, Direccion.UP, 2);
				break;
		}
	}
}
