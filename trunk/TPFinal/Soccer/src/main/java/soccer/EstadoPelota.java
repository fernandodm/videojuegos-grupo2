package soccer;

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
	
	public abstract void update(DeltaState deltaState);

}
