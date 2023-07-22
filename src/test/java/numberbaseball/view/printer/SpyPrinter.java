package numberbaseball.view.printer;

public class SpyPrinter implements Printer {

    private String printedMessage;

    @Override
    public void print(String message) {
        this.printedMessage = message;
    }

    public String getPrintedMessage() {
        return printedMessage;
    }
}
