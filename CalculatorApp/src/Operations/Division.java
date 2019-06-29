package Operations;

import Exceptions.DivisionToZeroException;

public class Division implements Operation {
    @Override
    public double getResultFor(double firstValue, double secondValue) throws DivisionToZeroException {
        if (secondValue != 0) return firstValue / secondValue;
        else throw new DivisionToZeroException();
    }
}
