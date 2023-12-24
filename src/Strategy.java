public abstract class Strategy {
    private boolean takesInsurance;
    private String strategyName;

    public abstract int calculateWager(int tableMin);

    public String getStrategyName() {
        return strategyName;
    }

    public boolean isTakesInsurance() {
        return takesInsurance;
    }

    public abstract void playHand(Hand hand, Deck deck, Hand dealerHand, Player player, int currentHand, Rules rules);

    public void setStrategyName(String strategyName) { this.strategyName = strategyName; }

    public void setTakesInsurance(boolean takesInsurance) {
        this.takesInsurance = takesInsurance;
    }
}