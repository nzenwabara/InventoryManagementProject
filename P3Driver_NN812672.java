/*
 * ICSI 201 Introduction to Computer Science
 * Fall 2023
 * Monday, 11:40 am
 * Nzeadibe Nwabara
 * NN812672
 * nnwabara@albany.edu
 * 
 * This class reads data from a text file and creates products to then place into an array.
 * This class takes user input and performs methods based on the chosen command.
 *
 * The program was tested with "InventoryList.txt".
 * 
*/

import java.io.*;
import java.util.*;

public class P3Driver_NN812672 {
	public static void main(String[] args) throws IOException {
		Scanner scan = new Scanner(System.in);						// Scanner for user input
		Product_NN812672[] inventory = new Product_NN812672[100];	// Array of Product objects
		int count = 0;												// Counter varibale which holds total value of items in array 
		ArrayList<Order_NN812672> orders = new ArrayList<>();		// ArrayList of Order objects
		String line;												// To hold a line from a file
		String name;												// Product's name
		double price;												// Product's price
		char type;													// Product's type
		char usrChoice;												// User input for desired action
		
		// BufferedReader to read text from file
		BufferedReader input = new BufferedReader(new FileReader("//Users//zecash//Projects//Project 3.1//InventoryList.txt"));
		// Read inventory data from a file
			while ((line = input.readLine()) != null) {
				name = line;
				price = Double.valueOf(line = input.readLine());
				line = input.readLine();
				type = line.charAt(0);
				// Create Product objects based on the type
				if (type == 'R') { inventory[count] = new ProductR_NN812672(name, price, type); }
				else if (type == 'B') { inventory[count] = new ProductB_NN812672(name, price, type); }
				else { inventory[count] = new ProductS_NN812672(name, price, type); }
				//	Increment count
				count++;
			}
			
			// Loop to rerun program
			while (true) {
				// Menu
				System.out.println("-----------------------------------------\nType first letter of desired action\n[I]nventory\n[O]rder\n[R]eview the order\n[E]xit\n");
				// User chooses action to perform
				usrChoice = Character.toUpperCase(scan.next().charAt(0));
				// Check whether user entered valid option
				while (!(usrChoice == 'I' || usrChoice == 'O' || usrChoice == 'R' || usrChoice == 'E' )) {
					System.out.println("Error. Enter a valid option.");
					usrChoice = Character.toUpperCase(scan.next().charAt(0));
				}
			
			// Perfrom methods based on user choice
			switch (usrChoice) {
				case 'I': showInventory(inventory, count); break;
				case 'O': orderMenu(scan, inventory, orders); break;
				case 'R': reviewOrders(orders); break;
				case 'E': saveOrders(orders); System.exit(0);
				default: break;
			}
		}
	}
	
	/**
	* The showInventory method displays the inventory.
	*
	* The program prints out the product details of each product in the array
	* 
	* @param inventory The array of products in the inventory.
	* @param count     The number of products in the inventory.
	*
	*/
	public static void showInventory(Product_NN812672[] inventory, int count) {
		System.out.println("-----+-------------------+-----------------+----------------\nID            Name              Price 			 Type\n-----+-------------------+-----------------+----------------");
		for (int i = 0; i < count; i++) {
			System.out.println(inventory[i].toString());
		}
	}
	
