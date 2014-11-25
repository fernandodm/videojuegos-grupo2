package soccer;

import java.util.LinkedList;
import java.util.List;

import soccer.estados.EstadoJugadorEnLateral;
import soccer.estados.EstadoJugadorEnLateralCPU;
import soccer.estados.EstadoJugadorNoSeleccionadoCPU;
import soccer.estados.EstadoJugadorSeleccionado;
import soccer.estados.EstadoJugadorSeleccionadoCPU;

import com.uqbar.vainilla.appearances.Sprite;

public class JugadorVisitante extends Jugador {

	public JugadorVisitante(String imagePath, double vel, double x, double y) {
		
		super(Sprite.fromImage(imagePath).crop(12*32,192,32,25), imagePath, vel, x, y);
		
		agregarSprite(Direccion.UP,1,8,32,160);
		agregarSprite(Direccion.DOWN,13,19,32,192);
		agregarSprite(Direccion.LEFT,8,16,32,224);
		agregarSprite(Direccion.RIGHT,16,19,32,160);
		this.setEstado(new EstadoJugadorNoSeleccionadoCPU(this));
	
	}
	
	public void agregarSprite(int direccion,int principio,int fin,int x,int y){
		
		List<Sprite> imagesCorriendo = new LinkedList<Sprite>();
		for (int i = principio; i < fin; i++) {
			imagesCorriendo.add(this.getImage().crop(x*i, y, 32, 25));
		}
		//este if esta xq el sprite para correr a la derecha
		//sigue en la segunda fila
		if(direccion == Direccion.RIGHT){
			for (int i = 1; i < 4; i++) {
				imagesCorriendo.add(this.getImage().crop(32*i, 192, 32, 25));
			}
		}
			
		images.put(direccion, imagesCorriendo);
	}

	@Override
	public Equipo equipoContrario() {
		return this.obtenerEquipoLocal();
	}

	@Override
	public boolean isLocal() {
		return false;
	}

	@Override
	public void setEstadoSeleccionado(Jugador jugadorCerca) {
		this.setEstado(new EstadoJugadorSeleccionadoCPU(jugadorCerca));
		
	}

	@Override
	public void setEstadoNoSeleccionado(Jugador jugador) {
		this.setEstado(new EstadoJugadorNoSeleccionadoCPU(jugador));
		
	}

	@Override
	public void moverLabel(double x, double y) {}

	@Override
	public void setEstadoAlLateral(Jugador jugador) {
		this.setEstado(new EstadoJugadorEnLateralCPU(jugador));
	}

}
