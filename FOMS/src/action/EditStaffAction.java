package action;

import java.util.Scanner;

import management.BranchManagement;
import user.*;
import Exception.*;

/**
* @author Boey, Madelyn, Owen, Rais, Rithika
* @version 25/4/2024
*/
public class EditStaffAction implements Action{
	
	/**
	 * a method to edit a Staff's attributes (StaffID, Password, Gender, Age)
  	 * @return String
	 */
	@Override
	public String execute() {
		try {
			Scanner scan = new Scanner(System.in);
			
			System.out.println("Staff to be edited");
			String staffID = scan.next();
			StaffGeneral Staff = BranchManagement.getStaff(staffID);
			if (Staff == null) {
				throw new NonExistent();
			}

			char NEWgender;
			int NEWage;
			
			System.out.println("Enter variable to be changed:");
			System.out.println("a. StaffID");
			System.out.println("b. Password");
			System.out.println("c. Gender");
			System.out.println("d. Age");
			char choice = scan.next().charAt(0);
			
			switch(choice) {
				case 'a':
					System.out.println("Enter New Staff ID: ");
					String NEWstaffID = scan.next();
					Staff.setStaffID(NEWstaffID);
					break;
				case 'b':
					System.out.println("Enter New Password: ");
					String NEWpw = scan.next();
					Staff.setStaffPW(NEWpw);
					break;
				case 'c':
					do {
						System.out.println("Enter New Gender: ");
						NEWgender = scan.next().charAt(0);
						if(NEWgender != 'M' && NEWgender != 'F') {
							System.out.println("Invalid gender! Please try again");
						}
						else {
							Staff.setGender(NEWgender);
							break;
						}
					}while(NEWgender != 'M' && NEWgender != 'F');
				case 'd':
					do {
						try {
							System.out.println("Enter New Age: ");
							NEWage = scan.nextInt();
							Staff.setAge(NEWage);
							break;
						}catch (Exception e) {
							System.out.println("Invalid value! Please try again");
						}
					}while(true);
				default:
					break;
			}
			
			// BranchManagement.removeStaffAccount(); // to be updated
			System.out.println("Staff edited");
			return " ";
		} catch (Exception NonExistent) {
			return "";
		}
	}
	
	/**
	 * @return Edit staff
	 */
	@Override
	public String description() {
		return "Edit staff";
	}

}

