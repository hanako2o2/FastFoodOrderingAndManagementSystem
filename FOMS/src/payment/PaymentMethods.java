package payment;

import java.util.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
* @author Boey, Madelyn, Owen, Rais, Rithika
* @version 25/4/2024
*/

public class PaymentMethods {
    private static List<Payment> paymentMethod = new ArrayList<>();

    /**
     * import payment_methods.csv
     * @param filePath local file path
     */
    public void importPaymentMethodFromCSV(String filePath){
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) { 
            String line; 
            while ((line = br.readLine()) != null) { 
                String[] parts = line.split(","); 
                if (parts.length == 1) { 
                    String name = parts[0].trim(); 
                    Payment payment = new Payment(name);
                    paymentMethod.add(payment);
                } else { 
                    System.out.println("Invalid line: " + line); 
                } 
            } 
        } catch (IOException e) { 
            e.printStackTrace(); 
        } 
    }

    /**
     * users to select which payment method to use
     * @return keyToPaymentMap
     */
    public static Map<Character, Payment> printAvailablePaymentMethods(){ 
        Scanner scanner = new Scanner(System.in); 
        List<Character> freeChars = new ArrayList<Character>(); 
        Map<Character, Payment> keyToPaymentMap = new HashMap<>(); 
     
        for (char j = 'a'; j <= 'z'; j++) { 
            freeChars.add(j); 
        } 
        for (Payment entry: paymentMethod) { 
            char c = freeChars.get(0); 
            freeChars.remove(Character.valueOf(c)); 
            keyToPaymentMap.put(c, entry); 
            System.out.println(c + ": " + entry.getName()); 
        } 
        return keyToPaymentMap; 
    }

    /**
     * removes a payment method
     * @param payment
     */
    public static void removePaymentMethod(Payment payment){
        paymentMethod.remove(payment);
    }

    /**
     * creates new payment
     * @param name
     */
    public static void createNewPayment(String name){
        paymentMethod.add(new Payment(name));
    }

    /**
     * exports payment methods to csv
     * @param filePath
     */
    public static void exportPaymentMethodToCSV(String filePath) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            for (Payment payment : paymentMethod) {
                bw.write(payment.getName());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}