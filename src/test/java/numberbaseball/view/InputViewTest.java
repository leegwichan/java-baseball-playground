package numberbaseball.view;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.in;

import numberbaseball.dto.RetryDto;
import numberbaseball.view.printer.Printer;
import numberbaseball.view.printer.SpyPrinter;
import numberbaseball.view.reader.MockReader;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

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

    @DisplayName("숫자 야구에서 숫자를 입력 받을 수 있다")
    @Nested
    class InputNumberTest {

        @DisplayName("숫자를 입력받아 해당 숫자를 입력받을 수 있다")
        @ParameterizedTest(name = "{0}")
        @CsvSource({"123,123", "846, 846", "741,741"})
        void inputNumberTest(String input, int expected) {
            SpyPrinter spyPrinter = newSpyPrinter();
            InputView inputView = newInputView(input, spyPrinter);

            int actual = inputView.inputNumber();

            assertThat(spyPrinter.getPrintedMessage()).isEqualTo("숫자를 입력해 주세요 : ");
            assertThat(actual).isEqualTo(expected);
        }

        @DisplayName("숫자 외의 다른 문자가 들어간 경우 예외를 던진다")
        @ParameterizedTest(name = "{0}")
        @CsvSource({"sdf", "85)", "-85~"})
        void inputNumberTest_whenInputNotNumber_throwException(String input) {
            InputView inputView = newInputView(input);

            assertThatThrownBy(() -> inputView.inputNumber())
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("[ERROR] 숫자를 입력해야 합니다");
        }
    }

    @DisplayName("재시도 여부를 입력받을 수 있다")
    @Nested
    class InputRetryDtoTest {

        @DisplayName("형식에 맞춰 입력하면 재시도 여부를 반환할 수 있다")
        @ParameterizedTest(name = "{0}을 입력하면 {1}을 반환한다")
        @CsvSource({"1,RESTART", "2,EXIT", " 1,RESTART", "2 ,EXIT"})
        void inputRetryTest(String input, RetryDto excepted) {
            SpyPrinter spyPrinter = newSpyPrinter();
            InputView inputView = newInputView(input, spyPrinter);

            RetryDto actual = inputView.inputRetryDto();

            assertThat(spyPrinter.getPrintedMessage())
                    .isEqualTo("게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.\n");
            assertThat(actual).isEqualTo(excepted);
        }

        @DisplayName("형식 외의 값을 입력하면 예외를 던진다")
        @ParameterizedTest(name = "{0}")
        @CsvSource({"12", "@", "3", "a"})
        void inputRetryTest(String input) {
            InputView inputView = newInputView(input);

            assertThatThrownBy(() -> inputView.inputRetryDto())
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("[ERROR] 재시도 형식에 맞추어 입력해야 합니다");
        }
    }

    InputView newInputView(String stubInputMessage, Printer printer) {
        return InputView.of(new MockReader(stubInputMessage), printer);
    }

    InputView newInputView(String stubInputMessage) {
        return InputView.of(new MockReader(stubInputMessage), newSpyPrinter());
    }

    SpyPrinter newSpyPrinter() {
        return new SpyPrinter();
    }
}