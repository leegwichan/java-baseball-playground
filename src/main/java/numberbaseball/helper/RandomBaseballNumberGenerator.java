package numberbaseball.helper;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import numberbaseball.domain.BaseballDigit;

public final class RandomBaseballNumberGenerator implements BaseballNumberGenerator {

    private final Random random = new Random();

    private RandomBaseballNumberGenerator() {}

    public static RandomBaseballNumberGenerator of() {
        return new RandomBaseballNumberGenerator();
    }

    @Override
    public List<BaseballDigit> generate(int size) {
        validateSize(size);

        List<BaseballDigit> digits = new ArrayList<>();
        while (digits.size() < size) {
            BaseballDigit randomDigit = BaseballDigit.of(randomFrom1To9());
            addDigitIfNotOverlapped(digits, randomDigit);
        }
        return digits;
    }

    private void validateSize(int size) {
        if (size < 1 || size > 9) {
            throw new IllegalArgumentException("곂치지 않는 숫자는 1~9자리 까지만 만들 수 있습니다");
        }
    }

    private int randomFrom1To9() {
        return random.nextInt(9) + 1;
    }

    private void addDigitIfNotOverlapped(List<BaseballDigit> digits, BaseballDigit digit) {
        if (!digits.contains(digit)) {
            digits.add(digit);
        }
    }
}
