package game;

import java.io.FileNotFoundException;
import java.util.*;

public class AnimateEntityFactory {
	
	public static AnimateEntity create(String name) 
	{
		if(name.equals("goblin"))
		{
			return new Monster("goblin", 3, 1, createInventory(), true);
		}
		if(name.equals("troll"))
		{
			return new Monster("troll", 5, 2, createInventory(), true);
		}
		if(name.equals("trader"))
		{
			return new Trader("trader", 100000, 0, createInventory(), false);
		}
		if(name.equals("giant rat"))
		{
			return new Monster("giant rat", 2, 1, createInventory(), true);
		}
		if(name.equals("giant spider"))
		{
			return new Monster("giant spider", 3, 1, createInventory(), true);
		}
		return null;
	}
	
	private static ArrayList<Item> createInventory()
	{
		ArrayList<Item> mInventory = new ArrayList<Item>();
		String[] itemTypesDropped = new String[6];
		itemTypesDropped[0] = "sword";
		itemTypesDropped[1] = "shield";
		itemTypesDropped[2] = "leather boots";
		itemTypesDropped[3] = "leather gloves";
		itemTypesDropped[4] = "leather boots";
		itemTypesDropped[5] = "leather helmet";
		int picker = (int)(Math.random()*6);
		mInventory.add(ItemFactory.create(itemTypesDropped[picker]));
		return mInventory;
	}

}
