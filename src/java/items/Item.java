package items;

public class Item
{
    // TODO make this use patterns instead of strings
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
