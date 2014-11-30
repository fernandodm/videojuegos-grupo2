package soccer.estados;

import soccer.ArqueroLocal;
import soccer.Direccion;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.events.constants.Key;

public class EstadoArqueroSaqueDeArco extends EstadoJugador {

	int seMovioUp = 1;
	int seMovioDown = 1;
	boolean seMovio = false;
	
	public EstadoArqueroSaqueDeArco(ArqueroLocal jugador) {
		this.setJugador(jugador);
	}

	@Override
	public void update(DeltaState deltaState) {
		
		this.ejecutar(deltaState);

	}

	private void ejecutar(DeltaState deltaState) {
		int direccion = obtenerDireccion(deltaState);
		//por si no se apreto ninguna tecla
		if((direccion == 0 && !seMovio))
			this.getJugador().getScene().getPelota().setUltimaDireccion(Direccion.UP);
		
		switch (direccion) {
		case Direccion.UPRIGHT:
			
			if(seMovioUp == 1){
				this.getJugador().setAppearance(this.getJugador().getImages().get(Direccion.UP).get(3).rotate(0.6));
				this.getJugador().setX(this.getJugador().getX() - 21);
				this.getJugador().moverLabel(this.getJugador().getX() - 21, this.getJugador().getY());
				this.getJugador().getScene().getPelota().setUltimaDireccion(Direccion.UPRIGHT);
				seMovioUp--;
				seMovioDown++;
			}
			if(seMovioUp == 2){
				this.getJugador().setAppearance(this.getJugador().getImages().get(Direccion.UP).get(3));
				this.getJugador().setY(this.getJugador().getY() + 5);
				this.getJugador().setX(this.getJugador().getX() - 21);
				this.getJugador().moverLabel(this.getJugador().getX() - 21, this.getJugador().getY());
				this.getJugador().getScene().getPelota().setUltimaDireccion(Direccion.UP);
				seMovioUp--;
				seMovioDown++;
			}
			seMovio = true;
			break;
		case Direccion.UPLEFT:

			if(seMovioDown == 1){
				this.getJugador().setAppearance(this.getJugador().getImages().get(Direccion.UP).get(3).rotate(-0.6));
				this.getJugador().setY(this.getJugador().getY() - 5);
				this.getJugador().setX(this.getJugador().getX() + 21);
				this.getJugador().moverLabel(this.getJugador().getX() + 28, this.getJugador().getY());
				this.getJugador().getScene().getPelota().setUltimaDireccion(Direccion.UPLEFT);
				seMovioUp++;
				seMovioDown--;
			}
			if(seMovioDown == 2){
				this.getJugador().setAppearance(this.getJugador().getImages().get(Direccion.UP).get(3));
				this.getJugador().setX(this.getJugador().getX() + 21);
				this.getJugador().moverLabel(this.getJugador().getX() + 28, this.getJugador().getY());
				this.getJugador().getScene().getPelota().setUltimaDireccion(Direccion.UP);
				seMovioUp++;
				seMovioDown--;
			}
			seMovio = true;
			break;
		}
		
	}

	private int obtenerDireccion(DeltaState deltaState) {
		if (deltaState.isKeyPressed(Key.A)) {
			return Direccion.UPRIGHT;
		} 
			
		if (deltaState.isKeyPressed(Key.D)) {
			return Direccion.UPLEFT;
		}
	
		return 0;
	}

}
