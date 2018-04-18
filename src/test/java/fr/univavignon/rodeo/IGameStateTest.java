package fr.univavignon.rodeo;

import fr.univavignon.rodeo.api.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.runner.RunWith;
import org.mockito.runners.*;
import org.junit.Test;

@RunWith(MockitoJUnitRunner.class)
public class IGameStateTest {
	
	//Liste qui contiendra des mocks de IEnvironment
	public static ISpecie iSpe = ISpecieTest.getTestInstance();
	public static IAnimal iAni = IAnimalTest.getTestInstance();

	/*Méthode créant un mock de l'interface IGameState
	 * Ce mock est utilisé dans les méthodes de test
	 */
	protected static IGameState getTestInstance(){
		IGameState mockedIGameState = mock(IGameState.class);
		doThrow(IllegalStateException.class).when(mockedIGameState).exploreArea();
		doThrow(IllegalArgumentException.class).when(mockedIGameState).catchAnimal(isA(IAnimal.class));
		doThrow(IllegalStateException.class).when(mockedIGameState).catchAnimal(iAni);
		doThrow(IllegalArgumentException.class).when(mockedIGameState).getSpecieLevel(isA(ISpecie.class));
		when(mockedIGameState.getProgression()).thenReturn(50);
		when(mockedIGameState.getSpecieLevel(isA(ISpecie.class))).thenReturn(SpecieLevel.NOVICE);
		return mockedIGameState;
	}
	
	@Test(expected=IllegalStateException.class)
	public void testExploreArea(){
		final IGameState gameS = getTestInstance();
		gameS.exploreArea();
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testCatchAnimalNull(){
		final IGameState gameS = getTestInstance();
		gameS.catchAnimal(null);
	}
	
	@Test (expected = IllegalStateException.class)
	public void testCatchAnimalNotFound(){
		final IGameState gameS = getTestInstance();
		gameS.catchAnimal(iAni);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testGetSpecieLevelNull(){
		final IGameState gameS = getTestInstance();
		gameS.getSpecieLevel(null);
	}
	
	@Test
	public void testGetSpecieLevel(){
		final IGameState gameS = getTestInstance();
		assertEquals(gameS.getSpecieLevel(iSpe),SpecieLevel.NOVICE);
	}
	
	@Test
	public void testGetProgression(){
		final IGameState gameS = getTestInstance();
		assertEquals(gameS.getProgression(), 50);
	}
	
}
