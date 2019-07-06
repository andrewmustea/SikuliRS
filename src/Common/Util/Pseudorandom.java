package Common.Util;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.sikuli.script.FindFailed;
import org.sikuli.script.Location;
import org.sikuli.script.Region;
import org.sikuli.script.Screen;

import Common.Enums.ClickType;

public class Pseudorandom
{
    public static Random rand = new Random();

    // Takes in a region and a buffer then returns a location with a random offset applied
    public static Location getLocationOffset(Region inRegion, int inPixelBuffer)
    {
        // Get a pseudorandom x value
        int xOffset = inRegion.x + rand.nextInt(inRegion.w + 2*inPixelBuffer) - inPixelBuffer;

        // Get a pseudorandom y value
        int yOffset = inRegion.y + rand.nextInt(inRegion.h + 2*inPixelBuffer) - inPixelBuffer;

        return new Location(xOffset, yOffset);
    }

    // Click on a region with an applied offset
    public static void pseudorandomClick(Region inRegion, ClickType inClickType, int inPixelBuffer, double inTimeRange) throws FindFailed, InterruptedException
    {
        pseudorandomSleep(inTimeRange, 0);

        CommonTools.Click(getLocationOffset(inRegion, inPixelBuffer), inClickType);
    }

    // Click on the compass to orient the map back to facing north
    public static void pseudorandomNorth(double inRandomTime) throws FindFailed, InterruptedException
    {
        if (Math.random() < inRandomTime)
        {
            Screen screen = new Screen();
            Region reg = screen.find("src/Common/Images/Compass.png");

            pseudorandomClick(reg, ClickType.LEFT, 0, 0.25);
        }
    }

    // Sleeps for a certain amount of time
    public static void pseudorandomSleep(double inTimeRange, double inMinimumSeconds) throws InterruptedException
    {
        TimeUnit.SECONDS.sleep((int)((Math.random() * inTimeRange) + inMinimumSeconds));
    }
}
