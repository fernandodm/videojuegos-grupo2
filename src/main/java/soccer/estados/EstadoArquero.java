package soccer.estados;

import com.uqbar.vainilla.DeltaState;

public abstract class EstadoArquero extends EstadoJugador{

	@Override
	public abstract void update(DeltaState deltaState);
	
	public abstract void achicar(DeltaState deltaState);
	public abstract void resetPos(DeltaState deltaState);

}
