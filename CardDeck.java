import java.util.ArrayList;
import java.util.Collections;

public class CardDeck {
    private ArrayList<ColorCard> deck;
    public CardDeck() {
        deck = new ArrayList<ColorCard>();
        add();
    }

    private void add() {
        for (int i = 0; i < 12; i++) {
            deck.add(new ColorCard("pink"));
        }
        for (int i = 0; i < 12; i++) {
            deck.add(new ColorCard("white"));
        }
        for (int i = 0; i < 12; i++) {
            deck.add(new ColorCard("blue"));
        }
        for (int i = 0; i < 12; i++) {
            deck.add(new ColorCard("yellow"));
        }
        for (int i = 0; i < 12; i++) {
            deck.add(new ColorCard("orange"));
        }
        for (int i = 0; i < 12; i++) {
            deck.add(new ColorCard("black"));
        }
        for (int i = 0; i < 12; i++) {
            deck.add(new ColorCard("red"));
        }
        for (int i = 0; i < 12; i++) {
            deck.add(new ColorCard("green"));
        }
        for (int i = 0; i < 14; i++) {
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

    public int tunnelDraw(ColorCard color) {
        int total = 0;
        for (int i = 0; i < 3; i++) {
            ColorCard item = deck.remove(deck.size() - 1);
            if (deck.size() == 0) {
                add();
            }
            if (color.getColor().equals(item.getColor())) {
                total++;
            }
        }
        return total;
    }

    public ArrayList<ColorCard> getDeck() {
        return deck;
    }
}
