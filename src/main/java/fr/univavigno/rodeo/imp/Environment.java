package fr.univavigno.rodeo.imp;

import java.util.List;

import fr.univavignon.rodeo.api.*;

public class Environment implements IEnvironment {

	private String name;
	private int numAreas;
	private List<ISpecie> listSpecies;
	
	public Environment() {}
	
	/**
	 * 
	 * @param name
	 * @param areas
	 * @param species
	 */
	public Environment(String name, int areas, List<ISpecie> species)
	{
		this.name = name;
		this.numAreas = areas;
		this.listSpecies = species;
	}
	@Override
	public String getName() {
		return this.name;
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
