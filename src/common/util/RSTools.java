package common.util;

import java.util.Iterator;
import java.util.concurrent.TimeUnit;

import org.sikuli.script.FindFailed;
import org.sikuli.script.Match;
import org.sikuli.script.Pattern;
import org.sikuli.script.Region;
import org.sikuli.script.Screen;

import common.enums.ClickType;

public class RSTools
{
    // Open the bank
    public static void openBank() throws InterruptedException, FindFailed
    {
        System.out.println("Opening bank");
        while(Coordinates.Bankbox.exists("src/common/Images/BankOfGlielinor.png") == null)
        {
            Pseudorandom.pseudorandomClick(Coordinates.BankerRegion, ClickType.RIGHT, 0, 0.1);

            Match bankMatch = Coordinates.LargerBankerRegion.exists("src/common/Images/BankBanker.png");
            if (bankMatch != null)
            {
                System.out.println("Found bank banker prompt");
                Pseudorandom.pseudorandomClick(bankMatch, ClickType.LEFT, 0, 0.1);
            }
            else
            {
                Pseudorandom.pseudorandomNorth(1);
            }
        }
    }

    // Open the bank
    public static void openBankRuneLite() throws InterruptedException, FindFailed
    {
        System.out.println("Opening bank");
        while(true)
        {
            Pseudorandom.pseudorandomClick(Coordinates.BankerRegion, ClickType.LEFT, 0, 0.1);

            if (Coordinates.Bankbox.exists("src/common/Images/BankOfGlielinor.png") != null)
            {
                break;
            }
        }
    }

    // Deposit from inventory
    public static void depositFromInventory(String inPicture) throws FindFailed, InterruptedException
    {
        System.out.println("Depositing from inventory: " + inPicture);
        while(true)
        {
            Match inventoryMatch = Coordinates.Inventory.exists(inPicture);
            if (inventoryMatch != null)
            {
                System.out.println("Found item in inventory: " + inPicture);
                Pseudorandom.pseudorandomClick(inventoryMatch, ClickType.RIGHT, 0, 0.1);

                Match depositMatch = Coordinates.BottomRight.exists("src/common/Images/DepositAll.png");
                if (depositMatch != null)
                {
                    System.out.println("Depositing: " + inPicture);
                    Pseudorandom.pseudorandomClick(depositMatch, ClickType.LEFT, 0, 0.1);
                    Pseudorandom.pseudorandomSleep(1, 1);
                }
            }
            else
            {
                System.out.println("Did not find item in inventory: " + inPicture);
                return;
            }
        }
    }

    // Withdraw from bank
    // This assumes you have the bank open already
    public static boolean withdrawFromBank(String inBankPicture, String inInventoryPicture, int inWithdrawAmount) throws FindFailed, InterruptedException
    {
        System.out.println("Withdrawing from bank: " + inBankPicture);

        // Check if the bank has the item
        Match bankItemMatch = Coordinates.Bankbox.exists(new Pattern(inBankPicture).similar((float)0.99));
        if (bankItemMatch != null)
        {
            // If the bank has the item, right click it
            Pseudorandom.pseudorandomClick(new Region(bankItemMatch.getRect()), ClickType.RIGHT, 0, 0.1);

            // Look for the withdraw popup
            Match withdrawMatch = null;

            // Get the amount of items to be withdrawn
            // TODO add support for custom withdraw amounts
            if (inWithdrawAmount == -1)
            {
                withdrawMatch = Coordinates.LargerBankerRegion.find("src/common/Images/WithdrawAll.png");
            }
            else if (inWithdrawAmount == 14)
            {
                withdrawMatch = Coordinates.LargerBankerRegion.find("src/common/Images/Withdraw14.png");
            }
            else
            {
                System.out.println("Unrecognized withdraw amount: " + inWithdrawAmount);
            }

            if (withdrawMatch != null)
            {
                // Click the withdraw option
                Region withdrawRegion = new Region(withdrawMatch.getRect());
                Pseudorandom.pseudorandomClick(withdrawRegion, ClickType.LEFT, 0, 0.1);

                return true;

                // TODO Reimplement the withdraw check

                // Check that the item was withdrawn
//                Match inventoryItemMatch = Coordinates.Inventory.exists(inInventoryPicture);
//                if (inventoryItemMatch != null)
//                {
//                    return true;
//                }
//                else
//                {
//                    System.out.println("Error in withdrawing: " + inInventoryPicture);
//                }
            }
            else
            {
                System.out.println("Error when trying to find withdraw popup.");
            }
        }
        else
        {
            System.out.println("Could not find item: " + inBankPicture + " in bank");
        }

        return false;
    }

    public static void setWithdrawX() throws FindFailed, InterruptedException
    {
        openBank();
        Pseudorandom.pseudorandomClick(Coordinates.WithdrawX, ClickType.LEFT, 0, 0.1);
    }

