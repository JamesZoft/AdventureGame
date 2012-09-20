package game;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;



public class Trader extends NPC{

	private Map<String, Integer> shop;
	private ArrayList<ConversationBranch> convoMap;
	//private File convoFile;
	private File z;
	private Scanner scanner;

	public Trader(String name, int health, int attack, ArrayList<Item> inventory, boolean isAggressive) 
	{
		super(name, health, attack, inventory, isAggressive);
		z = new File("D:/James/University stuff/Java/eclipse/Eclipse_projects/AdventureGame/src/game/trader_talk.txt");
		//convoFile = new File("trader_talk.txt");
		convoMap = new ArrayList<ConversationBranch>();
		try {
			scanner = new Scanner(z);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		try {
			setUpConvo();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public Map<String, Integer> getShop()
	{
		return shop;
	}
	
	public void talk()
	{
		System.out.println("What would you like to say?");
		System.out.println("1 " + convoMap.get(0) + "\n" + convoMap.size() + convoMap.get(convoMap.size() - 1).getPrompt());
		String nextLine = Game.scan.nextLine();
		ConversationBranch lastBranch = convoMap.get(0);
		try
		{
			switch(Integer.parseInt(nextLine))
			{
			case 1: System.out.println(convoMap.get(0).getResponse()); break;
			case 2: System.out.println(convoMap.get(convoMap.size() - 1).getResponse()); return;
			}
			
			while(!nextLine.contains(convoMap.get(convoMap.size() - 1).getPrompt()))
			{
				nextLine = Game.scan.nextLine();
				for(ConversationBranch b : convoMap)
				{
					if(nextLine.contains(b.getPrompt()))
					{
						System.out.println(b.getResponse());
						lastBranch = b;
						printConvoChoices(lastBranch);
					}
				}
			}
			System.out.println(convoMap.get(convoMap.size() - 1).getResponse());
		}
	
		catch(NumberFormatException e)
		{
			System.out.println("Please enter a valid number from the list.");
		}
	}
	
	private void printConvoChoices(ConversationBranch b)
	{
		System.out.println("Your conversation choices are:");
		int i = 0;
		for(i = 0; i < b.getBranches().length; i++)
		{
			System.out.println(i + ". " + convoMap.get(i - 1).getPrompt());
		}
		System.out.println(i + convoMap.get(convoMap.size()).getPrompt());
	}

	private void setUpConvo() throws FileNotFoundException
	{
		while(scanner.hasNextLine())
		{
			int number = Integer.parseInt(scanner.nextLine().split(" ")[1]);
			String prompt = scanner.nextLine();
			String response = scanner.nextLine();
			String[] stringBranches = scanner.nextLine().split(" ");
			int[] branches = new int[stringBranches.length];
			for(int i = 0; i < stringBranches.length; i++)
			{
				if(stringBranches[i].equals("quit"))
				{
					//inputFileScanner.nextLine();
					break;
				}
				branches[i] = Integer.parseInt(stringBranches[i]);
			}
			convoMap.add(new ConversationBranch(number, prompt, response, branches));
			
		}
	}
}
