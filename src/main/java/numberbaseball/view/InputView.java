package numberbaseball.view;

import java.util.Objects;
import numberbaseball.view.printer.Printer;
import numberbaseball.view.reader.Reader;

public class InputView {

    private static final String INPUT_NUMBER_REQUEST = "숫자를 입력해 주세요 : ";

    private static final String NUMBER_FORMAT_ERROR_MESSAGE = "[ERROR] 숫자를 입력해야 합니다";

    private final Reader reader;
    private final Printer printer;

    private InputView(Reader reader, Printer printer) {
        this.reader = Objects.requireNonNull(reader);
        this.printer = Objects.requireNonNull(printer);
    }

    public static InputView of(Reader reader, Printer printer) {
        return new InputView(reader, printer);
    }

    public int inputNumber() {
        printer.print(INPUT_NUMBER_REQUEST);
        return inputInt();
    }

    private int inputInt() {
        try {
            String inputMessage = reader.read();
            return Integer.parseInt(inputMessage);
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException(NUMBER_FORMAT_ERROR_MESSAGE);
        }
    }

}
