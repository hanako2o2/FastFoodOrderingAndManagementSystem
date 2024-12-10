package action;

import management.*;
import java.util.*;
import menu.*;

/**
 * @author Boey, Madelyn, Owen, Rais, Rithika
 * @version 25/4/2024
 */
public class AddMenuItemAction implements Action{
    private Branch branch;
    private MenuItem menuItem = new MenuItem();
    Scanner scan = new Scanner(System.in);

    /**
     * a constructor for AddMenuItemAction()
     * @param branch target branch
     */
    public AddMenuItemAction(Branch branch) {
        this.branch = branch;
    }

    /**
     * a method to add menu items
     * @return String
     */
    public String execute() {
        Map<Character, MenuItem> keyToMenuItemMap;

        System.out.println(branch.getName() + "'s menu: ");
        boolean menuOverride = true;
        keyToMenuItemMap = branch.menu.displayMenu(menuOverride);
        boolean flag = false;
        do {
            try {
                System.out.println("Enter name of menu item you would like to add: ");
                menuItem.setName(scan.nextLine());
                if (branch.menu.getMenuItem(menuItem.getName()) != null) {
                    System.out.println("Menu item already exists!");
                    break;
                }

                System.out.println("Enter price of menu item you would like to add: ");
                menuItem.setPrice(scan.nextDouble());
                scan.nextLine();

                System.out.println("Enter category of menu item you would like to add: ");
                menuItem.setCategory(scan.nextLine());   

            } catch (Exception e) {
                System.out.println("No funny business please! Run along now...");
                return "";
            }

            menuItem.setBranch(branch.getName());
            menuItem.setAvailability(menuOverride);

            System.out.println("Item name: " + menuItem.getName());
            System.out.println("Item price: " + menuItem.getPrice());
            System.out.println("Item category: " + menuItem.getCategory());
            do {
                System.out.println("Confirm (y/n).");
                try {
                    flag = scan.nextLine().charAt(0) == 'y'? true : false;
                    break;
                } catch (Exception e) {
                    return "";
                }
            } while (true);
        } while (flag == false);

        if (flag == true) {
            branch.editMenuItem(menuItem);
            System.out.println("Item succesfully added.");
            System.out.println(branch.getName() + "'s Menu: ");
            keyToMenuItemMap = branch.menu.displayMenu(menuOverride);
            return "";
        }
        return "";
    }

    /**
     * @return Add a menu item
     */
    public String description(){
        return ("Add a menu item");
    }
}
