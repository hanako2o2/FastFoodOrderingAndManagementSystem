//a bit unclear of how to implement branchmanager and manager (look in pdf)
//so i just add this class first
package management;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import Exception.*;
import java.util.*;
import user.*;

/**
 * @author Boey, Madelyn, Owen, Rais, Rithika
 * @version 25/4/2024
 */
public class BranchManagement {
    private static StaffGeneral admin;
    private static Map<Branch, List<StaffGeneral>> branchMap; 

	/**
 * a constructor for the class
 */
    public BranchManagement() { 
        branchMap = new HashMap<>(); 
    } 
    
    /**
     * a method used to import the branches from the CSV file
     * @param filePath Path of CSV file
     */
    public void importBranchesFromCSV(String filePath) { 
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) { 
            String line; 
            while ((line = br.readLine()) != null) { 
                String[] parts = line.split(","); 
                if (parts.length == 4) { 
                    String name = parts[0].trim(); 
                    String location = parts[1].trim(); 
                    int staffQuota = Integer.parseInt(parts[2].trim()); 
                    int staffCount = Integer.parseInt(parts[3]);
                    Branch branch = new Branch(name, location, staffQuota, staffCount); 
                    // Use the branch object as the key 
                    branchMap.put(branch, new ArrayList<>()); 
                } else { 
                    System.out.println("Invalid line: " + line); 
                } 
            } 
        } catch (IOException e) { 
            e.printStackTrace(); 
        } 
    } 
     
    /**
     * a method used to import the staff from the CSV file
     * @param filePath Path of CSV file
     */
    public void importStafflistFromCSV(String filePath) { 
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) { 
            String line; 
            while ((line = br.readLine()) != null) { 
                String[] parts = line.split(","); 
                if (parts.length == 7) { 
                    String name = parts[0].trim(); 
                    String staffLoginID = parts[1].trim(); 
                    char role = parts[2].trim().charAt(0); 
                    char gender = parts[3].trim().charAt(0); 
                    int age = Integer.parseInt(parts[4].trim()); 
                    String branchName = parts[5].trim(); 
                    String password = parts[6].trim();
                    /*swtich case to determine what kind of class to instantiate*/
                    StaffGeneral stafftemp = null;
                    switch (role) { 
                        case 'S': 
                         Staff staff = new Staff(name, role, gender, age, branchName, staffLoginID, password); 
                         stafftemp = staff; 
                         break; 
                        case 'M': 
                         BranchManager manager = new BranchManager(name, role, gender, age, branchName, staffLoginID, password); 
                         stafftemp = manager; 
                         break; 
                        case 'A': 
                         Admin admin = new Admin(name, role, gender, age, branchName, staffLoginID, password);
                         stafftemp = admin; 
                         break; 
                        }
                    Branch branch = findBranch(branchName); 
                    if (branch != null) { 
                        List<StaffGeneral>staffList = branchMap.get(branch);
                        staffList.add(stafftemp);
                    } else if(role == 'A'){
                        admin = stafftemp;
                    }
                    else { 
                        System.out.println("No branch found for staff: " + name); 
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
     * a method to find a branch object using its name
     * @param branchName name of branch
     * @return branch object
     */
    public static Branch findBranch(String branchName) { 
        for (Map.Entry<Branch, List<StaffGeneral>> entry : branchMap.entrySet()) { 
            if (entry.getKey().getName().equals(branchName)) { 
                return entry.getKey();
            }
        }
        return null; 
    }
    
    /**
     * 
     * @return
     */
    public static Set<Branch> getBranchList(){
        return branchMap.keySet();
    }
 
    // Other methods remain unchanged 
    
    /**
     * a method to find a staff object using its id
     * @param staffId ID of Staff
     * @return StaffGeneral object
     */
    public static StaffGeneral getStaff(String staffId) { 
        if (admin.getStaffID().equals(staffId)){
            return admin;
        }
        for (List<StaffGeneral> staffList : branchMap.values()) { 
            for (StaffGeneral staff : staffList) { 
                if (staff.getStaffID().equals(staffId)) { 
                    return staff; 
                } 
            } 
        }     
        return null; // Staff not found 
    }
    
    /**
     * a method to get a list of StaffGeneral objects from a Branch object
     * @param branch targeted branch
     * @return List of StaffGeneral objects
     */
    public static List<StaffGeneral> getStaffListForBranch(Branch branch) { 
        return branchMap.get(branch); 
    }
    
    /**
     * a method to select a branch and return it
     * @return Selected Branch
     * @throws Exception throws any exception
     */
    public static Branch branchSelection(){
        System.out.println();
        System.out.println("Please select your branch");
        Scanner scanner = new Scanner(System.in);
        List<Character> freeChars = new ArrayList<Character>();
        Map<Character, Branch> keyToBranchMap = new HashMap<>();
    
        for (char j = 'a'; j <= 'z'; j++) {
            freeChars.add(j);
        }
        for (Branch entry: getBranchList()) {
            char c = freeChars.get(0);
            freeChars.remove(Character.valueOf(c));
            keyToBranchMap.put(c, entry);
            System.out.println(c +" : "+ entry.getName());
        }
    
        char key;
        do {
            key = scanner.next().charAt(0);
            System.out.print("\n");
        } while (!keyToBranchMap.containsKey(key));
        
        return keyToBranchMap.get(key);
    }
    
    /**
     * a method to display all staffs under the age limit specified
     * @param ageUpperLimit age limit
     */
    public static void printStaffByAge(int ageUpperLimit){
        char index = 'a';
    	for (Branch branchsel: branchMap.keySet()) {
            for (StaffGeneral staffEntry: branchMap.get(branchsel)){
                if (staffEntry.getAge() <= ageUpperLimit){
                    System.out.print(index + ": ");
                    staffEntry.printStaffDetails();
                    index++;
                }   
            }
        }
    }

    /**
     * a method to display all staffs by gender
     * @param gender gender
     */
    public static void printStaffByGender(char gender){
        char index = 'a';
    	for (Branch branchsel: branchMap.keySet()) {
            for (StaffGeneral staffEntry: branchMap.get(branchsel)){
                if (staffEntry.getGender() == gender){
                    System.out.print(index + ": ");
                    staffEntry.printStaffDetails();
                    index++;
                }   
            }
        }
        if (admin.getGender() == gender){
            admin.printStaffDetails();
        }
    }

    /**
     * a method to display all staffs with a certain role
     * @param branch targetted role
     */
    public static void printStaffByRole(char role){
        char index = 'a';
        if (role == 'A'){
            admin.printStaffDetails();
        }
    	for (Branch branchsel: branchMap.keySet()) {
            for (StaffGeneral staffEntry: branchMap.get(branchsel)){
                if (staffEntry.getRole() == role){
                    System.out.print(index + ": ");
                    staffEntry.printStaffDetails();
                    index++;
                }   
            }
        }
    }

    /**
     * a method to display all staffs inside target branch
     * @param branch targetted branch
     */
    public static boolean printStaffByBranch(String branch){
        char index = 'a';
        boolean flag = false;
    	for (Branch branchsel: branchMap.keySet()) {
            if (branchsel.getName().equals(branch)){
                flag = true;
                for (StaffGeneral staffEntry: branchMap.get(branchsel)){
                    System.out.print(index + ": ");
                    staffEntry.printStaffDetails();
                    index++;
                }
            }
        }
        return flag;
    }

    /**
     * 
     * a method to print all existing staff
     */
    public static void printAllStaff() {
        char index = 'a';
    	for (List<StaffGeneral> staffList: branchMap.values()) {
            for (StaffGeneral staffEntry: staffList){
                System.out.print(index + ": ");
                staffEntry.printStaffDetails();
                index++;
            }
        }
        admin.printStaffDetails();
    }

    
    /**
     * a method to update the hash map after adding a Staff from the specified Branch
     * @param branch targeted branch
     * @param newStaff Staff getting added
     * @throws DuplicateInput throws an error if there are duplicate IDs
     * @throws QuotaExceeded throws an error if they exceed the quota
     */
    public static void addStaffAccount(Branch branch, StaffGeneral newStaff) throws DuplicateInput, QuotaExceeded {
    	//add Staff Account
    	try{
            if (branch.getStaffCount() >= branch.getStaffQuota()){
                throw new QuotaExceeded();
            }

            for(StaffGeneral staff : getStaffListForBranch(branch))
            {
                if (staff.getStaffID().equals(newStaff.getStaffID())) {
                    throw new DuplicateInput("Staff ID '" + newStaff.getStaffID() + "' already exists.");
                }
            }
            branchMap.get(branch).add(newStaff);
            if (newStaff.getRole() == 'S')
                branch.setStaffCount(branch.getStaffCount() + 1);
        }finally{}
    };
    
//    public void editStaffAccount() {
//    	//update hash map
//    	//update csv
//    };
    
    /**
     * a method to update the hashmap after removing a Staff from the specified Branch
     * @param branch targeted branch
     * @param Staff Staff getting removed
     * @throws Exception throws any error encountered
     */
    public static void removeStaffAccount(Branch branch, StaffGeneral Staff) throws Exception{
    	//delete key in hash map
    	try {
            if (Staff.getBranch() != branch.getName()){
                throw new Exception("This staff is not inside the branch!");
            }
            branchMap.get(branch).remove(Staff);
            branch.setStaffCount(branch.getStaffCount() - 1);
    	}catch (Exception e) {
    		throw e;
    	}
    };
    
    
//    public static void promoteStaff() {
//    	//staff to manager
//    	//update csv
//    };
    
    /**
     * a method to update the hashmap after transferring a Staff from the specified Branch
     * @param targetbranch targeted branch
     * @param Staff Staff getting transferred
     * @throws Exception throws any error encountered
     */
    public static void transferStaff(Branch targetbranch, StaffGeneral Staff) throws Exception{
    	try {
            Branch currentBranch = findBranch(Staff.getBranch());
            addStaffAccount(targetbranch, Staff);
            removeStaffAccount(targetbranch, Staff);
            Staff.setBranch(targetbranch.getName());
            removeStaffAccount(currentBranch, Staff);
    	}catch(Exception e) {
    		throw e;
    	}
    };

    /**
     * a method to open a branch
     * @param branch targeted branch
     * @throws Exception throws any error encountered
     */
    public static void openBranch(String name, String location, int quota) throws Exception{
    	//create new key in hashmap
    	//update csv
    	Branch branch = new Branch(name, location, quota, 0);
    	try {
    		branchMap.put(branch, new ArrayList<>());
    	}catch (Exception e) {
    		throw e;
    	}
    };
    
    /**
     * a method to close a branch
     * @param branch targeted branch
     * @throws Exception throws any error encountered
     */
    public static void closeBranch(Branch branch) throws Exception{
    	//remove key in hash map
    	//update csv
    	
    	try {
    		branchMap.remove(branch);
    	}catch(Exception e) {
    		throw e;
    	}
    };

    /**
     * Update staff list to csv
     */

    public static void updateStafflistToCSV(String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Map.Entry<Branch, List<StaffGeneral>> entry : branchMap.entrySet()) {
                List<StaffGeneral> staffList = entry.getValue();
                    for (StaffGeneral staff : staffList) {
                        writer.write(formatStaffDetails(staff));
                        writer.newLine();
                    }
                }
                writer.write(formatStaffDetails(admin));
                writer.newLine();
            } catch (IOException e) {
                e.printStackTrace();
        }
    }
    
    /**
     * format into csv
     * @param staff
     */
    private static String formatStaffDetails(StaffGeneral staff) {
        return staff.getName() + "," +
                staff.getStaffID() + "," +
                staff.getRole() + "," +
                staff.getGender() + "," +
                staff.getAge() + "," +
                staff.getBranch() + "," +
                staff.getStaffPW();
    }

    /**
     * Update branches to csv
     */
    public static void updateBranchesToCSV(String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            // Iterate over the branches in the branchMap
            for (Map.Entry<Branch, List<StaffGeneral>> entry : branchMap.entrySet()) {
                Branch branch = entry.getKey();
                writer.write(formatBranchDetails(branch));
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * format into csv
     * @param branch
     */
    private static String formatBranchDetails(Branch branch) {
        return branch.getName() + "," +
               branch.getLocation() + "," +
               branch.getStaffQuota() + "," +
               branch.getStaffCount();
    }
}
