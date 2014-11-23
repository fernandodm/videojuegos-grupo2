package soccer.estados;

import soccer.Direccion;
import soccer.Jugador;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.events.constants.Key;

public class EstadoJugadorEnCorner extends EstadoJugador {
	//variables para q se mueva en los corners
	int seMovioUp = 1;
	int seMovioDown = 0;
	boolean seMovio = false;
	
	public EstadoJugadorEnCorner(Jugador jugador) {
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
		int direccion = obtenerDireccion(deltaState);
		//por si no se apreto ninguna tecla
		if((direccion == 0 && !seMovio))
			this.getJugador().getScene().getPelota().setUltimaDireccion(Direccion.UPLEFT);
		
		switch (direccion) {
		case Direccion.UP:
			
			if(seMovioUp == 1){
				this.getJugador().setAppearance(this.getJugador().getImages().get(Direccion.UP).get(1));
				this.getJugador().setY(this.getJugador().getY() + 21);
				this.getJugador().setX(this.getJugador().getX() - 22);
				this.getJugador().moverLabel(this.getJugador().getX() - 22, this.getJugador().getY() + 21);
				this.getJugador().getScene().getPelota().setUltimaDireccion(Direccion.UP);
				seMovioUp--;
				seMovioDown++;
			}
			seMovio = true;
			break;
		case Direccion.DOWN:

			if(seMovioDown == 1){
				this.getJugador().setAppearance(this.getJugador().getImages().get(Direccion.LEFT).get(3).rotate(0.3));
				this.getJugador().setY(this.getJugador().getY() - 21);
				this.getJugador().setX(this.getJugador().getX() + 22);
				this.getJugador().moverLabel(this.getJugador().getX() + 22, this.getJugador().getY() - 21);
				this.getJugador().getScene().getPelota().setUltimaDireccion(Direccion.UPLEFT);
				seMovioUp++;
				seMovioDown--;
			}
			seMovio = true;
			break;
		}
		
	}
	
	
	
	
		
	
	
	
	public void ejecutarEsquinaInferiorIzquierda(DeltaState deltaState) {
		int direccion = obtenerDireccion(deltaState);
		//por si no se apreto ninguna tecla
		if((direccion == 0 && !seMovio))
			this.getJugador().getScene().getPelota().setUltimaDireccion(Direccion.UPRIGHT);
		
		switch (direccion) {
		case Direccion.UP:
			
			if(seMovioUp == 1){
				this.getJugador().setAppearance(this.getJugador().getImages().get(Direccion.UP).get(0));
				this.getJugador().setY(this.getJugador().getY() + 22);
				this.getJugador().setX(this.getJugador().getX() + 20);
				this.getJugador().moverLabel(this.getJugador().getX() + 20, this.getJugador().getY() + 22);
				this.getJugador().getScene().getPelota().setUltimaDireccion(Direccion.UP);
				seMovioUp--;
				seMovioDown++;
			}
			seMovio = true;
			break;
		case Direccion.DOWN:

			if(seMovioDown == 1){
				this.getJugador().setAppearance(this.getJugador().getImages().get(Direccion.RIGHT).get(0).rotate(0));
				this.getJugador().setY(this.getJugador().getY() - 22);
				this.getJugador().setX(this.getJugador().getX() - 20);
				this.getJugador().moverLabel(this.getJugador().getX() - 20, this.getJugador().getY() - 22);
				this.getJugador().getScene().getPelota().setUltimaDireccion(Direccion.UPRIGHT);
				seMovioUp++;
				seMovioDown--;
			}
			seMovio = true;
			break;
		}
	}
	
	
	
	
	
	public void ejecutarEsquinaSuperiorDerecha(DeltaState deltaState){
		
		int direccion = obtenerDireccion(deltaState);
		//por si no se apreto ninguna tecla
		if((direccion == 0 && !seMovio))
			this.getJugador().getScene().getPelota().setUltimaDireccion(Direccion.DOWNLEFT);
		
		switch (direccion) {
		case Direccion.UP:
			
			if(seMovioUp == 1){
				this.getJugador().setAppearance(this.getJugador().getImages().get(Direccion.DOWN).get(3));
				this.getJugador().setY(this.getJugador().getY() - 25);
				this.getJugador().setX(this.getJugador().getX() - 20);
				this.getJugador().moverLabel(this.getJugador().getX() - 20, this.getJugador().getY() - 25);
				this.getJugador().getScene().getPelota().setUltimaDireccion(Direccion.DOWN);
				seMovioUp--;
				seMovioDown++;
			}
			seMovio = true;
			break;
		case Direccion.DOWN:

			if(seMovioDown == 1){
				this.getJugador().setAppearance(this.getJugador().getImages().get(Direccion.LEFT).get(3).rotate(-0.3));
				this.getJugador().setY(this.getJugador().getY() + 25);
				this.getJugador().setX(this.getJugador().getX() + 20);
				this.getJugador().moverLabel(this.getJugador().getX() + 20, this.getJugador().getY() + 25);
				this.getJugador().getScene().getPelota().setUltimaDireccion(Direccion.DOWNLEFT);
				seMovioUp++;
				seMovioDown--;
			}
			seMovio = true;
			break;
		}
	}
	
	
	
	
	
	
	
	
	
	public void ejecutarEsquinaSuperiorIzquierda(DeltaState deltaState){
		int direccion = obtenerDireccion(deltaState);
		//por si no se apreto ninguna tecla
		if((direccion == 0 && !seMovio))
			this.getJugador().getScene().getPelota().setUltimaDireccion(Direccion.DOWNRIGHT);
		
		switch (direccion) {
		case Direccion.UP:
			
			if(seMovioUp == 1){
				this.getJugador().setAppearance(this.getJugador().getImages().get(Direccion.DOWN).get(3));
				this.getJugador().setY(this.getJugador().getY() - 22);
				this.getJugador().setX(this.getJugador().getX() + 20);
				this.getJugador().moverLabel(this.getJugador().getX() + 22, this.getJugador().getY() - 22);
				this.getJugador().getScene().getPelota().setUltimaDireccion(Direccion.DOWN);
				seMovioUp--;
				seMovioDown++;
			}
			seMovio = true;
			break;
		case Direccion.DOWN:

			if(seMovioDown == 1){
				this.getJugador().setAppearance(this.getJugador().getImages().get(Direccion.RIGHT).get(0).rotate(0.3));
				this.getJugador().setY(this.getJugador().getY() + 22);
				this.getJugador().setX(this.getJugador().getX() - 20);
				this.getJugador().moverLabel(this.getJugador().getX() - 22, this.getJugador().getY() + 22);
				this.getJugador().getScene().getPelota().setUltimaDireccion(Direccion.DOWNRIGHT);
				seMovioUp++;
				seMovioDown--;
			}
			seMovio = true;
			break;
		}
		
	}


	private int obtenerDireccion(DeltaState deltaState) {
		if (deltaState.isKeyPressed(Key.W)) {
			return Direccion.UP;
		} 
			
		if (deltaState.isKeyPressed(Key.S)) {
			return Direccion.DOWN;
		}
	
		return 0;
	}

}
