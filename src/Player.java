import java.util.LinkedList;

public class Player {
    private final LinkedList<Hand> hands = new LinkedList<>();
    private final LinkedList<Integer> wagers = new LinkedList<>();
    private double totalWagers = 0;
    private double insuranceWager;
    private double winsLosses = 0;
    private int initialWager;
    private Strategy strategy;

    public Player(String strategyString) {
        switch (strategyString) {
            case "BASIC STRATEGY":
                strategy = new BasicStrategy();
                break;
            case "DEALER STRATEGY":
                strategy = new DealerStrategy();
                break;
            case "STAND STRATEGY":
                strategy = new StandStrategy();
                break;
            case "FOLLOW DEALER STRATEGY":
                strategy = new FollowDealerStrategy();
                break;
            case "CAESARS DEPOSIT BONUS STRATEGY":
                strategy = new CaesarsDepositBonusStrategy();
                break;
            case "HI-LO STRATEGY":
                strategy = new HiLoStrategy();
                break;
            case "MENTOR STRATEGY":
                strategy = new MentorStrategy();
                break;
        }

        hands.add(new Hand());
    }

    public void addWinsLosses(double winsLosses) {
        this.winsLosses += winsLosses;
    }

    public void doubleDown(int currentHand) {
        totalWagers += initialWager;
        wagers.set(currentHand, wagers.get(currentHand) + initialWager);
    }

    public LinkedList<Hand> getHands() {
        return hands;
    }

    public double getInsuranceWager() {
        return insuranceWager;
    }

    public Strategy getStrategy() {
        return strategy;
    }

    public double getTotalWagers() {
        return totalWagers;
    }

    public LinkedList<Integer> getWagers() {
        return wagers;
    }

    public double getWinsLosses() {
        return winsLosses;
    }

    public void placeInsuranceWager() {
        insuranceWager = wagers.getFirst() / 2.0;
        totalWagers += insuranceWager;
    }

    public void placeWager(int tableMin, int tableMax) {
        wagers.add(strategy.calculateWager(tableMin, tableMax));
        initialWager = wagers.getFirst();
        totalWagers += initialWager;
    }

    public void resetRound(Deck deck) {
        for (Hand hand : hands) deck.discardCards(hand);
        wagers.clear();
        hands.clear();
        hands.add(new Hand());
        insuranceWager = 0;
        initialWager = 0;
    }

    public void splitHand(int currentHand, Deck deck) {
        hands.add(new Hand());
        hands.getLast().addCard(hands.get(currentHand).getCards().pop());
        hands.get(currentHand).decreaseValue(hands.get(currentHand).getCards().getFirst());
        strategy.addCardToCount(deck.getShoe().peek(), deck.getShoe().size());
        deck.dealCard(hands.getLast());
        wagers.add(initialWager);
        totalWagers += initialWager;
        if (hands.get(currentHand).getCards().getFirst() == 11) hands.get(currentHand).increaseValue();
    }

    public void surrender(int currentHand, Deck deck) {
        addWinsLosses(-1.0 * (wagers.get(currentHand) / 2.0));
        resetRound(deck);
    }
}