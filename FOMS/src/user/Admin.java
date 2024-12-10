/**Responsible for company management and staff management
 * Only the boss has this role
 *
 * Add, edit, or remove Staff accounts.
 *  Display staff list (filter: branch, role, gender, age).
 *  Assign managers to each branch within the quota constraint.
 *  Promote a staff to a Branch manager.
 *  Transfer a staff/manager among branches.
 *  Add/remove payment method.
 *  Open/close branch. */
package user;

import java.util.ArrayList;
import java.util.List;

import Exception.ManagerNotEnough;
import management.*;
import action.*;

/**
* @author Boey, Madelyn, Owen, Rais, Rithika
* @version 25/4/2024
*/
public class Admin extends StaffGeneral{
	/*
	 * Class constructor to create Admin objects
	 * Extends from StaffGeneral
  	 * @param name name of admin
    	 * @param role role
      	 * @param gender gender of admin
	 * @param age age of admin
  	 * @param branch branch
     	 * @param staffID staff id
         * @param password password
	 */
	public Admin(String name, char role, char gender, int age, String branch, String staffID, String password){
		super(name, role, gender, age, branch, staffID, password);
	}
	
	/**
	  * 
	  * @param branch targeted branch
	  * @return number of managers required
	  */
	 public static int calculateRequiredManagers(int staffCount) {
        if (staffCount >= 1 && staffCount <= 4) {
            return 1;
        } else if (staffCount >= 5 && staffCount <= 8) {
            return 2;
        } else if (staffCount >= 9 && staffCount <= 15) {
            return 3;
        }
        return 0;
    }
	 
	 /**
	  * 
	  * @param branch targeted branch
	  * @param cases case 1: staff is added into branch/case 2: manager is taken from the branch.
	  * @return true if the branch will fulfill ratio constraint for both cases.
	  */
	public static void isManagerEnough(Branch branch, int cases) throws ManagerNotEnough{
		int staffCount = branch.getStaffCount();
		int managerCount = 0;
		
		try{
			for (StaffGeneral staff : BranchManagement.getStaffListForBranch(branch)) {
				if (staff.getRole() == 'M') {
					managerCount++;
				}
			}
			switch(cases){
				case 1:
					if (calculateRequiredManagers(staffCount + 1) > managerCount)
						throw new ManagerNotEnough("Target branch does not have enough manager.");
					return;
				case 2:
					if (calculateRequiredManagers(staffCount) > managerCount-1)
						throw new ManagerNotEnough("Source branch does not have enough manager.");
					return;
			}
		}finally{}
	}
	
	/**
	 * List of actions that can be performed by Branch Manager
  	 * Overrides allowableActions interface from Action class and add the relevant actions
    	 * @return list of action that the user can execute
 	 */
	@Override
	public List<Action> allowableActions() {
	  	List<Action> actions = new ArrayList<>();
		actions.add(new DisplayStaffAction());
	  	actions.add(new AddStaffAction());
		actions.add(new EditStaffAction());
		actions.add(new RemoveStaffAction());
		actions.add(new TransferStaffAction());
		actions.add(new PromoteStaffAction());
		actions.add(new AddPaymentAction());
		actions.add(new RemovePaymentAction());
		actions.add(new OpenBranchAction());
		actions.add(new CloseBranchAction());
		actions.add(new ExitActionStaff());
		return actions;
	}
    
}
