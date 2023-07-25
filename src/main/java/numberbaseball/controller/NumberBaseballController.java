package numberbaseball.controller;

import java.util.Map;
import java.util.Objects;
import numberbaseball.domain.BaseballDigit;
import numberbaseball.domain.BaseballNumber;
import numberbaseball.dto.ResultDto;
import numberbaseball.dto.RetryDto;
import numberbaseball.helper.RandomBaseballNumberGenerator;
import numberbaseball.view.InputView;
import numberbaseball.view.OutputView;

public final class NumberBaseballController {

    private static final Map<RetryDto, Boolean> retryMap
            = Map.of(RetryDto.RESTART, true, RetryDto.EXIT, false);

    private final InputView inputView;
    private final OutputView outputView;
    private BaseballNumber answer;
    private boolean isMatched = false;

    private NumberBaseballController(InputView inputView, OutputView outputView) {
        this.inputView = Objects.requireNonNull(inputView);
        this.outputView = Objects.requireNonNull(outputView);
    }

    public static NumberBaseballController of(InputView inputView, OutputView outputView) {
        return new NumberBaseballController(inputView, outputView);
    }

    public void start() {
        answer = BaseballNumber.from(RandomBaseballNumberGenerator.of());
        isMatched = false;
    }

    public void tryCompareWithAnswer() {
        int input = inputView.inputNumber();
        BaseballNumber question = BaseballNumber.from(BaseballDigit.getList(input));
        ResultDto resultDto = ResultDto.builder(answer.getLength())
                .strike(answer.countSameDigitSamePosition(question))
                .ball(answer.countSameDigitDifferentPosition(question))
                .build();
        isMatched = resultDto.isCorrect();
        outputView.printMatchResult(resultDto);
    }

    public boolean isMatched() {
        return isMatched;
    }

    public boolean isRestart() {
        RetryDto retryDto = inputView.inputRetryDto();
        return retryMap.getOrDefault(retryDto, false);
    }

}
