package exercise;

public class NotExpirable extends Product {
	private String type = "";
	
	public NotExpirable(String name, double price, String type) {
		super(name, price);
		if(type != null && !type.equals("")) {
			this.type = type;
		}
	}
	
	@Override
	public String toString() {
		return super.toString() + "\nType: " + this.type;
	}
	
}