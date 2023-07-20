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

    @DisplayName("입력한 숫자간의 ")
    @ParameterizedTest(name = "{0}과 {1}을 비교하면 {2}이다")
    @CsvSource({"1,1,true", "9,9,true", "7,4,false", "6,1,false"})
    void equalsTest(int digit1, int digit2, boolean expected) {
        Digit target1 = newDigit(digit1);
        Digit target2 = newDigit(digit2);

        boolean actual = target1.equals(target2);

        assertThat(actual).isEqualTo(expected);
    }

    Digit newDigit(int digit) {
        return Digit.getList(digit).get(0);
    }
}