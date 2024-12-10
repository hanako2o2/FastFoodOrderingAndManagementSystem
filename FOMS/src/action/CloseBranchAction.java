package action;

import management.*;

/**
* @author Boey, Madelyn, Owen, Rais, Rithika
* @version 25/4/2024
*/
public class CloseBranchAction implements Action{
	
	/**
	 * a method to close a branch
  	 * @return String
	 */
	@Override
	public String execute() {
		do {
			try {
				Branch targetBranch = BranchManagement.branchSelection();
				BranchManagement.closeBranch(targetBranch);
				break;
			}catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}while(true);
		System.out.println("Branch closed.");
		
		return "";
	}
	
	/**
	 * @return Close branch
	 */
	@Override
	public String description() {
		return "Close branch.";
	}
	

}
