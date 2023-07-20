package numberbaseball.domain;

import java.util.List;

public final class Number {
    private static final int LENGTH = 3;

    private final List<Digit> number;

    private Number(List<Digit> digits) {
        if (digits.size() != 3) {
            throw new IllegalArgumentException("세자리 수만 생성할 수 있습니다 size : " + digits.size());
        }
        this.number = List.copyOf(digits);
    }

    public static Number from(List<Digit> digits) {
        return new Number(digits);
    }
}
