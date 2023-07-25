package numberbaseball.dto;

public class ResultDto {

    private final int strike;
    private final int ball;
    private final int totalDigit;

    private ResultDto(int strike, int ball, int totalDigit) {
        if (strike + ball > totalDigit) {
            throw new IllegalArgumentException();
        }
        this.strike = strike;
        this.ball = ball;
        this.totalDigit = totalDigit;
    }

    public static Builder builder(int totalDigit) {
        return new Builder(totalDigit);
    }

    public int getStrike() {
        return strike;
    }

    public int getBall() {
        return ball;
    }

    public boolean isCorrect() {
        return strike == totalDigit && ball == 0;
    }

    public static class Builder {
        private int strike;
        private int ball;
        private final int totalDigit;

        Builder(int totalDigit) {
            this.totalDigit = totalDigit;
        }

        public Builder strike(int strike) {
            this.strike = strike;
            return this;
        }

        public Builder ball(int ball) {
            this.ball = ball;
            return this;
        }

        public ResultDto build() {
            return new ResultDto(strike, ball, totalDigit);
        }
    }
}
