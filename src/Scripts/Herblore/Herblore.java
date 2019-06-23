package Scripts.Herblore;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.sikuli.script.FindFailed;
import org.sikuli.script.Match;
import org.sikuli.script.Region;

import Common.Enums.ClickType;
import Common.Util.Coordinates;
import Common.Util.Pseudorandom;
import Common.Util.RSTools;

public class Herblore {
	
	public static void main(String[] args)
	{
		try
		{
			boolean CleanHerbs = false;
			boolean MakePotions = false;
			
			// Take input to decide what to do
			System.out.println("Enter 1 to clean herbs. Enter 2 to make potions. Enter 3 for both."); 
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			String input = reader.readLine();
			
			switch(input) 
			{
				case "1":
					System.out.println("Cleaning herbs...");
					CleanHerbs = true;
					break;
					
				case "2":
					System.out.println("Making potions...");
					MakePotions = true;
					break;
					
				case "3":
					System.out.println("Cleaning herbs and making potions...");
					CleanHerbs = true;
					MakePotions = true;
					break;
					
				default:
					System.out.println("Could not understand input: " + input);
					return;
			}
			
			if (CleanHerbs) cleanHerbs();
			if (MakePotions) makePotions();
			
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	public static void cleanHerbs() throws FindFailed, InterruptedException, IOException
	{
		while (true)
		{
			// Begin bank search
			RSTools.openBank();
			
			// Deposit items
			RSTools.depositFromInventory("src/Scripts/Herblore/Images/InventoryCleanHarralander.png");
			RSTools.depositFromInventory("src/Scripts/Herblore/Images/InventoryGrimyHarralander.png");
			
			// Withdraw items
			if (!RSTools.withdrawFromBank("src/Scripts/Herblore/Images/BankGrimyHarralander", "src/Scripts/Herblore/Images/InventoryGrimyHarralander.png", -1))
			{
				break;
			}
			
			// Close bank
			RSTools.closeBank();
			
			// Face north occasionally
			Pseudorandom.pseudorandomNorth(0.25);
			
			// Clean all herbs
			RSTools.clickOnAllInInventory("src/Scripts/Herblore/Images/InventoryGrimyHarralander.png");
		}
	}
	
	public static void makePotions() throws FindFailed, InterruptedException
	{
		while(true)
		{
			// Begin bank search
			RSTools.openBank();
			
			// Deposit items
			RSTools.depositFromInventory("src/Scripts/Herblore/Images/InventoryCleanHarralander.png");
			RSTools.depositFromInventory("src/Scripts/Herblore/Images/InventoryWaterVial.png");
			RSTools.depositFromInventory("src/Scripts/Herblore/Images/InventoryUnfinishedHarralanderPotion.png");
			
			// Withdraw Items
			if (!RSTools.withdrawFromBank("src/Scripts/Herblore/Images/BankCleanHarralander.png", "src/Scripts/Herblore/Images/InventoryCleanHarralander.png", 14) ||
				!RSTools.withdrawFromBank("src/Scripts/Herblore/Images/BankWaterVial.png", "src/Scripts/Herblore/Images/InventoryWaterVial.png", 14))
			{
				break;
			}
			
			// Close bank
			RSTools.closeBank();
			
			// Face north occasionally
			Pseudorandom.pseudorandomNorth(0.25);
			
			// Make potions
			Match herbMatch = Coordinates.Inventory.exists("src/Scripts/Herblore/Images/InventoryCleanHarralander.png");
			Match vialMatch = Coordinates.Inventory.exists("src/Scripts/Herblore/Images/InventoryWaterVial.png");
			if (herbMatch != null && vialMatch != null)
			{
				Pseudorandom.pseudorandomClick(new Region(herbMatch.getRect()), ClickType.LEFT, 0, 1);
				Pseudorandom.pseudorandomClick(new Region(vialMatch.getRect()), ClickType.LEFT, 0, 1);
				
				Match potionMatch = Coordinates.Chatbox.exists("src/Scripts/Herblore/Images/HarralanderPotionPrompt.png");
				if (potionMatch != null)
				{
					Pseudorandom.pseudorandomClick(new Region(potionMatch.getRect()), ClickType.LEFT, 0, 1);
					Pseudorandom.pseudorandomSleep(1, 5);
				}
			}
		}
	}
}
