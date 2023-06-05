package exercise;
import java.util.*;

/**
 * Main class responsible for the execution of the program.
 * @author Alessandro Leotta
 */
public class Main {
	public static void main(String[] args) {
		TreeSet<Product> list = new TreeSet<Product>(); //Creation of list (TreeSet).
		Scanner sc = new Scanner(System.in); //Input scanner declared.
		Product obj = null; //Object that will be used for managing products inside list (TreeSet).
		String name = ""; //Declaration of variables necessary for product management.
		double price = 0;
		int expireDays = 0;
		String type = "";
		int option = 10; //Functionality decision input.
		while(option != 0) { //Creation of while loop containing CRUD.
			menu(); //Menu printing at beginning of while loop.
			option = sc.nextInt();
			sc.nextLine();
			switch(option) { //Program decides which functionality to use based on option input.
			case 1: //INSERT
				insert(type, price, option, type, obj, sc, list); //Call to extracted insert method.
				break;
			case 2: //READ
				for(Product instance: list) { //Foreach loop responsible for reading each product within list.
					System.out.println(instance.toString());
					System.out.println();
				}
				break;
			case 3: //DELETE
				delete(type, obj, sc, list); //Call to extracted delete method.
				break;
			case 0: //EXIT
				System.out.println("Exiting program...");
				break;
			default: //ERROR
				System.out.println("Please introduce a valid option.");
				break;
			}
		}
		sc.close(); //Input scanner closes.
	}
	
	/**
	 * Menu that gets printed every time the administrator has to choose a functionality.
	 */
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
	
	/**
	 * The functionality responsible for inserting products into the list (TreeSet) and checks if the following product already exists.
	 * @param name New name for the product.
	 * @param price New price for the product.
	 * @param expireDays Days left for the new product to expire.
	 * @param type Type of new product in case it has no expiration date.
	 * @param obj The object that will later be assigned with entered by keyboard values and will later be added to the list.
	 * @param sc Scanner that is responsible for taking keyboard values and assign them to new attributes.
	 * @param list List that contains all products.
	 */
	public static void insert(String name, double price, int expireDays, String type, Product obj, Scanner sc, TreeSet<Product> list) {
		char option1 = 'z';
		System.out.print("Introduce the product name: "); //New values input
		name = sc.nextLine();
		System.out.print("Introduce the product price: ");
		price = sc.nextDouble();
		sc.nextLine();
		do { //Administrator needs to tell the program with a char value whether the new product is expirable or not.
			System.out.println("Is the following product expirable?");
			option1 = sc.nextLine().charAt(0);
		}
		while(!(option1 == 'Y' || option1 == 'y' || option1 == 'N' || option1 == 'n'));
		if(option1 == 'Y' || option1 == 'y') { //If the product is expirable.
			System.out.print("Introduce expiration days: ");
			expireDays = sc.nextInt();
			sc.nextLine();
			obj = new Expirable(name, price, expireDays);
		} else { //If the product is not expirable.
			System.out.print("Introduce the type of product: ");
			type = sc.nextLine();
			obj = new NotExpirable(name, price, type);
		}
		if(list.add(obj)) { //Checks if the product already exists and if not it adds the product to the list.
			System.out.println("The following product has been successfully added.");
		} else {
			System.out.println("The following product already exists!");
		}
	}
	
	/**
	 * The functionality responsible for deleting products from the list (TreeSet) and checks if the following product doesn't exist.
	 * @param name Name of the following product that the administrator wants to delete from the list.
	 * @param obj Product object that will be used to search and delete existing product from the list.
	 * @param sc Scanner that is responsible for taking keyboard value for name.
	 * @param list List that contains all products.
	 */
	public static void delete(String name, Product obj, Scanner sc, TreeSet<Product> list) {
		System.out.print("Introduce the name of the following product to delete: ");
		name = sc.nextLine(); //Selection of product to delete.
		obj = new Product(name);
		if(list.remove(obj)) { //Checks if the product doesn't exist and if it does, it deletes the product from the list.
			System.out.println("The following product has been successfully deleted.");
		} else {
			System.out.println("The product name does not exist.");
		}
	}
	
}