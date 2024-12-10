package management;

import java.util.*;
import menu.*;
import order.*;

/**
* @author Boey, Madelyn, Owen, Rais, Rithika
* @version 25/4/2024
*/
public class Branch {
	public Menu menu = new Menu();
    public OrderList order = new OrderList();

/**
 * name of branch
 */
	private String name;
/**
 * location of branch
 */
    private String location;
/**
 * quota of staff for that branch
 */
    private int staffQuota;
/**
 * quota of staff for that branch
 */
	private int staffCount;

    /**
	* a constructor for branch
 	* @param name name of branch
  	* @param location location of the branch
   	* @param staffQuota staff quota for that branch
	*/
    public Branch(String name, String location, int staffQuota, int staffCount) {
        this.name = name;
        this.location = location;
        this.staffQuota = staffQuota;
		this.staffCount = staffCount;
    }
    
	/**
	* a method to get a new order id
 	* @return new order id
	*/
    public String getNewOrderID(){
        int lastID = Integer.parseInt(order.getLargestOrderID());
        lastID++;
        return Integer.toString(lastID);
    }

	/**
	* a method to display the menu
 	* @return hashmap of chracters and menu items
	*/
    public Map<Character, MenuItem> displayMenu(){
        return menu.displayMenu();
    }

	/**
	* a method to display the order
	*/
    public void displayOrder(){
        order.displayOrderList();
    }

	/**
	 * get order object
	 * @param orderID
	 * @return
	 */
	public double isEmptyCart(String orderID) {
		return order.isEmptyCart(orderID);
	}

	/**
	* a method to display the order by id
	*/
    public void displayByOrderID(String orderID){
        order.displayOrderByID(orderID);
    }

	/**
	* a method to import the menu from the csv file
	*/
    public void importMenuOrder(String inputPath){
        menu.loadMenuFromCSV(inputPath + "/FOMS/src/CSVs/menu_list.csv", getName());
    }

	/**
	* a method to import the order from the csv file
	*/
    public void importOrderList(String inputPath){
        order.loadOrdersFromCSV(inputPath + "/FOMS/src/CSVs/order_list.csv", getName());
    }

	/**
	* a method to get the name of the branch
 	* @return name of branch
	*/
    public String getName() {
        return this.name;
    }

	/**
	* a method to get the location of the branch
 	* @return location of branch
	*/
    public String getLocation() {
        return location;
    }

	/**
	* a method to get the status of an order
 	* @return location of branch
	*/
	public String getStatus(String orderID){
		return order.getStatus(orderID);
	}

	/**
	* a method to get the staff quota of the branch
 	* @return staff quota of that branch
	*/
    public int getStaffQuota() {
        return staffQuota;
    }

	/**
	* a method to get the staff count of the branch
 	* @return staff count of that branch
	*/
	public int getStaffCount() {
        return staffCount;
    }

	/**
	* a method to get the staff count of the branch
 	* @return staff count of that branch
	*/
	public void setStaffCount(int staffCount) {
        this.staffCount = staffCount;
    }
	
	/**
	* a method to add an order item
 	* @param oder order getting added
	*/
    public void addOrderItem(Order order){
        this.order.addOrderItem(order);
    }
    
	/**
	* a method to add a menu item
 	* @param item item getting added
	*/
    public void addMenuItem(MenuItem item) {
    	//add key in hash map
    	this.menu.setMenuItem(item);
    };

	/**
 	* a method to edit a menu item
  	* @param item item getting edited
 	*/
    public void editMenuItem(MenuItem item) {
    	//update csv
        this.menu.setMenuItem(item);
    };
    
	/**
	* a method to remove a menu item
	*/
    public void removeMenuItem(MenuItem item) {
    	this.menu.removeMenuItem(item);
    	//update csv
    };

	/**
	* a method to process the order
	*/
    public void processOrder(){
        order.processOrder();
    }

	/**
	* a method to update orders of branch to csv
	*/
	public void updateOrdersToCSV(String filePath, boolean flag){
		order.updateOrdersToCSV(filePath, flag);
	}

	/**
	* a method to update menu list of branch to csv
	*/
	public void updateMenuToCSV(String filePath, boolean flag){
		menu.updateMenuToCSV(filePath, flag);
	}

	/**
	 * 
	 * @return specific order
	 */
	public OrderList getOrderList(){
		return order;
	}
}