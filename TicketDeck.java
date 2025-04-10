package ttreImages;
import java.util.ArrayList;
import java.util.Collections;

import javax.imageio.ImageIO;

import java.awt.image.*;

public class TicketDeck {
	private ArrayList<Ticket> ticketList;
	private ArrayList<Ticket> longTicketList;
	public TicketDeck() {
		ticketList = new ArrayList<Ticket>();
		add();
	}
	
	private void add() {
		try {
		longTicketList.add(new Ticket("palermo", "moskva", 20, ImageIO.read(MainMenu.class.getResource("/ttreImages/longRoute1.png"))));
		longTicketList.add(new Ticket("brest", "petrograd", 20, ImageIO.read(MainMenu.class.getResource("/ttreImages/longRoute2.png"))));
		longTicketList.add(new Ticket("kobenhavn", "erzurum", 21, ImageIO.read(MainMenu.class.getResource("/ttreImages/longRoute3.png"))));
		longTicketList.add(new Ticket("cadiz", "stockholm", 21, ImageIO.read(MainMenu.class.getResource("/ttreImages/longRoute4.png"))));
		longTicketList.add(new Ticket("lisboa", "danzic", 20, ImageIO.read(MainMenu.class.getResource("/ttreImages/longRoute5.png"))));
		longTicketList.add(new Ticket("edinburch", "athina", 21, ImageIO.read(MainMenu.class.getResource("/ttreImages/longRoute6.png"))));
		shuffle();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	private void shuffle() {
        Collections.shuffle(ticketList);
        Collections.shuffle(longTicketList);
    }

	
	public Ticket draw() {
		Ticket item = ticketList.remove(ticketList.size() - 1);
        if (ticketList.size() == 0) {
            add();
        }
        
        return item;
	}
	public Ticket drawLongTicket() {
 		Ticket item = longTicketList.remove(longTicketList.size() - 1);
         if (longTicketList.size() == 0) {
             add();
         }
         
         return item;
 	}
}
