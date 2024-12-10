package user;

import action.*;
import java.util.*;

/**
* @author Boey, Madelyn, Owen, Rais, Rithika
* @version 25/4/2024
*/

public class StaffGeneral implements ActionCapable{
    private String name;
    private char role ;
    private char gender;
    private int age;
    private String branch;
    private String staffID;
    private String password;
	
	/**
	 * Class constructor to create StaffGeneral objects
	 * Extends from StaffGeneral
  	 * @param name name of staff
    	 * @param role role of staff
      	 * @param gender gender of staff
	 * @param age age of staff
  	 * @param branch branch of staff
     	 * @param staffID staff id
         * @param password password
	 */
    public StaffGeneral(String name, char role, char gender, int age, String branch, String staffID, String password){
		this.name = name;
        this.role = role;
        this.gender = gender;
        this.age = age;
        this.branch = branch;
        this.staffID = staffID;
        this.password = password;
	}

	/**
	 * validate whether password is correct
  	 * @param password password of Staff
    	 * @return boolean success
 	 */
    public boolean validate(String password){
        if (getStaffPW().equals(password)){
            System.out.println("\npassword succesfully validated");
            return true;
        }
        else return false;
    }
	
	/**
	 * validate whether is default password
    	 * @return boolean success
 	 */
    public boolean isDefaultPassword(){
        if (this.password.equals("password")) return true;
        return false;
    }
	
	/**
	 * @return name of staff
 	 */
    public String getName(){
        return name;
    }

	/**
 	* @return role of staff
  	*/
    public char getRole() {
        return role;
    }
	/**
 	* @return gender of staff
  	*/
    public char getGender() {
        return gender;
    }
/**
 	* @return age of staff
  	*/
    public int getAge() {
        return age;
    }
/**
 	* @return branch of staff
  	*/
    public String getBranch() {
        return branch;
    }

	/**
 	* @return id of staff
  	*/
    public String getStaffID() {
        return staffID;
    }

	/**
 	* @return password of staff
  	*/
    public String getStaffPW() {
        //forgot password method (sidequest)
        return password;
    }

/**
 	* @param new role
  	*/
    public void setRole(char role) {
        this.role = role;
    }

	/**
 	* @param gender new gender
  	*/
    public void setGender(char gender) {
        this.gender = gender;
    }

	/**
 	* @param age new age
  	*/
    public void setAge(int age) {
        this.age = age;
    }

	/**
 	* @param branch new branch
  	*/
    public void setBranch(String branch) {
        this.branch = branch;
    }

	/**
 	* @param staffID new id
  	*/
    public void setStaffID(String staffID) {
        this.staffID = staffID;
    }

	/**
 	* @param password new password
  	*/
    public void setStaffPW(String password) {
        this.password= password;
    }

    public void printStaffDetails(){
        System.out.println("StaffID " + getStaffID());
        System.out.println("1. Name: " + getName());
        System.out.println("2. Age: " + getAge());
        System.out.println("3. Gender: " + getGender());
        System.out.println("4. Branch: " + getBranch());
        System.out.println("5. Role: " + getRole());
        System.out.print("\n");
    }

	/**
	 * List of actions that can be performed by StaffGeneral
  	 * Overrides allowableActions interface from Action class and add the relevant actions
    	 * @return list of action that can be executed by the user
 	 */
    @Override
	public List<Action> allowableActions() {
		// TODO Auto-generated method stub
		return null;
	}
}
