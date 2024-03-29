package soccer;

import java.util.ArrayList;
import java.util.List;
import com.uqbar.vainilla.GameScene;
import com.uqbar.vainilla.sound.Sound;
import com.uqbar.vainilla.sound.SoundBuilder;

public class SoccerScene extends GameScene {

	private Cancha cancha;
	private List<Arco> arcos = new ArrayList<Arco>();
	private Equipo equipoLocal = new Equipo();
	private Equipo equipoVisitante = new Equipo();
	private Tiempo tiempo;
	private Marcador marcador;
	private LabelSeleccionado label;

	public LabelSeleccionado getLabel() {
		return label;
	}

	public void setLabel(LabelSeleccionado label) {
		this.label = label;
		this.addComponent(label);
	}

	public Tiempo getTiempo() {
		return tiempo;
	}

	public void setMarcador(Marcador marcador) {
		this.marcador = marcador;
		this.addComponent(marcador);
	}

	public void setTiempo(Tiempo tiempo) {
		this.tiempo = tiempo;
		this.addComponent(tiempo);
	}

	public List<Arco> getArcos() {
		return arcos;
	}

	public Equipo getEquipoLocal() {
		return equipoLocal;
	}

	public void setEquipoLocal(Equipo equipoLocal) {
		this.equipoLocal = equipoLocal;
	}

	public Equipo getEquipoVisitante() {
		return equipoVisitante;
	}

	public void setEquipoVisitante(Equipo equipoVisitante) {
		this.equipoVisitante = equipoVisitante;
	}

	private Pelota pelota;

	public Pelota getPelota() {
		return pelota;
	}

	public void setPelota(Pelota pelota) {
		this.pelota = pelota;
		this.addComponent(pelota);
	}

	public void addArco(Arco arco) {
		this.arcos.add(arco);
		this.addComponent(arco);
	}

	public Cancha getCancha() {
		return cancha;
	}

	public void setCancha(Cancha cancha) {
		this.addComponent(cancha);
		this.cancha = cancha;
	}

	public void addJugadorEquipoLocal(Jugador jugador) {
		this.addComponent(jugador);
		this.equipoLocal.getJugadores().add(jugador);
	}

	public void addJugadorEquipoVisitante(Jugador jugador) {
		this.addComponent(jugador);
		this.equipoVisitante.getJugadores().add(jugador);
	}

	public List<Jugador> getJugadoresLocales() {
		return this.equipoLocal.getJugadores();
	}

	public List<Jugador> getJugadoresVisitantes() {
		return this.equipoVisitante.getJugadores();
	}

	public SoccerScene(String soundFile) {
		Sound s = new SoundBuilder().buildSound(this.getClass()
				.getClassLoader().getResourceAsStream(soundFile));
		s.play();
	}

	public List<Jugador> getJugadores() {
		List<Jugador> jugadoresLocales = this.getPelota().getScene()
				.getEquipoLocal().getJugadores();
		List<Jugador> jugadoresVisitantes = this.getPelota().getScene()
				.getEquipoVisitante().getJugadores();
		List<Jugador> jugadores = new ArrayList<Jugador>();
		jugadores.addAll(jugadoresLocales);
		jugadores.addAll(jugadoresVisitantes);
		return jugadores;
	}

	public Marcador getMarcador() {
		return marcador;
	}

	public void resetScene(Marcador marcador){
		this.setMarcador(marcador);
	}
	
	public void resetSceneGV(Marcador marcador){
		((SoccerGame)this.getGame()).resetGolLoc(marcador,this.getTiempo());	
	}
	
	public void resetSceneGL(Marcador marcador){
		((SoccerGame)this.getGame()).resetGolVis(marcador,this.getTiempo());	
	}

}
