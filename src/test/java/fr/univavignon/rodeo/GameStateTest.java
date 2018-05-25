package fr.univavignon.rodeo;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.Test;

import fr.univavignon.rodeo.api.IAnimal;
import fr.univavignon.rodeo.api.IGameState;
import fr.univavignon.rodeo.api.ISpecie;
import fr.univavignon.rodeo.api.SpecieLevel;
import fr.univavignon.rodeo.imp.Animal;
import fr.univavignon.rodeo.imp.GameState;
import fr.univavignon.rodeo.imp.Specie;

public class GameStateTest extends IGameStateTest {

	public static List<IAnimal> listAnimals = IntStream
			.range(0,2)
			.mapToObj(i -> new Animal("Diabuffalo", 3, true, false, false))
			.collect(Collectors.toList());
	public static ISpecie specie = new Specie("Buffalo", 1, listAnimals);
	
	@Test(expected=NullPointerException.class)
	public void testExploreArea(){
		final IGameState gameS = new GameState("partie");
		gameS.exploreArea();
	}
	
	@Test (expected = NullPointerException.class)
	public void testCatchAnimalNull(){
		final IGameState gameS = new GameState("partie");
		gameS.catchAnimal(null);
	}
	
	@Test (expected = NullPointerException.class)
	public void testCatchAnimalNotFound(){
		final IGameState gameS = new GameState("partie");
		gameS.catchAnimal(new Animal("pouloulou",1,true, false, false));
	}
	
	@Test (expected = NullPointerException.class)
	public void testGetSpecieLevelNull(){
		final IGameState gameS = new GameState("partie");
		gameS.getSpecieLevel(null);
	}
	
	@Test (expected = NullPointerException.class)
	public void testGetSpecieLevel(){
		final IGameState gameS = new GameState("partie");
		assertEquals(gameS.getSpecieLevel(specie),SpecieLevel.NOVICE);
	}
	
	@Test (expected = NullPointerException.class)
	public void testGetProgression(){
		final IGameState gameS = new GameState("partie");
		assertEquals(gameS.getProgression(), 0);
	}
}
