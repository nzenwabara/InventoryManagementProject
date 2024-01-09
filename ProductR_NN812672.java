public class ProductR_NN812672 extends Product_NN812672 {
	// Constructor
	public ProductR_NN812672(String name, double price, char type) {
		super(name, price, 'R');
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
		double total = 0.0;
		// Total price is calculated by multiplying the quantity with the unit price
		total = quantity * getPrice();
		return total;
	}
}