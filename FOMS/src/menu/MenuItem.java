package menu;

/**
* @author Boey, Madelyn, Owen, Rais, Rithika
* @version 25/4/2024
*/
public class MenuItem {
	    private String name;
	    private double price;
	    private String category;
	    private String branch;
		private boolean availability;

		/**
		 * Class constructor to create Menu Item object
   		 * @param name name of menu item
      		 * @param price price of menu item
	 	 * @param category category where menu item belongs
    		 * @param branch branch where menu item is available
       		 * @param availability availability of menu item
	 	 */
		public MenuItem() {}
	    public MenuItem(String name, double price, String category, String branch, boolean availability) {
	        this.setName(name);
	        this.setPrice(price);
	        this.setCategory(category);
	        this.setBranch(branch);
			this.setAvailability(availability);
	    }
		/**
  		 * @return name of menu item
 	 	 */
		public String getName() {
			return name;
		}

		/**
  		* a method to replace the name of a menu item
		* @param name new name for menu item
		*/
		public void setName(String name) {
			this.name = name;
		}

		/**
		* @return price of menu item
		*/
		public double getPrice() {
			return price;
		}

		/**
  		* a method to replace the price of a menu item
		* @param price new price of menu item
		*/
		public void setPrice(double price) {
			this.price = price;
		}

		/**
		* @return categoty where menu item belongs to
		*/
		public String getCategory() {
			return category;
		}

		/**
  		* a method to change the category of menu item
		* @param category new category of menu item
		*/
		public void setCategory(String category) {
			this.category = category;
		}

		/**
		* @return branch where menu item is available
		*/
		public String getBranch() {
			return branch;
		}

		/**
  		* a method to change the branch where a menu item is available
		* @param branch new branch for menu item
		*/
		public void setBranch(String branch) {
			this.branch = branch;
		}

		/**
		* @return availability of menu item
		*/
		public boolean getAvailability() {
			return availability;
		}

		/**
		* a method to change the availability of a menu item
		*/
		public void setAvailability(boolean availability) {
			this.availability = availability;
		}

}
