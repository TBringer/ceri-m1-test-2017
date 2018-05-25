package fr.univavignon.rodeo;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.Test;

import fr.univavignon.rodeo.api.IAnimal;
import fr.univavignon.rodeo.imp.Animal;
import fr.univavignon.rodeo.imp.Specie;

public class SpecieTest extends ISpecieTest {
	
	public static List<IAnimal> listAnimals = IntStream
			.range(0,2)
			.mapToObj(i -> new Animal("Diabuffalo", 3, true, false, false))
			.collect(Collectors.toList());

	@Test
	public void testGetArea(){
		final Specie specie = new Specie("Buffalo", 1, listAnimals);
		assertEquals(1, specie.getArea());
	}
	
	@Test
	public void testGetAnimals(){
		final Specie specie = new Specie("Buffalo", 1, listAnimals);
		assertEquals(listAnimals, specie.getAnimals());
	}
}
