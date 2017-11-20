import java.util.LinkedList;

public class Plugboard {

	private LinkedList<Plug> plugList =  new LinkedList<Plug>();
	
	public boolean addPlug(char end1, char end2) { 
		if(plugList.size() < 14) {
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
	
	public char substitute(char charIn) {
		for(Plug curPlug : plugList) {
			char encodedChar = curPlug.encode(charIn);
			if(charIn != encodedChar) {
				return encodedChar;
			}
		}
		return charIn;
	}
	
	public int getNumPlugs() {
		return plugList.size();
	}
	
	public void clear() {
		plugList.clear();
	}
}
