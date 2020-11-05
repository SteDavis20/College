package arrayListBankCustomer;

import java.util.ArrayList;

public class ArrayListBankCustomer {

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

	public static String findCustomer(ArrayList<ArrayListBankCustomer> array, int accNumber) {
		String string = "";
		
		for (ArrayListBankCustomer customer : array) {
			if (customer.getAccountNumber() == accNumber) {
				return customer.getCustomerName();
			}
		}
		return "No bank customer(s) matches these credentials.";
		
	}
	
	public static String findCustomer(String dob, String address, ArrayList<ArrayListBankCustomer> array) {
		String customerName = "";
		for (int i=0; i<array.size(); i++) {
			if (array.get(i).date_Of_Birth==dob && array.get(i).customerAddress==address) {
				return array.get(i).customerName;
			}
		}
		return "No customer matches these credentials.";
	}
	
	public static void main(String[] args) {
		ArrayList<ArrayListBankCustomer> array = new ArrayList<ArrayListBankCustomer>(2);
		ArrayListBankCustomer customer1 = new ArrayListBankCustomer();
		customer1.setAccountNumber(12345);
		customer1.setCustomerAddress("Anfield");
		customer1.setCustomerName("Martin");
		customer1.setDate_Of_Birth("20th April 2020");
		array.add(customer1);
		
		ArrayListBankCustomer customer2 = new ArrayListBankCustomer();
		customer2.setAccountNumber(67890);
		customer2.setCustomerAddress("Berlin");
		customer2.setCustomerName("Mary");
		customer2.setDate_Of_Birth("11th April 1999");
		array.add(customer2);
		
		System.out.println("Customer Name: " + 
				/*customer1.*/findCustomer(array, 12345));
		System.out.println("Customer Name: " + 
				/*customer1.*/findCustomer("11th April 1999", "Berlin", array));
		System.out.println("Test: " + customer1.findCustomer(array, 5234));
	}
	
}
