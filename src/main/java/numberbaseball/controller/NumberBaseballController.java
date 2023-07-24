package numberbaseball.controller;

import java.util.Objects;
import numberbaseball.view.InputView;
import numberbaseball.view.OutputView;

public final class NumberBaseballController {
    private final InputView inputView;
    private final OutputView outputView;
    private Number answer;

    private NumberBaseballController(InputView inputView, OutputView outputView) {
        this.inputView = Objects.requireNonNull(inputView);
        this.outputView = Objects.requireNonNull(outputView);
    }

    public static NumberBaseballController of(InputView inputView, OutputView outputView) {
        return new NumberBaseballController(inputView, outputView);
    }

    public void start() {

    }

    public void tryCompareWithAnswer() {

    }

    public boolean isMatched() {
        return false;
    }

    public boolean isRestart() {
        return false;
    }

}
