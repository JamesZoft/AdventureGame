package game;

import java.util.*;
import java.io.*;

public class ConversationBranch{

	private int number;
	private String prompt;
	private String response;
	private int[] branches;

	public ConversationBranch(int number, String prompt, String response, int[] branches)
	{
		this.number = number;
		this.prompt = prompt;
		this.response = response;
		this.branches = branches;
	}
	
	public int getNumber()
	{
		return number;
	}
	
	public String getPrompt()
	{
		return prompt;
	}
	
	public String getResponse()
	{
		return response;
	}
	
	public int[] getBranches()
	{
		return branches;
	}
	
	public void listBranches()
	{
		for(int i : branches)
		{
			System.out.println(i);
		}
	}
}

