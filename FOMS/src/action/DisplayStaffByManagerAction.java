package action;

import management.*;

public class DisplayStaffByManagerAction implements Action{
    private String branch;

    /**
     * constructor for display staff by manager action
     * @param branch
     */
    public DisplayStaffByManagerAction(String branch){
        this.branch = branch;
    }

    /**
     * method to print staff by branch
     */
    public String execute() {
        BranchManagement.printStaffByBranch(branch);
        return "";
    }

    /**
    * @return Displays staff list inside of manager's branch
    */
    public String description(){
        return ("Display staff list in your branch");
    }
}
