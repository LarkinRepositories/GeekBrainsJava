package Operations;

public class Multiplication implements Operation {
    @Override
    public double getResultFor(double firstValue, double secondValue) {
        return firstValue * secondValue;
    }
}
