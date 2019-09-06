package skills.herblore;

import common.item.Item;

public class Herb extends Item
{
    public Herb(String inName, String inImageDirectory)
    {
        super(inName, inImageDirectory);
    }

    @Override
    public void setPatterns()
    {
        BankPicture            = ImageDirectory + "/" + ItemName + "_Clean_Bank.png";
        InventoryPicture       = ImageDirectory + "/" + ItemName + "_Clean_Inventory.png";
        GrimyBankPicture       = ImageDirectory + "/" + ItemName + "_Grimy_Bank.png";
        GrimyInventoryPicture  = ImageDirectory + "/" + ItemName + "_Grimy_Inventory.png";
        PotionInventoryPicture = ImageDirectory + "/" + ItemName + "_Potion_Inventory.png";
        PromptPicture          = ImageDirectory + "/" + ItemName + "_Prompt.png";
    }

    public String GrimyBankPicture;
    public String GrimyInventoryPicture;
    public String PotionInventoryPicture;
    public String PromptPicture;
}
