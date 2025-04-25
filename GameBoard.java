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
    private BufferedImage sidewaytemplate,playerGamble ,nextButton, previousButton,table, okbutton, board, player1, player2, player3, player4, cardBack, ticket, template, p1bg, p2bg, p3bg, p4bg, startticket, redplayer, blueplayer, greenplayer, yellowplayer, playerpointer;
    private boolean isPlayButtonHovered = false;
    private boolean isRulesScrollHovered = false;
    private ColorCard[] faceUpCard;
    private CardDeck cardDeck;
    private TicketDeck ticketDeck;
    private ArrayList<Player> players;
    private City[] citiesToBuy; //Stores 2 cities for buying routes purposes
    private boolean firstCityClicked; // highlighting purposes
    private ArrayList<Integer> highlightCity; // takes in 2 locations for highlighting purposes
    private Graph graph;// stores all the coordinates of the cities in a map
    private boolean firstTicketClicked, secondTicketClicked, thirdTicketClicked, fourthTicketClicked, t1Clicked, t2Clicked, t3Clicked;
    private Ticket t1, t2, t3, t4; 
    private int ticketsClicked;
    private int tClicked;
    private int currentPlr;
    private City[] clickedCity = new City[2];
    private int panelStuff = 0; // 0 = nothing, 1 = start of game ticket, 2 = when click ticket deck, 3 = show player ticks, 4 = gambling rahhhh
    private int ticketPage = 1; // 1 = first page, 2 = seconds page, 3 = third page(max)
    private int turnUsed = 0;
    private ArrayList<Ticket> ticketsShownList;
    private int ticketsShown = 0;
    private City currentCityHovered = null;
    private int globalX = 0;
	private int globalY = 0;
	private boolean lastTurn = false;

    
    public GameBoard() {
        try {
            table = ImageIO.read(MainMenu.class.getResource("/ttreImages/gamebg.png"));
            board = ImageIO.read(MainMenu.class.getResource("/ttreImages/gameboard.png"));
            player1 = ImageIO.read(MainMenu.class.getResource("/ttreImages/player1label.png"));
            player2 = ImageIO.read(MainMenu.class.getResource("/ttreImages/player2label.png"));
            player3 = ImageIO.read(MainMenu.class.getResource("/ttreImages/player3label.png"));
            player4 = ImageIO.read(MainMenu.class.getResource("/ttreImages/player4label.png"));
            cardBack = ImageIO.read(MainMenu.class.getResource("/ttreImages/backofcard.png"));
            okbutton = ImageIO.read(MainMenu.class.getResource("/ttreImages/okButton.png"));
            ticket = ImageIO.read(MainMenu.class.getResource("/ttreImages/ticket.png"));
            template = ImageIO.read(MainMenu.class.getResource("/ttreImages/blankcardtemp.png"));
            redplayer = ImageIO.read(MainMenu.class.getResource("/ttreImages/redplayer.png"));
            blueplayer = ImageIO.read(MainMenu.class.getResource("/ttreImages/blueplayer.png"));
            greenplayer = ImageIO.read(MainMenu.class.getResource("/ttreImages/greenplayer.png"));
            yellowplayer = ImageIO.read(MainMenu.class.getResource("/ttreImages/yellowplayer.png"));
            playerpointer = ImageIO.read(MainMenu.class.getResource("/ttreImages/currentplayerarrow.png"));
            playerGamble = ImageIO.read(MainMenu.class.getResource("/ttreImages/playerGambleCards.png"));;
            ticketDeck = new TicketDeck();
            sidewaytemplate = ImageIO.read(MainMenu.class.getResource("/ttreImages/blankcardtemp2.png"));
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
            players.add(new Player("blue", blueplayer, new Color(0,0,255)));
            players.add(new Player("green", greenplayer, new Color(0,255,0)));
            players.add(new Player("yellow", yellowplayer, new Color(255,255,0)));
            p1bg = ImageIO.read(MainMenu.class.getResource("/ttreImages/p1bg.png"));
            p2bg = ImageIO.read(MainMenu.class.getResource("/ttreImages/p2bg.png"));
            p3bg = ImageIO.read(MainMenu.class.getResource("/ttreImages/p3bg.png"));
            p4bg = ImageIO.read(MainMenu.class.getResource("/ttreImages/p4bg.png"));
            startticket = ImageIO.read(MainMenu.class.getResource("/ttreImages/ticketchoose.png"));
            nextButton = ImageIO.read(MainMenu.class.getResource("/ttreImages/nextButton.png"));
            previousButton = ImageIO.read(MainMenu.class.getResource("/ttreImages/previousButton.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        firstCityClicked = false;
        highlightCity = new ArrayList<Integer>();
        citiesToBuy = new City[2];
        graph = new Graph();
        graph.printCities();
        firstTicketClicked = false;
        secondTicketClicked = false;
        thirdTicketClicked = false;
        fourthTicketClicked = false;
        t1 =  ticketDeck.drawLongTicket();
        t2 = ticketDeck.draw();
        t3 = ticketDeck.draw();
        t4 = ticketDeck.draw();
        ticketsClicked = 0;
        ticketsShownList = new ArrayList<>();
        
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
        }
        else if( currentPlr ==1)
        {
        	g2d.drawImage(p2bg, 1420, 750, 500, 300, null);
        	g2d.drawImage(playerpointer, 1875, 350, null);
        }
        else if(currentPlr ==2)
        {
        	g2d.drawImage(p3bg, 1420, 750, 500, 300, null);
        	g2d.drawImage(playerpointer, 1875, 450, null);
        }
        else if(currentPlr ==3)
        {
        	g2d.drawImage(p4bg, 1420, 750, 500, 300, null);
        	g2d.drawImage(playerpointer, 1875, 560, null);   	
        }
        
        //rotate template cardholder and scale it smaller a bit
//        g2d.scale(0.8, 0.8);
 //       g2d.rotate(Math.toRadians(-90), 1520, 240);
        for (int i = 0; i < 5; i++) {
        	g2d.drawImage(faceUpCard[i].getImage(), getWidth() - 360, 220 + i*80, 120, 80, null);
        }
        
        //reset those transformations
        g2d.setTransform(new AffineTransform());
        
        //numerical stuff
        g2d.setFont(new Font("Serif", Font.BOLD, 50)); g2d.setColor(Color.WHITE); 
        
        //red player station and trains
        g2d.drawString(players.get(0).getTrainsLeft()+"", 1798, 288);
        g2d.drawString(players.get(0).getStations()+"", 1814, 245); 
        //orange player station and trains
        g2d.drawString(players.get(1).getTrainsLeft()+"", 1798, 398);
        g2d.drawString(players.get(1).getStations()+"", 1814, 353);
        //white player station and trains
        g2d.drawString(players.get(2).getTrainsLeft()+"", 1798, 504);
        g2d.drawString(players.get(2).getStations()+"", 1814, 462);
        //blue player station and trains
        g2d.drawString(players.get(3).getTrainsLeft()+"", 1798, 615);
        g2d.drawString(players.get(3).getStations()+"", 1814, 570);
        
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
    				continue;
    			}
    			else {
    				railRoadColor = currentRailRoad.get(0).getRailRoadColor();
    			}
    			// Draw all the railroads
    			for (int j = 0; j < currentRailRoad.size(); j++) {
    				g2d.rotate(currentRailRoad.get(j).getDegree(), currentRailRoad.get(j).getX(), currentRailRoad.get(j).getY());
    				g2d.setColor(railRoadColor);
    				g2d.fillRect(currentRailRoad.get(j).getX(), currentRailRoad.get(j).getY(), graph.getRailRoadSizeX(), graph.getRailRoadSizeY());
    				g2d.setColor(new Color(0, 0, 0));
    				g2d.setStroke(new BasicStroke(2));
    				g2d.drawRect(currentRailRoad.get(j).getX(), currentRailRoad.get(j).getY(), graph.getRailRoadSizeX(), graph.getRailRoadSizeY());
    				g2d.setTransform(new AffineTransform());
    			}
    			
    		}
    		allConnections = cityList.get(i).getSecondConnections();
    		cityKeys = allConnections.keySet();
    		// Go through all connections
    		for (City cities: cityKeys) {
    			// Get the railroads
    			ArrayList<RailRoad> currentRailRoad = allConnections.get(cities);
    			// Get the railroad color
    			Color railRoadColor = null;
    			if (currentRailRoad.get(0).getRailRoadColor() == null) {
    				continue;
    			}
    			else {
    				railRoadColor = currentRailRoad.get(0).getRailRoadColor();
    			}
    			// Draw all the railroads
    			for (int j = 0; j < currentRailRoad.size(); j++) {
    				g2d.rotate(currentRailRoad.get(j).getDegree(), currentRailRoad.get(j).getX(), currentRailRoad.get(j).getY());
    				g2d.setColor(railRoadColor);
    				g2d.fillRect(currentRailRoad.get(j).getX(), currentRailRoad.get(j).getY(), graph.getRailRoadSizeX(), graph.getRailRoadSizeY());
    				g2d.setColor(new Color(0, 0, 0));
    				g2d.setStroke(new BasicStroke(2));
    				g2d.drawRect(currentRailRoad.get(j).getX(), currentRailRoad.get(j).getY(), graph.getRailRoadSizeX(), graph.getRailRoadSizeY());
    				g2d.setTransform(new AffineTransform());
    			}
    		}
    		// Draw station
    		if (cityList.get(i).getHasStation() != null) {
    			g2d.drawImage(cityList.get(i).getHasStation().getStationImage(), cityList.get(i).getX() - 50, cityList.get(i).getY() - 50, 100, 100, null); //Need to change image
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
	        g2d.drawOval(clickedCity[1].getX(), clickedCity[1].getY(), 22, 22);
			clickedCity[0] = null;
			clickedCity[1] = null;
        }
        
       
        
        if (panelStuff != 0) {

//        	g2d.setPaint(new Color(0, 0, 0, 0.5f));
//        	g2d.fillRect(0, 0, getWidth(), getHeight());
        	g2d.setPaint(new Color(0, 0, 0));
        	g2d.fillRect(1500, 0, getWidth() - 1500, getHeight());
        	if (panelStuff == 1) {
        		
        		g2d.drawImage(startticket, 1490, 3, null);
        		if(currentPlr == 0)
        			g2d.drawImage(player1, 1760, 3, null);
        		if(currentPlr == 1)
        			g2d.drawImage(player2, 1760, 3, null);
        		if(currentPlr == 2)
        			g2d.drawImage(player3, 1760, 3, null);
        		if(currentPlr == 3)
        			g2d.drawImage(player4, 1760, 3, null);
        		
        		g2d.drawImage(t1.getImage(), 1540, 150, 300, 150, null);
        		g2d.drawImage(t2.getImage(), 1540, 350, 300, 150, null);
        		g2d.drawImage(t3.getImage(), 1540, 550, 300, 150, null);
        		g2d.drawImage(t4.getImage(), 1540, 750, 300, 150, null);
        		g2d.drawImage(okbutton, 1490, 900, null);
        	}
        	if (panelStuff == 2) {
        		g2d.drawImage(startticket, 1490, 3, null);
        		g2d.drawImage(player1, 1760, 3, null);
        		g2d.drawImage(t2.getImage(), 1540, 200, 300, 150, null);
        		g2d.drawImage(t3.getImage(), 1540, 400, 300, 150, null);
        		g2d.drawImage(t4.getImage(), 1540, 600, 300, 150, null);
        		g2d.drawImage(okbutton, 1490, 800, null);
        	}
        	
        	
        	if (panelStuff == 3) {
        		g2d.drawImage( nextButton, 1700, 870, 50,50,null );
        		g2d.drawImage( previousButton, 1600, 870, 50,50,null );
        		g2d.drawImage(player1, 1760, 3, null);
    			for(int i= 0; i<4;i++)
    			{
    				g2d.drawImage(ticketsShownList.get(i).getImage(), 1540, 100 + (200*i), 300, 150, null);
    			}
        		g2d.drawImage(okbutton, 1490, 900, null);
        		g2d.setFont(new Font("Serif", Font.BOLD, 70));
        		g2d.setColor(Color.WHITE);
        		g2d.drawString("Page", 1570, 80);
        		g2d.setFont(new Font("Serif", Font.BOLD, 70));
        		g2d.setColor(Color.WHITE);
        		g2d.drawString(ticketPage + "", 1770, 80);
        	}
        	if(panelStuff == 4) {
         		g2d.drawImage(sidewaytemplate, 1540, 50, 200, 100, null);
         		g2d.drawImage(sidewaytemplate, 1540, 200, 200, 100, null);
         		g2d.drawImage(sidewaytemplate, 1540, 350, 200, 100, null);
         		g2d.setColor(Color.WHITE);
         		g2d.drawString("Choose color to buy station", 1510, 550);
         		g2d.drawImage(playerGamble, 1510, 630, null);
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
         		g2d.setColor(Color.GREEN);
                 g2d.fillRect(1510, 970, 170, 50);
                 g2d.setColor(Color.BLACK);
                 g2d.drawString("Buy", 1520, 1000);
                 
                 g2d.setColor(Color.RED);
                 g2d.fillRect(1710, 970, 170, 50);
                 g2d.setColor(Color.BLACK);
                 g2d.drawString("Decline", 1720, 1000);
         	}
        }
        //highlights the ticket clicked 
        if(firstTicketClicked)
        {
	       
        	g2d.setStroke(new BasicStroke(6));
        	g2d.setColor(Color.YELLOW);
	        g2d.drawRect(1541, 151, 300, 150);
        }
        if(secondTicketClicked)
        {
	        //highlight city when clicked
        	g2d.setStroke(new BasicStroke(6));
        	g2d.setColor(Color.YELLOW);
	        g2d.drawRect(1541, 351, 300, 150);
        }
        if(thirdTicketClicked)
        {
	        //highlight city when clicked
        	g2d.setStroke(new BasicStroke(6));
        	g2d.setColor(Color.YELLOW);
	        g2d.drawRect(1541, 551, 300, 150);
        }
        if(fourthTicketClicked)
        {
	        //highlight city when clicked
        	g2d.setStroke(new BasicStroke(6));
        	g2d.setColor(Color.YELLOW);
	        g2d.drawRect(1541, 751, 300, 150);
        }
        if(t1Clicked)
        {
	        //highlight city when clicked
        	g2d.setStroke(new BasicStroke(6));
        	g2d.setColor(Color.YELLOW);
	        g2d.drawRect(1541, 200, 300, 150);
        }
        if(t2Clicked)
        {
	        //highlight city when clicked
        	g2d.setStroke(new BasicStroke(6));
        	g2d.setColor(Color.YELLOW);
	        g2d.drawRect(1541, 400, 300, 150);
        }
        if(t3Clicked)
        {
	        //highlight city when clicked
        	g2d.setStroke(new BasicStroke(6));
        	g2d.setColor(Color.YELLOW);
	        g2d.drawRect(1541, 600, 300, 150);
        }
        
        for (int i = 0; i < players.size(); i++) {
        	Player currentPlayer = players.get(i);
        	int pt = currentPlayer.getPoint()/2;
        	g2d.setPaint(currentPlayer.getPlrColor());
        	int reset = (pt + players.get(i).getStations() * 4) % 100;
        	if (reset <= 20) {
        		g2d.fillOval(32, 960 - (reset * 46), 30, 30);
            	g2d.setPaint(new Color(0, 0, 0));
            	g2d.setStroke(new BasicStroke(2));
            	g2d.drawOval(32, 960 - (reset * 46), 30, 30);
        	}
        	else if (reset <= 50) {
        		g2d.fillOval(32 + ((reset-20) * 47), 48, 30, 30);
            	g2d.setPaint(new Color(0, 0, 0));
            	g2d.setStroke(new BasicStroke(2));
            	g2d.drawOval(32 + ((reset-20) * 47), 48, 30, 30);
        	}
        	else if (reset <= 70) {
        		g2d.fillOval(1447, 61 + ((reset-50) * 46), 30, 30);
            	g2d.setPaint(new Color(0, 0, 0));
            	g2d.setStroke(new BasicStroke(2));
            	g2d.drawOval(1447, 61 + ((reset-50) * 46), 30, 30);
        	}
        	else {
        		g2d.fillOval(1447 - ((reset-70) * 47), 960, 30, 30);
            	g2d.setPaint(new Color(0, 0, 0));
            	g2d.setStroke(new BasicStroke(2));
            	g2d.drawOval(1447 - ((reset-70) * 47), 960, 30, 30);
        	}
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
        
        
    }

   
    
    @Override
    public void mouseClicked(MouseEvent e) {
    	int x = e.getX();
    	int y = e.getY();
    	System.out.println(x + " " + y);
    	
    	if(e.getButton() == MouseEvent.BUTTON1)// checks if the player left clicked	
    	{
    		if (panelStuff == 3) {
	    		if (x >= 1510 && x<= 1880 && y>= 915 && y <= 1000) {
	    			panelStuff = 0;
	    		}
    		}
	    	//alow for players to draw cards from the face-up card options
	    	for (int i = 0; i < 5; i++) {
	    		if (x > getWidth()-360 && x < getWidth()-240 && y> 220+i*80 && y<300+i*80 && turnUsed + faceUpCard[i].getCostToDraw() <= 2 && panelStuff == 0) {
	    			players.get(currentPlr).addCard(faceUpCard[i]);
	    			turnUsed += faceUpCard[i].getCostToDraw();
	    			faceUpCard[i] = cardDeck.drawCard();
	    		}
	        }
	    	
	    	//allow for players to draw cards from the pile
	    	if( x >= 1550 && x <= 1650 && y >= 3 && y <= 163 && panelStuff == 0)
	    	{
	    		players.get(currentPlr).addCard(cardDeck.drawCard());
	    		turnUsed += 1;
	    	}
	    	
	    	//Allow to click ticket
	        if( x >= 1795 && x <= 1895 && y >= 3 && y <= 163 && panelStuff == 0)
	    	{
	            t2 = ticketDeck.draw();
	            t3 = ticketDeck.draw();
	            t4 = ticketDeck.draw();
	    		panelStuff = 2;
	    	}
	        
	        //ticket 1 mid game draw
	        if( x >= 1540 && x <= 1840 && y >= 200 && y <= 350 && panelStuff == 2)
	        {
	        	if( !t1Clicked )
	        	{
	        		t1Clicked = true;
	        		tClicked++;
	        	}
	        	else
	        	{
	        		t1Clicked = false;
	        		tClicked--;
	        	}
	        	System.out.println("1");
	        }
	        
	      //ticket 2 mid game draw
	        if( x >= 1540 && x <= 1840 && y >= 400 && y <= 550 && panelStuff == 2)
	        {
	        	if( !t2Clicked )
	        	{
	        		t2Clicked = true;
	        		tClicked++;
	        	}
	        	else
	        	{
	        		t2Clicked = false;
	        		tClicked--;
	        	}
	        	System.out.println("1");
	        }
	        
	      //ticket 3 mid game draw
	        if( x >= 1540 && x <= 1840 && y >= 600 && y <= 750 && panelStuff == 2)
	        {
	        	if( !t3Clicked )
	        	{
	        		t3Clicked = true;
	        		tClicked++;
	        	}
	        	else
	        	{
	        		t3Clicked = false;
	        		tClicked--;
	        	}
	        	System.out.println("1");
	        }
	    	
	        //ticket 1 during start phase 
	        if( x >= 1540 && x <= 1840 && y >= 150 && y <= 300 && panelStuff == 1)
	        {
	        	if( !firstTicketClicked )
	        	{
	        		firstTicketClicked = true;
	        		ticketsClicked++;
	        	}
	        	else
	        	{
	        		firstTicketClicked = false;
	        		ticketsClicked--;
	        	}
	        	System.out.println("1");
	        }
	        
	      //ticket 2 during start phase 
	        if( x >= 1540 && x <= 1840 && y >= 350 && y <= 500 && panelStuff == 1)
	        {
	        	if( !secondTicketClicked )
	        	{
	        		secondTicketClicked = true;
	        		ticketsClicked++;
	        	}
	        	else
	        	{
	        		secondTicketClicked = false;
	        		ticketsClicked--;
	        	}
	        	System.out.println("2");
	        }
	        
	      //ticket 3 during start phase 
	        if( x >= 1540 && x <= 1840 && y >= 550 && y <= 700 && panelStuff == 1 )
	        {
	        	if( !thirdTicketClicked )
	        	{
	        		thirdTicketClicked = true;
	        		ticketsClicked++;
	        	}
	        	else
	        	{
	        		thirdTicketClicked = false;
	        		ticketsClicked--;
	        	}
	        	System.out.println("3");
	        }
	        
	      //ticket 4 during start phase 
	        if( x >= 1540 && x <= 1840 && y >= 750 && y <= 900 && panelStuff == 1)
	        {
	        	
	        	if( !fourthTicketClicked )
	        	{
	        		fourthTicketClicked = true;
	        		ticketsClicked++;
	        	}
	        	else
	        	{
	        		fourthTicketClicked = false;
	        		ticketsClicked--;
	        	}
	        	System.out.println("4");
	        }
	        
	      //ok button during start phase 
	        if( x >= 1540 && x <= 1840 && y >= 900 && y <= 1000 && panelStuff == 1 && ticketsClicked>=2 )
	        {
	        	if( firstTicketClicked)
	        	{
	        		players.get(currentPlr).addTicket(t1);;
	        	}
	        	if( secondTicketClicked)
	        	{
	        		players.get(currentPlr).addTicket(t2);;
	        	}
	        	if( thirdTicketClicked)
	        	{
	        		players.get(currentPlr).addTicket(t3);;
	        	}
	        	if( fourthTicketClicked)
	        	{
	        		players.get(currentPlr).addTicket(t4);;
	        	}
	        	
	        	out.println(players.get(0).getTicket().size());
	        	out.println(players.get(1).getTicket().size());
	        	out.println(players.get(2).getTicket().size());
	        	out.println(players.get(3).getTicket().size());
	        	firstTicketClicked = false;
	        	secondTicketClicked = false;
	        	thirdTicketClicked = false;
	        	fourthTicketClicked = false;
	        	t1 = ticketDeck.drawLongTicket();
		        t2 = ticketDeck.draw();
		        t3 = ticketDeck.draw();
		        t4 = ticketDeck.draw();
	        	System.out.println("ok");
	        	if( currentPlr == 3 )
	        	{
	        		panelStuff = 0;
	        	}
	        	ticketsClicked = 0;
	        	changeTurn();
	        }
	        
	      //ok button mid game draw 
	        if( x >= 1510 && x <= 1880 && y >= 820 && y <= 900 && panelStuff == 2 && tClicked>=1 )
	        {
	        	if( t1Clicked)
	        	{
	        		players.get(currentPlr).addTicket(t2);;
	        	}
	        	if( t2Clicked)
	        	{
	        		players.get(currentPlr).addTicket(t3);;
	        	}
	        	if( t3Clicked)
	        	{
	        		players.get(currentPlr).addTicket(t4);;
	        	}
	        	out.println(players.get(0).getTicket().size());
	        	out.println(players.get(1).getTicket().size());
	        	out.println(players.get(2).getTicket().size());
	        	out.println(players.get(3).getTicket().size());
	        	t1Clicked = false;
	        	t2Clicked = false;
	        	t3Clicked = false;
		        t2 = ticketDeck.draw();
		        t3 = ticketDeck.draw();
		        t4 = ticketDeck.draw();
	        	System.out.println("ok");
	        	panelStuff = 0;
	        	ticketsClicked = 0;
	        	changeTurn();
	        }
	        
	    	//allow for players to click on their tickets to check them
	    	if( x >=1820 && x <= 1885 && y >=767 && y <= 864 && panelStuff == 0)
	    	{
	    		panelStuff = 3;
	    	}
	    	
	    	// allows for player to go through their ticket pages
	    	// previous button
	    	if (x >=1600 && x <= 1650 && y >=870 && y <= 920 && panelStuff == 3)
	    	{
	    		if( ticketPage == 2 )
	    		{
	    			ticketsShownList = new ArrayList<>();
    				if( players.get(currentPlr).getTicket().size() > 0 )
    					ticketsShownList.add( players.get(currentPlr).getTicket().get(0) );
    				else
    					ticketsShownList.add( new Ticket( "", "", 0, new BufferedImage(1,1,1)) );
    				//-------------------------------------------------------------------------------------
    				if( players.get(currentPlr).getTicket().size() > 1 )
    					ticketsShownList.add( players.get(currentPlr).getTicket().get(1) );
    				else
    					ticketsShownList.add( new Ticket( "", "", 0, new BufferedImage(1,1,1)) );
    				//-------------------------------------------------------------------------------------
    				if( players.get(currentPlr).getTicket().size() > 2 )
    					ticketsShownList.add( players.get(currentPlr).getTicket().get(2) );
    				else
    					ticketsShownList.add( new Ticket( "", "", 0, new BufferedImage(1,1,1)) );
    				//-------------------------------------------------------------------------------------
    				if( players.get(currentPlr).getTicket().size() > 3 )
    					ticketsShownList.add( players.get(currentPlr).getTicket().get(3) );
    				else
    					ticketsShownList.add( new Ticket( "", "", 0, new BufferedImage(1,1,1)) );
	    			ticketPage--;
	    			
	    		}
	    		else if( ticketPage == 3)
	    		{
	    			ticketsShownList = new ArrayList<>();
    				if( players.get(currentPlr).getTicket().size() > 4 )
    					ticketsShownList.add( players.get(currentPlr).getTicket().get(4) );
    				else
    					ticketsShownList.add( new Ticket( "", "", 0, new BufferedImage(1,1,1)) );
    				//-------------------------------------------------------------------------------------
    				if( players.get(currentPlr).getTicket().size() > 5 )
    					ticketsShownList.add( players.get(currentPlr).getTicket().get(5) );
    				else
    					ticketsShownList.add( new Ticket( "", "", 0, new BufferedImage(1,1,1)) );
    				//-------------------------------------------------------------------------------------
    				if( players.get(currentPlr).getTicket().size() > 6 )
    					ticketsShownList.add( players.get(currentPlr).getTicket().get(6) );
    				else
    					ticketsShownList.add( new Ticket( "", "", 0, new BufferedImage(1,1,1)) );
    				//-------------------------------------------------------------------------------------
    				if( players.get(currentPlr).getTicket().size() > 7 )
    					ticketsShownList.add( players.get(currentPlr).getTicket().get(7) );
    				else
    					ticketsShownList.add( new Ticket( "", "", 0, new BufferedImage(1,1,1)) );
	    			ticketPage--;
	    			
	    		}
	    		
	    	}
	    	// next button
	    	if (x >=1700 && x <= 1750 && y >=870 && y <= 920 && panelStuff == 3)
	    	{
	    		if( ticketPage == 1)
	    		{
	    			ticketsShownList = new ArrayList<>();
    				if( players.get(currentPlr).getTicket().size() > 4 )
    					ticketsShownList.add( players.get(currentPlr).getTicket().get(4) );
    				else
    					ticketsShownList.add( new Ticket( "", "", 0, new BufferedImage(1,1,1)) );
    				//-------------------------------------------------------------------------------------
    				if( players.get(currentPlr).getTicket().size() > 5 )
    					ticketsShownList.add( players.get(currentPlr).getTicket().get(5) );
    				else
    					ticketsShownList.add( new Ticket( "", "", 0, new BufferedImage(1,1,1)) );
    				//-------------------------------------------------------------------------------------
    				if( players.get(currentPlr).getTicket().size() > 6 )
    					ticketsShownList.add( players.get(currentPlr).getTicket().get(6) );
    				else
    					ticketsShownList.add( new Ticket( "", "", 0, new BufferedImage(1,1,1)) );
    				//-------------------------------------------------------------------------------------
    				if( players.get(currentPlr).getTicket().size() > 7 )
    					ticketsShownList.add( players.get(currentPlr).getTicket().get(7) );
    				else
    					ticketsShownList.add( new Ticket( "", "", 0, new BufferedImage(1,1,1)) );
	    			ticketPage++;

	    		}
	    		else if( ticketPage == 2)
	    		{
    				ticketsShownList = new ArrayList<>();
    				if( players.get(currentPlr).getTicket().size() > 8 )
    					ticketsShownList.add( players.get(currentPlr).getTicket().get(8) );
    				else
    					ticketsShownList.add( new Ticket( "", "", 0, new BufferedImage(1,1,1)) );
    				//-------------------------------------------------------------------------------------
    				if( players.get(currentPlr).getTicket().size() > 9 )
    					ticketsShownList.add( players.get(currentPlr).getTicket().get(9) );
    				else
    					ticketsShownList.add( new Ticket( "", "", 0, new BufferedImage(1,1,1)) );
    				//-------------------------------------------------------------------------------------
    				if( players.get(currentPlr).getTicket().size() > 10 )
    					ticketsShownList.add( players.get(currentPlr).getTicket().get(10) );
    				else
    					ticketsShownList.add( new Ticket( "", "", 0, new BufferedImage(1,1,1)) );
    				//-------------------------------------------------------------------------------------
    				if( players.get(currentPlr).getTicket().size() > 11 )
    					ticketsShownList.add( players.get(currentPlr).getTicket().get(11) );
    				else
    					ticketsShownList.add( new Ticket( "", "", 0, new BufferedImage(1,1,1)) );
	    			ticketPage++;
	    			
	    		}
	    	}
	    	
	    	ArrayList<City> cityList = graph.getCities();
	    	int maxSize = graph.getClickRadius();
	    	for (int i = 0; i < cityList.size(); i++) {
	    		if (x > cityList.get(i).getX() - maxSize && x < cityList.get(i).getX() + maxSize && y > cityList.get(i).getY() - maxSize && y < cityList.get(i).getY() + maxSize && panelStuff == 0) {
	    			out.println("Player clicked: " + cityList.get(i).getName());
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
	   if (turnUsed >= 2)
	   {
		   changeTurn();
	   }
    	repaint();
    }
    public void changeTurn() {
    	if (lastTurn == true) {
    		players.get(currentPlr).setLastTurn();
    	}
    	currentPlr += 1;
    	turnUsed = 0;
    	if (currentPlr >= players.size()) {
    		currentPlr = 0;
    	}
    	out.println("PanelStuff = " + panelStuff);
    	if( panelStuff !=1)
    	{
    		ticketsShownList = new ArrayList<>();
    		if( players.get(currentPlr).getTicket().size() > 0 )
				ticketsShownList.add( players.get(currentPlr).getTicket().get(0) );
			else
				ticketsShownList.add( new Ticket( "", "", 0, new BufferedImage(1,1,1)) );
			//-------------------------------------------------------------------------------------
			if( players.get(currentPlr).getTicket().size() > 1 )
				ticketsShownList.add( players.get(currentPlr).getTicket().get(1) );
			else
				ticketsShownList.add( new Ticket( "", "", 0, new BufferedImage(1,1,1)) );
			//-------------------------------------------------------------------------------------
			if( players.get(currentPlr).getTicket().size() > 2 )
				ticketsShownList.add( players.get(currentPlr).getTicket().get(2) );
			else
				ticketsShownList.add( new Ticket( "", "", 0, new BufferedImage(1,1,1)) );
			//-------------------------------------------------------------------------------------
			if( players.get(currentPlr).getTicket().size() > 3 )
				ticketsShownList.add( players.get(currentPlr).getTicket().get(3) );
			else
				ticketsShownList.add( new Ticket( "", "", 0, new BufferedImage(1,1,1)) );
    	}
    	if (players.get(currentPlr).getLastTurn() == true) {
    		
    		out.println("End Game");
    	}
    }
    
    
    
    public void buyEvent() {
//    	inAnEvent = true;
//		repaint();
    	
    	ArrayList<RailRoad> neededRailRoad = graph.getCityConnection(clickedCity[0], clickedCity[1]);
    	ArrayList<RailRoad> neededRailRoad2 = graph.getCitySecondConnection(clickedCity[0], clickedCity[1]);
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
			if (railroadColor == null) {
				plrTotalCard = players.get(currentPlr).getHighestColorNum();
			}
			if (players.get(currentPlr).getCardColor("wild") >= wildNeeded && plrTotalCard >= amountNeeded) {
				//Buy the railroad successfully (Will add it to UI and remove from player card later)
				for (int i = 0; i < neededRailRoad.size(); i++) {
					neededRailRoad.get(i).setBought(players.get(currentPlr), players.get(currentPlr).getPlrColor());
				}
				turnUsed = 2;
				players.get(currentPlr).setPoint(graph.getPlayerTrainPoint(players.get(currentPlr)));
				players.get(currentPlr).removeTrain(amountNeeded);
				out.println("Railroad bought between: " + clickedCity[0].getName() + " and " + clickedCity[1].getName() + " is bought by the player " + players.get(currentPlr).getPlayerColor());
			} else {
				out.println("Not enough railroads");
			}
//			inAnEvent = false;
//			repaint();
		} else if (neededRailRoad2 != null && !neededRailRoad2.get(0).getBought()) {
			//Requirements to build a railroad
			String railroadColor = neededRailRoad2.get(0).getColor();
			int amountNeeded = neededRailRoad2.get(0).getCost();
			boolean isMountain = neededRailRoad2.get(0).getMountains();
			int wildNeeded = neededRailRoad2.get(0).getWildNum();
			
			
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
			if (railroadColor == null) {
				plrTotalCard = players.get(currentPlr).getHighestColorNum();
			}
			if (players.get(currentPlr).getCardColor("wild") >= wildNeeded && plrTotalCard >= amountNeeded) {
				//Buy the railroad successfully (Will add it to UI and remove from player card later)
				for (int i = 0; i < neededRailRoad2.size(); i++) {
					neededRailRoad2.get(i).setBought(players.get(currentPlr), players.get(currentPlr).getPlrColor());
				}
				turnUsed = 2;
				players.get(currentPlr).setPoint(graph.getPlayerTrainPoint(players.get(currentPlr)));
				players.get(currentPlr).removeTrain(amountNeeded);
				out.println("Railroad bought between: " + clickedCity[0].getName() + " and " + clickedCity[1].getName() + " is bought by the player " + players.get(currentPlr).getPlayerColor());
			} else {
				out.println("Not enough railroads");
			}
//			inAnEvent = false;
//			repaint();
		} else if (neededRailRoad == null && neededRailRoad2 == null) {
			out.println("No connection");
		} else {
			out.println("Railroad is already owned");
		}
		
		if (players.get(currentPlr).getTrainsLeft() <= 2) {
			out.println("Last turn");
			lastTurn = true;
		}
		repaint();
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
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    public void run() {}
}
