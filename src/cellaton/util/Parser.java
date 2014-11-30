package cellaton.util;

import java.io.FileInputStream;
import java.util.ArrayList;

/**
 * Created by Woodbin on 30.11.2014.
 */
public class Parser {
    private static Parser ourInstance = new Parser();

    public static Parser getInstance() {
        return ourInstance;
    }

    private Parser() {
    }

    public ArrayList<Integer> stringToInts(String s){
        ArrayList<Integer> numera = new ArrayList<Integer>();
        for(int i = 0; i<s.length(); i++){

            int temp=Character.getNumericValue(s.charAt(i));
            if((temp>=0)&&(temp<=8)) numera.add(temp);
        }

        return numera;
    }

    public ArrayList<Rule> streamToRules(FileInputStream fis){
        ArrayList<Rule> ret = new ArrayList<Rule>();
        //TODO - XML parsing?

        return ret;
    }



}
