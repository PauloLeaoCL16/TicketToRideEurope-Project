package ttreImages;

import javax.swing.*;
import java.awt.*;

public class ttreFrame extends JFrame {
    private static final int WIDTH = 1920;
    private static final int HEIGHT = 1080;

    public ttreFrame(String framename) {
        super(framename);
 

        setSize(WIDTH, HEIGHT);
        setResizable(false);

        //setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        add(new MainMenu());

        setVisible(true);
    }
}
