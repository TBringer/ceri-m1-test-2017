package fr.univavigno.rodeo.imp;

import fr.univavignon.rodeo.api.*;

public class Animal implements IAnimal {
	
	private String name;
	private int xp;
	private boolean secretState;
	private boolean endangeredState;
	private boolean bossState;
	
	public Animal() {}
	
	/**
	 * 
	 * @param name
	 * @param xp
	 * @param secret
	 * @param endangered
	 * @param boss
	 */
	public Animal(String name, int xp, boolean secret,boolean endangered, boolean boss)
	{
		this.name = name;
		this.xp = xp;
		this.secretState = secret;
		this.endangeredState = endangered;
		this.bossState = boss;
	}
	
	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public int getXP() {
		return this.xp;
	}

	@Override
	public boolean isSecret() {
		// TODO Auto-generated method stub
		return this.secretState;
	}

	@Override
	public boolean isEndangered() {
		// TODO Auto-generated method stub
		return this.endangeredState;
	}

	@Override
	public boolean isBoss() {
		// TODO Auto-generated method stub
		return this.bossState;
	}

}
