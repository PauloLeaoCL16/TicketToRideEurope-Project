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
    HashMap<String, ArrayList<Integer>> coordMap;// stores all the coordinates of the cities in a map
    private boolean[] turns = {true, false, false, false};
    
    private int currentPlr;
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
            longRoutes = new ArrayList<>();
            longRoutes.add(new Ticket("palermo", "moskva",20,ImageIO.read(MainMenu.class.getResource("/ttreImages/longRoute1.png"))));
            longRoutes.add(new Ticket("brest", "petrograd",20,ImageIO.read(MainMenu.class.getResource("/ttreImages/longRoute2.png"))));
            longRoutes.add(new Ticket("kobenhavwn", "erzurum",21,ImageIO.read(MainMenu.class.getResource("/ttreImages/longRoute3.png"))));
            longRoutes.add(new Ticket("cadiz", "sotckholm",21,ImageIO.read(MainMenu.class.getResource("/ttreImages/longRoute4.png"))));
            longRoutes.add(new Ticket("lisboa", "danzic",20,ImageIO.read(MainMenu.class.getResource("/ttreImages/longRoute5.png"))));
            longRoutes.add(new Ticket("edinburch", "athina",20,ImageIO.read(MainMenu.class.getResource("/ttreImages/longRoute6.png"))));

        } catch (IOException e) {
            e.printStackTrace();
        }
        currentPlr = 0;
        
        cardDeck = new CardDeck();
        ticketDeck = new TicketDeck();
        faceUpCard = new ColorCard[5];
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
        players.get(currentPlr).addTicket(longRoutes.get(currentPlr));
        
        firstCityClicked = false;
        highlightCity = new ArrayList<Integer>();
        citiesToBuy = new City[2];
        coordMap = new HashMap<String, ArrayList<Integer>>();
        initiateCoords();
        
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
        
        if(turns[0])
        {
        	//current player number
        	g2d.drawImage(player1, 1825, 948, null);
        		//current player arrow
        	g2d.drawImage(playerpointer, 1875, 270, null);
        	//current player's color
        	g2d.drawImage(redplayer, 1520, 925, null);
        }
        else if(turns[1])
        {
        	//current player number
        	g2d.drawImage(player2, 1825, 948, null);
        		//current player arrow
        	g2d.drawImage(playerpointer, 1875, 370, null);
        	//current player's color
        	g2d.drawImage(blueplayer, 1520, 925, null);
        }
        else if(turns[2])
        {
        	//current player number
        	g2d.drawImage(player3, 1825, 948, null);
        		//current player arrow
        	g2d.drawImage(playerpointer, 1875, 470, null);
        	//current player's color
        	g2d.drawImage(greenplayer, 1520, 925, null);
        }
        else if(turns[3])
        {
        	//current player number
        	g2d.drawImage(player4, 1825, 948, null);
        		//current player arrow
        	g2d.drawImage(playerpointer, 1875, 570, null);
        	//current player's color
        	g2d.drawImage(yellowplayer, 1520, 925, null);
        }
        
        if(firstCityClicked)
        {
	        //highlight city when clicked
        	g2d.setStroke(new BasicStroke(4));
        	g2d.setColor(Color.YELLOW);
	        g2d.drawOval(highlightCity.get(0), highlightCity.get(1), 23, 22);
        }
        
        //skip turn button for testing
        g2d.setColor(Color.BLACK);
        g2d.fillRect(1275, 10, 200, 50);
        g2d.setColor(Color.WHITE);
        g2d.drawString("skip turn", 1325, 40);
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
    public void mouseClicked(MouseEvent e) {
    	int x = e.getX();
    	int y = e.getY();
    	System.out.println(x + " " + (y));
    	if(e.getButton() == MouseEvent.BUTTON1)// checks if the player left clicked	
    	{
    		//click skip turn button for testing
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
	    	}
	    	
	        
	    	//allow for players to click on their tickets to check them
	    	if( x >=1820 && x <= 1885 && y >=798 && y <= 895)
	    	{
	    		out.println(players.get(currentPlr).getTicket().get(0).getToCity());
	    		out.println(players.get(currentPlr).getTicket().get(0).getFromCity());
	    		out.println(players.get(currentPlr).getTicket().get(0).getPoints());
	    	}
	    	
	    	
	    	//checks which city player clicked on and highlights it
	    	Iterator<String> iterator = coordMap.keySet().iterator();// list of the city names
	    	int i=0;
	    	while (iterator.hasNext())// loop neccessary for looping through the list
	    	{
	    		String city = iterator.next();
		    	if( coordMap.get(city).get(0)< x && coordMap.get(city).get(0)+25 > x && coordMap.get(city).get(1) < y && coordMap.get(city).get(1)+21 > y  )//checks where the X and Y Location is compared to the actual city locations
				{
	    			if(firstCityClicked == false)
	        	    {
	        	    	firstCityClicked = true;
	        	    	highlightCity.add(coordMap.get(city).get(0));
	        	    	highlightCity.add(coordMap.get(city).get(1));
	        	    	citiesToBuy[0] = new City(city);// I think this is usefull because you can use this to then call the needed methods and you would know what cities to put into the player object. ---->
	        	    	// you would only use as an easier way to keep track of the 2 clicked cities that the player wants to buy
	        	    }
	        	    else 
	        	    {
	        	    	firstCityClicked = false;
	        	    	highlightCity = new ArrayList<Integer>();
	        	    }
				}
		    	i++;
	    	}
    	}
    	else// this is where you write what happens if the player right clicks
    	{
    		
    	}
	   
    	repaint();
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
    	coordMap.put( "frankfurt" , new ArrayList<Integer>() );
    	coordMap.get("frankfurt").add(568);  
    	coordMap.get("frankfurt").add(433);  

    	coordMap.put( "brest", new ArrayList<Integer>() );
    	coordMap.get("brest").add(341);
    	coordMap.get("brest").add(313);

    	coordMap.put( "london", new ArrayList<Integer>() );
    	coordMap.get("london").add(341);
    	coordMap.get("london").add(313);

    	coordMap.put( "essen", new ArrayList<Integer>() );
    	coordMap.get("essen").add(584);
    	coordMap.get("essen").add(332);

    	coordMap.put( "berlin", new ArrayList<Integer>() );
    	coordMap.get("berlin").add(722);
    	coordMap.get("berlin").add(350);

    	coordMap.put( "wien", new ArrayList<Integer>() );
    	coordMap.get("wien").add(801);
    	coordMap.get("wien").add(510);

    	coordMap.put( "zurich", new ArrayList<Integer>() );
    	coordMap.get("zurich").add(552);
    	coordMap.get("zurich").add(573);

    	coordMap.put( "marseille", new ArrayList<Integer>() );
    	coordMap.get("marseille").add(513);
    	coordMap.get("marseille").add(703);

    	coordMap.put( "roma", new ArrayList<Integer>() );
    	coordMap.get("roma").add(673);
    	coordMap.get("roma").add(747);

    	coordMap.put( "brindisi", new ArrayList<Integer>() );
    	coordMap.get("brindisi").add(790);
    	coordMap.get("brindisi").add(781);

    	coordMap.put( "sofia", new ArrayList<Integer>() );
    	coordMap.get("sofia").add(987);
    	coordMap.get("sofia").add(729);

    	coordMap.put( "kobenhavn", new ArrayList<Integer>() );
    	coordMap.get("kobenhavn").add(678);
    	coordMap.get("kobenhavn").add(190);

    	coordMap.put( "amsterdam", new ArrayList<Integer>() );
    	coordMap.get("amsterdam").add(472);
    	coordMap.get("amsterdam").add(319);

    	coordMap.put( "dieppe", new ArrayList<Integer>() );
    	coordMap.get("dieppe").add(322);
    	coordMap.get("dieppe").add(438);

    	coordMap.put( "paris", new ArrayList<Integer>() );
    	coordMap.get("paris").add(390);
    	coordMap.get("paris").add(499);

    	coordMap.put( "bruxelles", new ArrayList<Integer>() );
    	coordMap.get("bruxelles").add(439);
    	coordMap.get("bruxelles").add(382);

    	coordMap.put( "munchen", new ArrayList<Integer>() );
    	coordMap.get("munchen").add(641);
    	coordMap.get("munchen").add(485);

    	coordMap.put( "athina", new ArrayList<Integer>() );
    	coordMap.get("athina").add(967);
    	coordMap.get("athina").add(885);

    	coordMap.put( "madrid", new ArrayList<Integer>() );
    	coordMap.get("madrid").add(169);
    	coordMap.get("madrid").add(816);

    	coordMap.put( "cadiz", new ArrayList<Integer>() );
    	coordMap.get("cadiz").add(167);
    	coordMap.get("cadiz").add(923);

    	coordMap.put( "barcelona", new ArrayList<Integer>() );
    	coordMap.get("barcelona").add(321);
    	coordMap.get("barcelona").add(842);

    	coordMap.put( "pamplona", new ArrayList<Integer>() );
    	coordMap.get("pamplona").add(304);
    	coordMap.get("pamplona").add(712);

    	coordMap.put( "constantinople", new ArrayList<Integer>() );
    	coordMap.get("constantinople").add(1143);
    	coordMap.get("constantinople").add(815);

    	coordMap.put( "budapest", new ArrayList<Integer>() );
    	coordMap.get("budapest").add(860);
    	coordMap.get("budapest").add(550);

    	coordMap.put( "bucuresti", new ArrayList<Integer>() );
    	coordMap.get("bucuresti").add(1073);
    	coordMap.get("bucuresti").add(644);

    	coordMap.put( "sevastopol", new ArrayList<Integer>() );
    	coordMap.get("sevastopol").add(1264);
    	coordMap.get("sevastopol").add(662);

    	coordMap.put( "kharkov", new ArrayList<Integer>() );
    	coordMap.get("kharkov").add(1336);
    	coordMap.get("kharkov").add(486);

    	coordMap.put( "kyiv", new ArrayList<Integer>() );
    	coordMap.get("kyiv").add(1153);
    	coordMap.get("kyiv").add(416);

    	coordMap.put( "moskva", new ArrayList<Integer>() );
    	coordMap.get("moskva").add(1360);
    	coordMap.get("moskva").add(278);

    	coordMap.put( "petrograd", new ArrayList<Integer>() );
    	coordMap.get("petrograd").add(1227);
    	coordMap.get("petrograd").add(112);

    	coordMap.put( "rica", new ArrayList<Integer>() );
    	coordMap.get("rica").add(994);
    	coordMap.get("rica").add(125);

    	coordMap.put( "danzic", new ArrayList<Integer>() );
    	coordMap.get("danzic").add(875);
    	coordMap.get("danzic").add(246);

    	coordMap.put( "edinburgh", new ArrayList<Integer>() );
    	coordMap.get("edinburgh").add(248);
    	coordMap.get("edinburgh").add(111);

    	coordMap.put( "lisboa", new ArrayList<Integer>() );
    	coordMap.get("lisboa").add(73);
    	coordMap.get("lisboa").add(856);

    	coordMap.put( "zagrab", new ArrayList<Integer>() );
    	coordMap.get("zagrab").add(784);
    	coordMap.get("zagrab").add(638);

    	coordMap.put( "sarajevo", new ArrayList<Integer>() );
    	coordMap.get("sarajevo").add(897);
    	coordMap.get("sarajevo").add(717);

    	coordMap.put( "palermo", new ArrayList<Integer>() );
    	coordMap.get("palermo").add(730);
    	coordMap.get("palermo").add(927);

    	coordMap.put( "smryna", new ArrayList<Integer>() );
    	coordMap.get("smryna").add(1084);
    	coordMap.get("smryna").add(921);

    	coordMap.put( "angora", new ArrayList<Integer>() );
    	coordMap.get("angora").add(1249);
    	coordMap.get("angora").add(888);

    	coordMap.put( "erzurum", new ArrayList<Integer>() );
    	coordMap.get("erzurum").add(1357);
    	coordMap.get("erzurum").add(855);

    	coordMap.put( "sochi", new ArrayList<Integer>() );
    	coordMap.get("sochi").add(1385);
    	coordMap.get("sochi").add(683);

    	coordMap.put( "rostov", new ArrayList<Integer>() );
    	coordMap.get("rostov").add(1393);
    	coordMap.get("rostov").add(562);

    	coordMap.put( "stockholm", new ArrayList<Integer>() );
    	coordMap.get("stockholm").add(822);
    	coordMap.get("stockholm").add(81);

    	coordMap.put( "wilno", new ArrayList<Integer>() );
    	coordMap.get("wilno").add(1098);
    	coordMap.get("wilno").add(308);
    	
    	coordMap.put( "warszawa", new ArrayList<Integer>() );
    	coordMap.get("warszawa").add(961);
    	coordMap.get("warszawa").add(353);
    	
    	coordMap.put( "venezia", new ArrayList<Integer>() );
    	coordMap.get("venezia").add(682);
    	coordMap.get("venezia").add(633);
         

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
