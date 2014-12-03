package soccer;

import soccer.estados.EstadoArqueroLocal;
import soccer.estados.EstadoArqueroSaqueDeArco;

public class ArqueroLocal extends JugadorLocal {

	
	public ArqueroLocal(String imagePath, double vel, double x, double y, LabelSeleccionado label) {
		
		super(imagePath, vel, x, y, label);
		
	}
	
	@Override
	public void setEstadoNoSeleccionado() {
		this.setEstado(new EstadoArqueroLocal(this));
	}
	
	@Override
	public void setEstadoArqueroSaqueDeArco() {
		this.setEstado(new EstadoArqueroSaqueDeArco(this));
	}
}
