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
    private BufferedImage sidewaytemplate, table, okbutton, board, player1, player2, player3, player4, cardBack, ticket, template, p1bg, p2bg, p3bg, p4bg, startticket, redplayer, blueplayer, greenplayer, yellowplayer, playerpointer;
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
    private int panelStuff = 1; // 0 = nothing, 1 = start of game ticket, 2 = when click ticket deck
    
    private int turnUsed = 0;
    
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
        } catch (IOException e) {
            e.printStackTrace();
        }
//        players.get(currentPlr).addTicket(longRoutes.get(currentPlr));
        
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
    		HashMap<City, ArrayList<RailRoad>> allSecondConnections = cityList.get(i).getSecondConnections();
    		Set<City> cityKeys = allConnections.keySet();
    		Set<City> citySecondKeys = allSecondConnections.keySet();
    		// Go through all connections
    		for (City cities: cityKeys) {
    			// Get the railroads
    			ArrayList<RailRoad> currentRailRoad = allConnections.get(cities);
    			// Get the railroad color
    			Color railRoadColor = null;
    			if (currentRailRoad.get(0).getRailRoadColor() == null) {
    				railRoadColor = new Color(255, 255, 255);
    			} else {
    				railRoadColor = currentRailRoad.get(0).getRailRoadColor();
    			}
    			g2d.setColor(railRoadColor);
    			// Draw all the railroads
    			for (int j = 0; j < currentRailRoad.size(); j++) {
    				
    				if(currentRailRoad.get(j).getDegree() != Math.toRadians(20))
    				{
    				g2d.rotate( currentRailRoad.get(j).getDegree() , currentRailRoad.get(j).getX(), currentRailRoad.get(j).getY());
    				g2d.fillRect(currentRailRoad.get(j).getX(), currentRailRoad.get(j).getY(), graph.getRailRoadSizeX(), graph.getRailRoadSizeY());
    				g2d.setTransform(new AffineTransform());
    				}
    				
    			}
    		}
    		for (City cities: citySecondKeys) {
    			// Get the railroads
    			ArrayList<RailRoad> currentRailRoad = allSecondConnections.get(cities);
    			// Get the railroad color
    			Color railRoadColor = null;
    			if (currentRailRoad.get(0).getRailRoadColor() == null) {
    				railRoadColor = new Color(255, 255, 255);
    			} else {
    				railRoadColor = currentRailRoad.get(0).getRailRoadColor();
    			}
    			g2d.setColor(railRoadColor);
    			// Draw all the railroads
    			for (int j = 0; j < currentRailRoad.size(); j++) {
    				
    				if(currentRailRoad.get(j).getDegree() != Math.toRadians(20))
    				{
    				g2d.rotate( currentRailRoad.get(j).getDegree() , currentRailRoad.get(j).getX(), currentRailRoad.get(j).getY());
    				g2d.fillRect(currentRailRoad.get(j).getX(), currentRailRoad.get(j).getY(), graph.getRailRoadSizeX(), graph.getRailRoadSizeY());
    				g2d.setTransform(new AffineTransform());
    				}
    				
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
        		g2d.drawImage(player1, 1760, 3, null);
    			for(int i= 0; i<players.get(currentPlr).getTicket().size();i++)
    			{
    				g2d.drawImage(players.get(currentPlr).getTicket().get(i).getImage(), 1540, 200 + (200*i), 300, 150, null);
    			}
        		g2d.drawImage(okbutton, 1490, 800, null);
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
    }

   
    
    @Override
    public void mouseClicked(MouseEvent e) {
    	int x = e.getX();
    	int y = e.getY();
    	System.out.println(x + " " + y);
    	
    	if(e.getButton() == MouseEvent.BUTTON1)// checks if the player left clicked	
    	{
    		
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
	            t2 = ticketDeck.draw();
	            t3 = ticketDeck.draw();
	            t4 = ticketDeck.draw();
	    		players.get(currentPlr).addCard(cardDeck.drawCard());
	    		turnUsed += 1;
	    	}
	    	
	    	//Allow to click ticket
	        if( x >= 1795 && x <= 1895 && y >= 3 && y <= 163 && panelStuff == 0)
	    	{
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
	        	t1 =ticketDeck.drawLongTicket();
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
	    	ArrayList<City> cityList = graph.getCities();
	    	int maxSize = graph.getClickRadius();
	    	for (int i = 0; i < cityList.size(); i++) {
	    		if (x > cityList.get(i).getX() - maxSize && x < cityList.get(i).getX() + maxSize && y > cityList.get(i).getY() - maxSize && y < cityList.get(i).getY() + maxSize && panelStuff == 0) {
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
		   	t1 =ticketDeck.drawLongTicket();
	         t2 = ticketDeck.draw();
	         t3 = ticketDeck.draw();
	         t4 = ticketDeck.draw();
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
    
    public void initiateCoords()
    {
    	//allow for player to click on a 2 cities to buy them
    	//coordinates for each point
    	//frankfurt: 568 <= x && x <= 603 && 433 <= y && y <= 462
    	//brest: 341 <= x && x <= 366 && 313 <= y && y <= 334
    	//essen: 584 <= x && x <= 609 && 332 <= y && y <= 353
    	//berlin: 722 <= x && x <= 747 && 350 <= y && y <= 371
    	//wien: 801 <= x && x <= 826 && 510 <= y && y <= 531
    	//zurich: 552 <= x && x <= 577 && 573 <= y && y <= 594
    	//marseille: 513 <= x && x <= 538 && 703 <= y && y <= 724
    	//roma: 673 <= x && x <= 698 && 747 <= y && y <= 768
    	//brindisi: 790 <= x && x <= 815 && 781 <= y && y <= 802
    	//sofia: 987 <= x && x <= 1012 && 729 <= y && y <= 750
    	//kobenhavn: 678 <= x && x <= 703 && 190 <= y && y <= 211
    	//amsterdam: 472 <= x && x <= 497 && 319 <= y && y <= 340
    	//dieppe: 322 <= x && x <= 347 && 438 <= y && y <= 459
    	//paris: 390 <= x && x <= 415 && 499 <= y && y <= 520
    	//bruxelles: 439 <= x && x <= 464 && 382 <= y && y <= 403
    	//munchen: 641 <= x && x <= 666 && 485 <= y && y <= 506
    	//athina: 967 <= x && x <= 992 && 885 <= y && y <= 906
    	//madrid: 169 <= x && x <= 194 && 816 <= y && y <= 847
    	//cadiz: 167 <= x && x <= 192 && 923 <= y && y <= 944
    	//barcelona: 321 <= x && x <= 346 && 842 <= y && y <= 863
    	//pamplona: 304 <= x && x <= 329 && 712 <= y && y <= 733
    	//constantinople: 1143 <= x && x <= 1168 && 815 <= y && y <= 836
    	//budapest: 860 <= x && x <= 885 && 550 <= y && y <= 571
    	//bucuresti: 1073 <= x && x <= 1098 && 644 <= y && y <= 665
    	//sevastopol: 1264 <= x && x <= 1289 && 662 <= y && y <= 683
    	//kharkov: 1336 <= x && x <= 1361 && 486 <= y && y <= 507
    	//kyiv: 1153 <= x && x <= 1178 && 416 <= y && y <= 437
    	//moskva: 1360 <= x && x <= 1385 && 278 <= y && y <= 299
    	//petrograd: 1227 <= x && x <= 1252 && 112 <= y && y <= 133
    	//rica: 994 <= x && x <= 1019 && 125 <= y && y <= 146
    	//danzic: 875 <= x && x <= 900 && 246 <= y && y <= 267
    	//edinburgh: 248 <= x && x <= 273 && 111 <= y && y <= 132
    	//lisboa: 73 <= x && x <= 98 && 856 <= y && y <= 877
    	//zagrab: 784 <= x && x <= 809 && 638 <= y && y <= 659
    	//sarajevo: 897 <= x && x <= 922 && 717 <= y && y <= 738
    	//palermo: 730 <= x && x <= 755 && 927 <= y && y <= 948
    	//smyrna: 1084 <= x && x <= 1109 && 921 <= y && y <= 942
    	//angora: 1249 <= x && x <= 1274 && 888 <= y && y <= 909
    	//erzurum: 1357 <= x && x <= 1382 && 855 <= y && y <= 876
    	//sochi: 1385 <= x && x <= 1410 && 683 <= y && y <= 704
    	//rostov: 1393 <= x && x <= 1418 && 562 <= y && y <= 583
    	//stockholm: 822 <= x && x <= 847 && 81 <= y && y <= 102
    	//wilno: 1098 <= x && x <= 1123 && 308 <= y && y <= 329
    	//warszawa: 946 <= x && x <= 971 && 344 <= y && y <= 365
    	//venezia: 667 <= x && x <= 693 && 619 <= y && y <= 640
    	//
    	//

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
