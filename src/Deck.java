import java.util.Collections;
import java.util.Stack;

public class Deck {
    final private Stack<Integer> discardTray = new Stack<>();
    final private Stack<Integer> shoe = new Stack<>();

    public Deck(Rules rules) {
        // Assemble contents of the playing shoe. Each element represents individual card values.
        for (int i = 0; i < rules.getTotalDecks(); i++) {
            for (int j = 0; j < 4; j++) {
                for (int k = 2; k < 15; k ++) {
                    if (k < 11) shoe.add(k); // 2 - 10
                    else if (k == 14) shoe.add(11);  // Ace
                    else shoe.add(10);  // Jack, Queen, King
                }
            }
        }

        shuffle(rules.getBurnCards());
    }

    public void dealCard(Hand hand) {
        hand.addCard(shoe.pop());
    }

    public Stack<Integer> getShoe() {
        return shoe;
    }

    public void discardCards(Hand hand) {
        while (!hand.getCards().isEmpty()) discardTray.add(hand.getCards().pop());
    }

    // All cards from discard tray are moved back to the shoe and shuffled.
    // After the shuffle, a specific number of cards are removed from play (burned).
    public void shuffle(int burnCards) {
        while (!discardTray.isEmpty()) shoe.add(discardTray.pop());
        Collections.shuffle(shoe);
        for (int j = 0; j < burnCards; j++) discardTray.add(shoe.pop());
    }
}