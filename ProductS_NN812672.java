public class ProductS_NN812672 extends Product_NN812672 {
	// Constructor
	public ProductS_NN812672(String name, double price, char type) {
		super(name, price, 'S');
	}
	
	/**
	* The total method calculates the total price of the regular product 
	* based on the given quantity.
	*
	* The program returns a double for the total after multiplying the quantity 
	* and price of the product.
	*
	* @param quantity The quantity of the regular product.
	* @return The total price of the regular product.
	*/
	@Override 
	public double total(int quantity) {
		return getPrice() * quantity;
	}
	
	/**
	* The total method calculates the total price of the product 
	* based on the given quantity and month, applying a discount 
	* for specific months.
	* 
	* The program returns a double for the total price of the product
	* based on the given quantity and month, applying a discount 
	* for specific months.
	*
	* @param quantity The quantity of the product.
	* @param month    The month of the purchase.
	* @return The total price with or without a discount based on the month.
	*/
	public double total(int quantity, int month) {
		// Apply a 40% discount for purchases in November, December, or January
		if (month == 11 || month == 12 || month == 1) {
			double discountPrice = getPrice() * 0.60;
			double total = discountPrice * quantity;
			return total;
		}
		else {
			// No discount for other months
			return getPrice() * quantity;
		}
	}
}