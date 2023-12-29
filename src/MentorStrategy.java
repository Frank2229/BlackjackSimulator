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

        if (getTrueCount() >= 5 && getTrueCount() <= 10) wager = tableMin * 5;
        else if (getTrueCount() >= 11 && getTrueCount() <= 15) wager = tableMin * 10;
        else if (getTrueCount() >= 11 && getTrueCount() <= 13) wager = tableMin * 15;
        else if (getTrueCount() >= 14 && getTrueCount() <= 16) wager = tableMin * 20;
        else if (getTrueCount() >= 17) wager = tableMin * 25;
        else wager = tableMin;

        if (wager > tableMax) wager = tableMax;

        return wager;
    }
}
