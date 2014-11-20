package soccer;

import java.awt.Color;
import java.awt.Dimension;

import soccer.estados.EstadoArqueroCpu;
import soccer.estados.EstadoJugadorNoSeleccionado;
import soccer.estados.EstadoJugadorSeleccionado;
import soccer.estados.Utils;

import com.uqbar.vainilla.DesktopGameLauncher;
import com.uqbar.vainilla.Game;
import com.uqbar.vainilla.GameScene;


public class SoccerGame extends Game {

        @Override
        protected void initializeResources() {
                // TODO Auto-generated method stub
        }
        
        public void resetGame(Marcador marcador, Tiempo tiempo){
        	
        	SoccerScene scene = new SoccerScene("sonido1.wav");
        	Utils.scene=scene;
        	Cancha cancha = new Cancha("cancha.png", 0, -460);
        	Arco arcoArriba = new Arco("arco.png", 0, 525, -452);
        	Arco arcoAbajo = new Arco("arco.png", 3.14, 525, 953);
        	Pelota pelota = new Pelota("pelota1.png");
	
        	LabelSeleccionado label = new LabelSeleccionado(625, 240);
	                
        		
        	JugadorLocal jugadorLocal1 = new JugadorLocal("jugadores.png", 150, 440, 300, label);
        	JugadorLocal jugadorLocal2 = new JugadorLocal("jugadores.png", 150, 622, 265, label);
        	JugadorLocal jugadorLocal3 = new JugadorLocal("jugadores.png", 150, 800, 300, label);
        	JugadorLocal jugadorLocal4 = new JugadorLocal("jugadores.png", 150, 900, 700, label);
        	JugadorLocal jugadorLocal5 = new JugadorLocal("jugadores.png", 150, 350, 700, label);
        	JugadorLocal jugadorLocal6 = new JugadorLocal("jugadores.png", 150, 625, 720, label);
        	JugadorLocal jugadorLocal7 = new JugadorLocal("jugadores.png", 150, 622, 500, label);
        	JugadorLocal arqueroLocal = new JugadorLocal("arqueros.png", 150, 622, 960, label);
	               
        	jugadorLocal1.setEstado(new EstadoJugadorNoSeleccionado(jugadorLocal1));
        	jugadorLocal2.setEstado(new EstadoJugadorSeleccionado(jugadorLocal2));
        	jugadorLocal3.setEstado(new EstadoJugadorNoSeleccionado(jugadorLocal3));
        	jugadorLocal4.setEstado(new EstadoJugadorNoSeleccionado(jugadorLocal4));
        	jugadorLocal5.setEstado(new EstadoJugadorNoSeleccionado(jugadorLocal5));
        	jugadorLocal6.setEstado(new EstadoJugadorNoSeleccionado(jugadorLocal6));
        	jugadorLocal7.setEstado(new EstadoJugadorNoSeleccionado(jugadorLocal7));
        	arqueroLocal.setEstado(new EstadoJugadorNoSeleccionado(arqueroLocal));
        	
        	JugadorVisitante jugadorVisitante1 = new JugadorVisitante("jugadores.png", 85, 440, 250, label);
        	JugadorVisitante jugadorVisitante2 = new JugadorVisitante("jugadores.png", 85, 622, 155, label);
        	JugadorVisitante jugadorVisitante3 = new JugadorVisitante("jugadores.png", 85, 800, 250, label);
        	JugadorVisitante jugadorVisitante4 = new JugadorVisitante("jugadores.png", 85, 900, -150, label);
        	JugadorVisitante jugadorVisitante5 = new JugadorVisitante("jugadores.png", 85, 350, -150, label);
        	JugadorVisitante jugadorVisitante6 = new JugadorVisitante("jugadores.png", 85, 625, -170, label);
        	JugadorVisitante jugadorVisitante7 = new JugadorVisitante("jugadores.png", 85, 622, -25, label);
        	JugadorVisitante arqueroVisitante = new JugadorVisitante("arqueros.png", 150, 622, -390, label);
        
        	
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
        	scene.addJugadorEquipoLocal(jugadorLocal7);
        	scene.addJugadorEquipoLocal(arqueroLocal);
        	
        	scene.addJugadorEquipoVisitante(jugadorVisitante1);
        	scene.addJugadorEquipoVisitante(jugadorVisitante2);
        	scene.addJugadorEquipoVisitante(jugadorVisitante3);
        	scene.addJugadorEquipoVisitante(jugadorVisitante4);
        	scene.addJugadorEquipoVisitante(jugadorVisitante5);
        	scene.addJugadorEquipoVisitante(jugadorVisitante6);
        	scene.addJugadorEquipoVisitante(jugadorVisitante7);
        	scene.addJugadorEquipoVisitante(arqueroVisitante);
        	
        	arqueroVisitante.setEstado(new EstadoArqueroCpu(arqueroVisitante));
        	
        	scene.addArco(arcoArriba);
        	scene.addArco(arcoAbajo);
        	scene.setTiempo(tiempo);
        	scene.setMarcador(marcador);
        	this.setCurrentScene(scene);
        	
        	Desplazador.getInstance().addComponent(arcoArriba);
        	Desplazador.getInstance().addComponent(arcoAbajo);
        	Desplazador.getInstance().addComponent(cancha);
        	Desplazador.getInstance().addComponent(jugadorLocal1);
        	Desplazador.getInstance().addComponent(jugadorLocal2);
        	Desplazador.getInstance().addComponent(jugadorLocal3);
        	Desplazador.getInstance().addComponent(jugadorLocal4);
        	Desplazador.getInstance().addComponent(jugadorLocal5);
        	Desplazador.getInstance().addComponent(jugadorLocal6);
        	Desplazador.getInstance().addComponent(jugadorLocal7);
        	Desplazador.getInstance().addComponent(arqueroLocal);
        	
        	Desplazador.getInstance().addComponent(jugadorVisitante1);
        	Desplazador.getInstance().addComponent(jugadorVisitante2);
        	Desplazador.getInstance().addComponent(jugadorVisitante3);
        	Desplazador.getInstance().addComponent(jugadorVisitante4);
        	Desplazador.getInstance().addComponent(jugadorVisitante5);
        	Desplazador.getInstance().addComponent(jugadorVisitante6);
        	Desplazador.getInstance().addComponent(jugadorVisitante7);
        	Desplazador.getInstance().addComponent(arqueroVisitante);
        	
        	Desplazador.getInstance().setCancha(cancha);
        	Desplazador.getInstance().setPelota(pelota);
        	Desplazador.getInstance().addComponent(label);
        	
        }

