package fr.univavignon.rodeo.imp;

import java.util.List;

import fr.univavignon.rodeo.api.*;

public class Environment extends NamedObject implements IEnvironment {

	private int numAreas;
	private List<ISpecie> listSpecies;
		
	/**
	 * 
	 * @param name
	 * @param areas
	 * @param species
	 */
	public Environment(String name, int areas, List<ISpecie> species)
	{
		super(name);
		numAreas = areas;
		listSpecies = species;
	}

	@Override
	public int getAreas() {
		return this.numAreas;
	}

	@Override
	public List<ISpecie> getSpecies() {
		return this.listSpecies;
	}

}
