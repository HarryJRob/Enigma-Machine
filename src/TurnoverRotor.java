//Provides extra logic on top of BasicRotor.
//Inherits from BasicRotor
public class TurnoverRotor extends BasicRotor {

	//The position at which the next rotor will be rotated
	private int turnoverPosition;
	//The reference to the next rotor
	private BasicRotor nextRotor;
	
	//Constructor
	public TurnoverRotor(String type, BasicRotor rotor) {
		super(type);
		nextRotor = rotor;
	}

	//Overrides the initialise method. 
	//Calls the superclass method and then sets the turnoverPosition depending on the type of rotor
	@Override
	public void initialise(String type) {
		super.initialise(type);
		
		switch(type) {
		
		case "I":
			turnoverPosition = 24;
			break;
		case "II": 
			turnoverPosition = 12;
			break;
		case "III": 
			turnoverPosition = 3;
			break;
		case "IV":
			turnoverPosition = 17;
			break;
		case "V":
			turnoverPosition = 7;
			break;
		}
	}
	
	//Provides extra logic to the rotate method.
	//If the position is equal to the turnover position and there is another rotor to rotate then it will rotate it.
	@Override
	public void rotate() {
		position += 1;
		position %= ROTORSIZE;
		
		if(position == turnoverPosition && nextRotor != null) {
			nextRotor.rotate();
		}
	}
	
}
