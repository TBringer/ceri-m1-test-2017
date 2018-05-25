package fr.univavignon.rodeo;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.Test;

import fr.univavignon.rodeo.api.IAnimal;
import fr.univavignon.rodeo.api.IEnvironment;
import fr.univavignon.rodeo.api.IEnvironmentProvider;
import fr.univavignon.rodeo.api.ISpecie;
import fr.univavignon.rodeo.imp.Animal;
import fr.univavignon.rodeo.imp.Environment;
import fr.univavignon.rodeo.imp.EnvironmentProvider;
import fr.univavignon.rodeo.imp.Specie;

public class EnvironmentProviderTest extends IEnvironmentProviderTest {

	public static List<IAnimal> listAnimals = IntStream
			.range(0,2)
			.mapToObj(i -> new Animal("Diabuffalo", 3, true, false, false))
			.collect(Collectors.toList());
	
	public static List<ISpecie> listSpecies = IntStream
			.range(0,2)
			.mapToObj(i -> new Specie("Buffalo", 1, listAnimals))
			.collect(Collectors.toList());
	
	public static List<IEnvironment> listEnvironment = IntStream
			.range(0,2)
			.mapToObj(i -> new Environment("Savannah", 5, listSpecies))
			.collect(Collectors.toList());
	
	@Test (expected = NullPointerException.class)
	public void testGetEnvironment(){
		final IEnvironmentProvider envProv = new EnvironmentProvider();
		assertEquals(envProv.getEnvironment("Savannah"), listEnvironments);
	}
	
	@Test (expected = NullPointerException.class)
	public void testGetAvailableEnvironments(){
		final IEnvironmentProvider envProv = new EnvironmentProvider();
		assertEquals(envProv.getAvailableEnvironments(), listEnvironments );

	}
}
