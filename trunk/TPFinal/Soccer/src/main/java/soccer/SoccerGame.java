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
				
		Cancha cancha = new Cancha("cancha.png", 0, 0);
		Arco arco = new Arco("arco.png");
		Pelota pelota = new Pelota("pelota.png", new Vector(0, -5), 10);
		
		LabelSeleccionado label = new LabelSeleccionado(434, 828);
		
		Jugador jugador = new Jugador("jugadores.png", 100, 425, 800, label);
		Jugador jugador2 = new Jugador("jugadores.png", 100, 625, 900, label);
		Jugador jugador3 = new Jugador("jugadores.png", 100, 800, 800, label);
		jugador.setEstaSeleccionado(true);
		
		scene.setCancha(cancha);
		scene.setPelota(pelota);
		scene.addJugador(jugador);
		scene.addJugador(jugador2);
		scene.addJugador(jugador3);
		scene.addComponent(label);
		scene.setArco(arco);
		
		this.setCurrentScene(scene);

	}



	@Override
	public Dimension getDisplaySize() {
		return new Dimension(950, 950);
	}

	@Override
	public String getTitle() {
		return "Soccer";
	}


	public static void main(String[] args) {

		new DesktopGameLauncher(new SoccerGame()).launch();

	}

}
