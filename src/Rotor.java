//A abstract super class for all types of rotor or reflector
public abstract class Rotor {

	//Internal variables
	//name is never used nor is it accessible
	protected String name;
	//position isn't used by reflector
	protected int position;
	protected int[] mapping;
	protected final int ROTORSIZE = 26;
	
	//Initialises a rotor when given the type of rotor 
	public abstract void initialise(String inputStr);
	
	//Maps a input int to it's position in mapping
	public abstract int substitute(int inputInt);
	
	//Sets the position of the rotor
	public void setPosition(int position) {
		this.position = position;
	}
	
	//Returns the position of the rotor
	public int getPosition() {
		return position;
	}
}
