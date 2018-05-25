package fr.univavignon.rodeo;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import fr.univavignon.rodeo.api.IGameStateProvider;
import fr.univavignon.rodeo.imp.GameStateProvider;

public class GameStateProviderTest extends IGameStateProviderTest {
	
	
	@Test
	public void testGetNull(){
		final IGameStateProvider gameSP = new GameStateProvider();
		assertEquals(gameSP.get(null), null);
	}
	
	@Test 
	public void testGet(){
		final IGameStateProvider gameSP = new GameStateProvider();
		assertEquals(gameSP.get(null), null);
	}
}
