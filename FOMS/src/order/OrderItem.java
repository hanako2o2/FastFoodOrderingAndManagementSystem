package order;

/**
* @author Boey, Madelyn, Owen, Rais, Rithika
* @version 25/4/2024
*/
public class OrderItem{
    private String OrderId;
	private String branch;
    private String itemName;
    private int quantity;
    private double price;
    private boolean upSize;
	private boolean takeaway;
	private String status;


	/**
	 * Class constructor to create OrderItem object
  	 * @param OrderID ID of OrderItem
    	 * @param branch branch where OrderItem is located
      	 * @param itemName name of the item
	 * @param quantity quantity of the item
  	 * @param price price of the item
    	 * @param upSize whether the item is upsize
      	 * @param takeaway whether the item is takeaway
	 * @param status status of item
 	 */
    public OrderItem(String OrderId, String branch, String itemName, int quantity, double price, boolean upSize, boolean takeaway, String status){
        this.setOrderId(OrderId);
	this.setBranch(branch);
        this.setItemName(itemName);
        this.setQuantity(quantity);
        this.setPrice(price);
        this.setUpSize(upSize);
		this.takeaway = takeaway;
		this.status = status;
   	 }

	/**
 	 * method to update order item in customisation for editing order item
   	 * @param quantity quantity of specific order item
     	 * @param upSize whether upSize is selection
    	 */
	public void customize(int quantity, boolean upSize){
		double originalQuantity = getQuantity();
		setPrice((quantity/originalQuantity)*getPrice());
		setQuantity(quantity);
		setUpSize(upSize);
	}

	/**
	 * @return order ID
	 */
	public String getOrderId() {
		return OrderId;
	}

	/**
	* a method to change the order ID
	* @param orderId new order ID
	*/
	public void setOrderId(String orderId) {
		OrderId = orderId;
	}

	/**
	* @return branch where item is located
	*/
	public String getBranch(){
		return branch;
	}

	/**
	* a method to change the branch where an item is located
	* @param branch new branch
	*/
	public void setBranch(String branch){
		this.branch = branch;
	}

	/**
	* @return name of item
	*/
	public String getItemName() {
		return itemName;
	}

	/**
	* a method to change the name of an item
	* @param itemName new name of the item
	*/
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	/**
	* @return quantity of the item
	*/
	public int getQuantity() {
		return quantity;
	}

	/**
	* a method to change the quantity of an item
 	* @param quantity new quantity of the item
	*/
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	/**
	* @return whether order is upsized
	*/
	public boolean getUpSize() {
		return upSize;
	}

	/**
	* a method to change whether an item is upsized
	* @param upSize new upsized status
	*/
	public void setUpSize(boolean upSize) {
		this.upSize = upSize;
	}

	/**
	* @return price of item
	*/
	public double getPrice() {
		return price;
	}

	/**
	* a method to change the price of an item
 	* @param price new price of item
	*/
	public void setPrice(double price) {
		this.price = price;
	}

	/*
	* @return whether the item is takeaway
	*/
	public boolean getTakeAway(){
		return takeaway;
	}

	/**
	* a method to change whether an item is takeaway
	* @param takeaway new takeaway status
	*/
	public void setTakeAway(boolean takeaway){
		this.takeaway = takeaway;
	}

	/**
	* @return status of item
	*/
	public String getStatus(){
		return status;
	}

	/**
	* a method to change the status of an item
	* @param status new status of item
	*/
	public void setStatus(String status){
		this.status = status;
	}
}
