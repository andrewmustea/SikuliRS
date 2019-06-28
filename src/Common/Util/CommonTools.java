package Common.Util;

import org.sikuli.script.FindFailed;
import org.sikuli.script.Location;
import org.sikuli.script.Screen;

import Common.Enums.ClickType;

public class CommonTools {

    // Determines the type of click to be used
    public static void Click(Location inLocation, ClickType inClickType) throws FindFailed
    {
        Screen screen = new Screen();

        switch(inClickType)
        {
            case LEFT :
                screen.click(inLocation);
                break;

            case RIGHT :
                screen.rightClick(inLocation);
                break;

            case DOUBLE :
                screen.doubleClick(inLocation);
                break;
        }
    }
}
