package numberbaseball.domain;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mockito;

class NumberTest {

    @DisplayName("인자를 받아 해당 객체를 생성할 수 있다")
    @Nested
    class CreationTest {

        @DisplayName("List<Digit>의 크기가 3이 아닌 경우, 예외를 발생시킨다")
        @ParameterizedTest(name = "size : {0}")
        @CsvSource({"2", "4", "5"})
        void creationTest_whenSizeIsNot3_throwException(int size) {
            List<Digit> digits = getMockDigits(size);

            assertThatThrownBy(() -> Number.from(digits))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("세자리 수만 생성할 수 있습니다")
                    .hasMessageContaining(String.valueOf(size));
        }

        @DisplayName("List<Digit>의 크기가 3인 경우, 정상적으로 생성된다")
        @Test
        void creationTest_whenSizeIs3() {
            List<Digit> digits = getMockDigits(3);

            assertThatCode(() -> Number.from(digits)).doesNotThrowAnyException();
        }
    }

    List<Digit> getMockDigits(int size) {
        return IntStream.range(0,size)
                .mapToObj(index -> getMockDigit())
                .collect(Collectors.toList());
    }

    Digit getMockDigit() {
        return Mockito.mock(Digit.class);
    }
}