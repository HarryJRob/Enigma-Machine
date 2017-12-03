
public abstract class Bombe {

	public static void test1() {
		System.out.println("Test 1");
		
		EnigmaMachine myEM = new EnigmaMachine();
		char[] plug1CharArray = "ABCEFGHIFKLMNOPQRSTUVWXYZ".toCharArray();
		char[] plug2CharArray = "ABCDEFGHIFKLMNOPQRTUVWXYZ".toCharArray();
		String inputString = "JBZAQVEBRPEVPUOBXFLCPJQSYFJI";
		
		myEM.addReflector(new Reflector("ReflectorI"));
		myEM.addRotor(new BasicRotor("IV"), 1);
		myEM.addRotor(new BasicRotor("III"), 2);
		myEM.addRotor(new BasicRotor("II"), 3);
		
		for(char curSettingPlug1 : plug1CharArray) {	
			for(char curSettingPlug2 : plug2CharArray) {
				myEM.clearPlugboard();
				myEM.setPosition(1, 8);
				myEM.setPosition(2, 4);
				myEM.setPosition(3, 21);
				
				myEM.addPlug('D', curSettingPlug1);
				myEM.addPlug('S', curSettingPlug2);

				if(curSettingPlug2 != curSettingPlug1) {
					String output = "";
					for(char curChar : inputString.toCharArray()) {
						output += myEM.encodeLetter(curChar);
					}
					
					if(output.contains("ANSWER")) {
						System.out.println("\nPossible Answer: " + output);
						System.out.println("[D-" + curSettingPlug1 + "][S-" + curSettingPlug2 + "]");
					}
				}
			}
		}
	}

	public static void test2() {
		System.out.println("\nTest 2");
		
		EnigmaMachine myEM = new EnigmaMachine();
		String inputString = "AVPBLOGHFRLTFELQEZQINUAXHTJMXDWERTTCHLZTGBFUPORNHZSLGZMJNEINTBSTBPPQFPMLSVKPETWFD";
		
		myEM.addPlug('H', 'L');
		myEM.addPlug('G', 'P');
		
		myEM.addRotor(new BasicRotor("V"), 1);
		myEM.addRotor(new BasicRotor("III"), 2);
		myEM.addRotor(new BasicRotor("II"), 3);
		myEM.addReflector(new Reflector("ReflectorI"));
		
		for(int r1 = 0; r1 < 26; r1++) {
			for(int r2 = 0; r2 < 26; r2++) {
				for(int r3 = 0; r3 < 26; r3++) {
					myEM.setPosition(1, r1);
					myEM.setPosition(2, r2);
					myEM.setPosition(3, r3);
					
					String output = "";
					for(char curChar : inputString.toCharArray()) {
						output += myEM.encodeLetter(curChar);
					}
					
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
	
	public static void test3() {
		System.out.println("\nTest 3");
		
		EnigmaMachine myEM = new EnigmaMachine();
		String inputString = "WMTIOMNXDKUCQCGLNOIBUYLHSFQSVIWYQCLRAAKZNJBOYWW";
		
		myEM.addPlug('M', 'F');
		myEM.addPlug('O', 'I');
		myEM.addReflector(new Reflector("ReflectorI"));
		
		for(int r1 = 0; r1 < 5; r1 ++) {
			for(int r2 = 0; r2 < 5; r2 ++) {
				for(int r3 = 0; r3 < 5; r3 ++) {
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
					
					myEM.setPosition(1, 22);
					myEM.setPosition(2, 24);
					myEM.setPosition(3, 23);
					
					String output = "";
					for(char curChar : inputString.toCharArray()) {
						output += myEM.encodeLetter(curChar);
					}
					
					if(output.contains("JAVA")) {
						System.out.println("\nPossible Answer: " + output);
						System.out.println("");
					}
				}
			}
		}
	}
}
