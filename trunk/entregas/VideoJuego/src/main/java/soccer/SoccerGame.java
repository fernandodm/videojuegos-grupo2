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
		
//		GameScene scene = new SoccerScene("jugador1.png", "jugador4.png","jugador5.png", 30, "sonido1.wav");
		SoccerScene scene = new SoccerScene("sonido1.wav");
		
		Cancha cancha = new Cancha("cancha.png", 0, 0);
		Jugador jugador = new Jugador("jugador1.png","jugadores.png", 70, 425, 425);
		
		scene.setCancha(cancha);
		scene.setJugador(jugador);
		
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
