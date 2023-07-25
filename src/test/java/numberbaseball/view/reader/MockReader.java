package numberbaseball.view.reader;

public class MockReader implements Reader {

    private static final String BLANK = "";

    private final String stubMessage;

    public MockReader(String stubMessage) {
        this.stubMessage = stubMessage;
    }

    public MockReader() {
        this(BLANK);
    }

    @Override
    public String read() {
        return stubMessage;
    }
}
