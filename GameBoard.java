package test2;

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
    private BufferedImage table, board, player1, player2, player3, player4, cardBack, ticket, template, p1bg, p2bg, p3bg, p4bg, redplayer, blueplayer, greenplayer, yellowplayer, playerpointer;
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
    
    private int currentPlr;
    private City[] clickedCity = new City[2];
    private int panelStuff = 0;
    
    private int turnUsed = 0;
    private City currentCityHovered = null;
    
	private int globalX = 0;
	private int globalY = 0;
    
    public GameBoard() {
        try {
            table = ImageIO.read(MainMenu.class.getResource("/ttreImages/gamebg.png"));
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
            players = new ArrayList<Player>();
            currentPlr = 0;
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
            players.add(new Player("red", redplayer, new Color(255,0,0)));
            players.add(new Player("orange", blueplayer, new Color(255,122,0)));
            players.add(new Player("white", greenplayer, new Color(255,255,255)));
            players.add(new Player("blue", yellowplayer, new Color(0,0,255)));
            p1bg = ImageIO.read(MainMenu.class.getResource("/ttreImages/p1bg.png"));
            p2bg = ImageIO.read(MainMenu.class.getResource("/ttreImages/p2bg.png"));
            p3bg = ImageIO.read(MainMenu.class.getResource("/ttreImages/p3bg.png"));
            p4bg = ImageIO.read(MainMenu.class.getResource("/ttreImages/p4bg.png"));
            longRoutes = new ArrayList<>();
//          longRoutes.add(new Ticket("palermo", "moskva",20,ImageIO.read(MainMenu.class.getResource("/tickets/longRoute1.png"))));
//          longRoutes.add(new Ticket("brest", "petrograd",20,ImageIO.read(MainMenu.class.getResource("/tickets/longRoute2.png"))));
//          longRoutes.add(new Ticket("kobenhavwn", "erzurum",21,ImageIO.read(MainMenu.class.getResource("/tickets/longRoute3.png"))));
//          longRoutes.add(new Ticket("cadiz", "sotckholm",21,ImageIO.read(MainMenu.class.getResource("/tickets/longRoute4.png"))));
//          longRoutes.add(new Ticket("lisboa", "danzic",20,ImageIO.read(MainMenu.class.getResource("/tickets/longRoute5.png"))));
//          longRoutes.add(new Ticket("edinburch", "athina",20,ImageIO.read(MainMenu.class.getResource("/tickets/longRoute6.png"))));

        } catch (IOException e) {
            e.printStackTrace();
        }
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
        if( currentPlr ==0 )
        {
        	g2d.drawImage(p1bg, 1420, 750, 500, 300, null);
        	g2d.drawImage(playerpointer, 1875, 225, null);
        	//g2d.drawImage(p1bg, 1546, 809, null);
        }
        else if( currentPlr ==1)
        {
        	g2d.drawImage(p2bg, 1420, 750, 500, 300, null);
        	g2d.drawImage(playerpointer, 1875, 350, null);
        	//g2d.drawImage(p2bg, 1546, 809, null);
        }
        else if(currentPlr ==2)
        {
        	g2d.drawImage(p3bg, 1420, 750, 500, 300, null);
        	g2d.drawImage(playerpointer, 1875, 450, null);
        	//g2d.drawImage(p3bg, 1546, 809, null);
        }
        else if(currentPlr ==3)
        {
        	g2d.drawImage(p4bg, 1420, 750, 500, 300, null);
        	g2d.drawImage(playerpointer, 1875, 560, null);
        	//g2d.drawImage(p4bg, 1546, 809, null);
        }
        
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
        g2d.drawString(players.get(0).getTrainsLeft()+"", 1820, 275);
        g2d.drawString(players.get(0).getStations()+"", 1820, 230); 
        //green player station and trains
        g2d.drawString(players.get(1).getTrainsLeft()+"", 1820, 375);
        g2d.drawString(players.get(1).getStations()+"", 1820, 330);
        //yellow player station and trains
        g2d.drawString(players.get(2).getTrainsLeft()+"", 1820, 475);
        g2d.drawString(players.get(2).getStations()+"", 1820, 430);
        //blue player station and trains
        g2d.drawString(players.get(3).getTrainsLeft()+"", 1820, 575);
        g2d.drawString(players.get(3).getStations()+"", 1820, 530);
        
        //number of trains stuff
        g2d.setFont(new Font("Serif", Font.BOLD, 30)); g2d.setColor(Color.WHITE); 
        g2d.drawString(String.valueOf(players.get(currentPlr).getCardColor("wild")), 1580, 762); // Locomotive
        g2d.drawString(String.valueOf(players.get(currentPlr).getCardColor("white")), 1645, 762); // White
        g2d.drawString(String.valueOf(players.get(currentPlr).getCardColor("red")), 1712, 762); // Red
        g2d.drawString(String.valueOf(players.get(currentPlr).getCardColor("purple")), 1777, 762); // Purple
        g2d.drawString(String.valueOf(players.get(currentPlr).getCardColor("green")), 1845, 762); // Green
        //2nd row
        g2d.drawString(String.valueOf(players.get(currentPlr).getCardColor("brown")), 1580, 890); // Brown
        g2d.drawString(String.valueOf(players.get(currentPlr).getCardColor("blue")), 1645, 890); // Blue
        g2d.drawString(String.valueOf(players.get(currentPlr).getCardColor("black")), 1712, 890); // Black
        g2d.drawString(String.valueOf(players.get(currentPlr).getCardColor("yellow")), 1777, 890); // Yellow
        g2d.drawString(players.get(currentPlr).getTicket().size()+"", 1845, 890	); // Ticket
        
        //Draw the station and railroads

        ArrayList<City> cityList = graph.getCities();
    	int maxSize = graph.getClickRadius();
    	for (int i = 0; i < cityList.size(); i++) {
    		// Draw station
    		if (cityList.get(i).getHasStation() != null) {
    			g2d.drawImage(cityList.get(i).getHasStation().getStationImage(), cityList.get(i).getX() - 50, cityList.get(i).getY() - 50, 100, 100, null); //Need to change image
    		}
    		// Get all connections
    		HashMap<City, ArrayList<RailRoad>> allConnections = cityList.get(i).getConnections();
    		Set<City> cityKeys = allConnections.keySet();
    		// Go through all connections
    		for (City cities: cityKeys) {
    			// Get the railroads
    			ArrayList<RailRoad> currentRailRoad = allConnections.get(cities);
    			// Get the railroad color
    			Color railRoadColor = null;
    			if (currentRailRoad.get(0).getRailRoadColor() == null) {
    				railRoadColor = new Color(255, 255, 255); //temporary color (Delete this later on to not have white)
//    				continue;
    			}
    			else {
    				railRoadColor = currentRailRoad.get(0).getRailRoadColor();
    			}
    			// Draw all the railroads
    			for (int j = 0; j < currentRailRoad.size(); j++) {
    				g2d.rotate(currentRailRoad.get(j).getDegree() + Math.toRadians(90), currentRailRoad.get(j).getX(), currentRailRoad.get(j).getY());
    				g2d.setColor(railRoadColor);
    				g2d.fillRect(currentRailRoad.get(j).getX(), currentRailRoad.get(j).getY(), graph.getRailRoadSizeX(), graph.getRailRoadSizeY());
    				g2d.setColor(new Color(0, 0, 0));
    				g2d.setStroke(new BasicStroke(2));
    				g2d.drawRect(currentRailRoad.get(j).getX(), currentRailRoad.get(j).getY(), graph.getRailRoadSizeX(), graph.getRailRoadSizeY());
    				g2d.setTransform(new AffineTransform());
    			}
    		}
    	}
    	g2d.setColor(new Color(255,255,255));
        
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
        
        if (currentCityHovered != null)
        {
        	g2d.setPaint(new Color(255, 255, 255));
        	g2d.fillRoundRect(globalX, globalY, 250, 80, 15, 15);
        	g2d.setPaint(new Color(0, 0, 0));
        	g2d.setStroke(new BasicStroke(2));
        	g2d.drawRoundRect(globalX, globalY, 250, 80, 15, 15);
        	g2d.drawString(currentCityHovered.getName(), globalX + 45, globalY + 45);
        }
        
         if (panelStuff != 0) {

//        	g2d.setPaint(new Color(0, 0, 0, 0.5f));
//        	g2d.fillRect(0, 0, getWidth(), getHeight());
        	g2d.setPaint(new Color(0, 0, 0));
        	g2d.fillRect(1500, 0, getWidth() - 1500, getHeight());
        	if (panelStuff == 1) {
        		g2d.drawImage(startticket, 1490, 3, null);
        		g2d.drawImage(player1, 1760, 3, null);
        		g2d.drawImage(sidewaytemplate, 1600, 200, null);
        		g2d.drawImage(sidewaytemplate, 1600, 400, null);
        		g2d.drawImage(sidewaytemplate, 1600, 600, null);
        		g2d.drawImage(sidewaytemplate, 1600, 800, null);
        	}
        	if (panelStuff == 2) {
        		g2d.drawImage(startticket, 1490, 3, null);
        		g2d.drawImage(player1, 1760, 3, null);
        		g2d.drawImage(ticket, 1650, 200, null);
        		g2d.drawImage(ticket, 1550, 500, null);
        		g2d.drawImage(ticket, 1750, 500, null);
        		g2d.drawImage(okbutton, 1490, 800, null);
        	}
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
	    	//alow for players to draw cards from the face-up card options
	    	for (int i = 0; i < 5; i++) {
	    		if (x > getWidth()-360 && x < getWidth()-240 && y> 220+i*80 && y<300+i*80 && turnUsed + faceUpCard[i].getCostToDraw() <= 2) {
	    			players.get(currentPlr).addCard(faceUpCard[i]);
	    			turnUsed += faceUpCard[i].getCostToDraw();
	    			faceUpCard[i] = cardDeck.drawCard();
	    		}
	        }
	    	
	    	//allow for players to draw cards from the pile
	    	if( x >= 1550 && x <= 1650 && y >= 3 && y <= 163)
	    	{
	    		players.get(currentPlr).addCard(cardDeck.drawCard());
	    		turnUsed += 1;
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
	    		if (x > cityList.get(i).getX() - maxSize && x < cityList.get(i).getX() + maxSize && y > cityList.get(i).getY() - maxSize && y < cityList.get(i).getY() + maxSize && turnUsed == 0) {
//	    			out.println("Player clicked: " + cityList.get(i).getName());
	    			if (clickedCity[0] == null) {
	    				clickedCity[0] = cityList.get(i);
	    			} else if (clickedCity[0] != cityList.get(i)) {
	    				clickedCity[1] = cityList.get(i);
		    			
		    			if (clickedCity[0] != null && clickedCity[1] != null) {
		    				buyEvent();
		    			}
	    			}
	    			break;
	    		}
	    	}
    	}
    	else if (e.getButton() == MouseEvent.BUTTON3)// this is where you write what happens if the player right clicks
    	{
    		clickedCity[0] = null;
			clickedCity[1] = null;
			if (turnUsed + 2 <= 2) {
				ArrayList<City> cityList = graph.getCities();
		    	int maxSize = graph.getClickRadius();
		    	for (int i = 0; i < cityList.size(); i++) {
		    		if (x > cityList.get(i).getX() - maxSize && x < cityList.get(i).getX() + maxSize && y > cityList.get(i).getY() - maxSize && y < cityList.get(i).getY() + maxSize && cityList.get(i).getHasStation() == null && players.get(currentPlr).getStations() > 0) {
		    			out.println("Place station at: " + cityList.get(i).getName());
		    			cityList.get(i).addStation(players.get(currentPlr).removeStation());
		    			cityList.get(i).getHasStation().setFromCity(cityList.get(i).getName());
		    			turnUsed += 2;
//		    			out.println(cityList.get(i).getHasStation().getFromCity());
		    			break;
		    		}
		    	}
			}
    	}
	   if (turnUsed >= 2) {
		   changeTurn();
	   }
	   repaint();
    }
    public void changeTurn() {
    	currentPlr += 1;
    	turnUsed = 0;
    	if (currentPlr >= players.size()) {
    		currentPlr = 0;
    	}
    }
    
    public void buyEvent() {
//    	inAnEvent = true;
//		repaint();
    	
    	ArrayList<RailRoad> neededRailRoad = graph.getCityConnection(clickedCity[0], clickedCity[1]);
		//Check if the railroad is already bought
		if (neededRailRoad != null && !neededRailRoad.get(0).getBought()) {
			//Requirements to build a railroad
			String railroadColor = neededRailRoad.get(0).getColor();
			int amountNeeded = neededRailRoad.get(0).getCost();
			boolean isMountain = neededRailRoad.get(0).getMountains();
			int wildNeeded = neededRailRoad.get(0).getWildNum();
			
			
			if (isMountain) {
				//Draw additional cards needed from deck if the railroad is a mountain (3 cards)
				for (int i = 0; i < 3; i++) {
					ColorCard draw = cardDeck.drawCard();
					if (railroadColor.equals(draw.getColor()) || draw.getColor().equals("wild")) {
						amountNeeded++;
					}
				}
			}
			int plrTotalCard = players.get(currentPlr).getCardColor(railroadColor) + players.get(currentPlr).getCardColor("wild");
			if (players.get(currentPlr).getCardColor("wild") >= wildNeeded && plrTotalCard >= amountNeeded) {
				//Buy the railroad successfully (Will add it to UI and remove from player card later)
				for (int i = 0; i < neededRailRoad.size(); i++) {
					neededRailRoad.get(i).setBought(players.get(currentPlr), players.get(currentPlr).getPlrColor());
				}
				turnUsed = 2;
				out.println("Railroad bought between: " + clickedCity[0].getName() + " and " + clickedCity[1].getName() + " is bought by the player " + players.get(currentPlr).getPlayerColor());
			} else {
				out.println("Not enough railroads");
			}
//			inAnEvent = false;
//			repaint();
		} else if (neededRailRoad == null) {
			out.println("No connection");
		} else {
			out.println("Railroad is already owned");
		}
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    	globalX = e.getX();
    	globalY = e.getY();
    	if (globalX > 1500) {
    		return;
    	}
    	ArrayList<City> cityList = graph.getCities();
    	int maxSize = graph.getClickRadius();
    	for (int i = 0; i < cityList.size(); i++) {
    		if (globalX > cityList.get(i).getX() - maxSize && globalX < cityList.get(i).getX() + maxSize && globalY > cityList.get(i).getY() - maxSize && globalY < cityList.get(i).getY() + maxSize) {
    			out.println("Hover: " + cityList.get(i).getName());
    			currentCityHovered = cityList.get(i);
//    			out.println(cityList.get(i).getHasStation().getFromCity());
    			break;
    		} else {
    			currentCityHovered = null;
    		}
    	}
		repaint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    public void run() {}
}
