package Utilities;

public class DEVELOPMENT_ERROR extends RuntimeException {
    public DEVELOPMENT_ERROR(String message){
        super("ERROR DE DESARROLLO: " + message);
    }
}
