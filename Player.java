package ttreImages;

import java.util.*;

public class Player {
	
    private int points;
    private ArrayList<Ticket> tickets;
    private ArrayList<ColorCard> card;
    private ArrayList<City> railRoadsBought;
    private ArrayList<Station> usedStationList;
    private ArrayList<Station> unusedStationList;
    private int trainsLeft;
    private String color;
    private int actions = 2;
    
    public Player(String color) {
        points = 0;
        trainsLeft = 45;
        tickets = new ArrayList<Ticket>();
        card = new ArrayList<ColorCard>();
        unusedStationList = new ArrayList<Station>();
        usedStationList = new ArrayList<Station>();
        unusedStationList.add(new Station(color));
        unusedStationList.add(new Station(color));
        unusedStationList.add(new Station(color));
        this.color = color;
    }
    
    public int getStations()
    {
    	int size = 0;
    	size += unusedStationList.size();
    	return size;
    }
    
    public Station useStation()
    {
    	Station station = new Station(color);
    	if(unusedStationList.size()>=1)
    		station = (unusedStationList.remove(unusedStationList.size()-1));
    	usedStationList.add(station);
    	return station;
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
    
    public int getActions()
    {
    	return actions;
    }
    
    public void setActions(int x)
    {
    	actions = x;
    }

    public void addCard(ColorCard card) {
        this.card.add(card);
    }

    public ArrayList<ColorCard> getCard() {
        return card;
    }
    
    public int getCardColor(String color) {
    	int total = 0;
    	for (int i = 0; i< card.size(); i++) {
    		if (card.get(i).getColor().equals(color)) {
    			total += 1;
    		}
    	}
    	return total;
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
    
    public Station removeStation() {
    	Station station = unusedStationList.remove(0);
    	return station;
    }

    public int getTrainsLeft() {
        return trainsLeft;
    }

    public String getPlayerColor() {
    	return color;
    }
}
