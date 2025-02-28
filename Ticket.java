import java.util.BufferedImage;
import javax.imageio.ImageIO;

public class Ticket {
    private String fromCity;
    private String toCity;
    private int points;
    private BufferedImage image;
    public Ticket(String start, String end, int points, String directory) {
        fromCity = start;
        toCity = end;
        this.points = points;
        image = ImageIO.read(new File(directory));
    }
}