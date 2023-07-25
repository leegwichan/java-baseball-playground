package numberbaseball.domain;

import static java.util.stream.Collectors.toList;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;
import org.mockito.Mockito;

public class MockDigitHelper {

    private final Map<Integer, BaseballDigit> numberToDigit = new HashMap<>();

    List<BaseballDigit> getMockDigitsBySize(int size) {
        return IntStream.range(0, size)
                .mapToObj(index -> getMockDigit())
                .collect(toList());
    }

    List<BaseballDigit> getMockDigitsByInitialNumber(int number) {
        return Arrays.stream(String.valueOf(number).split(""))
                .mapToInt(Integer::parseInt)
                .mapToObj(digitInt ->
                        numberToDigit.computeIfAbsent(digitInt, (integer) -> getMockDigit()))
                .collect(toList());
    }

    private BaseballDigit getMockDigit() {
        return Mockito.mock(BaseballDigit.class);
    }
}
