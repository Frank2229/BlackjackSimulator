public class BasicStrategy extends Strategy{
    public BasicStrategy() {
        setTakesInsurance(false);
        setStrategyName("BASIC STRATEGY");
    }

    public int calculateWager(int tableMin) {
        return tableMin;
    }

    public void playHand(Hand hand, boolean isS17, Deck deck, Hand dealerHand, boolean isDAS, int totalDecks, Player player, int currentHand, int doubleLimit, boolean isSurrender) {
        boolean isRoundOver = false;

        while (!isRoundOver) {
            if (hand.getCards().size() == 2) {
                if (hand.getCards().getFirst() == hand.getCards().get(1)) {
                    if (hand.getCards().getFirst() == 2) {
                        if (dealerHand.getCards().getFirst() >= 4 && dealerHand.getCards().getFirst() <= 7) {
                            player.splitHand(currentHand, deck);
                            hand.addCard(deck.dealCard());
                        }
                        else if (dealerHand.getCards().getFirst() == 2) {
                            if (totalDecks == 1 || isDAS) {
                                player.splitHand(currentHand, deck);
                                hand.addCard(deck.dealCard());
                            }
                            else hand.addCard(deck.dealCard());
                        }
                        else if (dealerHand.getCards().getFirst() == 3) {
                            if (isDAS) {
                                player.splitHand(currentHand, deck);
                                hand.addCard(deck.dealCard());
                            }
                            else hand.addCard(deck.dealCard());
                        }
                        else hand.addCard(deck.dealCard());
                    }
                    else if (hand.getCards().getFirst() == 3) {
                        if (dealerHand.getCards().getFirst() >= 4 && dealerHand.getCards().getFirst() <= 7) {
                            player.splitHand(currentHand, deck);
                            hand.addCard(deck.dealCard());
                        }
                        else if ((dealerHand.getCards().getFirst() == 2 || dealerHand.getCards().getFirst() == 3) && isDAS) {
                            player.splitHand(currentHand, deck);
                            hand.addCard(deck.dealCard());
                        }
                        else if (dealerHand.getCards().getFirst() == 8 && totalDecks == 1 && isDAS) {
                            player.splitHand(currentHand, deck);
                            hand.addCard(deck.dealCard());
                        }
                        else hand.addCard(deck.dealCard());
                    }
                    else if (hand.getCards().getFirst() == 4) {
                        if (dealerHand.getCards().getFirst() == 5 || dealerHand.getCards().getFirst() == 6) {
                            if (totalDecks == 1) {
                                if (isDAS) {
                                    player.splitHand(currentHand, deck);
                                    hand.addCard(deck.dealCard());
                                }
                                else {
                                    player.doubleDown(currentHand);
                                    hand.addCard(deck.dealCard());
                                    isRoundOver = true;
                                }
                            }
                            else {
                                if (isDAS) {
                                    player.splitHand(currentHand, deck);
                                    hand.addCard(deck.dealCard());
                                }
                                else hand.addCard(deck.dealCard());
                            }
                        }
                        else if (dealerHand.getCards().getFirst() == 4 && isDAS && totalDecks == 1) {
                            player.splitHand(currentHand, deck);
                            hand.addCard(deck.dealCard());
                        }
                        else hand.addCard(deck.dealCard());
                    }
                    else if (hand.getCards().getFirst() == 5) {
                        if (dealerHand.getCards().getFirst() <= 9) {
                            player.doubleDown(currentHand);
                            hand.addCard(deck.dealCard());
                            isRoundOver = true;
                        }
                        else hand.addCard(deck.dealCard());
                    }
                    else if (hand.getCards().getFirst() == 6) {
                        if (dealerHand.getCards().getFirst() >= 3 && dealerHand.getCards().getFirst() <= 6) {
                            player.splitHand(currentHand, deck);
                            hand.addCard(deck.dealCard());
                        }
                        else if (dealerHand.getCards().getFirst() == 2) {
                            if (totalDecks <= 2 || isDAS) {
                                player.splitHand(currentHand, deck);
                                hand.addCard(deck.dealCard());
                            }
                            else hand.addCard(deck.dealCard());
                        }
                        else if (dealerHand.getCards().getFirst() == 7) {
                            if (totalDecks <= 2 && isDAS) {
                                player.splitHand(currentHand, deck);
                                hand.addCard(deck.dealCard());
                            }
                            else hand.addCard(deck.dealCard());
                        }
                        else hand.addCard(deck.dealCard());
                    }
                    else if (hand.getCards().getFirst() == 7) {
                        if (dealerHand.getCards().getFirst() <= 7) {
                            player.splitHand(currentHand, deck);
                            hand.addCard(deck.dealCard());
                        }
                        else if (dealerHand.getCards().getFirst() == 8) {
                            if (totalDecks <= 2 && isDAS) {
                                player.splitHand(currentHand, deck);
                                hand.addCard(deck.dealCard());
                            }
                            else hand.addCard(deck.dealCard());
                        }
                        else if (dealerHand.getCards().getFirst() == 10) {
                            if (totalDecks == 1) {
                                if (isSurrender && player.getHands().size() == 1) player.surrender(currentHand, deck);
                                isRoundOver = true;
                            }
                            else hand.addCard(deck.dealCard());
                        }
                        else if (dealerHand.getCards().getFirst() == 11) {
                            if (totalDecks == 1) {
                                if (isSurrender && player.getHands().size() == 1) player.surrender(currentHand, deck);
                                isRoundOver = true;
                            }
                            else hand.addCard(deck.dealCard());
                        }
                        else hand.addCard(deck.dealCard());
                    }
                    else if (hand.getCards().getFirst() == 8) {
                        if (totalDecks > 1 && isSurrender && player.getHands().size() == 1 && dealerHand.getCards().getFirst() == 11) {
                            player.surrender(currentHand, deck);
                            isRoundOver = true;
                        }
                        else {
                            player.splitHand(currentHand, deck);
                            hand.addCard(deck.dealCard());
                        }
                    }
                    else if (hand.getCards().getFirst() == 9) {
                        if (dealerHand.getCards().getFirst() == 7 || dealerHand.getCards().getFirst() == 10) isRoundOver = true;
                        else if (dealerHand.getCards().getFirst() == 11) {
                            if (totalDecks == 1 && !isS17 && isDAS) {
                                player.splitHand(currentHand, deck);
                                hand.addCard(deck.dealCard());
                            }
                            else isRoundOver = true;
                        }
                        else {
                            player.splitHand(currentHand, deck);
                            hand.addCard(deck.dealCard());
                        }
                    }
                    else if (hand.getCards().getFirst() == 10) isRoundOver = true;
                    else {
                        player.splitHand(currentHand, deck);
                        hand.addCard(deck.dealCard());
                    }
                }
                else if (hand.getSoftCards() > 0) {
                    if (hand.getValue() <= 12) hand.addCard(deck.dealCard());
                    else if (hand.getValue() == 13) {
                        if ((dealerHand.getCards().getFirst() == 5 || dealerHand.getCards().getFirst() == 6) && doubleLimit == 0) {
                            player.doubleDown(currentHand);
                            hand.addCard(deck.dealCard());
                            isRoundOver = true;
                        }
                        else if (dealerHand.getCards().getFirst() == 4 && totalDecks == 1 && doubleLimit == 0) {
                            player.doubleDown(currentHand);
                            hand.addCard(deck.dealCard());
                            isRoundOver = true;
                        }
                        else hand.addCard(deck.dealCard());
                    }
                    else if (hand.getValue() == 14) {
                        if ((dealerHand.getCards().getFirst() == 5 || dealerHand.getCards().getFirst() == 6) && doubleLimit == 0) {
                            player.doubleDown(currentHand);
                            hand.addCard(deck.dealCard());
                            isRoundOver = true;
                        }
                        else if (dealerHand.getCards().getFirst() == 4 && doubleLimit == 0) {
                            if (totalDecks == 1 || (totalDecks == 2 && !isS17)) {
                                player.doubleDown(currentHand);
                                hand.addCard(deck.dealCard());
                                isRoundOver = true;
                            }
                            else hand.addCard(deck.dealCard());
                        }
                        else hand.addCard(deck.dealCard());
                    }
                    else if (hand.getValue() == 15 || hand.getValue() == 16) {
                        if ((dealerHand.getCards().getFirst() >= 4 && dealerHand.getCards().getFirst() <= 6) && doubleLimit == 0) {
                            player.doubleDown(currentHand);
                            hand.addCard(deck.dealCard());
                            isRoundOver = true;
                        }
                        else hand.addCard(deck.dealCard());
                    }
                    else if (hand.getValue() == 17) {
                        if ((dealerHand.getCards().getFirst() >= 3 && dealerHand.getCards().getFirst() <= 6) && doubleLimit == 0) {
                            player.doubleDown(currentHand);
                            hand.addCard(deck.dealCard());
                            isRoundOver = true;
                        }
                        else if (dealerHand.getCards().getFirst() == 2 && totalDecks == 1 && doubleLimit == 0) {
                            player.doubleDown(currentHand);
                            hand.addCard(deck.dealCard());
                            isRoundOver = true;
                        }
                        else hand.addCard(deck.dealCard());
                    }
                    else if (hand.getValue() == 18) {
                        if (dealerHand.getCards().getFirst() == 9 || dealerHand.getCards().getFirst() == 10) hand.addCard(deck.dealCard());
                        else if (dealerHand.getCards().getFirst() == 11) {
                            if (totalDecks == 1 && isS17) isRoundOver = true;
                            else hand.addCard(deck.dealCard());
                        }
                        else if (dealerHand.getCards().getFirst() >= 3 && dealerHand.getCards().getFirst() <= 6 && doubleLimit == 0) {
                            player.doubleDown(currentHand);
                            hand.addCard(deck.dealCard());
                            isRoundOver = true;
                        }
                        else if (dealerHand.getCards().getFirst() == 2 && doubleLimit == 0) {
                            if (totalDecks >= 2 && !isS17) {
                                player.doubleDown(currentHand);
                                hand.addCard(deck.dealCard());
                                isRoundOver = true;
                            }
                            else isRoundOver = true;
                        }
                        else isRoundOver = true;
                    }
                    else if (hand.getValue() == 19 && (totalDecks == 1 || !isS17)) {
                        player.doubleDown(currentHand);
                        hand.addCard(deck.dealCard());
                        isRoundOver = true;
                    }
                    else isRoundOver = true;
                }
                else {
                    if (hand.getValue() <= 7) hand.addCard(deck.dealCard());
                    else if (hand.getValue() == 8) {
                        if (totalDecks == 1 && doubleLimit <= 8 && (dealerHand.getCards().getFirst() == 5 || dealerHand.getCards().getFirst() == 6)) {
                            player.doubleDown(currentHand);
                            hand.addCard(deck.dealCard());
                            isRoundOver = true;
                        }
                        else hand.addCard(deck.dealCard());
                    }
                    else if (hand.getValue() == 9) {
                        if (doubleLimit <= 9 && (dealerHand.getCards().getFirst() >= 3 && dealerHand.getCards().getFirst() <= 6)) {
                            player.doubleDown(currentHand);
                            hand.addCard(deck.dealCard());
                            isRoundOver = true;
                        }
                        else if (totalDecks <= 2 && dealerHand.getCards().getFirst() == 2 && doubleLimit <= 9) {
                            player.doubleDown(currentHand);
                            hand.addCard(deck.dealCard());
                            isRoundOver = true;
                        }
                        else hand.addCard(deck.dealCard());
                    }
                    else if (hand.getValue() == 10) {
                        if (dealerHand.getCards().getFirst() <= 9) {
                            player.doubleDown(currentHand);
                            hand.addCard(deck.dealCard());
                            isRoundOver = true;
                        }
                        else hand.addCard(deck.dealCard());
                    }
                    else if (hand.getValue() == 11) {
                        if (dealerHand.getCards().getFirst() <= 10) {
                            player.doubleDown(currentHand);
                            hand.addCard(deck.dealCard());
                            isRoundOver = true;
                        }
                        else if (dealerHand.getCards().getFirst() == 11 && (totalDecks <= 2 || !isS17)) {
                            player.doubleDown(currentHand);
                            hand.addCard(deck.dealCard());
                            isRoundOver = true;
                        }
                        else hand.addCard(deck.dealCard());
                    }
                    else if (hand.getValue() == 12) {
                        if (dealerHand.getCards().getFirst() >= 4 && dealerHand.getCards().getFirst() <= 6) isRoundOver = true;
                        else hand.addCard(deck.dealCard());
                    }
                    else if (hand.getValue() == 13 || hand.getValue() == 14) {
                        if (dealerHand.getCards().getFirst() <= 6) isRoundOver = true;
                        else hand.addCard(deck.dealCard());
                    }
                    else if (hand.getValue() == 15) {
                        if (dealerHand.getCards().getFirst() <= 6) isRoundOver = true;
                        else if (dealerHand.getCards().getFirst() == 10 && isSurrender && player.getHands().size() == 1 && totalDecks > 1) {
                            player.surrender(currentHand, deck);
                            isRoundOver = true;
                        }
                        else if (dealerHand.getCards().getFirst() == 11 && isSurrender && player.getHands().size() == 1 && !isS17) {
                            player.surrender(currentHand, deck);
                            isRoundOver = true;
                        }
                        else hand.addCard(deck.dealCard());
                    }
                    else if (hand.getValue() == 16) {
                        if (dealerHand.getCards().getFirst() <= 6) isRoundOver = true;
                        else if ((dealerHand.getCards().getFirst() == 10 || dealerHand.getCards().getFirst() == 11) && isSurrender && player.getHands().size() == 1 && totalDecks > 1) {
                            player.surrender(currentHand, deck);
                            isRoundOver = true;
                        }
                        else if (dealerHand.getCards().getFirst() == 9 && isSurrender && player.getHands().size() == 1 && totalDecks >= 4) {
                            player.surrender(currentHand, deck);
                            isRoundOver = true;
                        }
                        else hand.addCard(deck.dealCard());
                    }
                    else if (hand.getValue() == 17) {
                        if (dealerHand.getCards().getFirst() == 11 && !isS17 && isSurrender && player.getHands().size() == 1) {
                            player.surrender(currentHand, deck);
                            isRoundOver = true;
                        }
                        else isRoundOver = true;
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
                            if (totalDecks == 1 && isS17) isRoundOver = true;
                            else hand.addCard(deck.dealCard());
                        }
                        else isRoundOver = true;
                    }
                    else isRoundOver = true;
                }
                else {
                    if (hand.getValue() <= 11) hand.addCard(deck.dealCard());
                    else if (hand.getValue() == 12) {
                        if (dealerHand.getCards().getFirst() >= 4 && dealerHand.getCards().getFirst() <= 6) isRoundOver = true;
                        else hand.addCard(deck.dealCard());
                    }
                    else if (hand.getValue() >= 13 && hand.getValue() <= 16) {
                        if (dealerHand.getCards().getFirst() <= 6) isRoundOver = true;
                        else hand.addCard(deck.dealCard());
                    }
                    else isRoundOver = true;
                }
            }
        }
    }
}