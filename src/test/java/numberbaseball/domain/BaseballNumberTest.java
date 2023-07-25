package numberbaseball.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import numberbaseball.helper.BaseballNumberGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class BaseballNumberTest extends MockDigitHelper {

    private static final int NUMBER_SIZE = 3;

    @DisplayName("인자를 받아 해당 객체를 생성할 수 있다")
    @Nested
    class CreationTest {
        private static final String NULL_POINTER_EXCEPTION_MESSAGE
                = "Number 생성할 때 입력 값은 null이 아니어야 합니다";
        private static final String DIGIT_SIZE_EXCEPTION_MESSAGE = "3자리 수만 생성할 수 있습니다";
        private static final String DIGITS_OVERLAPPED_EXCEPTION = "각각의 숫자들은 중복되지 않아야 합니다";

        @DisplayName("List<Digit> 자리에 null이 들어가서는 안된다")
        @Test
        void creationTest_whenWithNullDigitList() {
            assertThatThrownBy(() -> BaseballNumber.from((List<BaseballDigit>) null))
                    .isInstanceOf(NullPointerException.class)
                    .hasMessageContaining(NULL_POINTER_EXCEPTION_MESSAGE);
        }

        @DisplayName("Generator 자리에 null이 들어가서는 안된다")
        @Test
        void creationTest_whenWithNullNumberGenerator() {
            assertThatThrownBy(() -> BaseballNumber.from((BaseballNumberGenerator) null))
                    .isInstanceOf(NullPointerException.class)
                    .hasMessageContaining(NULL_POINTER_EXCEPTION_MESSAGE);
        }

        @DisplayName("숫자의 실이가 3이 아닌 경우, 예외를 발생시킨다")
        @ParameterizedTest(name = "size : {0}")
        @CsvSource({"2", "4", "5"})
        void creationTest_whenSizeIsNot3_throwException(int size) {
            List<BaseballDigit> digits = getMockDigitsBySize(size);

            assertThatThrownBy(() -> BaseballNumber.from(digits))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining(DIGIT_SIZE_EXCEPTION_MESSAGE)
                    .hasMessageContaining(String.valueOf(size));
        }

        @DisplayName("들어온 숫자들이 중복되면 예외를 던진다")
        @ParameterizedTest(name = "{0}")
        @CsvSource({"334", "877", "616"})
        void creationTest_whenInputOverlappedDigits_throwException(int value) {
            List<BaseballDigit> digits = getMockDigitsByInitialNumber(value);

            assertThatThrownBy(() -> BaseballNumber.from(digits))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining(DIGITS_OVERLAPPED_EXCEPTION);
        }

        @DisplayName("숫자의 길이가 3이며 중복되지 않는 경우, 정상적으로 생성된다")
        @Test
        void creationTest_whenSizeIs3() {
            List<BaseballDigit> digits = getMockDigitsBySize(NUMBER_SIZE);

            assertThatCode(() -> BaseballNumber.from(digits)).doesNotThrowAnyException();
        }

        @DisplayName("NumberGenerator에서 3자리 수를 생성하지 않으면 예외를 발생한다")
        @ParameterizedTest
        @CsvSource({"2", "4", "5"})
        void creationTest_numberGeneratorNotCreate3Digits_throwException(int digitSize) {
            BaseballNumberGenerator mockGenerator = (size) -> getMockDigitsBySize(digitSize);

            assertThatThrownBy(() -> BaseballNumber.from(mockGenerator))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining(DIGIT_SIZE_EXCEPTION_MESSAGE)
                    .hasMessageContaining(String.valueOf(digitSize));
        }
    }

    @DisplayName("같은 숫자 같은 자리 일치 개수를 셀 수 있다")
    @ParameterizedTest(name = "{0}과 {1}을 비교했을 떄 {2}개가 일치한다")
    @CsvSource({"123,231,0", "123,193,2", "789,789,3"})
    void countSameDigitSamePositionTest(int number1, int number2, int expected) {
        List<BaseballDigit> digits1 = getMockDigitsByInitialNumber(number1);
        List<BaseballDigit> digits2 = getMockDigitsByInitialNumber(number2);

        int actual = BaseballNumber.from(digits1).countSameDigitSamePosition(BaseballNumber.from(digits2));

        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("같은 숫자 다른 자리 일치 개수를 셀 수 있다.")
    @ParameterizedTest(name = "{0}과 {1}을 비교했을 떄 {2}개가 일치한다")
    @CsvSource({"123,231,3", "123,139,1", "789,789,0"})
    void countSameDigitDifferentPositionTest(int number1, int number2, int expected) {
        List<BaseballDigit> digits1 = getMockDigitsByInitialNumber(number1);
        List<BaseballDigit> digits2 = getMockDigitsByInitialNumber(number2);

        int actual = BaseballNumber.from(digits1).countSameDigitDifferentPosition(BaseballNumber.from(digits2));

        assertThat(actual).isEqualTo(expected);
    }

}