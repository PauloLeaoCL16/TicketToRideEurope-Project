package ttreImages;

import java.util.ArrayList;

public class Graph {
    private ArrayList<City> adjacencyList;
    private int clickRadius = 5;

    public Graph() {
        adjacencyList = new ArrayList<>();

        City city = addCity("frankfurt", 568, 433);
        city = addCity("brest", 341, 313);
        city = addCity("london", 341, 313);
        city = addCity("essen", 584, 332);
        city = addCity("berlin", 722, 350);
        city = addCity("wien", 801, 510);
        city = addCity("zurich", 552, 573);
        city = addCity("marseille", 513, 703);
        city = addCity("roma", 673, 747);
        city = addCity("brindisi", 790, 781);
        city = addCity("sofia", 987, 729);
        city = addCity("kobenhavn", 678, 190);
        city = addCity("amsterdam", 472, 319);
        city = addCity("dieppe", 322, 438);
        city = addCity("paris", 390, 499);
        city = addCity("bruxelles", 439, 382);
        city = addCity("munchen", 641, 485);
        city = addCity("athina", 967, 885);
        city = addCity("madrid", 169, 816);
        city = addCity("cadiz", 167, 923);
        city = addCity("barcelona", 321, 842);
        city = addCity("pamplona", 304, 712);
        city = addCity("constantinople", 1143, 815);
        city = addCity("budapest", 860, 550);
        city = addCity("bucuresti", 1073, 644);
        city = addCity("sevastopol", 1264, 662);
        city = addCity("kharkov", 1336, 486);
        city = addCity("kyiv", 1153, 416);
        city = addCity("moskva", 1360, 278);
        city = addCity("petrograd", 1227, 112);
        city = addCity("riga", 994, 125);
        city = addCity("danzig", 875, 246);
        city = addCity("edinburgh", 248, 111);
        city = addCity("lisboa", 73, 856);
        city = addCity("zagrab", 784, 638);
        city = addCity("sarajevo", 897, 717);
        city = addCity("palermo", 730, 927);
        city = addCity("smryna", 1084, 921);
        city = addCity("angora", 1249, 888);
        city = addCity("erzurum", 1357, 855);
        city = addCity("sochi", 1385, 683);
        city = addCity("rostov", 1393, 562);
        city = addCity("stockholm", 822, 81);
        city = addCity("wilno", 1098, 308);
    }

    private City addCity(String name, int x, int y) {
    	City city = new City(name, new Station(), x, y);
        adjacencyList.add(city);
    	return city;
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
