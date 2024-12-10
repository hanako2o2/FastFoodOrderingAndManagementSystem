package action;

import java.util.*;
import payment.*;

/**
* @author Boey, Madelyn, Owen, Rais, Rithika
* @version 25/4/2024
*/
public class RemovePaymentAction implements Action{
    Scanner scan = new Scanner(System.in);
    /**
    * Method to add a new payment method
    * @return String
    */
    public String execute(){
        Map<Character, Payment> keyToPaymentMap;
        System.out.print("\n");
        System.out.println("Current available payment method: ");
        keyToPaymentMap = PaymentMethods.printAvailablePaymentMethods();

        char key;
		System.out.println("Select which payment method to remove (a-z): ");
        do {
            key = scan.next().charAt(0);
            String buffer = scan.nextLine();
            System.out.print("\n");
        } while (!keyToPaymentMap.containsKey(key));

		Payment payment = keyToPaymentMap.get(key);
        PaymentMethods.removePaymentMethod(payment);
        
        System.out.println("Current available payment method: ");
        PaymentMethods.printAvailablePaymentMethods();
        return "";
    }

    /**
    * @return Add a new payment
    */
    public String description(){
        return ("Remove a new payment");
    }
}
