package numberbaseball.domain;

import static java.util.stream.Collectors.toList;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;
import org.mockito.Mockito;

public class MockDigitHelper {

    private final Map<Integer, Digit> numberToDigit = new HashMap<>();

    List<Digit> getMockDigitsBySize(int size) {
        return IntStream.range(0,size)
                .mapToObj(index -> getMockDigit())
                .collect(toList());
    }

    List<Digit> getMockDigitsByInitialNumber(int number) {
        return Arrays.stream(String.valueOf(number).split(""))
                .mapToInt(Integer::parseInt)
                .mapToObj(digitInt ->
                        numberToDigit.computeIfAbsent(digitInt, (integer) -> getMockDigit()))
                .collect(toList());
    }

    private Digit getMockDigit() {
        return Mockito.mock(Digit.class);
    }
}
