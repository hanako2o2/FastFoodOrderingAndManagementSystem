package action;


/**
* @author Boey, Madelyn, Owen, Rais, Rithika
* @version 25/4/2024
*/
public class ExitActionStaff implements Action{
    
    /**
    * a method for the customer to exit
    * @return String
    */
    public String execute(){
        return ("Triggered");
    }

    /**
    * @return Exit
    */
    public String description(){
        return ("Exit");
    }
}
