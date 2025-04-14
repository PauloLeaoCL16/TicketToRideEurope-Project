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
		City venezia = addCity("venezia", 695, 666);
		City warszawa = addCity("warszawa", 962, 352);
		City smolensk = addCity("smolensk", 1254, 322);

		//adding all the connections
		ArrayList<RailRoad> railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(2, "purple", 0, false, false,579, 455, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(2, "purple", 0, false, false,592,503, -Math.toRadians(20)));
		frankfurt.addConnection(munchen, railRoadList);	//frankfurt to munchen
		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(2, "green", 0, false, false,578,409, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(2, "green", 0, false, false,622,352, -Math.toRadians(20)));
		frankfurt.addConnection(essen, railRoadList);	//frankfurt to essen
		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(2, "white", 0, false, false,542,380, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(2, "white", 0, false, false,506,344, -Math.toRadians(20)));
		frankfurt.addConnection(amsterdam, railRoadList);	//frankfurt to amsterdam
		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(2, "blue", 0, false, false,529,393, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(2, "blue", 0, false, false,466,396, -Math.toRadians(20)));
		frankfurt.addConnection(bruxelles, railRoadList);	//frankfurt to bruxelles
		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(3, "black", 0, false, false,585,424, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(3, "black", 0, false, false,630,402, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(3, "black", 0, false, false,677,380, -Math.toRadians(20)));
		frankfurt.addConnection(berlin, railRoadList);	//frankfurt to berlin
		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(3, "red", 0, false, false,594,442, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(3, "red", 0, false, false,639,419, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(3, "red", 0, false, false,685,399, -Math.toRadians(20)));
		frankfurt.addSecondConnection(berlin, railRoadList); 	//frankfurt to berlin
		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(3, "white", 0, false, false,520,462, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(3, "white", 0, false, false,478,487, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(3, "white", 0, false, false,429,503, -Math.toRadians(20)));
		frankfurt.addConnection(paris, railRoadList);	//frankfurt to paris
		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(3, "orange", 0, false, false,529,477, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(3, "orange", 0, false, false,487,502, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(3, "orange", 0, false, false,439,518, -Math.toRadians(20)));
		frankfurt.addSecondConnection(paris, railRoadList);	//frankfurt to paris


		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(4, "black", 0, false, false,267,136, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(4, "black", 0, false, false,287,180, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(4, "black", 0, false, false,307,228, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(4, "black", 0, false, false,326,273, -Math.toRadians(20)));
		edinburgh.addConnection(london, railRoadList);	//edinburgh to london
		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(4, "orange", 0, false, false,285,129, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(4, "orange", 0, false, false,305,176, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(4, "orange", 0, false, false,326,219, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(4, "orange", 0, false, false,345,266, -Math.toRadians(20)));
		edinburgh.addSecondConnection(london, railRoadList);	//edinburgh to london


		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(2, "any", 2, false, false,370,319, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(2, "any", 2, false, false,418,320, -Math.toRadians(20)));
		london.addConnection(amsterdam, railRoadList);	//london to amsterdam
		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(2, "any", 1, false, false,349,340, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(2, "any", 1, false, false,346,390, -Math.toRadians(20)));
		london.addConnection(dieppe, railRoadList);	//london to dieppe
		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(2, "any", 1, false, false,332,339, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(2, "any", 1, false, false,327,388, -Math.toRadians(20)));
		london.addSecondConnection(dieppe, railRoadList);	//london to dieppe


		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(2, "green", 0, false, false,356,441, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(2, "green", 0, false, false,397,413, -Math.toRadians(20)));
		dieppe.addConnection(bruxelles, railRoadList);	//dieppe to bruxelles
		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(2, "orange", 0, false, false,273,440, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(2, "orange", 0, false, false,225,468, -Math.toRadians(20)));
		dieppe.addConnection(brest, railRoadList);	//dieppe to brest
		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(1, "purple", 0, false, false,359,457, -Math.toRadians(20)));
		dieppe.addConnection(paris, railRoadList);	//dieppe to paris	


		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(3, "black", 0, false, false,234,484, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(3, "black", 0, false, false,383,489, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(3, "black", 0, false, false,334,494, -Math.toRadians(20)));
		brest.addConnection(paris, railRoadList);	//brest to paris
		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(4, "purple", 0, false, false,234,503, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(4, "purple", 0, false, false,283,525, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(4, "purple", 0, false, false,312,573, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(4, "purple", 0, false, false,317,627, -Math.toRadians(20)));
		brest.addConnection(pamplona, railRoadList);	//brest to pamplona


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
		railRoadList.add(new RailRoad(2, "blue", 0, false, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(2, "blue", 0, false, false,592,463, -Math.toRadians(20)));
		cadiz.addConnection(lisboa, railRoadList);	//cadiz to lisboa


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
		railRoadList.add(new RailRoad(4, "any", 0, true, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(4, "any", 0, true, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(4, "any", 0, true, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(4, "any", 0, true, false,592,463, -Math.toRadians(20)));
		marseille.addConnection(roma, railRoadList);	//marseille to roma


		
		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(2, "yellow", 0, false, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(2, "yellow", 0, false, false,592,463, -Math.toRadians(20)));
		paris.addConnection(bruxelles, railRoadList);	//paris to bruxelles
		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(2, "red", 0, false, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(2, "red", 0, false, false,592,463, -Math.toRadians(20)));
		paris.addSecondConnection(bruxelles, railRoadList);	//paris to bruxelles
		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(3, "any", 0, true, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(3, "any", 0, true, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(3, "any", 0, true, false,592,463, -Math.toRadians(20)));
		paris.addConnection(zurich, railRoadList);	//paris to zurich


		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(2, "yellow", 0, true, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(2, "yellow", 0, true, false,592,463, -Math.toRadians(20)));
		zurich.addConnection(munchen, railRoadList);	//zurich to munchen
		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(2, "green", 0, true, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(2, "green", 0, true, false,592,463, -Math.toRadians(20)));
		zurich.addConnection(venezia, railRoadList);	//zurich to venezia

		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(1, "black", 0, false, false,592,463, -Math.toRadians(20)));
		bruxelles.addConnection(amsterdam, railRoadList);	//bruxelles to amsterdam


		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(3, "yellow", 0, false, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(3, "yellow", 0, false, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(3, "yellow", 0, false, false,592,463, -Math.toRadians(20)));
		amsterdam.addConnection(essen, railRoadList);	//amsterdam to essen


		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(2, "blue", 0, false, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(2, "blue", 0, false, false,592,463, -Math.toRadians(20)));
		essen.addConnection(berlin, railRoadList);	//essen to berlin


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
		railRoadList.add(new RailRoad(2, "any", 0, false, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(2, "any", 0, false, false,592,463, -Math.toRadians(20)));
		venezia.addConnection(zagrab, railRoadList);	//venezia to zagrab
		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(2, "black", 0, false, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(2, "black", 0, false, false,592,463, -Math.toRadians(20)));
		venezia.addConnection(roma, railRoadList);	//venezia to roma

		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(2, "white", 0, false, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(2, "white", 0, false, false,592,463, -Math.toRadians(20)));
		roma.addConnection(brindisi, railRoadList);	//roma to brindisi
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

		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(3, "any", 1, false, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(3, "any", 1, false, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(3, "any", 1, false, false,592,463, -Math.toRadians(20)));
		palermo.addConnection(brindisi, railRoadList);	//palermo to brindisi
		
		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(4, "any", 1, false, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(4, "any", 1, false, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(4, "any", 1, false, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(4, "any", 1, false, false,592,463, -Math.toRadians(20)));
		athina.addConnection(brindisi, railRoadList);	//athina to brindisi
		
		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(6, "any", 2, false, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(6, "any", 2, false, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(6, "any", 2, false, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(6, "any", 2, false, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(6, "any", 2, false, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(6, "any", 2, false, false,592,463, -Math.toRadians(20)));
		palermo.addConnection(smryna, railRoadList);	//palermo to smyrna
		
		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(2, "any", 1, false, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(2, "any", 1, false, false,592,463, -Math.toRadians(20)));
		athina.addConnection(smryna, railRoadList);	//athina to smyrna
		
		
		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(4, "green", 0, false, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(4, "green", 0, false, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(4, "green", 0, false, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(4, "green", 0, false, false,592,463, -Math.toRadians(20)));
		athina.addConnection(sarajevo, railRoadList);	//athina to sarajevo
		
		
		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(3, "red", 0, false, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(3, "red", 0, false, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(3, "red", 0, false, false,592,463, -Math.toRadians(20)));
		sarajevo.addConnection(zagrab, railRoadList);	//sarajevo to zagrab
		
		
		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(2, "any", 0, false, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(2, "any", 0, false, false,592,463, -Math.toRadians(20)));
		zagrab.addConnection(wien, railRoadList);	//zagrab to wien
		
		
		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(4, "any", 0, false, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(4, "any", 0, false, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(4, "any", 0, false, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(4, "any", 0, false, false,592,463, -Math.toRadians(20)));
		berlin.addConnection(danzig, railRoadList);	//berlin to danzig
		
		
		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(4, "purple", 0, false, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(4, "purple", 0, false, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(4, "purple", 0, false, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(4, "purple", 0, false, false,592,463, -Math.toRadians(20)));
		berlin.addConnection(warszawa, railRoadList);	//berlin to warszawa
		
		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(4, "yellow", 0, false, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(4, "yellow", 0, false, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(4, "yellow", 0, false, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(4, "yellow", 0, false, false,592,463, -Math.toRadians(20)));
		berlin.addSecondConnection(warszawa, railRoadList);	//berlin to warszawa
		
		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(3, "green", 0, false, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(3, "green", 0, false, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(3, "green", 0, false, false,592,463, -Math.toRadians(20)));
		berlin.addConnection(wien, railRoadList);	//berlin to wien
		
		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(4, "blue", 0, false, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(4, "blue", 0, false, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(4, "blue", 0, false, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(4, "blue", 0, false, false,592,463, -Math.toRadians(20)));
		warszawa.addConnection(wien, railRoadList);	//warszawa to wien

		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(1, "white", 0, false, false,592,463, -Math.toRadians(20)));
		wien.addConnection(budapest, railRoadList);	//wien to budapest
		
		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(1, "red", 0, false, false,592,463, -Math.toRadians(20)));
		wien.addSecondConnection(budapest, railRoadList);	//wien to budapest
		
		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(2, "orange", 0, false, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(2, "orange", 0, false, false,592,463, -Math.toRadians(20)));
		zagrab.addSecondConnection(budapest, railRoadList);	//zagrab to budapest
		
		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(3, "purple", 0, false, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(3, "purple", 0, false, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(3, "purple", 0, false, false,592,463, -Math.toRadians(20)));
		sarajevo.addSecondConnection(budapest, railRoadList);	//sarajevo to budapest
		
		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(3, "purple", 0, false, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(3, "purple", 0, false, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(3, "purple", 0, false, false,592,463, -Math.toRadians(20)));
		athina.addSecondConnection(sofia, railRoadList);	//athina to sofia
		
		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(2, "any", 0, true, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(2, "any", 0, true, false,592,463, -Math.toRadians(20)));
		sofia.addSecondConnection(sarajevo, railRoadList);	//sofia to sarajevo
		
		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(3, "blue", 0, false, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(3, "blue", 0, false, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(3, "blue", 0, false, false,592,463, -Math.toRadians(20)));
		sofia.addSecondConnection(constantinople, railRoadList);	//sofia to constantinople
		
		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(2, "any", 0, true, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(2, "any", 0, true, false,592,463, -Math.toRadians(20)));
		sofia.addSecondConnection(bucuresti, railRoadList);	//sofia to bucuresti
		
		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(6, "any", 0, true, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(6, "any", 0, true, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(6, "any", 0, true, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(6, "any", 0, true, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(6, "any", 0, true, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(6, "any", 0, true, false,592,463, -Math.toRadians(20)));
		kyiv.addConnection(budapest, railRoadList);	//kyiv to budapest
		
		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(4, "any", 0, false, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(4, "any", 0, false, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(4, "any", 0, false, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(4, "any", 0, false, false,592,463, -Math.toRadians(20)));
		kyiv.addConnection(warszawa, railRoadList);	//kyiv to warszawa
		
		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(3, "black", 0, false, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(3, "black", 0, false, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(3, "black", 0, false, false,592,463, -Math.toRadians(20)));
		danzig.addConnection(riga, railRoadList);	//danzig to riga
		
		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(4, "any", 0, true, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(4, "any", 0, true, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(4, "any", 0, true, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(4, "any", 0, true, false,592,463, -Math.toRadians(20)));
		bucuresti.addConnection(budapest, railRoadList); //bucuresti to budapest
		
		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(2, "any", 0, false, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(2, "any", 0, false, false,592,463, -Math.toRadians(20)));
		danzig.addConnection(warszawa, railRoadList);	//danzig to warszawa
		
		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(8, "any", 0, true, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(8, "any", 0, true, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(8, "any", 0, true, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(8, "any", 0, true, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(8, "any", 0, true, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(8, "any", 0, true, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(8, "any", 0, true, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(8, "any", 0, true, false,592,463, -Math.toRadians(20)));
		stockholm.addConnection(petrograd, railRoadList);	//stockholm to petrograd
		
		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(3, "red", 0, false, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(3, "red", 0, false, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(3, "red", 0, false, false,592,463, -Math.toRadians(20)));
		warszawa.addConnection(wilno, railRoadList);	//warszawa to wilno
		
		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(4, "green", 0, false, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(4, "green", 0, false, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(4, "green", 0, false, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(4, "green", 0, false, false,592,463, -Math.toRadians(20)));
		wilno.addConnection(riga, railRoadList);	//wilno to riga
		
		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(3, "yellow", 0, false, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(3, "yellow", 0, false, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(3, "yellow", 0, false, false,592,463, -Math.toRadians(20)));
		bucuresti.addConnection(constantinople, railRoadList);	//bucuresti to constantinople
		
		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(2, "any", 0, true, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(2, "any", 0, true, false,592,463, -Math.toRadians(20)));
		smryna.addConnection(constantinople, railRoadList);	//smyrna to constantinople
		
		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(3, "orange", 0, true, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(3, "orange", 0, true, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(3, "orange", 0, true, false,592,463, -Math.toRadians(20)));
		smryna.addConnection(angora, railRoadList);	//smyrna to angora
		
		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(2, "any", 0, true, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(2, "any", 0, true, false,592,463, -Math.toRadians(20)));
		constantinople.addConnection(angora, railRoadList);	//constantinople to angora
		
		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(4, "any", 0, false, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(4, "any", 0, false, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(4, "any", 0, false, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(4, "any", 0, false, false,592,463, -Math.toRadians(20)));
		bucuresti.addConnection(kyiv, railRoadList);	//bucuresti to kyiv
		
		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(4, "any", 0, false, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(4, "any", 0, false, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(4, "any", 0, false, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(4, "any", 0, false, false,592,463, -Math.toRadians(20)));
		riga.addConnection(petrograd, railRoadList);	//riga to petrograd
		
		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(4, "blue", 0, false, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(4, "blue", 0, false, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(4, "blue", 0, false, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(4, "blue", 0, false, false,592,463, -Math.toRadians(20)));
		wilno.addConnection(petrograd, railRoadList);	//wilno to petrograd
		
		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(2, "any", 0, false, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(2, "any", 0, false, false,592,463, -Math.toRadians(20)));
		wilno.addConnection(kyiv, railRoadList);	//wilno to kyiv
		
		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(4, "any", 2, false, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(4, "any", 2, false, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(4, "any", 2, false, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(4, "any", 2, false, false,592,463, -Math.toRadians(20)));
		constantinople.addConnection(sevastopol, railRoadList);	//constantinople to sevastopol
		
		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(4, "any", 2, false, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(4, "any", 2, false, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(4, "any", 2, false, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(4, "any", 2, false, false,592,463, -Math.toRadians(20)));
		erzurum.addConnection(sevastopol, railRoadList);	//erzurum to sevastopol
		
		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(3, "black", 0, false, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(3, "black", 0, false, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(3, "black", 0, false, false,592,463, -Math.toRadians(20)));
		erzurum.addConnection(angora, railRoadList);	//erzurum to angora
		
		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(3, "red", 0, true, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(3, "red", 0, true, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(3, "red", 0, true, false,592,463, -Math.toRadians(20)));
		erzurum.addConnection(sochi, railRoadList);	//erzurum to sochi
		
		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(2, "any", 1, false, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(2, "any", 1, false, false,592,463, -Math.toRadians(20)));
		sochi.addConnection(sevastopol, railRoadList);	//sochi to sevastopol
		
		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(2, "any", 0, false, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(2, "any", 0, false, false,592,463, -Math.toRadians(20)));
		sochi.addConnection(rostov, railRoadList);	//sochi to rostov

		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(4, "any", 0, false, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(4, "any", 0, false, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(4, "any", 0, false, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(4, "any", 0, false, false,592,463, -Math.toRadians(20)));
		sevastopol.addConnection(rostov, railRoadList);	//sevastopol to rostov
		
		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(4, "white", 0, false, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(4, "white", 0, false, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(4, "white", 0, false, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(4, "white", 0, false, false,592,463, -Math.toRadians(20)));
		sevastopol.addConnection(bucuresti, railRoadList);	//sevastopol to bucuresti

		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(2, "green", 0, false, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(2, "green", 0, false, false,592,463, -Math.toRadians(20)));
		rostov.addConnection(kharkov, railRoadList);	//rostov to kharkov
		
		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(4, "any", 0, false, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(4, "any", 0, false, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(4, "any", 0, false, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(4, "any", 0, false, false,592,463, -Math.toRadians(20)));
		kharkov.addConnection(kyiv, railRoadList);	//kharkov to kyiv
		
		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(3, "red", 0, false, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(3, "red", 0, false, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(3, "red", 0, false, false,592,463, -Math.toRadians(20)));
		kyiv.addConnection(smolensk, railRoadList);	//kyiv to smolensk
		
		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(3, "yellow", 0, false, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(3, "yellow", 0, false, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(3, "yellow", 0, false, false,592,463, -Math.toRadians(20)));
		smolensk.addConnection(wilno, railRoadList);	//smolensk to wilno
		
		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(2, "orange", 0, false, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(2, "orange", 0, false, false,592,463, -Math.toRadians(20)));
		smolensk.addConnection(moskva, railRoadList);	//smolensk to moskva
		
		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(4, "white", 0, false, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(4, "white", 0, false, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(4, "white", 0, false, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(4, "white", 0, false, false,592,463, -Math.toRadians(20)));
		moskva.addConnection(petrograd, railRoadList);	//moskva to petrograd
		
		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(4, "any", 0, false, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(4, "any", 0, false, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(4, "any", 0, false, false,592,463, -Math.toRadians(20)));
		railRoadList.add(new RailRoad(4, "any", 0, false, false,592,463, -Math.toRadians(20)));
		moskva.addConnection(kharkov, railRoadList);	//moskva to kharkov
		
	}

	private City addCity(String name, int x, int y) {
		City city = new City(name, null , x, y);
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
