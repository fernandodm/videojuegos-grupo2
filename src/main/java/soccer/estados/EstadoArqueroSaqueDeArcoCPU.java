package soccer.estados;

import java.util.Random;

import soccer.ArqueroVisitante;
import soccer.Direccion;
import soccer.Jugador;

import com.uqbar.vainilla.DeltaState;

public class EstadoArqueroSaqueDeArcoCPU extends EstadoJugador {

	int time = 0;
	boolean cambiarEstado = true;
	
	public EstadoArqueroSaqueDeArcoCPU(ArqueroVisitante jugador) {
		this.setJugador(jugador);
	}

	@Override
	public void update(DeltaState deltaState) {
		this.ejecutar(deltaState);
		if(this.cambiarEstado && time == 200){
			this.cambiarEstados();
		}
	}
	
	private void cambiarEstados() {
		for(Jugador jug: this.getJugador().getScene().getJugadores()){
			if(!jug.isEstaSeleccionado())
				jug.setEstadoNoSeleccionado();
		}
		this.cambiarEstado = false;
	}
	
	private void ejecutar(DeltaState deltaState) {
		if(time == 200){
			Random rnd = new Random();
			int direccion = rnd.nextInt(21);
			
			switch (direccion) {
				case 19:
					this.getJugador().setAppearance(this.getJugador().getImages().get(Direccion.DOWN).get(3));
					this.getJugador().getScene().getPelota().setDireccionRemate(Direccion.DOWN);
					break;
				case 20:
					this.getJugador().setAppearance(this.getJugador().getImages().get(Direccion.DOWN).get(3).rotate(-0.6));
					this.getJugador().setY(this.getJugador().getY() - 20);
					this.getJugador().getScene().getPelota().setDireccionRemate(Direccion.DOWNRIGHT);	
					break;
				default:
					this.getJugador().setAppearance(this.getJugador().getImages().get(Direccion.DOWN).get(3).rotate(0.6));
					this.getJugador().setX(this.getJugador().getX() + 20);
					this.getJugador().getScene().getPelota().setDireccionRemate(Direccion.DOWNLEFT);		
				}
			this.getJugador().setEstadoNoSeleccionado();
			this.getJugador().getScene().getPelota().setEnRemate(true);
			time = 0;
		}
		time++;
	}

}