    public static void setWithdrawAll() throws FindFailed, InterruptedException
    {
        openBank();
        Pseudorandom.pseudorandomClick(Coordinates.WithdrawAll, ClickType.LEFT, 0, 0.1);
    }

    public static void setWithdrawXRuneLite() throws FindFailed, InterruptedException
    {
        openBankRuneLite();
        Pseudorandom.pseudorandomClick(Coordinates.WithdrawX, ClickType.LEFT, 0, 0.1);
    }

    public static void setWithdrawAllRuneLite() throws FindFailed, InterruptedException
    {
        openBankRuneLite();
        Pseudorandom.pseudorandomClick(Coordinates.WithdrawAll, ClickType.LEFT, 0, 0.1);
    }

    // Be sure to set the quantity before calling this function
    public static boolean withdrawOneClick(String inBankPicture) throws FindFailed, InterruptedException
    {
        System.out.println("Withdrawing from bank: " + inBankPicture);

        // Check if the bank has the item
        Match bankItemMatch = Coordinates.Bankbox.exists(new Pattern(inBankPicture).similar((float)0.99));
        if (bankItemMatch != null)
        {
            Pseudorandom.pseudorandomClick(new Region(bankItemMatch.getRect()), ClickType.LEFT, 0, 0.1);

            return true;
        }
        else
        {
            System.out.println("Could not find item: " + inBankPicture + " in bank");
        }

        return false;
    }

    public static void depositAll() throws FindFailed, InterruptedException
    {
        System.out.println("Depositing entire inventory");
        Match depositAll = Coordinates.Bankbox.exists("src/common/Images/DepositInventory.png");
        if (depositAll != null)
        {
            Pseudorandom.pseudorandomClick(new Region(depositAll.getRect()), ClickType.LEFT, 0, 0.1);
        }
        else
        {
            System.out.println("Error when depositing entire inventory");
        }
    }

    // Close bank
    public static void closeBank() throws FindFailed, InterruptedException
    {
        Pseudorandom.pseudorandomClick(Coordinates.CloseBank, ClickType.LEFT, 0, 0.1);

        // TODO Reimplement a search function to close the bank

//        Match closeBox = Coordinates.Bankbox.exists("src/common/Images/ClosePopup.png");
//        if (closeBox != null)
//        {
//            Pseudorandom.pseudorandomClick(new Region(closeBox.getRect()), ClickType.LEFT, 0, 0.1);
//        }
    }

    // Click on all inventory items
    public static void clickOnAllInInventory(String inPicture) throws InterruptedException
    {
        System.out.println("Clicking on all in inventory: " + inPicture);

        try
        {
            Iterator<Match> it = Coordinates.Inventory.findAll(new Pattern(inPicture).similar((float)0.5));

            Match match;
            while (it.hasNext())
            {
                match = it.next();

                Pseudorandom.pseudorandomClick(new Region(match.getRect()), ClickType.LEFT, 0, 0);
            }
        }
        catch (FindFailed ff)
        {
            System.out.println("Could not find item in inventory: " + inPicture);
            return;
        }
    }

    public static void collectFromExchange() throws FindFailed, InterruptedException
{
        closeBank();

        Pseudorandom.pseudorandomClick(Coordinates.ExchangerRegion, ClickType.LEFT, 0, 0.1);

        TimeUnit.SECONDS.sleep(2);

        Match match = Coordinates.ExchangeBox.exists("src/common/Images/GrandExchange.png");
        if (match != null)
        {
            Pseudorandom.pseudorandomClick(Coordinates.CollectButton, ClickType.LEFT, 0, 0.1);

            Match closeBox = Coordinates.ExchangeBox.exists("src/common/Images/ClosePopup.png");
            if (closeBox != null)
            {
                Pseudorandom.pseudorandomClick(new Region(closeBox.getRect()), ClickType.LEFT, 0, 0.1);
            }
        }
        else
        {
            System.out.println("Couldn't find exchange");
        }
    }

    public static void removeTeamViewerPrompt() throws FindFailed, InterruptedException
    {
        // Wait 15 seconds for teamviewer to exit
        TimeUnit.SECONDS.sleep(15);

        Screen screen = new Screen();
        int count = 0;
        while (count < 1000)
        {
            TimeUnit.MICROSECONDS.sleep(5);

            Pseudorandom.pseudorandomNorth(1);

            // Find the prompt
            Match teamviewerPrompt = screen.exists("src/common/Images/TeamViewerPrompt.png");
            if (teamviewerPrompt != null)
            {
                // Click the ok button
                Match okButton = teamviewerPrompt.exists("src/common/Images/TeamViewerOkButton.png");

                if (okButton != null)
                {
                    System.out.println("Clicking on teamviewer ok button.");
                    okButton.click();
                }
                else
                {
                    System.out.println("Couldn't find ok button in teamviewer prompt.");
                }
            }
            else
            {
                System.out.println("Teamviewer prompt not found.");
                break;
            }

            count++;
        }
    }
}
