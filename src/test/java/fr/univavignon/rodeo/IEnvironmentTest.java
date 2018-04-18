package fr.univavignon.rodeo;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import fr.univavignon.rodeo.api.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.runner.RunWith;
import org.mockito.runners.*;
import org.junit.Test;

@RunWith(MockitoJUnitRunner.class)
public class IEnvironmentTest {
	
	//Liste qui contiendra des mocks de ISpecie
	public static List<ISpecie> listSpecies = IntStream
			.range(0,2)
			.mapToObj(i -> ISpecieTest.getTestInstance())
			.collect(Collectors.toList());

	/*Méthode créant un mock de l'interface IEnvironment
	 * Ce mock est utilisé dans les méthodes de test
	 */
	protected static IEnvironment getTestInstance(){
		IEnvironment mockedIEnvironment = mock(IEnvironment.class);
		when(mockedIEnvironment.getAreas()).thenReturn(10);
		when(mockedIEnvironment.getSpecies()).thenReturn(listSpecies);
		return mockedIEnvironment;
	}
	
	@Test 
	public void testGetAreas(){
		final IEnvironment env = getTestInstance();
		assertEquals(env.getAreas(), 10);
	}
	
	@Test
	public void testGetAnimals(){
		final IEnvironment env = getTestInstance();
		assertEquals(env.getSpecies(), listSpecies );

	}
	
}
