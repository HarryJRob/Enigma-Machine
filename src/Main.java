
public class Main {

	public static void main(String[] args) {
		EnigmaMachine myEM =  new EnigmaMachine();
		
		myEM.start();

		System.out.println("\nEnigma File Tests: \n");
		
		EnigmaFile.encodeFile("C:\\Users\\Harry\\git\\Enigma-Machine\\TestEncode.txt", "C:\\Users\\Harry\\git\\Enigma-Machine\\TestEncodeOut.txt");
		
	}

}
