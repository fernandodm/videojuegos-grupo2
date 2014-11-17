package soccer.estados;

import java.util.List;

import soccer.Arco;
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

	public static int direccionPelota() {
		Pelota pelota = Utils.scene.getPelota();
		return pelota.getUltimaDireccion();
	}
}
