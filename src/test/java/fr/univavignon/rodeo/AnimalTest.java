package fr.univavignon.rodeo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Test;

import fr.univavignon.rodeo.imp.Animal;

public class AnimalTest extends IAnimalTest {
	
	private Animal animal;
	
	@Test
	public void testName() {
		animal = new Animal("Buffalo Grill", 1, false, false, false);
		assertEquals("Buffalo Grill", animal.getName());
	}
	
	@Test 
	public void testGetXP(){
		animal = new Animal("Buffalo Grill", 1, false, false, false);
		assertEquals(1, animal.getXP());
	}
	
	@Test
	public void testIsSecret(){
		animal = new Animal("Buffalo Grill", 1, false, false, false);
		assertFalse(animal.isSecret());
	}
	
	@Test
	public void testIsEndangered(){
		animal = new Animal("Buffalo Grill", 1, false, false, false);
		assertFalse(animal.isEndangered());
	}
	
	@Test
	public void testIsBoss(){
		animal = new Animal("Buffalo Grill", 1, false, false, false);
		assertFalse(animal.isBoss());
	}
}
