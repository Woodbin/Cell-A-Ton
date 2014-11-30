package cellaton.util;
import cellaton.DebugCore;


/**
 * Created by Woodbin on 30.11.2014.
 */
public class Perceptron {

    private int[] weights;
    private int threshold;


    public Perceptron(){

    }




    public int respond(int[] input){
        int ret = 0;
        if(input.length!=weights.length){
            DebugCore.debugOut("Lengths of perceptron inputs and weights doesn't equal!");
        }
        else {
            for (int i = 0; i < weights.length; i++) {
                ret += input[i]*weights[i];
            }
            ret+=threshold;
            ret = (int) Math.signum(ret);
        }
        return ret;
    }

    public void teach(TeachingPattern pattern){
        weights = new int[pattern.getX()[0].length];


        //TODO Rosenblatt Teaching



        threshold = weights[0];
        int[] newweights = new int[weights.length-1];
        for(int i = 1; i<weights.length;i++){
            newweights[i-1]=weights[i];
        }


    }



}
