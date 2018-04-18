package fr.univavignon.rodeo;

import java.util.List;
import java.util.stream.*;

import fr.univavignon.rodeo.api.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.runner.RunWith;
import org.mockito.runners.*;
import org.junit.Test;

@RunWith(MockitoJUnitRunner.class)
public class ISpecieTest {
	
	//Liste qui contiendra des mocks de IAnimal
	public static List<IAnimal> listAnimals = IntStream
			.range(0,2)
			.mapToObj(i -> IAnimalTest.getTestInstance())
			.collect(Collectors.toList());

	/*Méthode créant un mock de l'interface ISpecie
	 * Ce mock est utilisé dans les méthodes de test
	 */
	protected static ISpecie getTestInstance(){
		ISpecie mockedISpecie = mock(ISpecie.class);
		when(mockedISpecie.getArea()).thenReturn(2);
		listAnimals.add(IAnimalTest.getTestInstance());
		listAnimals.add(IAnimalTest.getTestInstance());
		listAnimals.add(IAnimalTest.getTestInstance());
		listAnimals.add(IAnimalTest.getTestInstance());
		listAnimals.add(IAnimalTest.getTestInstance());
		when(mockedISpecie.getAnimals()).thenReturn(listAnimals);
		return mockedISpecie;
	}
	
	@Test
	public void testGetArea(){
		final ISpecie specie = getTestInstance();
		assertEquals(specie.getArea(), 2);
	}
	
	@Test
	public void testGetAnimals(){
		final ISpecie specie = getTestInstance();
		assertEquals(specie.getAnimals(), listAnimals );

	}
	
}
