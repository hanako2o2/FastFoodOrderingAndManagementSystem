package action;

import management.*;
import java.util.*;

/**
* @author Boey, Madelyn, Owen, Rais, Rithika
* @version 25/4/2024
*/
public class OpenBranchAction implements Action{
	Scanner scanner = new Scanner(System.in);
	/**
	 * a method to open a branch
  	 * @return String
	 */
	@Override
	public String execute() {
		try {
			Branch branch;
			String name, location;
			int quota;

			System.out.println("Enter branch details: ");
			System.out.print("Name: ");
			name = scanner.nextLine();
			System.out.print("Location: ");
			location = scanner.nextLine();
			do {
				try {
					System.out.print("Maximum staff quota: ");
					quota = scanner.nextInt();
					break;
				}catch (Exception e) {
					System.out.println("Invalid value! Please try again");
					scanner.nextLine();
				}
			}while(true);

			BranchManagement.openBranch(name, location, quota);
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		System.out.println("Branch opened.");
		
		return " "; // return empty string
	}
	
	/**
	 * @return Open branch
	 */
	@Override
	public String description() {
		return "Open branch.";
	}
	
}
