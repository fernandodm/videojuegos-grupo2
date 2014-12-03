package soccer;

import soccer.estados.EstadoArqueroSaqueDeArco;
import soccer.estados.EstadoJugadorEnCorner;
import soccer.estados.EstadoJugadorEnLateral;
import soccer.estados.EstadoJugadorNoSeleccionado;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.appearances.Sprite;
import com.uqbar.vainilla.events.constants.Key;

public class Cancha extends GameComponent<SoccerScene> {

	public Cancha(String cancha, double x, double y) {
		super(Sprite.fromImage(cancha), x, y);
	}

	@Override
	public void update(DeltaState deltaState) {
		
		Jugador JugadorActual = this.getScene().getPelota().getJugador();
		boolean puedeCambiar = true;
		
		if(JugadorActual != null){
			puedeCambiar = !(JugadorActual.getEstado() instanceof EstadoJugadorEnLateral)
								&& !(JugadorActual.getEstado() instanceof EstadoJugadorEnCorner)
								&& !(JugadorActual.getEstado() instanceof EstadoArqueroSaqueDeArco);
		}
		
		if (deltaState.isKeyPressed(Key.C) &&  puedeCambiar) {
			/* Busco al jugador selecionado para deseleccionarlo */

			for (Jugador jugador : super.getScene().getEquipoLocal().getJugadores()) {
				if (jugador.isEstaSeleccionado()) {
					jugador.setEstaSeleccionado(false);
					jugador.setEstado(new EstadoJugadorNoSeleccionado(jugador));
					jugador.setAppearance(jugador.images.get(1).get(3));
					break;
				}
			}
			/*
			 * Ahora selecciono al jugador mas cercano cuando este la pelota
			 * habra que hacerlo con ella
			 */
			seleccionarJugadorMasCercano(this.getScene().getPelota());
		}

	}

	public void seleccionarJugadorMasCercano(Pelota seleccionado) {
		Jugador jugadorCerca = null;
		double distancia = 100000; // es la maxima distancia entre dos jugadores
		for (Jugador jugador : super.getScene().getEquipoLocal().getJugadores()) {
			/*
			 * Calculo la distancia entre dos jugadores (distancia entre dos
			 * puntos)
			 */
			double distanciaActual = Math.sqrt(Math.pow(
					(jugador.getX() - seleccionado.getX()), 2)
					+ Math.pow((jugador.getY() - seleccionado.getY()), 2));

			if (distanciaActual <= distancia) {
				distancia = distanciaActual;
				jugadorCerca = jugador;
			}

		}

		/* Selecciono al jugador mas cercano */
		jugadorCerca.setEstadoSeleccionado();
		jugadorCerca.setEstaSeleccionado(true);
		jugadorCerca.moverLabel(jugadorCerca.getX() + 7, jugadorCerca.getY() + 28);
	}

}
