package fr.univavigno.rodeo.imp;

import java.util.List;

import fr.univavignon.rodeo.api.*;

public class Specie implements ISpecie {
	 
	private String name;
	private int area;
	private List<IAnimal> listAnimals;
	
	/** **/
	public Specie() {}
	
	/**
	 * 
	 * @param name
	 * @param area
	 * @param list
	 */
	public Specie(String name, int area, List<IAnimal> list)
	{
		this.name= name;
		this.area = area;
		this.listAnimals = list;
	}
	
	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public int getArea() {
		return this.area;
	}

	@Override
	public List<IAnimal> getAnimals() {
		return this.listAnimals;
	}

}
