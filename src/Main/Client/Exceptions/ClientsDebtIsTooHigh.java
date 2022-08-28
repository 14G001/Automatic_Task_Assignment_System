package Main.Client.Exceptions;

public class ClientsDebtIsTooHigh extends Exception {
    public ClientsDebtIsTooHigh(){
        super("La deuda del cliente es demasiado alta.");
    }
}
