package page;
import java.util.Scanner;
import display.*;
import user.*;
import login.LoginActor;
import management.*;
import action.*;
import payment.*;

/**
* @author Boey, Madelyn, Owen, Rais, Rithika
* @version 25/4/2024
*/
public class StaffPage {
    /**
     * input staff ID and Password
     * instantiate loginactor class
     * if password is default, option to change password is given
     * branch menu and order list is imported after retrieving branch information from staff when login is successful
     */
	private boolean exit = false;

	/**
	 * prints staff view
	 * @param inputPath
	 */
	public void staffView(String inputPath) {
		Branch currentBranch = null;
		StaffGeneral staff;
		Scanner scan = new Scanner(System.in);
		boolean flag = false;

		while (true) {
			System.out.println("Please input your Staff ID:");
			String StaffID = scan.nextLine();
			System.out.println("Please input your password:");
			String password = scan.nextLine();

			staff = LoginActor.validate(StaffID, password);
			if (staff != null){
				System.out.println("Welcome, " + staff.getName());
				break;
			}
			System.out.println("Wrong Staff ID or password.");
			System.out.println();
		}

		if (staff.isDefaultPassword()){
			System.out.println("Your password is default, do you want to change it? (y/n)");
			char selection = scan.nextLine().charAt(0);
			boolean changed = false;
			while (!changed && (selection != 'y' || selection != 'Y' || selection != 'n' || selection != 'N')){
				switch(selection){
					case 'y':case 'Y':
					System.out.println("Enter your new password: ");
					String newPassword = scan.nextLine();
					staff.setStaffPW(newPassword);
					System.out.println("Password updated succesfully.");
					changed = true;
					break;
					case 'n':case'N':
					changed = true;
					break;
				}
			}
		}
		if (staff.getRole() != 'A'){
			currentBranch = BranchManagement.findBranch(staff.getBranch());
		}

		while(!exit){
            System.out.println("Select actions:");
            Action action = displayAction.showMenu(staff.allowableActions());
            String status = action.execute();
            System.out.print("\n");
            if(status != null && status.equals("Triggered"))
                triggerExitStatus();
        }
		
		/*export files*/
		for (Branch branch: BranchManagement.getBranchList()){
				branch.updateMenuToCSV(inputPath + "/FOMS/src/CSVs/menu_list.csv", flag);
				branch.updateOrdersToCSV(inputPath + "/FOMS/src/CSVs/order_list.csv", flag);
				flag = true;
		}
			PaymentMethods.exportPaymentMethodToCSV(inputPath + "/FOMS/src/CSVs/payment_methods.csv");
			BranchManagement.updateBranchesToCSV(inputPath + "/FOMS/src/CSVs/branch_list.csv");
			BranchManagement.updateStafflistToCSV(inputPath + "/FOMS/src/CSVs/staff_list.csv");
	}
	
	/**
     * make exit status true to exit loop
     */
    public void triggerExitStatus(){
        exit = true;
    }

    /**
     * make exit status false to able to enter loop
     */
    public void resetExitStatus(){
        exit = false;
    }
}
