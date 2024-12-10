package action;

import java.util.*;
import order.*;
import Exception.EmptyCartException;

/**
* @author Boey, Madelyn, Owen, Rais, Rithika
* @version 25/4/2024
*/
public class EditOrderAction implements Action{
	private Order order;
	Scanner scan = new Scanner(System.in);
    public EditOrderAction(Order order){
		this.order = order;
    }

/**
	* a method to edit the order
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
            System.out.print("\n");

            char key;
            System.out.println("Select which item to edit (a-z): ");
            do {
                key = scan.next().charAt(0);
                String buffer = scan.nextLine();
                System.out.print("\n");
            } while (!keyToOrderItemMap.containsKey(key));

            OrderItem item = keyToOrderItemMap.get(key);
            
            int quantity = item.getQuantity();
            boolean upSize = item.getUpSize();
            char selection;
            do{
                System.out.println("What would you like to edit?");
                System.out.println("a: Quantity");
                System.out.println("b. UpSize");
                System.out.println("c. Finish edit");
                selection = scan.nextLine().charAt(0);
                System.out.print("\n");
                switch(selection){
                    case 'a':
                        System.out.println("Enter new quantity: ");
                        quantity = scan.nextInt();
                        String buffer = scan.nextLine();
                        break;
                    case 'b':
                        System.out.println("Do you want UpSize? (y/n)");
                        upSize = scan.nextLine().charAt(0) == 'y'? true : false;
                }
            }while(selection != 'c');
            
            order.customize(item, quantity, upSize);
            System.out.println("Item succesfully edited.");
            System.out.println("Your cart: ");
            keyToOrderItemMap = order.displayOrderItems();

            return "";
        } catch (EmptyCartException e) { 
            System.out.println(e.getMessage()); 
            return "Checkout failed due to empty cart."; 
        } 
    }

	/**
	* @return Edits your current cart.
	*/
    public String description(){
        return ("Edits your current cart.");
    }
}
