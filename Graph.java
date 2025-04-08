package ttreImages;

import java.util.ArrayList;

public class Graph {
	private ArrayList<City> adjacencyList;
	private int clickRadius = 25;
	private int railRoadSizeX = 35;
    private int railRoadSizeY = 15;	

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
		City venezia = addCity("venezia", 681,632);

		//adding all the connections
		ArrayList<RailRoad> railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(2, "purple", 0, false, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(2, "purple", 0, false, false,592,463, -Math.toRadians(20)));
		frankfurt.addConnection(munchen, railRoadList);	//frankfurt to munchen
		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(2, "green", 0, false, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(2, "green", 0, false, false,592,463, -Math.toRadians(20)));
		frankfurt.addConnection(essen, railRoadList);	//frankfurt to essen
		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(2, "white", 0, false, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(2, "white", 0, false, false,592,463, -Math.toRadians(20)));
		frankfurt.addConnection(amsterdam, railRoadList);	//frankfurt to amsterdam
		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(2, "blue", 0, false, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(2, "blue", 0, false, false,592,463, -Math.toRadians(20)));
		frankfurt.addConnection(bruxelles, railRoadList);	//frankfurt to bruxelles
		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(3, "black", 0, false, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(3, "black", 0, false, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(3, "black", 0, false, false,592,463, -Math.toRadians(20)));
		frankfurt.addConnection(berlin, railRoadList);	//frankfurt to berlin
		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(3, "red", 0, false, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(3, "red", 0, false, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(3, "red", 0, false, false,592,463, -Math.toRadians(20)));
		frankfurt.addSecondConnection(berlin, railRoadList); 	//frankfurt to berlin
		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(3, "white", 0, false, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(3, "white", 0, false, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(3, "white", 0, false, false,592,463, -Math.toRadians(20)));
		frankfurt.addConnection(paris, railRoadList);	//frankfurt to paris
		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(3, "orange", 0, false, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(3, "orange", 0, false, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(3, "orange", 0, false, false,592,463, -Math.toRadians(20)));
		frankfurt.addSecondConnection(paris, railRoadList);	//frankfurt to paris


		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(4, "black", 0, false, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(4, "black", 0, false, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(4, "black", 0, false, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(4, "black", 0, false, false,592,463, -Math.toRadians(20)));
		edinburgh.addConnection(london, railRoadList);	//edinburgh to london
		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(4, "orange", 0, false, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(4, "orange", 0, false, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(4, "orange", 0, false, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(4, "orange", 0, false, false,592,463, -Math.toRadians(20)));
		edinburgh.addSecondConnection(london, railRoadList);	//edinburgh to london


		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(4, "black", 0, false, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(4, "black", 0, false, false,592,463, -Math.toRadians(20)));
		london.addConnection(edinburgh, railRoadList);	//london to edinburgh
		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(4, "orange", 0, false, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(4, "orange", 0, false, false,592,463, -Math.toRadians(20)));
		london.addSecondConnection(edinburgh, railRoadList);	//london to edinburgh
		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(2, "any", 2, false, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(2, "any", 2, false, false,592,463, -Math.toRadians(20)));
		london.addConnection(amsterdam, railRoadList);	//london to amsterdam
		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(2, "any", 1, false, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(2, "any", 1, false, false,592,463, -Math.toRadians(20)));
		london.addConnection(dieppe, railRoadList);	//london to dieppe
		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(2, "any", 1, false, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(2, "any", 1, false, false,592,463, -Math.toRadians(20)));
		london.addSecondConnection(dieppe, railRoadList);	//london to dieppe


		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(2, "any", 1, false, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(2, "any", 1, false, false,592,463, -Math.toRadians(20)));
		dieppe.addConnection(london, railRoadList);	//dieppe to london
		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(2, "any", 1, false, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(2, "any", 1, false, false,592,463, -Math.toRadians(20)));
		dieppe.addSecondConnection(london, railRoadList);	//dieppe to london
		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(2, "green", 0, false, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(2, "green", 0, false, false,592,463, -Math.toRadians(20)));
		dieppe.addConnection(bruxelles, railRoadList);	//dieppe to bruxelles
		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(2, "orange", 0, false, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(2, "orange", 0, false, false,592,463, -Math.toRadians(20)));
		dieppe.addConnection(brest, railRoadList);	//dieppe to brest
		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(1, "purple", 0, false, false,592,463, -Math.toRadians(20)));
		dieppe.addConnection(paris, railRoadList);	//dieppe to paris	


		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(2, "orange", 0, false, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(2, "orange", 0, false, false,592,463, -Math.toRadians(20)));
		brest.addConnection(dieppe, railRoadList);	//brest to dieppe
		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(3, "black", 0, false, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(3, "black", 0, false, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(3, "black", 0, false, false,592,463, -Math.toRadians(20)));
		brest.addConnection(paris, railRoadList);	//brest to paris
		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(4, "purple", 0, false, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(4, "purple", 0, false, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(4, "purple", 0, false, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(4, "purple", 0, false, false,592,463, -Math.toRadians(20)));
		brest.addConnection(pamplona, railRoadList);	//brest to pamplona


		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(4, "purple", 0, false, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(4, "purple", 0, false, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(4, "purple", 0, false, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(4, "purple", 0, false, false,592,463, -Math.toRadians(20)));
		pamplona.addConnection(brest, railRoadList);	//pamplona to brest
		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(4, "blue", 0, false, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(4, "blue", 0, false, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(4, "blue", 0, false, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(4, "blue", 0, false, false,592,463, -Math.toRadians(20)));
		pamplona.addConnection(paris, railRoadList);	//pamplona to paris
		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(4, "green", 0, false, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(4, "green", 0, false, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(4, "green", 0, false, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(4, "green", 0, false, false,592,463, -Math.toRadians(20)));
		pamplona.addSecondConnection(paris, railRoadList);	//pamplona to paris
		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(4, "red", 0, false, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(4, "red", 0, false, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(4, "red", 0, false, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(4, "red", 0, false, false,592,463, -Math.toRadians(20)));
		pamplona.addConnection(marseille, railRoadList);	//pamplona to marseille
		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(3, "black", 0, true, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(3, "black", 0, true, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(3, "black", 0, true, false,592,463, -Math.toRadians(20)));
		pamplona.addConnection(madrid, railRoadList);	//pamplona to madrid
		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(3, "white", 0, true, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(3, "white", 0, true, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(3, "white", 0, true, false,592,463, -Math.toRadians(20)));
		pamplona.addSecondConnection(madrid, railRoadList);	//pamplona to madrid
		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(2, "any", 0, true, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(2, "any", 0, true, false,592,463, -Math.toRadians(20)));
		pamplona.addConnection(barcelona, railRoadList);	//pamplona to barcelona


		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(2, "any", 0, true, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(2, "any", 0, true, false,592,463, -Math.toRadians(20)));
		barcelona.addConnection(pamplona, railRoadList);	//barcelona to pamplona
		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(2, "yellow", 0, false, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(2, "yellow", 0, false, false,592,463, -Math.toRadians(20)));
		barcelona.addConnection(madrid, railRoadList);	//barcelona to madrid
		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(4, "any", 0, false, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(4, "any", 0, false, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(4, "any", 0, false, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(4, "any", 0, false, false,592,463, -Math.toRadians(20)));
		barcelona.addConnection(marseille, railRoadList);	//barcelona to marseille


		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(3, "purple", 0, false, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(3, "purple", 0, false, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(3, "purple", 0, false, false,592,463, -Math.toRadians(20)));
		madrid.addConnection(lisboa, railRoadList);	//madrid to lisboa
		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(3, "orange", 0, false, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(3, "orange", 0, false, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(3, "orange", 0, false, false,592,463, -Math.toRadians(20)));
		madrid.addConnection(cadiz, railRoadList);	//madrid to cadiz
		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(3, "black", 0, true, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(3, "black", 0, true, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(3, "black", 0, true, false,592,463, -Math.toRadians(20)));
		madrid.addConnection(pamplona, railRoadList);	//madrid to pamplona
		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(3, "white", 0, true, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(3, "white", 0, true, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(3, "white", 0, true, false,592,463, -Math.toRadians(20)));
		madrid.addSecondConnection(pamplona, railRoadList);	//madrid to pamplona
		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(2, "yellow", 0, false, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(2, "yellow", 0, false, false,592,463, -Math.toRadians(20)));
		madrid.addConnection(cadiz, railRoadList);	//madrid to barcelona


		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(3, "orange", 0, false, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(3, "orange", 0, false, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(3, "orange", 0, false, false,592,463, -Math.toRadians(20)));
		cadiz.addConnection(madrid, railRoadList);	//cadiz to madrid
		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(2, "blue", 0, false, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(2, "blue", 0, false, false,592,463, -Math.toRadians(20)));
		cadiz.addConnection(lisboa, railRoadList);	//cadiz to lisboa


		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(2, "blue", 0, false, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(2, "blue", 0, false, false,592,463, -Math.toRadians(20)));
		lisboa.addConnection(cadiz, railRoadList);	//lisboa to cadiz
		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(3, "purple", 0, false, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(3, "purple", 0, false, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(3, "purple", 0, false, false,592,463, -Math.toRadians(20)));
		lisboa.addConnection(madrid, railRoadList);	//lisboa to madrid	


		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(4, "red", 0, false, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(4, "red", 0, false, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(4, "red", 0, false, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(4, "red", 0, false, false,592,463, -Math.toRadians(20)));
		marseille.addConnection(pamplona, railRoadList);	//marseille to pamplona
		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(4, "any", 0, false, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(4, "any", 0, false, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(4, "any", 0, false, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(4, "any", 0, false, false,592,463, -Math.toRadians(20)));
		marseille.addConnection(paris, railRoadList);	//marseille to paris
		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(2, "purple", 0, true, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(2, "purple", 0, true, false,592,463, -Math.toRadians(20)));
		marseille.addConnection(zurich, railRoadList);	//marseille to zurich
		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(4, "any", 0, false, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(4, "any", 0, false, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(4, "any", 0, false, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(4, "any", 0, false, false,592,463, -Math.toRadians(20)));
		marseille.addConnection(barcelona, railRoadList);	//marseille to barcelona
		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(4, "any", 0, true, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(4, "any", 0, true, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(4, "any", 0, true, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(4, "any", 0, true, false,592,463, -Math.toRadians(20)));
		marseille.addConnection(roma, railRoadList);	//marseille to roma


		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(1, "purple", 0, false, false,592,463, -Math.toRadians(20)));
		paris.addConnection(dieppe, railRoadList);	//paris to dieppe
		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(2, "yellow", 0, false, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(2, "yellow", 0, false, false,592,463, -Math.toRadians(20)));
		paris.addConnection(bruxelles, railRoadList);	//paris to bruxelles
		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(2, "red", 0, false, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(2, "red", 0, false, false,592,463, -Math.toRadians(20)));
		paris.addSecondConnection(bruxelles, railRoadList);	//paris to bruxelles
		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(3, "white", 0, false, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(3, "white", 0, false, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(3, "white", 0, false, false,592,463, -Math.toRadians(20)));
		paris.addConnection(frankfurt, railRoadList);	//paris to frankfurt
		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(3, "orange", 0, false, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(3, "orange", 0, false, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(3, "orange", 0, false, false,592,463, -Math.toRadians(20)));
		paris.addSecondConnection(frankfurt, railRoadList);	//paris to frankfurt
		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(3, "any", 0, true, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(3, "any", 0, true, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(3, "any", 0, true, false,592,463, -Math.toRadians(20)));
		paris.addConnection(zurich, railRoadList);	//paris to zurich
		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(4, "any", 0, false, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(4, "any", 0, false, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(4, "any", 0, false, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(4, "any", 0, false, false,592,463, -Math.toRadians(20)));
		paris.addConnection(marseille, railRoadList);	//paris to marseille
		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(4, "blue", 0, false, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(4, "blue", 0, false, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(4, "blue", 0, false, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(4, "blue", 0, false, false,592,463, -Math.toRadians(20)));
		paris.addConnection(pamplona, railRoadList);	//paris to pamoplona
		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(4, "green", 0, false, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(4, "green", 0, false, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(4, "green", 0, false, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(4, "green", 0, false, false,592,463, -Math.toRadians(20)));
		paris.addSecondConnection(pamplona, railRoadList);	//paris to pamplona


		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(2, "yellow", 0, true, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(2, "yellow", 0, true, false,592,463, -Math.toRadians(20)));
		zurich.addConnection(munchen, railRoadList);	//zurich to munchen
		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(2, "green", 0, true, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(2, "green", 0, true, false,592,463, -Math.toRadians(20)));
		zurich.addConnection(venezia, railRoadList);	//zurich to venezia
		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(3, "any", 0, true, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(3, "any", 0, true, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(3, "any", 0, true, false,592,463, -Math.toRadians(20)));
		zurich.addConnection(paris, railRoadList);	//zurich to paris
		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(2, "purple", 0, true, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(2, "purple", 0, true, false,592,463, -Math.toRadians(20)));
		zurich.addConnection(marseille, railRoadList);	//zurich to marseille


		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(2, "green", 0, false, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(2, "green", 0, false, false,592,463, -Math.toRadians(20)));
		bruxelles.addConnection(bruxelles, railRoadList);	//bruxelles to dieppe
		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(2, "red", 0, false, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(2, "red", 0, false, false,592,463, -Math.toRadians(20)));
		bruxelles.addConnection(paris, railRoadList);	//bruxelles to paris
		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(2, "yellow", 0, false, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(2, "yellow", 0, false, false,592,463, -Math.toRadians(20)));
		bruxelles.addSecondConnection(paris, railRoadList);	//bruxelles to paris
		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(2, "blue", 0, false, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(2, "blue", 0, false, false,592,463, -Math.toRadians(20)));
		bruxelles.addConnection(frankfurt, railRoadList);	//bruxelles to frankfurt
		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(1, "black", 0, false, false,592,463, -Math.toRadians(20)));
		bruxelles.addConnection(amsterdam, railRoadList);	//bruxelles to amsterdam


		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(2, "any", 2, false, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(2, "any", 2, false, false,592,463, -Math.toRadians(20)));
		amsterdam.addConnection(london, railRoadList);	//amsterdam to london
		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(3, "yellow", 0, false, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(3, "yellow", 0, false, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(3, "yellow", 0, false, false,592,463, -Math.toRadians(20)));
		amsterdam.addConnection(essen, railRoadList);	//amsterdam to essen
		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(1, "black", 0, false, false,592,463, -Math.toRadians(20)));
		amsterdam.addConnection(bruxelles, railRoadList);	//amsterdam to bruxelles
		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(2, "white", 0, false, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(2, "white", 0, false, false,592,463, -Math.toRadians(20)));
		amsterdam.addConnection(frankfurt, railRoadList);	//amsterdam to frankfurt


		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(3, "yellow", 0, false, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(3, "yellow", 0, false, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(3, "yellow", 0, false, false,592,463, -Math.toRadians(20)));
		essen.addConnection(amsterdam, railRoadList);	//essen to amsterdam
		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(2, "green", 0, false, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(2, "green", 0, false, false,592,463, -Math.toRadians(20)));
		essen.addConnection(frankfurt, railRoadList);	//essen to frankfurt
		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(2, "blue", 0, false, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(2, "blue", 0, false, false,592,463, -Math.toRadians(20)));
		essen.addConnection(berlin, railRoadList);	//essen to berlin
		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(3, "any", 1, false, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(3, "any", 1, false, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(3, "any", 1, false, false,592,463, -Math.toRadians(20)));
		essen.addConnection(kobenhavn, railRoadList);	//essen to kobenhavn


		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(2, "purple", 0, false, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(2, "purple", 0, false, false,592,463, -Math.toRadians(20)));
		munchen.addConnection(frankfurt, railRoadList);	//munchen to frankfurt
		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(3, "orange", 0, false, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(3, "orange", 0, false, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(3, "orange", 0, false, false,592,463, -Math.toRadians(20)));
		munchen.addConnection(wien, railRoadList);	//munchen to wien
		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(2, "blue", 0, true, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(2, "blue", 0, true, false,592,463, -Math.toRadians(20)));
		munchen.addConnection(venezia, railRoadList);	//munchen to venezia
		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(2, "yellow", 0, true, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(2, "yellow", 0, true, false,592,463, -Math.toRadians(20)));
		munchen.addConnection(zurich, railRoadList);	//munchen to zurich


		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(2, "green", 0, true, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(2, "green", 0, true, false,592,463, -Math.toRadians(20)));
		venezia.addConnection(zurich, railRoadList);	//venezia to zurich
		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(2, "any", 0, false, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(2, "any", 0, false, false,592,463, -Math.toRadians(20)));
		venezia.addConnection(zagrab, railRoadList);	//venezia to zagrab
		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(2, "blue", 0, true, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(2, "blue", 0, true, false,592,463, -Math.toRadians(20)));
		venezia.addConnection(munchen, railRoadList);	//venezia to munchen
		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(2, "black", 0, false, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(2, "black", 0, false, false,592,463, -Math.toRadians(20)));
		venezia.addConnection(roma, railRoadList);	//venezia to roma


		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(4, "any", 0, true, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(4, "any", 0, true, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(4, "any", 0, true, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(4, "any", 0, true, false,592,463, -Math.toRadians(20)));
		roma.addConnection(marseille, railRoadList);	//roma to marseille
		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(2, "black", 0, false, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(2, "black", 0, false, false,592,463, -Math.toRadians(20)));
		roma.addConnection(venezia, railRoadList);	//roma to venezia
		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(2, "white", 0, false, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(2, "white", 0, false, false,592,463, -Math.toRadians(20)));
		roma.addConnection(brindisi, railRoadList);	//roma to brindesi
		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(4, "any", 0, false, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(4, "any", 0, false, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(4, "any", 0, false, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(4, "any", 0, false, false,592,463, -Math.toRadians(20)));
		roma.addConnection(palermo, railRoadList);	//roma to palermo


		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(3, "any", 1, false, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(3, "any", 1, false, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(3, "any", 1, false, false,592,463, -Math.toRadians(20)));
		kobenhavn.addConnection(essen, railRoadList);	//kobenhavn to essen
		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(3, "any", 1, false, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(3, "any", 1, false, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(3, "any", 1, false, false,592,463, -Math.toRadians(20)));
		kobenhavn.addSecondConnection(essen, railRoadList);	//kobenhavn to essen
		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(3, "white", 0, false, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(3, "white", 0, false, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(3, "white", 0, false, false,592,463, -Math.toRadians(20)));
		kobenhavn.addConnection(stockholm, railRoadList);	//kobenhavn to stockholm
		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(3, "yellow", 0, false, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(3, "yellow", 0, false, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(3, "yellow", 0, false, false,592,463, -Math.toRadians(20)));
		kobenhavn.addSecondConnection(stockholm, railRoadList);	//kobenhavn to stockholm



	}

	 private City addCity(String name, int x, int y) {
	    	City city = new City(name, null, x, y);
	        adjacencyList.add(city);
	    	return city;
	    }
	    
	    public int getRailRoadSizeX() {
	    	return railRoadSizeX;
	    }
	    
	    public int getRailRoadSizeY() {
	    	return railRoadSizeY;
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
	    
	    public void addStation(City city, Station station) {
	    	city.addStation(station);
	    }
}
