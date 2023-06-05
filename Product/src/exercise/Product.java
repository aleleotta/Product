package exercise;
/**
 * Product class responsible for main components and object creation within the program.
 * @author Alessandro Leotta
 */
public class Product implements Comparable<Product> {
	/**
	 * Name attribute used for any product.
	 * Identifies the product.
	 * This attribute can be inherited.
	 */
	protected String name = "";
	/**
	 * Price attribute used for any product.
	 * Determines how much a product costs.
	 * This attribute can be inherited.
	 */
	protected double price = 0;
	
	/**
	 * Product constructor which creates a product with just the name.
	 * @param name Identifies product.
	 */
	public Product(String name) {
		if(name != null && !name.equals("")) {
			this.name = name;
		}
	}
	
	/**
	 * Product constructor which defines everything for a certain product.
	 * @param name Identifies product.
	 * @param price Determines how much the product costs.
	 */
	public Product(String name, double price) {
		if(name != null && !name.equals("")) {
			this.name = name;
		}
		if(price > 0) {
			this.price = price;
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		if(name != null && !name.equals("")) {
			this.name = name;
		}
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		if(price > 0) {
			this.price = price;
		}
	}
	
	/**
	 * Prints main attributes of a product.
	 */
	@Override
	public String toString() {
		return "Name: " + this.name + "\nPrice: " + this.price;
	}
	
	/**
	 * Multiplies the quantity of a certain product by its price.
	 * @param quantity Amount of units for one specific product.
	 * @return Returns the total price.
	 */
	public double calculate(int quantity) {
		double totalPrice = 0;
		totalPrice = quantity * this.price;
		return totalPrice;
	}
	
	/**
	 * Determines the TreeSet criteria for identifying a certain product.
	 */
	@Override
	public int compareTo(Product obj) {
		int check = this.name.compareTo(obj.name);
		return check;
	}
	
	/**
	 * Checks if two products are equal.
	 */
	@Override
	public boolean equals(Object obj) {
		boolean check = false;
		Product prod = (Product) obj;
		if(this.name.equals(prod.name)) {
			check = true;
		}
		return check;
	}
	
}