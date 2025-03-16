package ttreImages;

import java.awt.*;
import javax.swing.*;
import java.awt.image.*;
import javax.imageio.ImageIO;
import java.io.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class MainMenu extends JPanel implements Runnable, MouseListener, MouseMotionListener {
    private BufferedImage background;
    private BufferedImage playImage;
    private BufferedImage rulesScroll;
    private boolean isPlayButtonHovered = false;
    private boolean isRulesScrollHovered = false;

    public MainMenu() {
        try {
            background = ImageIO.read(MainMenu.class.getResource("/ttreImages/background.png"));
            playImage = ImageIO.read(MainMenu.class.getResource("/ttreImages/playButton.png"));
            rulesScroll = ImageIO.read(MainMenu.class.getResource("/ttreImages/rulesScroll.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        addMouseListener(this);
        addMouseMotionListener(this);
        setFocusable(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        g2d.drawImage(background, 0, 0, getWidth(), getHeight(), null);

        int playButtonX = 1245;
        int playButtonY = 600;
        int playButtonWidth = playImage.getWidth();
        int playButtonHeight = playImage.getHeight();

        if (isPlayButtonHovered) {
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.8f));
        }
        g2d.rotate(-Math.toRadians(1.6), playButtonX + playButtonWidth / 2.0, playButtonY + playButtonHeight / 2.0);
        g2d.drawImage(playImage, playButtonX, playButtonY, null);
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
        g2d.rotate(Math.toRadians(1.6), playButtonX + playButtonWidth / 2.0, playButtonY + playButtonHeight / 2.0);

        int rulesScrollX = 1500;
        int rulesScrollY = 0;
        int rulesScrollWidth = (int) (rulesScroll.getWidth() * 0.8);
        int rulesScrollHeight = (int) (rulesScroll.getHeight() * 0.8);

        if (isRulesScrollHovered) {
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.6f));
        }
        g2d.drawImage(rulesScroll, rulesScrollX, rulesScrollY, rulesScrollWidth, rulesScrollHeight, null);
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int rulesScrollX = 1500;
        int rulesScrollY = 0;
        int rulesScrollWidth = (int) (rulesScroll.getWidth() * 0.8);
        int rulesScrollHeight = (int) (rulesScroll.getHeight() * 0.8);
        if (e.getX() >= rulesScrollX && e.getX() <= rulesScrollX + rulesScrollWidth
                && e.getY() >= rulesScrollY && e.getY() <= rulesScrollY + rulesScrollHeight) {
            JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
            topFrame.setContentPane(new RulesPanel());
            topFrame.validate();
        }
        int playButtonX = 1245;
        int playButtonY = 600;
        int playButtonWidth = playImage.getWidth();
        int playButtonHeight = playImage.getHeight();

        if (e.getX() >= playButtonX && e.getX() <= playButtonX + playButtonWidth
                && e.getY() >= playButtonY && e.getY() <= playButtonY + playButtonHeight) {
        	JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
            topFrame.setContentPane(new GameBoard());
            topFrame.validate();
        } 
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        int playButtonX = 1245;
        int playButtonY = 600;
        int playButtonWidth = playImage.getWidth();
        int playButtonHeight = playImage.getHeight();

        if (e.getX() >= playButtonX && e.getX() <= playButtonX + playButtonWidth
                && e.getY() >= playButtonY && e.getY() <= playButtonY + playButtonHeight) {
            isPlayButtonHovered = true;
        } else {
            isPlayButtonHovered = false;
        }

        int rulesScrollX = 1500;
        int rulesScrollY = 0;
        int rulesScrollWidth = (int) (rulesScroll.getWidth() * 0.8);
        int rulesScrollHeight = (int) (rulesScroll.getHeight() * 0.8);

        if (e.getX() >= rulesScrollX && e.getX() <= rulesScrollX + rulesScrollWidth
                && e.getY() >= rulesScrollY && e.getY() <= rulesScrollY + rulesScrollHeight) {
            isRulesScrollHovered = true;
        } else {
            isRulesScrollHovered = false;
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
