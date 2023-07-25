package numberbaseball;

import numberbaseball.controller.NumberBaseballController;
import numberbaseball.view.InputView;
import numberbaseball.view.OutputView;
import numberbaseball.view.printer.ConsolePrinter;
import numberbaseball.view.reader.ConsoleReader;

public class NumberBaseballApplication {

    private static final NumberBaseballController controller
            = NumberBaseballController.of(
            InputView.of(new ConsoleReader(), new ConsolePrinter()),
            OutputView.of(new ConsolePrinter()));

    private NumberBaseballApplication() {}

    public static void main(String[] args) {
        do {
            playNumberBaseballGame();
        } while (controller.isRestart());

    }

    private static void playNumberBaseballGame() {
        controller.start();
        while (!controller.isMatched()) {
            controller.tryCompareWithAnswer();
        }
    }
}
