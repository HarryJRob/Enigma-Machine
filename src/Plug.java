//Part 1 of the coursework
//Provides all implementation of the plug class
public class Plug {

	//Internal variables defining each end of the plug
	private char end1;
	private char end2;
	
	//Constructor for Plug
	public Plug(char end1, char end2) {
		this.end1 = end1;
		this.end2 = end2;
	}
	
	//Encode method which translates end1 to end2 and end2 to end1 if the input char matches end1 or end2 respectively
	public char encode(char letterIn) {
		if(end1 == letterIn) {
			return end2; 
		} else if(end2 == letterIn) {
			return end1;
		}
		
		return letterIn;
	}
	
	//Checks if a given plug has ends which conflicts with this plug
	public boolean clashesWith(Plug plugIn) {
		if(plugIn.getEnd1() == end1 || plugIn.getEnd2() == end1 || plugIn.getEnd1() == end2 || plugIn.getEnd2() == end2) {
			return true;
		}
		return false;
	}
	
	//Mutator for end1
	public void setEnd1(char end1) {
		this.end1 = end1;
	}
	
	//Mutator for end2
	public void setEnd2(char end2) {
		this.end2 = end2;
	}
	
	//Getter for end1
	public char getEnd1() {
		return end1;
	}
	
	//Getter for end2
	public char getEnd2() {
		return end2;
	}
}