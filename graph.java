import java.util.ArrayList;
// Store all cities and railroads
public class graph {
	private ArrayList<City> adjacencyList;
	
	public graph() {
		adjacencyList = new ArrayList<City>();
		City frankfurt = new City("frankfurt", new Station(), 568, 433);
		City brest = new City("brest", new Station(), 341, 313);
		City london = new City("london", new Station(), 341, 313);
		City essen = new City("essen", new Station(), 584, 332);
		City berlin = new City("berlin", new Station(), 722, 350);
		City wien = new City("wien", new Station(), 801, 510);
		City zurich = new City("zurich", new Station(), 552, 573);
		City marseille = new City("marseille", new Station(), 513, 703);
		City roma = new City("roma", new Station(), 673, 747);
		City brindisi = new City("brindisi", new Station(), 790, 781);
		City sofia = new City("sofia", new Station(), 987, 729);
		City kobenhavn = new City("kobenhavn", new Station(), 678, 190);
		City amsterdam = new City("amsterdam", new Station(), 472, 319);
		City dieppe = new City("dieppe", new Station(), 322, 438);
		City paris = new City("paris", new Station(), 390, 499);
		City bruxelles = new City("bruxelles", new Station(), 439, 382);
		City munchen = new City("munchen", new Station(), 641, 485);
		City athina = new City("athina", new Station(), 967, 885);
		City madrid = new City("madrid", new Station(), 169, 816);
		City cadiz = new City("cadiz", new Station(), 167, 923);
		City barcelona = new City("barcelona", new Station(), 321, 842);
		City pamplona = new City("pamplona", new Station(), 304, 712);
		City constantinople = new City("constantinople", new Station(), 1143, 815);
		City budapest = new City("budapest", new Station(), 860, 550);
		City bucuresti = new City("bucuresti", new Station(), 1073, 644);
		City sevastopol = new City("sevastopol", new Station(), 1264, 662);
		City kharkov = new City("kharkov", new Station(), 1336, 486);
		City kyiv = new City("kyiv", new Station(), 1153, 416);
		City moskva = new City("moskva", new Station(), 1360, 278);
		City petrograd = new City("petrograd", new Station(), 1227, 112);
		City rica = new City("rica", new Station(), 994, 125);
		City danzic = new City("danzic", new Station(), 875, 246);
		City edinburgh = new City("edinburgh", new Station(), 248, 111);
		City lisboa = new City("lisboa", new Station(), 73, 856);
		City zagrab = new City("zagrab", new Station(), 784, 638);
		City sarajevo = new City("sarajevo", new Station(), 897, 717);
		City palermo = new City("palermo", new Station(), 730, 927);
		City smryna = new City("smryna", new Station(), 1084, 921);
		City angora = new City("angora", new Station(), 1249, 888);
		City erzurum = new City("erzurum", new Station(), 1357, 855);
		City sochi = new City("sochi", new Station(), 1385, 683);
		City rostov = new City("rostov", new Station(), 1393, 562);
		City stockholm = new City("stockholm", new Station(), 822, 81);
		City wilno = new City("wilno", new Station(), 1098, 308);
		
	}
}
