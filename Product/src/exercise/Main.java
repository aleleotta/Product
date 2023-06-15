package exercise;
import java.util.*;
import java.io.*;

/**
 * Main class responsible for the execution of the program.
 * @author Alessandro Leotta
 */
public class Main {
	static TreeSet<Product> list = new TreeSet<Product>(); //Creation of list (TreeSet).
	static Scanner sc = new Scanner(System.in); //Input scanner declared.
	public static void main(String[] args) {
		list = read();
		int option = 10; //Functionality decision input.
		while(option != 0) { //Creation of while loop containing CRUD.
			menu(); //Menu printing at beginning of while loop.
			option = sc.nextInt();
			sc.nextLine();
			switch(option) { //Program decides which functionality to use based on option input.
			case 1: //INSERT
				insert(sc, list); //Call to extracted insert method.
				break;
			case 2: //READ
				for(Product instance: list) { //Foreach loop responsible for reading each product within list.
					System.out.println(instance.toString());
					System.out.println();
				}
				break;
			case 3: //MODIFY
				modify(sc, list);
				break;
			case 4: //DELETE
				delete(sc, list); //Call to extracted delete method.
				break;
			case 5:
				save();
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
		System.out.println("3) Modify product");
		System.out.println("4) Delete product");
		System.out.println("5) Save products");
		System.out.println("0) Exit");
		System.out.println();
		System.out.print("Option: ");
	}
	
	/**
	 * This method is responsible for keeping previously saved data on the previous execution of the program.
	 * @return Returns updated list with all previously saved data.
	 */
	public static TreeSet<Product> read() {
		BufferedReader reader = null;
		Product obj = null;
		try {
			reader = new BufferedReader(new FileReader("src\\exercise\\Products.txt"));
			String line = reader.readLine();
				while(line != null) {
					String[] values = line.split(";"); //Parameter is used to determine when to split line. This will separate every value needed.
					String name = values[0];
					double price = Double.parseDouble(values[1]);
					try {
						int expireDays = Integer.parseInt(values[2]);
						obj = new Expirable(name, price, expireDays);
					} catch (NumberFormatException err) {
						String type = values[2];
						obj = new NotExpirable(name, price, type);
					} finally {
						list.add(obj);
					}
					line = reader.readLine();
				}
		} catch (FileNotFoundException err) {
			System.out.println("Failed to generate writer!");
		} catch (IOException err) {
			System.out.println("Something went wrong");
			System.out.println(err.getMessage());
		} finally {
			try {
				reader.close();
			} catch (IOException err) {
				System.out.println("Something went wrong while closing the reader!");
				System.out.println(err.getMessage());
			}
		}
		return list;
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
	public static void insert(Scanner sc, TreeSet<Product> list) {
		char option1 = 'z';
		Product obj = null;
		System.out.print("Introduce the product name: "); //New values input
		String name = sc.nextLine();
		System.out.print("Introduce the product price: ");
		Double price = sc.nextDouble();
		sc.nextLine();
		do { //Administrator needs to tell the program with a char value whether the new product is expirable or not.
			System.out.println("Is the following product expirable?");
			option1 = sc.nextLine().charAt(0);
		}
		while(!(option1 == 'Y' || option1 == 'y' || option1 == 'N' || option1 == 'n'));
		if(option1 == 'Y' || option1 == 'y') { //If the product is expirable.
			System.out.print("Introduce expiration days: ");
			int expireDays = sc.nextInt();
			sc.nextLine();
			obj = new Expirable(name, price, expireDays);
		} else { //If the product is not expirable.
			System.out.print("Introduce the type of product: ");
			String type = sc.nextLine();
			obj = new NotExpirable(name, price, type);
		}
		if(list.add(obj)) { //Checks if the product already exists and if not it adds the product to the list.
			System.out.println("The following product has been successfully added.");
		} else {
			System.out.println("The following product already exists!");
		}
	}
	
	/**
	 * The functionality responsible for modifying products within the list (TreeSet) checking if the following product exists first.
	 * @param name Name of the following product that the administrator wants to modify within the list.
	 * @param price New price for the product.
	 * @param expireDays Days left for the product to expire.
	 * @param type Type of new product in case it has no expiration date.
	 * @param obj Product to modify.
	 * @param sc Scanner that is responsible for taking keyboard values.
	 * @param list List that contains all products.
	 */
	public static void modify(Scanner sc, TreeSet<Product> list) {
		System.out.print("Introduce the name of the following product to modify: ");
		String name = sc.nextLine(); //Selection of product to modify.
		Product obj = new Product(name);
		if(list.contains(obj)) {
			for(Product instance: list) {
				if(instance.equals(obj)) {
					System.out.print("New price: ");
					double price = sc.nextDouble();
					sc.nextLine();
					instance.setPrice(price);
					if(instance instanceof Expirable) {
						System.out.print("Days to expire: ");
						int expireDays = sc.nextInt();
						sc.nextLine();
						((Expirable) instance).setExpireDays(expireDays);
					} else if(instance instanceof NotExpirable) {
						System.out.print("New type: ");
						String type = sc.nextLine();
						((NotExpirable) instance).setType(type);
					}
				}
			}
		}
	}
	
	/**
	 * The functionality responsible for deleting products from the list (TreeSet) and checks if the following product doesn't exist.
	 * @param name Name of the following product that the administrator wants to delete from the list.
	 * @param obj Product object that will be used to search and delete existing product from the list.
	 * @param sc Scanner that is responsible for taking keyboard value for name.
	 * @param list List that contains all products.
	 */
	public static void delete(Scanner sc, TreeSet<Product> list) {
		System.out.print("Introduce the name of the following product to delete: ");
		String name = sc.nextLine(); //Selection of product to delete.
		Product obj = new Product(name);
		if(list.remove(obj)) { //Checks if the product doesn't exist and if it does, it deletes the product from the list.
			System.out.println("The following product has been successfully deleted.");
		} else {
			System.out.println("The product name does not exist.");
		}
	}
	
	public static void save() {
		BufferedWriter writer = null; //Declaring writer as null.
		try {
			writer = new BufferedWriter(new FileWriter("src\\exercise\\Products.txt"));
			if(list.isEmpty()) { //If the list is empty, a message pops up and no task will be performed.
				System.out.println("No products have been saved because the list is empty!");
			} else {
				for(Product instance: list) { //Starts writing details of each product into the txt file.
					writer.flush(); //Commits data from writer to file before any other task is made.
					if(instance instanceof Expirable) { //Checks if the following product is expirable.
						writer.write(((Expirable) instance).getName() + ";" + ((Expirable) instance).getPrice() + ";" + ((Expirable) instance).getExpireDays() + "\n");
					} else if(instance instanceof NotExpirable) { //Checks if the following product is not expirable.
						writer.write(((NotExpirable) instance).getName() + ";" + ((NotExpirable) instance).getPrice() + ";" + ((NotExpirable) instance).getType() + "\n");
					}
				}
				System.out.println("The following products have been added successfully to the list."); //Final message after all data has been written.
			}
		} catch (IOException err) {
			System.out.println("Failed to generate writer!");
			System.out.println(err.getMessage());
		} finally { //Cleanup
			try {
				writer.close(); //Writer closes.
			} catch (IOException err) {
				System.out.println("Error while closing writer.");
				System.out.println(err.getMessage());
			}
		}
	}
	
}