package soccer;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.appearances.Appearance;
import com.uqbar.vainilla.appearances.SimpleAppearance;
import com.uqbar.vainilla.appearances.Sprite;
import com.uqbar.vainilla.events.constants.Key;

public class Cancha extends GameComponent<SoccerScene>{
	
//	private Appearance image;
	private Pelota pelota;
	
	public Cancha(String cancha, double x, double y){
		super(Sprite.fromImage(cancha),x,y);
		
//		thithis.getScene().getCancha().getAppearance();
//		this.pelota = this.getScene().getPelota();

	}
	@Override
	public void update(DeltaState deltaState) {
		Jugador jug = null;
		if(deltaState.isKeyPressed(Key.C)){
			/*Busco al jugador selecionado para deseleccionarlo*/
			for(Jugador jugador: super.getScene().getJugadores()){
				if(jugador.isEstaSeleccionado()){
					jugador.setEstaSeleccionado(false);
					jugador.setAppearance(jugador.images.get(1).get(3));
					jug = jugador;
					break;
				}
			}
			/*Ahora selecciono al jugador mas cercano
			 * cuando este la pelota habra que hacerlo con ella*/
			seleccionarJugadorMasCercano(jug);
		}
//		((SimpleAppearance<Sprite>) this.image).setY(this.getY() + 2);
//		this.setAppearance(this.getAppearance());		
	}
	public void seleccionarJugadorMasCercano(Jugador seleccionado) {
		Jugador jugadorCerca = null;
		double distancia = 100000; //es la maxima distancia entre dos jugadores
		for(Jugador jugador: super.getScene().getJugadores()){
			if(jugador != seleccionado){
				/*Calculo la distancia entre dos jugadores (distancia entre dos puntos)*/
				double distanciaActual = Math.sqrt(Math.pow((jugador.getX() - seleccionado.getX()),2) 
						 			   + Math.pow((jugador.getY() - seleccionado.getY()), 2));
				if(distanciaActual <= distancia){
					distancia  = distanciaActual;
					jugadorCerca = jugador;
				}
			}
			
		}
		
		/*Selecciono al jugador mas cercano*/
		jugadorCerca.setEstaSeleccionado(true);
		jugadorCerca.getLabelSeleccionado().setX(jugadorCerca.getX() + 7);
		jugadorCerca.getLabelSeleccionado().setY(jugadorCerca.getY() + 28);
		
	}
	
	
}
	
