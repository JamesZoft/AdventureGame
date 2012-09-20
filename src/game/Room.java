package game;

import java.util.*;

public class Room {
	
	private ArrayList<AnimateEntity> animateEntities;
	private ArrayList<InanimateEntity> inanimateEntities;
	private ArrayList<Room> connectingRooms;

	
	public Room()
	{
		connectingRooms = new ArrayList<Room>();
		animateEntities = new ArrayList<AnimateEntity>();
		inanimateEntities = new ArrayList<InanimateEntity>();
	}
	
	
	public ArrayList<Room> getConnectedRooms()
	{
		System.out.println(connectingRooms.isEmpty());
		return connectingRooms;
	}
	
	public void removeInanimateEntity(InanimateEntity e)
	{
		inanimateEntities.remove(e);
	}
	
	public void removeAnimateEntity(AnimateEntity e)
	{
		animateEntities.remove(e);
	}
	
	public ArrayList<AnimateEntity> getAnimateEntities()
	{
		return animateEntities;
	}
	
	public ArrayList<InanimateEntity> getInanimateEntities()
	{
		return inanimateEntities;
	}
	
	public void addAnimateEntity(AnimateEntity e)
	{
		animateEntities.add(e);
	}
	
	public void addInanimateEntity(InanimateEntity e)
	{
		inanimateEntities.add(e);
	}
	
	@Override
	public String toString()
	{
		String toString = "This room contains the following items:" + "\n";
		for(InanimateEntity e : inanimateEntities)
		{
			if(e != null)
			{
				toString += e.getName() + ", ";
			}
		}
		toString += "\nand the following beings" + "\n";
		for(AnimateEntity e : animateEntities)
		{
			if(e != null)
			{
				toString += e.getName() + ", ";
			}
		}
		toString += "\n" + "There is also a door at one end of the room.";
		return toString;
	}
}
