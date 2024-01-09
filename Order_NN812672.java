public class Order_NN812672 {
	private Product_NN812672 product;		// Product the user is ordering
	private int quantity;			// Quantity of product user is ordering
	private float total;			// Total of the order
	private Date_NN812672 date;		// Date order is placed
	
	// Constructor
	public Order_NN812672(Product_NN812672 product, int quantity, Date_NN812672 date) {
		this.product = product;
		this.quantity = quantity;
		this.date = date;
		
		// Calculate total based on product type
		if (product instanceof ProductS_NN812672) {
			total = (float) ((ProductS_NN812672) product).total(quantity, date.getMonth());
		}
		else {
			total = (float) product.total(quantity);
		}
	}
		
	/**
	* Converts the order to a string.
	*
	* @return The string representation of the order.
	*/
	@Override
	public String toString() {
		return "-----------------------------------------\nDate: " + date.toString() + "\n-----+-------------------+-----------------+----------------\nID            Name              Price 			 Type\n-----+-------------------+-----------------+----------------\n" + product.toString() + "\nQuantity: " + quantity + "\nTotal: $" + String.format("%.02f", total);
	}
	
}