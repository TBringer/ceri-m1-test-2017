package fr.univavignon.rodeo.imp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import fr.univavignon.rodeo.api.IAnimal;
import fr.univavignon.rodeo.api.IEnvironment;
import fr.univavignon.rodeo.api.IEnvironmentProvider;
import fr.univavignon.rodeo.api.ISpecie;

public class EnvironmentProvider implements IEnvironmentProvider {

	private List<IEnvironment> availableEnvironments;
	private Document docAreas, docSpecies, docAnimals;
	private List<IEnvironment> environments;
	
	public EnvironmentProvider() 
	{
		try {
			
			//web crawler, retrieves data from wiki
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
                			for(Element tableAnimal : docAnimals.select("#mw-content-text > table:nth-child(4)")){
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
    	                this.environments.add(environment);
                	}
	            }
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	
	
	@SuppressWarnings("null")
	@Override
	public List<String> getAvailableEnvironments() {
		List<String> availEnvName = null;
		
		for(IEnvironment envi : availableEnvironments)
			availEnvName.add(envi.getName());
		
		return availEnvName;
	}

	
	public List<IEnvironment> getEnvironments() {
		return this.environments;
	}
	
	@Override
	public IEnvironment getEnvironment(String name)throws IllegalArgumentException {
		
		if(name == null)
			throw new IllegalArgumentException("null argument in getEnvironment()");
		
		for(IEnvironment envi : availableEnvironments)
			if(envi.getName().equals(name))
				return envi;
		
		return null;
	}

}
