import java.util.LinkedList;

//Part 1 of the coursework
public class Plugboard {

	//A list of all plugs
	private LinkedList<Plug> plugList =  new LinkedList<Plug>();
	
	//A method to add a plug to the plugboard
	//This can only happen if the number of plugs is less than 13 and no other plugs clash with it
	//We make it return a variable here but never use it later?
	public boolean addPlug(char end1, char end2) { 
		if(plugList.size() < 13) {
			Plug tempPlug =  new Plug(end1,end2);
			
			boolean hasClash = false;
			for(Plug curPlug : plugList) {
				if(curPlug.clashesWith(tempPlug)) {
					hasClash = true;
				}
			}
			
			if(!hasClash) {
				plugList.add(tempPlug);
				return true;
			}
		}
		return false;
	}
	
	//For each plug attempt to encode a given character. If the encoded character doesn't equal the input character return it.
	//If you reach the end of the possible plugs without it being encoded just return the input
	public char substitute(char charIn) {
		for(Plug curPlug : plugList) {
			char encodedChar = curPlug.encode(charIn);
			if(charIn != encodedChar) {
				return encodedChar;
			}
		}
		return charIn;
	}
	
	//Return the current number of plugs in the plugList
	public int getNumPlugs() {
		return plugList.size();
	}
	
	//Clears the plugList
	public void clear() {
		plugList.clear();
	}
}
