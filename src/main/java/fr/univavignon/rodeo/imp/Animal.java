package fr.univavignon.rodeo.imp;

import fr.univavignon.rodeo.api.*;

public class Animal extends NamedObject implements IAnimal {
	
	private int xp;
	private boolean secretState;
	private boolean endangeredState;
	private boolean bossState;
		
	/**
	 * 
	 * @param name
	 * @param xp
	 * @param secret
	 * @param endangered
	 * @param boss
	 */
	public Animal(String name, int exp, boolean secret,boolean endangered, boolean boss)
	{
		super(name);
		xp = exp;
		secretState = secret;
		endangeredState = endangered;
		bossState = boss;
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
