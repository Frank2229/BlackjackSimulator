import java.util.Objects;

public class BasicStrategy extends Strategy{
    public BasicStrategy() {
        setTakesInsurance(false);
        setStrategyName("BASIC STRATEGY");
        for (int i = 0; i < 10; i++) setCountValue(i, 0);
    }

    public int calculateWager(int tableMin, int tableMax) {
        return tableMin;
    }

    public void playHand(Hand hand, Deck deck, Hand dealerHand, Player player, int currentHand, Rules rules) {
        boolean isRoundOver = false;

        while (!isRoundOver) {
            if (hand.getCards().size() == 2) {
                if (Objects.equals(hand.getCards().getFirst(), hand.getCards().get(1))) {
                    if (hand.getCards().getFirst() == 2) {
                        if (dealerHand.getCards().getFirst() >= 4 && dealerHand.getCards().getFirst() <= 7) {
                            player.splitHand(currentHand, deck);
                            player.getStrategy().addCardToCount(deck.getShoe().peek(), deck.getShoe().size());
                            hand.addCard(deck.dealCard());
                        }
                        else if (dealerHand.getCards().getFirst() == 2) {
                            if (rules.getTotalDecks() == 1 || rules.isDAS()) {
                                player.splitHand(currentHand, deck);
                                player.getStrategy().addCardToCount(deck.getShoe().peek(), deck.getShoe().size());
                                hand.addCard(deck.dealCard());
                            }
                            else {
                                player.getStrategy().addCardToCount(deck.getShoe().peek(), deck.getShoe().size());
                                hand.addCard(deck.dealCard());
                            }
                        }
                        else if (dealerHand.getCards().getFirst() == 3) {
                            if (rules.isDAS()) {
                                player.splitHand(currentHand, deck);
                                player.getStrategy().addCardToCount(deck.getShoe().peek(), deck.getShoe().size());
                                hand.addCard(deck.dealCard());
                            }
                            else {
                                player.getStrategy().addCardToCount(deck.getShoe().peek(), deck.getShoe().size());
                                hand.addCard(deck.dealCard());
                            }
                        }
                        else {
                            player.getStrategy().addCardToCount(deck.getShoe().peek(), deck.getShoe().size());
                            hand.addCard(deck.dealCard());
                        }
                    }
                    else if (hand.getCards().getFirst() == 3) {
                        if (dealerHand.getCards().getFirst() >= 4 && dealerHand.getCards().getFirst() <= 7) {
                            player.splitHand(currentHand, deck);
                            player.getStrategy().addCardToCount(deck.getShoe().peek(), deck.getShoe().size());
                            hand.addCard(deck.dealCard());
                        }
                        else if ((dealerHand.getCards().getFirst() == 2 || dealerHand.getCards().getFirst() == 3) && rules.isDAS()) {
                            player.splitHand(currentHand, deck);
                            player.getStrategy().addCardToCount(deck.getShoe().peek(), deck.getShoe().size());
                            hand.addCard(deck.dealCard());
                        }
                        else if (dealerHand.getCards().getFirst() == 8 && rules.getTotalDecks() == 1 && rules.isDAS()) {
                            player.splitHand(currentHand, deck);
                            player.getStrategy().addCardToCount(deck.getShoe().peek(), deck.getShoe().size());
                            hand.addCard(deck.dealCard());
                        }
                        else {
                            player.getStrategy().addCardToCount(deck.getShoe().peek(), deck.getShoe().size());
                            hand.addCard(deck.dealCard());
                        }
                    }
                    else if (hand.getCards().getFirst() == 4) {
                        if (dealerHand.getCards().getFirst() == 5 || dealerHand.getCards().getFirst() == 6) {
                            if (rules.getTotalDecks() == 1) {
                                if (rules.isDAS()) {
                                    player.splitHand(currentHand, deck);
                                    player.getStrategy().addCardToCount(deck.getShoe().peek(), deck.getShoe().size());
                                    hand.addCard(deck.dealCard());
                                }
                                else if (rules.getDoubleLimit() <= 8) {
                                    player.doubleDown(currentHand);
                                    player.getStrategy().addCardToCount(deck.getShoe().peek(), deck.getShoe().size());
                                    hand.addCard(deck.dealCard());
                                    isRoundOver = true;
                                }
                                else {
                                    player.getStrategy().addCardToCount(deck.getShoe().peek(), deck.getShoe().size());
                                    hand.addCard(deck.dealCard());
                                }
                            }
                            else {
                                if (rules.isDAS()) {
                                    player.splitHand(currentHand, deck);
                                    player.getStrategy().addCardToCount(deck.getShoe().peek(), deck.getShoe().size());
                                    hand.addCard(deck.dealCard());
                                }
                                else hand.addCard(deck.dealCard());
                            }
                        }
                        else if (dealerHand.getCards().getFirst() == 4 && rules.isDAS() && rules.getTotalDecks() == 1) {
                            player.splitHand(currentHand, deck);
                            player.getStrategy().addCardToCount(deck.getShoe().peek(), deck.getShoe().size());
                            hand.addCard(deck.dealCard());
                        }
                        else {
                            player.getStrategy().addCardToCount(deck.getShoe().peek(), deck.getShoe().size());
                            hand.addCard(deck.dealCard());
                        }
                    }
                    else if (hand.getCards().getFirst() == 5) {
                        if (dealerHand.getCards().getFirst() <= 9) {
                            player.doubleDown(currentHand);
                            player.getStrategy().addCardToCount(deck.getShoe().peek(), deck.getShoe().size());
                            hand.addCard(deck.dealCard());
                            isRoundOver = true;
                        }
                        else {
                            player.getStrategy().addCardToCount(deck.getShoe().peek(), deck.getShoe().size());
                            hand.addCard(deck.dealCard());
                        }
                    }
                    else if (hand.getCards().getFirst() == 6) {
                        if (dealerHand.getCards().getFirst() >= 3 && dealerHand.getCards().getFirst() <= 6) {
                            player.splitHand(currentHand, deck);
                            player.getStrategy().addCardToCount(deck.getShoe().peek(), deck.getShoe().size());
                            hand.addCard(deck.dealCard());
                        }
                        else if (dealerHand.getCards().getFirst() == 2) {
                            if (rules.getTotalDecks() <= 2 || rules.isDAS()) {
                                player.splitHand(currentHand, deck);
                                player.getStrategy().addCardToCount(deck.getShoe().peek(), deck.getShoe().size());
                                hand.addCard(deck.dealCard());
                            }
                            else {
                                player.getStrategy().addCardToCount(deck.getShoe().peek(), deck.getShoe().size());
                                hand.addCard(deck.dealCard());
                            }
                        }
                        else if (dealerHand.getCards().getFirst() == 7) {
                            if (rules.getTotalDecks() <= 2 && rules.isDAS()) {
                                player.splitHand(currentHand, deck);
                                player.getStrategy().addCardToCount(deck.getShoe().peek(), deck.getShoe().size());
                                hand.addCard(deck.dealCard());
                            }
                            else {
                                player.getStrategy().addCardToCount(deck.getShoe().peek(), deck.getShoe().size());
                                hand.addCard(deck.dealCard());
                            }
                        }
                        else {
                            player.getStrategy().addCardToCount(deck.getShoe().peek(), deck.getShoe().size());
                            hand.addCard(deck.dealCard());
                        }
                    }
                    else if (hand.getCards().getFirst() == 7) {
                        if (dealerHand.getCards().getFirst() <= 7) {
                            player.splitHand(currentHand, deck);
                            player.getStrategy().addCardToCount(deck.getShoe().peek(), deck.getShoe().size());
                            hand.addCard(deck.dealCard());
                        }
                        else if (dealerHand.getCards().getFirst() == 8) {
                            if (rules.getTotalDecks() <= 2 && rules.isDAS()) {
                                player.splitHand(currentHand, deck);
                                player.getStrategy().addCardToCount(deck.getShoe().peek(), deck.getShoe().size());
                                hand.addCard(deck.dealCard());
                            }
                            else {
                                player.getStrategy().addCardToCount(deck.getShoe().peek(), deck.getShoe().size());
                                hand.addCard(deck.dealCard());
                            }
                        }
                        else if (dealerHand.getCards().getFirst() == 10) {
                            if (rules.getTotalDecks() == 1) {
                                if (rules.isSurrender() && player.getHands().size() == 1) player.surrender(currentHand, deck);
                                isRoundOver = true;
                            }
                            else {
                                player.getStrategy().addCardToCount(deck.getShoe().peek(), deck.getShoe().size());
                                hand.addCard(deck.dealCard());
                            }
                        }
                        else if (dealerHand.getCards().getFirst() == 11) {
                            if (rules.getTotalDecks() == 1) {
                                if (rules.isSurrender() && player.getHands().size() == 1) player.surrender(currentHand, deck);
                                isRoundOver = true;
                            }
                            else {
                                player.getStrategy().addCardToCount(deck.getShoe().peek(), deck.getShoe().size());
                                hand.addCard(deck.dealCard());
                            }
                        }
                        else {
                            player.getStrategy().addCardToCount(deck.getShoe().peek(), deck.getShoe().size());
                            hand.addCard(deck.dealCard());
                        }
                    }
                    else if (hand.getCards().getFirst() == 8) {
                        if (rules.getTotalDecks() > 1 && rules.isSurrender() && player.getHands().size() == 1 && dealerHand.getCards().getFirst() == 11) {
                            player.surrender(currentHand, deck);
                            isRoundOver = true;
                        }
                        else {
                            player.splitHand(currentHand, deck);
                            player.getStrategy().addCardToCount(deck.getShoe().peek(), deck.getShoe().size());
                            hand.addCard(deck.dealCard());
                        }
                    }
                    else if (hand.getCards().getFirst() == 9) {
                        if (dealerHand.getCards().getFirst() == 7 || dealerHand.getCards().getFirst() == 10) isRoundOver = true;
                        else if (dealerHand.getCards().getFirst() == 11) {
                            if (rules.getTotalDecks() == 1 && !rules.isS17() && rules.isDAS()) {
                                player.splitHand(currentHand, deck);
                                player.getStrategy().addCardToCount(deck.getShoe().peek(), deck.getShoe().size());
                                hand.addCard(deck.dealCard());
                            }
                            else isRoundOver = true;
                        }
                        else {
                            player.splitHand(currentHand, deck);
                            player.getStrategy().addCardToCount(deck.getShoe().peek(), deck.getShoe().size());
                            hand.addCard(deck.dealCard());
                        }
                    }
                    else if (hand.getCards().getFirst() == 10) isRoundOver = true;
                    else {
                        player.splitHand(currentHand, deck);
                        player.getStrategy().addCardToCount(deck.getShoe().peek(), deck.getShoe().size());
                        hand.addCard(deck.dealCard());
                        if (hand.getCards().get(1) != 11 || (hand.getCards().get(1) == 11 && !rules.isRSA())) isRoundOver = true;
                    }
                }
                else if (hand.getSoftCards() > 0) {
                    if (hand.getValue() <= 12) hand.addCard(deck.dealCard());
                    else if (hand.getValue() == 13) {
                        if ((dealerHand.getCards().getFirst() == 5 || dealerHand.getCards().getFirst() == 6) && rules.getDoubleLimit() == 0 && (rules.isDAS() || player.getHands().size() == 1)) {
                            player.doubleDown(currentHand);
                            player.getStrategy().addCardToCount(deck.getShoe().peek(), deck.getShoe().size());
                            hand.addCard(deck.dealCard());
                            isRoundOver = true;
                        }
                        else if (dealerHand.getCards().getFirst() == 4 && rules.getTotalDecks() == 1 && rules.getDoubleLimit() == 0 && (rules.isDAS() || player.getHands().size() == 1)) {
                            player.doubleDown(currentHand);
                            player.getStrategy().addCardToCount(deck.getShoe().peek(), deck.getShoe().size());
                            hand.addCard(deck.dealCard());
                            isRoundOver = true;
                        }
                        else {
                            player.getStrategy().addCardToCount(deck.getShoe().peek(), deck.getShoe().size());
                            hand.addCard(deck.dealCard());
                        }
                    }
                    else if (hand.getValue() == 14) {
                        if ((dealerHand.getCards().getFirst() == 5 || dealerHand.getCards().getFirst() == 6) && rules.getDoubleLimit() == 0 && (rules.isDAS() || player.getHands().size() == 1)) {
                            player.doubleDown(currentHand);
                            player.getStrategy().addCardToCount(deck.getShoe().peek(), deck.getShoe().size());
                            hand.addCard(deck.dealCard());
                            isRoundOver = true;
                        }
                        else if (dealerHand.getCards().getFirst() == 4 && rules.getDoubleLimit() == 0 && (rules.isDAS() || player.getHands().size() == 1)) {
                            if (rules.getTotalDecks() == 1 || (rules.getTotalDecks() == 2 && !rules.isS17())) {
                                player.doubleDown(currentHand);
                                player.getStrategy().addCardToCount(deck.getShoe().peek(), deck.getShoe().size());
                                hand.addCard(deck.dealCard());
                                isRoundOver = true;
                            }
                            else {
                                player.getStrategy().addCardToCount(deck.getShoe().peek(), deck.getShoe().size());
                                hand.addCard(deck.dealCard());
                            }
                        }
                        else {
                            player.getStrategy().addCardToCount(deck.getShoe().peek(), deck.getShoe().size());
                            hand.addCard(deck.dealCard());
                        }
                    }
                    else if (hand.getValue() == 15 || hand.getValue() == 16) {
                        if ((dealerHand.getCards().getFirst() >= 4 && dealerHand.getCards().getFirst() <= 6) && rules.getDoubleLimit() == 0 && (rules.isDAS() || player.getHands().size() == 1)) {
                            player.doubleDown(currentHand);
                            player.getStrategy().addCardToCount(deck.getShoe().peek(), deck.getShoe().size());
                            hand.addCard(deck.dealCard());
                            isRoundOver = true;
                        }
                        else {
                            player.getStrategy().addCardToCount(deck.getShoe().peek(), deck.getShoe().size());
                            hand.addCard(deck.dealCard());
                        }
                    }
                    else if (hand.getValue() == 17) {
                        if ((dealerHand.getCards().getFirst() >= 3 && dealerHand.getCards().getFirst() <= 6) && rules.getDoubleLimit() == 0 && (rules.isDAS() || player.getHands().size() == 1)) {
                            player.doubleDown(currentHand);
                            player.getStrategy().addCardToCount(deck.getShoe().peek(), deck.getShoe().size());
                            hand.addCard(deck.dealCard());
                            isRoundOver = true;
                        }
                        else if (dealerHand.getCards().getFirst() == 2 && rules.getTotalDecks() == 1 && rules.getDoubleLimit() == 0 && (rules.isDAS() || player.getHands().size() == 1)) {
                            player.doubleDown(currentHand);
                            player.getStrategy().addCardToCount(deck.getShoe().peek(), deck.getShoe().size());
                            hand.addCard(deck.dealCard());
                            isRoundOver = true;
                        }
                        else {
                            player.getStrategy().addCardToCount(deck.getShoe().peek(), deck.getShoe().size());
                            hand.addCard(deck.dealCard());
                        }
                    }
                    else if (hand.getValue() == 18) {
                        if (dealerHand.getCards().getFirst() == 9 || dealerHand.getCards().getFirst() == 10) {
                            player.getStrategy().addCardToCount(deck.getShoe().peek(), deck.getShoe().size());
                            hand.addCard(deck.dealCard());
                        }
                        else if (dealerHand.getCards().getFirst() == 11) {
                            if (rules.getTotalDecks() == 1 && rules.isS17()) isRoundOver = true;
                            else {
                                player.getStrategy().addCardToCount(deck.getShoe().peek(), deck.getShoe().size());
                                hand.addCard(deck.dealCard());
                            }
                        }
                        else if (dealerHand.getCards().getFirst() >= 3 && dealerHand.getCards().getFirst() <= 6 && rules.getDoubleLimit() == 0 && (rules.isDAS() || player.getHands().size() == 1)) {
                            player.doubleDown(currentHand);
                            player.getStrategy().addCardToCount(deck.getShoe().peek(), deck.getShoe().size());
                            hand.addCard(deck.dealCard());
                            isRoundOver = true;
                        }
                        else if (dealerHand.getCards().getFirst() == 2 && rules.getDoubleLimit() == 0 && (rules.isDAS() || player.getHands().size() == 1)) {
                            if (rules.getTotalDecks() >= 2 && !rules.isS17() && (rules.isDAS() || player.getHands().size() == 1)) {
                                player.doubleDown(currentHand);
                                player.getStrategy().addCardToCount(deck.getShoe().peek(), deck.getShoe().size());
                                hand.addCard(deck.dealCard());
                            }
                            isRoundOver = true;
                        }
                        else isRoundOver = true;
                    }
                    else if (hand.getValue() == 19 && (rules.getTotalDecks() == 1 || !rules.isS17()) && rules.getDoubleLimit() == 0 && (rules.isDAS() || player.getHands().size() == 1)) {
                        player.doubleDown(currentHand);
                        player.getStrategy().addCardToCount(deck.getShoe().peek(), deck.getShoe().size());
                        hand.addCard(deck.dealCard());
                        isRoundOver = true;
                    }
                    else isRoundOver = true;
                }
                else {
                    if (hand.getValue() <= 7) hand.addCard(deck.dealCard());
                    else if (hand.getValue() == 8) {
                        if (rules.getTotalDecks() == 1 && rules.getDoubleLimit() <= 8 && (dealerHand.getCards().getFirst() == 5 || dealerHand.getCards().getFirst() == 6) && (rules.isDAS() || player.getHands().size() == 1)) {
                            player.doubleDown(currentHand);
                            player.getStrategy().addCardToCount(deck.getShoe().peek(), deck.getShoe().size());
                            hand.addCard(deck.dealCard());
                            isRoundOver = true;
                        }
                        else {
                            player.getStrategy().addCardToCount(deck.getShoe().peek(), deck.getShoe().size());
                            hand.addCard(deck.dealCard());
                        }
                    }
                    else if (hand.getValue() == 9) {
                        if (rules.getDoubleLimit() <= 9 && (dealerHand.getCards().getFirst() >= 3 && dealerHand.getCards().getFirst() <= 6) && (rules.isDAS() || player.getHands().size() == 1)) {
                            player.doubleDown(currentHand);
                            player.getStrategy().addCardToCount(deck.getShoe().peek(), deck.getShoe().size());
                            hand.addCard(deck.dealCard());
                            isRoundOver = true;
                        }
                        else if (rules.getTotalDecks() <= 2 && dealerHand.getCards().getFirst() == 2 && rules.getDoubleLimit() <= 9 && (rules.isDAS() || player.getHands().size() == 1)) {
                            player.doubleDown(currentHand);
                            player.getStrategy().addCardToCount(deck.getShoe().peek(), deck.getShoe().size());
                            hand.addCard(deck.dealCard());
                            isRoundOver = true;
                        }
                        else {
                            player.getStrategy().addCardToCount(deck.getShoe().peek(), deck.getShoe().size());
                            hand.addCard(deck.dealCard());
                        }
                    }
                    else if (hand.getValue() == 10) {
                        if (dealerHand.getCards().getFirst() <= 9 && (rules.isDAS() || player.getHands().size() == 1)) {
                            player.doubleDown(currentHand);
                            player.getStrategy().addCardToCount(deck.getShoe().peek(), deck.getShoe().size());
                            hand.addCard(deck.dealCard());
                            isRoundOver = true;
                        }
                        else {
                            player.getStrategy().addCardToCount(deck.getShoe().peek(), deck.getShoe().size());
                            hand.addCard(deck.dealCard());
                        }
                    }
                    else if (hand.getValue() == 11) {
                        if (dealerHand.getCards().getFirst() <= 10 && (rules.isDAS() || player.getHands().size() == 1)) {
                            player.doubleDown(currentHand);
                            player.getStrategy().addCardToCount(deck.getShoe().peek(), deck.getShoe().size());
                            hand.addCard(deck.dealCard());
                            isRoundOver = true;
                        }
                        else if (dealerHand.getCards().getFirst() == 11 && (rules.getTotalDecks() <= 2 || !rules.isS17()) && (rules.isDAS() || player.getHands().size() == 1)) {
                            player.doubleDown(currentHand);
                            player.getStrategy().addCardToCount(deck.getShoe().peek(), deck.getShoe().size());
                            hand.addCard(deck.dealCard());
                            isRoundOver = true;
                        }
                        else {
                            player.getStrategy().addCardToCount(deck.getShoe().peek(), deck.getShoe().size());
                            hand.addCard(deck.dealCard());
                        }
                    }
                    else if (hand.getValue() == 12) {
                        if (dealerHand.getCards().getFirst() >= 4 && dealerHand.getCards().getFirst() <= 6) isRoundOver = true;
                        else {
                            player.getStrategy().addCardToCount(deck.getShoe().peek(), deck.getShoe().size());
                            hand.addCard(deck.dealCard());
                        }
                    }
                    else if (hand.getValue() == 13 || hand.getValue() == 14) {
                        if (dealerHand.getCards().getFirst() <= 6) isRoundOver = true;
                        else {
                            player.getStrategy().addCardToCount(deck.getShoe().peek(), deck.getShoe().size());
                            hand.addCard(deck.dealCard());
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
                            hand.addCard(deck.dealCard());
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
                            hand.addCard(deck.dealCard());
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
                    if (hand.getValue() <= 17) hand.addCard(deck.dealCard());
                    else if (hand.getValue() == 18) {
                        if (dealerHand.getCards().getFirst() == 9 || dealerHand.getCards().getFirst() == 10) hand.addCard(deck.dealCard());
                        else if (dealerHand.getCards().getFirst() == 11) {
                            if (rules.getTotalDecks() == 1 && rules.isS17()) isRoundOver = true;
                            else hand.addCard(deck.dealCard());
                        }
                        else isRoundOver = true;
                    }
                    else isRoundOver = true;
                }
                else {
                    if (hand.getValue() <= 11) {
                        player.getStrategy().addCardToCount(deck.getShoe().peek(), deck.getShoe().size());
                        hand.addCard(deck.dealCard());
                    }
                    else if (hand.getValue() == 12) {
                        if (dealerHand.getCards().getFirst() >= 4 && dealerHand.getCards().getFirst() <= 6) isRoundOver = true;
                        else {
                            player.getStrategy().addCardToCount(deck.getShoe().peek(), deck.getShoe().size());
                            hand.addCard(deck.dealCard());
                        }
                    }
                    else if (hand.getValue() >= 13 && hand.getValue() <= 16) {
                        if (dealerHand.getCards().getFirst() <= 6) isRoundOver = true;
                        else {
                            player.getStrategy().addCardToCount(deck.getShoe().peek(), deck.getShoe().size());
                            hand.addCard(deck.dealCard());
                        }
                    }
                    else isRoundOver = true;
                }
            }
        }
    }
}