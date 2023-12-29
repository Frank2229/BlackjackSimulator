import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {
        // SIMULATION PARAMETERS
        final DecimalFormat edgeFormat = new DecimalFormat("0.000");
        final int playerSeat = 0;
        final int totalOtherPlayers = 0;
        final int totalRounds = 10000000;
        final LinkedList<Integer> initialCards = new LinkedList<>();
        final NumberFormat winLossFormat = NumberFormat.getCurrencyInstance();
        int currentRound = 0;
        int tempInt;

        Rules rules = new Rules(true, true, false, false, false, 1.5, 0.17, 1, 0, 3, 1, 5000, 25, 3); // Establish game rules.

        // PLAYER SETUP
        final Player player = new Player("MENTOR STRATEGY");
        final Player[] players = new Player[5];
        final Player dealer = new Player("DEALER STRATEGY");

        tempInt = totalOtherPlayers;

        for (int i = 0; i < players.length; i++) {
            if (i == playerSeat) players[i] = player;
            else if (tempInt > 0) {
                players[i] = new Player("BASIC STRATEGY");
                tempInt--;
            }
        }

        Deck deck = new Deck(rules); // The deck is created, shuffled, and burn card taken.

        // Start simulation.
        // If the ratio of cards remaining by total cards is greater than penetration or the current round is less than the total rounds allowed before the dealer shuffles, play round. Otherwise, shuffle.
        while (currentRound < totalRounds) {
            if ((rules.isShuffleByRounds() && tempInt < rules.getRoundsPerShuffle()) || (!rules.isShuffleByRounds() && deck.getShoe().size() / (rules.getTotalDecks() * 52.0) > rules.getPenetration() && player.getStrategy().getTrueCount() >= -4)) {
                for (Player value : players) if (value != null) value.placeWager(rules.getTableMin(), rules.getTableMax()); // Place wagers.

                // Deal initial two cards.
                for (int j = 0; j < 2; j++) {
                    for (Player value : players) if (value != null) {
                        initialCards.add(deck.getShoe().peek());
                        deck.dealCard(value.getHands().getFirst());
                    }

                    if (j == 0) initialCards.add(deck.getShoe().peek());
                    deck.dealCard(dealer.getHands().getFirst());
                }

                // If dealer has an 11 showing, offer insurance.
                if (dealer.getHands().getFirst().getCards().getFirst() == 11) for (Player value : players) if (value != null) if (value.getStrategy().isTakesInsurance()) value.placeInsuranceWager();

                // All players add cards to counting strategies.
                for (Player item : players) if (item != null) for (Integer initialCard : initialCards) item.getStrategy().addCardToCount(initialCard, deck.getShoe().size());
                initialCards.clear();

                // If the dealer has 21, the round ends.
                // Insurance wagers are paid out, player blackjacks push, and all other hands lose.
                if (dealer.getHands().getFirst().getValue() == 21) {
                    for (Player value : players) {
                        if (value != null) {
                            if (value.getStrategy().isTakesInsurance()) value.addWinsLosses(value.getInsuranceWager() * 2.0);
                            if (value.getHands().getFirst().getValue() != 21) value.addWinsLosses(-1 * value.getWagers().getFirst());
                            value.resetRound(deck);
                            value.getStrategy().addCardToCount(dealer.getHands().getFirst().getCards().get(1), deck.getShoe().size());
                        }
                    }

                    dealer.resetRound(deck);
                }
                else {
                    for (Player value : players) {
                        if (value != null) {
                            if (value.getStrategy().isTakesInsurance()) value.addWinsLosses(-1 * value.getInsuranceWager());

                            // If the player has a blackjack, he/she is paid out. Otherwise, play hand.
                            if (value.getHands().getFirst().getValue() == 21) {
                                value.addWinsLosses(rules.getBlackjackPayout() * value.getWagers().getFirst());
                                value.resetRound(deck);
                            }
                            else playHand(value, deck, dealer, 0, rules);

                            for (Player item : players) {
                                if (item != null && item != value) {
                                    for (int j = 0; j < value.getHands().size(); j++) {
                                        for (int k = 0; k < value.getHands().get(j).getCards().size(); k++) {
                                            if (value.getHands().size() > 1) {
                                                if ((j == 0 || j == 1) && k >= 1)
                                                    item.getStrategy().addCardToCount(value.getHands().get(j).getCards().get(k), deck.getShoe().size());
                                            } else {
                                                if (k >= 2)
                                                    item.getStrategy().addCardToCount(value.getHands().get(j).getCards().get(k), deck.getShoe().size());
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }

                    dealer.getStrategy().playHand(dealer.getHands().getFirst(), deck, dealer.getHands().getFirst(), dealer, 0, rules); // After all hands are played, dealer plays hand.

                    // Add dealer's cards to player counts.
                    for (Player item : players)
                        if (item != null) for (int j = 1; j < dealer.getHands().getFirst().getCards().size(); j++)
                            item.getStrategy().addCardToCount(dealer.getHands().getFirst().getCards().get(j), deck.getShoe().size());

                    // All hands are compared to the dealer's.
                    // If a player's hand is greater than the dealer's without going over 21, he/she wins.
                    for (Player value : players) {
                        if (value != null) {
                            for (int j = 0; j < value.getHands().size(); j++) {
                                if (!value.getWagers().isEmpty()) {
                                    if (value.getHands().get(j).getValue() <= 21 && (value.getHands().get(j).getValue() > dealer.getHands().getFirst().getValue() || dealer.getHands().getFirst().getValue() > 21))
                                        value.addWinsLosses(value.getWagers().get(j));
                                    else if (value.getHands().get(j).getValue() > 21 || (value.getHands().get(j).getValue() < dealer.getHands().getFirst().getValue() && dealer.getHands().getFirst().getValue() <= 21))
                                        value.addWinsLosses(-1 * value.getWagers().get(j));
                                }
                            }

                            value.resetRound(deck); // Player clears hands and wagers.
                        }
                    }

                    dealer.resetRound(deck); // Dealer clears hands and wagers.
                }

                currentRound++;
                tempInt++;
            }
            else {
                deck.shuffle(rules.getBurnCards());
                for (Player value : players)
                    if (value != null) value.getStrategy().resetCount(); // Revert all player counts to 0.
                tempInt = 0;
            }
        }

        // Certain online casinos offer deposit bonuses, which are monetary incentives to gamble more.
        // This block factors in the wagering requirement to calculate the total deposit bonuses earned and add to winnings.
        if (player.getStrategy().getStrategyName().equals("CAESARS DEPOSIT BONUS STRATEGY")) {
            double depositBonus = player.getTotalWagers() / 50.0;
            player.addWinsLosses(depositBonus);
        }

        // Calculate the average win/loss per wager, or 'edge'.
        // If positive, there is a player edge. If negative, there is a house edge.
        double edge = (player.getWinsLosses() / player.getTotalWagers()) * 100;
        if (edge >= 0) System.out.println("\nPlayer Edge: " + edgeFormat.format(edge) + "%");
        else System.out.println("\nHouse Edge: " + edgeFormat.format(edge * -1) + "%");
        if (player.getWinsLosses() < 0) System.out.println("Net loss: (" + winLossFormat.format(player.getWinsLosses() * -1) + ")");
        else System.out.println("Net win: " + winLossFormat.format(player.getWinsLosses()));
    }

    // Recursive loop that terminates when the player plays all of his/her hands.
    // Total loops increases everytime the player splits hand.
    public static void playHand(Player player, Deck deck, Player dealer, int currentHand, Rules rules) {
        int tempInt = currentHand;
        player.getStrategy().playHand(player.getHands().get(tempInt), deck, dealer.getHands().getFirst(), player, tempInt, rules);
        tempInt++;
        if (tempInt < player.getHands().size())playHand(player, deck, dealer, tempInt, rules);
    }
}