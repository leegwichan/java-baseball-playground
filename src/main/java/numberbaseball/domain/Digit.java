package numberbaseball.domain;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public final class Digit {

    private final int digit;

    private Digit(int digit) {
        if (digit < 0 || digit > 9) {
            throw new IllegalArgumentException();
        }
        this.digit = digit;
    }

    public static List<Digit> getList(int number) {
        if (number < 0) {
            throw new IllegalArgumentException("음수로 숫자를 만들 수 없습니다 : " + number);
        }
        return Arrays.stream(String.valueOf(number).split(""))
                .mapToInt(Integer::parseInt)
                .mapToObj(Digit::new)
                .collect(Collectors.toList());
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Digit)) {
            return false;
        }
        return this.digit == ((Digit) o).digit;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(digit);
    }
}
