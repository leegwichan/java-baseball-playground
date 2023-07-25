package numberbaseball.dto;

public enum RetryDto {

    RESTART(true),
    EXIT(false);

    private final boolean isRetry;

    RetryDto(boolean isRetry) {
        this.isRetry = isRetry;
    }

    public boolean isRetry() {
        return isRetry;
    }
}
