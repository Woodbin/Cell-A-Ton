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
    private boolean perceptronReady;
    private boolean bordersContinuous;

    public ElementalAutomaton(){
        new ElementalAutomaton(256,90);
    }

    private ElementalAutomaton(int _size, int _rule){
        space = new int[_size];
        rule = _rule;
        processer = new Perceptron();

        processer.teach(ruleToPatterns(rule));
        perceptronReady = true;


    }

    private TeachingPattern ruleToPatterns(int _rule){
        TeachingPattern pc = new TeachingPattern(8,4);

        //TODO make converting from rule to perceptron pattern

        return pc;
    }

    public void generate(){
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
            space = newGeneration;
        }
    }






}
