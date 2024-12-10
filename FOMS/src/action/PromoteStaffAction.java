package action;

import java.util.Scanner;

import management.*;
import user.*;

/**
* @author Boey, Madelyn, Owen, Rais, Rithika
* @version 25/4/2024
*/
public class PromoteStaffAction implements Action{
	
	
	/**
	 * a method to promote a staff
  	 * @return String
	 */
	@Override
	public String execute() {
		Scanner scan = new Scanner(System.in);
		try {
			System.out.println("Staff to be promoted:");
			String staffID = scan.next();
			 
			StaffGeneral Staff = BranchManagement.getStaff(staffID);
			if (Staff == null){
				throw new Exception("Staff does not exist.");
			}
			
			Staff.setRole('M');
			System.out.println("Staff promoted.");
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		return " ";
	}
	
	/**
  	 * @return Promote staff
	 */
	@Override
	public String description() {
		return "Promote staff.";
	}

}
