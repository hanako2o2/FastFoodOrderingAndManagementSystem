package action;


import management.*;
import java.util.*;

public class DisplayStaffAction implements Action{
    /**
     * method to display staff according to filter
     */
    public String execute() {
        Scanner scan = new Scanner(System.in);
        int selection;
        do{
            System.out.println("Select a filter: ");
            System.out.println("1. None ");
            System.out.println("2. Branch");
            System.out.println("3. Role");
            System.out.println("4. Age");
            System.out.println("5. Gender ");
            selection = scan.nextInt();
        }while (selection < 1 && selection > 5);

        scan.nextLine();
        char role;
        int limit;
        char gender;
        switch(selection){
            case 1:
                BranchManagement.printAllStaff();
                break;
            case 2:
                System.out.println("Enter a branch name: ");
                String branch = scan.nextLine();
                if(!BranchManagement.printStaffByBranch(branch))
                    System.out.println("Branch of such name does not exist!");
                break;
            case 3:
                do{
                    System.out.println("Enter a role (A for admin, S for staff, M for manager): ");
                    role = scan.nextLine().charAt(0);
                    if(role == 'A' || role == 'S' || role == 'M')
                        BranchManagement.printStaffByRole(role);
                }while(role != 'A' && role != 'S' && role != 'M');
                
                break;
            case 4:
                System.out.println("Enter an upper age limit: ");
                limit = scan.nextInt();
                BranchManagement.printStaffByAge(limit);
                break;
            case 5:
                do{
                    System.out.println("Enter which gender to show (M/F): ");
                    gender = scan.nextLine().charAt(0);
                    if (gender == 'M' || gender == 'F')
                        BranchManagement.printStaffByGender(gender);
                }while(gender != 'M' && gender != 'F');
                break;
        }
        return "";
    }

    /**
    * @return Displays staff by various filters
    */
    public String description(){
        return ("Display staff lists by filter");
    }
}
