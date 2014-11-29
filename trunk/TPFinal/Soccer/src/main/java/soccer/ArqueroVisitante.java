package soccer;

import soccer.estados.EstadoArqueroCpu;

public class ArqueroVisitante extends JugadorVisitante {

	public ArqueroVisitante(String imagePath, double vel, double x, double y) {
		
		super(imagePath, vel, x, y);
		
	}
	
	
	@Override
	public void setEstadoNoSeleccionado() {
		this.setEstado(new EstadoArqueroCpu(this));
		
	}

}
