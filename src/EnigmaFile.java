import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

//Provides all of the code required for part 6
//Abstract as all this class does is run one function with no internal variables
//All methods are static as they are not dependent on internal variables and do not need to be instantiated to be used 
public abstract class EnigmaFile {

	//Loads a file as a single long string.
	private static String loadFile(String inputPath) throws IOException {
		String outputString = "";
		BufferedReader br = new BufferedReader(new FileReader(inputPath));
		String curLine = "";
		
		while(curLine != null) {
			curLine = br.readLine();
			if(curLine != null) {
				outputString += curLine;
			}
		}
		
		br.close();
		
		return outputString;
	}

	//Saves a single string to a file.
	private static void saveFile(String outputPath, String file) throws IOException {
		BufferedWriter bw = new BufferedWriter(new FileWriter(outputPath));
		bw.write(file);
		bw.close();
	}

	//Attempts to encode a file with a basic enigma machine
	public static void encodeFile(String inputPath, String outputPath) {
		try {
			//Loads a file to a string
			System.out.println("Loading File: " + inputPath);
			String fileStr = loadFile(inputPath);

			System.out.println("Encoding: " + fileStr);
			String outputStr = "";
			
			//Construct a enigma machine to encode with
			EnigmaMachine EM = new EnigmaMachine();
			
			EM.addRotor(new BasicRotor("I"), 1);
			EM.addRotor(new BasicRotor("II"), 2);
			EM.addRotor(new BasicRotor("III"), 3);
			
			Reflector reflector = new Reflector("ReflectorI");

			EM.addReflector(reflector);
			
			//Encode the string
			for(int i = 0; i < fileStr.length(); i++) {
				try {
					outputStr += EM.encodeLetter(fileStr.charAt(i));
				} catch (Exception e) {
					//If the encoding fails add '?' to the output for that character
					outputStr += '?';
				}
			}
			
			//Save the file
			System.out.println("Saving File: " + outputStr);
			saveFile(outputPath,outputStr);
			
			//Error handling
		} catch(IOException e) {
			System.out.println("Error while saving or loading the file");
			System.out.println(e.getMessage());
			
		} catch(ArrayIndexOutOfBoundsException e) {
			System.out.println("Unrecognised character in string");
			System.out.println(e.getMessage());
			
		} catch (Exception e) {
			System.out.println("Unknown Exception: ");
			e.printStackTrace();
		}
		
	}
	
	//Encodes a file with a given enigma machine
	public static void encodeFile(String inputPath, String outputPath, EnigmaMachine EM) {
		try {
			//Load the file to a string
			System.out.println("Loading File: " + inputPath);
			String fileStr = loadFile(inputPath);

			System.out.println("Encoding: " + fileStr);
			String outputStr = "";
			
			//Encode the string with the enigma machine given
			for(int i = 0; i < fileStr.length(); i++) {
				try {
					outputStr += EM.encodeLetter(fileStr.charAt(i));
				} catch (Exception e) {
					//If the encoding fails add '?' to the output for that character
					outputStr += '?';
				}
			}
			
			//Save the string to a file
			System.out.println("Saving File: " + outputStr);
			saveFile(outputPath,outputStr);
			
			//Error handling
		} catch(IOException e) {
			System.out.print("Error while saving or loading the file: ");
			System.out.println(e.getMessage());
			
		} catch(ArrayIndexOutOfBoundsException e) {
			System.out.print("Error while encoding: ");
			System.out.println(e.getMessage());
			
		} catch (Exception e) {
			System.out.print("Unhandled Exception: ");
			e.printStackTrace();
		}
		
	}
}
