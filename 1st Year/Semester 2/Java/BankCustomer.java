package bank_Customer;

public class BankCustomer {
	private int accountNumber;		// Below are the 4 attributes.
	private String customerName;
	private String customerAddress;
	private String date_Of_Birth;	
 
	public int getAccountNumber() {
		return accountNumber;		
	}
	public void setAccountNumber(int inputtedNumber) {
		accountNumber = inputtedNumber;	
	}

	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String inputtedName) {
		customerName = inputtedName;
	}

	public String getCustomerAddress() {
		return customerAddress;
	}
	public void setCustomerAddress(String inputtedAddress) {
		customerAddress = inputtedAddress;
	}

	public String getDate_Of_Birth() {
		return date_Of_Birth;
	}
	public void setDate_Of_Birth(String inputtedDate_Of_Birth) {
		date_Of_Birth = inputtedDate_Of_Birth;
	}

	// Method 1
	public String findCustomer(int accountNumber, BankCustomer arrayOfCustomers[]) {
		String customerName = "";
		for (int i=0; i<arrayOfCustomers.length; i++) {
			if (arrayOfCustomers[i].getAccountNumber() == accountNumber) {
				return arrayOfCustomers[i].getCustomerName();
			}
		}
		return "No customer matches these credentials.";
	}
	// Method 2
	public String findCustomer(String dob, String address, BankCustomer arrayOfCustomers[]) {
		String customerName = "";
		for (int i=0; i<arrayOfCustomers.length; i++) {
			if (arrayOfCustomers[i].getDate_Of_Birth() == dob && 
					arrayOfCustomers[i].getCustomerAddress() == address) {
				return arrayOfCustomers[i].getCustomerName();
			}
		}
		return "No customer matches these credentials.";
	}
	// Method 2 (Overloading)
	public static void main(String[] args) {
		BankCustomer[] arrayOfCustomers = new BankCustomer[10];
		
		// instantiate the 10 objects
		for (int i =0; i<arrayOfCustomers.length; i++) {
			arrayOfCustomers[i]=new BankCustomer();
			if (i == 4) {		// Random example to test method overloading with 2 different objects.
				arrayOfCustomers[i].setCustomerAddress("Longstone");
				arrayOfCustomers[i].setDate_Of_Birth("August 27th");
				arrayOfCustomers[i].setCustomerName("Michael");
			}
		}
		BankCustomer Brendan = new BankCustomer();	// Brendan is the new object in the class BankCustomer
		arrayOfCustomers[0] = Brendan;
		Brendan.setAccountNumber(7890);
		Brendan.setCustomerName("Brendan");
		Brendan.setCustomerAddress("Longstone, Ballymore Eustace");
		Brendan.setDate_Of_Birth("1st of January 1900");
		
//		System.out.println("Customer's account number is: " + Brendan.getAccountNumber());
//		System.out.println("Customer's name is: " + Brendan.getCustomerName());
//		System.out.println("Customer's dob is: " + Brendan.getDate_Of_Birth());
//		System.out.println("Customer's address is: " + Brendan.getCustomerAddress());
		System.out.println("Customer's Name: " + Brendan.findCustomer(7890, arrayOfCustomers));
		System.out.println("Customer 2's Name: " + Brendan.findCustomer("August 27th", "Longstone", arrayOfCustomers));
	}
	
	
}

