package ttreImages;
import java.awt.image.*;

public class ColorCard {
    private String color;
    private int costToDraw;
    private BufferedImage image;
    
    public ColorCard(String color) {
        this.color = color;
        if (color.equals("wild")) {
            costToDraw = 2;
        } else {
            costToDraw = 1;
        }
    }
    
    public ColorCard(String color, BufferedImage img)
    {
    	this.color = color;
        if (color.equals("wild")) {
            costToDraw = 2;
        } else {
            costToDraw = 1;
        }
        image = img;
    }

    public String getColor() {
        return color;
    }

    public int getCostToDraw() {
        return costToDraw;
    }

    public BufferedImage getImage() {
        return image;
    }
}
