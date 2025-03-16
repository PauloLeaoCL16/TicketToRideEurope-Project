package ttreImages;

import java.awt.*;
import javax.swing.*;
import java.awt.image.*;
import javax.imageio.ImageIO;
import java.io.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class RulesPanel extends JPanel implements Runnable, MouseListener, MouseMotionListener {
    private BufferedImage rulestuff;   
    private BufferedImage backScroll;  
    private boolean isBackScrollHovered = false;

    public RulesPanel() {
        try {
            rulestuff = ImageIO.read(RulesPanel.class.getResource("/ttreImages/rulestuff.png"));
            backScroll = ImageIO.read(RulesPanel.class.getResource("/ttreImages/backScroll.png"));
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

        g2d.drawImage(rulestuff, 0, 0, getWidth(), getHeight(), null);

        int backScrollX = 1500;
        int backScrollY = 0;
        int backScrollWidth = (int) (backScroll.getWidth() * 0.8); 
        int backScrollHeight = (int) (backScroll.getHeight() * 0.8); 

        if (isBackScrollHovered) {
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.6f));
        }
        g2d.drawImage(backScroll, backScrollX, backScrollY, backScrollWidth, backScrollHeight, null);
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int backScrollX = 1500;
        int backScrollY = 0;
        int backScrollWidth = (int) (backScroll.getWidth() * 0.8);
        int backScrollHeight = (int) (backScroll.getHeight() * 0.8);
        if (e.getX() >= backScrollX && e.getX() <= backScrollX + backScrollWidth
                && e.getY() >= backScrollY && e.getY() <= backScrollY + backScrollHeight) {
            JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
            topFrame.setContentPane(new MainMenu());
            topFrame.validate();
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        int backScrollX = 1500;
        int backScrollY = 0;
        int backScrollWidth = (int) (backScroll.getWidth() * 0.8);
        int backScrollHeight = (int) (backScroll.getHeight() * 0.8);

        if (e.getX() >= backScrollX && e.getX() <= backScrollX + backScrollWidth
                && e.getY() >= backScrollY && e.getY() <= backScrollY + backScrollHeight) {
            isBackScrollHovered = true;
        } else {
            isBackScrollHovered = false;
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
