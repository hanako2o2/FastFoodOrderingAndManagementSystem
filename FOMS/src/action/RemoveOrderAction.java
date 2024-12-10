package action;

import order.*;
import java.util.*;
import Exception.EmptyCartException;

/**
* @author Boey, Madelyn, Owen, Rais, Rithika
* @version 25/4/2024
*/
public class RemoveOrderAction implements Action{
    private Order order;
    Scanner scan = new Scanner(System.in);

/**
	* a constructor for RemoveOrderAction
	* @param order target order
	*/
    public RemoveOrderAction(Order order){
        this.order = order;
    }

	/**
	* a method to remove an order
 	* @return String
	*/
    public String execute(){
        try {
            Map<Character, OrderItem> keyToOrderItemMap;
            
            if (order.getTotalPrice() == 0) { 
                throw new EmptyCartException(); 
            } 

            System.out.print("\n");
            System.out.println("Your cart: ");
            keyToOrderItemMap = order.displayOrderItems();

            char key;
            System.out.println("Select which item to remove (a-z): ");
            do {
                key = scan.next().charAt(0);
                String buffer = scan.nextLine();
                System.out.print("\n");
            } while (!keyToOrderItemMap.containsKey(key));

            OrderItem item = keyToOrderItemMap.get(key);
            order.removeItem(item);
            System.out.println("Item succesfully removed.");
            System.out.println("Your cart: ");
            keyToOrderItemMap = order.displayOrderItems();

            return "";
        } catch (EmptyCartException e) { 
            System.out.println(e.getMessage()); 
            return "failed due to empty cart."; 
        } 
    }

	/**
	* @return Remove an item from your cart
	*/
    public String description(){
        return "Remove an item from your cart";
    }
}
