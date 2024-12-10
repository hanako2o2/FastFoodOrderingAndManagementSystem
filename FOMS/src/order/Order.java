/** requires functionality for Customers to:
 * place orders
 * customize food items (method of OrderItem)
 * choose between takeaway or dine-in
 *
 * Orders are automatically cancelled and removed from the "ready to pick up"
 * list if not picked up within a specified timeframe
 *
 * Facilitate the selection of items, customization, and confirmation of orders.
 * Item, quantity, price calculation.
 * Payment
 */

package order;
import java.util.*;

/**
* @author Boey, Madelyn, Owen, Rais, Rithika
* @version 25/4/2024
*/
public class Order{
    private double totalPrice;
    private String orderID;
    private String status;
    private boolean takeaway;
    private Map<String, OrderItem> orderLists = new HashMap<>();

    /**
     * default constructor for Order object
     */
    public Order() {}
    /**
	 * Class constructor to create Order object
  	`* @param orderID ID of Order object
   	 * @param status status of order
     * @param takeaway binary representation of whether is takeaway or not takeaway
 	 */
    public Order(String orderID, String status, boolean takeaway){
        this.orderID = orderID;
        this.status = status;
        this.takeaway = takeaway;
        totalPrice = 0;
    }

    /**
     * a method to change the order's ID
     *@param orderID new ID for order
     */
    public void setOrderID(String orderID){
        this.orderID = orderID;
    }

    /**
    * @return order ID
    */
    public String getOrderID(){
        return this.orderID;
    }

    /**
    * @return total price
    */
    public double getTotalPrice(){
        return totalPrice;
    }

    /**
    * @return status of order
    */
    public String getStatus(){
        return status;
    }

    /**
    * a method to change the status of an order
    * @param status new status of order
    */
    public void setStatus(String status){
        this.status = status;
        for (OrderItem entry: orderLists.values()){
            entry.setStatus(status);
        }
    }


/**
* @return a boolean expression of whether the order is takeaway or not takeaway
*/
    public boolean getTakeAway(){
        return takeaway;
    }

/**
* a method to change whether an order is takeaway or not takeaway
* @param takeAway new takeaway status of order
*/
    public void setTakeAway(boolean takeAway){
        this.takeaway = takeAway;
    }

    /**
     * a method to add item onto the orderList
     * @param orderItem from OrderItem
     */
    public void addItem(OrderItem orderItem){
        orderLists.put(orderItem.getItemName(), orderItem);
        this.updatePrice(computeTotalPrice());
    }

    /**
     * a method to remove item onto the orderList
     * @param orderItem from OrderItem
     */
    public void removeItem(OrderItem orderItem){
        orderLists.remove(orderItem.getItemName(), orderItem);
        this.updatePrice(computeTotalPrice());
    }

    /**
     * a method to update subtotal in order list
     * @param itemprice itemprice of specific order item from order list
     */
    public void updatePrice(double itemprice){
        this.totalPrice = itemprice;
    }

     /**
     * a method to update details of specific order item in order list
     * @param orderItem from OrderItem
     * @param quantity quantity of certain item  
     * @param upSize whether upsize is chosen
     */
    public void customize(OrderItem orderItem, int quantity, boolean upSize){
        orderItem.customize(quantity, upSize);
        this.updatePrice(computeTotalPrice());
    }

     /**
     * a method to compute total payment 
     * take values from HashMap OrderLists
     * @return totalPrice 
     */
    public double computeTotalPrice(){
        double totalPrice = 0;
        for (OrderItem entry: orderLists.values()){
            totalPrice = totalPrice + entry.getPrice();
        }
        return totalPrice;
    }

     /**
     * a method to display all order items in the order list
     * creates a hash map that has character in alphabetical order as key and order items as item
     * @return keyToOrderItemMap 
     */
    public Map<Character, OrderItem> displayOrderItems(){
        Scanner scanner = new Scanner(System.in);
        List<Character> freeChars = new ArrayList<Character>();
        Map<Character, OrderItem> keyToOrderItemMap = new HashMap<>();
    
        for (char j = 'a'; j <= 'z'; j++) {
            freeChars.add(j);
        }
        System.out.println("OrderID " + getOrderID());
        for (OrderItem entry: orderLists.values()) {
            char c = freeChars.get(0);
            freeChars.remove(Character.valueOf(c));
            keyToOrderItemMap.put(c, entry);
            System.out.println(c + ": " + entry.getQuantity() + " " + entry.getItemName() +  " Subtotal: " + String.format("%.2f", entry.getPrice()) +  " Upsize: " + entry.getUpSize());
        }
        System.out.println("Total price: " + String.format("%.2f", totalPrice));
        return keyToOrderItemMap;
    }

    /**
     * to get an list of order item to return in order list for csv conversion
     * @return order items list
     */
    public List<OrderItem> getOrderItems() {
        return new ArrayList<>(orderLists.values());
    }
}
