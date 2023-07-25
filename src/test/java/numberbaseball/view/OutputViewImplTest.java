package numberbaseball.view;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import numberbaseball.dto.ResultDto;
import numberbaseball.view.printer.SpyPrinter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class OutputViewImplTest {

    @DisplayName("인자를 받아 생성할 수 있다")
    @Nested
    class CreationTest {

        @DisplayName("인자에 null이 있으면 예외를 던진다")
        @Test
        void creationTest_whenParameterIsNull_throwException() {
            assertThatThrownBy(() -> OutputViewImpl.of(null))
                    .isInstanceOf(NullPointerException.class);
        }
    }

    @DisplayName("결과 값에 따라 형식에 맞춰 출력한다")
    @ParameterizedTest()
    @CsvSource(value = {"2,0,3,2스트라이크", "2,1,3,1볼 2스트라이크", "0,0,3,아웃", "0,1,3,1볼",
        "3,0,3,3스트라이크\n3개의 숫자를 모두 맞히셨습니다! 게임 종료"}, delimiterString = ",")
    void printMatchResultTest(int strike, int ball, int totalDigit, String expected) {
        SpyPrinter printer = new SpyPrinter();
        OutputViewImpl outputView = OutputViewImpl.of(printer);
        ResultDto resultDto = newResultDto(strike, ball, totalDigit);

        outputView.printMatchResult(resultDto);

        assertThat(printer.getPrintedMessage()).contains(expected);
    }

    ResultDto newResultDto(int strike, int ball, int totalDigit) {
        return ResultDto.builder(totalDigit)
                .strike(strike).ball(ball).build();
    }
}