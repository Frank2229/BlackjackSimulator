public class FollowDealerStrategy extends Strategy{
    public FollowDealerStrategy() {
        setTakesInsurance(false);
        setStrategyName("FOLLOW DEALER STRATEGY");
        for (int i = 0; i < 10; i++) setCountValue(i, 0);
    }

    public int calculateWager(int tableMin, int tableMax) {
        return tableMin;
    }

    public void playHand(Hand hand, Deck deck, Hand dealerHand, Player player, int currentHand, Rules rules) {
        boolean isRoundOver = false;

        // Dealer keeps hitting until hand is greater than 17.
        // If hand is equal to 17, the dealer only takes another card if the rules specify the dealer is supposed to hit
        // on soft 17 and has 1 or more soft cards.
        while (!isRoundOver) {
            if (hand.getValue() < 17) deck.dealCard(hand);
            else if (hand.getValue() > 17) isRoundOver = true;
            else {
                if (hand.getSoftCards() > 0 && !rules.isS17()) deck.dealCard(hand);
                else isRoundOver = true;
            }
        }
    }
}