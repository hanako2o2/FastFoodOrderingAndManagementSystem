package action;

import java.util.*;
import order.*;

/**
* @author Boey, Madelyn, Owen, Rais, Rithika
* @version 25/4/2024
*/
public class DisplayCartAction implements Action{
    private Order order;
	Scanner scan = new Scanner(System.in);
    public DisplayCartAction(Order order){
		this.order = order;
    }
    /**
	* a method to display cart
 	* @return String
	*/
    public String execute(){
        System.out.print("\n");
		System.out.println("Your cart: ");
		order.displayOrderItems();
        System.out.print("\n");

        return "";
    }

	/**
	* @return Display your cart
	*/
    public String description(){
        return ("Display your cart.");
    }
}
