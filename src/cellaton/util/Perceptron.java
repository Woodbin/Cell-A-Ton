package cellaton.util;
import cellaton.DebugCore;
/**
 * Created by Woodbin on 30.11.2014.
 */
public class Perceptron {

    private int[] weights;
    private int threshold;


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





}
