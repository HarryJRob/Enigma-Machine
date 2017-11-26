import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public abstract class EnigmaFile {

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

	private static void saveFile(String outputPath, String file) throws IOException {
		BufferedWriter bw = new BufferedWriter(new FileWriter(outputPath));
		bw.write(file);
		bw.close();
	}

	public static void encodeFile(String inputPath, String outputPath) {
		try {
			System.out.println("Loading File: " + inputPath);
			String fileStr = loadFile(inputPath);

			System.out.println("Encoding: " + fileStr);
			String outputStr = "";
			
			EnigmaMachine EM = new EnigmaMachine();
			
			EM.addRotor(new BasicRotor("I"), 1);
			EM.addRotor(new BasicRotor("II"), 2);
			EM.addRotor(new BasicRotor("III"), 3);
			
			Reflector reflector = new Reflector();
			reflector.initialise("ReflectorI");
			EM.addReflector(reflector);
			
			for(int i = 0; i < fileStr.length(); i++) {
				outputStr += EM.encodeLetter(fileStr.charAt(i));
			}
			
			System.out.println("Saving File: " + outputStr);
			saveFile(outputPath,outputStr);
			
		} catch(IOException e) {
			System.out.println("Error while saving or loading the file");
			System.out.println(e.getMessage());
			
		} catch(ArrayIndexOutOfBoundsException e) {
			System.out.println("Unrecognised character in string");
			System.out.println(e.getMessage());
			
		} catch (Exception e) {
			System.out.println("Unhandled Exception: ");
			e.printStackTrace();
		}
		
	}
	
	public static void encodeFile(String inputPath, String outputPath, EnigmaMachine EM) {
		try {
			System.out.println("Loading File: " + inputPath);
			String fileStr = loadFile(inputPath);

			System.out.println("Encoding: " + fileStr);
			String outputStr = "";
			
			for(int i = 0; i < fileStr.length(); i++) {
				outputStr += EM.encodeLetter(fileStr.charAt(i));
			}
			
			System.out.println("Saving File: " + outputStr);
			saveFile(outputPath,outputStr);
			
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
