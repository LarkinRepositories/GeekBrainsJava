package Operations;

public class Addition implements Operation {
    @Override
    public double getResultFor(double firstValue, double secondValue) {
        return firstValue+secondValue;
    }
}
