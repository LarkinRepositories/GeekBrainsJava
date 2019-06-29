package Exceptions;

public class UnsupportedOperationException extends Exception {
    public UnsupportedOperationException() {
        super("Операция не поддерживается");
    }
}