	/**
	* The orderMenu method handles the ordering process.
	*
	* The program prints out statemnets prompting the user 
	* to enter details about the order, and then adds each order
	* to the ArrayList containing the orders.
	*
	* @param scan     The scanner for user input.
	* @param inventory The array of products in the inventory.
	* @param orders   The list of orders.
	*/
	public static void orderMenu(Scanner scan, Product_NN812672[] inventory, ArrayList<Order_NN812672> orders) {
		System.out.println("-----------------------------------------\nEnter the product: " ); 
		// Clear input buffer
		scan.nextLine();
		// User enters desired product
		String desiredProduct = scan.nextLine();
		// Check if product exists
		while (!(findProduct(inventory, desiredProduct) != null)) {
			System.out.println("Enter new product:");
			desiredProduct = scan.nextLine(); 
		}
		// If product exists, enter order details
		System.out.println("-----------------------------------------\nEnter quantity: ");
		// Order quantity
		int quantity = getValidQuant(scan);
		System.out.println("-----------------------------------------\nEnter the month of purchasing: (mm)");
		// Month of order
		int month = getValidMonth(scan);
		System.out.println("-----------------------------------------\nEnter the day of purchasing: (dd)");
		// Day of order
		int day = getValidDay(scan, month);
		System.out.println("-----------------------------------------\nEnter the year of purchasing: (yyyy)");
		// Year of order
		int year = getValidYear(scan, month, day);
		// Create new date using instance variables
		Date_NN812672 date = new Date_NN812672(month, day, year);
		// Create new order
		Order_NN812672 order = new Order_NN812672(findProduct(inventory, desiredProduct), quantity, date);
		// Add order to list of orders
		orders.add(order);
	}
	
	/**
	* The findProduct method finds a product in the inventory based on its name.
	*
	* The program retuns a Product in the array of Products which 
	* matches the name of the product the user is searching for. If
	* Product is not found, the user is prompted to refine their search.
	* 
	* @param inventory    The array of products in the inventory.
	* @param productFind  The name of the product to find.
	* @return             The found product, or null if not found.
	*/
	public static Product_NN812672 findProduct(Product_NN812672[] inventory, String productFind) {
		// Iterate through the array
		for (int i = 0; i < inventory.length; i++) {
			// Check is the current array element is not null and has the desired product
			if (inventory[i] != null && inventory[i].getName().equals(productFind)) {
				// Return the product if the conditions are met
				return inventory[i];
			}
		}
		
		// if the product is not found, print a  message and return null
		System.out.println("-----------------------------------------\nItem not found");
		return null;
	}
	/**
	* The getValidQuant method prompts the user to enter a valid quantity and returns the input. 
	* It continues to prompt until a positive integer is entered.
	*
	* The program returns an int value for the quantity 
	* if the value the user enters is valid.
	*
	* @param scan The Scanner object for reading user input
	* @return The valid quantity entered by the user
	*/
	public static int getValidQuant(Scanner scan) {
		int quantity = 0;
		boolean isQuantValid = false;
		
		do {
			try {
				// Read user input
				quantity = scan.nextInt();
				// Check if the entered quantity is greater than 0
				if (quantity > 0) {
					isQuantValid = true; // Set the flag to true if the quantity is valid
				}
				else {
					System.out.println("Invalid quantity. Enter valid amount:");
				}
				// Handle the InputMismatchException if a non-integer is entered
			} catch (InputMismatchException e) {
				System.out.println("Invalid input. Enter a valid amount.");
				scan.nextLine();
			}
		} while (!(isQuantValid)); // Continue the loop until a valid quantity is entered
		return quantity; // Return the valid quantity
	}
	
	/**
	* The getValidMonth method validates and retrieves 
	* a valid month from user input.
	*
	* The program returns an int value for the month if 
	* the value the user enters is valid.
	*
	* @param scan The scanner for user input.
	* @return     A valid month
	*/
	public static int getValidMonth(Scanner scan) {
		int month = 0;					// Variable to store the entered month
		boolean isMonthValid = false;	// Flag to track the validity of the entered month
		
		do {
			try {
				// Read user input
				month = scan.nextInt();
				// Check if the entered month is within the valid range
				if (1 <= month && month <= 12) {
					isMonthValid = true; // Set flag to true if month is vald
				}
				else {
					System.out.println("Invalid month. Enter a month from 1 to 12:");
				}
			} catch (InputMismatchException e) {
				// Handle the InputMismatchException if a non-integer is entered
				System.out.println("Invalid input. Enter a valid month.");
				scan.nextLine();
			}
		} while (!(isMonthValid)); // Continue the loop until a valid month is entered
		return month; // Return valid month
	}
	
