public abstract class Rotor {

	protected String name;
	protected int position;
	protected int[] mapping;
	protected final int ROTORSIZE = 26;
	
	public abstract void initialise(String inputStr);
	
	public abstract int substitute(int inputInt);
	
	public void setPosition(int position) {
		this.position = position;
	}
	
	public int getPosition() {
		return position;
	}
}
