import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) {
		//Create a new Enigma Machine
		EnigmaMachine myEM =  new EnigmaMachine();
		
		//Run the tests specified in part 5
		myEM.start();

		System.out.println("\nEnigma File Tests: \n");
		
		//Run a test to confirm that Enigma File is working
		EnigmaFile.encodeFile("TestEncode.txt", "TestEncodeOut.txt");
		
		System.out.println("\nBombe Tests\n");
		
		//Run the basic decode tests
		Bombe.test1();
		Bombe.test2();
		Bombe.test3();
		
		//Extensions
		System.out.println("\nExtensions");
		System.out.println("Typing strings into the command line will encrypt them with the current settings");
		System.out.println("Type !help for the command list\n");
		
		boolean willExit = false;

		BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));
		String curInput = "";
		
		while(!willExit) {
		
			try {
				curInput = inputReader.readLine();
			} catch (IOException e) {
				System.out.println("Error while reading from the console: " + e.getMessage());
			}
			
			//If the first character is a ! then this is a command
			if(curInput.charAt(0) == '!') {
				
				if(curInput.matches("!help")) {
					//Displays all commands
					System.out.println("Commands:");
					System.out.println("\t!addPlug <Character> <Character>");
					System.out.println("\t!addRotor <Rotor number: 1 to 3> <Rotor type: I to V>");
					System.out.println("\t!addReflector <I to V>");
					System.out.println("\t!setRotorPosition <Rotor number: 1 to 3> <Rotor Position: 1 to 26>");
					System.out.println("\t!clearPlugs");
					System.out.println("\t!encodeFile <Input Path> <Output Path>");
					System.out.println("\t!exit");
					
				} else if (curInput.matches("!addPlug [A-Z] [A-Z]")) {
					//Adds a plug to the plug board
					String[] stringParts = curInput.split(" ");
					
					myEM.addPlug(stringParts[1].charAt(0), stringParts[2].charAt(0));
					
				} else if (curInput.matches("!addRotor [1-3] [I|II|III|IV|V]")) {
					//Adds a rotor to the EM
					String[] stringParts = curInput.split(" ");
					
					myEM.addRotor(new BasicRotor(stringParts[2]), Integer.parseInt(stringParts[1]));
					
				} else if (curInput.matches("!addReflector [I|II]")) {
					//Adds a reflector to the EM!
					String[] stringParts = curInput.split(" ");
					
					myEM.addReflector(new Reflector(stringParts[1]));
					
				} else if (curInput.matches("!clearPlugs")) {
					//Clears the plugboard
					myEM.clearPlugboard();
					
				} else if (curInput.matches("!setRotorPosition [1-3] [0-9]+")) {
					//Set the position of a given rotor
					String[] stringParts = curInput.split(" ");
					
					if(Integer.parseInt(stringParts[2]) <= 26 && Integer.parseInt(stringParts[2]) >= 1)
						myEM.setPosition(Integer.parseInt(stringParts[1]), Integer.parseInt(stringParts[2]) - 1);
					
				} else if (curInput.matches("!encodeFile [A-Za-z0-9_\\\\:\\.]+ [A-Za-z0-9_\\\\:\\.]+")) {
					//Encode a given file and save it to a given output
					String[] stringParts = curInput.split(" ");
					
					EnigmaFile.encodeFile(stringParts[1], stringParts[2], myEM);
				} else if (curInput.matches("!exit")) {
					willExit = true;
					
				}
				
			} else {
				
				String output = "";
				//If the entry isn't a command
				for(char curChar : curInput.toCharArray()) {
					try {
						output += myEM.encodeLetter(curChar);
					} catch (ArrayIndexOutOfBoundsException e) {
						output += "?";
					}
				}
				
				System.out.println(output);
			}
		}
		
	}

}
