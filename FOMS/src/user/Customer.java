/* Can browse the menu, customize orders, make payments, and track order status.
 * collect food after teh order status becomes "ready to pick up"
 *
 *
 * Select “customer” between “customer” and “staff” options.
 * Select branch.
 * Check placed order status using order ID.
 *
 * Organized display of menu items.
 * Add, edit, delete menu items from the cart.
 * Choose either takeaway or dine-in for the order.
 * Check out cart.
 * Make payment for the order (no need to implement, just
 * have an option for them to simulate payment).
 * Print receipt with order ID.
 * Collect food to make the order status change from “Ready
 * to pickup” to “completed”.
 */


package user;

import java.util.ArrayList;
import java.util.List;

import order.*;
import action.*;
import management.*;

/**
* @author Boey, Madelyn, Owen, Rais, Rithika
* @version 25/4/2024
*/

public class Customer implements ActionCapable{
	private Order order;
	private Branch currentBranch;

	/**
	 * Intialise the branch of the customer 
  	 * @param branch branch from Customer class
	 */
	public Customer(Branch branch){
		this.currentBranch = branch;
	}

	/**
 	 * Create new order using orderID and takeaway
   	 * @param orderID ID of Order
     	 * @param takeaway a binary value for whether they having for takeaway
   	 */
	public void newOrder(String orderId, int takeaway){
		if (takeaway == 1)
			order = new Order(orderId, "Not Ready", false);
		else
			order = new Order(orderId, "Not Ready", true);
	}
	
	/**
	 * List of actions that can be performed by Branch Manager
  	 * Overrides allowableActions interface from Action class and add the relevant actions
    	 * @return list of action that can be executed by the user
 	 */
	@Override
	public List<Action> allowableActions() {
		List<Action> actions = new ArrayList<>();
		actions.add(new DisplayCartAction(this.order));
		actions.add(new AddOrderItemAction(this.currentBranch, this.order));
		actions.add(new EditOrderAction(this.order));
		actions.add(new RemoveOrderAction(this.order));
		actions.add(new CheckoutAction(this.order));
		actions.add(new ExitActionCustomer());
		return actions;
	}
}
