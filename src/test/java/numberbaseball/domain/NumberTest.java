package numberbaseball.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class NumberTest extends MockDigitHelper {

    private static final int NUMBER_SIZE = 3;

    @DisplayName("인자를 받아 해당 객체를 생성할 수 있다")
    @Nested
    class CreationTest {

        @DisplayName("List<Digit>의 크기가 3이 아닌 경우, 예외를 발생시킨다")
        @ParameterizedTest(name = "size : {0}")
        @CsvSource({"2", "4", "5"})
        void creationTest_whenSizeIsNot3_throwException(int size) {
            List<Digit> digits = getMockDigitsBySize(size);

            assertThatThrownBy(() -> Number.from(digits))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("세자리 수만 생성할 수 있습니다")
                    .hasMessageContaining(String.valueOf(size));
        }

        @DisplayName("List<Digit>의 크기가 3인 경우, 정상적으로 생성된다")
        @Test
        void creationTest_whenSizeIs3() {
            List<Digit> digits = getMockDigitsBySize(NUMBER_SIZE);

            assertThatCode(() -> Number.from(digits)).doesNotThrowAnyException();
        }
    }

    @DisplayName("같은 숫자 같은 자리 일치 개수를 셀 수 있다")
    @ParameterizedTest(name = "{0}과 {1}을 비교했을 떄 {2}개가 일치한다")
    @CsvSource({"123,231,0", "123,193,2", "789,789,3"})
    void countSameDigitSamePositionTest(int number1, int number2, int expected) {
        List<Digit> digits1 = getMockDigitsByInitialNumber(number1);
        List<Digit> digits2 = getMockDigitsByInitialNumber(number2);

        int actual = Number.from(digits1).countSameDigitSamePosition(Number.from(digits2));

        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("같은 숫자 다른 자리 일치 개수를 셀 수 있다.")
    @ParameterizedTest(name = "{0}과 {1}을 비교했을 떄 {2}개가 일치한다")
    @CsvSource({"123,231,3", "123,139,1", "789,789,0"})
    void countSameDigitDifferentPositionTest(int number1, int number2, int expected) {
        List<Digit> digits1 = getMockDigitsByInitialNumber(number1);
        List<Digit> digits2 = getMockDigitsByInitialNumber(number2);

        int actual = Number.from(digits1).countSameDigitDifferentPosition(Number.from(digits2));

        assertThat(actual).isEqualTo(expected);
    }

}