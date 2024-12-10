package action;

import java.util.Scanner;
import management.BranchManagement;
import order.*;
import management.*;
import java.util.*;
 
public class CollectFoodAction { 
    private Branch branch; 
    Scanner scanner = new Scanner(System.in);
 
    /**
     * constructor for Action
     * @param branch
     */
    public CollectFoodAction(Branch branch) { 
        this.branch = branch;
    } 

    /**
     * method to collect food
     * and removes order object
     */
    public void execute() { 
        try{
            Order order = null;
            System.out.println("Enter your OrderID: ");
            String orderId = scanner.nextLine();
            for (Order entry: branch.getOrderList().getMap().values()){
                if (entry.getOrderID().equals(orderId)){
                    order = entry;
                }
            }
            if (order == null){
                throw new Exception("No such OrderID");
            }
            if (order.getStatus().equals("Ready to pick up")) { 
                System.out.println("Your order is ready to be picked up"); 
                System.out.println("Please enter 'a' to pick up your order"); 
                
                Scanner scanner = new Scanner(System.in); 
                char pickup = scanner.nextLine().charAt(0); 
                if (pickup == 'a') { 
                    ; 
                    System.out.println("Order picked up successfully."); 
                } else { 
                    System.out.println("Invalid input. Please enter 'a' to pick up your order."); 
                } 
            } else { 
                System.out.println("The order is not ready for pickup yet."); 
            } 
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        
    } 
}