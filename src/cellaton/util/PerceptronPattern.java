package cellaton.util;

/**
 * Created by Woodbin on 30.11.2014.
 * Crate class for perceptron teaching
 */
public class PerceptronPattern {
    public int[] ys;
    public int[][] X;


    public PerceptronPattern(int _numberOfPatterns, int _lengthOfPattern){
        ys = new int[_numberOfPatterns];
        X = new int[_numberOfPatterns][_lengthOfPattern];



    }

    public PerceptronPattern(){
        ys = new int[8];
        X = new int[8][4];
    }

    public int[] getYs(){return ys;}
    public int[][] getX(){return X;}


}
