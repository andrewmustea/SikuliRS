package common.util;

import org.sikuli.script.FindFailed;
import org.sikuli.script.Location;
import org.sikuli.script.Screen;

import common.enums.ClickType;

/**
 * This class holds commonly used methods.
 */
public class SharedTools
{
    /**
     * This method performs a Sikuli click based on the parameters given.
     * @param inLocation    The pixel location of the desired click.
     * @param inClickType   Type of click desired.
     * @throws FindFailed   Throws a FindFailed exception if the location is not within the screen.
     */
    public static void Click(Location inLocation, ClickType inClickType) throws FindFailed
    {
        Screen screen = new Screen();

        switch(inClickType)
        {
            case LEFT:
                screen.click(inLocation);
                break;

            case RIGHT:
                screen.rightClick(inLocation);
                break;

            case DOUBLE:
                screen.doubleClick(inLocation);
                break;
        }
    }
}
