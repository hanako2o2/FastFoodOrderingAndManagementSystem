package payment;

/**
* @author Boey, Madelyn, Owen, Rais, Rithika
* @version 25/4/2024
*/

public class Payment {
    private String name;

    /**
     * a constructor for Payment
     */
    public Payment(String name){
        this.setName(name);
    }

    /**
    * a method to execute payment
    */
    public void executePayment(){
        System.out.println("Payment by " + getName() + ". . .");
        System.out.println("Payment successful!");
    }

    /**
    * @return name of payment
    */
    public String getName(){
        return name;
    }

    /**
    * a method to change the name of a payment
    * @param name new name of payment
    */
    public void setName(String name){
        this.name = name;
    }
}
