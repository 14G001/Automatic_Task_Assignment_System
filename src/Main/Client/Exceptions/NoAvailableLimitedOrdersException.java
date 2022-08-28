package Main.Client.Exceptions;

public class NoAvailableLimitedOrdersException extends Exception{
    public NoAvailableLimitedOrdersException(){
        super("No quedan pedidos limitados disponibles");
    }
}
