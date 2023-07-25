package numberbaseball.helper;

import java.util.List;
import numberbaseball.domain.BaseballDigit;

@FunctionalInterface
public interface NumberGenerator {

    List<BaseballDigit> generate(int totalDigit);
}
