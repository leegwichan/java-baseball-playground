package study;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class SetTest {
    private Set<Integer> numbers;

    @BeforeEach
    void setUp() {
        numbers = new HashSet<>();
        numbers.add(1);
        numbers.add(1);
        numbers.add(2);
        numbers.add(3);
    }

    @Test
    @DisplayName("집합의 크기를 알 수 있다")
    void sizeTest() {
        int expected = 3;

        int actual = numbers.size();

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("집합에 해당 원소가 들어있는지 알 수 있다")
    void containsTest() {
        assertThat(numbers.contains(1)).isTrue();
        assertThat(numbers.contains(3)).isTrue();
        assertThat(numbers.contains(5)).isFalse();
    }

    @DisplayName("집합에 각 원소가 있는지 알 수 있다.")
    @ParameterizedTest
    @CsvSource(value = {"1:true", "2:true", "3:true", "4:false", "5:false"},
            delimiter = ':')
    void containsTest_parameterized(int element, boolean expected) {
        boolean actual = numbers.contains(element);

        assertThat(actual).isEqualTo(expected);
    }
}
