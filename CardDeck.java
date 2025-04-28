package ttreImages;
import java.awt.image.BufferedImage;
import java.awt.image.*;
import javax.imageio.ImageIO;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

import javax.imageio.ImageIO;

public class CardDeck {
	
    private ArrayList<ColorCard> deck;
    private ArrayList<ColorCard> discardDeck;
    private ColorCard purple,white,blue,yellow,orange,black,red,green,wild;
    
    public CardDeck() {
        deck = new ArrayList<ColorCard>();
        discardDeck = new ArrayList<ColorCard>();
        add();
    }

    private void add() {
    	try {
	        for (int i = 0; i < 12; i++) {
	        	purple = new ColorCard("purple", ImageIO.read(MainMenu.class.getResource("/ttreImages/purplecard.png")));
	            deck.add(purple);
	        }
	        for (int i = 0; i < 12; i++) {
	        	white = new ColorCard("white", ImageIO.read(MainMenu.class.getResource("/ttreImages/whitecard.png")));
	            deck.add(white);
	        }
	        for (int i = 0; i < 12; i++) {
	        	blue = new ColorCard("blue", ImageIO.read(MainMenu.class.getResource("/ttreImages/bluecard.png")));
	            deck.add(blue);
	        }
	        for (int i = 0; i < 12; i++) {
	        	yellow = new ColorCard("yellow", ImageIO.read(MainMenu.class.getResource("/ttreImages/yellowcard.png")));
	            deck.add(yellow);
	        }
	        for (int i = 0; i < 12; i++) {
	        	orange = new ColorCard("orange", ImageIO.read(MainMenu.class.getResource("/ttreImages/browncard.png")));
	            deck.add(orange);
	        }
	        for (int i = 0; i < 12; i++) {
	        	black =new ColorCard("black", ImageIO.read(MainMenu.class.getResource("/ttreImages/blackcard.png")));
	            deck.add(black);
	        }
	        for (int i = 0; i < 12; i++) {
	        	red = new ColorCard("red", ImageIO.read(MainMenu.class.getResource("/ttreImages/redcard.png")));
	            deck.add(red);
	        }
	        for (int i = 0; i < 12; i++) {
	        	green = new ColorCard("green", ImageIO.read(MainMenu.class.getResource("/ttreImages/greencard.png")));
	            deck.add(green);
	        }
	        for (int i = 0; i < 14; i++) {
	        	wild = new ColorCard("wild", ImageIO.read(MainMenu.class.getResource("/ttreImages/wildcard.png")));
	            deck.add(wild);
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
        discardDeck.add(item);
        if (deck.size() == 0) {
        	for(int i = 0; i<discardDeck.size();i++)
        		deck.add(discardDeck.remove(i));
        }
        p.addCard(item);
    }
    
    public ColorCard drawCard() {
        ColorCard item = deck.remove(deck.size() - 1);
        discardDeck.add(item);
        if (deck.size() == 0) {
        	for(int i = 0; i<discardDeck.size();i++)
        		deck.add(discardDeck.remove(i));
        }
        return item;
    }
    
    public void addDiscard(ColorCard card)
    {
    	discardDeck.add(card);
    }
    
    public BufferedImage getTopDiscard()
    {
    	if( discardDeck.size() >= 1 )
    		return discardDeck.get(discardDeck.size()-1).getImage();
    	else
    		return new BufferedImage(1,1,1);
    }

    public ArrayList<ColorCard> getDeck() 
    {
    	return deck;
    }
    
    
}
