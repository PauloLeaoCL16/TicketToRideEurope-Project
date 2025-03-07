import java.util.ArrayList;
import java.util.Collections;
import java.awt.image.*;

public class TicketDeck {
	private ArrayList<Ticket> ticketList;
	public TicketDeck() {
		ticketList = new ArrayList<Ticket>();
		add();
	}
	
	private void add() {
		ticketList.add(new Ticket("Amsterdam", "Pamblona", 7, new BufferedImage(1, 1, 1)));
		shuffle();
	}
	
	private void shuffle() {
        Collections.shuffle(ticketList);
    }

	
	public void draw(Player p) {
		Ticket item = ticketList.remove(ticketList.size() - 1);
        if (ticketList.size() == 0) {
            add();
        }
        
        p.addTicket(item);
	}
}
