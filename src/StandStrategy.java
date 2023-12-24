public class StandStrategy extends Strategy{
    public StandStrategy() {
        setTakesInsurance(false);
        setStrategyName("STAND STRATEGY");
    }

    public int calculateWager(int tableMin) {
        return tableMin;
    }

    public void playHand(Hand hand, Deck deck, Hand dealerHand, Player player, int currentHand, Rules rules) {

    }
}