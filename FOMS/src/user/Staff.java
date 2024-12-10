package user;

import java.util.ArrayList;
import java.util.List;

import management.BranchManagement;
import action.*;

/**
* @author Boey, Madelyn, Owen, Rais, Rithika
* @version 25/4/2024
*/

public class Staff extends StaffGeneral{

	/**
	 * Class constructor to create Staff objects
	 * Extends from StaffGeneral
  	 * @param name name of staff
    	 * @param role role of staff
      	 * @param gender gender of staff
	 * @param age age of staff
  	 * @param branch branch of staff
     	 * @param staffID staff id
         * @param password password
	 */
	public Staff(String name, char role, char gender, int age, String branch, String staffID, String password){
		super(name, role, gender, age, branch, staffID, password);
	}

	/**
	 * List of actions that can be performed by Staff
  	 * Overrides allowableActions interface from Action class and add the relevant actions
    	 * @return list of action that can be executed by the user
 	 */
	@Override
	public List<Action> allowableActions() {
		List<Action> actions = new ArrayList<>();
		actions.add(new DisplayOrderStaff(BranchManagement.findBranch(getBranch())));
		actions.add(new ProcessOrderStaff(BranchManagement.findBranch(getBranch())));
		actions.add(new ExitActionStaff());
		return actions;
	}

}
