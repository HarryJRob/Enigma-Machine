//Provides all of the code necessary to run a enigma machine
public class EnigmaMachine {
	
	//Internal Variables
	private Plugboard plugboard;
	private Reflector reflector;
	private BasicRotor[] rotorArray = new BasicRotor[3];
	
	//A array of all possible input characters
	private final char[] charMapping = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
	
	//Constructor to create a new plugboard
	public EnigmaMachine() {
		plugboard = new Plugboard();
	}
	
	//Encodes a letter. Throws a error if it fails
	public char encodeLetter(char inputChar) throws Exception {
		//Puts the letter through the plugboard
		inputChar = plugboard.substitute(Character.toUpperCase(inputChar));
		
		//Convert the letter to a numeric value
		int charValue = -1;
		
		for(int i = 0; i < 26; i++) {
			if(charMapping[i] == inputChar) {
				charValue = i;
				break;
			}
		}
		
		//Pass the value through each rotor
		for(int i = 0; i < 3; i++) {
			charValue = rotorArray[i].substitute(charValue);
		}
		
		//Pass the value through the reflector
		charValue = reflector.substitute(charValue);
		
		//Pass the value back through the rotors
		for(int i = 2; i > -1; i--) {
			charValue = rotorArray[i].substituteBack(charValue);
		}
		
		//Rotate the first rotor
		rotorArray[0].rotate();
		
		//Maps the value back to a character and puts it back through the plug board
		return plugboard.substitute(charMapping[charValue]);
	}

	//Provides the code for all of the tests specified.
	//Naming this start is a really bad idea as it provides no information as to what it actually does
	public void start() {
		//Test 1
		//Set up the enigma machine
		BasicRotor rotor1 = new BasicRotor("I");
		BasicRotor rotor2 = new BasicRotor("II");
		BasicRotor rotor3 = new BasicRotor("III");
		reflector =  new Reflector("ReflectorI");
		String inputString;
		
		System.out.println("Test 1\n");
		
		inputString = "GFWIQH";
		
		System.out.println("Input String: " + inputString);
		
		addPlug('A', 'M');
		addPlug('G', 'L');
		addPlug('E', 'T');
		
		addRotor(rotor1,1);
		addRotor(rotor2,2);
		addRotor(rotor3,3);
		
		addReflector(reflector);
		
		setPosition(1,6);
		setPosition(2,12);
		setPosition(3,5);
		
		//Encode the input
		System.out.print("Output String: ");
		for(int i = 0; i < inputString.length(); i++) {
			try {
				System.out.print(encodeLetter(inputString.charAt(i)));
				
			} catch (Exception e) {
				//Print out '?' if a error is thrown by encode
				System.out.print("?");
			}
		}
		
		System.out.println();
		
		plugboard.clear();
		
		//Test 2
		//Set up the enigma machine
		System.out.println("\nTest 2\n");
		
		inputString = "GACIG";
		
		System.out.println("Input String: " + inputString);
		
		addPlug('B', 'C');
		addPlug('R', 'I');
		addPlug('S', 'M');
		addPlug('A', 'F');
		
		rotor1 = new BasicRotor("IV");
		rotor2 = new BasicRotor("V");
		rotor3 = new BasicRotor("II");
		
		reflector.initialise("ReflectorII");
		
		addRotor(rotor1,1);
		addRotor(rotor2,2);
		addRotor(rotor3,3);
		
		addReflector(reflector);
		
		setPosition(1,23);
		setPosition(2,4);
		setPosition(3,9);
		
		//Encode the input string
		System.out.print("Output String: ");
		for(int i = 0; i < inputString.length(); i++) {
			try {
				System.out.print(encodeLetter(inputString.charAt(i)));
				
			} catch (Exception e) {
				//Print out '?' if a error is thrown by encode
				System.out.print("?");
			}
		}
		
		System.out.println();
		
		plugboard.clear();
		
		//Test 3
		//Set up the enigma machine
		System.out.println("\nTest 3\n");
		
		inputString = "OJVAYFGUOFIVOTAYRNIWJYQWMXUEJGXYGIFT";
		
		System.out.println("Input String: " + inputString);
		
		addPlug('Q', 'F');
		
		TurnoverRotor turnRotor3 = new TurnoverRotor("III", null);
		TurnoverRotor turnRotor2 = new TurnoverRotor("II",turnRotor3);
		TurnoverRotor turnRotor1 = new TurnoverRotor("I",turnRotor2);
		
		addRotor(turnRotor1,1);
		addRotor(turnRotor2,2);
		addRotor(turnRotor3,3);
		
		reflector.initialise("ReflectorI");
		
		setPosition(1,23);
		setPosition(2,11);
		setPosition(3,7);
		
		//Encode the input string
		System.out.print("Output String: ");
		for(int i = 0; i < inputString.length(); i++) {
			try {
				System.out.print(encodeLetter(inputString.charAt(i)));
				
			} catch (Exception e) {
				//Print out '?' if a error is thrown by encode
				System.out.print("?");
			}
		}
		
		System.out.println();
	}
	
	//Adds a plug to the plugboard
	public void addPlug(char end1, char end2) {
		plugboard.addPlug(end1, end2);
	}
	
	//Clears the plugboard
	public void clearPlugboard() {
		plugboard.clear();
	}
	
	//Adds a rotor to the rotor list. 
	//Throws a error if you try to address a slot outside the bounds of the rotor array. 
	//Uses slot - 1 to address the rotorArray as 1-3 as a slot number makes more sense than 0-2
	public void addRotor(BasicRotor rotor, int slot) throws ArrayIndexOutOfBoundsException {
		rotorArray[slot-1] = rotor;
	}
	
	//Returns a rotor at a given slot
	//Throws a error if you try to address a slot outside the bounds of the rotor array. 
	public BasicRotor getRotor(int slot) throws ArrayIndexOutOfBoundsException {
		return rotorArray[slot-1];
	}
	
	//Adds a reflector the enigma machine
	public void addReflector(Reflector reflector) {
		this.reflector = reflector;
	}
	
	//Returns the reflector of the enigma machine
	public Reflector getReflector() {
		return reflector;
	}
	
	//Sets the position of a rotor in a given slot
	//Throws a error if you try to address a slot outside the bounds of the rotor array. 
	//Uses slot - 1 to address the rotorArray as 1-3 as a slot number makes more sense than 0-2
	public void setPosition(int slot, int position) throws ArrayIndexOutOfBoundsException {
		rotorArray[slot-1].setPosition(position);
	}
	
	//A method used for the extension.
	//If the enigma machine contains null values then it cannot run without causing a error and therefore should not run
	public boolean isComplete() {
		if(rotorArray[0] != null && rotorArray[1] != null && rotorArray[1] != null && reflector != null && plugboard != null) {
			return true;
		}
		
		return false;
	}
}