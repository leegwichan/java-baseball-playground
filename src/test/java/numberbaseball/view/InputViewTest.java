package numberbaseball.view;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import numberbaseball.view.printer.SpyPrinter;
import numberbaseball.view.reader.MockReader;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class InputViewTest {

    @DisplayName("인자를 받아 객체를 생성할 수 있다")
    @Nested
    class CreationTest {

        @DisplayName("Reader가 null인 경우 예외를 던진다")
        @Test
        void creationTest_whenReaderIsNull_throwException() {
            assertThatThrownBy(() -> InputView.of(null, new SpyPrinter()))
                    .isInstanceOf(NullPointerException.class);
        }

        @DisplayName("Printer가 null인 경우 예외를 던진다")
        @Test
        void creationTest_whenPrinterIsNull_throwException() {
            assertThatThrownBy(() -> InputView.of(new MockReader(""), null))
                    .isInstanceOf(NullPointerException.class);
        }

        @DisplayName("인자들이 모두 null이 아닌 경우 정상적으로 생성된다")
        @Test
        void creationTest_whenAllParameterIsNotNull() {
            assertThatCode(() -> InputView.of(new MockReader(""), new SpyPrinter()))
                    .doesNotThrowAnyException();
        }
    }
}