package action;

import management.*;
import java.util.*;
import menu.*;

/**
* @author Boey, Madelyn, Owen, Rais, Rithika
* @version 25/4/2024
*/
public class EditMenuAction implements Action {
    private Branch branch;
    Scanner scan = new Scanner(System.in);

    /**
    * a constructor for the EditMenuAction class
    * @param branch target branch
	*/
    public EditMenuAction(Branch branch) {
        this.branch = branch;
    }

	/**
 	* a method to edit menu
	* @return String
	*/
    public String execute() {
        Map<Character, MenuItem> keyToMenuItemMap;

        System.out.println(branch.getName() + "'s menu: ");
        boolean menuOverride = true;
        keyToMenuItemMap = branch.menu.displayMenu(menuOverride);

        char key;
        System.out.println("Select an item to edit (a-z) or enter a non-valid item to exit editor: ");
        do {
            key = scan.next().charAt(0);
            String buffer = scan.nextLine();
            if (keyToMenuItemMap.containsKey(key)) {
                break;
            }
            return "";
        } while (true);

        MenuItem item = keyToMenuItemMap.get(key);
        double price = item.getPrice();
        boolean availability = item.getAvailability();

        char selection;
        do{
            System.out.println("What would you like to edit?");
            System.out.println("a: Price");
            System.out.println("b. Availability");
            System.out.println("c. Go back");
            selection = scan.nextLine().charAt(0);
            switch(selection){
                case 'a':
                    int flag = 0;
                    do {
                        System.out.println("Current price: " + price);
                        System.out.printf("Enter new price: ");
                        try {
                            price = scan.nextDouble();
                            flag = 1;
                        } catch (Exception e) {
                            System.out.println("Enter a number for price: ");
                            scan.nextLine();
                        }
                    } while (flag == 0);
                    
                    String buffer = scan.nextLine();
                    break;

                case 'b':
                    do {
                        System.out.println("Current item availability: " + availability);
                        System.out.println("Set Availability of item (y/n): ");
                        try {
                            availability = scan.nextLine().charAt(0) == 'y'? true : false;
                            break;
                        } catch (InputMismatchException e) {
                            System.out.println("Enter \"y\" or \"n\"");
                        }
                    } while (true);
            }
        }while(selection != 'c');
        
        item.setAvailability(availability);
        item.setPrice(price);
        branch.editMenuItem(item);
        System.out.println("Item succesfully edited.");
        System.out.println("Menu: ");
		keyToMenuItemMap = branch.menu.displayMenu(menuOverride);

        return "";
    }

	/**
	* @return Edit a menu item
	*/
    public String description(){
        return ("Edit a menu item");
    }
}
