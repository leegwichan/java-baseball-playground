package numberbaseball.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class DigitTest {

    @Nested
    @DisplayName("양수를 입력받아 Digit List를 만들 수 있다")
    class CreationTest {

        @DisplayName("음수를 입력하면 예외를 던진다")
        @Test
        void getListTest_whenInputNegativeNumber_throwException() {
            int number = -456;

            assertThatThrownBy(() -> Digit.getList(number))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("음수로 숫자를 만들 수 없습니다")
                    .hasMessageContaining(String.valueOf(number));
        }

        @DisplayName("양수를 통해 한 자리당 하나씩 Digit을 만든다")
        @ParameterizedTest(name = "{0}을 통해 {1}자리 수를 만든다")
        @CsvSource({"456,3", "12,2", "78945,5"})
        void getListTest(int number, int expectedSize) {
            List<Digit> result = Digit.getList(number);

            assertThat(result).hasSize(expectedSize);
        }
    }
}