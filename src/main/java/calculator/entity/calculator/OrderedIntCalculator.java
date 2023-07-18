package calculator.entity.calculator;

import calculator.entity.Operation;
import java.util.List;

public class OrderedIntCalculator implements IntCalculator {

    @Override
    public int calculate(List<Integer> numbers, List<Operation> operations) {
        validateCalculateParameterFormat(numbers, operations);

        int answer = numbers.get(0);
        for (int i = 0; i < operations.size(); i++) {
            Operation operation = operations.get(i);
            answer = operation.calculate(answer, numbers.get(i+1));
        }
        return answer;
    }

    private void validateCalculateParameterFormat(List<Integer> numbers, List<Operation> operations) {
        if (numbers.size() != operations.size() + 1) {
            throw new IllegalArgumentException();
        }
    }
}
