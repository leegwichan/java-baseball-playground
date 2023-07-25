package numberbaseball.domain;

import static java.util.stream.Collectors.toList;

import java.util.Arrays;
import java.util.List;

public final class BaseballDigit {

    private final int value;

    private BaseballDigit(int value) {
        if (value < 1 || value > 9) {
            throw new IllegalArgumentException("0이 포함되지 않은 양수를 입력하여야 합니다");
        }
        this.value = value;
    }

    public static BaseballDigit of(int value) {
        return new BaseballDigit(value);
    }

    public static List<BaseballDigit> getList(int number) {
        if (number < 0) {
            throw new IllegalArgumentException("0이 포함되지 않은 양수를 입력하여야 합니다");
        }
        return Arrays.stream(String.valueOf(number).split(""))
                .mapToInt(Integer::parseInt)
                .mapToObj(BaseballDigit::new)
                .collect(toList());
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof BaseballDigit)) {
            return false;
        }
        return this.value == ((BaseballDigit) o).value;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(value);
    }
}
