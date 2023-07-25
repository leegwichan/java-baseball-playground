package numberbaseball.helper;

import java.util.List;
import numberbaseball.domain.BaseballDigit;

@FunctionalInterface
public interface BaseballNumberGenerator {

    List<BaseballDigit> generate(int size);
}
