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

    public abstract void playHand(Hand hand, boolean isS17, Deck deck, Hand dealerHand, boolean isDAS, int totalDecks, Player player, int currentHand, int doubleLimit, boolean isSurrender);

    public void setStrategyName(String strategyName) { this.strategyName = strategyName; }

    public void setTakesInsurance(boolean takesInsurance) {
        this.takesInsurance = takesInsurance;
    }
}