package numberbaseball.view;

import java.util.Objects;
import numberbaseball.view.printer.Printer;
import numberbaseball.view.reader.Reader;

public class InputView {

    private final Reader reader;
    private final Printer printer;

    private InputView(Reader reader, Printer printer) {
        this.reader = Objects.requireNonNull(reader);
        this.printer = Objects.requireNonNull(printer);
    }

    public static InputView of(Reader reader, Printer printer) {
        return new InputView(reader, printer);
    }

}
