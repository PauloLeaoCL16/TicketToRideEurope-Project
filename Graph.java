package ttreImages;

import java.util.ArrayList;

public class Graph {
    private ArrayList<City> adjacencyList;
    private int clickRadius = 50;

    public Graph() {
        adjacencyList = new ArrayList<>();

        //declaring the cities
        City frankfurt = addCity("frankfurt", 568, 433);
        City brest = addCity("brest", 341, 313);
        City london = addCity("london", 341, 313);
        City essen = addCity("essen", 584, 332);
        City berlin = addCity("berlin", 722, 350);
        City wien = addCity("wien", 801, 510);
        City zurich = addCity("zurich", 552, 573);
        City marseille = addCity("marseille", 513, 703);
        City roma = addCity("roma", 673, 747);
        City brindisi = addCity("brindisi", 790, 781);
        City sofia = addCity("sofia", 987, 729);
        City kobenhavn = addCity("kobenhavn", 678, 190);
        City amsterdam = addCity("amsterdam", 472, 319);
        City dieppe = addCity("dieppe", 322, 438);
        City paris = addCity("paris", 390, 499);
        City bruxelles = addCity("bruxelles", 439, 382);
        City munchen = addCity("munchen", 641, 485);
        City athina = addCity("athina", 967, 885);
        City madrid = addCity("madrid", 169, 816);
        City cadiz = addCity("cadiz", 167, 923);
        City barcelona = addCity("barcelona", 321, 842);
        City pamplona = addCity("pamplona", 304, 712);
        City constantinople = addCity("constantinople", 1143, 815);
        City budapest = addCity("budapest", 860, 550);
        City bucuresti = addCity("bucuresti", 1073, 644);
        City sevastopol = addCity("sevastopol", 1264, 662);
        City kharkov = addCity("kharkov", 1336, 486);
        City kyiv = addCity("kyiv", 1153, 416);
        City moskva = addCity("moskva", 1360, 278);
        City petrograd = addCity("petrograd", 1227, 112);
        City riga = addCity("riga", 994, 125);
        City danzig = addCity("danzig", 875, 246);
        City edinburgh = addCity("edinburgh", 248, 111);
        City lisboa = addCity("lisboa", 73, 856);
        City zagrab = addCity("zagrab", 784, 638);
        City sarajevo = addCity("sarajevo", 897, 717);
        City palermo = addCity("palermo", 730, 927);
        City smryna = addCity("smryna", 1084, 921);
        City angora = addCity("angora", 1249, 888);
        City erzurum = addCity("erzurum", 1357, 855);
        City sochi = addCity("sochi", 1385, 683);
        City rostov = addCity("rostov", 1393, 562);
        City stockholm = addCity("stockholm", 822, 81);
        City wilno = addCity("wilno", 1098, 308);
        
        //adding all the connections
        ArrayList<RailRoad> railRoadList = new ArrayList<RailRoad>();
        railRoadList.add(new RailRoad(2, "purple", 0, false, false, 604, 481, Math.toRadians(4)));
        railRoadList.add(new RailRoad(2, "purple", 0, false, false, 604, 481, Math.toRadians(4)));
        frankfurt.addConnection(munchen, railRoadList);
        
        
    }

    private City addCity(String name, int x, int y) {
    	City city = new City(name, null , x, y);
        adjacencyList.add(city);
    	return city;
    }

    public ArrayList<RailRoad> getCityConnection(City city1, City city2) {
    	// Get the railroad connection of city to city stuff
    	if (!city1.getConnections().containsKey(city2)) {
    		return null;
    	}
    	return city1.getConnections().get(city2);
    }

    public ArrayList<City> getCities() {
        return adjacencyList;
    }
    
    public int getClickRadius() {
    	return clickRadius;
    }

    public void printCities() {
        for (City city : adjacencyList) {
            System.out.println(city.getName() + " (" + city.getX() + ", " + city.getY() + ")");
        }
    }
}
