package soccer;

import soccer.estados.EstadoArqueroCpu;
import soccer.estados.EstadoArqueroSaqueDeArcoCPU;

public class ArqueroVisitante extends JugadorVisitante {

	public ArqueroVisitante(String imagePath, double vel, double x, double y) {
		
		super(imagePath, vel, x, y);
		
	}
	
	@Override
	public void setEstadoNoSeleccionado() {
		this.setEstado(new EstadoArqueroCpu(this));
	}
	
	@Override
	public void setEstadoArqueroSaqueDeArco() {
		this.setEstado(new EstadoArqueroSaqueDeArcoCPU(this));
	}

}
