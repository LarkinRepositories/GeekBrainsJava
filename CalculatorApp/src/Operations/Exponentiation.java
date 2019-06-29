package Operations;

public class Exponentiation implements Operation {
    @Override
    public double getResultFor(double firstValue, double secondValue) {
        return Math.pow(firstValue, secondValue);
    }
}
