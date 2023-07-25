package numberbaseball.view;

import numberbaseball.dto.RetryDto;
import numberbaseball.view.printer.Printer;
import numberbaseball.view.reader.Reader;

public interface InputView {

    static InputView of(Reader reader, Printer printer) {
        return InputViewImpl.of(reader, printer);
    }

    int inputNumber();
    RetryDto inputRetryDto();
}
