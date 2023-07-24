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

        @DisplayName("음수 또는 0을 포함한 숫자를 입력하면 예외를 출력한다")
        @ParameterizedTest(name = "{0}")
        @CsvSource({"-456", "708", "-1", "0"})
        void getListTest_throwException(int number) {
            assertThatThrownBy(() -> Digit.getList(number))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("0이 포함되지 않은 양수를 입력하여야 합니다");
        }

        @DisplayName("양수를 통해 한 자리당 하나씩 Digit을 만든다")
        @ParameterizedTest(name = "{0}을 통해 {1}자리 수를 만든다")
        @CsvSource({"456,3", "12,2", "78945,5"})
        void getListTest(int number, int expectedSize) {
            List<Digit> result = Digit.getList(number);

            assertThat(result).hasSize(expectedSize);
        }
    }

    @DisplayName("입력한 숫자간의 논리적 동치성을 판단할 수 있다")
    @ParameterizedTest(name = "{0}과 {1}을 비교하면 {2}이다")
    @CsvSource({"1,1,true", "9,9,true", "7,4,false", "6,1,false"})
    void equalsTest(int digit1, int digit2, boolean expected) {
        Digit target1 = newDigit(digit1);
        Digit target2 = newDigit(digit2);

        boolean actual = target1.equals(target2);

        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("논리적 동치성에 따라 같은 hashCode를 제공할 수 있다")
    @ParameterizedTest
    @CsvSource({"1", "5", "7"})
    void hashCodeTest(int digit) {
        Digit target1 = newDigit(digit);
        Digit target2 = newDigit(digit);

        assertThat(target1.equals(target2)).isTrue();
        assertThat(target1).hasSameHashCodeAs(target2);
    }

    Digit newDigit(int digit) {
        return Digit.getList(digit).get(0);
    }
}