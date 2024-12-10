package page;

import java.util.Scanner;

import management.*;
import user.*;
import order.*;
import display.*;
import action.*;

public class CustomerPage {
    private boolean exit = false;
    
    public void customerView(String inputPath) {
        Scanner scan = new Scanner(System.in);
        int selection;
        Branch currentBranch;
        boolean flag = false;

        /*
         * currentBranch stores the specific branch that customer is at right now.
         */
        currentBranch = BranchManagement.branchSelection();

        /*
         * Instantiate customer object
         */
        Customer customer = new Customer(currentBranch);

         /*
         * Depending on new customer or current customer, choose appropriate selection.
         * Create new order will invoke a series of actions that can be done by the customer
         * Track order allows customer to input orderID and track order status and collect order
         */
        do {
            resetExitStatus();
            System.out.println("Do you want to create a new order or track current order?");
            System.out.println("1: Create new order");
            System.out.println("2: Track current order");
            System.out.println("3: Pick up your order");
            System.out.println("4: Exit");
            
            selection = scan.nextInt();
            System.out.print("\n");
            switch (selection) {
                case 1:
                    System.out.println("Create new order selected");
                    System.out.println("Are you dining in or taking away?");
                    System.out.println("1: Dining In");
                    System.out.println("2: Takeaway");
                    int selection2;
                    do{
                        selection2 = scan.nextInt();
                        System.out.print("\n");
                    }while(selection2 != 1 && selection2 != 2);
                    customer.newOrder(currentBranch.getNewOrderID(), selection2);
                    while(!exit){
                        System.out.println("Select actions:");
                        Action action = displayAction.showMenu(customer.allowableActions());
                        String status = action.execute();
                        System.out.print("\n");
                        if(status != null && status.equals("Triggered"))
                            triggerExitStatus();
                    }
                    break;
                case 2:
                  System.out.println("Track current order selected");
                  System.out.println("Please input your order ID:");
                    // Create an instance of Order with the desired order ID
                    String buffer = scan.nextLine();
                    String orderID = scan.nextLine();
                    TrackOrderAction trackOrderAction = new TrackOrderAction(orderID, currentBranch);
                    trackOrderAction.execute();
                    break;
                case 4:
                    System.out.println("Exiting...");
                    
                    for (Branch branch: BranchManagement.getBranchList()){
                        branch.updateOrdersToCSV(inputPath + "/FOMS/src/CSVs/order_list.csv", flag);
                        flag = true;
                    }
                    break;
                case 3:
                    CollectFoodAction action = new CollectFoodAction(currentBranch);
                    action.execute();
                    break;
                default:
                    System.out.println("Unavailable selection");
            }
        } while (selection != 4);
    }

    /**
     * make exit status true to exit loop
     */
    public void triggerExitStatus(){
        exit = true;
    }

    /**
     * make exit status false to able to enter loop
     */
    public void resetExitStatus(){
        exit = false;
    }
}