	/**
	* The getValidDay method validates and retrieves a valid 
	* day from user input based on the month.
	*
	* The program returns an int value for the day if 
	* the value the user enters is valid based on the month.
	*
	* @param scan  The scanner for user input.
	* @param month The selected month.
	* @return      A valid day.
	*/
	public static int getValidDay(Scanner scan, int month) {
		int day = 0;					// Variable to store the entered day
		boolean isDayValid = false;		// Flag to track the validity of the entered day
		
		do {
			try {
				// Try to read user
				day = scan.nextInt();
				// Check if the entered day is within the valid range
				if (!(day < 1 || (day > 29 && month == 2) || (day > 30 && (month == 4 || month == 6 || month == 9 || month == 11)) || (day > 31))) {
					isDayValid = true; // Set flag to true if day is valid
				}
				else {
					System.out.println("Invalid day. Enter a valid day:");
				}
			} catch (InputMismatchException e) {
				// Handle the InputMismatchException if a non-integer is entered
				System.out.println("Invalid input. Enter a valid day.");
				scan.nextLine();
			}
		} while (!(isDayValid)); // Continue the loop until a valid month is entered
		return day; // Return valid month
	}
	
	/**
	* The getValidYear method validates and retrieves a valid year 
	* from user input based on the month and day.
	*
	* The program returns an int value for the year if 
	* the value the user enters is valid based on the month and day.
	*
	* @param scan  The scanner for user input.
	* @param month The selected month.
	* @param day   The selected day.
	* @return      A valid year.
	*/
	public static int getValidYear(Scanner scan, int month, int day) {
		int year = 0;
		boolean isYearValid = false;
	
		do {
			try {
				// Try to read user 
				year = scan.nextInt();
				// Check if the entered year is within the valid range
				if (!((month == 2 && day == 29 && (year < 2000 || year > 2100)) || (year < 2000 || year > 2100))) {
					isYearValid = true; // Set flag to true if day is valid
				}
				else {
					System.out.println("Invalid year. Enter a valid year:");
				}
			} catch (InputMismatchException e) {
				// Handle the InputMismatchException if a non-integer is entered
				System.out.println("Invalid input. Enter a valid year.");
				scan.nextLine();
			}
		} while (!(isYearValid)); // Continue the loop until a valid month is entered
		return year; // Return valid month
	}
	
	/**
	* The reviewOrders method displays a summary of the orders.
	*
	* The program iterates through the ArrayList of orders and 
	* prints out the order inpformation using the toString method.
	*
	* @param orders The list of orders.
	*/
	public static void reviewOrders(ArrayList<Order_NN812672> orders) {
		// Check is the ArrayList is empty
		if (orders.isEmpty()) {
			System.out.println("There are no orders");
		}
		else {
			// Iterate throght the Arraylist and print the details of each otrder
			for (int i = 0; i < orders.size(); i++) {
				System.out.println(orders.get(i).toString());
			}
		}
	}
	
	/**
	* The saveOrders method saves the orders to a file.
	* 
	* The program iterates through the ArryList of orders and 
	* utilizes a bufferedWriter to write each orders information to a .txt file.
	*
	* @param orders The list of orders.
	* @throws IOException If an I/O error occurs.
	*/
	private static void saveOrders(ArrayList<Order_NN812672> orders) throws IOException {
		// Create a BufferedWriter to write to the "orders.txt" file
		BufferedWriter input = new BufferedWriter(new FileWriter("orders.txt"));
		// Iterate through the ArrayList and write each order's details to the file
		for (int i = 0; i < orders.size(); i++) {
			input.write(orders.get(i).toString());
			input.newLine();
		}
		// Flush and close the BufferedWriter
		input.flush();
		input.close();
		System.out.println("Your order is saved. Goodbye!");
	}
}