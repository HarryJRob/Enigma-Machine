import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class EnigmaFile {

	public String loadFile(String inputPath) throws IOException {
		String outputString = "";
		
		BufferedReader br = new BufferedReader(new FileReader("file.txt"));
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

	public void saveFile(String outputPath, String file) {
		
	}
}
