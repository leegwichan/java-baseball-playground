package numberbaseball.view;

import java.util.Objects;
import numberbaseball.view.printer.Printer;

public final class OutputView {

    private final Printer printer;

    private OutputView(Printer printer) {
        this.printer = Objects.requireNonNull(printer);
    };

    public static OutputView of(Printer printer) {
        return new OutputView(printer);
    }
}
