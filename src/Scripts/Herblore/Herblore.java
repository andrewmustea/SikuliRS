package Scripts.Herblore;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;

import org.sikuli.script.FindFailed;
import org.sikuli.script.Match;
import org.sikuli.script.Region;
import org.sikuli.script.Screen;

import Common.Enums.ClickType;
import Common.Util.Coordinates;
import Common.Util.Pseudorandom;
import Common.Util.RSTools;

public class Herblore {

    // Bank pictures
    public static String BankCleanHerb;
    public static String BankGrimyHerb;
    public static String BankWaterVial = "src/Scripts/Herblore/Images/BankWaterVial.png";;

    // Inventory pictures
    public static String InventoryCleanHerb;
    public static String InventoryGrimyHerb;
    public static String InventoryWaterVial = "src/Scripts/Herblore/Images/InventoryWaterVial.png";
    public static String InventoryUnfinshedPotion;

    // Other pictures
    public static String PotionPrompt;

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

                    BankCleanHerb            = "src/Scripts/Herblore/Images/BankCleanHarralander.png";
                    BankGrimyHerb            = "src/Scripts/Herblore/Images/BankGrimyHarralander.png";
                    InventoryCleanHerb       = "src/Scripts/Herblore/Images/InventoryCleanHarralander.png";
                    InventoryGrimyHerb       = "src/Scripts/Herblore/Images/InventoryGrimyHarralander.png";
                    InventoryUnfinshedPotion = "src/Scripts/Herblore/Images/InventoryUnfinishedHarralanderPotion.png";
                    PotionPrompt             = "src/Scripts/Herblore/Images/HarralanderPotionPrompt.png";

                    break;

                case "2":
                    System.out.println("Using ranarr");

                    BankCleanHerb            = "src/Scripts/Herblore/Images/BankCleanRanarr.png";
                    BankGrimyHerb            = "src/Scripts/Herblore/Images/BankGrimyRanarr.png";
                    InventoryCleanHerb       = "src/Scripts/Herblore/Images/InventoryCleanRanarr.png";
                    InventoryGrimyHerb       = "src/Scripts/Herblore/Images/InventoryGrimyRanarr.png";
                    InventoryUnfinshedPotion = "src/Scripts/Herblore/Images/InventoryUnfinishedRanarrPotion.png";
                    PotionPrompt             = "src/Scripts/Herblore/Images/RanarrPotionPrompt.png";

                    break;

                case "3":
                    System.out.println("Using avantoe");

                    BankCleanHerb            = "src/Scripts/Herblore/Images/BankCleanAvantoe.png";
                    BankGrimyHerb            = "src/Scripts/Herblore/Images/BankGrimyAvantoe.png";
                    InventoryCleanHerb       = "src/Scripts/Herblore/Images/InventoryCleanAvantoe.png";
                    InventoryGrimyHerb       = "src/Scripts/Herblore/Images/InventoryGrimyAvantoe.png";
                    InventoryUnfinshedPotion = "src/Scripts/Herblore/Images/InventoryUnfinishedAvantoePotion.png";
                    PotionPrompt             = "src/Scripts/Herblore/Images/AvantoePotionPrompt.png";

                    break;

                case "4":
                    System.out.println("Using snapdragon");

                    BankCleanHerb            = "src/Scripts/Herblore/Images/BankCleanSnapdragon.png";
                    BankGrimyHerb            = "src/Scripts/Herblore/Images/BankGrimySnapdragon.png";
                    InventoryCleanHerb       = "src/Scripts/Herblore/Images/InventoryCleanSnapdragon.png";
                    InventoryGrimyHerb       = "src/Scripts/Herblore/Images/InventoryGrimySnapdragon.png";
                    InventoryUnfinshedPotion = "src/Scripts/Herblore/Images/InventoryUnfinishedSnapdragonPotion.png";
                    PotionPrompt             = "src/Scripts/Herblore/Images/SnapdragonPotionPrompt.png";

                    break;

                default:
                    System.out.println("Could not understand input: " + input);
                    return;
            }

            // Remove the teamviewer prompt if necessary
            System.out.println("Is there a TeamViewer dialog box to remove? Enter 1 for yes. Enter anything else for no");
            input = reader.readLine();
            if (input.equals("1")) removeTeamViewerPrompt();

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

    public static void removeTeamViewerPrompt() throws FindFailed, InterruptedException
    {
        // Wait 5 seconds for teamviewer to exit
        TimeUnit.SECONDS.sleep(5);

        // Find the prompt
        Screen screen = new Screen();
        Match teamviewerPrompt = screen.find("src/Common/Images/TeamViewerPrompt.png");
        if (teamviewerPrompt != null)
        {
            // Click the ok button
            Match okButton = teamviewerPrompt.find("src/Common/Images/TeamViewerOkButton.png");
            okButton.click();
        }
    }

    public static void cleanHerbs() throws FindFailed, InterruptedException, IOException
    {
        while (true)
        {
            // Begin bank search
            RSTools.openBank();

            // Deposit items
            RSTools.depositFromInventory(InventoryCleanHerb);
            RSTools.depositFromInventory(InventoryGrimyHerb);

            // Withdraw items
            if (!RSTools.withdrawFromBank(BankGrimyHerb, InventoryGrimyHerb, -1))
            {
                break;
            }

            // Close bank
            RSTools.closeBank();

            // Face north occasionally
            Pseudorandom.pseudorandomNorth(0.25);

            // Clean all herbs
            RSTools.clickOnAllInInventory(InventoryGrimyHerb);
        }
    }

    public static void makePotions() throws FindFailed, InterruptedException
    {
        while(true)
        {
            // Begin bank search
            RSTools.openBank();

            // Deposit items
            RSTools.depositFromInventory(InventoryCleanHerb);
            RSTools.depositFromInventory(InventoryWaterVial);
            RSTools.depositFromInventory(InventoryUnfinshedPotion);

            // Withdraw Items
            if (!RSTools.withdrawFromBank(BankCleanHerb, InventoryCleanHerb, 14) ||
                !RSTools.withdrawFromBank(BankWaterVial, InventoryWaterVial, 14))
            {
                break;
            }

            // Close bank
            RSTools.closeBank();

            // Face north occasionally
            Pseudorandom.pseudorandomNorth(0.25);

            // Make potions
            Match herbMatch = Coordinates.Inventory.exists(InventoryCleanHerb);
            Match vialMatch = Coordinates.Inventory.exists(InventoryWaterVial);
            if (herbMatch != null && vialMatch != null)
            {
                Pseudorandom.pseudorandomClick(new Region(herbMatch.getRect()), ClickType.LEFT, 0, 1);
                Pseudorandom.pseudorandomClick(new Region(vialMatch.getRect()), ClickType.LEFT, 0, 1);

                Match potionMatch = Coordinates.Chatbox.exists(PotionPrompt);
                if (potionMatch != null)
                {
                    Pseudorandom.pseudorandomClick(new Region(potionMatch.getRect()), ClickType.LEFT, 0, 1);
                    Pseudorandom.pseudorandomSleep(1, 5);
                }
            }
            else
            {
                System.out.println("Couldn't find herb or water vial in bank");
            }
        }
    }
}
