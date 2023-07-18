package calculator.entity;

import java.util.Arrays;
import java.util.function.IntBinaryOperator;

public enum Operation {

    PLUS('+', (a, b) -> a + b),
    MINUS('-', (a, b) -> a - b),
    MULTIPLY('*', (a, b) -> a * b),
    DIVIDE('/', (a, b) -> a / b);

    private final char sign;
    private final IntBinaryOperator operator;

    Operation(char sign, IntBinaryOperator operator) {
        this.sign = sign;
        this.operator = operator;
    }

    public static Operation of(char sign) {
        return Arrays.stream(Operation.values())
                .filter(operator -> operator.sign == sign)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException());
    }

    public int calculate(int left, int right) {
        return operator.applyAsInt(left, right);
    }
}
