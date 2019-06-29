package Operations;

import Exceptions.DivisionToZeroException;

public interface Operation {
   public double getResultFor(double firstValue, double secondValue) throws DivisionToZeroException;
}
