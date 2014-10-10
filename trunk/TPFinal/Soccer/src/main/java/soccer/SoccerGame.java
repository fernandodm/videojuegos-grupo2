package soccer;

import java.awt.Dimension;


import com.uqbar.vainilla.DesktopGameLauncher;
import com.uqbar.vainilla.Game;

public class SoccerGame extends Game {

	@Override
	protected void initializeResources() {
		// TODO Auto-generated method stub
	}

	@Override
	protected void setUpScenes(){
		
		SoccerScene scene = new SoccerScene("sonido1.wav");
				
		Cancha cancha = new Cancha("cancha.png", 0, -460);
		Arco arcoArriba = new Arco("arco.png", 0, 525, -452);
		Arco arcoAbajo = new Arco("arco.png", 3.14, 525, 953);
		Pelota pelota = new Pelota("pelota1.png", new Vector(0, -5), 10);
		
		LabelSeleccionado label = new LabelSeleccionado(608, 325);
		
		Jugador jugador = new Jugador("jugadores.png", 150, 600, 300, label);
		Jugador jugador2 = new Jugador("jugadores.png", 150, 600, 350, label);
		Jugador jugador3 = new Jugador("jugadores.png", 150, 800, 300, label);
		jugador.setEstaSeleccionado(true);
		
		scene.setCancha(cancha);
		scene.addComponent(label);
		scene.setPelota(pelota);
		scene.addJugador(jugador);
		scene.addJugador(jugador2);
		scene.addJugador(jugador3);
		
		scene.addArco(arcoArriba);
		scene.addArco(arcoAbajo);
		this.setCurrentScene(scene);

	}

	@Override
	public Dimension getDisplaySize() {
		return new Dimension(1280, 600);
	}

	@Override
	public String getTitle() {
		return "Soccer";
	}


	public static void main(String[] args) {

		new DesktopGameLauncher(new SoccerGame()).launch();

	}

}
