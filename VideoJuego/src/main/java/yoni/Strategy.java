package yoni;

import com.uqbar.vainilla.DeltaState;

import fernando.Pelota;
import fernando.PelotitaScene;

public interface Strategy {

	public void update(Raqueta raqueta, PelotitaScene scene, DeltaState deltaState);
}
