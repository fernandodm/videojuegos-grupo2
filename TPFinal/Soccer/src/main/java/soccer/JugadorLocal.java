package soccer;

import java.util.LinkedList;
import java.util.List;

import soccer.estados.EstadoJugadorEnLateral;
import soccer.estados.EstadoJugadorNoSeleccionado;
import soccer.estados.EstadoJugadorSeleccionado;

import com.uqbar.vainilla.appearances.Sprite;

public class JugadorLocal extends Jugador {
	
	private LabelSeleccionado labelSeleccionado;

	public JugadorLocal(String imagePath, double vel, double x, double y,
			LabelSeleccionado label) {

		super(Sprite.fromImage(imagePath).crop(0, 0, 32, 25), imagePath,
				vel, x, y);

		agregarSprite(Direccion.UP, 1, 8, 32, 0);
		agregarSprite(Direccion.DOWN, 13, 19, 32, 32);
		agregarSprite(Direccion.LEFT, 9, 16, 32, 64);
		agregarSprite(Direccion.RIGHT, 16, 19, 32, 0);
		
		this.labelSeleccionado = label;
		this.posicion=new Vector(posicion.getX(),posicion.getY()-200);

	}

	public void agregarSprite(int direccion, int principio, int fin, int x,
			int y) {

		List<Sprite> imagesCorriendo = new LinkedList<Sprite>();
		for (int i = principio; i < fin; i++) {
			imagesCorriendo.add(this.getImage().crop(x * i, y, 32, 25));
		}
		// este if esta xq el sprite para correr a la derecha
		// sigue en la segunda fila
		if (direccion == Direccion.RIGHT) {
			for (int i = 1; i < 4; i++) {
				imagesCorriendo.add(this.getImage().crop(32 * i, 32, 32, 25));
			}
		}

		images.put(direccion, imagesCorriendo);
	}

	@Override
	public Equipo equipoContrario() {

		return this.obtenerEquipoVisitante();
	}

	@Override
	public boolean isLocal() {
		return true;
	}

	@Override
	public void setEstadoSeleccionado() {
		this.setEstado(new EstadoJugadorSeleccionado(this));

	}

	@Override
	public void setEstadoNoSeleccionado() {
		this.setEstado(new EstadoJugadorNoSeleccionado(this));
	}
	
	public LabelSeleccionado getLabelSeleccionado() {
		return labelSeleccionado;
	}

	public void setLabelSeleccionado(LabelSeleccionado labelSeleccionado) {
		this.labelSeleccionado = labelSeleccionado;
	}

	@Override
	public void moverLabel(double x, double y) {
		this.labelSeleccionado.setX(x);
		this.labelSeleccionado.setY(y);
		
	}

	@Override
	public void setEstadoAlLateral() {
		this.setEstado(new EstadoJugadorEnLateral(this));
		
	}

}
