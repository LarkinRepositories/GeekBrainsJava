package Operations;

public class Substraction implements Operation {
    @Override
    public double getResultFor(double firstValue, double secondValue) {
        return firstValue - secondValue;
    }
}
