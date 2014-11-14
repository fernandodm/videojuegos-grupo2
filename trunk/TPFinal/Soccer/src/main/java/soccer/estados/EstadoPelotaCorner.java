package soccer.estados;

import soccer.Jugador;
import soccer.Pelota;

import com.uqbar.vainilla.DeltaState;

public class EstadoPelotaCorner extends EstadoPelota {

	public EstadoPelotaCorner(Pelota pelota) {
		this.setPelota(pelota);
	}

	@Override
	public void update(DeltaState deltaState) {
		for(Jugador jugador : this.getPelota().getJugador().equipoContrario().getJugadores()){
			if(jugador.isEstaSeleccionado()){
				jugador.setEstaSeleccionado(false);
				jugador.setEstado(new EstadoJugadorNoSeleccionado(jugador));
//				jugador.setAppearance(jugador.images.get(1).get(3));
				break;
			}
		}
		if(this.getPelota().getY() < 50){
			
		}else{
			
		}

	}

	@Override
	public void cambiar(EstadoPelotaEnJuego estadoPelotaEnJuego) {
		// TODO Auto-generated method stub

	}
	
	private void pocisionarJugadorCorner(Jugador jugador) {
		if(this.getPelota().getX() < 640){
			this.getPelota().setX(162);
			jugador.setX(138);
			jugador.setY(this.getPelota().getY()-3);
			jugador.getLabelSeleccionado().setX(jugador.getX() - 10);
			jugador.getLabelSeleccionado().setY(jugador.getY() + 6);
		}else{
			jugador.setX(1090+10);
			jugador.setY(this.getPelota().getY()-3);
			jugador.getLabelSeleccionado().setX(jugador.getX() + 25);
			jugador.getLabelSeleccionado().setY(jugador.getY() + 10);
		}
		
	}

}
