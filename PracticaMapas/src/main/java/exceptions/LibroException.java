package exceptions;

/**
 * Excepción asociada al comportamiento de gestión de libros
 */
public class LibroException extends Exception {
    public LibroException(String message){
        super(message);
    }
}
