package ttreImages;

import java.awt.*;
import javax.swing.*;
import java.awt.image.*;
import javax.imageio.ImageIO;
import java.io.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.*;

import static java.lang.System.*;

public class GameBoard extends JPanel implements Runnable, MouseListener, MouseMotionListener {
    private BufferedImage table, board, player1, player2, player3, player4, cardBack, ticket, template, redplayer, blueplayer, greenplayer, yellowplayer, playerpointer;
    private boolean isPlayButtonHovered = false;
    private boolean isRulesScrollHovered = false;
    private ColorCard[] faceUpCard;
    private CardDeck cardDeck;
    private TicketDeck ticketDeck;
    private ArrayList<Ticket> longRoutes;
    private ArrayList<Player> players;
    private City[] citiesToBuy; //Stores 2 cities for buying routes purposes
    private boolean firstCityClicked; // highlighting purposes
    private ArrayList<Integer> highlightCity; // takes in 2 locations for highlighting purposes
    private Graph graph;// stores all the coordinates of the cities in a map
    private boolean[] turns = {true, false, false, false};
    
    private int currentPlr;
    private City[] clickedCity = new City[2];
    private boolean inAnEvent;
    
    public GameBoard() {
        try {
            table = ImageIO.read(MainMenu.class.getResource("/ttreImages/gameBackground.png"));
            board = ImageIO.read(MainMenu.class.getResource("/ttreImages/gameboard.png"));
            player1 = ImageIO.read(MainMenu.class.getResource("/ttreImages/player1label.png"));
            player2 = ImageIO.read(MainMenu.class.getResource("/ttreImages/player2label.png"));
            player3 = ImageIO.read(MainMenu.class.getResource("/ttreImages/player3label.png"));
            player4 = ImageIO.read(MainMenu.class.getResource("/ttreImages/player4label.png"));
            cardBack = ImageIO.read(MainMenu.class.getResource("/ttreImages/backofcard.png"));
            ticket = ImageIO.read(MainMenu.class.getResource("/ttreImages/ticket.png"));
            template = ImageIO.read(MainMenu.class.getResource("/ttreImages/blankcardtemp.png"));
            redplayer = ImageIO.read(MainMenu.class.getResource("/ttreImages/redplayer.png"));
            blueplayer = ImageIO.read(MainMenu.class.getResource("/ttreImages/blueplayer.png"));
            greenplayer = ImageIO.read(MainMenu.class.getResource("/ttreImages/greenplayer.png"));
            yellowplayer = ImageIO.read(MainMenu.class.getResource("/ttreImages/yellowplayer.png"));
            playerpointer = ImageIO.read(MainMenu.class.getResource("/ttreImages/currentplayerarrow.png"));
            ticketDeck = new TicketDeck();
//            longRoutes = new ArrayList<>();
//            longRoutes.add(new Ticket("palermo", "moskva",20,ImageIO.read(MainMenu.class.getResource("/ttreImages/longRoute1.png"))));
//            longRoutes.add(new Ticket("brest", "petrograd",20,ImageIO.read(MainMenu.class.getResource("/ttreImages/longRoute2.png"))));
//            longRoutes.add(new Ticket("kobenhavwn", "erzurum",21,ImageIO.read(MainMenu.class.getResource("/ttreImages/longRoute3.png"))));
//            longRoutes.add(new Ticket("cadiz", "sotckholm",21,ImageIO.read(MainMenu.class.getResource("/ttreImages/longRoute4.png"))));
//            longRoutes.add(new Ticket("lisboa", "danzic",20,ImageIO.read(MainMenu.class.getResource("/ttreImages/longRoute5.png"))));
//            longRoutes.add(new Ticket("edinburch", "athina",20,ImageIO.read(MainMenu.class.getResource("/ttreImages/longRoute6.png"))));

        } catch (IOException e) {
            e.printStackTrace();
        }
        currentPlr = 0;
        inAnEvent = false;
        cardDeck = new CardDeck();
        ticketDeck = new TicketDeck();
        faceUpCard = new ColorCard[5];
        clickedCity[0] = null;
        clickedCity[1] = null;
        faceUpCard[0] = cardDeck.drawCard();
        faceUpCard[1] = cardDeck.drawCard();
        faceUpCard[2] = cardDeck.drawCard();
        faceUpCard[3] = cardDeck.drawCard();
        faceUpCard[4] = cardDeck.drawCard();
        
        players = new ArrayList<Player>();
        players.add(new Player("red"));
        players.add(new Player("blue"));
        players.add(new Player("green"));
        players.add(new Player("yellow"));
//        players.get(currentPlr).addTicket(longRoutes.get(currentPlr));
        
        firstCityClicked = false;
        highlightCity = new ArrayList<Integer>();
        citiesToBuy = new City[2];
        graph = new Graph();
        graph.printCities();
        
        addMouseListener(this);
        addMouseMotionListener(this);
        setFocusable(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        g2d.drawImage(table, 0, 0, getWidth(), getHeight(), null);
        g2d.drawImage(board, 0, 0, 1500, 1040, null);
        g2d.drawImage(cardBack, 1560, 3, null);
        g2d.drawImage(template, 1675, 3, null);
        g2d.drawImage(ticket, 1795, 3, null);
        
        //rotate template cardholder and scale it smaller a bit
//        g2d.scale(0.8, 0.8);
//        g2d.rotate(Math.toRadians(-90), 1520, 240);
        for (int i = 0; i < 5; i++) {
        	g2d.drawImage(faceUpCard[i].getImage(), getWidth() - 360, 220 + i*80, 120, 80, null);
        }
        
        //reset those transformations
        g2d.setTransform(new AffineTransform());
        
        //numerical stuff
        g2d.setFont(new Font("Serif", Font.BOLD, 50)); g2d.setColor(Color.WHITE); 
        
        //red player station and trains
        g2d.drawString(players.get(0).getTrainsLeft()+"", 1758, 285);
        g2d.drawString(players.get(0).getStations()+"", 1828, 325); 
        //green player station and trains
        g2d.drawString(players.get(1).getTrainsLeft()+"", 1758, 385);
        g2d.drawString(players.get(1).getStations()+"", 1828, 420);
        //yellow player station and trains
        g2d.drawString(players.get(2).getTrainsLeft()+"", 1758, 480);
        g2d.drawString(players.get(2).getStations()+"", 1825, 520);
        //blue player station and trains
        g2d.drawString(players.get(3).getTrainsLeft()+"", 1752, 580);
        g2d.drawString(players.get(3).getStations()+"", 1822, 620);
        
        //number of trains stuff
        g2d.setFont(new Font("Serif", Font.BOLD, 30)); g2d.setColor(Color.WHITE); 
        g2d.drawString(String.valueOf(players.get(currentPlr).getCardColor("wild")), 1580, 790); // Locomotive
        g2d.drawString(String.valueOf(players.get(currentPlr).getCardColor("white")), 1645, 790); // White
        g2d.drawString(String.valueOf(players.get(currentPlr).getCardColor("red")), 1712, 790); // Red
        g2d.drawString(String.valueOf(players.get(currentPlr).getCardColor("purple")), 1777, 790); // Purple
        g2d.drawString(String.valueOf(players.get(currentPlr).getCardColor("green")), 1845, 790); // Green
        //2nd row
        g2d.drawString(String.valueOf(players.get(currentPlr).getCardColor("brown")), 1580, 930); // Brown
        g2d.drawString(String.valueOf(players.get(currentPlr).getCardColor("blue")), 1645, 930); // Blue
        g2d.drawString(String.valueOf(players.get(currentPlr).getCardColor("black")), 1712, 930); // Black
        g2d.drawString(String.valueOf(players.get(currentPlr).getCardColor("yellow")), 1777, 930); // Yellow
        g2d.drawString(players.get(currentPlr).getTicket().size()+"", 1845, 930); // Ticket
        
        //current player stuff
        
        //current player number
        g2d.drawImage(player1, 1825, 948, null);
        //current player arrow
        g2d.drawImage(playerpointer, 1875, 270, null);
        //current player's color
        g2d.drawImage(redplayer, 1520, 925, null);
        
        //Draw the station
        ArrayList<City> cityList = graph.getCities();
    	int maxSize = graph.getClickRadius();
    	for (int i = 0; i < cityList.size(); i++) {
    		if (cityList.get(i).getHasStation() != null) {
    			g2d.drawImage(redplayer, cityList.get(i).getX() - 50, cityList.get(i).getY() - 50, 100, 100, null); //Need to change image
    		}
    	}
        
        if(firstCityClicked)
        {
	        //highlight city when clicked
        	g2d.setStroke(new BasicStroke(4));
        	g2d.setColor(Color.YELLOW);
	        g2d.drawOval(highlightCity.get(0), highlightCity.get(1), 23, 23);
        }
        
        if(clickedCity[0] != null)
        {
	        //highlight city when clicked
        	g2d.setStroke(new BasicStroke(4));
        	g2d.setColor(Color.YELLOW);
	        g2d.drawOval(clickedCity[0].getX(), clickedCity[0].getY(), 22, 22);
        }
        if(clickedCity[1] != null)
        {
	        //highlight city when clicked
        	g2d.setStroke(new BasicStroke(4));
        	g2d.setColor(Color.YELLOW);
	        g2d.drawOval(clickedCity[1].getX() - 11, clickedCity[1].getY() - 11, 22, 22);
			clickedCity[0] = null;
			clickedCity[1] = null;
        }
        
        if (inAnEvent) {

//        	g2d.setPaint(new Color(0, 0, 0, 0.5f));
//        	g2d.fillRect(0, 0, getWidth(), getHeight());
        	g2d.setPaint(new Color(0, 0, 0));
        	g2d.fillRect(1500, 0, getWidth() - 1500, getHeight());
        }
    }

   
    
    @Override
    public void mouseClicked(MouseEvent e) {
    	int x = e.getX();
    	int y = e.getY();
    	System.out.println(x + " " + y);
    	if (inAnEvent) {
    		return;
        }
    	if(e.getButton() == MouseEvent.BUTTON1)// checks if the player left clicked	
    	{
    		if (x > 1275 && x < 1475 && y> 10 && y<60)
    		{
    			cycleTurn();
    		}
    		
    		//alow for players to draw cards from the face-up card options
	    	for (int i = 0; i < 5; i++) {
	    		if (x > getWidth()-360 && x < getWidth()-240 && y> 220+i*80 && y<300+i*80) {
	    			if(players.get(currentPlr).getActions() == 1 && faceUpCard[i].getColor().equals("wild") == false)
	    			{
	    				players.get(currentPlr).addCard(faceUpCard[i]);
	    				faceUpCard[i] = cardDeck.drawCard();
	    				players.get(currentPlr).setActions(2);
	    				cycleTurn();
	    			}
	    			else if(players.get(currentPlr).getActions() == 2 && faceUpCard[i].getColor().equals("wild") == false)
	    			{
	    				players.get(currentPlr).addCard(faceUpCard[i]);
	    				faceUpCard[i] = cardDeck.drawCard();
	    				players.get(currentPlr).setActions(1);
	    			}
	    			else if(players.get(currentPlr).getActions() == 2 && faceUpCard[i].getColor().equals("wild") == true)
	    			{
	    				players.get(currentPlr).addCard(faceUpCard[i]);
	    				faceUpCard[i] = cardDeck.drawCard();
	    				players.get(currentPlr).setActions(2);
	    				cycleTurn();
	    			}
	    		}
	        }
	    	
	    	//allow for players to draw cards from the pile
	    	if( x >= 1550 && x <= 1650 && y >= 3 && y <= 163)
	    	{
	    		if(players.get(currentPlr).getActions() == 1)
	    		{
	    			players.get(currentPlr).addCard(cardDeck.drawCard());
	    			players.get(currentPlr).setActions(2);
	    			cycleTurn();
	    		}
	    		else if(players.get(currentPlr).getActions() == 2)
	    		{
	    			players.get(currentPlr).addCard(cardDeck.drawCard());
	    			players.get(currentPlr).setActions(1);
	    		}
	    	}
	    	
	    	//Allow to click ticket
	        if( x >= 1795 && x <= 1895 && y >= 3 && y <= 163)
	    	{
	    		players.get(currentPlr).addTicket(ticketDeck.draw());
	    		inAnEvent = true;
	    	}
	    	
	        
	    	//allow for players to click on their tickets to check them
	    	if( x >=1820 && x <= 1885 && y >=798 && y <= 895)
	    	{
	    		out.println(players.get(currentPlr).getTicket().get(0).getToCity());
	    		out.println(players.get(currentPlr).getTicket().get(0).getFromCity());
	    		out.println(players.get(currentPlr).getTicket().get(0).getPoints());
	    	}
	    	ArrayList<City> cityList = graph.getCities();
	    	int maxSize = graph.getClickRadius();
	    	for (int i = 0; i < cityList.size(); i++) {
	    		if (x > cityList.get(i).getX() - maxSize && x < cityList.get(i).getX() + maxSize && y > cityList.get(i).getY() - maxSize && y < cityList.get(i).getY() + maxSize) {
	    			out.println("Player clicked: " + cityList.get(i).getName());
	    			if (clickedCity[0] == null) {
	    				clickedCity[0] = cityList.get(i);
	    			} else if (clickedCity[0] != cityList.get(i)) {
	    				clickedCity[1] = cityList.get(i);
		    			
		    			if (clickedCity[0] != null && clickedCity[1] != null) {
		    				out.print(clickedCity[0].getName() + " " + clickedCity[1].getName());
		    			}
	    			}
	    			break;
	    		}
	    	}
    	}
    	else if (e.getButton() == MouseEvent.BUTTON3)// this is where you write what happens if the player right clicks
    	{
    		ArrayList<City> cityList = graph.getCities();
	    	int maxSize = graph.getClickRadius();
	    	for (int i = 0; i < cityList.size(); i++) {
	    		if (x > cityList.get(i).getX() - maxSize && x < cityList.get(i).getX() + maxSize && y > cityList.get(i).getY() - maxSize && y < cityList.get(i).getY() + maxSize && cityList.get(i).getHasStation() == null && players.get(currentPlr).getStations() > 0) {
	    			out.println("Place station at: " + cityList.get(i).getName());
	    			cityList.get(i).addStation(players.get(currentPlr).useStation());
	    			cityList.get(i).getHasStation().setFromCity(cityList.get(i).getName());
	    			
//	    			out.println(cityList.get(i).getHasStation().getFromCity());
	    			break;
	    		}
	    	}
    	}
	   
    	repaint();
    }
    public void cycleTurn()
    {
 	   int currentTurn = 1;
 	   if(turns[0])
 	   {
 		   turns[0]=false;
 		   turns[1]=true;
 		   currentPlr = 1;
 	   }
 	   else if(turns[1])
 	   {
 		   turns[1]=false;
 		   turns[2]=true;
 		   currentPlr = 2;
 	   }
 	   else if(turns[2])
 	   {
 		   turns[2]=false;
 		   turns[3]=true;
 		   currentPlr = 3;
 	   }
 	   else if(turns[3])
 	   {
 		   turns[3]=false;
 		   turns[0]=true;
 		   currentPlr = 0;
 	   }
 	   for(int i = 0; i<4; i++)
 	   {
 		   if(turns[i]==true)
 			   currentTurn=i+1;
 	   }
 	   System.out.println("turn cycled: player " + currentTurn);
    }
    

    @Override
    public void mouseMoved(MouseEvent e) {
      
    }

    @Override
    public void mouseDragged(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    public void run() {}
}
