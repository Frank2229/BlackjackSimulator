import java.util.Objects;

public class MentorStrategy extends BasicStrategy {
    public MentorStrategy() {
        setTakesInsurance(false);
        setStrategyName("MENTOR STRATEGY");
        setCountValue(0, 1);
        setCountValue(1, 2);
        setCountValue(2, 2);
        setCountValue(3, 2);
        setCountValue(4, 2);
        setCountValue(5, 1);
        setCountValue(6, 0);
        setCountValue(7, -1);
        setCountValue(8, -2);
        setCountValue(9, -1);
    }

    @Override
    public void addCardToCount(int card, int remainingCards) {
        setRunningCount(getRunningCount() + getCountValues()[card - 2]);
        setTrueCount((int) (getRunningCount() / (remainingCards / 104.0)));
    }

    public int calculateWager(int tableMin, int tableMax) {
        int wager;

        if (getTrueCount() >= 4 && getTrueCount() <= 6) wager = tableMin * 2;
        else if (getTrueCount() >= 7 && getTrueCount() <= 9) wager = tableMin * 4;
        else if (getTrueCount() >= 10 && getTrueCount() <= 12) wager = tableMin * 6;
        else if (getTrueCount() >= 13 && getTrueCount() <= 15) wager = tableMin * 8;
        else if (getTrueCount() >= 16) wager = tableMin * 10;
        else wager = tableMin;

        if (wager > tableMax) wager = tableMax;

        return wager;
    }
}
