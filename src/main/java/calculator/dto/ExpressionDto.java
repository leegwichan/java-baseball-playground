package calculator.dto;

import calculator.entity.Operation;
import java.util.List;

public class ExpressionDto {

    private final List<Integer> numbers;
    private final List<Operation> operations;

    public ExpressionDto(List<Integer> numbers, List<Operation> operations) {
        this.numbers = numbers;
        this.operations = operations;
    }

    public List<Integer> getNumbers() {
        return numbers;
    }

    public List<Operation> getOperations() {
        return operations;
    }
}
