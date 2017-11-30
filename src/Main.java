
public class Main {

	public static void main(String[] args) {
		EnigmaMachine myEM =  new EnigmaMachine();
		
		myEM.start();

		System.out.println("\nEnigma File Tests: \n");
		
		EnigmaFile.encodeFile("TestEncode.txt", "TestEncodeOut.txt");
		
	}

}
