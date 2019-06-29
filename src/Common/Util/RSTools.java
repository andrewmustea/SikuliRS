package Common.Util;

import java.util.Iterator;
import java.util.concurrent.TimeUnit;

import org.sikuli.script.FindFailed;
import org.sikuli.script.Match;
import org.sikuli.script.Pattern;
import org.sikuli.script.Region;
import org.sikuli.script.Screen;

import Common.Enums.ClickType;

public class RSTools {

    // Open the bank
    public static void openBank() throws InterruptedException, FindFailed
    {
        System.out.println("Opening bank");
        while(Coordinates.Bankbox.exists("src/Common/Images/BankOfGlielinor.png") == null)
        {
            Pseudorandom.pseudorandomClick(Coordinates.BankerRegion, ClickType.RIGHT, 0, 1);

            Match bankMatch = Coordinates.LargerBankerRegion.exists("src/Common/Images/BankBanker.png");
            if (bankMatch != null)
            {
                System.out.println("Found bank banker prompt");
                Pseudorandom.pseudorandomClick(bankMatch, ClickType.LEFT, 0, 1);
            }
            else
            {
                Pseudorandom.pseudorandomNorth(1);
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
                Pseudorandom.pseudorandomClick(inventoryMatch, ClickType.RIGHT, 0, 1);

                Match depositMatch = Coordinates.BottomRight.exists("src/Common/Images/DepositAll.png");
                if (depositMatch != null)
                {
                    System.out.println("Depositing: " + inPicture);
                    Pseudorandom.pseudorandomClick(depositMatch, ClickType.LEFT, 0, 1);
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
            Region bankItemRegion = new Region(bankItemMatch.getRect());
            Pseudorandom.pseudorandomClick(bankItemRegion, ClickType.RIGHT, 0, 1);

            // Look for the withdraw popup
            Match withdrawMatch = null;

            // Get the amount of items to be withdrawn
            // TODO add support for custom withdraw amounts
            if (inWithdrawAmount == -1)
            {
                withdrawMatch = Coordinates.LargerBankerRegion.find("src/Common/Images/WithdrawAll.png");
            }
            else if (inWithdrawAmount == 14)
            {
                withdrawMatch = Coordinates.LargerBankerRegion.find("src/Common/Images/Withdraw14.png");
            }
            else
            {
                System.out.println("Unrecognized withdraw amount: " + inWithdrawAmount);
            }

            if (withdrawMatch != null)
            {
                // Click the withdraw option
                Region withdrawRegion = new Region(withdrawMatch.getRect());
                Pseudorandom.pseudorandomClick(withdrawRegion, ClickType.LEFT, 0, 1);

                // Check that the item was withdrawn
                Match inventoryItemMatch = Coordinates.Inventory.exists(inInventoryPicture);
                if (inventoryItemMatch != null)
                {
                    return true;
                }
                else
                {
                    System.out.println("Error in withdrawing: " + inInventoryPicture);
                }
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

    // Close bank
    public static void closeBank() throws FindFailed, InterruptedException
    {
        Match closeBox = Coordinates.Bankbox.exists("src/Common/Images/ClosePopup.png");
        if (closeBox != null)
        {
            Pseudorandom.pseudorandomClick(new Region(closeBox.getRect()), ClickType.LEFT, 0, 1);
        }
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

                Pseudorandom.pseudorandomClick(new Region(match.getRect()), ClickType.LEFT, 0, 0.25);
            }
        }
        catch (FindFailed ff)
        {
            System.out.println("Could not find item in inventory: " + inPicture);
            return;
        }
    }

    public static void removeTeamViewerPrompt() throws FindFailed, InterruptedException
    {
        // Wait 15 seconds for teamviewer to exit
        TimeUnit.SECONDS.sleep(15);

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
}
