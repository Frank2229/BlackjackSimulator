import java.util.Objects;

public class HiLoStrategy extends BasicStrategy {
    public HiLoStrategy() {
        setTakesInsurance(false);
        setStrategyName("HI-LO STRATEGY");
        setCountValue(0, 1);
        setCountValue(1, 1);
        setCountValue(2, 1);
        setCountValue(3, 1);
        setCountValue(4, 1);
        setCountValue(5, 0);
        setCountValue(6, 0);
        setCountValue(7, 0);
        setCountValue(8, -1);
        setCountValue(9, -1);
    }

    public int calculateWager(int tableMin, int tableMax) {
        int wager;

        if (getTrueCount() >= 0) {
            wager = tableMin * getTrueCount();

            if (wager > tableMax) wager = tableMax;
        }
        else wager = 0;

        return wager;
    }
}