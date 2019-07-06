package Scripts.Herblore;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.sikuli.script.FindFailed;
import org.sikuli.script.Match;
import org.sikuli.script.Region;

import Common.Enums.ClickType;
import Common.Item.Item;
import Common.Util.Coordinates;
import Common.Util.Pseudorandom;
import Common.Util.RSTools;

public class Herblore
{
    public static boolean RuneLite = true;

    // Initialize the Herb and Water Vial
    public static Herb anHerb;
    public static Item WaterVial = new Item("WaterVial", "src/Scripts/Herblore/Images/WaterVial");

    public static void main(String[] args)
    {

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
            System.out.println("Enter 1 to use harralander. Enter 2 to use ranarr. Enter 3 to use avantoe. Enter 4 to use snapdragon.");
            input = reader.readLine();
            switch(input)
            {
                case "1":
                    System.out.println("Using harralander");
                    anHerb = new Herb("Harralander", "src/Scripts/Herblore/Images/Harralander");
                    break;

                case "2":
                    System.out.println("Using ranarr");
                    anHerb = new Herb("Ranarr", "src/Scripts/Herblore/Images/Ranarr");
                    break;

                case "3":
                    System.out.println("Using avantoe");
                    anHerb = new Herb("Avantoe", "src/Scripts/Herblore/Images/Avantoe");
                    break;

                case "4":
                    System.out.println("Using snapdragon");
                    anHerb = new Herb("Snapdragon", "src/Scripts/Herblore/Images/Snapdragon");
                    break;

                default:
                    System.out.println("Could not understand input: " + input);
                    return;
            }

            // Remove the teamviewer prompt if necessary
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
                Pseudorandom.pseudorandomClick(new Region(herbMatch.getRect()), ClickType.LEFT, 0, 0.25);
                Pseudorandom.pseudorandomClick(new Region(vialMatch.getRect()), ClickType.LEFT, 0, 0.25);

                Match promptMatch = Coordinates.Chatbox.exists(anHerb.PromptPicture);
                if (promptMatch != null)
                {
                    Pseudorandom.pseudorandomClick(new Region(promptMatch.getRect()), ClickType.LEFT, 0, 0.25);
                    Pseudorandom.pseudorandomSleep(1, 5);
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
    }
}
