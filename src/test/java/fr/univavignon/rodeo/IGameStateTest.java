package fr.univavignon.rodeo;

import fr.univavignon.rodeo.api.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.runner.RunWith;
import org.mockito.runners.*;
import org.junit.Test;

@RunWith(MockitoJUnitRunner.class)
public class IGameStateTest {
	
	@Mock
	private static IAnimal animal;
	
	@Mock
	private static ISpecie specie;

	@Mock
	private IGameState gameState;
	
	private static SpecieLevel noviceLevel, wranglerLevel, championLevel, masterLevel;
	
	public static IGameState getMock() {
		IGameState gameState = mock(IGameState.class);
		when(gameState.getSpecieLevel(specie)).thenReturn(masterLevel);
		when(gameState.getProgression()).thenReturn(0);
		doThrow(IllegalStateException.class).when(gameState).exploreArea();
		doThrow(IllegalArgumentException.class).when(gameState).catchAnimal(null);
		doThrow(IllegalStateException.class).when(gameState).catchAnimal(animal);
		doThrow(IllegalArgumentException.class).when(gameState).getSpecieLevel(null);
		return gameState;
	}

	@Before
	public void init() {
		noviceLevel = SpecieLevel.NOVICE;
		wranglerLevel = SpecieLevel.WRANGLER;
		championLevel = SpecieLevel.CHAMPION;
		masterLevel = SpecieLevel.MASTER;
		
		animal = IAnimalTest.getMock();
		specie = ISpecieTest.getMock();
		gameState = getMock();		
	}
	
	@Test (expected=IllegalStateException.class)
	public void testExploreArea() {
		gameState.exploreArea();
	}
	
	@Test (expected=IllegalStateException.class)
	public void testCatchAnimalNotExist() {
		gameState.catchAnimal(animal);
	}
	
	@Test (expected=IllegalArgumentException.class)
	public void testCatchAnimalIsNull() {
		gameState.catchAnimal(null);
	}
	
	@Test
	public void testGetSpecieLevel() {
		assertEquals(masterLevel, gameState.getSpecieLevel(specie));
		assertEquals(600, gameState.getSpecieLevel(specie).getRequiredXP());
	}
	
	@Test (expected=IllegalArgumentException.class)
	public void testGetSpecieLevelIsNull() {
		gameState.getSpecieLevel(null);
	}
	
	@Test
	public void testGetSpecieNoviceLevel() {
		when(gameState.getSpecieLevel(specie)).thenReturn(noviceLevel);
		assertEquals(noviceLevel, gameState.getSpecieLevel(specie));
	}
	
	@Test
	public void testGetSpecieWranglerLevel() {
		when(gameState.getSpecieLevel(specie)).thenReturn(wranglerLevel);
		assertEquals(wranglerLevel, gameState.getSpecieLevel(specie));
	}
	
	@Test
	public void testGetSpecieChampionLevel() {
		when(gameState.getSpecieLevel(specie)).thenReturn(championLevel);
		assertEquals(championLevel, gameState.getSpecieLevel(specie));
	}
	
	@Test
	public void testGetSpecieMasterLevel() {
		when(gameState.getSpecieLevel(specie)).thenReturn(masterLevel);
		assertEquals(masterLevel, gameState.getSpecieLevel(specie));
	}
	
	@Test
	public void testGetProgression() {
		assertEquals(0, gameState.getProgression());
	}
	
}
