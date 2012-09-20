package game;

import game.Player;

import java.util.*;

public class Game {

	public static Scanner scan;
	private Room currentRoom;
	private static Player player;
	private ArrayList<Room> rooms;

	public Game()
	{
		rooms = new ArrayList<Room>();
		scan = new Scanner(System.in);
		for(int i = 0; i < 10; i++)
		{
			rooms.add(RoomFactory.makeRandomRoom());
		}
		//setup the picker
		int roomConnectorPicker = (int)(Math.random()*(rooms.size() - 1));
		//set up room last connected
		Room roomLastConnected = rooms.get(roomConnectorPicker);
		//make sure the first room you connect to isn't the same as the first room
		while(roomLastConnected.equals(rooms.get(0)))
		{
			roomConnectorPicker = (int)(Math.random()*(rooms.size() - 1));
			roomLastConnected = rooms.get(roomConnectorPicker);
		}
		//link the first room
		RoomFactory.linkToRoom(roomLastConnected, rooms.get(0));
		for(int i = 0; i < rooms.size() - 1; i++)
		{
			roomConnectorPicker = (int)(Math.random()*(rooms.size() - 1));
			RoomFactory.linkToRoom(rooms.get(roomConnectorPicker), roomLastConnected);
			roomLastConnected = rooms.get(roomConnectorPicker);
		}
		player = new Player();
		player.setCurrentRoom(currentRoom);
		setCurrentRoom();
	}

	public Item trade(Trader t, String itemNameWanted)
	{
		if(!t.getShop().containsKey(itemNameWanted))
			System.out.println("The trader does not have this item to sell.");
		else
		{
			int itemCost = t.getShop().get(itemNameWanted);
			if(player.getGold() < itemCost)
				System.out.println("You do not have enough gold to purchase this item.");
			else
			{
				player.removeGold(itemCost);
				return ItemFactory.create(itemNameWanted);
			}
		}
		return null;
	}

	private void loot(Monster b)
	{
		for(Item x : b.inventory)
		{
			player.addToInventory(x);
			String output = "You looted a";
			if(startsWithVowel(x.getName()))
			{
				output += "n ";
			}
			output += x.getName() + "!";
		}
	}

	private boolean startsWithVowel(String word)
	{
		boolean x = false;
		if(word.startsWith("a", 0) || word.startsWith("e", 0) || word.startsWith("i", 0) || word.startsWith("o", 0) || word.startsWith("u", 0))
		{
			x = true;
		}
		return x;
	}

	private void engageCombat(Monster b)
	{
		while(!(b.getHealth() <= 0) && !(player.getHealth() <= 0))
		{
			player.attack(b);
			b.attack(player);
		}
		if(b.getHealth() <= 0 && player.getHealth() > 0)
		{
			System.out.println("You killed the " + b.getName() + "!");
			loot(b);
		}
		else
		{
			System.out.println("You have been defeated! Your bones will lie here as a testament to those who would " +
			"venture into the depths of these dungeons...");
			System.exit(0);
		}
	}

	public static Player getPlayer()
	{
		return player;
	}

	public void setCurrentRoom()
	{
		for(Room r : rooms)
		{
			if(r.getAnimateEntities().contains(player))
			{
				currentRoom = r;
			}
		}
	}

	public static void main(String[] args)
	{
		System.out.println("What is your name?");
		Game game = new Game();
		player.setName(game.scan.nextLine());
		Room startingRoom = game.rooms.get((int)(Math.random()*game.rooms.size()));
		player.setCurrentRoom(startingRoom);
		game.currentRoom = startingRoom;
		game.currentRoom.addAnimateEntity(player);
		System.out.println("Welcome to Adventure Game, " + player.getName() + "; a text-based RPG! In this game, you will fight epic battles," +
				"collect legendary items and solve mysterious puzzles." + "\n" +  "When it's finished. Maybe. " +
		"Experiment with commands, find out what's around you, and explore the game! GL HF!");
		System.out.println(game.currentRoom.toString());
		while(true)
		{
			String input = game.scan.nextLine().toLowerCase();
			if(input.equals("pickup"))
			{
				System.out.println("Pickup what?");
				String userInput = game.scan.nextLine();
				boolean isItem = false;
				for(InanimateEntity e : game.currentRoom.getInanimateEntities())
				{
					if(userInput.equals(e.getName()) && !(userInput.equals(player.getName())))
						isItem = true;
				}
				if(isItem == true)
					player.pickUpItem(userInput);
				else
					System.out.println("That item is not in this room.");
			}
			else if(input.equals("open door"))
			{
				System.out.println("You open the door of this room and walk into the next.");
				game.currentRoom = game.currentRoom.getConnectedRooms().get(0);
				player.setCurrentRoom(game.currentRoom.getConnectedRooms().get(0));
				System.out.println(game.currentRoom.toString());
			}
			else if(input.equals("list inventory"))
			{
				System.out.println(player.listInventory());
			}
			else if(input.equals("attack"))
			{
				System.out.println("Attack what?");
				String userInput = game.scan.nextLine();
				Monster attackee = new Monster();
				for(AnimateEntity e : game.currentRoom.getAnimateEntities())
				{
					if(userInput.equals(player.getName()))
					{
						System.out.println("This is an adventure game not a suicide, attack the enemies.");
						break;
					}
					if(userInput.equals(e.getName()) && !(userInput.equals(player.getName())))
					{
						attackee = (Monster) e;
						if(attackee.isAggressive)
						{
							game.engageCombat(attackee);
							break;
						}
						else
							System.out.println("You cannot attack the " + userInput + " because it is not aggressive");
					}
					else
						System.out.println("That entity does not exist in this room.");
				}
				ArrayList<AnimateEntity> monstersKilled = new ArrayList<AnimateEntity>();
				for(AnimateEntity e : game.currentRoom.getAnimateEntities())
				{
					if(e.getHealth() <= 0)
					{
						monstersKilled.add(e);

					}
				}
				for(AnimateEntity e : monstersKilled)
				{
					game.currentRoom.removeAnimateEntity(e);
				}
			}
			else if(input.equals("talk"))
			{
				for(AnimateEntity e : game.currentRoom.getAnimateEntities())
				{
					if(e instanceof Trader)
					{
						((Trader) e).talk();
					}
				}
			}
			else if(input != null)
			{
				System.out.println("You cannot do that, as you are a potato.");
			}
			continue;
		}
	}
}