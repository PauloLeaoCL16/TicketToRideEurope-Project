import java.util.ArrayList;
import java.util.Collections;

public class Deck {
    private ArrayList<String> deck;
    public Deck() {
        deck = new ArrayList<String>();
        add();
    }

    private void add() {
        for (int i = 0; i < 10; i++) {
            deck.add("red");
        }
        for (int i = 0; i < 10; i++) {
            deck.add("blue");
        }
        for (int i = 0; i < 10; i++) {
            deck.add("green");
        }
        for (int i = 0; i < 5; i++) {
            deck.add("wild");
        }
        shuffle();
    }

    private void shuffle() {
        Collections.shuffle(deck);
    }

    public String draw() {
        String item = deck.remove(deck.size() - 1);
        if (deck.size() == 0) {
            add();
        }
        return item;
    }

    public ArrayList<String> getDeck() {
        return deck;
    }
}
