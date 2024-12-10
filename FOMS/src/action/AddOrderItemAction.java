package action;

import management.*;
import java.util.*;
import menu.*;
import order.*;

/**
* @author Boey, Madelyn, Owen, Rais, Rithika
* @version 25/4/2024
*/
public class AddOrderItemAction implements Action{
	private Branch branch;
	private Order order;
	Scanner scan = new Scanner(System.in);

    /**
    * a constructor for AddOrderItemAction
    * @param branch target branch 
    * @param order target order
    */
    public AddOrderItemAction(Branch branch, Order order){
        this.branch = branch;
		this.order = order;
    }

	/**
	* a method to add an order item
 	* @return String
	*/
	public String execute(){
		Map<Character, MenuItem> keyToMenuItemMap;
		try{
			System.out.print("\n");
			System.out.println("Menu list: ");
			keyToMenuItemMap = branch.displayMenu();
			char key;
			System.out.println("Enter your selection (a-z): ");
			do {
				key = scan.next().charAt(0);
				System.out.print("\n");
			} while (!keyToMenuItemMap.containsKey(key));

			MenuItem item = keyToMenuItemMap.get(key);
			for (OrderItem entry: order.getOrderItems()){
				if (item.getName().equals(entry.getItemName()))
					throw new Exception("You have added this item already!");
			}
			System.out.println("Enter the quantity: ");
			int quantity = scan.nextInt();
			if (quantity <=0) {System.out.println("No funny business!!"); return null;}
			String buffer = scan.nextLine();
			System.out.print("Do you want upsize? (y/n): ");
			boolean upSize = scan.nextLine().charAt(0) == 'y'? true : false;
			System.out.print("\n");

			OrderItem orderItem = new OrderItem(order.getOrderID(), item.getBranch(), item.getName(), quantity, quantity*item.getPrice(), upSize, order.getTakeAway(), order.getStatus());
			order.addItem(orderItem);
			branch.addOrderItem(order);
			System.out.println("Item succesfully added.");
			System.out.println("Your cart: ");
			order.displayOrderItems();
		}
		catch (Exception e){
			System.out.println(e.getMessage());
		}
		return null;
	}
    
	/**
 	* @return To add new item into cart
	*/
	public String description(){
		return ("To add new item into cart");
	}
}
