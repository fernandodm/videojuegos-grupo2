package soccer;

import java.awt.Dimension;




import soccer.estados.EstadoJugadorNoSeleccionado;
import soccer.estados.EstadoJugadorSeleccionado;

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
		Pelota pelota = new Pelota("pelota1.png");

//		pelota.setEstadoP(new EstadoPelotaEnJuego(pelota));
		
		LabelSeleccionado label = new LabelSeleccionado(625, 240);
		
		Jugador jugadorLocal1 = new Jugador("jugadores.png", 150, 440, 300, label);
		Jugador jugadorLocal2 = new Jugador("jugadores.png", 150, 622, 265, label);
		Jugador jugadorLocal3 = new Jugador("jugadores.png", 150, 800, 300, label);
		Jugador jugadorLocal4 = new Jugador("jugadores.png", 150, 900, 700, label);
		Jugador jugadorLocal5 = new Jugador("jugadores.png", 150, 350, 700, label);
		Jugador jugadorLocal6 = new Jugador("jugadores.png", 150, 625, 720, label);
		Jugador arqueroLocal = new Jugador("arqueros.png", 150, 622, 960, label);
		
		jugadorLocal1.setEstado(new EstadoJugadorNoSeleccionado(jugadorLocal1));
		jugadorLocal2.setEstado(new EstadoJugadorSeleccionado(jugadorLocal2));
		jugadorLocal3.setEstado(new EstadoJugadorNoSeleccionado(jugadorLocal3));
		jugadorLocal4.setEstado(new EstadoJugadorNoSeleccionado(jugadorLocal4));
		jugadorLocal5.setEstado(new EstadoJugadorNoSeleccionado(jugadorLocal5));
		jugadorLocal6.setEstado(new EstadoJugadorNoSeleccionado(jugadorLocal6));
		arqueroLocal.setEstado(new EstadoJugadorNoSeleccionado(arqueroLocal));
		
		jugadorLocal2.setEstaSeleccionado(true);
		jugadorLocal2.setAppearance(jugadorLocal2.images.get(Direccion.DOWN).get(3));
		
		scene.setCancha(cancha);
		scene.addComponent(label);
		scene.setPelota(pelota);
		scene.addJugadorEquipoLocal(jugadorLocal1);
		scene.addJugadorEquipoLocal(jugadorLocal2);
		scene.addJugadorEquipoLocal(jugadorLocal3);
		scene.addJugadorEquipoLocal(jugadorLocal4);
		scene.addJugadorEquipoLocal(jugadorLocal5);
		scene.addJugadorEquipoLocal(jugadorLocal6);
		scene.addJugadorEquipoLocal(arqueroLocal);
		
		
		scene.addArco(arcoArriba);
		scene.addArco(arcoAbajo);
		this.setCurrentScene(scene);
		
		Desplazador.getInstance().addComenent(arcoArriba);
		Desplazador.getInstance().addComenent(arcoAbajo);
		Desplazador.getInstance().addComenent(cancha);
		Desplazador.getInstance().addComenent(jugadorLocal1);
		Desplazador.getInstance().addComenent(jugadorLocal2);
		Desplazador.getInstance().addComenent(jugadorLocal3);
		Desplazador.getInstance().addComenent(jugadorLocal4);
		Desplazador.getInstance().addComenent(jugadorLocal5);
		Desplazador.getInstance().addComenent(jugadorLocal6);
		Desplazador.getInstance().addComenent(arqueroLocal);
		Desplazador.getInstance().setCancha(cancha);
		Desplazador.getInstance().setPelota(pelota);
		Desplazador.getInstance().addComenent(label);
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
