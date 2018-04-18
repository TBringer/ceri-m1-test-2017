package fr.univavigno.rodeo.imp;

import java.util.List;

import fr.univavignon.rodeo.api.*;

public class EnvironmentProvider implements IEnvironmentProvider {

	private List<String> availableEnvironments;
	
	public EnvironmentProvider() 
	{
		
	}
	@Override
	public List<String> getAvailableEnvironments() {
		return null;
	}

	@Override
	public IEnvironment getEnvironment(String name)
			throws IllegalArgumentException {
		return null;
	}

}
