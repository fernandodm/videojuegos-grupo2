package soccer.estados;

import soccer.Pelota;
import soccer.SoccerScene;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.GameComponent;

public abstract class EstadoPelota extends GameComponent<SoccerScene> {
	private Pelota pelota;

	public Pelota getPelota() {
		return pelota;
	}

	public void setPelota(Pelota pelota) {
		this.pelota = pelota;
	}
	
	public void frenarTiempo() {
		this.getPelota().getScene().getTiempo().setTieneQueParar(true);		
	}
	
	public void reanudarTiempo(){
		this.getPelota().getScene().getTiempo().setTieneQueParar(false);	
	}
	
	public abstract void update(DeltaState deltaState);

}
