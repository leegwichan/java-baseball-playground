package numberbaseball.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class RetryDtoTest {

    @DisplayName("재시도 여부를 제공할 수 있다")
    @ParameterizedTest(name = "{0} : {1}")
    @CsvSource({"RESTART,true","EXIT,false"})
    void isRetryTest(RetryDto retryDto, boolean expected) {
        boolean actual = retryDto.isRetry();

        assertThat(actual).isEqualTo(expected);
    }
}