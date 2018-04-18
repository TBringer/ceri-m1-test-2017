package fr.univavignon.rodeo;

import java.util.Arrays;
import java.util.List;

import fr.univavignon.rodeo.api.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.runner.RunWith;
import org.mockito.runners.*;
import org.junit.Test;

@RunWith(MockitoJUnitRunner.class)
public class IEnvironmentProviderTest {
	
	//Liste qui contiendra des mocks de IEnvironment
	public static List<String> listEnvironments = Arrays.asList("Jungle", "Savane", "Banquise");
	public static IEnvironment iEnv = IEnvironmentTest.getTestInstance();

	/*Méthode créant un mock de l'interface IEnvironmentProvider
	 * Ce mock est utilisé dans les méthodes de test
	 */
	protected static IEnvironmentProvider getTestInstance(){
		IEnvironmentProvider mockedIEnvironmentProvider = mock(IEnvironmentProvider.class);
		when(mockedIEnvironmentProvider.getEnvironment("Jungle")).thenReturn(iEnv);
		when(mockedIEnvironmentProvider.getAvailableEnvironments()).thenReturn(listEnvironments);
		return mockedIEnvironmentProvider;
	}
	
	@Test 
	public void testGetEnvironment(){
		final IEnvironmentProvider env = getTestInstance();
		assertEquals(env.getEnvironment("Jungle"), iEnv );
	}
	
	@Test 
	public void testGetAvailableEnvironments(){
		final IEnvironmentProvider env = getTestInstance();
		assertEquals(env.getAvailableEnvironments(), listEnvironments );

	}
	
}
