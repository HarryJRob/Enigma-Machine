public class Plug {

	private char end1;
	private char end2;
	
	public Plug(char end1, char end2) {
		this.end1 = end1;
		this.end2 = end2;
	}
	
	public char encode(char letterIn) {
		if(end1 == letterIn) {
			return end2; 
		} else if(end2 == letterIn) {
			return end1;
		}
		
		return letterIn;
	}
	
	public boolean clashesWith(Plug plugIn) {
		if(plugIn.getEnd1() == end1 || plugIn.getEnd2() == end1 || plugIn.getEnd1() == end2 || plugIn.getEnd2() == end2) {
			return true;
		}
		return false;
	}
	
	public void setEnd1(char end1) {
		this.end1 = end1;
	}
	
	public void setEnd2(char end2) {
		this.end2 = end2;
	}
	
	public char getEnd1() {
		return end1;
	}
	
	public char getEnd2() {
		return end2;
	}
}