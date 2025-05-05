
package ttreImages;

import java.awt.*;
import javax.swing.*;
import java.awt.image.*;
import javax.imageio.ImageIO;
import java.io.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.*;
import javax.swing.Timer;


public class EndGame extends JPanel implements Runnable, MouseListener, MouseMotionListener  {
	private BufferedImage endBack, winner, trofee;
	private ArrayList<Integer> scores;
	private ArrayList<Confetti> confettiList;
	private int a, b, c, d, w, x, y, z;
    
	public EndGame(int plr1, int plr2, int plr3, int plr4, int t1, int t2, int t3, int t4) {
	    try {
	    	endBack = ImageIO.read(MainMenu.class.getResource("/ttreImages/endScreen.png"));
	    	winner = ImageIO.read(MainMenu.class.getResource("/ttreImages/winnerPic.png"));
	    	trofee = ImageIO.read(MainMenu.class.getResource("/ttreImages/trophee.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

	    w = plr1;
	    x = plr2;
	    y = plr3;
	    z = plr4;

	    scores = new ArrayList<>();
	    scores.add(w);
	    scores.add(x);
	    scores.add(y);
	    scores.add(z);
	    Collections.sort(scores);
	    
	    a = t1;
	    b = t2;
	    c = t3;
	    d = t4;
	    
        // Initialize confetti
        confettiList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            confettiList.add(new Confetti());
        }

        Timer timer = new Timer(30, e -> {
            for (Confetti confetti : confettiList) {
                confetti.update();
            }
            repaint();
        });
        timer.start();
    }
	
	@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(endBack, 0, 0, null);
        //cords for the images
        // need an if statement to see which player won, cords are below

	if(scores.get(scores.size()-1) == w)
	{
        	g.drawImage(winner, 620, 670, null);
	}
	else if(scores.get(scores.size()-1) == x){
		g.drawImage(winner, 1025, 670, null);
	}
	else if(scores.get(scores.size()-1) == y) {
		g.drawImage(winner, 1390, 670, null);
        } 
	else{
		g.drawImage(winner, 1410, 670, null);
	}
	// or 620, 1025, 1390, 1410
        
        //player points
        g.drawString(w + "",510, 650);
        g.drawString(x + "",870, 650);
        g.drawString(y+ "",1280, 650);
        g.drawString(z+"",1660, 650);
        
        //ticket points
        g.drawString(a+"",310, 650);
        g.drawString(b+"",670, 650);
        g.drawString(c+"",1060, 650);
        g.drawString(d+"",1460, 650);
        
       
        //if statement for this too
        //longest route winner cords lol
        g.drawImage(trofee, 280, 380, null);
        // 280, 640, 1040, 1440

        

        // Draw confetti
        for (Confetti confetti : confettiList) {
            confetti.draw(g);
        }
    }
	
	@Override
    public void mouseClicked(MouseEvent e) {
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

class Confetti {
    private int x, y, size, speedY;
    private Color color;
    private static final Random random = new Random();

    public Confetti() {
        x = random.nextInt(1920); 
        y = random.nextInt(1080); 
        size = random.nextInt(10) + 5;
        speedY = random.nextInt(5) + 2;
        color = new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256));
    }

    public void update() {
        y += speedY;
        if (y > 1080) { 
            y = -size;
            x = random.nextInt(1920);
        }
    }

    public void draw(Graphics g) {
        g.setColor(color);
        g.fillRect(x, y, size, size);
    }
}
