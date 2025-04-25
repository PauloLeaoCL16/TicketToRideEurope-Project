package ttreImages;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.ArrayList;

public class Graph {
	private ArrayList<City> adjacencyList;
	private int clickRadius = 25;
 	private int railRoadSizeX = 50;
    private int railRoadSizeY = 17;	

	public Graph() {
		adjacencyList = new ArrayList<>();

		//declaring the cities
		City frankfurt = addCity("frankfurt", 568, 433);
		City brest = addCity("brest", 215, 489);
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

		//if the railroad is facing upwards, get point from top right
		//if the railroad is facing sideways, get the point from top left
		//adding all the connections
		ArrayList<RailRoad> railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(2, "purple", 0, false, false,591, 452, Math.toRadians(74.1)));
		railRoadList.add(new RailRoad(2, "purple", 0, false, false,592,503, Math.toRadians(-13.2)));
		frankfurt.addConnection(munchen, railRoadList);	//frankfurt to munchen	
		munchen.addConnection(frankfurt, railRoadList);	//frankfurt to munchen	
		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(2, "green", 0, false, false,578,409, Math.toRadians(-23.1)));
		railRoadList.add(new RailRoad(2, "green", 0, false, false,622,352, Math.toRadians(68.7)));
		frankfurt.addConnection(essen, railRoadList);	//frankfurt to essen
		essen.addConnection(frankfurt, railRoadList);	//frankfurt to essen
		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(2, "white", 0, false, false,542,380, Math.toRadians(180-135)));
		railRoadList.add(new RailRoad(2, "white", 0, false, false,506,344, Math.toRadians(180-135)));
		frankfurt.addConnection(amsterdam, railRoadList);	//frankfurt to amsterdam
		amsterdam.addConnection(frankfurt, railRoadList);	//frankfurt to amsterdam
		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(2, "blue", 0, false, false,554,433, Math.toRadians(-135)));
		railRoadList.add(new RailRoad(2, "blue", 0, false, false,515,399, Math.toRadians(166.1)));
		frankfurt.addConnection(bruxelles, railRoadList);	//frankfurt to bruxelles
		bruxelles.addConnection(frankfurt, railRoadList);	//frankfurt to bruxelles
		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(3, "black", 0, false, false,633,418, Math.toRadians(155.6)));
		railRoadList.add(new RailRoad(3, "black", 0, false, false,680,399, Math.toRadians(155.6)));
		railRoadList.add(new RailRoad(3, "black", 0, false, false,728,374, Math.toRadians(155.6)));
		frankfurt.addConnection(berlin, railRoadList);	//frankfurt to berlin
		berlin.addConnection(frankfurt, railRoadList);	//frankfurt to berlin
		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(3, "red", 0, false, false,641,436, Math.toRadians(155.6)));
		railRoadList.add(new RailRoad(3, "red", 0, false, false,686,413, Math.toRadians(155.6)));
		railRoadList.add(new RailRoad(3, "red", 0, false, false,734,391, Math.toRadians(155.6)));
		frankfurt.addSecondConnection(berlin, railRoadList); 	//frankfurt to berlin
		berlin.addSecondConnection(frankfurt, railRoadList); 	//frankfurt to berlin
		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(3, "white", 0, false, false,483,502, Math.toRadians(163.6)));
		railRoadList.add(new RailRoad(3, "white", 0, false, false,526,476, Math.toRadians(152.2)));
		railRoadList.add(new RailRoad(3, "white", 0, false, false,567,450, Math.toRadians(143.2)));
		frankfurt.addConnection(paris, railRoadList);	//frankfurt to paris
		paris.addConnection(frankfurt, railRoadList);	//frankfurt to paris
		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(3, "orange", 0, false, false,580,460, Math.toRadians(145.2)));
		railRoadList.add(new RailRoad(3, "orange", 0, false, false,536,493, Math.toRadians(152.2)));
		railRoadList.add(new RailRoad(3, "orange", 0, false, false,492,519, Math.toRadians(163.6)));
		frankfurt.addSecondConnection(paris, railRoadList);	//frankfurt to paris
		paris.addSecondConnection(frankfurt, railRoadList);	//frankfurt to paris


		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(4, "black", 0, false, false,267,136, Math.toRadians(68)));
		railRoadList.add(new RailRoad(4, "black", 0, false, false,287,180, Math.toRadians(68)));
		railRoadList.add(new RailRoad(4, "black", 0, false, false,307,228, Math.toRadians(68)));
		railRoadList.add(new RailRoad(4, "black", 0, false, false,326,273, Math.toRadians(68)));
		edinburgh.addConnection(london, railRoadList);	//edinburgh to london
		london.addConnection(edinburgh, railRoadList);	//edinburgh to london
		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(4, "orange", 0, false, false,285,129, Math.toRadians(68)));
		railRoadList.add(new RailRoad(4, "orange", 0, false, false,305,176, Math.toRadians(68)));
		railRoadList.add(new RailRoad(4, "orange", 0, false, false,326,219, Math.toRadians(68)));
		railRoadList.add(new RailRoad(4, "orange", 0, false, false,345,266, Math.toRadians(68)));
		edinburgh.addSecondConnection(london, railRoadList);	//edinburgh to london
		london.addSecondConnection(edinburgh, railRoadList);	//edinburgh to london


		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(2, "any", 2, false, false,416,331, Math.toRadians(-180)));
		railRoadList.add(new RailRoad(2, "any", 2, false, false,466,333, Math.toRadians(-180)));
		london.addConnection(amsterdam, railRoadList);	//london to amsterdam
		amsterdam.addConnection(london, railRoadList);	//london to amsterdam
		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(2, "any", 1, false, false,348,384, Math.toRadians(-86)));
		railRoadList.add(new RailRoad(2, "any", 1, false, false,343,433, Math.toRadians(-86)));
		london.addConnection(dieppe, railRoadList);	//london to dieppe
		dieppe.addConnection(london, railRoadList);	//london to dieppe
		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(2, "any", 1, false, false,328,385, Math.toRadians(-86)));
		railRoadList.add(new RailRoad(2, "any", 1, false, false,324,435, Math.toRadians(-86)));
		london.addSecondConnection(dieppe, railRoadList);	//london to dieppe
		dieppe.addSecondConnection(london, railRoadList);	//london to dieppe
		


		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(2, "green", 0, false, false,400,425, Math.toRadians(145)));
		railRoadList.add(new RailRoad(2, "green", 0, false, false,444,397, Math.toRadians(145)));
		dieppe.addConnection(bruxelles, railRoadList);	//dieppe to bruxelles
		bruxelles.addConnection(dieppe, railRoadList);	//dieppe to bruxelles
		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(2, "orange", 0, false, false,321,455, Math.toRadians(180)));
		railRoadList.add(new RailRoad(2, "orange", 0, false, false,270,455, Math.toRadians(147)));
		dieppe.addConnection(brest, railRoadList);	//dieppe to brest
		brest.addConnection(dieppe, railRoadList);	//dieppe to brest
		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(1, "purple", 0, false, false,359,457, Math.toRadians(43)));
		dieppe.addConnection(paris, railRoadList);	//dieppe to paris	
		paris.addConnection(dieppe, railRoadList);	//dieppe to paris	


		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(3, "black", 0, false, false,234,484, Math.toRadians(6)));
		railRoadList.add(new RailRoad(3, "black", 0, false, false,281,488, Math.toRadians(6)));
		railRoadList.add(new RailRoad(3, "black", 0, false, false,334,494, Math.toRadians(6)));
		brest.addConnection(paris, railRoadList);	//brest to paris
		paris.addConnection(brest, railRoadList);	//brest to paris
		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(4, "purple", 0, false, false,234,503, Math.toRadians(23)));
		railRoadList.add(new RailRoad(4, "purple", 0, false, false,283,525, Math.toRadians(57)));
		railRoadList.add(new RailRoad(4, "purple", 0, false, false,312,573, Math.toRadians(84)));
		railRoadList.add(new RailRoad(4, "purple", 0, false, false,317,627, Math.toRadians(90)));
		brest.addConnection(pamplona, railRoadList);	//brest to pamplona
		pamplona.addConnection(brest, railRoadList);	//brest to pamplona


		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(4, "blue", 0, false, false,344,671, Math.toRadians(126)));
		railRoadList.add(new RailRoad(4, "blue", 0, false, false,368,622, Math.toRadians(115)));
		railRoadList.add(new RailRoad(4, "blue", 0, false, false,383,571, Math.toRadians(106)));
		railRoadList.add(new RailRoad(4, "blue", 0, false, false,388,521, Math.toRadians(96)));
		pamplona.addConnection(paris, railRoadList);	//pamplona to paris
		paris.addConnection(pamplona, railRoadList);	//pamplona to paris
		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(4, "green", 0, false, false,362,677, Math.toRadians(126)));
		railRoadList.add(new RailRoad(4, "green", 0, false, false,384,627, Math.toRadians(115)));
		railRoadList.add(new RailRoad(4, "green", 0, false, false,398,578, Math.toRadians(106)));
		railRoadList.add(new RailRoad(4, "green", 0, false, false,407,528, Math.toRadians(96)));
		pamplona.addSecondConnection(paris, railRoadList);	//pamplona to paris
		paris.addSecondConnection(pamplona, railRoadList);	//pamplona to paris
		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(4, "red", 0, false, false,341,721, Math.toRadians(18)));
		railRoadList.add(new RailRoad(4, "red", 0, false, false,401,689, Math.toRadians(112)));
		railRoadList.add(new RailRoad(4, "red", 0, false, false,407,672, Math.toRadians(0)));
		railRoadList.add(new RailRoad(4, "red", 0, false, false,463,678, Math.toRadians(28)));
		pamplona.addConnection(marseille, railRoadList);	//pamplona to marseille
		marseille.addConnection(pamplona, railRoadList);	//pamplona to marseille
		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(3, "black", 0, true, false,296,726, Math.toRadians(146)));
		railRoadList.add(new RailRoad(3, "black", 0, true, false,256,753, Math.toRadians(135)));
		railRoadList.add(new RailRoad(3, "black", 0, true, false,222,788, Math.toRadians(128)));
		pamplona.addConnection(madrid, railRoadList);	//pamplona to madrid
		madrid.addConnection(pamplona, railRoadList);	//pamplona to madrid
		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(3, "white", 0, true, false,310,739, Math.toRadians(146)));
		railRoadList.add(new RailRoad(3, "white", 0, true, false,270,765, Math.toRadians(135)));
		railRoadList.add(new RailRoad(3, "white", 0, true, false,234,800, Math.toRadians(128)));
		pamplona.addSecondConnection(madrid, railRoadList);	//pamplona to madrid
		madrid.addSecondConnection(pamplona, railRoadList);	//pamplona to madrid
		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(2, "any", 0, true, false,328,739, Math.toRadians(84)));
		railRoadList.add(new RailRoad(2, "any", 0, true, false,334,789, Math.toRadians(84)));
		pamplona.addConnection(barcelona, railRoadList);	//pamplona to barcelona
		barcelona.addConnection(pamplona, railRoadList);	//pamplona to barcelona



		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(2, "yellow", 0, false, false,221,834, Math.toRadians(3)));
		railRoadList.add(new RailRoad(2, "yellow", 0, false, false,275,836, Math.toRadians(3)));
		barcelona.addConnection(madrid, railRoadList);	//barcelona to madrid
		madrid.addConnection(barcelona, railRoadList);	//barcelona to madrid
		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(4, "any", 0, false, false,385,804, Math.toRadians(130)));
		railRoadList.add(new RailRoad(4, "any", 0, false, false,423,772, Math.toRadians(139)));
		railRoadList.add(new RailRoad(4, "any", 0, false, false,468,749, Math.toRadians(150)));
		railRoadList.add(new RailRoad(4, "any", 0, false, false,512,728, Math.toRadians(159)));
		barcelona.addConnection(marseille, railRoadList);	//barcelona to marseille
		marseille.addConnection(barcelona, railRoadList);	//barcelona to marseille


		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(3, "purple", 0, false, false,140,791, Math.toRadians(32)));
		railRoadList.add(new RailRoad(3, "purple", 0, false, false,83,787, Math.toRadians(0)));
		railRoadList.add(new RailRoad(3, "purple", 0, false, false,95,807, Math.toRadians(90)));
		madrid.addConnection(lisboa, railRoadList);	//madrid to lisboa
		lisboa.addConnection(madrid, railRoadList);	//madrid to lisboa
		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(3, "orange", 0, false, false,202,839, Math.toRadians(40)));
		railRoadList.add(new RailRoad(3, "orange", 0, false, false,244,878, Math.toRadians(67)));
		railRoadList.add(new RailRoad(3, "orange", 0, false, false,240,924, Math.toRadians(160)));
		madrid.addConnection(cadiz, railRoadList);	//madrid to cadiz
		cadiz.addConnection(madrid, railRoadList);	//madrid to cadiz


		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(2, "blue", 0, false, false,166,941, Math.toRadians(-168)));
		railRoadList.add(new RailRoad(2, "blue", 0, false, false,108,921, Math.toRadians(-121)));
		cadiz.addConnection(lisboa, railRoadList);	//cadiz to lisboa
		lisboa.addConnection(cadiz, railRoadList);	//cadiz to lisboa


		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(4, "any", 0, false, false,503,659, Math.toRadians(60)));
		railRoadList.add(new RailRoad(4, "any", 0, false, false,457,632, Math.toRadians(27)));
		railRoadList.add(new RailRoad(4, "any", 0, false, false,426,592, Math.toRadians(56)));
		railRoadList.add(new RailRoad(4, "any", 0, false, false,423,540, Math.toRadians(83)));
		marseille.addConnection(paris, railRoadList);	//marseille to paris
		paris.addConnection(marseille, railRoadList);	//marseille to paris
		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(2, "purple", 0, true, false,573,606, Math.toRadians(105)));
		railRoadList.add(new RailRoad(2, "purple", 0, true, false,561,653, Math.toRadians(105)));
		marseille.addConnection(zurich, railRoadList);	//marseille to zurich
		zurich.addConnection(marseille, railRoadList);	//marseille to zurich
		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(4, "any", 0, true, false,585,692, Math.toRadians(140)));
		railRoadList.add(new RailRoad(4, "any", 0, true, false,625,663, Math.toRadians(140)));
		railRoadList.add(new RailRoad(4, "any", 0, true, false,630,668, Math.toRadians(52)));
		railRoadList.add(new RailRoad(4, "any", 0, true, false,662,710, Math.toRadians(52)));
		marseille.addConnection(roma, railRoadList);	//marseille to roma
		roma.addConnection(marseille, railRoadList);	//marseille to roma


		
		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(2, "yellow", 0, false, false,435,453, Math.toRadians(117)));
		railRoadList.add(new RailRoad(2, "yellow", 0, false, false,455,412, Math.toRadians(117)));
		paris.addConnection(bruxelles, railRoadList);	//paris to bruxelles
		bruxelles.addConnection(paris, railRoadList);	//paris to bruxelles
		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(2, "red", 0, false, false,449,463, Math.toRadians(117)));
		railRoadList.add(new RailRoad(2, "red", 0, false, false,473,419, Math.toRadians(117)));
		paris.addSecondConnection(bruxelles, railRoadList);	//paris to bruxelles
		bruxelles.addSecondConnection(paris, railRoadList);	//paris to bruxelles
		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(3, "any", 0, true, false,436,531, Math.toRadians(60)));
		railRoadList.add(new RailRoad(3, "any", 0, true, false,465,571, Math.toRadians(26)));
		railRoadList.add(new RailRoad(3, "any", 0, true, false,552,601, Math.toRadians(179)));
		paris.addConnection(zurich, railRoadList);	//paris to zurich
		zurich.addConnection(paris, railRoadList);	//paris to zurich


		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(2, "yellow", 0, true, false,613,552, Math.toRadians(135)));
		railRoadList.add(new RailRoad(2, "yellow", 0, true, false,652,518, Math.toRadians(135)));
		zurich.addConnection(munchen, railRoadList);	//zurich to munchen
		munchen.addConnection(zurich, railRoadList);	//zurich to munchen
		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(2, "green", 0, true, false,586,590, Math.toRadians(23)));
		railRoadList.add(new RailRoad(2, "green", 0, true, false,631,613, Math.toRadians(23)));
		zurich.addConnection(venezia, railRoadList);	//zurich to venezia
		venezia.addConnection(zurich, railRoadList);	//zurich to venezia

		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(1, "black", 0, false, false,487,347, Math.toRadians(116)));
		bruxelles.addConnection(amsterdam, railRoadList);	//bruxelles to amsterdam
		amsterdam.addConnection(bruxelles, railRoadList);	//bruxelles to amsterdam


		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(3, "yellow", 0, false, false,503,277, Math.toRadians(111)));
		railRoadList.add(new RailRoad(3, "yellow", 0, false, false,549,301, Math.toRadians(-168)));
		railRoadList.add(new RailRoad(3, "yellow", 0, false, false,580,340, Math.toRadians(-130)));
		amsterdam.addConnection(essen, railRoadList);	//amsterdam to essen
		essen.addConnection(amsterdam, railRoadList);	//amsterdam to essen


		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(2, "blue", 0, false, false,627,329, Math.toRadians(10)));
		railRoadList.add(new RailRoad(2, "blue", 0, false, false,676,336, Math.toRadians(10)));
		essen.addConnection(berlin, railRoadList);	//essen to berlin
		berlin.addConnection(essen, railRoadList);	//essen to berlin


		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(3, "orange", 0, false, false,675,507, Math.toRadians(52)));
		railRoadList.add(new RailRoad(3, "orange", 0, false, false,707,545, Math.toRadians(2)));
		railRoadList.add(new RailRoad(3, "orange", 0, false, false,801,525, Math.toRadians(143)));
		munchen.addConnection(wien, railRoadList);	//munchen to wien
		wien.addConnection(munchen, railRoadList);	//munchen to wien
		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(2, "blue", 0, true, false,664,526, Math.toRadians(79)));
		railRoadList.add(new RailRoad(2, "blue", 0, true, false,674,574, Math.toRadians(79)));
		munchen.addConnection(venezia, railRoadList);	//munchen to venezia
		venezia.addConnection(munchen, railRoadList);	//munchen to venezia


		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(2, "any", 0, false, false,739,621, Math.toRadians(163)));
		railRoadList.add(new RailRoad(2, "any", 0, false, false,750,612, Math.toRadians(24)));
		venezia.addConnection(zagrab, railRoadList);	//venezia to zagrab
		zagrab.addConnection(venezia, railRoadList);	//venezia to zagrab
		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(2, "black", 0, false, false,689,644, Math.toRadians(77)));
		railRoadList.add(new RailRoad(2, "black", 0, false, false,696,691, Math.toRadians(77)));
		venezia.addConnection(roma, railRoadList);	//venezia to roma
		roma.addConnection(venezia, railRoadList);	//venezia to roma

		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(2, "white", 0, false, false,711,738, Math.toRadians(0)));
		railRoadList.add(new RailRoad(2, "white", 0, false, false,774,741, Math.toRadians(52)));
		roma.addConnection(brindisi, railRoadList);	//roma to brindisi
		brindisi.addConnection(roma, railRoadList);	//roma to brindisi
		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(4, "any", 0, false, false,707,755, Math.toRadians(29)));
		railRoadList.add(new RailRoad(4, "any", 0, false, false,754,785, Math.toRadians(46)));
		railRoadList.add(new RailRoad(4, "any", 0, false, false,784,833, Math.toRadians(90)));
		railRoadList.add(new RailRoad(4, "any", 0, false, false,778,889, Math.toRadians(130)));
		roma.addConnection(palermo, railRoadList);	//roma to palermo
		palermo.addConnection(roma, railRoadList);	//roma to palermo


		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(3, "any", 1, false, false,701,215, Math.toRadians(124)));
		railRoadList.add(new RailRoad(3, "any", 1, false, false,673,258, Math.toRadians(124)));
		railRoadList.add(new RailRoad(3, "any", 1, false, false,644,301, Math.toRadians(124)));
		kobenhavn.addConnection(essen, railRoadList);	//kobenhavn to essen
		essen.addConnection(kobenhavn, railRoadList);	//kobenhavn to essen
		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(3, "any", 1, false, false,686,205, Math.toRadians(124)));
		railRoadList.add(new RailRoad(3, "any", 1, false, false,656,249, Math.toRadians(124)));
		railRoadList.add(new RailRoad(3, "any", 1, false, false,626,291, Math.toRadians(124)));
		kobenhavn.addSecondConnection(essen, railRoadList);	//kobenhavn to essen
		essen.addSecondConnection(kobenhavn, railRoadList);	//kobenhavn to essen
		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(3, "white", 0, false, false,752,170, Math.toRadians(133)));
		railRoadList.add(new RailRoad(3, "white", 0, false, false,787,139, Math.toRadians(140)));
		railRoadList.add(new RailRoad(3, "white", 0, false, false,834,114, Math.toRadians(151)));
		kobenhavn.addConnection(stockholm, railRoadList);	//kobenhavn to stockholm
		stockholm.addConnection(kobenhavn, railRoadList);	//kobenhavn to stockholm
		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(3, "yellow", 0, false, false,740,155, Math.toRadians(133)));
		railRoadList.add(new RailRoad(3, "yellow", 0, false, false,778,122, Math.toRadians(140)));
		railRoadList.add(new RailRoad(3, "yellow", 0, false, false,822,97, Math.toRadians(151)));
		kobenhavn.addSecondConnection(stockholm, railRoadList);	//kobenhavn to stockholm
		stockholm.addSecondConnection(kobenhavn, railRoadList);	//kobenhavn to stockholm

		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(3, "any", 1, false, false,794,901, Math.toRadians(130)));
		railRoadList.add(new RailRoad(3, "any", 1, false, false,829,865, Math.toRadians(132)));
		railRoadList.add(new RailRoad(3, "any", 1, false, false,816,806	, Math.toRadians(62)));
		palermo.addConnection(brindisi, railRoadList);	//palermo to brindisi
		brindisi.addConnection(palermo, railRoadList);	//palermo to brindisi
		
		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(4, "any", 1, false, false,969,910, Math.toRadians(172)));
		railRoadList.add(new RailRoad(4, "any", 1, false, false,873,889, Math.toRadians(16)));
		railRoadList.add(new RailRoad(4, "any", 1, false, false,854,845, Math.toRadians(66)));
		railRoadList.add(new RailRoad(4, "any", 1, false, false,834,797	, Math.toRadians(66)));
		athina.addConnection(brindisi, railRoadList);	//athina to brindisi
		brindisi.addConnection(athina, railRoadList);	//athina to brindisi
		
		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(6, "any", 2, false, false,785,925, Math.toRadians(0)));
		railRoadList.add(new RailRoad(6, "any", 2, false, false,834,925, Math.toRadians(0)));
		railRoadList.add(new RailRoad(6, "any", 2, false, false,884,925, Math.toRadians(0)));
		railRoadList.add(new RailRoad(6, "any", 2, false, false,928,925, Math.toRadians(0)));
		railRoadList.add(new RailRoad(6, "any", 2, false, false,987,925, Math.toRadians(0)));
		railRoadList.add(new RailRoad(6, "any", 2, false, false,1037,925, Math.toRadians(0)));
		palermo.addConnection(smryna, railRoadList);	//palermo to smyrna
		smryna.addConnection(palermo, railRoadList);	//palermo to smyrna
		
		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(2, "any", 1, false, false,993,888, Math.toRadians(0)));
		railRoadList.add(new RailRoad(2, "any", 1, false, false,1048,890, Math.toRadians(22)));
		athina.addConnection(smryna, railRoadList);	//athina to smyrna
		smryna.addConnection(athina, railRoadList);	//athina to smyrna
		
		
		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(4, "green", 0, false, false,918,876, Math.toRadians(0)));
		railRoadList.add(new RailRoad(4, "green", 0, false, false,914,845, Math.toRadians(96)));
		railRoadList.add(new RailRoad(4, "green", 0, false, false,918,794, Math.toRadians(96)));
		railRoadList.add(new RailRoad(4, "green", 0, false, false,920,742, Math.toRadians(96)));
		athina.addConnection(sarajevo, railRoadList);	//athina to sarajevo
		sarajevo.addConnection(athina, railRoadList);	//athina to sarajevo
		
		
		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(3, "red", 0, false, false,902,734, Math.toRadians(161)));
		railRoadList.add(new RailRoad(3, "red", 0, false, false,813,711, Math.toRadians(39)));
		railRoadList.add(new RailRoad(3, "red", 0, false, false,806,662, Math.toRadians(84)));
		sarajevo.addConnection(zagrab, railRoadList);	//sarajevo to zagrab
		zagrab.addConnection(sarajevo, railRoadList);	//sarajevo to zagrab
		
		
		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(2, "any", 0, false, false,807,587, Math.toRadians(95)));
		railRoadList.add(new RailRoad(2, "any", 0, false, false,812,538, Math.toRadians(95)));
		zagrab.addConnection(wien, railRoadList);	//zagrab to wien
		wien.addConnection(zagrab, railRoadList);	//zagrab to wien
		
		
		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(4, "any", 0, false, false,752,299, Math.toRadians(98)));
		railRoadList.add(new RailRoad(4, "any", 0, false, false,781,259, Math.toRadians(129)));
		railRoadList.add(new RailRoad(4, "any", 0, false, false,830,247, Math.toRadians(169)));
		railRoadList.add(new RailRoad(4, "any", 0, false, false,841,233, Math.toRadians(12)));
		berlin.addConnection(danzig, railRoadList);	//berlin to danzig
		danzig.addConnection(berlin, railRoadList);	//berlin to danzig
		
		
		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(4, "purple", 0, false, false,793,349, Math.toRadians(160)));
		railRoadList.add(new RailRoad(4, "purple", 0, false, false,845,343, Math.toRadians(175)));
		railRoadList.add(new RailRoad(4, "purple", 0, false, false,849,326, Math.toRadians(0)));
		railRoadList.add(new RailRoad(4, "purple", 0, false, false,902,326, Math.toRadians(9)));
		berlin.addConnection(warszawa, railRoadList);	//berlin to warszawa
		warszawa.addConnection(berlin, railRoadList);	//berlin to warszawa
		
		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(4, "yellow", 0, false, false,797,367, Math.toRadians(160)));
		railRoadList.add(new RailRoad(4, "yellow", 0, false, false,846,362, Math.toRadians(175)));
		railRoadList.add(new RailRoad(4, "yellow", 0, false, false,851,348, Math.toRadians(0)));
		railRoadList.add(new RailRoad(4, "yellow", 0, false, false,901,350, Math.toRadians(9)));
		berlin.addSecondConnection(warszawa, railRoadList);	//berlin to warszawa
		warszawa.addSecondConnection(berlin, railRoadList);	//berlin to warszawa
		
		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(3, "green", 0, false, false,749,380, Math.toRadians(73)));
		railRoadList.add(new RailRoad(3, "green", 0, false, false,763,428, Math.toRadians(60)));
		railRoadList.add(new RailRoad(3, "green", 0, false, false,787,472, Math.toRadians(51)));
		berlin.addConnection(wien, railRoadList);	//berlin to wien
		wien.addConnection(berlin, railRoadList);	//berlin to wien
		
		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(4, "blue", 0, false, false,963,369, Math.toRadians(124)));
		railRoadList.add(new RailRoad(4, "blue", 0, false, false,935,417, Math.toRadians(127)));
		railRoadList.add(new RailRoad(4, "blue", 0, false, false,902,459, Math.toRadians(137)));
		railRoadList.add(new RailRoad(4, "blue", 0, false, false,866,495, Math.toRadians(150)));
		warszawa.addConnection(wien, railRoadList);	//warszawa to wien
		wien.addConnection(warszawa, railRoadList);	//warszawa to wien

		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(1, "white", 0, false, false,833,521, Math.toRadians(31)));
		wien.addConnection(budapest, railRoadList);	//wien to budapest
		budapest.addConnection(wien, railRoadList);	//wien to budapest
		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(1, "red", 0, false, false,822,538, Math.toRadians(31)));
		wien.addSecondConnection(budapest, railRoadList);	//wien to budapest
		budapest.addSecondConnection(wien, railRoadList);	//wien to budapest
		
		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(2, "orange", 0, false, false,849,617, Math.toRadians(135)));
		railRoadList.add(new RailRoad(2, "orange", 0, false, false,875,573, Math.toRadians(124)));
		zagrab.addSecondConnection(budapest, railRoadList);	//zagrab to budapest
		budapest.addSecondConnection(zagrab, railRoadList);	//zagrab to budapest
		
		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(3, "purple", 0, false, false,909,672, Math.toRadians(81)));
		railRoadList.add(new RailRoad(3, "purple", 0, false, false,901,622, Math.toRadians(81)));
		railRoadList.add(new RailRoad(3, "purple", 0, false, false,897,578, Math.toRadians(81)));
		sarajevo.addSecondConnection(budapest, railRoadList);	//sarajevo to budapest
		budapest.addSecondConnection(sarajevo, railRoadList);	//sarajevo to budapest
		
		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(3, "purple", 0, false, false,963,841, Math.toRadians(62)));
		railRoadList.add(new RailRoad(3, "purple", 0, false, false,968,790, Math.toRadians(94)));
		railRoadList.add(new RailRoad(3, "purple", 0, false, false,997,756, Math.toRadians(133)));
		athina.addSecondConnection(sofia, railRoadList);	//athina to sofia
		sofia.addSecondConnection(athina, railRoadList);	//athina to sofia
		
		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(2, "any", 0, true, false,980,689, Math.toRadians(62)));
		railRoadList.add(new RailRoad(2, "any", 0, true, false,969,710, Math.toRadians(157)));
		sofia.addSecondConnection(sarajevo, railRoadList);	//sofia to sarajevo
		sarajevo.addSecondConnection(sofia, railRoadList);	//sofia to sarajevo
		
		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(3, "blue", 0, false, false,1018,742, Math.toRadians(30)));
		railRoadList.add(new RailRoad(3, "blue", 0, false, false,1065,766, Math.toRadians(30)));
		railRoadList.add(new RailRoad(3, "blue", 0, false, false,1109,792, Math.toRadians(30)));
		sofia.addSecondConnection(constantinople, railRoadList);	//sofia to constantinople
		constantinople.addSecondConnection(sofia, railRoadList);	//sofia to constantinople
		
		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(2, "any", 0, true, false,1061,724, Math.toRadians(164)));
		railRoadList.add(new RailRoad(2, "any", 0, true, false,1079,667, Math.toRadians(94)));
		sofia.addSecondConnection(bucuresti, railRoadList);	//sofia to bucuresti
		bucuresti.addSecondConnection(sofia, railRoadList);	//sofia to bucuresti
		
		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(6, "any", 0, true, false,1111,426, Math.toRadians(3)));
		railRoadList.add(new RailRoad(6, "any", 0, true, false,1099,440, Math.toRadians(173)));
		railRoadList.add(new RailRoad(6, "any", 0, true, false,1049,442, Math.toRadians(163)));
		railRoadList.add(new RailRoad(6, "any", 0, true, false,1005,461, Math.toRadians(151)));
		railRoadList.add(new RailRoad(6, "any", 0, true, false,959,487, Math.toRadians(143)));
		railRoadList.add(new RailRoad(6, "any", 0, true, false,919,514, Math.toRadians(131)));
		kyiv.addConnection(budapest, railRoadList);	//kyiv to budapest
		budapest.addConnection(kyiv, railRoadList);	//kyiv to budapest
		
		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(4, "any", 0, false, false,1109,405, Math.toRadians(6)));
		railRoadList.add(new RailRoad(4, "any", 0, false, false,1055,406, Math.toRadians(0)));
		railRoadList.add(new RailRoad(4, "any", 0, false, false,1009,390, Math.toRadians(24)));
		railRoadList.add(new RailRoad(4, "any", 0, false, false,975,355, Math.toRadians(45)));
		kyiv.addConnection(warszawa, railRoadList);	//kyiv to warszawa
		warszawa.addConnection(kyiv, railRoadList);	//kyiv to warszawa
		
		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(3, "black", 0, false, false,916,203, Math.toRadians(105)));
		railRoadList.add(new RailRoad(3, "black", 0, false, false,947,162, Math.toRadians(123)));
		railRoadList.add(new RailRoad(3, "black", 0, false, false,995,144, Math.toRadians(163)));
		danzig.addConnection(riga, railRoadList);	//danzig to riga
		riga.addConnection(danzig, railRoadList);	//danzig to riga
		
		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(4, "any", 0, true, false,1038,623, Math.toRadians(25)));
		railRoadList.add(new RailRoad(4, "any", 0, true, false,991,601, Math.toRadians(25)));
		railRoadList.add(new RailRoad(4, "any", 0, true, false,946,575, Math.toRadians(25)));
		railRoadList.add(new RailRoad(4, "any", 0, true, false,902,552, Math.toRadians(25)));
		bucuresti.addConnection(budapest, railRoadList); //bucuresti to budapest
		budapest.addConnection(bucuresti, railRoadList); //bucuresti to budapest
		
		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(2, "any", 0, false, false,907,256, Math.toRadians(32)));
		railRoadList.add(new RailRoad(2, "any", 0, false, false,955,291	, Math.toRadians(78)));
		danzig.addConnection(warszawa, railRoadList);	//danzig to warszawa
		warszawa.addConnection(danzig, railRoadList);	//danzig to warszawa
		
		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(8, "any", 0, true, false,858,102, Math.toRadians(53)));
		railRoadList.add(new RailRoad(8, "any", 0, true, false,922,106, Math.toRadians(141)));
		railRoadList.add(new RailRoad(8, "any", 0, true, false,930,88, Math.toRadians(0)));
		railRoadList.add(new RailRoad(8, "any", 0, true, false,981,90, Math.toRadians(0)));
		railRoadList.add(new RailRoad(8, "any", 0, true, false,1034,88, Math.toRadians(0)));
		railRoadList.add(new RailRoad(8, "any", 0, true, false,1081,88, Math.toRadians(0)));
		railRoadList.add(new RailRoad(8, "any", 0, true, false,1133,88, Math.toRadians(0)));
		railRoadList.add(new RailRoad(8, "any", 0, true, false,1189,91, Math.toRadians(22)));
		stockholm.addConnection(petrograd, railRoadList);	//stockholm to petrograd
		petrograd.addConnection(stockholm, railRoadList);	//stockholm to petrograd
		
		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(3, "red", 0, false, false,1003,306, Math.toRadians(119)));
		railRoadList.add(new RailRoad(3, "red", 0, false, false,1050,296, Math.toRadians(165)));
		railRoadList.add(new RailRoad(3, "red", 0, false, false,1061,284, Math.toRadians(31)));
		warszawa.addConnection(wilno, railRoadList);	//warszawa to wilno
		wilno.addConnection(warszawa, railRoadList);	//warszawa to wilno
		
		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(4, "green", 0, false, false,1072,269, Math.toRadians(31)));
		railRoadList.add(new RailRoad(4, "green", 0, false, false,1028,242, Math.toRadians(31)));
		railRoadList.add(new RailRoad(4, "green", 0, false, false,1007,200, Math.toRadians(69)));
		railRoadList.add(new RailRoad(4, "green", 0, false, false,1017,150, Math.toRadians(101)));
		wilno.addConnection(riga, railRoadList);	//wilno to riga
		riga.addConnection(wilno, railRoadList);	//wilno to riga
		
		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(3, "yellow", 0, false, false,1102,667, Math.toRadians(65)));
		railRoadList.add(new RailRoad(3, "yellow", 0, false, false,1119,713, Math.toRadians(65)));
		railRoadList.add(new RailRoad(3, "yellow", 0, false, false,1140,760, Math.toRadians(65)));
		bucuresti.addConnection(constantinople, railRoadList);	//bucuresti to constantinople
		constantinople.addConnection(bucuresti, railRoadList);	//bucuresti to constantinople
		
		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(2, "any", 0, true, false,1127,884, Math.toRadians(117)));
		railRoadList.add(new RailRoad(2, "any", 0, true, false,1152,837, Math.toRadians(117)));
		smryna.addConnection(constantinople, railRoadList);	//smyrna to constantinople
		constantinople.addConnection(smryna, railRoadList);	//smyrna to constantinople
		
		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(3, "orange", 0, true, false,1117,927, Math.toRadians(0)));
		railRoadList.add(new RailRoad(3, "orange", 0, true, false,1207,940, Math.toRadians(177)));
		railRoadList.add(new RailRoad(3, "orange", 0, true, false,1261,915, Math.toRadians(146)));
		smryna.addConnection(angora, railRoadList);	//smyrna to angora
		angora.addConnection(smryna, railRoadList);	//smyrna to angora
		
		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(2, "any", 0, true, false,1179,827, Math.toRadians(37)));
		railRoadList.add(new RailRoad(2, "any", 0, true, false,1219,858, Math.toRadians(37)));
		constantinople.addConnection(angora, railRoadList);	//constantinople to angora
		angora.addConnection(constantinople, railRoadList);	//constantinople to angora
		
		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(4, "any", 0, false, false,1106,595, Math.toRadians(110)));
		railRoadList.add(new RailRoad(4, "any", 0, false, false,1123,548, Math.toRadians(110)));
		railRoadList.add(new RailRoad(4, "any", 0, false, false,1136,501, Math.toRadians(110)));
		railRoadList.add(new RailRoad(4, "any", 0, false, false,1153,452, Math.toRadians(110)));
		bucuresti.addConnection(kyiv, railRoadList);	//bucuresti to kyiv
		kyiv.addConnection(bucuresti, railRoadList);	//bucuresti to kyiv
		
		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(4, "any", 0, false, false,1020,128, Math.toRadians(0)));
		railRoadList.add(new RailRoad(4, "any", 0, false, false,1074,126, Math.toRadians(0)));
		railRoadList.add(new RailRoad(4, "any", 0, false, false,1126,125, Math.toRadians(0)));
		railRoadList.add(new RailRoad(4, "any", 0, false, false,1177,125, Math.toRadians(0)));
		riga.addConnection(petrograd, railRoadList);	//riga to petrograd
		petrograd.addConnection(riga, railRoadList);	//riga to petrograd
		
		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(4, "blue", 0, false, false,1151,267, Math.toRadians(124)));
		railRoadList.add(new RailRoad(4, "blue", 0, false, false,1179,227, Math.toRadians(124)));
		railRoadList.add(new RailRoad(4, "blue", 0, false, false,1209,186, Math.toRadians(124)));
		railRoadList.add(new RailRoad(4, "blue", 0, false, false,1241,144, Math.toRadians(124)));
		wilno.addConnection(petrograd, railRoadList);	//wilno to petrograd
		petrograd.addConnection(wilno, railRoadList);	//wilno to petrograd
		
		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(2, "any", 0, false, false,1131,321, Math.toRadians(35)));
		railRoadList.add(new RailRoad(2, "any", 0, false, false,1178,362, Math.toRadians(92)));
		wilno.addConnection(kyiv, railRoadList);	//wilno to kyiv
		kyiv.addConnection(wilno, railRoadList);	//wilno to kyiv
		
		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(4, "any", 2, false, false,1198,781, Math.toRadians(126)));
		railRoadList.add(new RailRoad(4, "any", 2, false, false,1201,759, Math.toRadians(38)));
		railRoadList.add(new RailRoad(4, "any", 2, false, false,1266,744, Math.toRadians(126)));
		railRoadList.add(new RailRoad(4, "any", 2, false, false,1271,687, Math.toRadians(90)));
		constantinople.addConnection(sevastopol, railRoadList);	//constantinople to sevastopol
		sevastopol.addConnection(constantinople, railRoadList);	//constantinople to sevastopol
		
		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(4, "any", 2, false, false,1327,830, Math.toRadians(30)));
		railRoadList.add(new RailRoad(4, "any", 2, false, false,1298,787, Math.toRadians(53)));
		railRoadList.add(new RailRoad(4, "any", 2, false, false,1289,737, Math.toRadians(81)));
		railRoadList.add(new RailRoad(4, "any", 2, false, false,1290,689, Math.toRadians(90)));
		erzurum.addConnection(sevastopol, railRoadList);	//erzurum to sevastopol
		sevastopol.addConnection(erzurum, railRoadList);	//erzurum to sevastopol
		
		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(3, "black", 0, false, false,1384,880, Math.toRadians(79)));
		railRoadList.add(new RailRoad(3, "black", 0, false, false,1372,925, Math.toRadians(171)));
		railRoadList.add(new RailRoad(3, "black", 0, false, false,1284,898, Math.toRadians(33)));
		erzurum.addConnection(angora, railRoadList);	//erzurum to angora
		angora.addConnection(erzurum, railRoadList);	//erzurum to angora
		
		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(3, "red", 0, true, false,1391,812, Math.toRadians(92)));
		railRoadList.add(new RailRoad(3, "red", 0, true, false,1398,762, Math.toRadians(92)));
		railRoadList.add(new RailRoad(3, "red", 0, true, false,1405,711, Math.toRadians(92)));
		erzurum.addConnection(sochi, railRoadList);	//erzurum to sochi
		sochi.addConnection(erzurum, railRoadList);	//erzurum to sochi
		
		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(2, "any", 1, false, false,1344,677, Math.toRadians(8)));
		railRoadList.add(new RailRoad(2, "any", 1, false, false,1296,667, Math.toRadians(8)));
		sochi.addConnection(sevastopol, railRoadList);	//sochi to sevastopol
		sevastopol.addConnection(sochi, railRoadList);	//sochi to sevastopol
		
		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(2, "any", 0, false, false,1411,635, Math.toRadians(95)));
		railRoadList.add(new RailRoad(2, "any", 0, false, false,1416,585, Math.toRadians(95)));
		sochi.addConnection(rostov, railRoadList);	//sochi to rostov
		rostov.addConnection(sochi, railRoadList);	//sochi to rostov

		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(4, "any", 0, false, false,1302,617, Math.toRadians(100)));
		railRoadList.add(new RailRoad(4, "any", 0, false, false,1309,567, Math.toRadians(100)));
		railRoadList.add(new RailRoad(4, "any", 0, false, false,1300,545, Math.toRadians(9)));
		railRoadList.add(new RailRoad(4, "any", 0, false, false,1351,553, Math.toRadians(9)));
		sevastopol.addConnection(rostov, railRoadList);	//sevastopol to rostov
		rostov.addConnection(sevastopol, railRoadList);	//sevastopol to rostov
		
		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(4, "white", 0, false, false,1247, 620, Math.toRadians(55)));
		railRoadList.add(new RailRoad(4, "white", 0, false, false,1198,594, Math.toRadians(23)));
		railRoadList.add(new RailRoad(4, "white", 0, false, false,1187,605, Math.toRadians(167)));
		railRoadList.add(new RailRoad(4, "white", 0, false, false,1137,618, Math.toRadians(136)));
		sevastopol.addConnection(bucuresti, railRoadList);	//sevastopol to bucuresti
		bucuresti.addConnection(sevastopol, railRoadList);	//sevastopol to bucuresti

		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(2, "green", 0, false, false,1415,510, Math.toRadians(88)));
		railRoadList.add(new RailRoad(2, "green", 0, false, false,1370,493, Math.toRadians(0)));
		rostov.addConnection(kharkov, railRoadList);	//rostov to kharkov
		kharkov.addConnection(rostov, railRoadList);	//rostov to kharkov
		
		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(4, "any", 0, false, false,1340,507, Math.toRadians(163)));
		railRoadList.add(new RailRoad(4, "any", 0, false, false,1241,506, Math.toRadians(0)));
		railRoadList.add(new RailRoad(4, "any", 0, false, false,1198,484, Math.toRadians(27)));
		railRoadList.add(new RailRoad(4, "any", 0, false, false,1179,438, Math.toRadians(69)));
		kharkov.addConnection(kyiv, railRoadList);	//kharkov to kyiv
		kyiv.addConnection(kharkov, railRoadList);	//kharkov to kyiv
		
		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(3, "red", 0, false, false,1184,418, Math.toRadians(0)));
		railRoadList.add(new RailRoad(3, "red", 0, false, false,1276,396, Math.toRadians(133)));
		railRoadList.add(new RailRoad(3, "red", 0, false, false,1265,337, Math.toRadians(73)));
		kyiv.addConnection(smolensk, railRoadList);	//kyiv to smolensk
		smolensk.addConnection(kyiv, railRoadList);	//kyiv to smolensk
		
		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(3, "yellow", 0, false, false,1209,289, Math.toRadians(35)));
		railRoadList.add(new RailRoad(3, "yellow", 0, false, false,1167,257, Math.toRadians(35)));
		railRoadList.add(new RailRoad(3, "yellow", 0, false, false,1167,281, Math.toRadians(123)));
		smolensk.addConnection(wilno, railRoadList);	//smolensk to wilno
		wilno.addConnection(smolensk, railRoadList);	//smolensk to wilno
		
		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(2, "orange", 0, false, false,1269,317, Math.toRadians(5)));
		railRoadList.add(new RailRoad(2, "orange", 0, false, false,1364,301, Math.toRadians(141)));
		smolensk.addConnection(moskva, railRoadList);	//smolensk to moskva
		moskva.addConnection(smolensk, railRoadList);	//smolensk to moskva
		
		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(4, "white", 0, false, false,1372,230, Math.toRadians(79)));
		railRoadList.add(new RailRoad(4, "white", 0, false, false,1349,182, Math.toRadians(65)));
		railRoadList.add(new RailRoad(4, "white", 0, false, false,1308,146, Math.toRadians(43)));
		railRoadList.add(new RailRoad(4, "white", 0, false, false,1256,125, Math.toRadians(31)));
		moskva.addConnection(petrograd, railRoadList);	//moskva to petrograd
		petrograd.addConnection(moskva, railRoadList);	//moskva to petrograd
		
		railRoadList = new ArrayList<RailRoad>();
		railRoadList.add(new RailRoad(4, "any", 0, false, false,1385,300, Math.toRadians(70)));
		railRoadList.add(new RailRoad(4, "any", 0, false, false,1408,349, Math.toRadians(77)));
		railRoadList.add(new RailRoad(4, "any", 0, false, false,1414,405, Math.toRadians(107)));
		railRoadList.add(new RailRoad(4, "any", 0, false, false,1396,458, Math.toRadians(137)));
		moskva.addConnection(kharkov, railRoadList);	//moskva to kharkov
		kharkov.addConnection(moskva, railRoadList);	//moskva to kharkov
		
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
    
    public ArrayList<RailRoad> getCitySecondConnection(City city1, City city2) {
    	// Get the railroad connection of city to city stuff
    	if (!city1.getSecondConnections().containsKey(city2)) {
    		return null;
    	}
    	return city1.getSecondConnections().get(city2);
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
    public int getPlayerTrainPoint(Player p) { // Return player point (Through railroad)
    	int total = 0;
    	for (int i = 0; i < adjacencyList.size(); i++) {
    		// Get Current city
    		City currentCity = adjacencyList.get(i);
    		// Get the railroad
    		HashMap<City, ArrayList<RailRoad>> railRoadList = currentCity.getConnections();
    		HashMap<City, ArrayList<RailRoad>> railRoadListSecondConnection = currentCity.getSecondConnections();
    		// Get Connection to other railroads
    		Set<City> cityList = railRoadList.keySet();
    		// Iterate through other railroads
    		for (City currentRailRoad: cityList) {
    			ArrayList<RailRoad> theRailRoad = railRoadList.get(currentRailRoad);
    			if (theRailRoad.get(0).getPlrBought() == p) {
    				if (theRailRoad.size() <= 2) {
    					total += theRailRoad.size();
    				} else if (theRailRoad.size() == 3) {
    					total += 4;
    				} else if (theRailRoad.size() == 4) {
    					total += 7;
    				} else if (theRailRoad.size() == 6) {
    					total += 15;
    				} else {
    					total += 21;
    				}
    			}
    		}
    		
    		cityList = railRoadListSecondConnection.keySet();
    		
    		for (City currentRailRoad: cityList) {
    			ArrayList<RailRoad> theRailRoad = railRoadListSecondConnection.get(currentRailRoad);
    			if (theRailRoad.get(0).getPlrBought() == p) {
    				if (theRailRoad.size() <= 2) {
    					total += theRailRoad.size();
    				} else if (theRailRoad.size() == 3) {
    					total += 4;
    				} else if (theRailRoad.size() == 4) {
    					total += 7;
    				} else if (theRailRoad.size() == 6) {
    					total += 15;
    				} else {
    					total += 21;
    				}
    			}
    		}
    	}
    	return total;
    }
    
    // Support function for longest route
    private int countRailRoads(ArrayList<City> pastCities, int size) {
    	return -1;
    }
    // Longest Route (Unfinished)
    public Player getLongestPlrRoute() {
    	Player currentLongestPlr = null;
    	Integer currentLongestSize = null;
    	
    	for (int i = 0; i < adjacencyList.size(); i++) {
    		HashMap<City, ArrayList<RailRoad>> railRoadList = adjacencyList.get(i).getConnections();
    		Set<City> cities = railRoadList.keySet();
    		for (City currentCity: cities) {
    			ArrayList<City> newArr = new ArrayList<City>();
    			newArr.add(currentCity);
    			int currentRailRoadLength = countRailRoads(newArr, 0);
    			if (currentLongestSize == null || currentLongestSize < currentRailRoadLength) {
    				currentRailRoadLength = currentLongestSize;
    			}
    		}
    	}
    	
    	return null;
    }
}
