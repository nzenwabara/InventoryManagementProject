public class ProductB_NN812672 extends Product_NN812672 {
	// Constructor
	public ProductB_NN812672(String name, double price, char type) {
		super(name, price, 'B');
	}
	
	/**
	* The total method calculates the total price of the regular product based on the given quantity.
	*
	* The program calculates a double for the total after multiplying the quantity 
	* and price of the product. The program returns the double after the appropriate 
	* discount is applied based on the quantity thresholds.
	*
	* @param quantity The quantity of the regular product.
	* @return The total price of the regular product.
	*/
	@Override
	public double total(int quantity) {
		// Check if quantity is less than 100
		if (quantity < 100) {
			// No discount for quantities less than 100
			double total = quantity * getPrice();
			return total;
			
		}
		else if (quantity > 100 && quantity < 500) {
			double discountPrice; // Variable holding the price deducted
			// Apply 5% discount for quantities between 100 and 499
			discountPrice = getPrice() * 0.95;
			double total = discountPrice * quantity;
			return total;
		}
		else if (quantity > 500 && quantity < 1500) {
			double discountPrice;
			// Apply 15% discount for quantities between 500 and 1499
			discountPrice = getPrice() * 0.85;
			double total = discountPrice * quantity;
			return total;
		}
		else {
			double discountPrice;
			// Apply 25% discount for quantities of 1500 or more
			discountPrice = getPrice() * 0.75;
			double total = discountPrice * quantity;
			return total;
		}
	}
}