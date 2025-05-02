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
	private BufferedImage endBack, redPfp, bluePfp, whitePfp, orangePfp, circle, trofee;
	private ArrayList<Integer> scores;
	private ArrayList<Confetti> confettiList;
	public EndGame(int plr1, int plr2, int plr3, int plr4, int ticketPt1, int ticketPt2, int ticketPt3, int ticketPt4) {
	    try {
	    	endBack = ImageIO.read(MainMenu.class.getResource("/ttreImages/endScreen.png"));
	    	redPfp = ImageIO.read(MainMenu.class.getResource("/ttreImages/redPfp.png"));
	    	bluePfp = ImageIO.read(MainMenu.class.getResource("/ttreImages/bluePfp.png"));
	    	whitePfp = ImageIO.read(MainMenu.class.getResource("/ttreImages/whitePfp.png"));
	    	orangePfp = ImageIO.read(MainMenu.class.getResource("/ttreImages/orangePfp.png"));
	    	circle = ImageIO.read(MainMenu.class.getResource("/ttreImages/circle.png"));
	    	trofee = ImageIO.read(MainMenu.class.getResource("/ttreImages/trofee.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
	    scores = new ArrayList<>();
	    scores.add(plr1);
	    scores.add(plr2);
	    scores.add(plr3);
	    scores.add(plr4);
	    Collections.sort(scores);
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
