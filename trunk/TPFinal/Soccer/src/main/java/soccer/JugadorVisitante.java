package soccer;

import java.util.LinkedList;
import java.util.List;

import soccer.estados.EstadoJugadorEnCornerCPU;
import soccer.estados.EstadoJugadorEnLateralCPU;
import soccer.estados.EstadoJugadorNoSeleccionadoCPU;
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
		this.posicion=new Vector(posicion.getX(),posicion.getY()+200);
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
	public void setEstadoSeleccionado() {
		this.setEstado(new EstadoJugadorSeleccionadoCPU(this));
		
	}

	@Override
	public void setEstadoNoSeleccionado() {
		this.setEstado(new EstadoJugadorNoSeleccionadoCPU(this));
		
	}

	@Override
	public void moverLabel(double x, double y) {}

	@Override
	public void setEstadoAlLateral() {
		this.setEstado(new EstadoJugadorEnLateralCPU(this));
	}

	@Override
	public void setEstadoAlCorner() {
		this.setEstado(new EstadoJugadorEnCornerCPU(this));
	}

	@Override
	public void setEstadoArqueroSaqueDeArco() {}

}
