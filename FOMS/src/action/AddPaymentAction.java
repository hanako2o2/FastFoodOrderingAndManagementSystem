package action;

import payment.PaymentMethods;
import java.util.*;

/**
* @author Boey, Madelyn, Owen, Rais, Rithika
* @version 25/4/2024
*/
public class AddPaymentAction implements Action{
    Scanner scan = new Scanner(System.in);
    /**
    * Method to add a new payment method
    * @return String
    */
    public String execute(){
        System.out.print("\n");
        System.out.println("Enter new payment method's name: ");
        String name = scan.nextLine();
        PaymentMethods.createNewPayment(name);
        System.out.println("Current available payment method: ");
        PaymentMethods.printAvailablePaymentMethods();
        return "";
    }

    /**
    * @return Add a new payment
    */
    public String description(){
        return ("Add a new payment");
    }
}
