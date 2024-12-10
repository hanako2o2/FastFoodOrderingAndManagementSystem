package action;

import management.*;
import java.util.*;
import menu.*;

public class RemoveMenuItemAction implements Action{
    private Branch branch;
	private MenuItem menuItem;
	Scanner scan = new Scanner(System.in);

    /**
     * constructor for remove menu item action
     * @param branch
     */
    public RemoveMenuItemAction(Branch branch) {
        this.branch = branch;
    }

    /**
     * method to remove a menu item
     */
    public String execute() {
        Map<Character, MenuItem> keyToMenuItemMap;
        System.out.println(branch.getName() + "'s menu: ");
        keyToMenuItemMap = branch.menu.displayMenu(true);
        boolean flag = false;

        char key;
        System.out.println("Select an item to remove (a-z) or enter a non-valid item to exit editor: ");
        do {
            key = scan.next().charAt(0);
            String buffer = scan.nextLine();
            if (keyToMenuItemMap.containsKey(key)) {
                break;
            }
            return "";
        } while (true);

        menuItem = keyToMenuItemMap.get(key);

        do {
            System.out.println("Delete " + menuItem.getName() + "?");
            System.out.println("Confirm (y/n).");
            try {
                flag = scan.nextLine().charAt(0) == 'y'? true : false;
                break;
            } catch (Exception e) {
                return "";
            }
        } while (true);

        if (flag == true) {
            branch.removeMenuItem(menuItem);
            System.out.println("Item succesfully removed.");
            System.out.println(branch.getName() + "'s Menu: ");
            keyToMenuItemMap = branch.menu.displayMenu(true);
            return "";
        }
        return "";
    }


    /**
	* @return Remove a menu item
	*/
    public String description(){
        return ("Remove a menu item");
    }
}
