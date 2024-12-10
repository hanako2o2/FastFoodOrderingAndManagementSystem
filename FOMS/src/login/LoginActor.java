package login;

import management.*;
import user.*;

/**
* @author Boey, Madelyn, Owen, Rais, Rithika
* @version 25/4/2024
*/
public class LoginActor{
    private static StaffGeneral staff;

    /**
    * a method to validate whether the id and password are correct
    * @param id staff id
    * @param password  staff password
    */
    public static StaffGeneral validate(String id, String password){
        if (BranchManagement.getStaff(id) == null) return null;
        staff = BranchManagement.getStaff(id);
        
        if (staff.validate(password)){
            return staff;
        }else return null;
    }
}
