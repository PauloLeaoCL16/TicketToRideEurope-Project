import java.util.ArrayList;
import java.util.HashMap;

public class Player {
    private int points;
    private ArrayList<Ticket> tickets; //(Ex: {[Berlin, Amsterdam]=8}
    private ArrayList<ColorCard> card;
    private int station;
    private int trainsLeft;
    public Player() {
        station = 3;
        points = 0;
        trainsLeft = 45;
        tickets = new ArrayList<Ticket>();
        card = new ArrayList<ColorCard>();
    }

    public void addTicket(Ticket ticket) {
        tickets.add(ticket);
    }

    public void removeTicket(Ticket ticket) {
        tickets.remove(ticket);
    }

    public ArrayList<Ticket> getTicket() {
        return tickets;
    }

    public int getStation() {
        return station;
    }

    public void setPoint(int pt) {
        points = pt;
    }

    public int getPoint() {
        return points;
    }

    public void addCard(ColorCard card) {
        this.card.add(card);
    }

    public ArrayList<ColorCard> getCard() {
        return card;
    }

    public void removeTrain(int numberToRemove) {
        if (trainsLeft - numberToRemove < 0) {
            System.out.print("Not enough train");
            return;
        }
        trainsLeft -= numberToRemove;
    }

    public int getTrainsLeft() {
        return trainsLeft;
    }
}
