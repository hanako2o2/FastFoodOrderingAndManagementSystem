package action; 
 
import order.*; 
import payment.Payment; 
import payment.PaymentMethods; 
import Exception.EmptyCartException; 
 
import java.util.*; 
 
public class CheckoutAction implements Action { 
    private Order order; 
    Scanner scan = new Scanner(System.in); 
 
    public CheckoutAction(Order order) { 
        this.order = order; 
    } 
 
    /** 
    * a method to check out customer's cart 
    * @return String 
    */ 
    public String execute() { 
        try { 
            Map<Character, Payment> keyToPaymentMap; 
 
            if (order.getTotalPrice() == 0) { 
                throw new EmptyCartException(); 
            } 
 
            System.out.print("\n"); 
            System.out.println("Select a payment method: "); 
            keyToPaymentMap = PaymentMethods.printAvailablePaymentMethods(); 
 
            char key; 
            System.out.println("Select which payment method to (a-z): "); 
            do { 
                key = scan.next().charAt(0); 
                String buffer = scan.nextLine(); 
            } while (!keyToPaymentMap.containsKey(key)); 
 
            Payment payment = keyToPaymentMap.get(key); 
            payment.executePayment(); 
 
            System.out.print("\n"); 
            System.out.println("Receipt: "); 
            order.displayOrderItems(); 
            System.out.println("Paid by " + payment.getName()); 
 
            return "Triggered"; 
        } catch (EmptyCartException e) { 
            System.out.println(e.getMessage()); 
            return "Checkout failed due to empty cart."; 
        } 
    } 
 
    /** 
    * @return Checkout 
    */ 
    public String description() { 
        return ("Checkout"); 
    } 
}