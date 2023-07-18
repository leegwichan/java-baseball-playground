package calculator.entity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class OperationTest {

    @Nested
    @DisplayName("of() 테스트")
    class StaticFactoryTest {

        @DisplayName("기호를 통해 Operator 객체를 가져올 수 있다")
        @ParameterizedTest
        @CsvSource(value = {"+,PLUS", "-,MINUS", "*,MULTIPLY", "/,DIVIDE"})
        void ofTest(char sign, Operation expected) {
            Operation actual = Operation.of(sign);

            assertThat(actual).isEqualTo(expected);
        }

        @DisplayName("허용 기호 외의 것을 넣으면 에러를 일으킨다")
        @ParameterizedTest
        @CsvSource(value = {"i","0","P"})
        void ofTest_IllegalArgumentException(char sign) {
            assertThatThrownBy(() -> Operation.of(sign))
                    .isInstanceOf(IllegalArgumentException.class);
        }
    }

    @DisplayName("Operation에 따라 계산을 할 수 있다.")
    @ParameterizedTest
    @CsvSource(value = {"PLUS,10", "MINUS,6", "MULTIPLY,16", "DIVIDE,4"})
    void calculateTest(Operation operation, int expected) {
        int left = 8;
        int right = 2;

        int actual = operation.calculate(left, right);

        assertThat(actual).isEqualTo(expected);
    }
}