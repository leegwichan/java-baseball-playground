package calculator.entity.calculator;

import calculator.entity.Operation;
import java.util.List;

public interface IntCalculator {
    int calculate(List<Integer> numbers, List<Operation> operations);
}
