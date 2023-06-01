package exercise;

public class Product implements Comparable<Product> {
	protected String name = "";
	protected double price = 0;
	
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
	
	@Override
	public String toString() {
		return "Name: " + this.name + "\nPrice: " + this.price;
	}
	
	public double calculate(int quantity) {
		double totalPrice = 0;
		totalPrice = quantity * this.price;
		return totalPrice;
	}
	
	@Override
	public int compareTo(Product obj) {
		int check = this.name.compareTo(obj.name);
		return check;
	}
	
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