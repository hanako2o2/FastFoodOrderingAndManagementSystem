package action; 
 
import management.*; 
import Exception.*; 
import order.Order;
 
public class TrackOrderAction implements Action { 
    private String orderID; 
    private Branch branch; 

    /**
     * constructor for track order action
     * @param orderID
     * @param branch
     */
    public TrackOrderAction(String orderID, Branch branch) { 
        this.orderID = orderID; 
        this.branch = branch; 
    } 
 
    @Override 
    /**
     * method to track an order's status
     */
    public String execute() { 
        try { 
            String status = branch.getStatus(orderID); 
            double orderPrice = branch.isEmptyCart(orderID);
            if (status != null) { 
                if (orderPrice == 0) {
                    throw new NonexistentOrderID("No such OrderID");
                }
                System.out.println(status); 
                return ""; 
            } else { 
                throw new NonexistentOrderID("No such OrderID"); 
            } 
        } catch (NonexistentOrderID e) { 
            System.out.println( e.getMessage()); 
        }
        return "";
    } 
 
    @Override 
    public String description() { 
        return "Track Order Action: Track the status of an order."; 
    } 
}