import java.util.LinkedList;

public class Hand {
    private final LinkedList<Integer> cards = new LinkedList<>();
    private int softCards = 0;
    private int value = 0;

    public void addCard(int card) {
        cards.add(card);
        if (card == 11) softCards++;
        value += card;

        // if the players hand is soft but goes over 21, the hand value is decreased by 10, but the 11 is no longer treated as 'soft'.
        int temp = softCards;

        for (int i = 0; i < temp; i++) {
            if (value > 21 && softCards > 0) {
                value -= 10;
                softCards--;
            }
        }
    }


    public void decreaseValue(int card) {
        value -= card;
        if (card == 11) softCards--;
    }

    public LinkedList<Integer> getCards() {
        return cards;
    }

    public int getSoftCards() {
        return softCards;
    }

    public int getValue() {
        return value;
    }
}