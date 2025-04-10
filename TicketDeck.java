package ttreImages;
import java.util.ArrayList;
import java.util.Collections;

import javax.imageio.ImageIO;

import java.awt.image.*;
import java.io.IOException;

public class TicketDeck {
	private ArrayList<Ticket> ticketList;
	private ArrayList<Ticket> longTicketList;
	
	public TicketDeck() {
		ticketList = new ArrayList<Ticket>();
		longTicketList = new ArrayList<Ticket>();
		add();
	}
	
	private void add() {
		try 
		{
			ticketList.add(new Ticket("amsterdam", "pamplona", 7, ImageIO.read(MainMenu.class.getResource("/ttreImages/amsterdam-pamplona.png"))));
			ticketList.add(new Ticket("amsterdam", "pamplona", 7, ImageIO.read(MainMenu.class.getResource("/ttreImages/amsterdam-pamplona.png"))));
			ticketList.add(new Ticket("amsterdam", "pamplona", 7, ImageIO.read(MainMenu.class.getResource("/ttreImages/amsterdam-pamplona.png"))));
			ticketList.add(new Ticket("amsterdam", "pamplona", 7, ImageIO.read(MainMenu.class.getResource("/ttreImages/amsterdam-pamplona.png"))));
			ticketList.add(new Ticket("amsterdam", "pamplona", 7, ImageIO.read(MainMenu.class.getResource("/ttreImages/amsterdam-pamplona.png"))));
			ticketList.add(new Ticket("amsterdam", "pamplona", 7, ImageIO.read(MainMenu.class.getResource("/ttreImages/amsterdam-pamplona.png"))));
			ticketList.add(new Ticket("amsterdam", "pamplona", 7, ImageIO.read(MainMenu.class.getResource("/ttreImages/amsterdam-pamplona.png"))));
			ticketList.add(new Ticket("amsterdam", "pamplona", 7, ImageIO.read(MainMenu.class.getResource("/ttreImages/amsterdam-pamplona.png"))));
			ticketList.add(new Ticket("amsterdam", "pamplona", 7, ImageIO.read(MainMenu.class.getResource("/ttreImages/amsterdam-pamplona.png"))));
			ticketList.add(new Ticket("amsterdam", "pamplona", 7, ImageIO.read(MainMenu.class.getResource("/ttreImages/amsterdam-pamplona.png"))));
			ticketList.add(new Ticket("amsterdam", "pamplona", 7, ImageIO.read(MainMenu.class.getResource("/ttreImages/amsterdam-pamplona.png"))));
			ticketList.add(new Ticket("amsterdam", "pamplona", 7, ImageIO.read(MainMenu.class.getResource("/ttreImages/amsterdam-pamplona.png"))));
			ticketList.add(new Ticket("amsterdam", "pamplona", 7, ImageIO.read(MainMenu.class.getResource("/ttreImages/amsterdam-pamplona.png"))));
			ticketList.add(new Ticket("amsterdam", "pamplona", 7, ImageIO.read(MainMenu.class.getResource("/ttreImages/amsterdam-pamplona.png"))));
			ticketList.add(new Ticket("amsterdam", "pamplona", 7, ImageIO.read(MainMenu.class.getResource("/ttreImages/amsterdam-pamplona.png"))));
			ticketList.add(new Ticket("amsterdam", "pamplona", 7, ImageIO.read(MainMenu.class.getResource("/ttreImages/amsterdam-pamplona.png"))));
			ticketList.add(new Ticket("amsterdam", "pamplona", 7, ImageIO.read(MainMenu.class.getResource("/ttreImages/amsterdam-pamplona.png"))));
			ticketList.add(new Ticket("amsterdam", "pamplona", 7, ImageIO.read(MainMenu.class.getResource("/ttreImages/amsterdam-pamplona.png"))));
			ticketList.add(new Ticket("amsterdam", "pamplona", 7, ImageIO.read(MainMenu.class.getResource("/ttreImages/amsterdam-pamplona.png"))));
			ticketList.add(new Ticket("amsterdam", "pamplona", 7, ImageIO.read(MainMenu.class.getResource("/ttreImages/amsterdam-pamplona.png"))));
			shuffle();
		}
		catch (IOException e) {
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
