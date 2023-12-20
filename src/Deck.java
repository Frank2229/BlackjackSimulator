import java.util.Collections;
import java.util.Stack;

public class Deck {
    final private Stack<Integer> discardTray = new Stack<>();
    final private Stack<Integer> shoe = new Stack<>();

    public Deck(int totalDecks, int burnCards) {
        // Assemble contents of the playing shoe. Each element represents individual card values.
        for (int i = 0; i < totalDecks; i++) {
            for (int j = 0; j < 4; j++) {
                for (int k = 2; k < 15; k ++) {
                    if (k < 11) shoe.add(k); // 2 - 10
                    else if (k == 14) shoe.add(11);  // Ace
                    else shoe.add(10);  // Jack, Queen, King
                }
            }
        }

        shuffle(burnCards);
    }

    public int dealCard() {
        int temp = shoe.peek();
        discardTray.add(shoe.pop());

        return temp;
    }

    public Stack<Integer> getDiscardTray() {
        return discardTray;
    }

    public Stack<Integer> getShoe() {
        return shoe;
    }

    // All cards from discard tray are moved back to the shoe and shuffled.
    // After the shuffle, a specific number of cards are removed from play (burned).
    public void shuffle(int burnCards) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < discardTray.size(); j++) shoe.add(discardTray.pop());
            Collections.shuffle(shoe);
            for (int j = 0; j < burnCards; j++) discardTray.add(shoe.pop());
        }
    }
}