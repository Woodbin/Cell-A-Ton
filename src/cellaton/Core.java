package cellaton;

import cellaton.util.Automaton;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Woodbin on 12.12.2014.
 */
public class Core {
    private static Core ourInstance = new Core();
    private Automaton automaton;

    public static Core getInstance() {
        return ourInstance;
    }

    private Core() {
        setAutomaton(new MultistateAutomaton(32));
    }

    public void setAutomaton(Automaton _at){
        automaton = _at;
    }

    public int[][] getStates(){
        return automaton.getCellStates();
    }

    public  void setCellState(int x, int y, int state){
        automaton.getCellStates()[x][y] = state;
    }

    public  ArrayList<Color> getColors(){
        return automaton.getColorScheme();
    }

    public  void iterate(){
        DebugCore.debugOut("Core iterating");
        automaton.iterate();
    }

}
