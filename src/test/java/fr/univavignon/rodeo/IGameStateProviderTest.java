package fr.univavignon.rodeo;

import fr.univavignon.rodeo.api.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.runner.RunWith;
import org.mockito.runners.*;
import org.junit.Test;

@RunWith(MockitoJUnitRunner.class)
public class IGameStateProviderTest {
	
	//objets utilisés pour tester les fonctions
	public static IGameState iGameS = IGameStateTest.getTestInstance();
	public static IGameState newState = IGameStateTest.getTestInstance();


	/*Méthode créant un mock de l'interface IGameStateProvider
	 * Ce mock est utilisé dans les méthodes de test
	 */
	protected static IGameStateProvider getTestInstance(){
		IGameStateProvider mockedIGameStateProvider = mock(IGameStateProvider.class);
		doThrow(IllegalArgumentException.class).when(mockedIGameStateProvider).get(null);
		when(mockedIGameStateProvider.get("iGameS")).thenReturn(iGameS);
		return mockedIGameStateProvider;
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testGetNull(){
		final IGameStateProvider gameSP = getTestInstance();
		gameSP.get(null);
	}
	
	@Test
	public void testGet(){
		final IGameStateProvider gameSP = getTestInstance();
		assertEquals(gameSP.get("iGameS"), iGameS);
	}
	
}
