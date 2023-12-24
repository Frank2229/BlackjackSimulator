import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {
        // SIMULATION PARAMETERS
        final int playerSeat = 2;
        final int totalOtherPlayers = 0;
        final int totalRounds = 100000;
        double progress;
        int currentRound = 0;
        int progressRounded;
        int tempInt;

        Rules rules = new Rules(true, true, true, 1.5, 0.2, 1, 0, 5000, 25, 8);

        // PLAYER SETUP
        final Player player = new Player("BASIC STRATEGY");
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
        // If the ratio of cards remaining by total cards is greater than penetration, play round. Otherwise, shuffle.
        while (currentRound < totalRounds) {
            if (deck.getShoe().size() / (double)(deck.getShoe().size() + deck.getDiscardTray().size()) > rules.getPenetration()) {
                for (Player value : players) if (value != null) value.placeWager(rules.getTableMin()); // Place wagers.

                // Deal initial two cards.
                for (int j = 0; j < 2; j++) {
                    for (Player value : players) if (value != null) value.getHands().getFirst().addCard(deck.dealCard());

                    dealer.getHands().getFirst().addCard(deck.dealCard());
                }

                // If dealer has an 11 showing, offer insurance.
                if (dealer.getHands().getFirst().getCards().getFirst() == 11) {
                    for (Player value : players) if (value != null) if (value.getStrategy().isTakesInsurance()) value.placeInsuranceWager();
                }

                // If the dealer has 21, the round ends.
                // Insurance wagers are paid out, player blackjacks push, and all other hands lose.
                if (dealer.getHands().getFirst().getValue() == 21) {
                    for (Player value : players) {
                        if (value != null) {
                            if (value.getStrategy().isTakesInsurance()) value.addWinsLosses(value.getInsuranceWager() * 2.0);
                            if (value.getHands().getFirst().getValue() != 21) value.addWinsLosses(-1 * value.getWagers().getFirst());
                            value.resetRound(deck);
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
                        }
                    }

                    dealer.getStrategy().playHand(dealer.getHands().getFirst(), deck, dealer.getHands().getFirst(), dealer, 0, rules); // After all hands are played, dealer plays hand.

                    //LinkedList<String> playerHandsStrings = new LinkedList<>();
                    //StringBuilder tempString;
                    //for (int i = 0; i < player.getHands().size(); i++) {
                        //playerHandsStrings.add("Player Hand " + (i + 1) + ": ");
                        //tempString = new StringBuilder();
                        //for (int j = 0; j < player.getHands().get(i).getCards().size(); j++) tempString.append(Integer.toString(player.getHands().get(i).getCards().get(j))).append(" ");
                        //playerHandsStrings.set(i, playerHandsStrings.get(i) + tempString);
                    //}
                    //for (int i = 0; i < playerHandsStrings.size(); i++) {
                        //System.out.println(playerHandsStrings.get(i));
                        //System.out.println(player.getHands().get(i).getValue());
                    //}
                    //String dealerHandString = "Dealer Hand: ";
                    //tempString = new StringBuilder();
                    //for (int i = 0; i < dealer.getHands().getFirst().getCards().size(); i++) tempString.append(Integer.toString(dealer.getHands().getFirst().getCards().get(i))).append(" ");
                    //dealerHandString += tempString;
                    //System.out.println(dealerHandString);
                    //System.out.println(dealer.getHands().getFirst().getValue());

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

                                    value.resetRound(deck); // Player clears hands and wagers.
                                }
                            }
                        }
                    }

                    dealer.resetRound(deck); // Dealer clears hands and wagers.
                }

                currentRound++;
                //progress = (currentRound / (double) totalRounds) * 100;
                //progressRounded = (int) Math.round(progress);
                //System.out.println(progressRounded + "% complete");
            }
            else deck.shuffle(rules.getBurnCards());
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
        if (edge >= 0) System.out.println("\nPlayer Edge: " + edge);
        else System.out.println("\nHouse Edge: " + edge * -1);
    }

    public static int adjustCount(int[] countValues, int card, int runningCount) {
        int temp = runningCount;

        switch (card) {
            case 2:
                temp += countValues[0];
                break;
            case 3:
                temp += countValues[1];
                break;
            case 4:
                temp += countValues[2];
                break;
            case 5:
                temp += countValues[3];
                break;
            case 6:
                temp += countValues[4];
                break;
            case 7:
                temp += countValues[5];
                break;
            case 8:
                temp += countValues[6];
                break;
            case 9:
                temp += countValues[7];
                break;
            case 10:
                temp += countValues[8];
                break;
            case 11:
                temp += countValues[9];
                break;
        }

        return temp;
    }

    public static void playHand(Player player, Deck deck, Player dealer, int currentHand, Rules rules) {
        int tempInt = currentHand;
        player.getStrategy().playHand(player.getHands().getFirst(), deck, dealer.getHands().getFirst(), player, tempInt, rules);
        tempInt++;
        if (tempInt < player.getHands().size())playHand(player, deck, dealer, tempInt, rules);
    }
}