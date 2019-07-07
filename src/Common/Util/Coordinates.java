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
	public static Region CloseBank          = new Region(1372,237,18,18);
	public static Region WithdrawX          = new Region(1195,1006,16,14);
	public static Region WithdrawAll        = new Region(1221,1006,16,14);

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

	public static void set1440RuneLite()
	{
	    Inventory   = new Region(2308,1102,191,261);
        Chatbox     = new Region(6,1240,508,131);
        Bankbox     = new Region(898,234,477,791);
        BottomRight = new Region(2091,947,433,453);

        // Bank
        BankerRegion       = new Region(1323,605,8,17);
        LargerBankerRegion = new Region(692,135,905,1014);
        CloseBank          = new Region(1354,237,18,18);
        WithdrawX          = new Region(1177,1005,16,15);
        WithdrawAll        = new Region(1202,1006,17,13);
	}

	public static void set1080RuneLite()
	{
	    Inventory = new Region(1669,741,190,262);
	    Chatbox = new Region(5,880,509,132);
	    Bankbox = new Region(578,54,479,791);
	    BottomRight = new Region(1517,633,367,407);

	    //Bank
	    BankerRegion = new Region(1009,436,12,19);
	    LargerBankerRegion = new Region(406,31,841,893);
	    CloseBank = new Region(1035,58,17,17);
	    WithdrawX = new Region(858,826,15,14);
	    WithdrawAll = new Region(883,827,16,11);
	}
}
