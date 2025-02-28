import java.util.BufferedImage;

public class ColorCard {
    private String color;
    private int costToDraw;
    BufferedImage image;
    public ColorCard(String color) {
        this.color = color;
        if (color.equals("wild")) {
            costToDraw = 2;
        } else {
            costToDraw = 1;
        }
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