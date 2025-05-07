/*
///////////////////////////TO DO LIST//////////////////////////////////////  
show and score each complete and incomplete ticket
show each players longest route and award the EU express
 fix stations
 */

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
    private BufferedImage sidewaytemplate, greyGambling ,dbPayment,playerGamble ,nextButton, previousButton,table, okbutton, board, player1, player2, player3, player4, cardBack, ticket, template, p1bg, p2bg, p3bg, p4bg, startticket, redplayer, blueplayer, greenplayer, yellowplayer, playerpointer;
    private ColorCard[] faceUpCard;
    private CardDeck cardDeck;
    private Stack<ColorCard> discardDeck;
    private TicketDeck ticketDeck;
    private ArrayList<Player> players;
    private Graph graph;// stores all the coordinates of the cities in a map
    private boolean firstTicketClicked, secondTicketClicked, thirdTicketClicked, fourthTicketClicked, t1Clicked, t2Clicked, t3Clicked;
    private Ticket t1, t2, t3, t4; 
    private int ticketsClicked;
    private int tClicked;
    private int currentPlr;
    private City[] clickedCity = new City[2];
    private int panelStuff = 1;// 0 = nothing,1 = Start of game,2 = draw tickets,3 = Inventory,4 = Gambling,5 = Grey route payment,6 = Double route payment,7 = choosing color for grey Gambling
    private int ticketPage = 1; // 1 = first page, 2 = seconds page, 3 = third page(max)
    private int turnUsed = 0;
    private ArrayList<Ticket> ticketsShownList;
    private City currentCityHovered = null;
    private int globalX = 0;
	private int globalY = 0;
	private boolean lastTurn = false;
	private boolean greyRequiredMet;
	private int colorsChosen;
	private int amountNeeded;
	private String railroadColor1;
	private String railroadColor2;
	private boolean railroadBought1;
	private boolean railroadBought2;
	private int cardsRemoved;
	private boolean roadChosen;
	private int wildNeeded;
	private ArrayList<RailRoad> railRoadChosen;
	private int wildNum = 0,whiteNum = 0,redNum = 0,purpleNum = 0,greenNum = 0,brownNum = 0,blueNum = 0,blackNum=0,yellowNum = 0;
	private boolean whiteClicked= false,redClicked= false,purpleClicked= false,greenClicked= false,brownClicked= false,blueClicked= false,blackClicked= false,yellowClicked = false;
	private ArrayList<ColorCard> gamblingDraw;
	private int gamblingExtraCost;
	private boolean gamblingConfirm;
	private boolean gamblingRequiredMet;
	private String greyGamblingColor;

    public GameBoard() {
        try {
            table = ImageIO.read(MainMenu.class.getResource("/ttreImages/gameBg.png"));
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
            dbPayment = ImageIO.read(MainMenu.class.getResource("/ttreImages/dbpayment.png"));;
            players = new ArrayList<Player>();
            currentPlr = 0;
            cardDeck = new CardDeck();
            discardDeck = new Stack<>();
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
            players.add(new Player("orange", blueplayer, new Color(255,165,0)));
            players.add(new Player("white", greenplayer, new Color(255,255,255)));
            players.add(new Player("blue", yellowplayer, new Color(0,0,255)));
            p1bg = ImageIO.read(MainMenu.class.getResource("/ttreImages/p1bg.png"));
            p2bg = ImageIO.read(MainMenu.class.getResource("/ttreImages/p2bg.png"));
            p3bg = ImageIO.read(MainMenu.class.getResource("/ttreImages/p3.png"));
            p4bg = ImageIO.read(MainMenu.class.getResource("/ttreImages/P4 BG.png"));
            startticket = ImageIO.read(MainMenu.class.getResource("/ttreImages/ticketchoose.png"));
            nextButton = ImageIO.read(MainMenu.class.getResource("/ttreImages/nextButton.png"));
            previousButton = ImageIO.read(MainMenu.class.getResource("/ttreImages/previousButton.png"));
            greyGambling = ImageIO.read(MainMenu.class.getResource("/ttreImages/greyGambling.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

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
        greyRequiredMet = false;
        colorsChosen = 0;
        amountNeeded = 0;
        railroadColor1 = "";
        railroadColor2 = "";
        railroadBought1 = false;
        railroadBought2 = false;
        roadChosen = false;
        railRoadChosen = new ArrayList<>();
        wildNeeded = 0;
        gamblingDraw= new ArrayList<>();
        gamblingExtraCost = 0;
        gamblingRequiredMet = false;
        gamblingConfirm = false;
        greyGamblingColor = "";

        
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
        if (!discardDeck.isEmpty() && discardDeck.getLast() != null) {
         	g2d.drawImage(discardDeck.getLast().getImage(), 1675, 3, 100, 150, null);
         } else {
             g2d.drawImage(template, 1675, 3, null);
         }
        g2d.drawImage(ticket, 1795, 3, null);
        if( currentPlr ==0&& panelStuff == 0 )
        {
        	g2d.drawImage(p1bg, 1420, 750, 500, 300, null);
        	g2d.drawImage(playerpointer, 1875, 225, null);
        }
        else if( currentPlr ==1 && panelStuff == 0)
        {
        	g2d.drawImage(p2bg, 1420, 750, 500, 300, null);
        	g2d.drawImage(playerpointer, 1875, 350, null);
        }
        else if(currentPlr ==2&& panelStuff == 0)
        {
        	g2d.drawImage(p3bg, 1420, 750, 500, 300, null);
        	g2d.drawImage(playerpointer, 1875, 450, null);
        }
        else if(currentPlr ==3&& panelStuff == 0
        		)
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
        
        //Right click text
        g2d.setFont(new Font("Serif", Font.BOLD, 22)); g2d.setColor(Color.WHITE); 
        g2d.drawString("Right click on a city to build station", 15, 25);

        
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
    				railRoadColor = currentRailRoad.get(0).getPlrBought().getPlrColor();
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

        if(clickedCity[0] != null && panelStuff == 0)
        {
	        //highlight city when clicked
        	g2d.setStroke(new BasicStroke(4));
        	g2d.setColor(Color.YELLOW);
	        g2d.drawOval(clickedCity[0].getX()-10, clickedCity[0].getY()-10, 22, 22);
        }
        if(clickedCity[1] != null && panelStuff == 0)
        {
	        //highlight city when clicked
        	g2d.setStroke(new BasicStroke(4));
        	g2d.setColor(Color.YELLOW);
	        g2d.drawOval(clickedCity[1].getX()-10, clickedCity[1].getY()-10, 22, 22);
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
        		g2d.drawImage( nextButton, 1700, 850, 50,50,null );
        		g2d.drawImage( previousButton, 1600, 850, 50,50,null );
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
         		g2d.drawString("Choose color to buy route", 1510, 550);
         		g2d.drawString("Extra Cost: " + gamblingExtraCost, 1490, 600);
         		g2d.drawString("Cards Chosen: " + colorsChosen, 1680, 600);
         		g2d.drawImage(playerGamble, 1510, 630, null);
         		g2d.drawImage( gamblingDraw.get(0).getImage()  ,1545,54,186,88,null );
         		g2d.drawImage( gamblingDraw.get(1).getImage()  ,1545,204,186,88,null );
         		g2d.drawImage( gamblingDraw.get(2).getImage()  ,1545,354,186,88,null );
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
        	if(panelStuff == 5)
        	{
         		g2d.setColor(Color.WHITE);
         		g2d.drawString("Choose color to buy route", 1510, 450);
         		g2d.drawString("Price of the RailRoad: " + amountNeeded , 1510, 500);
         		g2d.drawString("Colors chosen: " + colorsChosen, 1510, 550);
         		g2d.drawString("Wild cards required " + wildNeeded, 1510, 600);
         		
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
        	if(panelStuff == 6)
        	{
        		g2d.drawImage(dbPayment,0,0,1880,1070,null);
        		g2d.setColor(Color.WHITE);
        		g2d.drawString(railroadColor1 + " RailRoad", 1650, 515 );
        		g2d.drawString(amountNeeded + "", 1620, 565 );
        		if( railroadBought1 )
        			g2d.drawString("owned", 1650, 600 );
        		else
        			g2d.drawString("not owned", 1650, 600 );
        		
        		g2d.drawString(railroadColor2 + " RailRoad", 1650, 695 );
        		g2d.drawString(amountNeeded + "", 1620, 745 );
        		if( railroadBought2 )
        			g2d.drawString("owned", 1650, 780 );
        		else
        			g2d.drawString("not owned", 1650, 780 );
        		g2d.setFont(new Font("Serif", Font.BOLD, 23));
        		g2d.drawString(String.valueOf(players.get(currentPlr).getCardColor("wild")), 1560, 762+150); // Locomotive
                g2d.drawString(String.valueOf(players.get(currentPlr).getCardColor("white")), 1625, 762+150); // White
                g2d.drawString(String.valueOf(players.get(currentPlr).getCardColor("red")), 1692, 762+150); // Red
                g2d.drawString(String.valueOf(players.get(currentPlr).getCardColor("purple")), 1757, 762+150); // Purple
                g2d.drawString(String.valueOf(players.get(currentPlr).getCardColor("green")), 1825, 762+150); // Green
                //2nd row
                g2d.drawString(String.valueOf(players.get(currentPlr).getCardColor("brown")), 1560, 890+140); // Brown
                g2d.drawString(String.valueOf(players.get(currentPlr).getCardColor("blue")), 1625, 890+140); // Blue
                g2d.drawString(String.valueOf(players.get(currentPlr).getCardColor("black")), 1692, 890+140); // Black
                g2d.drawString(String.valueOf(players.get(currentPlr).getCardColor("yellow")), 1757, 890+140); // Yellow
        		
        	}
        	if( panelStuff == 7)
        	{
        		g2d.drawImage(greyGambling,0,0,1880,1070,null);
        		
        		g2d.setColor(Color.WHITE);
        		g2d.setFont(new Font("Serif", Font.BOLD, 30));
                g2d.drawString(String.valueOf(players.get(currentPlr).getCardColor("white")), 1566, 620); // White
                g2d.drawString(String.valueOf(players.get(currentPlr).getCardColor("red")), 1666-15, 620); // Red
                g2d.drawString(String.valueOf(players.get(currentPlr).getCardColor("purple")), 1766-35, 620); // Purple
                g2d.drawString(String.valueOf(players.get(currentPlr).getCardColor("green")), 1866-55, 620); // Green
                //2nd row
                g2d.drawString(String.valueOf(players.get(currentPlr).getCardColor("brown")), 1566, 821); // Brown
                g2d.drawString(String.valueOf(players.get(currentPlr).getCardColor("blue")), 1666-15, 821); // Blue
                g2d.drawString(String.valueOf(players.get(currentPlr).getCardColor("black")), 1766-35, 821); // Black
                g2d.drawString(String.valueOf(players.get(currentPlr).getCardColor("yellow")), 1866-55, 821); // Yellow
        		
        		
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
        	int reset = (pt + players.get(i).getStations() * 4) % 100 -12;
        	if (reset < 0) {
         		g2d.fillOval(32 - (reset * 47), 960, 30, 30);
 	            	g2d.setPaint(new Color(0, 0, 0));
 	            	g2d.setStroke(new BasicStroke(2));
 	            	g2d.drawOval(32 - (reset * 47), 960, 30, 30);
         	}
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
        	g2d.setFont(new Font("Serif", Font.BOLD, 30));
        	g2d.setStroke(new BasicStroke(2));
        	g2d.drawRoundRect(globalX, globalY, 250, 80, 15, 15);
        	g2d.drawString(currentCityHovered.getName(), globalX + 45, globalY + 45);
        	g2d.setFont(new Font("Serif", Font.BOLD, 15));
        	g2d.drawString("Right click to place down station", globalX + 5, globalY + 70);
        	
        }
    }

   
    
    @Override
    public void mouseClicked(MouseEvent e) {
    	int x = e.getX();
    	int y = e.getY();
    	//System.out.println(x + " " + y);
   
    	
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
 	    		int wildNumber = 0;
 	    		for (int j = 0; j < faceUpCard.length; j++) {
 	    			
 	    			if (faceUpCard[j].getColor().equals("wild")) wildNumber++;
 	    		}
 	    		if (wildNumber >= 3) {
 	    			for (int j = 0; j < faceUpCard.length; j++) {
 	    				discardDeck.add(faceUpCard[j]);
 		    			faceUpCard[j] = cardDeck.drawCard();
 		    		}
	    		}
	        }
		
			//allows for player to click on first road option
			if( x>=1499 && x<=1878 && y>=474 && y<=539 && panelStuff == 6 )
			{
				if(!railroadBought1)
				{
					roadChosen = true;
					railRoadChosen = graph.getCityConnection(clickedCity[0], clickedCity[1]);
					if( railroadColor1.equals("any") )
						panelStuff = 5;
					else
					{
						buyEvent();
						if( railRoadChosen.get(0).getMountains() )
							panelStuff = 4;
						else
								panelStuff = 0;
					}
				}
			}
			
			//allows for player to click on second road option
			if( x>=1499 && x<=1878 && y>=652 && y<=717 && panelStuff == 6 )
			{
				if(!railroadBought2)
				{
					roadChosen = true;
					railRoadChosen = graph.getCitySecondConnection(clickedCity[0], clickedCity[1]);
					
					if( railroadColor1.equals("any") )
						panelStuff = 5;
					else
					{
						buyEvent();
						if( railRoadChosen.get(0).getMountains() )
							panelStuff = 4;
						else
							panelStuff = 0;
					}
				}
					
			}
			
			if( x>=1539 && x<=1612 && y>=481 && y<=595 && panelStuff == 7  )//white
			{
				out.println(amountNeeded);
				if( players.get(currentPlr).getHighestColorNum() > amountNeeded )
				{
					greyGamblingColor = "white";
					buyEvent();
				}
			}
			if( x>=1612 && x<=1685 && y>=481 && y<=595 && panelStuff == 7  )//red
			{
				if( players.get(currentPlr).getHighestColorNum()> amountNeeded )
				{
					greyGamblingColor = "red";
					buyEvent();
				}
			}
			if( x>=1685 && x<=1758 && y>=481 && y<=595 && panelStuff == 7  )//purple
			{
				if( players.get(currentPlr).getHighestColorNum() > amountNeeded )
				{
				greyGamblingColor = "purple";
				buyEvent();
				}
			}
			if( x>=1758 && x<=1831 && y>=481 && y<=595 && panelStuff == 7  )//green
			{
				if( players.get(currentPlr).getHighestColorNum() > amountNeeded )
				{
				greyGamblingColor = "green";
				buyEvent();
				}
			}
			//2nd row of cards
			if( x>=1539 && x<=1612 && y>=684 && y<=805 && panelStuff == 7  )//brown
			{
				if( players.get(currentPlr).getHighestColorNum() > amountNeeded )
				{
				greyGamblingColor = "brown";
				buyEvent();
				}
			}
			if( x>=1612 && x<=1685 && y>=684 && y<=805 && panelStuff == 7  )//blue
			{
				if( players.get(currentPlr).getHighestColorNum() > amountNeeded )
				{
				greyGamblingColor = "blue";
				buyEvent();
				}
			}
			if( x>=1685 && x<=1758 && y>=684 && y<=805 && panelStuff == 7  )//black
			{
				if( players.get(currentPlr).getHighestColorNum() > amountNeeded )
				{
				greyGamblingColor = "black";
				buyEvent();
				}
			}
			if( x>=1758 && x<=1831 && y>=684 && y<=805 && panelStuff == 7  )//yellow
			{
				if( players.get(currentPlr).getHighestColorNum() > amountNeeded )
				{
				greyGamblingColor = "yellow";
				buyEvent();
				}
			}
			
		
			if( x>=1550 && x<=1618 && y>=631 && y<=731 && (panelStuff == 5 || panelStuff == 4) )//wild card
			{
				if( panelStuff == 5)
				{
					int wildsFound = 0;
					for(int i = 0; i<players.get(currentPlr).getCard().size();i++)
					{
						if( players.get(currentPlr).getCard().get(i).getColor().equals("wild")   )
						{
							wildsFound++;
						}
					}
					
					if( wildsFound > 0 && colorsChosen < amountNeeded)
					{
						wildsFound--;
						wildNum++;
						players.get(currentPlr).removeCards("wild", 1);
						colorsChosen++;
						cardsRemoved++;
					}
					if(colorsChosen == amountNeeded)
					{
						greyRequiredMet=true;
					}
					
				}
				else
				{
					int wildsFound = 0;
					for(int i = 0; i<players.get(currentPlr).getCard().size();i++)
					{
						if( players.get(currentPlr).getCard().get(i).getColor().equals("wild")   )
						{
							wildsFound++;
						}
					}
					if( wildsFound > 0 && colorsChosen < gamblingExtraCost)
					{
						wildsFound--;
						wildNum++;
						players.get(currentPlr).removeCards("wild", 1);
						colorsChosen++;
						cardsRemoved++;
					}
					if(colorsChosen == gamblingExtraCost)
					{
						gamblingRequiredMet=true;
					}
					
					
				}
			
			}
			if(x>=1618 && x<=1686 && y>=631 && y<=731 && (panelStuff == 5 || panelStuff == 4))//white card
			{
				if( panelStuff == 5 )
				{
					if( (whiteClicked || !whiteClicked)&& !redClicked && !redClicked && !purpleClicked && !greenClicked && !blueClicked && !yellowClicked && !yellowClicked )
					{
						whiteClicked = true;
						int whiteFound = 0;
						for(int i = 0; i<players.get(currentPlr).getCard().size();i++)
						{
							if( players.get(currentPlr).getCard().get(i).getColor().equals("white")   )
							{
								whiteFound++;
							}
						}
						
						if( whiteFound > 0 && colorsChosen < amountNeeded)
						{
							whiteFound--;
							whiteNum++;
							players.get(currentPlr).removeCards("white", 1);
							colorsChosen++;
							cardsRemoved++;
						}
						if(colorsChosen == amountNeeded)
						{
							greyRequiredMet=true;
						}
					}
				}
				else
				{
					if( (whiteClicked || !whiteClicked)&& !redClicked && !redClicked && !purpleClicked && !greenClicked && !blueClicked && !yellowClicked && !yellowClicked )
					{
						whiteClicked = true;
						int whiteFound = 0;
						for(int i = 0; i<players.get(currentPlr).getCard().size();i++)
						{
							if( players.get(currentPlr).getCard().get(i).getColor().equals("white")   )
							{
								whiteFound++;
							}
						}
						
						if( whiteFound > 0 && colorsChosen < gamblingExtraCost)
						{
							whiteFound--;
							whiteNum++;
							players.get(currentPlr).removeCards("white", 1);
							colorsChosen++;
							cardsRemoved++;
						}
						if(colorsChosen == gamblingExtraCost)
						{
							gamblingRequiredMet=true;
						}
					}
					
					
					
				}
				
			}
			if(x>=1686 && x<=1754 && y>=631 && y<=731 && (panelStuff == 5 || panelStuff == 4) )//red card
			{
				if( panelStuff==5 )
				{
					if( (redClicked || !redClicked)&& !whiteClicked && !brownClicked && !purpleClicked && !greenClicked && !blueClicked && !yellowClicked && !yellowClicked )
					{
						redClicked = true;
						int redFound = 0;
						for(int i = 0; i<players.get(currentPlr).getCard().size();i++)
						{
							if( players.get(currentPlr).getCard().get(i).getColor().equals("red")   )
							{
								redFound++;
							}
						}
						
						if( redFound > 0 && colorsChosen < amountNeeded)
						{
							redFound--;
							redNum++;
							players.get(currentPlr).removeCards("red", 1);
							colorsChosen++;
							cardsRemoved++;
						}
						if(colorsChosen == amountNeeded)
						{
							greyRequiredMet=true;
						}
					}
				}
				else
				{
					if( (redClicked || !redClicked)&& !whiteClicked && !brownClicked && !purpleClicked && !greenClicked && !blueClicked && !yellowClicked && !yellowClicked )
					{
						redClicked = true;
						int redFound = 0;
						for(int i = 0; i<players.get(currentPlr).getCard().size();i++)
						{
							if( players.get(currentPlr).getCard().get(i).getColor().equals("red")   )
							{
								redFound++;
							}
						}
						
						if( redFound > 0 && colorsChosen < gamblingExtraCost)
						{
							redFound--;
							redNum++;
							players.get(currentPlr).removeCards("red", 1);
							colorsChosen++;
							cardsRemoved++;
						}
						if(colorsChosen == gamblingExtraCost)
						{
							gamblingRequiredMet=true;
						}
					}
				}
			
			}
			if(x>=1754 && x<=1822 && y>=631 && y<=731 && (panelStuff == 5 || panelStuff == 4) )//purple card
			{
				if( panelStuff == 5)
				{
					if( (purpleClicked || !purpleClicked)&& !whiteClicked && !redClicked && !brownClicked && !greenClicked && !blueClicked && !yellowClicked && !yellowClicked )
					{
						purpleClicked = true;
						int purpleFound = 0;
						for(int i = 0; i<players.get(currentPlr).getCard().size();i++)
						{
							if( players.get(currentPlr).getCard().get(i).getColor().equals("purple")   )
							{
								purpleFound++;
							}
						}
						
						if( purpleFound > 0 && colorsChosen < amountNeeded)
						{
							purpleFound--;
							purpleNum++;
							players.get(currentPlr).removeCards("purple", 1);
							colorsChosen++;
							cardsRemoved++;
						}
						if(colorsChosen == amountNeeded)
						{
							greyRequiredMet=true;
						}
					}
				}
				else
				{
					if( (purpleClicked || !purpleClicked)&& !whiteClicked && !redClicked && !brownClicked && !greenClicked && !blueClicked && !yellowClicked && !yellowClicked )
					{
						purpleClicked = true;
						int purpleFound = 0;
						for(int i = 0; i<players.get(currentPlr).getCard().size();i++)
						{
							if( players.get(currentPlr).getCard().get(i).getColor().equals("purple")   )
							{
								purpleFound++;
							}
						}
						
						if( purpleFound > 0 && colorsChosen < gamblingExtraCost)
						{
							purpleFound--;
							purpleNum++;
							players.get(currentPlr).removeCards("purple", 1);
							colorsChosen++;
							cardsRemoved++;
						}
						if(colorsChosen == gamblingExtraCost)
						{
							gamblingRequiredMet=true;
						}
					}
				}
			}
			if( x >=1820 && x <= 1885 && y >=631 && y <= 731 && (panelStuff == 5 || panelStuff == 4))//green card
			{
				if( panelStuff == 5)
				{
					if( (greenClicked || !greenClicked)&& !whiteClicked && !redClicked && !purpleClicked && !brownClicked && !blueClicked && !yellowClicked && !yellowClicked )
					{
						greenClicked = true;
						int greenFound = 0;
						for(int i = 0; i<players.get(currentPlr).getCard().size();i++)
						{
							if( players.get(currentPlr).getCard().get(i).getColor().equals("green")   )
							{
								greenFound++;
							}
						}
						
						if( greenFound > 0 && colorsChosen < amountNeeded)
						{
							greenFound--;
							greenNum++;
							players.get(currentPlr).removeCards("green", 1);
							colorsChosen++;
							cardsRemoved++;
						}
						if(colorsChosen == amountNeeded)
						{
							greyRequiredMet=true;
						}
					}
				}
				else
				{
					if( (greenClicked || !greenClicked)&& !whiteClicked && !redClicked && !purpleClicked && !brownClicked && !blueClicked && !yellowClicked && !yellowClicked )
					{
						greenClicked = true;
						int greenFound = 0;
						for(int i = 0; i<players.get(currentPlr).getCard().size();i++)
						{
							if( players.get(currentPlr).getCard().get(i).getColor().equals("green")   )
							{
								greenFound++;
							}
						}
						
						if( greenFound > 0 && colorsChosen < gamblingExtraCost)
						{
							greenFound--;
							greenNum++;
							players.get(currentPlr).removeCards("green", 1);
							colorsChosen++;
							cardsRemoved++;
						}
						if(colorsChosen == gamblingExtraCost)
						{
							gamblingRequiredMet=true;
						}
					}
				}
				
			}
			//2nd half of cards	
			if( x>=1550 && x<=1618 && y>=773 && y<=876 && (panelStuff == 5 || panelStuff == 4) )//orange card
			{
				if( panelStuff == 5)
				{
					if( (brownClicked || !brownClicked)&& !whiteClicked && !redClicked && !purpleClicked && !greenClicked && !blueClicked && !yellowClicked && !yellowClicked )
					{
						brownClicked = true;
						out.println("Brown Card clicked");
						int brownFound = 0;
						for(int i = 0; i<players.get(currentPlr).getCard().size();i++)
						{
							if( players.get(currentPlr).getCard().get(i).getColor().equals("brown")   )
							{
								brownFound++;
							}
						}
						
						if( brownFound > 0 && colorsChosen < amountNeeded)
						{
							brownFound++;
							brownNum++;
							players.get(currentPlr).removeCards("brown", 1);
							colorsChosen++;
							cardsRemoved++;
						}
						if(colorsChosen == amountNeeded)
						{
							greyRequiredMet=true;
						}
					}
				}
				else
				{
					if( (brownClicked || !brownClicked)&& !whiteClicked && !redClicked && !purpleClicked && !greenClicked && !blueClicked && !yellowClicked && !yellowClicked )
					{
						brownClicked = true;
						out.println("Brown Card clicked");
						int brownFound = 0;
						for(int i = 0; i<players.get(currentPlr).getCard().size();i++)
						{
							if( players.get(currentPlr).getCard().get(i).getColor().equals("brown")   )
							{
								brownFound++;
							}
						}
						
						if( brownFound > 0 && colorsChosen < gamblingExtraCost)
						{
							brownFound++;
							brownNum++;
							players.get(currentPlr).removeCards("brown", 1);
							colorsChosen++;
							cardsRemoved++;
						}
						if(colorsChosen == gamblingExtraCost)
						{
							gamblingRequiredMet=true;
						}
					}
				}
		
			}
			if( x>=1618 && x<=1686 && y>=773 && y<=876 && (panelStuff == 5 || panelStuff == 4) )//blue card
			{
				if( panelStuff == 5 )
				{
					if( (blueClicked || !blueClicked)&& !whiteClicked && !redClicked && !purpleClicked && !greenClicked && !brownClicked && !yellowClicked && !yellowClicked )
					{
						blueClicked = true;
						out.println("Blue Card clicked");
						int blueFound = 0;
						for(int i = 0; i<players.get(currentPlr).getCard().size();i++)
						{
							if( players.get(currentPlr).getCard().get(i).getColor().equals("blue")   )
							{
								blueFound++;
							}
						}
						
						if( blueFound > 0 && colorsChosen < amountNeeded)
						{
							blueFound--;
							blueNum++;
							players.get(currentPlr).removeCards("blue", 1);
							colorsChosen++;
							cardsRemoved++;
						}
						if(colorsChosen == amountNeeded)
						{
							greyRequiredMet=true;buyEvent();
						}
					}
				}
				else
				{
					if( (blueClicked || !blueClicked)&& !whiteClicked && !redClicked && !purpleClicked && !greenClicked && !brownClicked && !yellowClicked && !yellowClicked )
					{
						blueClicked = true;
						out.println("Blue Card clicked");
						int blueFound = 0;
						for(int i = 0; i<players.get(currentPlr).getCard().size();i++)
						{
							if( players.get(currentPlr).getCard().get(i).getColor().equals("blue")   )
							{
								blueFound++;
							}
						}
						
						if( blueFound > 0 && colorsChosen < gamblingExtraCost)
						{
							blueFound--;
							blueNum++;
							players.get(currentPlr).removeCards("blue", 1);
							colorsChosen++;
							cardsRemoved++;
						}
						if(colorsChosen == gamblingExtraCost)
						{
							gamblingRequiredMet=true;
						}
					}
				}
			}
			if( x>=1686 && x<=1754 && y>=773 && y<=876 && (panelStuff == 5 || panelStuff == 4) )//black card
			{
				if( panelStuff == 5 )
				{
					if( (blackClicked || !blackClicked)&& !whiteClicked && !redClicked && !purpleClicked && !greenClicked && !brownClicked && !blueClicked && !yellowClicked )
					{
						blackClicked = true;
						out.println("black Card clicked");
						int blackFound = 0;
						for(int i = 0; i<players.get(currentPlr).getCard().size();i++)
						{
							if( players.get(currentPlr).getCard().get(i).getColor().equals("black")   )
							{
								blackFound++;
							}
						}
						
						if( blackFound > 0 && colorsChosen < amountNeeded)
						{
							blackFound--;
							blackNum++;
							players.get(currentPlr).removeCards("black", 1);
							colorsChosen++;
							cardsRemoved++;
						}
						if(colorsChosen == amountNeeded)
						{
							greyRequiredMet=true;
						}
					}
				}
				else
				{
					if( (blackClicked || !blackClicked)&& !whiteClicked && !redClicked && !purpleClicked && !greenClicked && !brownClicked && !blueClicked && !yellowClicked )
					{
						blackClicked = true;
						out.println("black Card clicked");
						int blackFound = 0;
						for(int i = 0; i<players.get(currentPlr).getCard().size();i++)
						{
							if( players.get(currentPlr).getCard().get(i).getColor().equals("black")   )
							{
								blackFound++;
							}
						}
						
						if( blackFound > 0 && colorsChosen < gamblingExtraCost)
						{
							blackFound--;
							blackNum++;
							players.get(currentPlr).removeCards("black", 1);
							colorsChosen++;
							cardsRemoved++;
						}
						if(colorsChosen == gamblingExtraCost)
						{
							gamblingRequiredMet=true;
						}
					}
				}
			}
			if( x>=1754 && x<=1822 && y>=773 && y<=876 && (panelStuff == 5 || panelStuff == 4) )//yellow card
			{
				if( panelStuff == 5 )
				{
					if( (yellowClicked || !yellowClicked)&& !whiteClicked && !redClicked && !purpleClicked && !greenClicked && !brownClicked && !blueClicked && !blackClicked )
					{
						yellowClicked = true;
						out.println("Yellow Card clicked");
						int yellowFound = 0;
						for(int i = 0; i<players.get(currentPlr).getCard().size();i++)
						{
							if( players.get(currentPlr).getCard().get(i).getColor().equals("yellow")   )
							{
								yellowFound++;
							}
						}
						
						
						if( yellowFound > 0 && colorsChosen < amountNeeded)
						{
							yellowFound--;
							yellowNum++;
							players.get(currentPlr).removeCards("yellow", 1);
							colorsChosen++;
							cardsRemoved++;
						}
						if(colorsChosen == amountNeeded)
						{
							greyRequiredMet=true;
							
						}
					}
				}
				else
				{
					if( (yellowClicked || !yellowClicked)&& !whiteClicked && !redClicked && !purpleClicked && !greenClicked && !brownClicked && !blueClicked && !blackClicked )
					{
						yellowClicked = true;
						out.println("Yellow Card clicked");
						int yellowFound = 0;
						for(int i = 0; i<players.get(currentPlr).getCard().size();i++)
						{
							if( players.get(currentPlr).getCard().get(i).getColor().equals("yellow")   )
							{
								yellowFound++;
							}
						}
						
						
						if( yellowFound > 0 && colorsChosen < gamblingExtraCost)
						{
							yellowFound--;
							yellowNum++;
							players.get(currentPlr).removeCards("yellow", 1);
							colorsChosen++;
							cardsRemoved++;
						}
						if(colorsChosen == gamblingExtraCost)
						{
							gamblingRequiredMet=true;
						}
					}
				}
			}
	    	
			//checks if player clicked confirm on panelStuff = 5
			if( x>=1510 && x<=1679 && y>=969 && y<=1019 && (panelStuff == 5 || panelStuff == 4))
			{
				out.println( "The current panel is " + panelStuff );
				if( panelStuff == 5 )
				{
					if( greyRequiredMet && wildNum >= wildNeeded )
					{
						out.println("It at least calls the buy method");
						buyEvent();
						if( !greyGamblingColor.equals("") )
							panelStuff = 4;
						else
							panelStuff = 0;
						wildNum = 0;
						whiteNum = 0;
						redNum = 0;
						purpleNum = 0;
						greenNum = 0;
						brownNum = 0;
						blueNum = 0;
						blackNum = 0;
						yellowNum = 0;
						greyRequiredMet = false;
						colorsChosen = 0;
						cardsRemoved = 0;
						whiteClicked= false;redClicked= false;purpleClicked= false;greenClicked= false;brownClicked= false;blueClicked= false;blackClicked= false;yellowClicked = false;
					}
				}
				else
				{
					if( ( gamblingExtraCost == 0 || gamblingRequiredMet) && wildNum >= wildNeeded )
					{
						out.println("Confirm button is clicked");
						gamblingConfirm = true;
						buyEvent();
						panelStuff = 0;
						wildNum = 0;
						whiteNum = 0;
						redNum = 0;
						purpleNum = 0;
						greenNum = 0;
						brownNum = 0;
						blueNum = 0;
						blackNum = 0;
						yellowNum = 0;
						gamblingExtraCost = 0;
						gamblingRequiredMet = false;
						colorsChosen = 0;
						cardsRemoved = 0;
						whiteClicked= false;redClicked= false;purpleClicked= false;greenClicked= false;brownClicked= false;blueClicked= false;blackClicked= false;yellowClicked = false;

						
					}
					
				}
				
			}
			
			//checks if player clicked declined on panelStuff = 5 
			if( x>=1708 && x<=1879 && y>=970 && y<=1019 && (panelStuff == 5 || panelStuff == 4))
			{
				if( panelStuff == 5 )
				{
					players.get(currentPlr).addCard("wild",wildNum);
					wildNum = 0;
					players.get(currentPlr).addCard("white",whiteNum);
					whiteNum = 0;
					players.get(currentPlr).addCard("red",redNum);
					redNum = 0;
					players.get(currentPlr).addCard("purple",purpleNum);
					purpleNum = 0;
					players.get(currentPlr).addCard("green",greenNum);
					greenNum = 0;
					players.get(currentPlr).addCard("brown",brownNum);
					brownNum = 0;
					players.get(currentPlr).addCard("blue",blueNum);
					blueNum = 0;
					players.get(currentPlr).addCard("black",blackNum);
					blackNum = 0;
					players.get(currentPlr).addCard("yellow",yellowNum);
					yellowNum = 0;
					colorsChosen = 0;
					cardsRemoved = 0;
					greyRequiredMet = false;
					whiteClicked= false;redClicked= false;purpleClicked= false;greenClicked= false;brownClicked= false;blueClicked= false;blackClicked= false;yellowClicked = false;
					panelStuff = 0;
				}
				else
				{
					players.get(currentPlr).addCard("wild",wildNum);
					wildNum = 0;
					players.get(currentPlr).addCard("white",whiteNum);
					whiteNum = 0;
					players.get(currentPlr).addCard("red",redNum);
					redNum = 0;
					players.get(currentPlr).addCard("purple",purpleNum);
					purpleNum = 0;
					players.get(currentPlr).addCard("green",greenNum);
					greenNum = 0;
					players.get(currentPlr).addCard("brown",brownNum);
					brownNum = 0;
					players.get(currentPlr).addCard("blue",blueNum);
					blueNum = 0;
					players.get(currentPlr).addCard("black",blackNum);
					blackNum = 0;
					players.get(currentPlr).addCard("yellow",yellowNum);
					yellowNum = 0;
					gamblingDraw = new ArrayList<>();
					gamblingExtraCost = 0;
					colorsChosen = 0;
					cardsRemoved = 0;
					gamblingRequiredMet = false;
					whiteClicked= false;redClicked= false;purpleClicked= false;greenClicked= false;brownClicked= false;blueClicked= false;blackClicked= false;yellowClicked = false;
					panelStuff = 0;
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
	    	if (x >=1600 && x <= 1650 && y >=850 && y <= 920 && panelStuff == 3)
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
	    	if (x >=1700 && x <= 1750 && y >=850 && y <= 920 && panelStuff == 3)
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
    	clickedCity[0] = null;
     	clickedCity[1] = null;
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
    		
    		Player plr1 = players.get(0);
    		int pt1 = plr1.getPoint()/2;
         	int ticketPt1 = graph.getPlayerTrainPoint(players.get(0));
         	int totalPt1 = pt1 + players.get(0).getStations() * 4 + ticketPt1;
         	
         	Player plr2 = players.get(1);
         	int pt2 = plr2.getPoint()/2;
         	int ticketPt2 = graph.getPlayerTrainPoint(players.get(1));
         	int totalPt2 = pt2 + players.get(1).getStations() * 4 + ticketPt2;
         	
         	Player plr3 = players.get(2);
         	int pt3 = plr3.getPoint()/2;
         	int ticketPt3 = graph.getPlayerTrainPoint(players.get(2));
         	int totalPt3 = pt3 + players.get(2).getStations() * 4 + ticketPt3;
         	
         	Player plr4 = players.get(3);
         	int pt4 = plr4.getPoint()/2;
         	int ticketPt4 = graph.getPlayerTrainPoint(players.get(3));
         	int totalPt4 = pt1 + players.get(3).getStations() * 4 + ticketPt4;
         	
        	
        	int longestRouteNum = 1;
        	if (graph.getLongestPlrRoute() == players.get(1)) {
        		longestRouteNum = 2;
        	} else if (graph.getLongestPlrRoute() == players.get(2)) {
        		longestRouteNum = 3;
        	} else if (graph.getLongestPlrRoute() == players.get(3)) {
        		longestRouteNum = 4;
        	}
        	JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(GameBoard.this);
         	topFrame.getContentPane().removeAll();
         	endGameRunner fr = new endGameRunner("Frame", totalPt1, totalPt2, totalPt3, totalPt4, ticketPt1, ticketPt2, ticketPt3, ticketPt4, longestRouteNum);
    	}
    }
    
    
    
    
	public void buyEvent() {
    	ArrayList<RailRoad> neededRailRoad = graph.getCityConnection(clickedCity[0], clickedCity[1]);
    	ArrayList<RailRoad> neededRailRoad2 = graph.getCitySecondConnection(clickedCity[0], clickedCity[1]);
    	wildNeeded = 0;
    	if (neededRailRoad.get(0) != null) {
    		wildNeeded = neededRailRoad.get(0).getWildNum();
    	}
    	
		if ( neededRailRoad.get(0).getPlrBought() == players.get(currentPlr)) {
			return;
		}
		if (neededRailRoad2 != null && neededRailRoad2.get(0).getBought() && neededRailRoad2.get(0).getPlrBought() == players.get(currentPlr)) {
			return;
		}
	    railroadBought1 = neededRailRoad.get(0).getBought();
	    railroadColor1 = neededRailRoad.get(0).getColor();
	    amountNeeded = neededRailRoad.get(0).getCost();
	    if( neededRailRoad2 != null )
	    {
	    	railroadColor2 = neededRailRoad2.get(0).getColor();
	    	railroadBought2 = neededRailRoad2.get(0).getBought();
	    }
	    
	    out.println("Is the railRoad2 null?" + neededRailRoad2 == null);
	    //so if there is 1 single road only, runs normal code
	    //else if theres 2 roads, it looks at the one chosen by the player in pop-up
	    out.println("Turns Used: " +turnUsed);

			if( neededRailRoad2 == null && turnUsed == 0 )
			{
				//Requirements to build a railroad
				boolean isMountain1 = neededRailRoad.get(0).getMountains();
				boolean isGreyRoad1 = railroadColor1.equals("any");
				int plrTotalCard1 = players.get(currentPlr).getCardColor(railroadColor1) + players.get(currentPlr).getCardColor("wild");
				
				if (railroadColor1 == null) {
					plrTotalCard1 = players.get(currentPlr).getHighestColorNum();
				}
				
				
					if( isMountain1 && railroadColor1.equals("any") )
					{
						if( players.get(currentPlr).getHighestColorNum() > amountNeeded & greyGamblingColor.equals("") )
						{
							panelStuff = 7;
						}
						else if( players.get(currentPlr).getHighestColorNum() > amountNeeded && panelStuff == 7)
						{
							panelStuff = 5;
						}
						else if( players.get(currentPlr).getHighestColorNum() > amountNeeded && panelStuff == 5 )
						{
							panelStuff = 4;
								for (int i = 0; i < 3; i++) 
								{
									
									ColorCard draw = cardDeck.drawCard();
									gamblingDraw.add(draw);
								
									if (greyGamblingColor.equals(draw.getColor()) || draw.getColor().equals("wild")) 
									{
										gamblingExtraCost++;
									
									}	
								}
						}
						else if( players.get(currentPlr).getHighestColorNum() > amountNeeded && panelStuff == 4 )
						{
							out.println("It got to the last IF statement");
							if( gamblingRequiredMet || (gamblingExtraCost == 0 && gamblingConfirm))
							{
								gamblingConfirm = false;
								//Buy the railroad successfully (Will add it to UI and remove from player card later)
								for (int i = 0; i < neededRailRoad.size(); i++) {
									neededRailRoad.get(i).setBought(players.get(currentPlr), players.get(currentPlr).getPlrColor());
								}
								turnUsed = 2;
								players.get(currentPlr).setPoint(graph.getPlayerTrainPoint(players.get(currentPlr)));
								players.get(currentPlr).removeTrain(amountNeeded);
								if (players.get(currentPlr).getCardColor(railroadColor1) < amountNeeded) {
				 					discardDeck.add(players.get(currentPlr).removeCards("wild", amountNeeded - players.get(currentPlr).getCardColor(railroadColor1)));
				 					discardDeck.add(players.get(currentPlr).removeCards(railroadColor2, gamblingExtraCost));
				 				} else {
				 					int amount = players.get(currentPlr).getCardColor(railroadColor2);
				 					int leftOver = amountNeeded - amount;
				 					discardDeck.add(players.get(currentPlr).removeCards(railroadColor2, amount));
				 					discardDeck.add(players.get(currentPlr).removeCards("wild", leftOver));
				 				}
								panelStuff = 0;
								greyGamblingColor = "";
							}
							
						}
					}
					else if (isMountain1) 
					{
						if( !gamblingConfirm && !neededRailRoad.get(0).getBought() )
						{
							for (int i = 0; i < 3; i++) {
								
									ColorCard draw = cardDeck.drawCard();
									gamblingDraw.add(draw);
								
								if (railroadColor1.equals(draw.getColor()) || draw.getColor().equals("wild")) 
								{
									gamblingExtraCost++;
								}
							
						}
							panelStuff = 4;
						}
						else
						{
							//Draw additional cards needed from deck if the railroad is a mountain (3 cards)
							
								for (int i = 0; i < 3; i++) {
									if( gamblingDraw.size() ==0 )
									{
										ColorCard draw = cardDeck.drawCard();
										gamblingDraw.add(draw);
									}
									if (railroadColor1.equals(gamblingDraw.get(i).getColor()) || gamblingDraw.get(i).getColor().equals("wild")) 
									{
										gamblingExtraCost++;
									}
								
							}
							out.println("This is gamblingExtraCost: " + gamblingExtraCost + " This is gamblingConfirm: " + gamblingConfirm);
							out.println("This is what gamblingRequiredMet is: " + gamblingRequiredMet);
							if( gamblingRequiredMet || (gamblingExtraCost == 0 && gamblingConfirm))
							{
								gamblingConfirm = false;
								//Buy the railroad successfully (Will add it to UI and remove from player card later)
								for (int i = 0; i < neededRailRoad.size(); i++) {
									neededRailRoad.get(i).setBought(players.get(currentPlr), players.get(currentPlr).getPlrColor());
								}
								turnUsed = 2;
								players.get(currentPlr).setPoint(graph.getPlayerTrainPoint(players.get(currentPlr)));
								players.get(currentPlr).removeTrain(amountNeeded);
								if (players.get(currentPlr).getCardColor(railroadColor1) < amountNeeded) {
				 					discardDeck.add(players.get(currentPlr).removeCards("wild", amountNeeded - players.get(currentPlr).getCardColor(railroadColor1)));
				 					discardDeck.add(players.get(currentPlr).removeCards(railroadColor2, gamblingExtraCost));
				 				} else {
				 					int amount = players.get(currentPlr).getCardColor(railroadColor2);
				 					int leftOver = amountNeeded - amount;
				 					discardDeck.add(players.get(currentPlr).removeCards(railroadColor2, amount));
				 					discardDeck.add(players.get(currentPlr).removeCards("wild", leftOver));
				 				}
							}
							panelStuff = 0;
						}
					}
					else if( isGreyRoad1 && (players.get(currentPlr).getCardColor(players.get(currentPlr).getHighestColorNumStr()) >= amountNeeded || cardsRemoved >= amountNeeded|| players.get(currentPlr).getHighestColorNum() + players.get(currentPlr).getCardColor("wild") >= amountNeeded) )
					{
						panelStuff = 5;
						if( greyRequiredMet )
						{
							out.println("Bought it using this greyRoad being met");
								//Buy the railroad successfully (Will add it to UI and remove from player card later)
								for (int i = 0; i < neededRailRoad.size(); i++) {
									neededRailRoad.get(i).setBought(players.get(currentPlr), players.get(currentPlr).getPlrColor());
								}
								turnUsed = 2;
								players.get(currentPlr).setPoint(graph.getPlayerTrainPoint(players.get(currentPlr)));
								players.get(currentPlr).removeTrain(amountNeeded);
								out.println("Railroad bought between: " + clickedCity[0].getName() + " and " + clickedCity[1].getName() + " is bought by the player " + players.get(currentPlr).getPlayerColor());
							
							
						}
					}
					else if (players.get(currentPlr).getCardColor("wild") >= wildNeeded && plrTotalCard1 >= amountNeeded && !isMountain1 && !isGreyRoad1 && !railroadBought1) {
						out.println("Bought it using this greyRoad not	 being met");
						//Buy the railroad successfully (Will add it to UI and remove from player card later)
						for (int i = 0; i < neededRailRoad.size(); i++) {
							neededRailRoad.get(i).setBought(players.get(currentPlr), players.get(currentPlr).getPlrColor());
						}
						turnUsed = 2;
						players.get(currentPlr).setPoint(graph.getPlayerTrainPoint(players.get(currentPlr)));
						players.get(currentPlr).removeTrain(amountNeeded);
						if (players.get(currentPlr).getCardColor(railroadColor1) < amountNeeded) {
		 					discardDeck.add(players.get(currentPlr).removeCards("wild", amountNeeded-players.get(currentPlr).getCardColor(railroadColor1)));
		 					discardDeck.add(players.get(currentPlr).removeCards(railroadColor1, amountNeeded));
		 				} else {
		 					int amount = players.get(currentPlr).getCardColor(railroadColor1);
		 					int leftOver = amountNeeded - amount;
		 					discardDeck.add(players.get(currentPlr).removeCards(railroadColor1, amount));
		 					discardDeck.add(players.get(currentPlr).removeCards("wild", leftOver));
		 				}
						out.println("Railroad bought between: " + clickedCity[0].getName() + " and " + clickedCity[1].getName() + " is bought by the player " + players.get(currentPlr).getPlayerColor());
					} else {
						out.println("Not enough railroads");
					}
			}
			else if(  neededRailRoad2 != null && turnUsed == 0 )
			{
				out.println("Does the code get here 2 times " + gamblingConfirm);
				out.println(( !railroadBought1 || !railroadBought2 ) && !roadChosen && ( players.get(currentPlr).getCardColor(players.get(currentPlr).getHighestColorNumStr()) + players.get(currentPlr).getCardColor("wild") >= amountNeeded || players.get(currentPlr).getCardColor(players.get(currentPlr).getHighestColorNumStr()) + players.get(currentPlr).getCardColor("wild") >= amountNeeded ) );
				if( ( !railroadBought1 || !railroadBought2 ) &&  !gamblingConfirm && !roadChosen && ( players.get(currentPlr).getCardColor(players.get(currentPlr).getHighestColorNumStr()) + players.get(currentPlr).getCardColor("wild") >= amountNeeded || players.get(currentPlr).getCardColor(players.get(currentPlr).getHighestColorNumStr()) + players.get(currentPlr).getCardColor("wild") >= amountNeeded ) )
				{
					panelStuff = 6;
				}
				else
				{
					String railroadColor = railRoadChosen.get(0).getColor();
					boolean isMountain = railRoadChosen.get(0).getMountains();
					boolean isGreyRoad = railroadColor.equals("any");
					int plrTotalCard = players.get(currentPlr).getCardColor(railroadColor) + players.get(currentPlr).getCardColor("wild");
					
					out.println("This is plrTotal: " + plrTotalCard + " vs the amountNeeded: " + amountNeeded);
					out.println("This is wildNumber: " + players.get(currentPlr).getCardColor("wild") + " vs the wildNeeded: " + wildNeeded);
					out.println("This is isMountain: " + isMountain + " vs the isGreyRoad: " + isGreyRoad);
					
					 if (isMountain) {
						//Draw additional cards needed from deck if the railroad is a mountain (3 cards)
						for (int i = 0; i < 3; i++) {
							if( gamblingDraw.size() ==0 )
							{
								ColorCard draw = cardDeck.drawCard();
								gamblingDraw.add(draw);
							}
							if (railroadColor.equals(gamblingDraw.get(i).getColor()) || gamblingDraw.get(i).getColor().equals("wild")) 
							{
								gamblingExtraCost++;
							}
						
					}
						if( gamblingRequiredMet || (gamblingExtraCost == 0 && gamblingConfirm))
						{
							gamblingConfirm = false;
							//Buy the railroad successfully (Will add it to UI and remove from player card later)
							for (int i = 0; i < railRoadChosen.size(); i++) {
								railRoadChosen.get(i).setBought(players.get(currentPlr), players.get(currentPlr).getPlrColor());
							}
							turnUsed = 2;
							players.get(currentPlr).setPoint(graph.getPlayerTrainPoint(players.get(currentPlr)));
							players.get(currentPlr).removeTrain(amountNeeded);
							if (players.get(currentPlr).getCardColor(railroadColor) < amountNeeded) {
			 					discardDeck.add(players.get(currentPlr).removeCards("wild", amountNeeded - players.get(currentPlr).getCardColor(railroadColor)));
			 					discardDeck.add(players.get(currentPlr).removeCards(railroadColor2, gamblingExtraCost));
			 				} else {
			 					int amount = players.get(currentPlr).getCardColor(railroadColor2);
			 					int leftOver = amountNeeded - amount;
			 					discardDeck.add(players.get(currentPlr).removeCards(railroadColor2, amount));
			 					discardDeck.add(players.get(currentPlr).removeCards("wild", leftOver));
			 				}
						}
						panelStuff = 0;
						
					}
					else if( isGreyRoad && (players.get(currentPlr).getCardColor(players.get(currentPlr).getHighestColorNumStr()) >= amountNeeded || cardsRemoved >= amountNeeded|| players.get(currentPlr).getHighestColorNum() + players.get(currentPlr).getCardColor("wild") >= amountNeeded) )
					{
						panelStuff = 5;
						if( greyRequiredMet )
						{
							
								//Buy the railroad successfully (Will add it to UI and remove from player card later)
								for (int i = 0; i < railRoadChosen.size(); i++) {
									railRoadChosen.get(i).setBought(players.get(currentPlr), players.get(currentPlr).getPlrColor());
								}
								turnUsed = 2;
								players.get(currentPlr).setPoint(graph.getPlayerTrainPoint(players.get(currentPlr)));
								players.get(currentPlr).removeTrain(amountNeeded);
								out.println("Railroad bought between: " + clickedCity[0].getName() + " and " + clickedCity[1].getName() + " is bought by the player " + players.get(currentPlr).getPlayerColor());
							
						}
					}
					else if (players.get(currentPlr).getCardColor("wild") >= wildNeeded && plrTotalCard >= amountNeeded && !isMountain && !isGreyRoad && !railRoadChosen.get(0).getBought()) {
						//Buy the railroad successfully (Will add it to UI and remove from player card later)
						for (int i = 0; i < railRoadChosen.size(); i++) {
							railRoadChosen.get(i).setBought(players.get(currentPlr), players.get(currentPlr).getPlrColor());
						}
						turnUsed = 2;
						players.get(currentPlr).setPoint(graph.getPlayerTrainPoint(players.get(currentPlr)));
						players.get(currentPlr).removeTrain(amountNeeded);
						if (players.get(currentPlr).getCardColor(railroadColor2) < amountNeeded) {
		 					discardDeck.add(players.get(currentPlr).removeCards("wild", amountNeeded - players.get(currentPlr).getCardColor(railroadColor2)));
		 					discardDeck.add(players.get(currentPlr).removeCards(railroadColor2, amountNeeded));
		 				} else {
		 					int amount = players.get(currentPlr).getCardColor(railroadColor2);
		 					int leftOver = amountNeeded - amount;
		 					discardDeck.add(players.get(currentPlr).removeCards(railroadColor2, amount));
		 					discardDeck.add(players.get(currentPlr).removeCards("wild", leftOver));
		 				}
						out.println("Railroad bought between: " + clickedCity[0].getName() + " and " + clickedCity[1].getName() + " is bought by the player " + players.get(currentPlr).getPlayerColor());
					} else {
						out.println("Not enough railroads");
					}
					roadChosen = false;
				}
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
