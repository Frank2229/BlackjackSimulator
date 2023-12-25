public class StandStrategy extends Strategy{
    public StandStrategy() {
        setTakesInsurance(false);
        setStrategyName("STAND STRATEGY");
        for (int i = 0; i < 10; i++) setCountValue(i, 0);
    }

    public int calculateWager(int tableMin, int tableMax) {
        return tableMin;
    }

    public void playHand(Hand hand, Deck deck, Hand dealerHand, Player player, int currentHand, Rules rules) {

    }
}