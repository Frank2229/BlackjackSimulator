import java.util.Objects;

public class MentorStrategy extends Strategy{
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

        if (getTrueCount() >= 5 && getTrueCount() <= 7) wager = tableMin * 5;
        else if (getTrueCount() >= 8 && getTrueCount() <= 10) wager = tableMin * 10;
        else if (getTrueCount() >= 11 && getTrueCount() <= 13) wager = tableMin * 15;
        else if (getTrueCount() >= 14 && getTrueCount() <= 16) wager = tableMin * 20;
        else if (getTrueCount() >= 17) wager = tableMin * 25;
        else wager = tableMin;

        if (wager > tableMax) wager = tableMax;

        return wager;
    }

    public void playHand(Hand hand, Deck deck, Hand dealerHand, Player player, int currentHand, Rules rules) {
        boolean isRoundOver = false;

        if (player.getHands().size() > 1 && hand.getCards().size() == 2 && hand.getCards().getFirst() == 11) {
            if (hand.getCards().get(1) != 11 || (hand.getCards().get(1) == 11 && !rules.isRSA())) isRoundOver = true;
        }

        while (!isRoundOver) {
            if (hand.getCards().size() == 2) {
                if (Objects.equals(hand.getCards().getFirst(), hand.getCards().get(1)) && player.getHands().size() < rules.getMaxSplitHands()) {
                    if (hand.getCards().getFirst() == 2) {
                        if (dealerHand.getCards().getFirst() >= 4 && dealerHand.getCards().getFirst() <= 7) {
                            player.splitHand(currentHand, deck);
                            player.getStrategy().addCardToCount(deck.getShoe().peek(), deck.getShoe().size());
                            deck.dealCard(hand);
                        }
                        else if (dealerHand.getCards().getFirst() == 2) {
                            if (rules.isDAS()) player.splitHand(currentHand, deck);
                            player.getStrategy().addCardToCount(deck.getShoe().peek(), deck.getShoe().size());
                            deck.dealCard(hand);
                        }
                        else if (dealerHand.getCards().getFirst() == 3) {
                            if (rules.isDAS() || rules.getTotalDecks() == 1) player.splitHand(currentHand, deck);
                            player.getStrategy().addCardToCount(deck.getShoe().peek(), deck.getShoe().size());
                            deck.dealCard(hand);
                        }
                        else {
                            player.getStrategy().addCardToCount(deck.getShoe().peek(), deck.getShoe().size());
                            deck.dealCard(hand);
                        }
                    }
                    else if (hand.getCards().getFirst() == 3) {
                        if (dealerHand.getCards().getFirst() >= 4 && dealerHand.getCards().getFirst() <= 7) {
                            player.splitHand(currentHand, deck);
                            player.getStrategy().addCardToCount(deck.getShoe().peek(), deck.getShoe().size());
                            deck.dealCard(hand);
                        }
                        else if ((dealerHand.getCards().getFirst() == 2 || dealerHand.getCards().getFirst() == 3) && rules.isDAS()) {
                            player.splitHand(currentHand, deck);
                            player.getStrategy().addCardToCount(deck.getShoe().peek(), deck.getShoe().size());
                            deck.dealCard(hand);
                        }
                        else if (dealerHand.getCards().getFirst() == 8 && rules.getTotalDecks() == 1 && rules.isDAS()) {
                            player.splitHand(currentHand, deck);
                            player.getStrategy().addCardToCount(deck.getShoe().peek(), deck.getShoe().size());
                            deck.dealCard(hand);
                        }
                        else {
                            player.getStrategy().addCardToCount(deck.getShoe().peek(), deck.getShoe().size());
                            deck.dealCard(hand);
                        }
                    }
                    else if (hand.getCards().getFirst() == 4) {
                        if (dealerHand.getCards().getFirst() == 5 || dealerHand.getCards().getFirst() == 6) {
                            if (rules.getTotalDecks() == 1) {
                                if (rules.isDAS()) {
                                    player.splitHand(currentHand, deck);
                                    player.getStrategy().addCardToCount(deck.getShoe().peek(), deck.getShoe().size());
                                    deck.dealCard(hand);
                                }
                                else if (rules.getDoubleLimit() <= 8) {
                                    player.doubleDown(currentHand);
                                    player.getStrategy().addCardToCount(deck.getShoe().peek(), deck.getShoe().size());
                                    deck.dealCard(hand);
                                    isRoundOver = true;
                                }
                                else {
                                    player.getStrategy().addCardToCount(deck.getShoe().peek(), deck.getShoe().size());
                                    deck.dealCard(hand);
                                }
                            }
                            else {
                                if (rules.isDAS()) {
                                    player.splitHand(currentHand, deck);
                                    player.getStrategy().addCardToCount(deck.getShoe().peek(), deck.getShoe().size());
                                    deck.dealCard(hand);
                                }
                                else deck.dealCard(hand);
                            }
                        }
                        else if (dealerHand.getCards().getFirst() == 4 && rules.isDAS() && rules.getTotalDecks() == 1) {
                            player.splitHand(currentHand, deck);
                            player.getStrategy().addCardToCount(deck.getShoe().peek(), deck.getShoe().size());
                            deck.dealCard(hand);
                        }
                        else {
                            player.getStrategy().addCardToCount(deck.getShoe().peek(), deck.getShoe().size());
                            deck.dealCard(hand);
                        }
                    }
                    else if (hand.getCards().getFirst() == 5) {
                        if (dealerHand.getCards().getFirst() <= 9) {
                            player.doubleDown(currentHand);
                            player.getStrategy().addCardToCount(deck.getShoe().peek(), deck.getShoe().size());
                            deck.dealCard(hand);
                            isRoundOver = true;
                        }
                        else {
                            player.getStrategy().addCardToCount(deck.getShoe().peek(), deck.getShoe().size());
                            deck.dealCard(hand);
                        }
                    }
                    else if (hand.getCards().getFirst() == 6) {
                        if (dealerHand.getCards().getFirst() >= 3 && dealerHand.getCards().getFirst() <= 6) {
                            player.splitHand(currentHand, deck);
                            player.getStrategy().addCardToCount(deck.getShoe().peek(), deck.getShoe().size());
                            deck.dealCard(hand);
                        }
                        else if (dealerHand.getCards().getFirst() == 2) {
                            if (rules.getTotalDecks() <= 2 || rules.isDAS()) player.splitHand(currentHand, deck);
                            player.getStrategy().addCardToCount(deck.getShoe().peek(), deck.getShoe().size());
                            deck.dealCard(hand);
                        }
                        else if (dealerHand.getCards().getFirst() == 7) {
                            if (rules.getTotalDecks() <= 2 && rules.isDAS()) player.splitHand(currentHand, deck);
                            player.getStrategy().addCardToCount(deck.getShoe().peek(), deck.getShoe().size());
                            deck.dealCard(hand);
                        }
                        else {
                            player.getStrategy().addCardToCount(deck.getShoe().peek(), deck.getShoe().size());
                            deck.dealCard(hand);
                        }
                    }
                    else if (hand.getCards().getFirst() == 7) {
                        if (dealerHand.getCards().getFirst() <= 7) {
                            player.splitHand(currentHand, deck);
                            player.getStrategy().addCardToCount(deck.getShoe().peek(), deck.getShoe().size());
                            deck.dealCard(hand);
                        }
                        else if (dealerHand.getCards().getFirst() == 8) {
                            if (rules.getTotalDecks() <= 2 && rules.isDAS()) player.splitHand(currentHand, deck);
                            player.getStrategy().addCardToCount(deck.getShoe().peek(), deck.getShoe().size());
                            deck.dealCard(hand);
                        }
                        else if (dealerHand.getCards().getFirst() == 10) {
                            if (rules.getTotalDecks() == 1) {
                                if (rules.isSurrender() && player.getHands().size() == 1) player.surrender(currentHand, deck);
                                isRoundOver = true;
                            }
                            else {
                                player.getStrategy().addCardToCount(deck.getShoe().peek(), deck.getShoe().size());
                                deck.dealCard(hand);
                            }
                        }
                        else if (dealerHand.getCards().getFirst() == 11) {
                            if (rules.getTotalDecks() == 1 && !rules.isS17() && player.getHands().size() == 1 && rules.isSurrender()) {
                                player.surrender(currentHand, deck);
                                isRoundOver = true;
                            }
                            else {
                                player.getStrategy().addCardToCount(deck.getShoe().peek(), deck.getShoe().size());
                                deck.dealCard(hand);
                            }
                        }
                        else {
                            player.getStrategy().addCardToCount(deck.getShoe().peek(), deck.getShoe().size());
                            deck.dealCard(hand);
                        }
                    }
                    else if (hand.getCards().getFirst() == 8) {
                        if (rules.getTotalDecks() > 1 && rules.isSurrender() && player.getHands().size() == 1 && dealerHand.getCards().getFirst() == 11 && !rules.isS17()) {
                            player.surrender(currentHand, deck);
                            isRoundOver = true;
                        }
                        else {
                            player.splitHand(currentHand, deck);
                            player.getStrategy().addCardToCount(deck.getShoe().peek(), deck.getShoe().size());
                            deck.dealCard(hand);
                        }
                    }
                    else if (hand.getCards().getFirst() == 9) {
                        if (dealerHand.getCards().getFirst() == 7 || dealerHand.getCards().getFirst() == 10) isRoundOver = true;
                        else if (dealerHand.getCards().getFirst() == 11) {
                            if (rules.getTotalDecks() == 1 && !rules.isS17() && rules.isDAS()) {
                                player.splitHand(currentHand, deck);
                                player.getStrategy().addCardToCount(deck.getShoe().peek(), deck.getShoe().size());
                                deck.dealCard(hand);
                            }
                            else isRoundOver = true;
                        }
                        else {
                            player.splitHand(currentHand, deck);
                            player.getStrategy().addCardToCount(deck.getShoe().peek(), deck.getShoe().size());
                            deck.dealCard(hand);
                        }
                    }
                    else if (hand.getCards().getFirst() == 10) isRoundOver = true;
                    else {
                        player.splitHand(currentHand, deck);
                        player.getStrategy().addCardToCount(deck.getShoe().peek(), deck.getShoe().size());
                        deck.dealCard(hand);
                        if (hand.getCards().get(1) != 11 || (hand.getCards().get(1) == 11 && !rules.isRSA())) isRoundOver = true;
                    }
                }
                else if (hand.getSoftCards() > 0) {
                    if (hand.getValue() <= 12) deck.dealCard(hand);
                    else if (hand.getValue() == 13) {
                        if ((dealerHand.getCards().getFirst() == 5 || dealerHand.getCards().getFirst() == 6) && rules.getDoubleLimit() == 0 && (rules.isDAS() || (!rules.isDAS() && player.getHands().size() == 1))) {
                            player.doubleDown(currentHand);
                            player.getStrategy().addCardToCount(deck.getShoe().peek(), deck.getShoe().size());
                            deck.dealCard(hand);
                            isRoundOver = true;
                        }
                        else if (dealerHand.getCards().getFirst() == 4 && rules.getTotalDecks() == 1 && rules.getDoubleLimit() == 0 && (rules.isDAS() || (!rules.isDAS() && player.getHands().size() == 1))) {
                            player.doubleDown(currentHand);
                            player.getStrategy().addCardToCount(deck.getShoe().peek(), deck.getShoe().size());
                            deck.dealCard(hand);
                            isRoundOver = true;
                        }
                        else {
                            player.getStrategy().addCardToCount(deck.getShoe().peek(), deck.getShoe().size());
                            deck.dealCard(hand);
                        }
                    }
                    else if (hand.getValue() == 14) {
                        if ((dealerHand.getCards().getFirst() == 5 || dealerHand.getCards().getFirst() == 6) && rules.getDoubleLimit() == 0 && (rules.isDAS() || (!rules.isDAS() && player.getHands().size() == 1))) {
                            player.doubleDown(currentHand);
                            player.getStrategy().addCardToCount(deck.getShoe().peek(), deck.getShoe().size());
                            deck.dealCard(hand);
                            isRoundOver = true;
                        }
                        else if (dealerHand.getCards().getFirst() == 4 && rules.getDoubleLimit() == 0 && (rules.isDAS() || (!rules.isDAS() && player.getHands().size() == 1))) {
                            if (rules.getTotalDecks() == 1 || (rules.getTotalDecks() == 2 && !rules.isS17())) {
                                player.doubleDown(currentHand);
                                player.getStrategy().addCardToCount(deck.getShoe().peek(), deck.getShoe().size());
                                deck.dealCard(hand);
                                isRoundOver = true;
                            }
                            else {
                                player.getStrategy().addCardToCount(deck.getShoe().peek(), deck.getShoe().size());
                                deck.dealCard(hand);
                            }
                        }
                        else {
                            player.getStrategy().addCardToCount(deck.getShoe().peek(), deck.getShoe().size());
                            deck.dealCard(hand);
                        }
                    }
                    else if (hand.getValue() == 15 || hand.getValue() == 16) {
                        if ((dealerHand.getCards().getFirst() >= 4 && dealerHand.getCards().getFirst() <= 6) && rules.getDoubleLimit() == 0 && (rules.isDAS() || (!rules.isDAS() && player.getHands().size() == 1))) {
                            player.doubleDown(currentHand);
                            player.getStrategy().addCardToCount(deck.getShoe().peek(), deck.getShoe().size());
                            deck.dealCard(hand);
                            isRoundOver = true;
                        }
                        else {
                            player.getStrategy().addCardToCount(deck.getShoe().peek(), deck.getShoe().size());
                            deck.dealCard(hand);
                        }
                    }
                    else if (hand.getValue() == 17) {
                        if ((dealerHand.getCards().getFirst() >= 3 && dealerHand.getCards().getFirst() <= 6) && rules.getDoubleLimit() == 0 && (rules.isDAS() || (!rules.isDAS() && player.getHands().size() == 1))) {
                            player.doubleDown(currentHand);
                            player.getStrategy().addCardToCount(deck.getShoe().peek(), deck.getShoe().size());
                            deck.dealCard(hand);
                            isRoundOver = true;
                        }
                        else if (dealerHand.getCards().getFirst() == 2 && rules.getTotalDecks() == 1 && rules.getDoubleLimit() == 0 && (rules.isDAS() || (!rules.isDAS() && player.getHands().size() == 1))) {
                            player.doubleDown(currentHand);
                            player.getStrategy().addCardToCount(deck.getShoe().peek(), deck.getShoe().size());
                            deck.dealCard(hand);
                            isRoundOver = true;
                        }
                        else {
                            player.getStrategy().addCardToCount(deck.getShoe().peek(), deck.getShoe().size());
                            deck.dealCard(hand);
                        }
                    }
                    else if (hand.getValue() == 18) {
                        if (dealerHand.getCards().getFirst() == 9 || dealerHand.getCards().getFirst() == 10) {
                            player.getStrategy().addCardToCount(deck.getShoe().peek(), deck.getShoe().size());
                            deck.dealCard(hand);
                        }
                        else if (dealerHand.getCards().getFirst() == 11) {
                            if (rules.getTotalDecks() == 1 && rules.isS17()) isRoundOver = true;
                            else {
                                player.getStrategy().addCardToCount(deck.getShoe().peek(), deck.getShoe().size());
                                deck.dealCard(hand);
                            }
                        }
                        else if (dealerHand.getCards().getFirst() >= 3 && dealerHand.getCards().getFirst() <= 6 && rules.getDoubleLimit() == 0 && (rules.isDAS() || (!rules.isDAS() && player.getHands().size() == 1))) {
                            player.doubleDown(currentHand);
                            player.getStrategy().addCardToCount(deck.getShoe().peek(), deck.getShoe().size());
                            deck.dealCard(hand);
                            isRoundOver = true;
                        }
                        else if (dealerHand.getCards().getFirst() == 2 && rules.getDoubleLimit() == 0 && (rules.isDAS() || (!rules.isDAS() && player.getHands().size() == 1))) {
                            if (rules.getTotalDecks() >= 2 && !rules.isS17()) {
                                player.doubleDown(currentHand);
                                player.getStrategy().addCardToCount(deck.getShoe().peek(), deck.getShoe().size());
                                deck.dealCard(hand);
                            }
                            isRoundOver = true;
                        }
                        else isRoundOver = true;
                    }
                    else if (hand.getValue() == 19 && (rules.getTotalDecks() == 1 || !rules.isS17()) && rules.getDoubleLimit() == 0 && (rules.isDAS() || (!rules.isDAS() && player.getHands().size() == 1))) {
                        player.doubleDown(currentHand);
                        player.getStrategy().addCardToCount(deck.getShoe().peek(), deck.getShoe().size());
                        deck.dealCard(hand);
                        isRoundOver = true;
                    }
                    else isRoundOver = true;
                }
                else {
                    if (hand.getValue() <= 7) deck.dealCard(hand);
                    else if (hand.getValue() == 8) {
                        if (rules.getTotalDecks() == 1 && rules.getDoubleLimit() <= 8 && (dealerHand.getCards().getFirst() == 5 || dealerHand.getCards().getFirst() == 6) && (rules.isDAS() || (!rules.isDAS() && player.getHands().size() == 1))) {
                            player.doubleDown(currentHand);
                            player.getStrategy().addCardToCount(deck.getShoe().peek(), deck.getShoe().size());
                            deck.dealCard(hand);
                            isRoundOver = true;
                        }
                        else {
                            player.getStrategy().addCardToCount(deck.getShoe().peek(), deck.getShoe().size());
                            deck.dealCard(hand);
                        }
                    }
                    else if (hand.getValue() == 9) {
                        if (rules.getDoubleLimit() <= 9 && (dealerHand.getCards().getFirst() >= 3 && dealerHand.getCards().getFirst() <= 6) && (rules.isDAS() || (!rules.isDAS() && player.getHands().size() == 1))) {
                            player.doubleDown(currentHand);
                            player.getStrategy().addCardToCount(deck.getShoe().peek(), deck.getShoe().size());
                            deck.dealCard(hand);
                            isRoundOver = true;
                        }
                        else if (rules.getTotalDecks() <= 2 && dealerHand.getCards().getFirst() == 2 && rules.getDoubleLimit() <= 9 && (rules.isDAS() || (!rules.isDAS() && player.getHands().size() == 1))) {
                            player.doubleDown(currentHand);
                            player.getStrategy().addCardToCount(deck.getShoe().peek(), deck.getShoe().size());
                            deck.dealCard(hand);
                            isRoundOver = true;
                        }
                        else {
                            player.getStrategy().addCardToCount(deck.getShoe().peek(), deck.getShoe().size());
                            deck.dealCard(hand);
                        }
                    }
                    else if (hand.getValue() == 10) {
                        if (dealerHand.getCards().getFirst() <= 9 && (rules.isDAS() || (!rules.isDAS() && player.getHands().size() == 1))) {
                            player.doubleDown(currentHand);
                            player.getStrategy().addCardToCount(deck.getShoe().peek(), deck.getShoe().size());
                            deck.dealCard(hand);
                            isRoundOver = true;
                        }
                        else {
                            player.getStrategy().addCardToCount(deck.getShoe().peek(), deck.getShoe().size());
                            deck.dealCard(hand);
                        }
                    }
                    else if (hand.getValue() == 11) {
                        if (dealerHand.getCards().getFirst() <= 10 && (rules.isDAS() || (!rules.isDAS() && player.getHands().size() == 1))) {
                            player.doubleDown(currentHand);
                            player.getStrategy().addCardToCount(deck.getShoe().peek(), deck.getShoe().size());
                            deck.dealCard(hand);
                            isRoundOver = true;
                        }
                        else if (dealerHand.getCards().getFirst() == 11 && (rules.getTotalDecks() <= 2 || !rules.isS17()) && (rules.isDAS() || (!rules.isDAS() && player.getHands().size() == 1))) {
                            player.doubleDown(currentHand);
                            player.getStrategy().addCardToCount(deck.getShoe().peek(), deck.getShoe().size());
                            deck.dealCard(hand);
                            isRoundOver = true;
                        }
                        else {
                            player.getStrategy().addCardToCount(deck.getShoe().peek(), deck.getShoe().size());
                            deck.dealCard(hand);
                        }
                    }
                    else if (hand.getValue() == 12) {
                        if (dealerHand.getCards().getFirst() >= 4 && dealerHand.getCards().getFirst() <= 6) isRoundOver = true;
                        else {
                            player.getStrategy().addCardToCount(deck.getShoe().peek(), deck.getShoe().size());
                            deck.dealCard(hand);
                        }
                    }
                    else if (hand.getValue() == 13 || hand.getValue() == 14) {
                        if (dealerHand.getCards().getFirst() <= 6) isRoundOver = true;
                        else {
                            player.getStrategy().addCardToCount(deck.getShoe().peek(), deck.getShoe().size());
                            deck.dealCard(hand);
                        }
                    }
                    else if (hand.getValue() == 15) {
                        if (dealerHand.getCards().getFirst() <= 6) isRoundOver = true;
                        else if (dealerHand.getCards().getFirst() == 10 && rules.isSurrender() && player.getHands().size() == 1 && rules.getTotalDecks() > 1) {
                            player.surrender(currentHand, deck);
                            isRoundOver = true;
                        }
                        else if (dealerHand.getCards().getFirst() == 11 && rules.isSurrender() && player.getHands().size() == 1 && !rules.isS17()) {
                            player.surrender(currentHand, deck);
                            isRoundOver = true;
                        }
                        else {
                            player.getStrategy().addCardToCount(deck.getShoe().peek(), deck.getShoe().size());
                            deck.dealCard(hand);
                        }
                    }
                    else if (hand.getValue() == 16) {
                        if (dealerHand.getCards().getFirst() <= 6) isRoundOver = true;
                        else if ((dealerHand.getCards().getFirst() == 10 || dealerHand.getCards().getFirst() == 11) && rules.isSurrender() && player.getHands().size() == 1 && rules.getTotalDecks() > 1) {
                            player.surrender(currentHand, deck);
                            isRoundOver = true;
                        }
                        else if (dealerHand.getCards().getFirst() == 9 && rules.isSurrender() && player.getHands().size() == 1 && rules.getTotalDecks() >= 4) {
                            player.surrender(currentHand, deck);
                            isRoundOver = true;
                        }
                        else {
                            player.getStrategy().addCardToCount(deck.getShoe().peek(), deck.getShoe().size());
                            deck.dealCard(hand);
                        }
                    }
                    else if (hand.getValue() == 17) {
                        if (dealerHand.getCards().getFirst() == 11 && !rules.isS17() && rules.isSurrender() && player.getHands().size() == 1) player.surrender(currentHand, deck);
                        isRoundOver = true;
                    }
                    else isRoundOver = true;
                }
            }
            else {
                if (hand.getSoftCards() > 0) {
                    if (hand.getValue() <= 17) deck.dealCard(hand);
                    else if (hand.getValue() == 18) {
                        if (dealerHand.getCards().getFirst() == 9 || dealerHand.getCards().getFirst() == 10) deck.dealCard(hand);
                        else if (dealerHand.getCards().getFirst() == 11) {
                            if (rules.getTotalDecks() == 1 && rules.isS17()) isRoundOver = true;
                            else deck.dealCard(hand);
                        }
                        else isRoundOver = true;
                    }
                    else isRoundOver = true;
                }
                else {
                    if (hand.getValue() <= 11) {
                        player.getStrategy().addCardToCount(deck.getShoe().peek(), deck.getShoe().size());
                        deck.dealCard(hand);
                    }
                    else if (hand.getValue() == 12) {
                        if (dealerHand.getCards().getFirst() >= 4 && dealerHand.getCards().getFirst() <= 6) isRoundOver = true;
                        else {
                            player.getStrategy().addCardToCount(deck.getShoe().peek(), deck.getShoe().size());
                            deck.dealCard(hand);
                        }
                    }
                    else if (hand.getValue() >= 13 && hand.getValue() <= 16) {
                        if (dealerHand.getCards().getFirst() <= 6) isRoundOver = true;
                        else {
                            player.getStrategy().addCardToCount(deck.getShoe().peek(), deck.getShoe().size());
                            deck.dealCard(hand);
                        }
                    }
                    else isRoundOver = true;
                }
            }
        }
    }
}
