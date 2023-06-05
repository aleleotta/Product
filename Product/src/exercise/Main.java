package exercise;
import java.util.*;

public class Main {
	public static void main(String[] args) {
		TreeSet<Product> list = new TreeSet<Product>();
		Scanner sc = new Scanner(System.in);
		Product obj = null;
		String name = "";
		double price = 0;
		int expireDays = 0;
		String type = "";
		int option = 10;
		while(option != 0) {
			menu();
			option = sc.nextInt();
			sc.nextLine();
			switch(option) {
			case 1: //INSERT
				insert(type, price, option, type, obj, sc, list);
				break;
			case 2: //READ
				for(Product instance: list) {
					System.out.println(instance.toString());
					System.out.println();
				}
				break;
			case 3: //DELETE
				delete(type, obj, sc, list);
				break;
			case 0: //EXIT
				System.out.println("Exiting program...");
				break;
			default: //ERROR
				System.out.println("Please introduce a valid option.");
				break;
			}
		}
		sc.close();
	}
	
	public static void menu() {
		System.out.println("MENU");
		System.out.println("====================================================");
		System.out.println("1) Add product");
		System.out.println("2) Print list of products");
		System.out.println("3) Delete product");
		System.out.println("0) Exit");
		System.out.println();
		System.out.print("Option: ");
	}
	
	public static void insert(String name, double price, int expireDays, String type, Product obj, Scanner sc, TreeSet<Product> list) {
		char option1 = 'z';
		System.out.print("Introduce the product name: ");
		name = sc.nextLine();
		System.out.print("Introduce the product price: ");
		price = sc.nextDouble();
		sc.nextLine();
		do {
			System.out.println("Is the following product expirable?");
			option1 = sc.nextLine().charAt(0);
		}
		while(!(option1 == 'Y' || option1 == 'y' || option1 == 'N' || option1 == 'n'));
		if(option1 == 'Y' || option1 == 'y') {
			System.out.print("Introduce expiration days: ");
			expireDays = sc.nextInt();
			sc.nextLine();
			obj = new Expirable(name, price, expireDays);
		} else {
			System.out.print("Introduce the type of product: ");
			type = sc.nextLine();
			obj = new NotExpirable(name, price, type);
		}
		if(list.add(obj)) {
			System.out.println("The following product has been successfully added.");
		} else {
			System.out.println("The following product already exists!");
		}
	}
	
	public static void delete(String name, Product obj, Scanner sc, TreeSet<Product> list) {
		System.out.print("Introduce the name of the following product to delete: ");
		name = sc.nextLine();
		obj = new Product(name);
		if(list.remove(obj)) {
			System.out.println("The following product has been successfully deleted.");
		} else {
			System.out.println("The product name does not exist.");
		}
	}
	
}