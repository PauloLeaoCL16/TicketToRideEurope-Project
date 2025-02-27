import java.util.ArrayList;
import java.util.Collections;

public class Deck {
    private ArrayList<ColorCard> deck;
    public Deck() {
        deck = new ArrayList<ColorCard>();
        add();
    }

    private void add() {
        for (int i = 0; i < 10; i++) {
            deck.add(new ColorCard("red"));
        }
        for (int i = 0; i < 10; i++) {
            deck.add(new ColorCard("blue"));
        }
        for (int i = 0; i < 10; i++) {
            deck.add(new ColorCard("green"));
        }
        for (int i = 0; i < 5; i++) {
            deck.add(new ColorCard("wild"));
        }
        shuffle();
    }

    private void shuffle() {
        Collections.shuffle(deck);
    }

    public void draw(Player p) {
        ColorCard item = deck.remove(deck.size() - 1);
        if (deck.size() == 0) {
            add();
        }
        p.addCard(item);
    }

    public ArrayList<String> getDeck() {
        return deck;
    }
}
