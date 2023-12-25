public abstract class Strategy {
    private boolean takesInsurance;
    private int trueCount = 0;
    private int[] countValues = new int[10];
    private int runningCount = 0;
    private String strategyName;

    public void addCardToCount(int card, int remainingCards) {
        runningCount += countValues[card - 2];
        trueCount = (int) (runningCount / (remainingCards / 52.0));
    }

    public abstract int calculateWager(int tableMin, int tableMax);

    public int[] getCountValues() {
        return countValues;
    }

    public int getRunningCount() {
        return runningCount;
    }

    public String getStrategyName() {
        return strategyName;
    }

    public int getTrueCount() {
        return trueCount;
    }

    public boolean isTakesInsurance() {
        return takesInsurance;
    }

    public abstract void playHand(Hand hand, Deck deck, Hand dealerHand, Player player, int currentHand, Rules rules);

    public void resetCount() {
        runningCount = 0;
        trueCount = 0;
    }

    public void setCountValue(int index, int value) {
        countValues[index] = value;
    }

    public void setRunningCount(int runningCount) {
        this.runningCount = runningCount;
    }

    public void setStrategyName(String strategyName) { this.strategyName = strategyName; }

    public void setTakesInsurance(boolean takesInsurance) {
        this.takesInsurance = takesInsurance;
    }

    public void setTrueCount(int trueCount) {
        this.trueCount = trueCount;
    }
}