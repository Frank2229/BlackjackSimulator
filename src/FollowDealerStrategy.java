public class FollowDealerStrategy extends Strategy{
    public FollowDealerStrategy() {
        setTakesInsurance(false);
        setStrategyName("FOLLOW DEALER STRATEGY");
    }

    public int calculateWager(int tableMin) {
        return tableMin;
    }

    public void playHand(Hand hand, boolean isS17, Deck deck, Hand dealerHand, boolean isDAS, int totalDecks, Player player, int currentHand, int doubleLimit, boolean isSurrender) {
        boolean isRoundOver = false;

        // Dealer keeps hitting until hand is greater than 17.
        // If hand is equal to 17, the dealer only takes another card if the rules specify the dealer is supposed to hit
        // on soft 17 and has 1 or more soft cards.
        while (!isRoundOver) {
            if (hand.getValue() < 17) hand.addCard(deck.dealCard());
            else if (hand.getValue() > 17) isRoundOver = true;
            else {
                if (hand.getSoftCards() > 0 && !isS17) hand.addCard(deck.dealCard());
                else isRoundOver = true;
            }
        }
    }
}