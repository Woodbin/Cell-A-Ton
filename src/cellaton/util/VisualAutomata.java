package cellaton.util;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Woodbin on 1.12.2014.
 */
public interface VisualAutomata {


    public int[][] getCellStates();
    public ArrayList<Color> getColorScheme();
    public void wasEdited();

}
