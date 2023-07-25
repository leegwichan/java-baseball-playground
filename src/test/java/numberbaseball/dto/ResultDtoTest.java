package numberbaseball.dto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class ResultDtoTest {

    @DisplayName("자릿 수보다 ball과 strike의 합이 더 크면 예외를 던진다")
    @ParameterizedTest(name = "strike : {0}, ball : {1}, 자릿 수 : {2}")
    @CsvSource({"4,0,3", "2,2,3"})
    void creationTest_whenStrikeAndBallIsOverThanTotalDigit_throwException(
            int strike, int ball, int totalDigit) {
        ResultDto.Builder builder = ResultDto.builder(totalDigit).strike(strike).ball(ball);

        assertThatThrownBy(() -> builder.build())
                .isInstanceOf(IllegalArgumentException.class);

    }

    @DisplayName("입력한 값에 따라 strike, ball값을 반환한다")
    @ParameterizedTest(name = "strike : {0}, ball : {1}, 자릿 수 : {2}")
    @CsvSource({"1,1,3", "3,0,3", "0,2,3"})
    void getterTest(int strike, int ball, int totalDigit) {
        ResultDto actual = ResultDto.builder(totalDigit)
                .strike(strike).ball(ball).build();

        assertThat(actual.getStrike()).isEqualTo(strike);
        assertThat(actual.getBall()).isEqualTo(ball);
    }

    @DisplayName("strike 값과 ball 값을 통해 현재 정답과 일치했는지 반환한다")
    @ParameterizedTest(name = "strike : {0}, ball : {1}, 자릿 수 : {2}, 일치 여부 : {3}")
    @CsvSource({"1,1,3,false", "3,0,3,true", "0,2,3,false"})
    void isCorrectTest(int strike, int ball, int totalDigit, boolean expected) {
        ResultDto actual = ResultDto.builder(totalDigit)
                .strike(strike).ball(ball).build();

        assertThat(actual.isCorrect()).isEqualTo(expected);
    }
}