//A class which contains all the tests for Part 8
public abstract class Bombe {

	//The first test in part 8
	public static void test1() {
		System.out.println("Test 1");
		
		EnigmaMachine myEM = new EnigmaMachine();
		//Arrays of possible plug ends
		char[] plug1CharArray = "ABCEFGHIFKLMNOPQRSTUVWXYZ".toCharArray();
		char[] plug2CharArray = "ABCDEFGHIFKLMNOPQRTUVWXYZ".toCharArray();
		String inputString = "JBZAQVEBRPEVPUOBXFLCPJQSYFJI";
		
		//Construct the Enigma machine
		myEM.addReflector(new Reflector("ReflectorI"));
		myEM.addRotor(new BasicRotor("IV"), 1);
		myEM.addRotor(new BasicRotor("III"), 2);
		myEM.addRotor(new BasicRotor("II"), 3);
		
		//For each plug
		for(char curSettingPlug1 : plug1CharArray) {	
			for(char curSettingPlug2 : plug2CharArray) {
				
				//Reset the enigma machine
				myEM.clearPlugboard();
				myEM.setPosition(1, 8);
				myEM.setPosition(2, 4);
				myEM.setPosition(3, 21);
				
				//Add the current possible settings to the plugboard
				myEM.addPlug('D', curSettingPlug1);
				myEM.addPlug('S', curSettingPlug2);

				//Since each character can be mapped to by only one other character they cannot have the same end 
				if(curSettingPlug2 != curSettingPlug1) {
					String output = "";
					//Encode the input string to produce a output
					for(char curChar : inputString.toCharArray()) {
						try {
							output += myEM.encodeLetter(curChar);
						} catch (Exception e) {
							output += '?';
						}
					}
					
					//If it contains a given word output that as a possible answer
					if(output.contains("ANSWER")) {
						System.out.println("\nPossible Answer: " + output);
						System.out.println("[D-" + curSettingPlug1 + "][S-" + curSettingPlug2 + "]");
					}
				}
			}
		}
	}

	//The second test in part 8
	public static void test2() {
		System.out.println("\nTest 2");
		
		EnigmaMachine myEM = new EnigmaMachine();
		String inputString = "AVPBLOGHFRLTFELQEZQINUAXHTJMXDWERTTCHLZTGBFUPORNHZSLGZMJNEINTBSTBPPQFPMLSVKPETWFD";
		
		//Setup the enigma machine
		myEM.addPlug('H', 'L');
		myEM.addPlug('G', 'P');
		
		myEM.addRotor(new BasicRotor("V"), 1);
		myEM.addRotor(new BasicRotor("III"), 2);
		myEM.addRotor(new BasicRotor("II"), 3);
		myEM.addReflector(new Reflector("ReflectorI"));
		
		//For each possible position of the three rotors do
		for(int r1 = 0; r1 < 26; r1++) {
			for(int r2 = 0; r2 < 26; r2++) {
				for(int r3 = 0; r3 < 26; r3++) {
					myEM.setPosition(1, r1);
					myEM.setPosition(2, r2);
					myEM.setPosition(3, r3);
					
					//Encode input string to produce a output
					String output = "";
					for(char curChar : inputString.toCharArray()) {
						try {
							output += myEM.encodeLetter(curChar);
						} catch (Exception e) {
							output += '?';
						}
					}
					
					//If the output contains ELECTRIC output it as a possible answer
					if(output.contains("ELECTRIC")) {
						System.out.println("\nPossible Answer: " + output);
						System.out.println("Position of rotor 1: " + r1);
						System.out.println("Position of rotor 2: " + r2);
						System.out.println("Position of rotor 3: " + r3);
					}
				}
			}
		}
	}
	
	//The third test in part 8
	public static void test3() {
		
		System.out.println("\nTest 3");
		
		EnigmaMachine myEM = new EnigmaMachine();
		String inputString = "WMTIOMNXDKUCQCGLNOIBUYLHSFQSVIWYQCLRAAKZNJBOYWW";
		
		//Set up the Enigma Machine
		myEM.addPlug('M', 'F');
		myEM.addPlug('O', 'I');
		myEM.addReflector(new Reflector("ReflectorI"));
		
		//For each rotor type (Represented as a integer)
		for(int r1 = 0; r1 < 5; r1 ++) {
			for(int r2 = 0; r2 < 5; r2 ++) {
				for(int r3 = 0; r3 < 5; r3 ++) {
					//Convert each rotor type as a integer to a rotor in the machine
					for(int curRotor = 1; curRotor < 4; curRotor++) {
						int typeNum = 0;
						
						switch(curRotor) {
						
						case 1: typeNum = r1; break;
						case 2: typeNum = r2; break;
						case 3: typeNum = r3; break;
						
						}
						
						switch(typeNum) {
						
						case 0: myEM.addRotor(new BasicRotor("I"), curRotor); break;
						case 1: myEM.addRotor(new BasicRotor("II"), curRotor); break;
						case 2: myEM.addRotor(new BasicRotor("III"), curRotor); break;
						case 3: myEM.addRotor(new BasicRotor("IV"), curRotor); break;
						case 4: myEM.addRotor(new BasicRotor("V"), curRotor); break;
						
						}
					}
					
					//Set the default positions
					myEM.setPosition(1, 22);
					myEM.setPosition(2, 24);
					myEM.setPosition(3, 23);
					
					//Encode the input string to get a output
					String output = "";
					for(char curChar : inputString.toCharArray()) {
						try {
							output += myEM.encodeLetter(curChar);
						} catch (Exception e) {
							output += '?';
						}
					}
					
					//If the output contains java output it as a possible answer
					if(output.contains("JAVA")) {
						System.out.println("\nPossible Answer: " + output);
						System.out.println("\tType of rotor 1: " + r1);
						System.out.println("\tType of rotor 2: " + r2);
						System.out.println("\tType of rotor 3: " + r3);
						System.out.println("");
					}
				}
			}
		}
	}
}
