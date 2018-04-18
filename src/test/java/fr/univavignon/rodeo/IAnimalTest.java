package fr.univavignon.rodeo;

import fr.univavignon.rodeo.api.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import org.junit.runner.RunWith;
import org.mockito.runners.*;
import org.junit.Test;

@RunWith(MockitoJUnitRunner.class)
public class IAnimalTest {
	
	/*Méthode créant un mock de l'interface IAnimal
	 * Ce mock est utilisé dans les méthodes de test
	 */
	protected static IAnimal getTestInstance(){
		IAnimal mockedIAnimal = mock(IAnimal.class);
		when(mockedIAnimal.getXP()).thenReturn(50);
		when(mockedIAnimal.isSecret()).thenReturn(true);
		when(mockedIAnimal.isEndangered()).thenReturn(true);
		when(mockedIAnimal.isBoss()).thenReturn(true);
		return mockedIAnimal;
	}
	
	@Test 
	public void testGetXP(){
		final IAnimal animal = getTestInstance();
		assertEquals(animal.getXP(), 50);
	}
	
	@Test
	public void testIsSecret(){
		final IAnimal animal = getTestInstance();
		assertEquals(animal.isSecret(), true);

	}
	
	@Test
	public void testIsEndangered(){
		final IAnimal animal = getTestInstance();
		assertEquals(animal.isEndangered(), true);

	}
	
	@Test
	public void testIsBoss(){
		final IAnimal animal = getTestInstance();
		assertEquals(animal.isBoss(), true);

	}
}
