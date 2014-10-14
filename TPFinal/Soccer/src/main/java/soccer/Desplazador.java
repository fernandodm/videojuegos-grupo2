package soccer;

import java.util.LinkedList;
import java.util.List;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.GameComponent;

@SuppressWarnings("rawtypes")
public class Desplazador {

	private List<GameComponent> aDesplazar;

	private static Desplazador desplazador = new Desplazador();

	private Pelota pelota;
	private Cancha cancha;

	public Pelota getPelota() {
		return pelota;
	}

	public void setPelota(Pelota pelota) {
		this.pelota = pelota;
	}

	public Cancha getCancha() {
		return cancha;
	}

	public void setCancha(Cancha cancha) {
		this.cancha = cancha;
	}

	private Desplazador() {
		super();
		this.setaDesplazar(new LinkedList<GameComponent>());
	}

	public static Desplazador getInstance() {
		return desplazador;
	}

	public void addComenent(GameComponent go) {
		this.getaDesplazar().add(go);
	}

	public List<GameComponent> getaDesplazar() {
		return aDesplazar;
	}

	public void setaDesplazar(List<GameComponent> aDesplazar) {
		this.aDesplazar = aDesplazar;
	}

	public boolean hayQueDesplazarCamara() {
		return cancha.getY() <= 0 && cancha.getY() >= -930;
	}

	public void desplazarComponentes(double n, DeltaState deltaState) {
		for (GameComponent x : this.getaDesplazar()) {
			x.setY(x.getY() + (0.4 * n));
		}

	}

}
