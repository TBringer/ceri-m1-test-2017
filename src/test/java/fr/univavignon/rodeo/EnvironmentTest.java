package fr.univavignon.rodeo;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.Test;

import fr.univavignon.rodeo.api.IAnimal;
import fr.univavignon.rodeo.api.IEnvironment;
import fr.univavignon.rodeo.api.ISpecie;
import fr.univavignon.rodeo.imp.Animal;
import fr.univavignon.rodeo.imp.Environment;
import fr.univavignon.rodeo.imp.Specie;

public class EnvironmentTest extends IEnvironmentTest {
	
	public static List<IAnimal> listAnimals = IntStream
			.range(0,2)
			.mapToObj(i -> new Animal("Diabuffalo", 3, true, false, false))
			.collect(Collectors.toList());
	
	public static List<ISpecie> listSpecies = IntStream
			.range(0,2)
			.mapToObj(i -> new Specie("Buffalo", 1, listAnimals))
			.collect(Collectors.toList());
	
	@Test 
	public void testGetAreas(){
		final IEnvironment env = new Environment("Savannah", 1, listSpecies);
		assertEquals(1, env.getAreas());
	}
	
	@Test
	public void testGetAnimals(){
		final IEnvironment env = new Environment("Savannah", 1, listSpecies);
		assertEquals(listSpecies, env.getSpecies());
	}
}
