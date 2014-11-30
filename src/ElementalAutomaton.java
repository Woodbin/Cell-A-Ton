import util.Perceptron;
import util.PerceptronPattern;

import java.util.ArrayList;

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

    private PerceptronPattern ruleToPatterns(int _rule){
        PerceptronPattern pc = new PerceptronPattern(8,4);

        //TODO make converting from rule to perceptron pattern

        return pc;
    }







}
