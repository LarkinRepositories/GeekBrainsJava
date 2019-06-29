package Logic;

public class ButtonLogic {
    private boolean isOperationSet;
    private double firstValue, secondValue, value;
    private char operation;

    public void setValue(double value) {
        this.value = this.value * 10 + value;
    }
    public void setValue(char operation) {
        this.operation = operation;
        value = 0;
        isOperationSet = true;
    }

    public void setOperation(char operation) {
        this.operation = operation;
    }
    public String getStringFromOperation() {
        if (!isOperationSet) {
            firstValue = value;
            return String.valueOf(firstValue);
        }
        else {
            secondValue = value;
            return String.valueOf(firstValue) + operation +String.valueOf(secondValue);
        }
    }
    public String getResult() throws Exception {
        return new CalcLogic(operation, firstValue, secondValue).getResult();
    }
}
