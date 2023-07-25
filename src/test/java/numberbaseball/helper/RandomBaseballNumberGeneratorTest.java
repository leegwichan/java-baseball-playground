package numberbaseball.helper;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import java.util.Set;
import numberbaseball.domain.BaseballDigit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class RandomBaseballNumberGeneratorTest {

    @DisplayName("크기에 따라 곂치지 않는 숫자들을 만들어 낼 수 있다")
    @ParameterizedTest(name = "size = {0}")
    @CsvSource({"3", "4", "5"})
    void generateTest(int size) {
        RandomBaseballNumberGenerator generator = RandomBaseballNumberGenerator.of();

        List<BaseballDigit> actual = generator.generate(size);

        assertThat(actual).hasSize(size);
        assertListElementIsNotEqual(actual);
    }

    @DisplayName("크기가 1보다 작거나 9보다 클 경우 예외를 던진다")
    @ParameterizedTest(name = "size = {0}")
    @CsvSource({"-1", "10", "0"})
    void generateTest_whenSizeIsOutOfRange_throwException(int size) {
        RandomBaseballNumberGenerator generator = RandomBaseballNumberGenerator.of();

        assertThatThrownBy(() -> generator.generate(size))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("곂치지 않는 숫자는 1~9자리 까지만 만들 수 있습니다");
    }

    void assertListElementIsNotEqual(List<BaseballDigit> digits) {
        assertThat(digits).hasSameSizeAs(Set.copyOf(digits));
    }
}