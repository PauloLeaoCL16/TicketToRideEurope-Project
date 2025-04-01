package test2;

import java.util.*;
import java.awt.image.*;

public class Player {
	
    private int points;
    private ArrayList<Ticket> tickets;
    private ArrayList<ColorCard> card;
    private ArrayList<City> railRoadsBought;
    private ArrayList<Station> usedStationList;
    private ArrayList<Station> unusedStationList;
    private int trainsLeft;
    private BufferedImage plrLabel;
    private String color;
    private BufferedImage profileImage;
    
    public Player(String color, BufferedImage stationImage, BufferedImage plrLabel, BufferedImage profileImage) {
        points = 0;
        trainsLeft = 45;
        tickets = new ArrayList<Ticket>();
        card = new ArrayList<ColorCard>();
        unusedStationList = new ArrayList<Station>();
        usedStationList = new ArrayList<Station>();
        unusedStationList.add(new Station(color, stationImage));
        unusedStationList.add(new Station(color, stationImage));
        unusedStationList.add(new Station(color, stationImage));
        this.color = color;
        this.plrLabel = plrLabel;
        this.profileImage = profileImage;
    }
    
    public int getStations()
    {
    	int size = 0;
    	size += unusedStationList.size();
    	return size;
    }
    
    public BufferedImage getProfileImage() {
    	return profileImage;
    }
    
    public BufferedImage getPlrLabel() {
    	return plrLabel;
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
