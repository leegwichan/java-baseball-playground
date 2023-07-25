package numberbaseball.view;

import numberbaseball.dto.RetryDto;

public class StubInputView implements InputView {
    @Override
    public int inputNumber() {
        return 0;
    }

    @Override
    public RetryDto inputRetryDto() {
        return null;
    }
}
