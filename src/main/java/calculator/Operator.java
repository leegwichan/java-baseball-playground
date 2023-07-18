package calculator;

import java.util.Arrays;
import java.util.function.IntBinaryOperator;

public enum Operator {

    PLUS('+', (a, b) -> a + b),
    MINUS('-', (a, b) -> a - b),
    MULTIPLY('*', (a, b) -> a * b),
    DIVIDE('/', (a, b) -> a / b);

    private final char sign;
    private final IntBinaryOperator operation;

    Operator(char sign, IntBinaryOperator operation) {
        this.sign = sign;
        this.operation = operation;
    }

    public static Operator of(char sign) {
        return Arrays.stream(Operator.values())
                .filter(operator -> operator.sign == sign)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException());
    }
}
