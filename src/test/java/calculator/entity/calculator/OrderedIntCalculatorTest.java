package calculator.entity.calculator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import calculator.entity.Operation;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OrderedIntCalculatorTest {

    private final IntCalculator calculator = new OrderedIntCalculator();

    @Test
    @DisplayName("operations 개수가 numbers 개수보다 하나 작지 않은 경우 예외를 던진다")
    void calculateTest_whenFormatIsNotMatched() {
        List<Integer> numbers = List.of(1, 2, 3);
        List<Operation> operations = List.of(Operation.PLUS);

        assertThatThrownBy(() -> calculator.calculate(numbers, operations))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("숫자 한개만 입력한 경우 해당 숫자를 그대로 반환한다")
    void calculateTest_whenInputOnlyOneNumber() {
        List<Integer> numbers = List.of(1);
        List<Operation> operations = List.of();

        int actual = calculator.calculate(numbers, operations);

        assertThat(actual).isEqualTo(1);
    }

    @Test
    @DisplayName("2 + 3 * 5의 계산 결과는 25이다")
    void calculateTest_normalCase1() {
        List<Integer> numbers = List.of(2, 3, 5);
        List<Operation> operations = List.of(Operation.PLUS, Operation.MULTIPLY);

        int actual = calculator.calculate(numbers, operations);

        assertThat(actual).isEqualTo(25);
    }

    @Test
    @DisplayName("2 - 3의 계산 결과는 -1이다")
    void calculateTest_normalCase2() {
        List<Integer> numbers = List.of(2, 3);
        List<Operation> operations = List.of(Operation.MINUS);

        int actual = calculator.calculate(numbers, operations);

        assertThat(actual).isEqualTo(-1);
    }
}