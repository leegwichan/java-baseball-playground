package calculator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class OperatorTest {

    @Nested
    @DisplayName("of() 테스트")
    class StaticFactoryTest {

        @DisplayName("기호를 통해 Operator 객체를 가져올 수 있다")
        @ParameterizedTest
        @CsvSource(value = {"+,PLUS", "-,MINUS", "*,MULTIPLY", "/,DIVIDE"})
        void ofTest(char sign, Operator expected) {
            Operator actual = Operator.of(sign);

            assertThat(actual).isEqualTo(expected);
        }

        @DisplayName("허용 기호 외의 것을 넣으면 에러를 일으킨다")
        @ParameterizedTest
        @CsvSource(value = {"i","0","P"})
        void ofTest_IllegalArgumentException(char sign) {
            assertThatThrownBy(() -> Operator.of(sign))
                    .isInstanceOf(IllegalArgumentException.class);
        }
    }
}