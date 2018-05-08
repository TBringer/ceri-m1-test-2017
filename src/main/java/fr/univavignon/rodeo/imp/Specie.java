package fr.univavignon.rodeo.imp;

import java.util.List;

import fr.univavignon.rodeo.api.*;

public class Specie extends NamedObject implements ISpecie {
	 
	private int area;
	private List<IAnimal> listAnimals;
		
	/**
	 * 
	 * @param name
	 * @param area
	 * @param list
	 */
	public Specie(String name, int a, List<IAnimal> list)
	{
		super(name);
		area = a;
		listAnimals = list;
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
