package Main.Client.Exceptions;

public class UnregisteredClient extends Exception {
    public UnregisteredClient(){
        super("El cliente no se encuentra registrado.");
    }
}