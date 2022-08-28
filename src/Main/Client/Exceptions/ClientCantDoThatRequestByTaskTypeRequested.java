package Main.Client.Exceptions;

public class ClientCantDoThatRequestByTaskTypeRequested extends Exception {
    public ClientCantDoThatRequestByTaskTypeRequested(){
        super("El cliente no puede realizar ese tipo de pedido.");
    }
}