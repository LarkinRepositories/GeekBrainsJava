package Exceptions;

public class DivisionToZeroException extends Exception {
    public DivisionToZeroException() {
        super("Деление на ноль");
    }
}
