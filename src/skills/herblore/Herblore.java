package skills.herblore;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.sikuli.script.FindFailed;
import org.sikuli.script.Match;
import org.sikuli.script.Region;

import common.enums.ClickType;
import common.item.Item;
import common.util.Coordinates;
import common.util.Pseudorandom;
import common.util.RSTools;

public class Herblore
{
    public static boolean RuneLite = true;

    // Initialize the Herb and Water Vial
    public static Herb anHerb;
    public static Item WaterVial = new Item("WaterVial", "src/skills/Herblore/Images/WaterVial");

    public static void main(String[] args)
    {
        // TODO use config to set
        Coordinates.set1440RuneLite();

        try
        {
            boolean CleanHerbs  = false;
            boolean MakePotions = false;

            // Take input to decide what to do
            System.out.println("Enter 1 to clean herbs. Enter 2 to make potions. Enter 3 for both.");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            String input = reader.readLine();
            switch(input)
            {
                case "1":
                    System.out.println("Cleaning herbs");
                    CleanHerbs = true;
                    break;

                case "2":
                    System.out.println("Making potions");
                    MakePotions = true;
                    break;

                case "3":
                    System.out.println("Cleaning herbs and making potions");
                    CleanHerbs = true;
                    MakePotions = true;
                    break;

                default:
                    System.out.println("Could not understand input: " + input);
                    return;
            }

            // Find out which herb to use
            System.out.println("Enter 1 to use harralander. Enter 2 to use ranarr. Enter 3 to use avantoe. Enter 4 to use snapdragon. Enter 5 to use dwarf weed. Enter 6 to use irit.");
            input = reader.readLine();
            switch(input)
            {
                case "1":
                    System.out.println("Using harralander");
                    anHerb = new Herb("Harralander", "src/skills/Herblore/Images/Harralander");
                    break;

                case "2":
                    System.out.println("Using ranarr");
                    anHerb = new Herb("Ranarr", "src/skills/Herblore/Images/Ranarr");
                    break;

                case "3":
                    System.out.println("Using avantoe");
                    anHerb = new Herb("Avantoe", "src/skills/Herblore/Images/Avantoe");
                    break;

                case "4":
                    System.out.println("Using snapdragon");
                    anHerb = new Herb("Snapdragon", "src/skills/Herblore/Images/Snapdragon");
                    break;

                case "5":
                    System.out.println("Using dwarf weed");
                    anHerb = new Herb("DwarfWeed", "src/skills/Herblore/Images/DwarfWeed");
                    break;

                case "6":
                    System.out.println("Using irit");
                    anHerb = new Herb("Irit", "src/skills/Herblore/Images/Irit");
                    break;

                default:
                    System.out.println("Could not understand input: " + input);
                    return;
            }

            // Remove the teamviewer prompt if necessary
            // TODO put all into a function
            System.out.println("Is there a TeamViewer dialog box to remove? Enter 1 for yes. Enter anything else for no");
            input = reader.readLine();
            if (input.equals("1")) RSTools.removeTeamViewerPrompt();

            // Clean herbs
            if (CleanHerbs) cleanHerbs();

            // Make Potions
            if (MakePotions) makePotions();

        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    public static void cleanHerbs() throws FindFailed, InterruptedException, IOException
    {
        if (RuneLite) RSTools.setWithdrawAllRuneLite();
        else          RSTools.setWithdrawAll();

        while (true)
        {
            // Deposit items
            RSTools.depositAll();

            // Withdraw items
            if (!RSTools.withdrawOneClick(anHerb.GrimyBankPicture))
            {
                break;
            }

            // Close bank
            RSTools.closeBank();

            // Clean all herbs
            RSTools.clickOnAllInInventory(anHerb.GrimyInventoryPicture);

            // Open Bank
            if (RuneLite) RSTools.openBankRuneLite();
            else          RSTools.openBank();
        }

        RSTools.collectFromExchange();
    }

    public static void makePotions() throws FindFailed, InterruptedException
    {
        if (RuneLite) RSTools.setWithdrawXRuneLite();
        else          RSTools.setWithdrawX();

        while(true)
        {
            // Deposit items
            RSTools.depositAll();

            // Withdraw Items
            if (!RSTools.withdrawOneClick(anHerb.BankPicture) ||
                !RSTools.withdrawOneClick(WaterVial.BankPicture))
            {
                break;
            }

            // Close bank
            RSTools.closeBank();

            // Make potions
            Match herbMatch = Coordinates.Inventory.exists(anHerb.InventoryPicture);
            Match vialMatch = Coordinates.Inventory.exists(WaterVial.InventoryPicture);
            if (herbMatch != null && vialMatch != null)
            {
                Pseudorandom.pseudorandomClick(new Region(herbMatch.getRect()), ClickType.LEFT, 0, 0.1);
                Pseudorandom.pseudorandomClick(new Region(vialMatch.getRect()), ClickType.LEFT, 0, 0.1);

                Match promptMatch = Coordinates.Chatbox.exists(anHerb.PromptPicture);
                if (promptMatch != null)
                {
                    Pseudorandom.pseudorandomClick(new Region(promptMatch.getRect()), ClickType.LEFT, 0, 0.1);
                    Pseudorandom.pseudorandomSleep(1, 9);
                }
            }
            else
            {
                System.out.println("Couldn't find herb or water vial in bank");
            }

            // Open Bank
            if (RuneLite) RSTools.openBankRuneLite();
            else          RSTools.openBank();
        }

        RSTools.collectFromExchange();
    }
}
