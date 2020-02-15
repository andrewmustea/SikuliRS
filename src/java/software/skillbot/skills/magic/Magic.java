package software.skillbot.skills.magic;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;

import org.sikuli.script.FindFailed;
import org.sikuli.script.Match;
import org.sikuli.script.Region;

import common.enums.ClickType;
import items.Item;
import common.util.Coordinates;
import common.util.Pseudorandom;
import common.util.RSTools;

public class Magic
{
    public static boolean RuneLite = true;

    public static Item anItem;
    public static Item cosmicRune = new Item("CosmicRune", "src/software.skillbot.skills/Magic/Images/CosmicRune");


    //TODO make spell class
    public static String Spell;

    public static void main(String[] args)
    {
        // TODO use config to set
        Coordinates.set1440RuneLite();

        try
        {
            boolean highAlch = false;
            boolean enchant  = false;

            // Take input to decide what to do
            System.out.println("Enter 1 to high alch. Enter 2 to enchant.");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            String input = reader.readLine();
//            switch(input)
//            {
//                case "1":
//                    System.out.println("Using high alch");
//                    Spell    = "src/software.skillbot.skills/Magic/Images/Spells/HighAlch.png";
//                    highAlch = true;
//                    break;
//
//                case "2":
//                    System.out.println("Using enchant");
//                    enchant  = true;
//                    break;
//            }

            // Find out what item to use
            System.out.println("Enter 1 to use emerald rings. Enter 2 to use ruby amulets.");
            input = reader.readLine();
//            switch(input)
//            {
//                case "1":
//                    System.out.println("Using emerald rings");
//                    anItem = new Item("EmeraldRing", "src/software.skillbot.skills/Magic/Images/EmeraldRing");
//                    Spell = "src/software.skillbot.skills/Magic/Images/Spells/EnchantEmerald.png";
//                    break;
//
//                case "2":
//                    System.out.println("Using ruby amulet");
//                    anItem = new Item("RubyAmulet", "src/software.skillbot.skills/Magic/Images/RubyAmulet");
//                    Spell = "src/software.skillbot.skills/Magic/Images/Spells/EnchantRuby.png";
//                    break;
//            }

            // Remove the teamviewer prompt if necessary
            System.out.println("Is there a TeamViewer dialog box to remove? Enter 1 for yes. Enter anything else for no");
            input = reader.readLine();
            if (input.equals("1")) RSTools.removeTeamViewerPrompt();


            if (highAlch) highAlch();
            if (enchant) enchantItem();

        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    public static void highAlch()
    {

    }

    public static void enchantItem() throws FindFailed, InterruptedException
    {
        if (RuneLite) RSTools.setWithdrawAllRuneLite();
        else          RSTools.setWithdrawAll();

        while (true)
        {
            // Deposit items
            RSTools.depositAll();

            // Withdraw cosmic rune
            if (!RSTools.withdrawOneClick(cosmicRune.BankPicture))
            {
                break;
            }

            // Withdraw item
            if (!RSTools.withdrawOneClick(anItem.BankPicture))
            {
                break;
            }

            // Close bank
            RSTools.closeBank();

            // Click on enchantment
            Match enchantmentMatch = Coordinates.Inventory.exists(Spell);
            if (enchantmentMatch != null)
            {
                Pseudorandom.pseudorandomClick(new Region(enchantmentMatch.getRect()), ClickType.LEFT, -2, 0.1);

                Match itemMatch = Coordinates.Inventory.exists(anItem.InventoryPicture);
                if (itemMatch != null)
                {
                    Pseudorandom.pseudorandomClick(new Region(itemMatch.getRect()), ClickType.LEFT, 0, 0.1);

                    TimeUnit.SECONDS.sleep(110);
                }
                else
                {
                    // TODO
                    System.out.println("Could not find item in inventory");
                    break;
                }
            }
            else
            {
                // TODO Try and find it
                System.out.println("Couldnt find spell");
                break;
            }

            // Open Bank
            if (RuneLite) RSTools.openBankRuneLite();
            else          RSTools.openBank();
        }
    }

}
