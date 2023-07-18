package calculator.view;

import calculator.dto.ExpressionDto;
import calculator.entity.Operation;
import calculator.reader.Reader;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class InputView {

    private final Reader reader;

    public InputView(Reader reader) {
        this.reader = reader;
    }

    public ExpressionDto inputExpression() {
        String input = reader.read();
        String[] elements = input.split(" ");

        try {
            return new ExpressionDto(toNumbers(elements), toOperations(elements));
        } catch (IllegalArgumentException exception) {
            System.err.println("[ERROR] 입력 형식이 잘못되었습니다.");
            throw exception;
        }
    }

    private List<Integer> toNumbers(String[] elements) {
        return IntStream.range(0, elements.length)
                .filter(i -> i % 2 == 0)
                .mapToObj(i -> Integer.parseInt(elements[i]))
                .collect(Collectors.toList());
    }

    private List<Operation> toOperations(String[] elements) {
        return IntStream.range(0, elements.length)
                .filter(i -> i % 2 == 1)
                .mapToObj(i -> Operation.of(elements[i].charAt(0)))
                .collect(Collectors.toList());
    }
}
