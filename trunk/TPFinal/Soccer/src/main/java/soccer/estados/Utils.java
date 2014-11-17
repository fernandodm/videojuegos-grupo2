package soccer.estados;

import java.util.List;

import soccer.Arco;
import soccer.Jugador;
import soccer.JugadorLocal;
import soccer.Pelota;
import soccer.SoccerScene;

public class Utils {

	public static SoccerScene  scene;
	
	public static double distanciaConPelota(int x,int y){
		Pelota pelota=Utils.scene.getPelota();
		double distanciaActual = Math.sqrt(Math.pow((x - pelota.getX()),2) 
	 			   + Math.pow((y- pelota.getY()), 2));
		return distanciaActual;
	}
	
	public static double distanciaConArcoLocal(int x,int y){
		Arco Arco=Utils.scene.getArcos().get(1);
		double distanciaActual = Math.sqrt(Math.pow((x - Arco.getX()),2) 
	 			   + Math.pow((y- Arco.getY()), 2));
		return distanciaActual;
	}
	
	public static double distanciaConArcoVisitante(int x,int y){
		Arco Arco=Utils.scene.getArcos().get(0);
		double distanciaActual = Math.sqrt(Math.pow((x - Arco.getX()),2) 
	 			   + Math.pow((y- Arco.getY()), 2));
		return distanciaActual;
	}
	
	public static boolean tienePelotaLocal(){
		List<Jugador> Jugadores=Utils.scene.getJugadoresLocales();
		for (Jugador jugador : Jugadores) {
			if(jugador.flag){
				return true;
			}
		}
		return false;
	}
	
	public static boolean tienePelotaVisitante(){
		List<Jugador> Jugadores=Utils.scene.getJugadoresVisitantes();
		for (Jugador jugador : Jugadores) {
			if(jugador.flag){
				return true;
			}
		}
		return false;
	}
}
