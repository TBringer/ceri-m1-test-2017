package fr.univavigno.rodeo.imp;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import fr.univavignon.rodeo.api.*;

public class EnvironmentProvider implements IEnvironmentProvider {

	private List<String> availableEnvironments;
	private Document docAreas, docSpecies, docAnimals;
	
	public EnvironmentProvider() 
	{
		
	}
	@Override
	public List<String> getAvailableEnvironments() {
		return null;
	}

	@Override
	public IEnvironment getEnvironment(String name)
			throws IllegalArgumentException {
		
	
	    try {
	
	        // need http protocol
	        docAreas = Jsoup.connect("http://rodeo-stampede.wikia.com/wiki/Zones_and_Animals").get();
	        
	        for (Element table : docAreas.select("table.tablehead")) {
	            Element row = table.selectFirst("tr:nth-child(1)");
                Elements tds = row.select("td");
                int numArea = 0;
                List<ISpecie> listSpecies = new ArrayList<ISpecie>();
                for(Element td : tds){
                	docSpecies = Jsoup.connect("http://rodeo-stampede.wikia.com/wiki/"+td.text()).get();
                	for(Element tableSpecie : docSpecies.select("#mw-content-text > table:nth-child(10)")){
                		Elements rows = tableSpecie.select("tr");
                		for(int i=1; i>=rows.size(); i++){
                			Element rowSpecie = rows.get(i);
                			Element tdSpecie = rowSpecie.selectFirst("td");
                			Element tdArea = rowSpecie.selectFirst("#td:nth-child(2)");
                			int length = tdArea.text().length();
                			int idArea = Integer.parseInt(tdArea.text().substring(length -1, length));
                			List<IAnimal> listAnimals = new ArrayList<IAnimal>();
                			docAnimals = Jsoup.connect("http://rodeo-stampede.wikia.com/wiki/Species:_"+tdSpecie.text()).get();
                			for(Element tableAnimal : docSpecies.select("#mw-content-text > table:nth-child(4)")){
    	                		Elements rowsAnimals = tableAnimal.select("tr");
    	                		for(int j=1; j>=rowsAnimals.size(); j++){
    	                			Element rowAnimal = rowsAnimals.get(i);
    	                			Elements tdsAnimal = rowAnimal.select("td");
    	                			String nameAnimal = tdsAnimal.get(0).text();
    	                			int xpAnimal = Integer.parseInt(tdsAnimal.get(3).text());
    	                			boolean secret = false;
    	                			boolean endangered = false;
    	                			boolean boss = false;
    	                			if(tdsAnimal.get(1).text().equals("Secret"))
    	                				secret = true;
    	                			else if(tdsAnimal.get(1).text().equals("Endangered"))
    	                				endangered = true;
    	                			else if(tdsAnimal.get(1).text().equals("Boss"))
    	                				boss = true;
    	                			IAnimal animal = new Animal(nameAnimal, xpAnimal, secret, endangered, boss);
    	                			listAnimals.add(animal);
    	                		}
                			}
                			ISpecie specie = new Specie(tdSpecie.text(), idArea, listAnimals);
	    	                listSpecies.add(specie);
                		}
    	                Elements trNumArea = docSpecies.select("#mw-content-text > table:nth-child(15) > tbody > tr:nth-child(2)");
    	                numArea = trNumArea.size();
    	                IEnvironment environment = new Environment(td.text(), numArea, listSpecies);
                	}
	            }
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
		return null;
	}

}
