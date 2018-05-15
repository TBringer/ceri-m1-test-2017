package fr.univavignon.rodeo.imp;

import java.util.HashMap;
import java.util.Map;

import fr.univavignon.rodeo.api.IAnimal;
import fr.univavignon.rodeo.api.IEnvironment;
import fr.univavignon.rodeo.api.IGameState;
import fr.univavignon.rodeo.api.ISpecie;
import fr.univavignon.rodeo.api.SpecieLevel;

public class GameState extends NamedObject implements IGameState {

	private int progress;
	private EnvironmentProvider envProv;
	private Map<ISpecie, Integer> speciesXP;
	private Map<IAnimal, Boolean> caughtAnimals;
	private IEnvironment currentEnv;
	private int currentArea;
	private int countCaughtAnimalsInCurrentArea;
	private int countCaughtAnimals;
	private int MaxNumberAnimalsInCurrentArea;

	
	public GameState(String name)
	{
		super(name);
		progress = 0;
		envProv = new EnvironmentProvider();
		speciesXP = new HashMap<ISpecie, Integer>();
		caughtAnimals = new HashMap<IAnimal, Boolean>();
		currentEnv = envProv.getEnvironment("Savannah");
		currentArea = 0;
		countCaughtAnimalsInCurrentArea = 0;
		countCaughtAnimals = 0;
		
		for (IEnvironment env : envProv.getEnvironments())
			for(ISpecie spe : env.getSpecies()){
				speciesXP.put(spe, 0);
				for(IAnimal ani : spe.getAnimals())
					caughtAnimals.put(ani, false);
			}		
				
	}
	
	// so you need to catch every animals to unlock next area/environment
	@Override
	public void exploreArea() throws IllegalStateException {
		int index = envProv.getEnvironments().indexOf(currentEnv);
		if(countCaughtAnimalsInCurrentArea == MaxNumberAnimalsInCurrentArea ){
			if(currentArea < currentEnv.getAreas()){
				currentArea++;
				countCaughtAnimalsInCurrentArea = 0;
				MaxNumberAnimalsInCurrentArea = getMaxNumberAnimalsInCurrentArea();
			}
			else if( index < envProv.getEnvironments().size()-1 ){
				currentEnv = envProv.getEnvironments().get(index+1);
				currentArea = 0;
				countCaughtAnimalsInCurrentArea = 0;
				MaxNumberAnimalsInCurrentArea = getMaxNumberAnimalsInCurrentArea();
				envProv.getAvailableEnvironments().add(currentEnv.getName());
			}
			else {
				throw new IllegalStateException ("il n'y a plus d'environnements a explorer.");
			}
		}
		else {
			throw new IllegalStateException("tous les animaux n'ont pas ete attrapes.");
		}
			
				
	}

	@Override
	public void catchAnimal(IAnimal animal) throws IllegalArgumentException, IllegalStateException {
		
		if (animal == null) {
			throw new IllegalArgumentException("null argument in catchAnimal()");
		}
		
		
		IAnimal ani = null;
		//check if animal exists, if not throw exception
		for (Map.Entry<IAnimal, Boolean> ent : caughtAnimals.entrySet())
			if(ent.getKey().equals(animal)){
				ani = ent.getKey();
				break;
			}
		
		if (ani == null)
			throw new IllegalStateException ("animal not found in catchAnimal()");
		
		//update caughtAnimals map
		caughtAnimals.put(ani, true);
		countCaughtAnimalsInCurrentArea++;
		countCaughtAnimals++;
		
		//update animal's specie XP
		ISpecie spe = getSpecie(ani);
		int currentSpeXP = speciesXP.get(spe);
		speciesXP.put(spe, currentSpeXP + ani.getXP());
		
	}

	@Override
	public SpecieLevel getSpecieLevel(ISpecie specie) throws IllegalArgumentException {

		if (specie == null)
			throw new IllegalArgumentException ("null argument in getSpecieLevel()");
		
		int speXP = speciesXP.get(specie);
		
		SpecieLevel speLevel = SpecieLevel.NOVICE;
		
		if(speXP >= SpecieLevel.WRANGLER.getRequiredXP()){
			speLevel = SpecieLevel.WRANGLER;
			if(speXP >= SpecieLevel.CHAMPION.getRequiredXP()){
				speLevel = SpecieLevel.CHAMPION;
				if(speXP >= SpecieLevel.MASTER.getRequiredXP())
					speLevel = SpecieLevel.MASTER;
			}
		}
			
		return speLevel;
	}

	@Override
	public int getProgression() {
		makeProgress();
		return this.progress;
	}
	
	public ISpecie getSpecie(IAnimal animal){
		
		ISpecie spe = null;
		
		for(Map.Entry<ISpecie, Integer> ent : speciesXP.entrySet())
			for(IAnimal ani : ent.getKey().getAnimals())
				if(ani.equals(animal)){
					spe = ent.getKey();
					break;
				}
					
		return spe;
	}

	public int getMaxNumberAnimalsInCurrentArea(){
		
		int res = 0;
		for(ISpecie spe : currentEnv.getSpecies())
			if(spe.getArea() == currentArea+1)
				res += spe.getAnimals().size();
		
		return res;
	}
	
	public void makeProgress(){
		this.progress = (int) Math.round((countCaughtAnimals / caughtAnimals.size())*100);
	}
	
	//used when u want to use an old gamestate again from savings
	public void setProgress(int progress){
		this.progress = progress;
	}
}
