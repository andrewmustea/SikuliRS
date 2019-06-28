package Common.Util;

import org.sikuli.script.Region;

public class Coordinates
{
	public static Region Inventory   = new Region(2344,1101,193,261);
	public static Region Chatbox     = new Region(6,1241,508,131);
	public static Region Bankbox     = new Region(914,230,485,799);
	public static Region BottomRight = new Region(2175,979,386,422);

	// Bank
	public static Region BankerRegion       = new Region(1351,582,15,57);
	public static Region LargerBankerRegion = new Region(547,107,1216,1099);

	// TODO make this a configurable setting in a JSON or .txt
	public static void set1080()
	{
	    Inventory   = new Region(1708,744,182,252);
	    Chatbox     = new Region(6,880,507,131);
	    Bankbox     = new Region(594,50,483,797);
	    BottomRight = new Region(1468,585,450,451);

	    // Bank
	    BankerRegion       = new Region(998,436,10,40);
	    LargerBankerRegion = new Region(447,32,815,855);
	}

	public static void set1440()
	{
	    Inventory   = new Region(2344,1101,193,261);
        Chatbox     = new Region(6,1241,508,131);
        Bankbox     = new Region(914,230,485,799);
        BottomRight = new Region(2175,979,386,422);

        // Bank
        BankerRegion       = new Region(1351,582,15,57);
        LargerBankerRegion = new Region(547,107,1216,1099);
	}
}
