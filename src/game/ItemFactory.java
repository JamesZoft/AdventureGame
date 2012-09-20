package game;

public class ItemFactory {
	
	
	
	public ItemFactory()
	{
		
	}
	
	public static Item create(String item)
	{
		if(item.equals("sword"))
		{
			return new Item("sword", 3, 3, 0);
		}
		if(item.equals("shield"))
		{
			return new Item("shield", 5, 0, 2);
		}
		if(item.equals("key"))
		{
			return new Item("key", 1, 0, 0);
		}
		if(item.equals("leather boots"))
		{
			return new Item("leather boots", 2, 0, 1);
		}
		if(item.equals("leather helmet"))
		{
			return new Item("leather helmet", 2, 0, 2);
		}
		if(item.equals("leather gloves"))
		{
			return new Item("leather gloves", 2, 0, 1);
		}
		if(item.equals("leather bracers"))
		{
			return new Item("leather bracers", 1, 0, 1);
		}
		if(item.equals("magic staff"))
		{
			return new Item("magic staff", 3, 2, 1);
		}
		if(item.equals("magic ring"))
		{
			return new Item("magic ring", 1, 1, 1);
		}
		if(item.equals("magic amulet"))
		{
			return new Item("magic amulet", 1, 1, 1);
		}
		
		return null;
	}


}
