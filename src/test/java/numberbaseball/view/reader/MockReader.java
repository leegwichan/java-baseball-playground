package numberbaseball.view.reader;

public class MockReader implements Reader {

    private final String stubMessage;

    public MockReader(String stubMessage) {
        this.stubMessage = stubMessage;
    }

    @Override
    public String read() {
        return stubMessage;
    }
}
