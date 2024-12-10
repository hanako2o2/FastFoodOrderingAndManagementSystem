package app;

import management.*;
import page.*;
import payment.*;
import java.util.Scanner;

/**
* @author Boey, Madelyn, Owen, Rais, Rithika
* @version 25/4/2024
*/
public class Main {
    /**
    * a main method
    * @param args placeholder argunents
    */
    public static void main(String[] args) {
        System.out.println("\n########################################################");
        System.out.println("## Please input your filepath up until before '/FOMs' ##\n## (i.e., \"/your/file/path\"/FOMS/src...)              ##\n########################################################\n\nInput file path:");
        Scanner scan = new Scanner(System.in);
        String inputPath = scan.nextLine();
        /**
         * Importing branch and staff list data
         */
        BranchManagement branchManagement = new BranchManagement();
        branchManagement.importBranchesFromCSV(inputPath + "/FOMS/src/CSVs/branch_list.csv");
        branchManagement.importStafflistFromCSV(inputPath + "/FOMS/src/CSVs/staff_list.csv");

        /**
         * Loop through every single branch and import the corresponding menu and order list.
         */
        for (Branch branch: BranchManagement.getBranchList()){
            branch.importMenuOrder(inputPath);
            branch.importOrderList(inputPath);
        }

        /**
         * Loop through every single branch and import the corresponding menu and order list.
         */
        PaymentMethods paymentMethods = new PaymentMethods();
        paymentMethods.importPaymentMethodFromCSV(inputPath + "/FOMS/src/CSVs/payment_methods.csv");

        FirstPage firstPage = new FirstPage();
        firstPage.printStatus(inputPath);
    }
}
