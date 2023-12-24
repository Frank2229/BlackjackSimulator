public class DealerStrategy extends Strategy{
    public DealerStrategy() {
        setStrategyName("DEALER STRATEGY");
    }
    public int calculateWager(int tableMin) {
        return 0;
    }

    public void playHand(Hand hand, Deck deck, Hand dealerHand, Player player, int currentHand, Rules rules) {
        boolean isRoundOver = false;

        // Dealer keeps hitting until hand is greater than 17.
        // If hand is equal to 17, the dealer only takes another card if the rules specify the dealer is supposed to hit
        // on soft 17 and has 1 or more soft cards.
        while (!isRoundOver) {
            if (hand.getValue() < 17) hand.addCard(deck.dealCard());
            else if (hand.getValue() > 17) isRoundOver = true;
            else {
                if (hand.getSoftCards() > 0 && !rules.isS17()) hand.addCard(deck.dealCard());
                else isRoundOver = true;
            }
        }
    }
}