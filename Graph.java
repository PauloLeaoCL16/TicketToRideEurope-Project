import java.util.ArrayList;

public class Graph {
    private ArrayList<City> adjacencyList;

    public Graph() {
        adjacencyList = new ArrayList<>();

        addCity("frankfurt", 568, 433);
        addCity("brest", 341, 313);
        addCity("london", 341, 313);
        addCity("essen", 584, 332);
        addCity("berlin", 722, 350);
        addCity("wien", 801, 510);
        addCity("zurich", 552, 573);
        addCity("marseille", 513, 703);
        addCity("roma", 673, 747);
        addCity("brindisi", 790, 781);
        addCity("sofia", 987, 729);
        addCity("kobenhavn", 678, 190);
        addCity("amsterdam", 472, 319);
        addCity("dieppe", 322, 438);
        addCity("paris", 390, 499);
        addCity("bruxelles", 439, 382);
        addCity("munchen", 641, 485);
        addCity("athina", 967, 885);
        addCity("madrid", 169, 816);
        addCity("cadiz", 167, 923);
        addCity("barcelona", 321, 842);
        addCity("pamplona", 304, 712);
        addCity("constantinople", 1143, 815);
        addCity("budapest", 860, 550);
        addCity("bucuresti", 1073, 644);
        addCity("sevastopol", 1264, 662);
        addCity("kharkov", 1336, 486);
        addCity("kyiv", 1153, 416);
        addCity("moskva", 1360, 278);
        addCity("petrograd", 1227, 112);
        addCity("riga", 994, 125);
        addCity("danzig", 875, 246);
        addCity("edinburgh", 248, 111);
        addCity("lisboa", 73, 856);
        addCity("zagrab", 784, 638);
        addCity("sarajevo", 897, 717);
        addCity("palermo", 730, 927);
        addCity("smryna", 1084, 921);
        addCity("angora", 1249, 888);
        addCity("erzurum", 1357, 855);
        addCity("sochi", 1385, 683);
        addCity("rostov", 1393, 562);
        addCity("stockholm", 822, 81);
        addCity("wilno", 1098, 308);
    }

    private City addCity(String name, int x, int y) {
    	City city = new City(name, new Station(), x, y);
        adjacencyList.add(city);
    	return city;
    }

    public ArrayList<City> getCities() {
        return adjacencyList;
    }

    public void printCities() {
        for (City city : adjacencyList) {
            System.out.println(city.getName() + " (" + city.getX() + ", " + city.getY() + ")");
        }
    }
}
