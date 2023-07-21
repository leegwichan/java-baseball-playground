package numberbaseball.domain;

import java.util.List;
import java.util.stream.IntStream;

public final class Number {
    private static final int LENGTH = 3;

    private final List<Digit> digits;

    private Number(List<Digit> digits) {
        if (digits.size() != LENGTH) {
            throw new IllegalArgumentException("세자리 수만 생성할 수 있습니다 size : " + digits.size());
        }
        this.digits = List.copyOf(digits);
    }

    public static Number from(List<Digit> digits) {
        return new Number(digits);
    }

    public int countSameDigitSamePosition(Number number) {
        return (int) IntStream.range(0, digits.size())
                .filter(index -> this.digits.get(index).equals(number.digits.get(index)))
                .count();
    }
}
