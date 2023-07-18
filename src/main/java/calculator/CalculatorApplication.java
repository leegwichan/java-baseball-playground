package calculator;

import calculator.dto.ExpressionDto;
import calculator.entity.Operation;
import calculator.entity.calculator.IntCalculator;
import calculator.entity.calculator.OrderedIntCalculator;
import calculator.reader.ConsoleReader;
import calculator.view.InputView;
import java.util.List;

public final class CalculatorApplication {
    private static final InputView inputView = new InputView(new ConsoleReader());
    private static final IntCalculator intCalculator = new OrderedIntCalculator();

    public static void main(String[] args) {
        ExpressionDto expression = inputView.inputExpression();

        List<Operation> operations = expression.getOperations();
        List<Integer> numbers = expression.getNumbers();

        int answer = intCalculator.calculate(numbers, operations);
        System.out.println(answer);
    }
}
