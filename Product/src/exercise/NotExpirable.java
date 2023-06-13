package exercise;

/**
 * Class responsible for products that don't have an expiration date.
 * @author Alessandro Leotta
 */
public class NotExpirable extends Product {
	/**
	 * Type of product
	 */
	private String type = "";
	
	/**
	 * Constructor that creates a not expirable product object.
	 * @param name Identifies the product.
	 * @param price Determines how much the product costs.
	 * @param type Type of product
	 */
	public NotExpirable(String name, double price, String type) {
		super(name, price);
		if(type != null && !type.equals("")) {
			this.type = type;
		}
	}
	/**
	 * Returns the type of product.
	 * @return Returns type.
	 */
	public String getType() {
		return type;
	}
	/**
	 * Sets a new type for one product.
	 * @param type Parameter that sets new value for the type of product.
	 */
	public void setType(String type) {
		if(!type.equals("")) {
			this.type = type;
		}
	}

	/**
	 * Prints secondary attributes for a not expirable product.
	 */
	@Override
	public String toString() {
		return super.toString() + "\nType: " + this.type;
	}
	
}