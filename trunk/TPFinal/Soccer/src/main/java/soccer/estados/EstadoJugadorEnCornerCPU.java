package soccer.estados;

import java.util.Random;

import soccer.Direccion;
import soccer.Jugador;

import com.uqbar.vainilla.DeltaState;

public class EstadoJugadorEnCornerCPU extends EstadoJugador {
	
	int time = 0;
	
	public EstadoJugadorEnCornerCPU(Jugador jugador) {
		this.setJugador(jugador);
	}

	@Override
	public void update(DeltaState deltaState) {
		
		if(this.getJugador().getScene().getPelota().getY() < 60){
			if(this.getJugador().getScene().getPelota().getX() < 640){
				this.ejecutarEsquinaSuperiorIzquierda(deltaState);
			}else{
				this.ejecutarEsquinaSuperiorDerecha(deltaState);
			}
		}else{
			if(this.getJugador().getScene().getPelota().getX() < 640){
				this.ejecutarEsquinaInferiorIzquierda(deltaState);
			}else{
				this.ejecutarEsquinaInferiorDerecha(deltaState);
			}
		}
	}
		
	private void ejecutarEsquinaInferiorDerecha(DeltaState deltaState) {
		if(time == 200){
			Random rnd = new Random();
			int direccion = rnd.nextInt(3);
			
			switch (direccion) {
			case 2:
				this.getJugador().setAppearance(this.getJugador().getImages().get(Direccion.LEFT).get(3).rotate(0.3));
				this.getJugador().setY(this.getJugador().getY() - 21);
				this.getJugador().setX(this.getJugador().getX() + 22);
				this.getJugador().getScene().getPelota().setDireccionRemate(Direccion.UPLEFT);
				break;
				
			default:
				this.getJugador().setAppearance(this.getJugador().getImages().get(Direccion.UP).get(1));
				this.getJugador().setY(this.getJugador().getY() + 21);
				this.getJugador().setX(this.getJugador().getX() - 22);
				this.getJugador().getScene().getPelota().setDireccionRemate(Direccion.UP);
				break;
			}
			this.getJugador().getScene().getPelota().setEnRemate(true);
			this.getJugador().getScene().getPelota().setRemateEnCorner(true);
			time = 0;
		}
		time++;
	}

	
	public void ejecutarEsquinaInferiorIzquierda(DeltaState deltaState) {
		if(time == 200){
			Random rnd = new Random();
			int direccion = rnd.nextInt(3);
			
			switch (2) {
			case 2:
				this.getJugador().setAppearance(this.getJugador().getImages().get(Direccion.RIGHT).get(0).rotate(0));
				this.getJugador().setY(this.getJugador().getY() - 22);
				this.getJugador().setX(this.getJugador().getX() - 20);
				this.getJugador().getScene().getPelota().setDireccionRemate(Direccion.UPRIGHT);
				break;
				
			default:
				this.getJugador().setAppearance(this.getJugador().getImages().get(Direccion.UP).get(0));
				this.getJugador().setY(this.getJugador().getY() + 22);
				this.getJugador().setX(this.getJugador().getX() + 20);
				this.getJugador().getScene().getPelota().setDireccionRemate(Direccion.UP);
				break;
			}
			this.getJugador().getScene().getPelota().setEnRemate(true);
			this.getJugador().getScene().getPelota().setRemateEnCorner(true);
			time = 0;
		}
		time++;
	}
	
	
	public void ejecutarEsquinaSuperiorDerecha(DeltaState deltaState){
		if(time == 200){
			Random rnd = new Random();
			int direccion = rnd.nextInt(3);
			
			switch (direccion) {
			case 2:
	
				this.getJugador().setAppearance(this.getJugador().getImages().get(Direccion.LEFT).get(3).rotate(-0.3));
				this.getJugador().setY(this.getJugador().getY() + 25);
				this.getJugador().setX(this.getJugador().getX() + 20);
				this.getJugador().getScene().getPelota().setDireccionRemate(Direccion.DOWNLEFT);
				break;
			default:
				this.getJugador().setAppearance(this.getJugador().getImages().get(Direccion.DOWN).get(3));
				this.getJugador().setY(this.getJugador().getY() - 25);
				this.getJugador().setX(this.getJugador().getX() - 20);
				this.getJugador().getScene().getPelota().setDireccionRemate(Direccion.DOWN);
				break;
			}
			this.getJugador().getScene().getPelota().setEnRemate(true);
			this.getJugador().getScene().getPelota().setRemateEnCorner(true);
			time = 0;
		}
		time++;
	}
	
	
	public void ejecutarEsquinaSuperiorIzquierda(DeltaState deltaState){
		if(time == 200){
			Random rnd = new Random();
			int direccion = rnd.nextInt(3);
			
			switch (direccion) {
			case 2:
	
				this.getJugador().setAppearance(this.getJugador().getImages().get(Direccion.RIGHT).get(0).rotate(0.3));
				this.getJugador().setY(this.getJugador().getY() + 22);
				this.getJugador().setX(this.getJugador().getX() - 20);
				this.getJugador().getScene().getPelota().setDireccionRemate(Direccion.DOWNRIGHT);
				break;
			default:
				this.getJugador().setAppearance(this.getJugador().getImages().get(Direccion.DOWN).get(3));
				this.getJugador().setY(this.getJugador().getY() - 22);
				this.getJugador().setX(this.getJugador().getX() + 20);
				this.getJugador().getScene().getPelota().setDireccionRemate(Direccion.DOWN);
				break;
			}
			this.getJugador().getScene().getPelota().setEnRemate(true);
			this.getJugador().getScene().getPelota().setRemateEnCorner(true);
			time = 0;
		}
		time++;
	}
}
