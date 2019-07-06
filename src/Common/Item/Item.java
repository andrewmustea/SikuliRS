package Common.Item;

public class Item
{
    public Item(String inName, String inImageDirectory)
    {
        ItemName       = inName;
        ImageDirectory = inImageDirectory;

        setPatterns();
    }

    public void setPatterns()
    {
        BankPicture      = ImageDirectory + "/" + ItemName + "_Bank.png";
        InventoryPicture = ImageDirectory + "/" + ItemName + "_Inventory.png";
    }

    public String ItemName;
    public String ImageDirectory;
    public String BankPicture;
    public String InventoryPicture;
}
