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

public class GameBoard extends JPanel implements Runnable, MouseListener, MouseMotionListener {
    private BufferedImage table, board, player1, player2, player3, player4, cardBack, ticket, template, redplayer, blueplayer, greenplayer, yellowplayer, playerpointer;
    private boolean isPlayButtonHovered = false;
    private boolean isRulesScrollHovered = false;
    private ColorCard[] faceUpCard;
    private CardDeck cardDeck;

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
        } catch (IOException e) {
            e.printStackTrace();
        }
        cardDeck = new CardDeck();
        faceUpCard = new ColorCard[5];
        faceUpCard[0] = cardDeck.drawCard();
        faceUpCard[1] = cardDeck.drawCard();
        faceUpCard[2] = cardDeck.drawCard();
        faceUpCard[3] = cardDeck.drawCard();
        faceUpCard[4] = cardDeck.drawCard();
        
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
        g2d.scale(0.8, 0.8);
        g2d.rotate(Math.toRadians(-90), 1520, 240);
        g2d.drawImage(faceUpCard[0].getImage(), 1390, 640, null);
        g2d.drawImage(faceUpCard[1].getImage(), 1290, 640, null);
        g2d.drawImage(faceUpCard[2].getImage(), 1190, 640, null);
        g2d.drawImage(faceUpCard[3].getImage(), 1090, 640, null);
        g2d.drawImage(faceUpCard[4].getImage(), 990, 640, null);
        //reset those transformations
        g2d.setTransform(new AffineTransform());
        
        //numerical stuff
        g2d.setFont(new Font("Serif", Font.BOLD, 50)); g2d.setColor(Color.WHITE); 
        
        //red player station and trains
        g2d.drawString("0", 1758, 285);
        g2d.drawString("0", 1828, 325); 
        //green player station and trains
        g2d.drawString("0", 1758, 385);
        g2d.drawString("0", 1828, 420);
        //yellow player station and trains
        g2d.drawString("0", 1758, 480);
        g2d.drawString("0", 1825, 520);
        //blue player station and trains
        g2d.drawString("0", 1752, 580);
        g2d.drawString("0", 1822, 620);
        
        //number of trains stuff
        g2d.setFont(new Font("Serif", Font.BOLD, 30)); g2d.setColor(Color.WHITE); 
        g2d.drawString("0", 1580, 790);
        g2d.drawString("0", 1645, 790);
        g2d.drawString("0", 1712, 790);
        g2d.drawString("0", 1777, 790);
        g2d.drawString("0", 1845, 790);
        //2nd row
        g2d.drawString("0", 1580, 930);
        g2d.drawString("0", 1645, 930);
        g2d.drawString("0", 1712, 930);
        g2d.drawString("0", 1777, 930);
        g2d.drawString("0", 1845, 930);
        
        //current player stuff
        
        //current player number
        g2d.drawImage(player1, 1825, 948, null);
        //current player arrow
        g2d.drawImage(playerpointer, 1875, 270, null);
        //current player's color
        g2d.drawImage(redplayer, 1520, 925, null);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    	System.out.println(e.getX() + " " + e.getY());
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

