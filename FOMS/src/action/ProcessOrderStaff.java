package action;

import management.*;
import order.*;

import java.util.Map;
import java.util.Scanner;
import java.util.Timer; 
import java.util.TimerTask; 

/**
* @author Boey, Madelyn, Owen, Rais, Rithika
* @version 25/4/2024
*/
public class ProcessOrderStaff implements Action{
	private Branch branch;
	private Timer timer = new Timer(); 
	
	/**
	* a contructor for ProcessOrderStaff
 	* @param branch target branch
	*/
	public ProcessOrderStaff(Branch branch){
		this.branch = branch;
	}

	/**
	 * method to start the timer countdown before order is removed
	 * @param map
	 * @param order
	 */
	public void startTimer(Map<String, Order> map, Order order) { 
        TimerTask task = new TimerTask() { 

            public void run() { 
                System.out.println("<Notification! >" + "Order " + order.getOrderID()+ " has been automatically completed(uncollected)."); 
				if (map.containsKey(order.getOrderID()))
				map.remove(order.getOrderID());
            } 
        }; 
 
        timer.schedule(task, 60000); 
	}
	/**
	* a method to process the order
 	* @return String
	*/
	public String execute(){
		System.out.println();
        System.out.println("Please select order to process by typing in an OrderID");
        Scanner scanner = new Scanner(System.in);

        String orderID = scanner.nextLine();
        for (Order entry: branch.getOrderList().getMap().values()) {
            if(entry.getOrderID().equals(orderID)){
				entry.setStatus("Ready To Pick Up");
				System.out.println("OrderID " + orderID + "'s status has been set to \"Ready To Pick Up\"");
				System.out.println();
				startTimer(branch.getOrderList().getMap(), entry);
				return "";
			}
        }
        System.out.println("OrderID does not exist");
        System.out.println();
		return "";
	}

	/**
	* @return Process an order
	*/
	public String description(){
		return ("Process an order");
	}
}
