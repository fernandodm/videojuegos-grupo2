package soccer;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.events.constants.Key;

public class Direccion {

	final static int UP=1;
	final static int DOWN=2;
	final static int LEFT=3;
	final static int RIGHT=4;
	final static int UPRIGHT=5;
	final static int DOWNRIGHT=6;
	final static int UPLEFT=7;
	final static int DOWNLEFT=8;

	final static Key leftKey = Key.A;
	final static Key rigthKey = Key.D;
	final static Key upKey = Key.W;
	final static Key downKey = Key.S;
	
	public static int obtenerDireccion(DeltaState deltaState) {
		if (deltaState.isKeyBeingHold(upKey) && deltaState.isKeyBeingHold(rigthKey)) {
			return UPRIGHT;
		} 
		if (deltaState.isKeyBeingHold(upKey) && deltaState.isKeyBeingHold(leftKey)) {
			return UPLEFT;
		} 
		if (deltaState.isKeyBeingHold(downKey) && deltaState.isKeyBeingHold(rigthKey)) {
			return DOWNRIGHT;
		} 
		if (deltaState.isKeyBeingHold(downKey) && deltaState.isKeyBeingHold(leftKey)) {
			return DOWNLEFT;
		} 
		if (deltaState.isKeyBeingHold(upKey)) {
			return UP;
		} 
			
		if (deltaState.isKeyBeingHold(downKey)) {
			return DOWN;
		}
	
		if (deltaState.isKeyBeingHold(leftKey)) {
			return LEFT;
		} 
		if (deltaState.isKeyBeingHold(rigthKey)) {
			return RIGHT;
		}
		return 0;
	}
}
