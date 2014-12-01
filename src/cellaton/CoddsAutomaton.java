package cellaton;

import cellaton.util.AutomataActions;
import cellaton.util.VisualAutomata;
import cellaton.util.Rule;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Woodbin on 30.11.2014.
 */
public class CoddsAutomaton implements VisualAutomata,AutomataActions {
    @Override
    public ArrayList<Color> getColorScheme() {
        return null;
    }

    @Override
    public int[][] getCellStates() {
        return new int[0][];
    }

    @Override
    public void iterate() {

    }

    @Override
    public void clear() {

    }

    @Override
    public void undo() {

    }

    //TODO Codd's Automaton

}
