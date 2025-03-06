import java.util.*;

public class Player {
	
    private int points;
    private ArrayList<Ticket> tickets; //(Ex: {[Berlin, Amsterdam]=8}
    private ArrayList<ColorCard> card;
    private ArrayList<City> railRoadsBought;
    private ArrayList<Station> usedStationList;
    private ArrayList<Station> unusedStationList;
    private int trainsLeft;
    
    public Player() {
        points = 0;
        trainsLeft = 45;
        tickets = new ArrayList<Ticket>();
        card = new ArrayList<ColorCard>();
        unusedStationList.add(new Station());
        unusedStationList.add(new Station());
        unusedStationList.add(new Station());
    }
    
    public void useStation()
    {
    	if(unusedStationList.size()>=1)
    		usedStationList.add(unusedStationList.remove(unusedStationList.size()-1));
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
    
    public void removeCards(String color, int num)
    {
    	for(ColorCard cardRemove : card)
    	{
	    	if(cardRemove.getColor().equals(color) && num>0)
	    	{
	    		card.remove(cardRemove);
	    		num--;
	    	}
    	}
    }

    public void removeTrain(int numberToRemove)
    {
    	if( trainsLeft - numberToRemove >= 0)
    		trainsLeft -= numberToRemove;
    }

    public int getTrainsLeft() {
        return trainsLeft;
    }
}
