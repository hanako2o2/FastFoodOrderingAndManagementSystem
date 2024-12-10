package page;

import java.util.Scanner;

/**
* @author Boey, Madelyn, Owen, Rais, Rithika
* @version 25/4/2024
*/
public class FirstPage {
	
	private Scanner scanner = new Scanner(System.in);

	/**
	 * method for users to select their identity and create relevant object
  	 * invoke the correct view for their respective identity
	 */
	public void consoleMenu(String inputPath) {
        System.out.println("Please choose your identity");
    	System.out.println("a. Customer");
    	System.out.println("b. Staff");
    	char selection = scanner.next().charAt(0);
    	if (selection == 'a') {
        	System.out.println("Your selection is Customer");
        	CustomerPage customerPage = new CustomerPage();
			customerPage.customerView(inputPath);
        } else if (selection == 'b'){
        	System.out.println("Your selection is Staff");
        	StaffPage staffPage = new StaffPage();
			staffPage.staffView(inputPath);
        } else {
        	System.out.println("Invalid selection");
        }
           System.out.println("#########################################");
	}
	/**
	 * prints welcome and exit
	 * @param inputPath
	 */
	 public void printStatus(String inputPath) {
	        System.out.println("Welcome to FOMS");
	        consoleMenu(inputPath);
			//update csv here
	        System.out.println("Thank you for visiting.");
	    }
}
