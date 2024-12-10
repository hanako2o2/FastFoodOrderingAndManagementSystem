package action;

import java.util.Scanner;

import management.BranchManagement;
import user.*;

/**
* @author Boey, Madelyn, Owen, Rais, Rithika
* @version 25/4/2024
*/
public class RemoveStaffAction implements Action{

	/**
	 * a method to remove a staff
  	`* @return String
	 */
	@Override
	public String execute() {
		Scanner scan = new Scanner(System.in);
		
		try {
			System.out.println("Enter the ID of staff to be removed");
			String staffID = scan.next();
				
			StaffGeneral Staff = BranchManagement.getStaff(staffID);
			if (Staff == null) 
				throw new Exception("Staff does not exist");
			BranchManagement.removeStaffAccount(BranchManagement.findBranch(Staff.getBranch()), Staff);
			System.out.println("Staff removed");
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		return " ";
	}
	
	/**
	 * @return Removing staff
	 */
	@Override
	public String description() {
		return "Removing staff";
	}

}
