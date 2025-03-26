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
        
        
        if(firstCityClicked)
        {
	        //highlight city when clicked
        	g2d.setStroke(new BasicStroke(4));
        	g2d.setColor(Color.YELLOW);
	        g2d.drawOval(highlightCity.get(0), highlightCity.get(1), 23, 22);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    	int x = e.getX();
    	int y = e.getY();
    	System.out.println(x + " " + (y));
    	//alow for players to draw cards from the face-up card options
    	for (int i = 0; i < 5; i++) {
    		if (x > getWidth()-360 && x < getWidth()-240 && y> 220+i*80 && y<300+i*80) {
    			players.get(currentPlr).addCard(faceUpCard[i]);
    			faceUpCard[i] = cardDeck.drawCard();
    		}
        }
    	
    	//allow for players to draw cards from the pile
    	if( x >= 1550 && x <= 1650 && y >= 3 && y <= 163)
    	{
    		players.get(currentPlr).addCard(cardDeck.drawCard());
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
    	
    	//allow for player to click on a 2 cities to buy them
    	//coordinates for each point
    	//frankfurt: 644<=x<=669 || 490<=y<=511
    	//brest: 202<=x<=227 || 478<=y<=499
    	//london: 341<=x<=366 || 313<=y<=334
    	//essen: 584<=x<=609 || 332<=y<=353
    	//berlin: 722<=x<=747 || 350<=y<=371
    	//wien: 801<=x<=826 || 510<=y<=531
    	//zurich: 552<=x<=577 || 573<=y<=594
    	//marseille: 513<=x<=538 || 703<=y<=724
    	//roma: 673<=x<=698 || 747<=y<=768
    	//brindisi: 790<=x<=815 || 781<=y<=802
    	//sofia: 987<=x<=1012 || 729<=y<=750
    	//kobenhavn: 678<=x<=703 || 190<=y<=211
    	//amsterdam: 472<=x<=497 || 319<=y<=340
    	//dieppe: 322<=x<=347 || 438<=y<=459
    	//paris: 390<=x<=415 || 499<=y<=520
    	//bruxelles: 439<=x<=464 || 382<=y<=403
    	//munchen: 641<=x<=666 || 485<=y<=506
    	//athina: 967<=x<=992 || 885<=y<=906
    	//madrid: 167<=x<=192 || 923<=y<=944
    	//cadiz: 321<=x<=346 || 842<=y<=863
    	//barcelona: 304<=x<=329 || 712<=y<=733
    	//pamplona: 1143<=x<=1168 || 815<=y<=836
    	//constantinople: 860<=x<=885 || 550<=y<=571
    	//budapest: 1073<=x<=1098 || 644<=y<=665
    	//bucuresti: 1264<=x<=1289 || 662<=y<=683
    	//sevastopol: 1336<=x<=1361 || 486<=y<=507
    	//kharkov: 1155<=x<=1180 || 412<=y<=433
    	//kyiv: 1360<=x<=1385 || 278<=y<=299
    	//moskva: 1227<=x<=1252 || 112<=y<=133
    	//petrograd: 995<=x<=1020 || 124<=y<=145
    	//rica: 682<=x<=707 || 189<=y<=210
    	//danzic: 875<=x<=900 || 246<=y<=267
    	//edinburgh: 248<=x<=273 || 111<=y<=132
    	//lisboa: 73<=x<=98 || 856<=y<=877
    	//zagrab: 784<=x<=809 || 638<=y<=659
    	//sarajevo: 897<=x<=922 || 717<=y<=738
    	//palermo: 730<=x<=755 || 927<=y<=948
    	//smyrna: 1084<=x<=1109 || 921<=y<=942
    	//angora: 1249<=x<=1274 || 888<=y<=909
    	//erzurum: 1357<=x<=1382 || 855<=y<=876
    	//sochi: 1385<=x<=1410 || 683<=y<=704
    	//rostov: 1393<=x<=1418 || 562<=y<=583
    	//stockholm: 822<=x<=847 || 81<=y<=102
    	//wilno: 1098<=x<=1123 || 308<=y<=329
    	//
    	//
    	//frankfurt
    	if (568 <= x && x <= 603 && 433 <= y && y <= 462)
    	{
    	    if(firstCityClicked == false)
    	    {
    	    	firstCityClicked = true;
    	    	highlightCity.add(567);
    	    	highlightCity.add(430);
    	    	citiesToBuy[0] = new City("frankfurt");
    	    }
    	    else 
    	    {
    	    	firstCityClicked = false;
    	    	highlightCity = new ArrayList<Integer>();
    	    }
    	}
    	
    	//brest
    	if (202 <= x && x <= 227 && 478 <= y && y <= 499) {
    		if(firstCityClicked == false)
    	    {
    	    	firstCityClicked = true;
    	    	highlightCity.add(205);
    	    	highlightCity.add(479);
    	    }
    	    else 
    	    {
    	    	firstCityClicked = false;
    	    	highlightCity = new ArrayList<Integer>();
    	    }
    	    System.out.println("brest");
    	}

    	//london
    	if (341 <= x && x <= 366 && 313 <= y && y <= 334) {
    		if(firstCityClicked == false)
    	    {
    	    	firstCityClicked = true;
    	    	highlightCity.add(343);
    	    	highlightCity.add(318);
    	    }
    	    else 
    	    {
    	    	firstCityClicked = false;
    	    	highlightCity = new ArrayList<Integer>();
    	    }
    	    System.out.println("london");
    	}

    	//essen
    	if (584 <= x && x <= 609 && 332 <= y && y <= 353) {
    		if(firstCityClicked == false)
    	    {
    	    	firstCityClicked = true;
    	    	highlightCity.add(588);
    	    	highlightCity.add(334);
    	    }
    	    else 
    	    {
    	    	firstCityClicked = false;
    	    	highlightCity = new ArrayList<Integer>();
    	    }
    	    System.out.println("essen");
    	}

    	//berlin
    	if (722 <= x && x <= 747 && 350 <= y && y <= 371) {
    		if(firstCityClicked == false)
    	    {
    	    	firstCityClicked = true;
    	    	highlightCity.add(727);
    	    	highlightCity.add(354);
    	    }
    	    else 
    	    {
    	    	firstCityClicked = false;
    	    	highlightCity = new ArrayList<Integer>();
    	    }
    	    System.out.println("berlin");
    	}

    	//wien
    	if (801 <= x && x <= 826 && 510 <= y && y <= 531) {
    		if(firstCityClicked == false)
    	    {
    	    	firstCityClicked = true;
    	    	highlightCity.add(804);
    	    	highlightCity.add(513);
    	    }
    	    else 
    	    {
    	    	firstCityClicked = false;
    	    	highlightCity = new ArrayList<Integer>();
    	    }
    	    System.out.println("wien");
    	}

    	//zurich
    	if (552 <= x && x <= 577 && 573 <= y && y <= 594) {
    		if(firstCityClicked == false)
    	    {
    	    	firstCityClicked = true;
    	    	highlightCity.add(555);
    	    	highlightCity.add(577);
    	    }
    	    else 
    	    {
    	    	firstCityClicked = false;
    	    	highlightCity = new ArrayList<Integer>();
    	    }
    	    System.out.println("zurich");
    	}

    	//marseille
    	if (513 <= x && x <= 538 && 703 <= y && y <= 724) {
    		if(firstCityClicked == false)
    	    {
    	    	firstCityClicked = true;
    	    	highlightCity.add(518);
    	    	highlightCity.add(707);
    	    }
    	    else 
    	    {
    	    	firstCityClicked = false;
    	    	highlightCity = new ArrayList<Integer>();
    	    }
    	    System.out.println("marseille");
    	}

    	//roma
    	if (673 <= x && x <= 698 && 747 <= y && y <= 768) {
    		if(firstCityClicked == false)
    	    {
    	    	firstCityClicked = true;
    	    	highlightCity.add(681);
    	    	highlightCity.add(746);
    	    }
    	    else 
    	    {
    	    	firstCityClicked = false;
    	    	highlightCity = new ArrayList<Integer>();
    	    }
    	    System.out.println("roma");
    	}

    	//brindisi
    	if (790 <= x && x <= 815 && 781 <= y && y <= 802) {
    		if(firstCityClicked == false)
    	    {
    	    	firstCityClicked = true;
    	    	highlightCity.add(799);
    	    	highlightCity.add(781);
    	    }
    	    else 
    	    {
    	    	firstCityClicked = false;
    	    	highlightCity = new ArrayList<Integer>();
    	    }
    	    System.out.println("brindisi");
    	}

    	//sofia
    	if (987 <= x && x <= 1012 && 729 <= y && y <= 750) {
    		if(firstCityClicked == false)
    	    {
    	    	firstCityClicked = true;
    	    	highlightCity.add(994);
    	    	highlightCity.add(731);
    	    }
    	    else 
    	    {
    	    	firstCityClicked = false;
    	    	highlightCity = new ArrayList<Integer>();
    	    }
    	    System.out.println("sofia");
    	}

    	//kobenhavn
    	if (678 <= x && x <= 703 && 190 <= y && y <= 211) {
    		if(firstCityClicked == false)
    	    {
    	    	firstCityClicked = true;
    	    	highlightCity.add(688);
    	    	highlightCity.add(189);
    	    }
    	    else 
    	    {
    	    	firstCityClicked = false;
    	    	highlightCity = new ArrayList<Integer>();
    	    }
    	    System.out.println("kobenhavn");
    	}

    	//amsterdam
    	if (472 <= x && x <= 497 && 319 <= y && y <= 340) {
    		if(firstCityClicked == false)
    	    {
    	    	firstCityClicked = true;
    	    	highlightCity.add(477);
    	    	highlightCity.add(321);
    	    }
    	    else 
    	    {
    	    	firstCityClicked = false;
    	    	highlightCity = new ArrayList<Integer>();
    	    }
    	    System.out.println("amsterdam");
    	}

    	//dieppe
    	if (322 <= x && x <= 347 && 438 <= y && y <= 459) {
    		if(firstCityClicked == false)
    	    {
    	    	firstCityClicked = true;
    	    	highlightCity.add(331);
    	    	highlightCity.add(440);
    	    }
    	    else 
    	    {
    	    	firstCityClicked = false;
    	    	highlightCity = new ArrayList<Integer>();
    	    }
    	    System.out.println("dieppe");
    	}

    	//paris
    	if (390 <= x && x <= 415 && 499 <= y && y <= 520) {
    		if(firstCityClicked == false)
    	    {
    	    	firstCityClicked = true;
    	    	highlightCity.add(394);
    	    	highlightCity.add(501);
    	    }
    	    else 
    	    {
    	    	firstCityClicked = false;
    	    	highlightCity = new ArrayList<Integer>();
    	    }
    	    System.out.println("paris");
    	}

    	//bruxelles
    	if (439 <= x && x <= 464 && 382 <= y && y <= 403) {
    		if(firstCityClicked == false)
    	    {
    	    	firstCityClicked = true;
    	    	highlightCity.add(446);
    	    	highlightCity.add(387);
    	    }
    	    else 
    	    {
    	    	firstCityClicked = false;
    	    	highlightCity = new ArrayList<Integer>();
    	    }
    	    System.out.println("bruxelles");
    	}

    	//munchen
    	if (641 <= x && x <= 666 && 485 <= y && y <= 506) {
    		if(firstCityClicked == false)
    	    {
    	    	firstCityClicked = true;
    	    	highlightCity.add(646);
    	    	highlightCity.add(490);
    	    }
    	    else 
    	    {
    	    	firstCityClicked = false;
    	    	highlightCity = new ArrayList<Integer>();
    	    }
    	    System.out.println("munchen");
    	}

    	//athina
    	if (967 <= x && x <= 992 && 885 <= y && y <= 906) {
    		if(firstCityClicked == false)
    	    {
    	    	firstCityClicked = true;
    	    	highlightCity.add(967);
    	    	highlightCity.add(887);
    	    }
    	    else 
    	    {
    	    	firstCityClicked = false;
    	    	highlightCity = new ArrayList<Integer>();
    	    }
    	    System.out.println("athina");
    	}

    	//madrid
    	if (169 <= x && x <= 194 && 816 <= y && y <= 847) {
    		if(firstCityClicked == false)
    	    {
    	    	firstCityClicked = true;
    	    	highlightCity.add(170);
    	    	highlightCity.add(824);
    	    }
    	    else 
    	    {
    	    	firstCityClicked = false;
    	    	highlightCity = new ArrayList<Integer>();
    	    }
    	    System.out.println("madrid");
    	}

    	//cadiz
    	if (167 <= x && x <= 192 && 923 <= y && y <= 944) {
    		if(firstCityClicked == false)
    	    {
    	    	firstCityClicked = true;
    	    	highlightCity.add(170);
    	    	highlightCity.add(926);
    	    }
    	    else 
    	    {
    	    	firstCityClicked = false;
    	    	highlightCity = new ArrayList<Integer>();
    	    }
    	    System.out.println("cadiz");
    	}

    	//barcelona
    	if (321 <= x && x <= 346 && 842 <= y && y <= 863) {
    		if(firstCityClicked == false)
    	    {
    	    	firstCityClicked = true;
    	    	highlightCity.add(324);
    	    	highlightCity.add(838);
    	    }
    	    else 
    	    {
    	    	firstCityClicked = false;
    	    	highlightCity = new ArrayList<Integer>();
    	    }
    	    System.out.println("barcelona");
    	}

    	//pamplona
    	if (304 <= x && x <= 329 && 712 <= y && y <= 733) {
    		if(firstCityClicked == false)
    	    {
    	    	firstCityClicked = true;
    	    	highlightCity.add(308);
    	    	highlightCity.add(712);
    	    }
    	    else 
    	    {
    	    	firstCityClicked = false;
    	    	highlightCity = new ArrayList<Integer>();
    	    }
    	    System.out.println("pamplona");
    	}

    	//constantinople
    	if (1143 <= x && x <= 1168 && 815 <= y && y <= 836) {
    		if(firstCityClicked == false)
    	    {
    	    	firstCityClicked = true;
    	    	highlightCity.add(1148);
    	    	highlightCity.add(814);
    	    }
    	    else 
    	    {
    	    	firstCityClicked = false;
    	    	highlightCity = new ArrayList<Integer>();
    	    }
    	    System.out.println("constantinople");
    	}

    	//budapest
    	if (860 <= x && x <= 885 && 550 <= y && y <= 571) {
    		if(firstCityClicked == false)
    	    {
    	    	firstCityClicked = true;
    	    	highlightCity.add(870);
    	    	highlightCity.add(547);
    	    }
    	    else 
    	    {
    	    	firstCityClicked = false;
    	    	highlightCity = new ArrayList<Integer>();
    	    }
    	    System.out.println("budapest");
    	}

    	//bucuresti
    	if (1073 <= x && x <= 1098 && 644 <= y && y <= 665) {
    		if(firstCityClicked == false)
    	    {
    	    	firstCityClicked = true;
    	    	highlightCity.add(1078);
    	    	highlightCity.add(644);
    	    }
    	    else 
    	    {
    	    	firstCityClicked = false;
    	    	highlightCity = new ArrayList<Integer>();
    	    }
    	    System.out.println("bucuresti");
    	}

    	//sevastopol
    	if (1264 <= x && x <= 1289 && 662 <= y && y <= 683) {
    		if(firstCityClicked == false)
    	    {
    	    	firstCityClicked = true;
    	    	highlightCity.add(1267);
    	    	highlightCity.add(665);
    	    }
    	    else 
    	    {
    	    	firstCityClicked = false;
    	    	highlightCity = new ArrayList<Integer>();
    	    }
    	    System.out.println("sevastopol");
    	}

    	//kharkov
    	if (1336 <= x && x <= 1361 && 486 <= y && y <= 507) {
    		if(firstCityClicked == false)
    	    {
    	    	firstCityClicked = true;
    	    	highlightCity.add(1342);
    	    	highlightCity.add(488);
    	    }
    	    else 
    	    {
    	    	firstCityClicked = false;
    	    	highlightCity = new ArrayList<Integer>();
    	    }
    	    System.out.println("kharkov");
    	}

    	//kyiv
    	if (1153 <= x && x <= 1178 && 416 <= y && y <= 437) {
    		if(firstCityClicked == false)
    	    {
    	    	firstCityClicked = true;
    	    	highlightCity.add(1156);
    	    	highlightCity.add(414);
    	    }
    	    else 
    	    {
    	    	firstCityClicked = false;
    	    	highlightCity = new ArrayList<Integer>();
    	    }
    	    System.out.println("kyiv");
    	}

    	//moskva
    	if (1360 <= x && x <= 1385 && 278 <= y && y <= 299) {
    		if(firstCityClicked == false)
    	    {
    	    	firstCityClicked = true;
    	    	highlightCity.add(1360);
    	    	highlightCity.add(281);
    	    }
    	    else 
    	    {
    	    	firstCityClicked = false;
    	    	highlightCity = new ArrayList<Integer>();
    	    }
    	    System.out.println("moskva");
    	}

    	//petrograd
    	if (1227 <= x && x <= 1252 && 112 <= y && y <= 133) {
    		if(firstCityClicked == false)
    	    {
    	    	firstCityClicked = true;
    	    	highlightCity.add(1227);
    	    	highlightCity.add(117);
    	    }
    	    else 
    	    {
    	    	firstCityClicked = false;
    	    	highlightCity = new ArrayList<Integer>();
    	    }
    	    System.out.println("petrograd");
    	}

    	//rica
    	if (994 <= x && x <= 1019 && 125 <= y && y <= 146) {
    		if(firstCityClicked == false)
    	    {
    	    	firstCityClicked = true;
    	    	highlightCity.add(995);
    	    	highlightCity.add(123);
    	    }
    	    else 
    	    {
    	    	firstCityClicked = false;
    	    	highlightCity = new ArrayList<Integer>();
    	    }
    	    System.out.println("rica");
    	}

    	//danzic
    	if (875 <= x && x <= 900 && 246 <= y && y <= 267) {
    		if(firstCityClicked == false)
    	    {
    	    	firstCityClicked = true;
    	    	highlightCity.add(882);
    	    	highlightCity.add(247);
    	    }
    	    else 
    	    {
    	    	firstCityClicked = false;
    	    	highlightCity = new ArrayList<Integer>();
    	    }
    	    System.out.println("danzic");
    	}

    	//edinburgh
    	if (248 <= x && x <= 273 && 111 <= y && y <= 132) {
    		if(firstCityClicked == false)
    	    {
    	    	firstCityClicked = true;
    	    	highlightCity.add(252);
    	    	highlightCity.add(112);
    	    }
    	    else 
    	    {
    	    	firstCityClicked = false;
    	    	highlightCity = new ArrayList<Integer>();
    	    }
    	    System.out.println("edinburgh");
    	}

    	//lisboa
    	if (73 <= x && x <= 98 && 856 <= y && y <= 877) {
    		if(firstCityClicked == false)
    	    {
    	    	firstCityClicked = true;
    	    	highlightCity.add(79);
    	    	highlightCity.add(854);
    	    }
    	    else 
    	    {
    	    	firstCityClicked = false;
    	    	highlightCity = new ArrayList<Integer>();
    	    }
    	    System.out.println("lisboa");
    	}

    	//zagrab
    	if (784 <= x && x <= 809 && 638 <= y && y <= 659) {
    		if(firstCityClicked == false)
    	    {
    	    	firstCityClicked = true;
    	    	highlightCity.add(785);
    	    	highlightCity.add(638);
    	    }
    	    else 
    	    {
    	    	firstCityClicked = false;
    	    	highlightCity = new ArrayList<Integer>();
    	    }
    	    System.out.println("zagrab");
    	}

    	//sarajevo
    	if (897 <= x && x <= 922 && 717 <= y && y <= 738) {
    		if(firstCityClicked == false)
    	    {
    	    	firstCityClicked = true;
    	    	highlightCity.add(901);
    	    	highlightCity.add(719);
    	    }
    	    else 
    	    {
    	    	firstCityClicked = false;
    	    	highlightCity = new ArrayList<Integer>();
    	    }
    	    System.out.println("sarajevo");
    	}

    	//palermo
    	if (730 <= x && x <= 755 && 927 <= y && y <= 948) {
    		if(firstCityClicked == false)
    	    {
    	    	firstCityClicked = true;
    	    	highlightCity.add(731);
    	    	highlightCity.add(927);
    	    }
    	    else 
    	    {
    	    	firstCityClicked = false;
    	    	highlightCity = new ArrayList<Integer>();
    	    }
    	    System.out.println("palermo");
    	}

    	//smyrna
    	if (1084 <= x && x <= 1109 && 921 <= y && y <= 942) {
    		if(firstCityClicked == false)
    	    {
    	    	firstCityClicked = true;
    	    	highlightCity.add(1087);
    	    	highlightCity.add(921);
    	    }
    	    else 
    	    {
    	    	firstCityClicked = false;
    	    	highlightCity = new ArrayList<Integer>();
    	    }
    	    System.out.println("smyrna");
    	}

    	//angora
    	if (1249 <= x && x <= 1274 && 888 <= y && y <= 909) {
    		if(firstCityClicked == false)
    	    {
    	    	firstCityClicked = true;
    	    	highlightCity.add(1253);
    	    	highlightCity.add(889);
    	    }
    	    else 
    	    {
    	    	firstCityClicked = false;
    	    	highlightCity = new ArrayList<Integer>();
    	    }
    	    System.out.println("angora");
    	}

    	//erzurum
    	if (1357 <= x && x <= 1382 && 855 <= y && y <= 876) {
    		if(firstCityClicked == false)
    	    {
    	    	firstCityClicked = true;
    	    	highlightCity.add(1364);
    	    	highlightCity.add(856);
    	    }
    	    else 
    	    {
    	    	firstCityClicked = false;
    	    	highlightCity = new ArrayList<Integer>();
    	    }
    	    System.out.println("erzurum");
    	}

    	//sochi
    	if (1385 <= x && x <= 1410 && 683 <= y && y <= 704) {
    		if(firstCityClicked == false)
    	    {
    	    	firstCityClicked = true;
    	    	highlightCity.add(1389);
    	    	highlightCity.add(684);
    	    }
    	    else 
    	    {
    	    	firstCityClicked = false;
    	    	highlightCity = new ArrayList<Integer>();
    	    }
    	    System.out.println("sochi");
    	}

    	//rostov
    	if (1393 <= x && x <= 1418 && 562 <= y && y <= 583) {
    		if(firstCityClicked == false)
    	    {
    	    	firstCityClicked = true;
    	    	highlightCity.add(1395);
    	    	highlightCity.add(561);
    	    }
    	    else 
    	    {
    	    	firstCityClicked = false;
    	    	highlightCity = new ArrayList<Integer>();
    	    }
    	    System.out.println("rostov");
    	}

    	//stockholm
    	if (822 <= x && x <= 847 && 81 <= y && y <= 102) {
    		if(firstCityClicked == false)
    	    {
    	    	firstCityClicked = true;
    	    	highlightCity.add(827);
    	    	highlightCity.add(82);
    	    }
    	    else 
    	    {
    	    	firstCityClicked = false;
    	    	highlightCity = new ArrayList<Integer>();
    	    }
    	    System.out.println("stockholm");
    	}

    	//wilno
    	if (1098 <= x && x <= 1123 && 308 <= y && y <= 329) {
    		if(firstCityClicked == false)
    	    {
    	    	firstCityClicked = true;
    	    	highlightCity.add(1103);
    	    	highlightCity.add(307);
    	    }
    	    else 
    	    {
    	    	firstCityClicked = false;
    	    	highlightCity = new ArrayList<Integer>();
    	    }
    	    System.out.println("wilno");
    	}
    	
    	//warszawa
    	if (946 <= x && x <= 971 && 344 <= y && y <= 365)
    	{
    		if(firstCityClicked == false)
    	    {
    	    	firstCityClicked = true;
    	    	highlightCity.add(950);
    	    	highlightCity.add(343);
    	    }
    	    else 
    	    {
    	    	firstCityClicked = false;
    	    	highlightCity = new ArrayList<Integer>();
    	    }
    		System.out.println("warszawa");
    	}
    	
    	//venezia
    	if (667 <= x && x <= 693 && 619 <= y && y <= 640)
    	{
    		if(firstCityClicked == false)
    	    {
    	    	firstCityClicked = true;
    	    	highlightCity.add(668);
    	    	highlightCity.add(622);
    	    }
    	    else 
    	    {
    	    	firstCityClicked = false;
    	    	highlightCity = new ArrayList<Integer>();
    	    }
    		System.out.println("venezia");
    	}

    	
    	repaint();
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
