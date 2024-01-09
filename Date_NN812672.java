public class Date_NN812672 {
	private int day;		// Day of the date
	private int month;		// Month of the date
	private int year;		// Year of the date
	
	// Constructor
	public Date_NN812672(int month, int day, int year) {
		this.month = month;
		this.day = day;
		this.year = year;
	}
	
	/**
	* Gets the month of the date.
	*
	* @return The month.
	*/
	public int getMonth() {
		return month;
	}
	
	/**
	* Converts the date to a string.
	*
	* @return The string representation of the date (MM/DD/YYYY).
	*/
	public String toString() {
		return month + "/" + day + "/" + year; 
	}
}
