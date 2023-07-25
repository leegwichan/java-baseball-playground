package numberbaseball.domain;

import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;
import numberbaseball.helper.NumberGenerator;

public final class BaseballNumber {
    private static final int LENGTH = 3;

    private final List<BaseballDigit> digits;

    private BaseballNumber(List<BaseballDigit> digits) {
        Objects.requireNonNull(digits, "Number 생성할 때 입력 값은 null이 아니어야 합니다");
        if (digits.size() != LENGTH) {
            throw new IllegalArgumentException("세자리 수만 생성할 수 있습니다 size : " + digits.size());
        }
        this.digits = List.copyOf(digits);
    }

    public static BaseballNumber from(List<BaseballDigit> digits) {
        return new BaseballNumber(digits);
    }

    public static BaseballNumber from(NumberGenerator generator) {
        Objects.requireNonNull(generator, "Number 생성할 때 입력 값은 null이 아니어야 합니다.");
        return new BaseballNumber(generator.generate(LENGTH));
    }

    public int countSameDigitSamePosition(BaseballNumber number) {
        return (int) IntStream.range(0, digits.size())
                .filter(index -> this.digits.get(index).equals(number.digits.get(index)))
                .count();
    }

    public int countSameDigitDifferentPosition(BaseballNumber number) {
        return (int) IntStream.range(0, digits.size())
                .filter(index -> isContainDigitExceptMatchedIndex(number.digits.get(index), index))
                .count();
    }

    public int getLength() {
        return LENGTH;
    }

    private boolean isContainDigitExceptMatchedIndex(BaseballDigit digit, int index) {
        return IntStream.range(0, this.digits.size())
                .filter(digitIndex -> digitIndex != index)
                .anyMatch(digitIndex -> digit.equals(this.digits.get(digitIndex)));
    }
}
