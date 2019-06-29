package Logic;

import Operations.*;

import java.util.HashMap;
import java.util.Map;

public class CalcLogic {
    private static final Map<Character, Operation> OPERATIONS = new HashMap<>();
    private final char operator;
    private double result;
    private final double firstNumber, secondNumber;

    public CalcLogic(char operator, double firstNumber, double secondNumber) throws Exception {
        this.operator = operator;
        this.firstNumber = firstNumber;
        this.secondNumber = secondNumber;
        calculateResult();
    }

    static {
        OPERATIONS.put('+', new Addition());
        OPERATIONS.put('-', new Substraction());
        OPERATIONS.put('/', new Division());
        OPERATIONS.put('*', new Multiplication());
        OPERATIONS.put('^', new Exponentiation());

    }
    public String getResult() {
        return String.valueOf(result);

    }
    private void calculateResult() throws Exception {
        Operation operation = OPERATIONS.get(operator);
        if (operation != null) result = operation.getResultFor(firstNumber,secondNumber);
        else throw new UnsupportedOperationException();
    }

}
