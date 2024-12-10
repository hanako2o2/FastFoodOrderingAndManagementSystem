package action;

import management.Branch;

/**
* @author Boey, Madelyn, Owen, Rais, Rithika
* @version 25/4/2024
*/
public class DisplayOrderStaff implements Action{
    private Branch branch;

    /**
    * a constructor for DisplayOrderStaff
    * @param branch target branch
    */
    public DisplayOrderStaff(Branch branch){
        this.branch = branch;
    }

    /**
    * a method to display the order
    * @return String
    */
    public String execute(){
        branch.displayOrder();
        return null;
    }

    /**
    * @return Displays all orders
    */
    public String description(){
        return ("Displays all orders");
    }
}
