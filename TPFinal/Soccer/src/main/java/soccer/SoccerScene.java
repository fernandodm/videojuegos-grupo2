package soccer;

import java.util.ArrayList;
import java.util.List;

import com.uqbar.vainilla.GameScene;
import com.uqbar.vainilla.sound.Sound;
import com.uqbar.vainilla.sound.SoundBuilder;

public class SoccerScene extends GameScene {

	private CanchaScene cancha;
	private List<Jugador> jugadores = new ArrayList<Jugador>();
	
	
	public CanchaScene getCancha() {
		return cancha;
	}

	public void setCancha(CanchaScene cancha) {
		this.addComponent(cancha);
		this.cancha = cancha;
	}



	public List<Jugador> getJugadores() {
		return jugadores;
	}

	public void addJugador(Jugador jugador) {
		this.addComponent(jugador);
		this.jugadores.add(jugador);
	}
	
	
	public SoccerScene(String soundFile){
		Sound s = new SoundBuilder().buildSound(this.getClass().getClassLoader().getResourceAsStream(soundFile));
		s.play();
	}
	
}
