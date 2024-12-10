/*Ability to add, update, or remove menu items, including prices, descriptions, and item categories */

package menu;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
* @author Boey, Madelyn, Owen, Rais, Rithika
* @version 25/4/2024
*/
public class Menu {
    private Map<String, MenuItem> menuItems;

    /**
	 * Intialise menu items into a hash map with its name as the key
 	 */
    public Menu() {
        menuItems = new HashMap<>();
    }

	/**
	 * load menu_list.csv based on the branch selected
  	 * @param local file path and branch selected from either customer or staff
    	 * @param branchSelected branch selected
 	 */
    public void loadMenuFromCSV(String filePath, String branchSelected) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 5) { // Assuming CSV has 4 columns: name, price, category, branch
                    String name = parts[0].trim();
                    double price = Double.parseDouble(parts[1].trim());
                    String category = parts[2].trim();
                    String branch = parts[3].trim();
                    boolean availability = parts[4].equals("Y") ? true : false;
                    if (branch.equals(branchSelected)){
                        MenuItem menuItem = new MenuItem(name, price, category, branch, availability);
                        menuItems.put(name, menuItem); // Store item in HashMap using name as key
                    }
                } else {
                    System.out.println("Invalid line: " + line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

	/**
	 * Retrieve a specific menu item based on its name
  	 * @param item name of menu item
    	 * @return specific menu item with its name
 	 */
    public MenuItem getMenuItem(String itemName) {
        return menuItems.get(itemName);
    }

	/**
 	* a method to set a menu item
 	* @param item name of menu item
  	*/
    public void setMenuItem(MenuItem item) {
        menuItems.computeIfAbsent(item.getName(), key -> new MenuItem(item.getName(),item.getPrice(),item.getCategory(),item.getBranch(),item.getAvailability()));
        
        if (menuItems.containsKey(item.getName())) {
            menuItems.replace(item.getName(), item);
        }

    }

    public void removeMenuItem(MenuItem item) {
        menuItems.remove(item.getName());
    }

	/**
 	*
  	*/
	public Map<String, MenuItem> getMenuItems() { //for printing of method by branch
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * display menu from Hash Map with alphabetical character as index
  	 * @return keyToItemMap
 	 */
    public Map<Character, MenuItem> displayMenu(){
        Scanner scanner = new Scanner(System.in);
        List<Character> freeChars = new ArrayList<Character>();
        Map<Character, MenuItem> keyToMenuItemMap = new HashMap<>();
    
        for (char j = 'a'; j <= 'z'; j++) {
            freeChars.add(j);
        }
        for (MenuItem entry: menuItems.values()) {
            char c = freeChars.get(0);
            freeChars.remove(Character.valueOf(c));
            keyToMenuItemMap.put(c, entry);
            if (entry.getAvailability() == true) {
                System.out.println(c + ": " + entry.getName() + ", Price: " + String.format("%.2f", entry.getPrice()) + ", Category: " + entry.getCategory()); 
            }    
        }
        return keyToMenuItemMap;
    }

	/**
 	* overloading displayMenu with an override
	* @param override override
 	* @return keyToMenuItemMap
	*/
    public Map<Character, MenuItem> displayMenu(boolean override){
        Scanner scanner = new Scanner(System.in);
        List<Character> freeChars = new ArrayList<Character>();
        Map<Character, MenuItem> keyToMenuItemMap = new HashMap<>();
    
        for (char j = 'a'; j <= 'z'; j++) {
            freeChars.add(j);
        }
        for (MenuItem entry: menuItems.values()) {
            char c = freeChars.get(0);
            freeChars.remove(Character.valueOf(c));
            keyToMenuItemMap.put(c, entry);
            System.out.println(c + ": " + entry.getName() + ", Price: " + String.format("%.2f", entry.getPrice()) + ", Category: " + entry.getCategory() + ", Availability: " + entry.getAvailability());
        }
        return keyToMenuItemMap;
    }

    /**
     * Update branches to csv
     */
    public void updateMenuToCSV(String filePath, boolean flag) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, flag))) {
            // Iterate over the menu items
            for (MenuItem menuItem : menuItems.values()) {
                // Write menu item details to CSV
                writer.write(formatMenuItemDetails(menuItem));
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * format into csv
     * @param MenuItem menuItem
     */
    private String formatMenuItemDetails(MenuItem menuItem) {
        return menuItem.getName() + "," +
        String.format("%.2f", menuItem.getPrice()) + "," +
               menuItem.getCategory() + "," +
               menuItem.getBranch() + "," +
               (menuItem.getAvailability() ? "Y" : "N");
    }
}
