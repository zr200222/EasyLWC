package zr2002.autolwc;

public class Lock {

	private String type;
	private String pos;
	
	public Lock(String type, String pos) {
		this.type = type;
		this.pos = pos;
	}
	
	public String getType() {
		return type;
	}
	
	public String getPos() {
		return pos;
	}
	
	public String toString() {
		return "1 " + type + " /tppos " + pos;
	}

}
