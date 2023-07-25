package numberbaseball.view;

import java.util.Objects;
import numberbaseball.dto.ResultDto;
import numberbaseball.view.printer.Printer;

public final class OutputViewImpl implements OutputView {

    private static final String BLANK = " ";
    private static final String LINE_CHANGER = "\n";
    private static final String STRIKE = "스트라이크";
    private static final String BALL = "볼";
    private static final String OUT = "아웃";
    private static final String GAME_END = "개의 숫자를 모두 맞히셨습니다! 게임 종료\n";

    private final Printer printer;

    private OutputViewImpl(Printer printer) {
        this.printer = Objects.requireNonNull(printer);
    }

    static OutputViewImpl of(Printer printer) {
        return new OutputViewImpl(printer);
    }

    @Override
    public void printMatchResult(ResultDto result) {
        StringBuilder sb = new StringBuilder();
        appendBallMessage(sb, result);
        appendStrikeMessage(sb, result);
        appendOutMessageIfExistNothing(sb);
        sb.append(LINE_CHANGER);
        appendEndMessageIfAllMatched(sb, result);

        printer.print(sb.toString());
    }

    private void appendBallMessage(StringBuilder sb, ResultDto resultDto) {
        if (resultDto.getBall() == 0) {
            return;
        }
        appendBlankIfExist(sb);
        sb.append(resultDto.getBall()).append(BALL);
    }

    private void appendStrikeMessage(StringBuilder sb, ResultDto resultDto) {
        if (resultDto.getStrike() == 0) {
            return;
        }
        appendBlankIfExist(sb);
        sb.append(resultDto.getStrike()).append(STRIKE);
    }

    private void appendOutMessageIfExistNothing(StringBuilder sb) {
        if (sb.length() == 0) {
            sb.append(OUT);
        }
    }

    private void appendEndMessageIfAllMatched(StringBuilder sb, ResultDto resultDto) {
        if (resultDto.isCorrect()) {
            sb.append(GAME_END);
        }
    }

    private void appendBlankIfExist(StringBuilder sb) {
        if (sb.length() != 0) {
            sb.append(BLANK);
        }
    }
}
