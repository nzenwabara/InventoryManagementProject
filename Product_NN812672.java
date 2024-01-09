public abstract class Product_NN812672 {
	private static int invSize = 0;	// Variable to keep track of the total number of products in the inventory
	
	private int iD;					// Unique identifier for the product
	private String name;			// Name of the product
	private double price;			// Price of the product
	private char type;				// Type of the product
	
	// Product constructor
	public Product_NN812672(String name, double price, char type) {
		this.name = name;
		this.price = price;
		this.type = type;
		this.iD = ++invSize;
	}
	
	/**
	* The total method is an abstract method to be implemented by subclasses.
	* It calculates the total price of the product based on the given quantity.
	*
	* The program returns a double for the total after multiplyimg the quantity 
	* and price of the product.
	*
	* @param quantity The quantity of the product.
	* @return The total price of the product.
	*/
	public abstract double total(int quantity); 
	
	/**
	* Returns a string representation of the product.
	*
	* @return A string containing the product ID, name, price, and type.
	*/
	@Override
	public String toString() {
		return  iD + "        " + name + "     	\t$" + price + "  			  " + type;
	}
	
	/**
	* The getInvSize gets the total number of products in the inventory.
	*
	* @return The total number of products in the inventory.
	*/
	public static int getInvSize() {
		return invSize;
	}
	/**
	* The getPrice gets the price of the product.
	*
	* @return The price of the product.
	*/
	public double getPrice() {
		return price;
	}
	/**
	* The getName method gets the name of the product.
	*
	* @return The name of the product.
	*/
	public String getName() {
		return this.name;
	}
	
	/**
	* The getType gets the product's type.
	*
	* @return The type of the product.
	*/
	public char getType() {
		return this.type;
	}
}