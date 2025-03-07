import java.awt.*;
import javax.swing.*;
import java.awt.image.*;
import javax.imageio.ImageIO;
import java.io.*;
public class ttreFrame extends JFrame{
	private static final int WIDTH = 1920;
	private static final int HEIGHT = 1080;
	
	public ttreFrame(String framename)
	{
		super(framename);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(WIDTH,HEIGHT);
		setExtendedState(JFrame.MAXIMIZED_BOTH); 
		setUndecorated(false);
		//add(new MainMenu(true));
		setVisible(true);
	}
}
