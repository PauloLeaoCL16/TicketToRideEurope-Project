import java.awt.image.BufferedImage;
import java.awt.image.*;
import javax.imageio.ImageIO;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

import javax.imageio.ImageIO;

public class CardDeck {
	
    private ArrayList<ColorCard> deck;
    
    public CardDeck() {
        deck = new ArrayList<ColorCard>();
        add();
    }

    private void add() {
    	try {
	        for (int i = 0; i < 12; i++) {
	            deck.add(new ColorCard("pink", ImageIO.read(MainMenu.class.getResource("/cards/purplecard.png"))));
	        }
	        for (int i = 0; i < 12; i++) {
	            deck.add(new ColorCard("white", ImageIO.read(MainMenu.class.getResource("/cards/whitecard.png"))));
	        }
	        for (int i = 0; i < 12; i++) {
	            deck.add(new ColorCard("blue", ImageIO.read(MainMenu.class.getResource("/cards/bluecard.png"))));
	        }
	        for (int i = 0; i < 12; i++) {
	            deck.add(new ColorCard("yellow", ImageIO.read(MainMenu.class.getResource("/cards/yellowcard.png"))));
	        }
	        for (int i = 0; i < 12; i++) {
	            deck.add(new ColorCard("orange", ImageIO.read(MainMenu.class.getResource("/cards/browncard.png"))));
	        }
	        for (int i = 0; i < 12; i++) {
	            deck.add(new ColorCard("black", ImageIO.read(MainMenu.class.getResource("/cards/blackcard.png"))));
	        }
	        for (int i = 0; i < 12; i++) {
	            deck.add(new ColorCard("red", ImageIO.read(MainMenu.class.getResource("/cards/redcard.png"))));
	        }
	        for (int i = 0; i < 12; i++) {
	            deck.add(new ColorCard("green", ImageIO.read(MainMenu.class.getResource("/cards/greencard.png"))));
	        }
	        for (int i = 0; i < 14; i++) {
	            deck.add(new ColorCard("wild", ImageIO.read(MainMenu.class.getResource("/cards/wildcard.png"))));
	        }
	        shuffle();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
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
    
    public ColorCard drawCard() {
        ColorCard item = deck.remove(deck.size() - 1);
        if (deck.size() == 0) {
            add();
        }
        return item;
    }

    public ArrayList<ColorCard> getDeck() 
    {
    	return deck;
    }
}
