package cellaton.util;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Woodbin on 30.11.2014.
 */
public class Rule {

    private ArrayList<Integer> live;
    private ArrayList<Integer> revive;
    private int dieInto;
    private Color color;
    private String name;


    //TODO Cleanup constructors

    /**
     * Constructors! Many of them, as you can see, for different aproaches to creating new rules
     */
    /**
     * Basic constructor
     */
    public Rule(){
        live = new ArrayList<Integer>();
        revive = new ArrayList<Integer>();
        dieInto = 0;
        color = Color.GREEN;
    }


    public Rule(int l, int r){
        live = new ArrayList<Integer>(l);
        revive = new ArrayList<Integer>(r);
        dieInto = 0;
        color = Color.GREEN;

    }


    public Rule(ArrayList<Integer> l, ArrayList<Integer> r){
        live = l;
        revive = r;
        dieInto = 0;
        color = Color.GREEN;

    }
    public Rule(int d){
        live = new ArrayList<Integer>();
        revive = new ArrayList<Integer>();
        dieInto = d;
        color = Color.GREEN;

    }
    public Rule(int l, int r, int d){
        live = new ArrayList<Integer>(l);
        revive = new ArrayList<Integer>(r);
        dieInto = d;
        color = Color.GREEN;

    }
    public Rule(ArrayList<Integer> l, ArrayList<Integer> r, int d){
        live = l;
        revive = r;
        dieInto = d;
        color = Color.GREEN;
    }

    /**
     * The best constructor,  takes rulesets and other information
     * @param l array of rules to live
     * @param r array of rules to revive
     * @param d into which rule this rule dies
     * @param c Color of rule
     */
    public Rule(ArrayList<Integer> l, ArrayList<Integer> r, int d, Color c){
        live = l;
        revive = r;
        dieInto = d;
        color = c;
    }

    /**
     * Returns name
     * @return String name
     */
    public String getName(){
        return name;
    }

    /**
     * Sets rule name
     * @param _n
     */
    public void setName(String _n){
        name = _n;
    }

    public void setLive(ArrayList<Integer> _live){
        live=_live;
    }
    public void setRevive(ArrayList<Integer> _revive){
        revive=_revive;
    }


    public ArrayList<Integer> getLive(){
        return live;
    }

    public ArrayList<Integer> getRevive(){
        return revive;
    }

    public void removeLive(int a){
        live.remove((Integer) a);
    }

    public void removeRevive(int a){
        revive.remove((Integer) a);
    }


    /** Adds to "live" ruleset
     *
     * @param a number of neighbours to live
     */
    public void addLive(int a){
        live.add(a);
    }

    public void addRevive(int a){
        revive.add(a);
    }

    public int getDieInto() {
        return dieInto;
    }

    public void setDieInto(int dieInto) {
        this.dieInto = dieInto;
    }

    public void setColor(Color _b){
        color = _b;
    }

    public Color getColor(){ return color;}

}
