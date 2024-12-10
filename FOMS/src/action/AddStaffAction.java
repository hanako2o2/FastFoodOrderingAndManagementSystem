package action;

import java.util.Scanner;
import management.*;
import user.*;

/**
* @author Boey, Madelyn, Owen, Rais, Rithika
* @version 25/4/2024
*/
public class AddStaffAction implements Action{
	
	/**
	 * a method to add a staff
  	 * @return String
	 */
	@Override
	public String execute() {
		Scanner scan = new Scanner(System.in);
		String branchName;
		Branch branch;
		char role, gender;
		int age;
			try {
				System.out.println("Enter Name: ");
				String name = scan.next();
				 
				do {
					System.out.println("Enter Role(S/M): ");
					role = scan.next().charAt(0);
					if(role != 'S' && role != 'M')
					{
						System.out.println("Invalid role! Please try again");
					}
				}while(role != 'S' && role != 'M');
				 
				do {
					System.out.println("Enter Gender(M/F): ");
					gender = scan.next().charAt(0);
					if(gender != 'M' && gender != 'F')
					{
						System.out.println("Invalid gender! Please try again");
					}
				}while(gender != 'M' && gender != 'F');
					
				do {
					try {
						System.out.println("Enter Age: ");
						age = scan.nextInt();
						break;
					}catch (Exception e) {
						System.out.println("Invalid age! Please try again");
						scan.next();
					}
				}while(true);
				
				do{
					System.out.println("Enter Branch: ");
					branchName = scan.next();
					if (BranchManagement.findBranch(branchName) == null){
						System.out.println("Branch does not exist");
						continue;
					}
					break;
				}while(true);
				
				branch = BranchManagement.findBranch(branchName);
				 
				System.out.println("Enter Staff ID: ");
				String staffID= scan.next();
				 
				System.out.println("Enter Password: ");
				String password = scan.next();
				if (role == 'S')
					Admin.isManagerEnough(branch, 1);
			
				StaffGeneral newStaff = new StaffGeneral(name, role, gender, age, branchName, staffID, password);
				BranchManagement.addStaffAccount(branch, newStaff);
				System.out.println("New Staff Added.");
			}catch (Exception e){
				System.out.println(e.getMessage());
			}
		return " "; // return empty string
	}
	
	/**
  	 * @return Add new staff
	 */
	@Override
	public String description() {
		return "Add new staff.";
	}
	

}

