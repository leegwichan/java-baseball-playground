package numberbaseball.view;

import java.util.Map;
import java.util.Objects;
import numberbaseball.dto.RetryDto;
import numberbaseball.view.printer.Printer;
import numberbaseball.view.reader.Reader;

public final class InputView {

    private static final String RESTART_INPUT_FORMAT = "1";
    private static final String EXIT_INPUT_FORMAT = "2";
    private static final Map<String, RetryDto> retryFormatMap
             = Map.of(RESTART_INPUT_FORMAT, RetryDto.RESTART, EXIT_INPUT_FORMAT, RetryDto.EXIT);
    private static final String INPUT_NUMBER_REQUEST = "숫자를 입력해 주세요 : ";
    private static final String INPUT_RETRY_REQUEST =
            String.format("게임을 새로 시작하려면 %s, 종료하려면 %s를 입력하세요.", RESTART_INPUT_FORMAT, EXIT_INPUT_FORMAT);

    private static final String NUMBER_FORMAT_ERROR_MESSAGE = "[ERROR] 숫자를 입력해야 합니다";
    private static final String RETRY_FORMAT_ERROR_MESSAGE = "[ERROR] 재시도 형식에 맞추어 입력해야 합니다";

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

    public RetryDto inputRetryDto() {
        String inputMessage = reader.read().trim();

        if (retryFormatMap.containsKey(inputMessage)) {
            return retryFormatMap.get(inputMessage);
        }
        throw new IllegalArgumentException(RETRY_FORMAT_ERROR_MESSAGE);
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
