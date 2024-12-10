package action;

import java.util.Scanner;

import Exception.InputErrorException;
import management.*;
import user.*;

/**
* @author Boey, Madelyn, Owen, Rais, Rithika
* @version 25/4/2024
*/
public class TransferStaffAction implements Action{
	
	/**
	 * a method to transfer a staff from one branch to another
  	 * @return String
	 */
	@Override
	public String execute() {
		Scanner scan = new Scanner(System.in);
		Branch currentBranch, targetBranch;
		StaffGeneral staff;
		do {
			System.out.println("Enter the ID of staff/manager to be transferred:");
			String staffID = scan.next();
			staff = BranchManagement.getStaff(staffID);
			if (staff == null || staff.getRole() == 'A'){
				System.out.println("Please enter a valid staff/manager.");
				continue;
			}else{
				currentBranch = BranchManagement.findBranch(staff.getBranch());
				break;
			}
		}while(true);
		
		try {
			System.out.println("Enter branch name to be transferred to:");
			targetBranch = BranchManagement.findBranch(scan.next());

			if (targetBranch == null){
				throw new InputErrorException("Branch of such name does not exist.");
			}

			if (targetBranch == currentBranch){
				throw new Exception("The staff is already in that branch.");
			}

			if(currentBranch != targetBranch){
				if(staff.getRole() == 'S') {
					Admin.isManagerEnough(targetBranch, 1);
					BranchManagement.transferStaff(targetBranch, staff);
					System.out.println("Staff transferred to " + targetBranch.getName());
				}else if(staff.getRole() == 'M'){
					Admin.isManagerEnough(currentBranch, 2);
					BranchManagement.transferStaff(targetBranch, staff);
					System.out.println("Manager transferred to " + targetBranch.getName());
				}
			}
			staff.setBranch(targetBranch.getName());
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return " "; // return empty string
	}
	
	/**
	 * @return Transfer staff
	 */
	@Override
	public String description() {
		return "Transfer staff.";
	}
}
