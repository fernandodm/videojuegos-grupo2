package soccer.estados;

import java.util.List;

import soccer.Arco;
import soccer.Direccion;
import soccer.Jugador;
import soccer.Pelota;
import soccer.SoccerScene;

public class Utils {

	public static SoccerScene scene;

	public static double distanciaConPelota(double x, double y) {
		Pelota pelota = Utils.scene.getPelota();
		double distanciaActual = Math.sqrt(Math.pow((x - pelota.getX()), 2)
				+ Math.pow((y - pelota.getY()), 2));
		return distanciaActual;
	}

	public static double distanciaConArcoLocal(double x, double y) {
		Arco Arco = Utils.scene.getArcos().get(1);
		double distanciaActual = Math.sqrt(Math.pow((x - Arco.getX()), 2)
				+ Math.pow((y - Arco.getY()), 2));
		return distanciaActual;
	}

	public static double distanciaConArcoVisitante(double x, double y) {
		Arco Arco = Utils.scene.getArcos().get(0);
		double distanciaActual = Math.sqrt(Math.pow((x - Arco.getX()), 2)
				+ Math.pow((y - Arco.getY()), 2));
		return distanciaActual;
	}

	public static boolean tienePelotaLocal() {
		List<Jugador> Jugadores = Utils.scene.getJugadoresLocales();
		for (Jugador jugador : Jugadores) {
			if (jugador.flag) {
				return true;
			}
		}
		return false;
	}

	public static boolean tienePelotaVisitante() {
		List<Jugador> Jugadores = Utils.scene.getJugadoresVisitantes();
		for (Jugador jugador : Jugadores) {
			if (jugador.flag) {
				return true;
			}
		}
		return false;
	}
	
	public static Jugador jugadorLocalMasCercano(double x,double y) {
		Jugador jugadorCerca = null;
		double distancia = 100000; //es la maxima distancia entre dos jugadores
		List<Jugador> Jugadores = Utils.scene.getJugadoresLocales();
		for (Jugador jugador : Jugadores) {
				double distanciaActual = Math.sqrt(Math.pow((x - jugador.getX()), 2)
						+ Math.pow((y - jugador.getY()), 2));
				if(distanciaActual <= distancia){
					distancia  = distanciaActual;
					jugadorCerca = jugador;
				}
		}
		return jugadorCerca;
	}

	public static int direccionPelota(double x,double y) {
		Pelota pelota = Utils.scene.getPelota();
		if(pelota.getY()> y){
			if(pelota.getX()< x){
				return Direccion.DOWNRIGHT;
			}else{
				return Direccion.DOWNLEFT;
			}
		}
		if(pelota.getY()< y){
			if(pelota.getX()< x){

				return Direccion.UPRIGHT;
			}else{
				return Direccion.UPLEFT;
			}
		}
		return pelota.getUltimaDireccion();
	}
	
	public static int direccionEsquivar(double x,double y) {
		Pelota pelota = Utils.scene.getPelota();
		
		if(pelota.getY()> y && pelota.getUltimaDireccion() != Direccion.DOWNLEFT
				&& pelota.getUltimaDireccion() != Direccion.DOWNRIGHT){
			if(pelota.getX()< x){
				return Direccion.UPLEFT;
			}else{
				return Direccion.UPRIGHT;
			}
		}
		
		if(pelota.getY()< y && pelota.getUltimaDireccion() != Direccion.UPLEFT
				&& pelota.getUltimaDireccion() != Direccion.UPRIGHT){
			if(pelota.getX()< x){
				return Direccion.DOWNLEFT;
			}else{
				return Direccion.DOWNRIGHT;
			}
		}

		return pelota.getUltimaDireccion();
	}
}
