package soccer;

import java.awt.Dimension;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.GameScene;
import com.uqbar.vainilla.appearances.Sprite;
import com.uqbar.vainilla.events.constants.Key;
import com.uqbar.vainilla.sound.Sound;
import com.uqbar.vainilla.sound.SoundBuilder;
import com.uqbar.vainilla.sound.SoundPlayer;

public class SoccerScene extends GameScene {

	private Cancha cancha;
	private Jugador jugador;
	
	
	public Cancha getCancha() {
		return cancha;
	}



	public void setCancha(Cancha cancha) {
		this.addComponent(cancha);
		this.cancha = cancha;
	}



	public Jugador getJugador() {
		return jugador;
	}

	public void setJugador(Jugador jugador) {
		this.addComponent(jugador);
		this.jugador = jugador;
	}
	
	
	public SoccerScene(String soundFile){
		Sound s = new SoundBuilder().buildSound(this.getClass().getClassLoader().getResourceAsStream(soundFile));
		s.play();
	}
}
