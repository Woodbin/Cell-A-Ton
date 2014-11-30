package cellaton;

import cellaton.util.Perceptron;
import cellaton.util.TeachingPattern;

/**
 * Created by Woodbin on 30.11.2014.
 */
public class ElementalAutomaton {

    //TODO Elemental Automaton

    private int[] space;
    private int rule;
    private Perceptron processer;


    public ElementalAutomaton(){

    }

    private ElementalAutomaton(int _size, int _rule){
        space = new int[_size];
        rule = _rule;
        processer = new Perceptron();




    }

    private TeachingPattern ruleToPatterns(int _rule){
        TeachingPattern pc = new TeachingPattern(8,4);

        //TODO make converting from rule to perceptron pattern

        return pc;
    }







}