        @Override
        protected void setUpScenes(){
		               
        	SoccerScene scene = new SoccerScene("sonido1.wav");
        	Utils.scene=scene;
        	Cancha cancha = new Cancha("cancha.png", 0, -460);
        	Arco arcoArriba = new Arco("arco.png", 0, 525, -452);
        	Arco arcoAbajo = new Arco("arco.png", 3.14, 525, 953);
        	Pelota pelota = new Pelota("pelota1.png");
        	Marcador marcadores = new Marcador();
	
        	LabelSeleccionado label = new LabelSeleccionado(625, 240);
	                
        	Tiempo tiempo = new Tiempo(300, 10, Color.lightGray);
        		
        	JugadorLocal jugadorLocal1 = new JugadorLocal("jugadores.png", 150, 440, 300, label);
        	JugadorLocal jugadorLocal2 = new JugadorLocal("jugadores.png", 150, 622, 265, label);
        	JugadorLocal jugadorLocal3 = new JugadorLocal("jugadores.png", 150, 800, 300, label);
        	JugadorLocal jugadorLocal4 = new JugadorLocal("jugadores.png", 150, 900, 700, label);
        	JugadorLocal jugadorLocal5 = new JugadorLocal("jugadores.png", 150, 350, 700, label);
        	JugadorLocal jugadorLocal6 = new JugadorLocal("jugadores.png", 150, 625, 720, label);
        	JugadorLocal jugadorLocal7 = new JugadorLocal("jugadores.png", 150, 622, 500, label);
        	JugadorLocal arqueroLocal = new JugadorLocal("arqueros.png", 150, 622, 960, label);
	               
        	jugadorLocal1.setEstado(new EstadoJugadorNoSeleccionado(jugadorLocal1));
        	jugadorLocal2.setEstado(new EstadoJugadorSeleccionado(jugadorLocal2));
        	jugadorLocal3.setEstado(new EstadoJugadorNoSeleccionado(jugadorLocal3));
        	jugadorLocal4.setEstado(new EstadoJugadorNoSeleccionado(jugadorLocal4));
        	jugadorLocal5.setEstado(new EstadoJugadorNoSeleccionado(jugadorLocal5));
        	jugadorLocal6.setEstado(new EstadoJugadorNoSeleccionado(jugadorLocal6));
        	jugadorLocal7.setEstado(new EstadoJugadorNoSeleccionado(jugadorLocal7));
        	arqueroLocal.setEstado(new EstadoJugadorNoSeleccionado(arqueroLocal));
        	
        	JugadorVisitante jugadorVisitante1 = new JugadorVisitante("jugadores.png", 85, 440, 250, label);
        	JugadorVisitante jugadorVisitante2 = new JugadorVisitante("jugadores.png", 85, 622, 155, label);
        	JugadorVisitante jugadorVisitante3 = new JugadorVisitante("jugadores.png", 85, 800, 250, label);
        	JugadorVisitante jugadorVisitante4 = new JugadorVisitante("jugadores.png", 85, 900, -150, label);
        	JugadorVisitante jugadorVisitante5 = new JugadorVisitante("jugadores.png", 85, 350, -150, label);
        	JugadorVisitante jugadorVisitante6 = new JugadorVisitante("jugadores.png", 85, 625, -170, label);
        	JugadorVisitante jugadorVisitante7 = new JugadorVisitante("jugadores.png", 85, 622, -25, label);
        	JugadorVisitante arqueroVisitante = new JugadorVisitante("arqueros.png", 150, 622, -390, label);
        
        	
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
        	scene.addJugadorEquipoLocal(jugadorLocal7);
        	scene.addJugadorEquipoLocal(arqueroLocal);
        	
        	scene.addJugadorEquipoVisitante(jugadorVisitante1);
        	scene.addJugadorEquipoVisitante(jugadorVisitante2);
        	scene.addJugadorEquipoVisitante(jugadorVisitante3);
        	scene.addJugadorEquipoVisitante(jugadorVisitante4);
        	scene.addJugadorEquipoVisitante(jugadorVisitante5);
        	scene.addJugadorEquipoVisitante(jugadorVisitante6);
        	scene.addJugadorEquipoVisitante(jugadorVisitante7);
        	scene.addJugadorEquipoVisitante(arqueroVisitante);
        	
        	arqueroVisitante.setEstado(new EstadoArqueroCpu(arqueroVisitante));
        	
        	scene.addArco(arcoArriba);
        	scene.addArco(arcoAbajo);
        	scene.setTiempo(tiempo);
        	scene.setMarcador(marcadores);
        	this.setCurrentScene(scene);
        	
        	Desplazador.getInstance().addComponent(arcoArriba);
        	Desplazador.getInstance().addComponent(arcoAbajo);
        	Desplazador.getInstance().addComponent(cancha);
        	Desplazador.getInstance().addComponent(jugadorLocal1);
        	Desplazador.getInstance().addComponent(jugadorLocal2);
        	Desplazador.getInstance().addComponent(jugadorLocal3);
        	Desplazador.getInstance().addComponent(jugadorLocal4);
        	Desplazador.getInstance().addComponent(jugadorLocal5);
        	Desplazador.getInstance().addComponent(jugadorLocal6);
        	Desplazador.getInstance().addComponent(jugadorLocal7);
        	Desplazador.getInstance().addComponent(arqueroLocal);
        	
        	Desplazador.getInstance().addComponent(jugadorVisitante1);
        	Desplazador.getInstance().addComponent(jugadorVisitante2);
        	Desplazador.getInstance().addComponent(jugadorVisitante3);
        	Desplazador.getInstance().addComponent(jugadorVisitante4);
        	Desplazador.getInstance().addComponent(jugadorVisitante5);
        	Desplazador.getInstance().addComponent(jugadorVisitante6);
        	Desplazador.getInstance().addComponent(jugadorVisitante7);
        	Desplazador.getInstance().addComponent(arqueroVisitante);
        	
        	Desplazador.getInstance().setCancha(cancha);
        	Desplazador.getInstance().setPelota(pelota);
        	Desplazador.getInstance().addComponent(label);
        }	
       
        @Override
        public Dimension getDisplaySize() {
                return new Dimension(1280, 600);
        }

        @Override
        public String getTitle() {
                return "Soccer";
        }

        public GanasteOPerdisteScene buildScene(String msj, String resultado) {
    		return new GanasteOPerdisteScene(msj, resultado);
    	}

        public static void main(String[] args) {

            new DesktopGameLauncher(new SoccerGame()).launch();

        }

}
