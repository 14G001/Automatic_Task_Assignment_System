package Main.Client.Exceptions;

public class OrderWithoutTasksException extends Exception{
    public OrderWithoutTasksException(){
        super("ERROR: La Orden no contiene tareas.");
    }
}
