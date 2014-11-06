package soccer.estados;

import soccer.Pelota;

import com.uqbar.vainilla.DeltaState;

public class EstadoPelotaFueraDeJuego extends EstadoPelota {
	
	public EstadoPelotaFueraDeJuego(Pelota pelota) {
		this.setPelota(pelota);
	
	}

	@Override
	public void update(DeltaState deltaState) {
		// TODO Auto-generated method stub

	}

}
