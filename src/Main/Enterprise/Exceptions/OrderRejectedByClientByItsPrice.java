package Main.Enterprise.Exceptions;

public class OrderRejectedByClientByItsPrice extends Exception {
    public OrderRejectedByClientByItsPrice(){
        super("Order was rejected by client.");
    }
}
