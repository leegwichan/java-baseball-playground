package study;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class StringTest {
    @Test
    void replace() {
        String actual = "abc".replace("b", "d");
        assertThat(actual).isEqualTo("adc");
    }

    @Nested
    @DisplayName("split() 테스트")
    class SplitTest {
        @Test
        @DisplayName("\'1,2\'를 ,으로 나누면 1와 2를 포함한다")
        void splitTwoElementsTest() {
            String[] expected = new String[]{"1", "2"};

            String[] actual = "1,2".split(",");

            assertThat(actual).contains(expected);
        }

        @Test
        @DisplayName("\'1\'를 ,으로 split하면 1로 분리된다.")
        void splitOneElementTest() {
            String[] expected = new String[]{"1"};

            String[] actual = "1".split(",");

            assertThat(actual).containsExactly(expected);
        }
    }
}
