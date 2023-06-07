package exercise;

/**
 * Class responsible for products that have an expiration date.
 * @author Alessandro Leotta
 */
public class Expirable extends Product {
	/**
	 * Days left for product expiration.
	 */
	private int expireDays = 0;
	/**
	 * Constructor that creates an expirable product object.
	 * @param name Identifies the product.
	 * @param price Determines how much the product costs.
	 * @param expireDays Days left for expiration of the product.
	 */
	public Expirable(String name, double price, int expireDays) {
		super(name, price);
		if(expireDays > 0) {
			this.expireDays = expireDays;
		}
	}
	
	public int getExpireDays() {
		return expireDays;
	}
	public void setExpireDays(int expireDays) {
		if(expireDays > 0) {
			this.expireDays = expireDays;
		}
	}
	
	/**
	 * Prints secondary attributes for an expirable product.
	 */
	@Override
	public String toString() {
		return super.toString() + "\nExpiration days left: " + this.expireDays;
	}
	/**
	 * Multiplies the quantity of a certain product by its price
	 * and then determines how much to divide the total price depending on its expiration date.
	 */
	@Override
	public double calculate(int quantity) {
		double totalPrice = 0;
		totalPrice = super.calculate(quantity); //Calls the method from Product class
		if(this.expireDays == 3) { //3 days left case
			totalPrice = totalPrice / 2; //Divides total price by 2.
		} else if(this.expireDays == 2) { //2 days left case
			totalPrice = totalPrice / 3; //Divides total price by 3.
		} else if(this.expireDays == 1) { //1 days left case
			totalPrice = totalPrice / 4; //Divides total price by 4.
		}
		return totalPrice;
	}
	
}