package numberbaseball.helper;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import numberbaseball.domain.Digit;

public final class RandomNotOverlappedNumberGenerator implements NumberGenerator {

    private final Random random = new Random();

    private RandomNotOverlappedNumberGenerator() {}

    public static RandomNotOverlappedNumberGenerator of() {
        return new RandomNotOverlappedNumberGenerator();
    }

    @Override
    public List<Digit> generate(int size) {
        validateSize(size);

        List<Digit> digits = new ArrayList<>();
        while (digits.size() < size) {
            Digit randomDigit = Digit.of(randomOneSizeInt());
            addDigitIfNotOverlapped(digits, randomDigit);
        }
        return digits;
    }

    private void validateSize(int size) {
        if (size < 1 || size > 9) {
            throw new IllegalArgumentException("곂치지 않는 숫자는 1~9자리 까지만 만들 수 있습니다");
        }
    }

    private int randomOneSizeInt() {
        return random.nextInt(10);
    }

    private void addDigitIfNotOverlapped(List<Digit> digits, Digit digit){
        if (digits.size() == 0 && digit.equals(Digit.ZERO) || digits.contains(digit)) {
            return;
        }

        digits.add(digit);
    }
}
