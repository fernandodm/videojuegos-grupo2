package yoni;

import com.uqbar.vainilla.DeltaState;

import fernando.Pelota;
import fernando.ArkanoidScene;

public interface Strategy {

	public void update(Raqueta raqueta, ArkanoidScene scene, DeltaState deltaState);
}
