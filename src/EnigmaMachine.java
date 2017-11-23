
public class EnigmaMachine {
	
	private Plugboard plugboard;
	private Reflector reflector;
	private BasicRotor[] rotorArray = new BasicRotor[3];
	
	private final char[] charMapping = "abcdefghijklmnopqrstuvwxyz".toCharArray();
	
	public EnigmaMachine() {
		plugboard = new Plugboard();
	}
	
	public char encodeLetter(char inputChar) throws ArrayIndexOutOfBoundsException {
		inputChar = plugboard.substitute(Character.toLowerCase(inputChar));
		
		int charValue = -1;
		
		for(int i = 0; i < 26; i++) {
			if(charMapping[i] ==  inputChar) {
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
		BasicRotor rotor1 = new BasicRotor();
		BasicRotor rotor2 = new BasicRotor();
		BasicRotor rotor3 = new BasicRotor();
		Reflector reflector =  new Reflector();
		String inputString;
		
		System.out.println("Test 1\n");
		inputString = "GFWIQH";
		
		System.out.println("Input String: " + inputString);
		
		addPlug('a', 'm');
		addPlug('g', 'l');
		addPlug('e', 't');
		
		rotor1.initialise("I");
		rotor2.initialise("II");
		rotor3.initialise("III");
		reflector.initialise("ReflectorI");
		
		rotor1.setPosition(6);
		rotor2.setPosition(12);
		rotor3.setPosition(5);
		
		addRotor(rotor1,1);
		addRotor(rotor2,2);
		addRotor(rotor3,3);
		addReflector(reflector);
		
		for(int i = 0; i < inputString.length(); i++) {
			System.out.print(encodeLetter(inputString.charAt(i)));
		}
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
