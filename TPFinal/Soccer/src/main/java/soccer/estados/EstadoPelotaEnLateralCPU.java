package soccer.estados;

import soccer.Pelota;

import com.uqbar.vainilla.DeltaState;

public class EstadoPelotaEnLateralCPU extends EstadoPelota {

	public EstadoPelotaEnLateralCPU(Pelota pelota) {
		this.setPelota(pelota);
	}

	@Override
	public void update(DeltaState deltaState) {

		if(this.getPelota().isEnRemate()){
			this.getPelota().moverPelotaPorRemate(deltaState);
		}
	}

}
