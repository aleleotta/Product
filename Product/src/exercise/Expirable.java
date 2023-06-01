package exercise;

public class Expirable extends Product {
	private int expireDays = 0;
	
	public Expirable(String name, double price, int expireDays) {
		super(name, price);
		if(expireDays > 0) {
			this.expireDays = expireDays;
		}
	}
	
	@Override
	public String toString() {
		return super.toString() + "\nExpiration days left: " + this.expireDays;
	}
	
	@Override
	public double calculate(int quantity) {
		double totalPrice = 0;
		totalPrice = super.calculate(quantity);
		if(this.expireDays == 3) {
			totalPrice = totalPrice / 2;
		} else if(this.expireDays == 2) {
			totalPrice = totalPrice / 3;
		} else if(this.expireDays == 1) {
			totalPrice = totalPrice / 4;
		}
		return totalPrice;
	}
	
}