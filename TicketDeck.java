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
			ticketList.add(new Ticket("amsterdam", "wilno", 12, ImageIO.read(MainMenu.class.getResource("/ttreImages/amsterdam-wilno.png"))));
			ticketList.add(new Ticket("angora", "kharkov", 10, ImageIO.read(MainMenu.class.getResource("/ttreImages/angora-kharkov.png"))));
			ticketList.add(new Ticket("athina", "angora", 5, ImageIO.read(MainMenu.class.getResource("/ttreImages/athina-angora.png"))));
			ticketList.add(new Ticket("athina", "wilno", 11, ImageIO.read(MainMenu.class.getResource("/ttreImages/athina-wilno.png"))));
			ticketList.add(new Ticket("barcelona", "bruxelles", 8, ImageIO.read(MainMenu.class.getResource("/ttreImages/barcelona-bruxelles.png"))));
			ticketList.add(new Ticket("barcelona", "munchen", 8, ImageIO.read(MainMenu.class.getResource("/ttreImages/barcelona-munchen.png"))));
			ticketList.add(new Ticket("berlin", "bucuresti", 7, ImageIO.read(MainMenu.class.getResource("/ttreImages/berlin-bucuresti.png"))));
			ticketList.add(new Ticket("berlin", "moskva", 7, ImageIO.read(MainMenu.class.getResource("/ttreImages/berlin-moskva.png"))));
			ticketList.add(new Ticket("berlin", "roma", 7, ImageIO.read(MainMenu.class.getResource("/ttreImages/berlin-roma.png"))));
			ticketList.add(new Ticket("brest", "marseille", 7, ImageIO.read(MainMenu.class.getResource("/ttreImages/brest-marseille.png"))));
			ticketList.add(new Ticket("brest", "petrograd", 7, ImageIO.read(MainMenu.class.getResource("/ttreImages/brest-petrograd.png"))));
			ticketList.add(new Ticket("brest", "venezia", 7, ImageIO.read(MainMenu.class.getResource("/ttreImages/brest-venezia.png"))));
			ticketList.add(new Ticket("bruxelles", "danzic", 7, ImageIO.read(MainMenu.class.getResource("/ttreImages/bruxelles-danzic.png"))));
			ticketList.add(new Ticket("budapest", "sofia", 7, ImageIO.read(MainMenu.class.getResource("/ttreImages/budapest-sofia.png"))));
			ticketList.add(new Ticket("cadiz", "stockholm", 7, ImageIO.read(MainMenu.class.getResource("/ttreImages/cadiz-stockholm.png"))));
			ticketList.add(new Ticket("edinburgh", "athina", 7, ImageIO.read(MainMenu.class.getResource("/ttreImages/edinburgh-athina.png"))));
			ticketList.add(new Ticket("edinburgh", "paris", 7, ImageIO.read(MainMenu.class.getResource("/ttreImages/edinburgh-paris.png"))));
			ticketList.add(new Ticket("erzurum", "rostov", 7, ImageIO.read(MainMenu.class.getResource("/ttreImages/erzurum-rostov.png"))));
			ticketList.add(new Ticket("essen", "kyiv", 7, ImageIO.read(MainMenu.class.getResource("/ttreImages/essen-kyiv.png"))));
			ticketList.add(new Ticket("frankfurt", "kobenhavn", 7, ImageIO.read(MainMenu.class.getResource("/ttreImages/frankfurt-kobenhavn.png"))));
			ticketList.add(new Ticket("frankfurt", "smolensk", 7, ImageIO.read(MainMenu.class.getResource("/ttreImages/frankfurt-smolensk.png"))));
			ticketList.add(new Ticket("kobenhavn", "erzurum", 7, ImageIO.read(MainMenu.class.getResource("/ttreImages/kobenhavn-erzurum.png"))));
			ticketList.add(new Ticket("kyiv", "petrograd", 7, ImageIO.read(MainMenu.class.getResource("/ttreImages/kyiv-petrograd.png"))));
			ticketList.add(new Ticket("kyiv", "sochi", 7, ImageIO.read(MainMenu.class.getResource("/ttreImages/kyiv-sochi.png"))));
			ticketList.add(new Ticket("lisboa", "danzig", 7, ImageIO.read(MainMenu.class.getResource("/ttreImages/lisboa-danzig.png"))));
			ticketList.add(new Ticket("london", "berlin", 7, ImageIO.read(MainMenu.class.getResource("/ttreImages/london-berlin.png"))));
			ticketList.add(new Ticket("london", "wien", 7, ImageIO.read(MainMenu.class.getResource("/ttreImages/london-wien.png"))));
			ticketList.add(new Ticket("madrid", "dieppe", 7, ImageIO.read(MainMenu.class.getResource("/ttreImages/madrid-dieppe.png"))));
			ticketList.add(new Ticket("madrid", "zurich", 7, ImageIO.read(MainMenu.class.getResource("/ttreImages/madrid-zurich.png"))));
			ticketList.add(new Ticket("marseille", "essen", 7, ImageIO.read(MainMenu.class.getResource("/ttreImages/marseille-essen.png"))));
			ticketList.add(new Ticket("palermo", "constantinople", 7, ImageIO.read(MainMenu.class.getResource("/ttreImages/palermo-constantinople.png"))));
			ticketList.add(new Ticket("palermo", "moskva", 7, ImageIO.read(MainMenu.class.getResource("/ttreImages/palermo-moskva.png"))));
			ticketList.add(new Ticket("paris", "wien", 7, ImageIO.read(MainMenu.class.getResource("/ttreImages/paris-wien.png"))));
			ticketList.add(new Ticket("paris", "zagrab", 7, ImageIO.read(MainMenu.class.getResource("/ttreImages/paris-zagrab.png"))));
			ticketList.add(new Ticket("riga", "bucuresti", 7, ImageIO.read(MainMenu.class.getResource("/ttreImages/riga-bucuresti.png"))));
			ticketList.add(new Ticket("roma", "smyrna", 7, ImageIO.read(MainMenu.class.getResource("/ttreImages/roma-smyrna.png"))));
			ticketList.add(new Ticket("sarajevo", "sevastopol", 7, ImageIO.read(MainMenu.class.getResource("/ttreImages/sarajevo-sevastopol.png"))));
			ticketList.add(new Ticket("smolensk", "rostov", 7, ImageIO.read(MainMenu.class.getResource("/ttreImages/smolensk-rostov.jpg"))));
			ticketList.add(new Ticket("sofia", "smyrna", 7, ImageIO.read(MainMenu.class.getResource("/ttreImages/sofia-smyrna.png"))));
			ticketList.add(new Ticket("stockholm", "wien", 7, ImageIO.read(MainMenu.class.getResource("/ttreImages/stockholm-wien.png"))));
			ticketList.add(new Ticket("venezia", "constantinople", 7, ImageIO.read(MainMenu.class.getResource("/ttreImages/venezia-constantinople.png"))));
			ticketList.add(new Ticket("warszawa", "smokensk", 7, ImageIO.read(MainMenu.class.getResource("/ttreImages/warszawa-smokensk.png"))));
			ticketList.add(new Ticket("zacrab", "brindisi", 7, ImageIO.read(MainMenu.class.getResource("/ttreImages/zacrab-brindisi.png"))));
			ticketList.add(new Ticket("zurich", "brindisi", 7, ImageIO.read(MainMenu.class.getResource("/ttreImages/zurich-brindisi.png"))));
			ticketList.add(new Ticket("zurich", "budapest", 7, ImageIO.read(MainMenu.class.getResource("/ttreImages/zurich-budapest.png"))));
			
			longTicketList.add(new Ticket("palermo", "moskva", 20, ImageIO.read(MainMenu.class.getResource("/ttreImages/longRoute1.png"))));
			longTicketList.add(new Ticket("brest", "petrograd", 20, ImageIO.read(MainMenu.class.getResource("/ttreImages/longRoute2.png"))));
			longTicketList.add(new Ticket("kobenhavn", "erzurum", 21, ImageIO.read(MainMenu.class.getResource("/ttreImages/longRoute3.png"))));
			longTicketList.add(new Ticket("cadiz", "stockholm", 21, ImageIO.read(MainMenu.class.getResource("/ttreImages/longRoute4.png"))));
			longTicketList.add(new Ticket("lisboa", "danzic", 20, ImageIO.read(MainMenu.class.getResource("/ttreImages/longRoute5.png"))));
			longTicketList.add(new Ticket("edinburch", "athina", 21, ImageIO.read(MainMenu.class.getResource("/ttreImages/longRoute6.png"))));
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
