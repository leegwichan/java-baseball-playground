package numberbaseball.helper;

import java.util.List;
import numberbaseball.domain.Digit;

@FunctionalInterface
public interface NumberGenerator {

    List<Digit> generate(int totalDigit);
}
