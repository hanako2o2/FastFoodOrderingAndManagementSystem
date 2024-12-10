package user;

import java.util.ArrayList;
import java.util.List;

import action.*;
import management.*;

/**
* @author Boey, Madelyn, Owen, Rais, Rithika
* @version 25/4/2024
*/
public class BranchManager extends Staff{

	/**
	 * Class constructor to create BranchManager objects
	 * Extends from Staff
  	 * @param name name of manager
    	 * @param role role
      	 * @param gender gender of manager
	 * @param age age of manager
  	 * @param branch branch that they are managing
     	 * @param staffID staff id
         * @param password password
	 */
	public BranchManager(String name, char role, char gender, int age, String branch, String staffID, String password){
		super(name, role, gender, age, branch, staffID, password);
	}

	/**
	 * List of actions that can be performed by Branch Manager
  	 * Overrides allowableActions interface from Action class and add the relevant actions
    	 * @return list of action that can be executed by the user
 	 */
	@Override
	public List<Action> allowableActions() {
		List<Action> actions = new ArrayList<>();
		actions.add(new DisplayOrderStaff(BranchManagement.findBranch(getBranch())));
		actions.add(new ProcessOrderStaff(BranchManagement.findBranch(getBranch())));
		actions.add(new AddMenuItemAction(BranchManagement.findBranch(getBranch())));
		actions.add(new EditMenuAction(BranchManagement.findBranch(getBranch())));
		actions.add(new RemoveMenuItemAction(BranchManagement.findBranch(getBranch())));
		actions.add(new DisplayStaffByManagerAction(getBranch()));
		actions.add(new ExitActionStaff());
		return actions;
	}

}
