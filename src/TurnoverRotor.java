
public class TurnoverRotor extends BasicRotor {

	private int turnoverPosition;
	private BasicRotor nextRotor;
	
	public TurnoverRotor(String type, BasicRotor rotor) {
		super(type);
		nextRotor = rotor;
	}

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
		
		inverseMapping =  new int[ROTORSIZE];
		
		for(int i = 0; i < ROTORSIZE; i++) {
			inverseMapping[mapping[i]] = i;
		}
	}
	
	@Override
	public void rotate() {
		position += 1;
		position %= ROTORSIZE;
		
		if(position == turnoverPosition && nextRotor != null) {
			nextRotor.rotate();
		}
	}
	
}
