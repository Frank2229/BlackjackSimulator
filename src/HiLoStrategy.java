public class HiLoStrategy extends Strategy{
    public HiLoStrategy() {
        setTakesInsurance(false);
        setStrategyName("HI-LO STRATEGY");
    }

    public int calculateWager(int tableMin) {
        return tableMin;
    }

    public void playHand(Hand hand, boolean isS17, Deck deck, Hand dealerHand, boolean isDAS, int totalDecks, Player player, int currentHand, int doubleLimit, boolean isSurrender) {

    }
}