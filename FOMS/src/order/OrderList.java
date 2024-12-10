package order;
import java.util.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.*;

/**
* @author Boey, Madelyn, Owen, Rais, Rithika
* @version 25/4/2024
*/
public class OrderList{
    private Map<String, Order> orderLists;
    

    /**
     * initialize HashMap for order list for one customer
     */
    public OrderList(){
        orderLists = new HashMap<>();
    }

    /**
     * load order_list.csv
     * @param filePath local file path 
     * @param branchSelected branch selected either from customer or staff
     */
    public void loadOrdersFromCSV(String filePath, String branchSelected) {
        Path path = Paths.get(filePath);
        try (BufferedReader br = Files.newBufferedReader(path)) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                String orderId = values[0];
                String branch = values[1];
                String itemName = values[2];
                int quantity = Integer.parseInt(values[3]);
                double price = Double.parseDouble(values[4]);
                boolean upSize = values[5].equals("Y") ? true : false;
                boolean takeaway = values[6].equals("Y") ? true : false;
                String status = values[7];
                if (branch.equals(branchSelected)){
                    OrderItem item = new OrderItem(orderId, branch, itemName, quantity, price, upSize, takeaway, status);
                    Order order = orderLists.computeIfAbsent(orderId, id -> new Order(id, item.getStatus(), item.getTakeAway()));
                    order.addItem(item);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * add order item into order list
     * @param order order from Order
     */
    public void addOrderItem(Order order){
        orderLists.put(order.getOrderID(), order);
    }

    /**
     * display all orders for staff usage
     */
    public void displayOrderList() { 
        for (Order entry: orderLists.values()) { 
            System.out.println("\nOrderID" + " " + entry.getOrderID() + ":" + entry.getStatus());
            entry.displayOrderItems();
        } 
    }

    public String getStatus(String orderID){
        for (Order order: orderLists.values()){
            if (order.getOrderID().equals(orderID)){
                return order.getStatus();
            }
        }
        return "No such orderID";
    }

    /**
     * allow staff to process order and updates order status
     */
    public void processOrder() {
        System.out.println();
        System.out.println("Please select order to process by typing in an OrderID");
        Scanner scanner = new Scanner(System.in);

        String orderID = scanner.nextLine();
        if (orderLists.containsKey(orderID)) {
            Order order = orderLists.get(orderID);

            order.setStatus("Ready To Pick Up");
            System.out.println("OrderID " + orderID + "'s status has been set to \"Ready To Pick Up\"");
            System.out.println();
        }
        else {
            System.out.println("OrderID does not exist");
            System.out.println();
        }
    }

    public double isEmptyCart(String orderID) {
        if (orderLists.containsKey(orderID)) { 
            Order order = orderLists.get(orderID);
            return order.getTotalPrice();
        } else {
            return 0;
        }
    }

    /**
     * displays specific order from a branch using a order ID
     * @param orderID orderID from Order Lists
     */
    public void displayOrderByID(String orderID) { 
        if (orderLists.containsKey(orderID)) { 
            Order order = orderLists.get(orderID);
            System.out.println("OrderID: " + order.getOrderID() + ":");
            order.displayOrderItems();
        } else { 
            System.out.println("Order with ID " + orderID + " not found."); 
        } 
    }

    /**
     * gets the current largest order ID exist in the Hash Map
     */
    public String getLargestOrderID() {
        String largestOrderID = null;
        if (!orderLists.isEmpty()) {
            for (String orderID : orderLists.keySet()) {
                if (largestOrderID == null || orderID.compareTo(largestOrderID) > 0) {
                    largestOrderID = orderID;
                }
            }
        } else {
            largestOrderID = "0";
        }
        return largestOrderID;
    }

    /**
     * method to get order items into a list for csv conversion
     * @return specific order item
     */
    public List<OrderItem> getOrderItems() {
        List<OrderItem> orderItems = new ArrayList<>();
        for (Order order : orderLists.values()) {
            orderItems.addAll(order.getOrderItems());
        }
        return orderItems;
    }

    /**
     * Update orders to csv
     */

    public void updateOrdersToCSV(String filePath, boolean flag) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, flag))) {
            for (Order order : orderLists.values()) {
                for (OrderItem item : order.getOrderItems()) {
                    writer.write(formatOrderItemDetails(order.getOrderID(), item));
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * format into csv
     * @param orderID of order, items of order
     */

    private String formatOrderItemDetails(String orderId, OrderItem item) {
        return orderId + "," +
               item.getBranch() + "," +
               item.getItemName() + "," +
               item.getQuantity() + "," +
               String.format("%.2f", item.getPrice()) + "," +
               (item.getUpSize() ? "Y" : "N") + "," +
               (item.getTakeAway() ? "Y" : "N") + "," +
               item.getStatus();
    }

    /**
     * 
     * @return return orderLists
     */
    public Map<String, Order> getMap(){
        return orderLists;
    }
}
