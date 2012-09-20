package game;

import java.util.*;

public class Item extends InanimateEntity{

	private int attack;
	private int health;
	
	public Item(String name, int weight, int attack, int health)
	{
		this.name = name;
		weight = size;
		this.attack = attack;
		this.health = health;
	}
	
	public int getAttack()
	{
		return attack;
	}
	
	public int getHealth()
	{
		return health;
	}
	
	public String getName()
	{
		return name;
	}

	public int getWeight() {
		return size;
	}
	
	@Override
	public String toString()
	{
		return name;
	}
	

}
