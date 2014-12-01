package cellaton;

import cellaton.util.AutomataActions;
import cellaton.util.VisualAutomata;
import cellaton.util.Perceptron;
import cellaton.util.TeachingPattern;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Woodbin on 30.11.2014.
 */
public class ElementaryAutomaton implements VisualAutomata,AutomataActions {

    //TODO Elemental Automaton

    private int[] space;
    private int[] undoSpace;
    private int rule;
    private Perceptron processer;
    private boolean perceptronReady;
    private boolean bordersContinuous;

    public ElementaryAutomaton(){
        new ElementaryAutomaton(256,90,false);           //basic elementary automaton with rule 90 on 256 size field
    }

    private ElementaryAutomaton(int _size, int _rule, boolean _bordersContinuous){
        space = new int[_size];
        undoSpace = space;
        rule = _rule;
        bordersContinuous = _bordersContinuous;

        processer = new Perceptron();
        processer.teach(ruleToPatterns(rule));
        perceptronReady = true;
    }

    private TeachingPattern ruleToPatterns(int _rule){
        TeachingPattern pc = new TeachingPattern(8,4);

        //TODO make converting from rule to perceptron pattern

        return pc;
    }

    public void iterate(){
        if(perceptronReady){
            int size = space.length;
            int[] newGeneration = new int[size];
            if(bordersContinuous){
                for(int i = size-1; i<size*2;i++){
                    int[] cells = {space[(i-1)%size],space[i%size],space[(i+1)%size]};
                    newGeneration[i%size] = processer.respond(cells);
                }
            }
            if(!bordersContinuous){
                int[] cells = {space[0],space[1],space[2]};
                newGeneration[0]= processer.respond(cells);
                for(int i = 1; i<size-1;i++) {
                    cells = new int[]{space[i-1],space[i],space[i+1]};
                    newGeneration[i]=processer.respond(cells);

                }
                cells = new int[]{space[size-3],space[size-2], space[size-1]};
                newGeneration[size-1] = processer.respond(cells);
            }
            undoSpace = space;
            space = newGeneration;
        }
    }

    public int[][] getCellStates() {
        int[][] ret = new int[1][space.length];
        ret[1] = space;
        return ret;
    }

    public ArrayList<Color> getColorScheme(){
        ArrayList<Color>  ret = new ArrayList<Color>();
        ret.add(Color.BLACK);
        ret.add(Color.WHITE);
        return ret;
    }

    public void undo(){

    }

    public void clear(){
        space = new int[space.length];
    }


}
