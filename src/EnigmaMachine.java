
public class EnigmaMachine {
	
	private Plugboard plugboard;
	private Reflector reflector;
	private BasicRotor[] rotorArray = new BasicRotor[3];
	
	private final char[] charMapping = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
	
	public EnigmaMachine() {
		plugboard = new Plugboard();
	}
	
	public char encodeLetter(char inputChar) throws ArrayIndexOutOfBoundsException {
		inputChar = plugboard.substitute(Character.toUpperCase(inputChar));
		
		int charValue = -1;
		
		for(int i = 0; i < 26; i++) {
			if(charMapping[i] == inputChar) {
				charValue = i;
				break;
			}
		}
		
		for(int i = 0; i < 3; i++) {
			charValue = rotorArray[i].substitute(charValue);
		}
		
		charValue = reflector.substitute(charValue);
		
		for(int i = 2; i > -1; i--) {
			charValue = rotorArray[i].substituteBack(charValue);
		}
		
		rotorArray[0].rotate();
		
		return plugboard.substitute(charMapping[charValue]);
	}

	public void start() {
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
		
		System.out.print("Output String: ");
		for(int i = 0; i < inputString.length(); i++) {
			System.out.print(encodeLetter(inputString.charAt(i)));
		}
		
		System.out.println();
		
		plugboard.clear();
		
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
		
		System.out.print("Output String: ");
		for(int i = 0; i < inputString.length(); i++) {
			System.out.print(encodeLetter(inputString.charAt(i)));
		}
		
		System.out.println();
		
		plugboard.clear();
		
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
		
		System.out.print("Output String: ");
		for(int i = 0; i < inputString.length(); i++) {
			System.out.print(encodeLetter(inputString.charAt(i)));
		}
		
		System.out.println();
	}
	
	public void addPlug(char end1, char end2) {
		plugboard.addPlug(end1, end2);
	}
	
	public void clearPlugboard() {
		plugboard.clear();
	}
	
	public void addRotor(BasicRotor rotor, int slot) throws ArrayIndexOutOfBoundsException {
		rotorArray[slot-1] = rotor;
	}
	
	public BasicRotor getRotor(int slot) {
		return rotorArray[slot-1];
	}
	
	public void addReflector(Reflector reflector) {
		this.reflector = reflector;
	}
	
	public Reflector getReflector() {
		return reflector;
	}
	
	public void setPosition(int slot, int position) throws ArrayIndexOutOfBoundsException {
		rotorArray[slot-1].setPosition(position);
	}
}