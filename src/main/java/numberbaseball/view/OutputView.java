package numberbaseball.view;

import numberbaseball.dto.ResultDto;
import numberbaseball.view.printer.Printer;

public interface OutputView {

    static OutputView of(Printer printer) {
        return OutputViewImpl.of(printer);
    }

    void printMatchResult(ResultDto result);
}
