package soccer.estados;

import java.util.Random;

import soccer.Direccion;
import soccer.Jugador;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.events.constants.Key;

public class EstadoJugadorEnLateralCPU extends EstadoJugador {

	int time = 0;
	
	
	
	public EstadoJugadorEnLateralCPU(Jugador jugador) {
		this.setJugador(jugador);
	}

	@Override
	public void update(DeltaState deltaState) {
		
		if(this.getJugador().getScene().getPelota().getX() < 162){
			this.ejecutar(Direccion.RIGHT, Direccion.DOWNRIGHT, Direccion.UPRIGHT, 0, 0.6, deltaState);
		}else{		
			this.ejecutar(Direccion.LEFT, Direccion.DOWNLEFT, Direccion.UPLEFT, 3, -0.6, deltaState);
		}

	}

	private void ejecutar(int dir, int downright, int upright, int n, double radio, DeltaState deltaState) {
		if(time == 200){
		Random rnd = new Random();
		int direccion = rnd.nextInt(21);
				
		switch (direccion) {
		case 19:
			this.getJugador().setAppearance(this.getJugador().getImages().get(dir).get(n));
			this.getJugador().getScene().getPelota().setDireccionRemate(dir);
			break;
		case 20:
			this.getJugador().setAppearance(this.getJugador().getImages().get(Direccion.DOWN).get(3).rotate(-radio));
			this.getJugador().setY(this.getJugador().getY() - 20);
			this.getJugador().getScene().getPelota().setDireccionRemate(downright);	
			break;
		default:
			this.getJugador().setAppearance(this.getJugador().getImages().get(Direccion.UP).get(3).rotate(radio));
			this.getJugador().setY(this.getJugador().getY() + 20);
			this.getJugador().getScene().getPelota().setDireccionRemate(upright);		
		}
		this.getJugador().setEstadoNoSeleccionado(this.getJugador());
		this.getJugador().getScene().getPelota().setEnRemate(true);
		time = 0;
		}
		time++;
	}
}
