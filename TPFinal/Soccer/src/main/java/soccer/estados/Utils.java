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
	
	public static double distanciaPosicion(Jugador jugador) {
		soccer.Vector pos = jugador.posicion;
		double distanciaActual = Math.sqrt(Math.pow((jugador.getX()- pos.getX()), 2)
				+ Math.pow((jugador.getY() - pos.getY()), 2));
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
	
	public static double distanciaConJugadorVisitanteMasCercano(double x, double y) {
		Jugador jugador = Utils.jugadorVisitanteMasCercano(x, y);
		double distanciaActual = Math.sqrt(Math.pow((x - jugador.getX()), 2)
				+ Math.pow((y - jugador.getY()), 2));
		return distanciaActual;
	}
	
	public static Jugador jugadorVisitanteMasCercano(double x,double y) {
		Jugador jugadorCerca = null;
		double distancia = 100000; //es la maxima distancia entre dos jugadores
		List<Jugador> Jugadores = Utils.scene.getJugadoresVisitantes();
		for (Jugador jugador : Jugadores) {
				if(jugador.getX() != x && jugador.getY() != y){
				
					double distanciaActual = Math.sqrt(Math.pow((x - jugador.getX()), 2)
							+ Math.pow((y - jugador.getY()), 2));
					if(distanciaActual <= distancia){
						distancia  = distanciaActual;
						jugadorCerca = jugador;
					}
				}
		}
		return jugadorCerca;
	}
	
	public static int direccionJugadorVisitanteCercano(double x,double y) {
		Jugador jugador = Utils.jugadorVisitanteMasCercano(x, y);
		
		if((int)jugador.getX() == (int) x){
			if(jugador.getY()> y){
				return Direccion.DOWN;
			}else{
				return Direccion.UP;
			}
		}
		
		if((int)jugador.getY() == (int) y){
			if(jugador.getX()> x){
				return Direccion.RIGHT;
			}else{
				return Direccion.LEFT;
			}
		}
		
		if(jugador.getY()> y){
			if(jugador.getX()< x){
				return Direccion.DOWNLEFT;
			}else{
				return Direccion.DOWNRIGHT;
			}
		}
		if(jugador.getY()< y){
			if(jugador.getX()< x){

				return Direccion.UPLEFT;
			}else{
				return Direccion.UPRIGHT;
			}
		}
		return 0;
	}

	public static int direccionPelota(double x,double y) {
		Pelota pelota = Utils.scene.getPelota();
		
		if((int)pelota.getX() == (int) x){
			if(pelota.getY()> y){
				return Direccion.DOWN;
			}else{
				return Direccion.UP;
			}
		}
		
		if((int)pelota.getY() == (int) y){
			if(pelota.getX()> x){
				return Direccion.RIGHT;
			}else{
				return Direccion.LEFT;
			}
		}
		
		if(pelota.getY()> y){
			if(pelota.getX()< x){
				return Direccion.DOWNLEFT;
			}else{
				return Direccion.DOWNRIGHT;
			}
		}
		if(pelota.getY()< y){
			if(pelota.getX()< x){

				return Direccion.UPLEFT;
			}else{
				return Direccion.UPRIGHT;
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
	
	public static int direccionParaAtacar(double x,double y) {
		Arco arco = Utils.scene.getArcos().get(0);
		

		if(x> 380 &&  x<650){
				return Direccion.DOWN;

		}
		

		if(arco.getX()< x){
				return Direccion.DOWNLEFT;
		}else{
				return Direccion.DOWNRIGHT;
		}
	}
	
	public static int obtenerDireccionDeRemateVisitante(){
		if((int) (Math.random()*10) <2){
			int[] direcciones={Direccion.DOWN,Direccion.DOWNLEFT,Direccion.DOWNRIGHT};
			return direcciones[(int) (Math.random()*direcciones.length)];
		}else{
			if(Utils.scene.getPelota().getX()< Utils.scene.getArcos().get(0).getX()+25){
				return Direccion.DOWNRIGHT;
			}else{
				return Direccion.DOWNLEFT;
			}
		}
		
	}
	
	public static void marcarJugadoresNoSeleccionadoVisitantes(){
		List<Jugador> jugadoresv = scene.getJugadoresVisitantes();
		for (Jugador jv : jugadoresv) {
			jv.setEstado(new EstadoJugadorNoSeleccionadoCPU(jv));
			jv.setEstaSeleccionado(false);
			jv.flag=false;
		}
	}
	
	public static void marcarJugadoresNoSeleccionadoLocales(){
		List<Jugador> jugadoresv = scene.getJugadoresLocales();
		for (Jugador jv : jugadoresv) {
			jv.setEstado(new EstadoJugadorNoSeleccionado(jv));
			jv.setEstaSeleccionado(false);
			jv.flag=false;
		}
	}
	
	public static Jugador jugadorLocalMasCercanoAPelota(){
		return Utils.jugadorLocalMasCercano(scene.getPelota().getX(), scene.getPelota().getY());
	}

	public static int direccionConPosicion(Jugador jugador) {
	soccer.Vector pelota = jugador.posicion;
		
		if((int)pelota.getX() == (int) jugador.getX()){
			if(pelota.getY()> jugador.getY()){
				return Direccion.DOWN;
			}else{
				return Direccion.UP;
			}
		}
		
		if((int)pelota.getY() == (int) jugador.getY()){
			if(pelota.getX()>  jugador.getX()){
				return Direccion.RIGHT;
			}else{
				return Direccion.LEFT;
			}
		}
		
		if(pelota.getY()>  jugador.getY()){
			if(pelota.getX()<  jugador.getX()){
				return Direccion.DOWNLEFT;
			}else{
				return Direccion.DOWNRIGHT;
			}
		}
		if(pelota.getY()< jugador.getY()){
			if(pelota.getX()< jugador.getX()){

				return Direccion.UPLEFT;
			}else{
				return Direccion.UPRIGHT;
			}
		}
		return 0;
	}

	public static boolean hacerPase() {
		return (Math.random()*500) > 490;
	}
		
	
}
