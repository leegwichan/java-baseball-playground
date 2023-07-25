package numberbaseball.controller;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import numberbaseball.view.InputView;
import numberbaseball.view.OutputView;
import numberbaseball.view.StubInputView;
import numberbaseball.view.StubOutputView;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class NumberBaseballControllerTest {

    @DisplayName("인자를 받아 객체를 생성할 수 있다")
    @Nested
    class CreationTest {

        private final InputView stubInputView = new StubInputView();
        private final OutputView stubOutputView = new StubOutputView();

        @DisplayName("InputView가 null인 경우 예외를 던진다")
        @Test
        void creationTest_whenInputViewIsNull_throwException() {
            assertThatThrownBy(() -> NumberBaseballController.of(null, stubOutputView))
                    .isInstanceOf(NullPointerException.class);
        }

        @DisplayName("OutputView가 null인 경우 예외를 던진다")
        @Test
        void creationTest_whenOutputViewIsNull_throwException() {
            assertThatThrownBy(() -> NumberBaseballController.of(stubInputView, null))
                    .isInstanceOf(NullPointerException.class);
        }
    }


}