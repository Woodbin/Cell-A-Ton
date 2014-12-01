package cellaton;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

import cellaton.util.AutomataActions;
import cellaton.util.Rule;
import cellaton.util.VisualAutomata;

/**
 * Created by Woodbin on 30.11.2014.
 */
public class MultistateAutomaton implements VisualAutomata, AutomataActions{

    private int size = 16;
    private int[][] cellStates = new int[16][16];
    private int[][] cellMoores = new int[16][16];
    private int[][] undoCellStates = new int[16][16];
    private int[][] undoCellMoores = new int[16][16];

    private int generation = 0;

    private ArrayList<Rule> rules = new ArrayList();

    private boolean edited = true;
    private boolean bordersContinuous = true; //TODO Implement MultistateAutomaton for closed borders

    // ~~~~~ CONSTRUCTOR ~~~~~
    public MultistateAutomaton(int r){
        size = r;
        initialization();
    }


    // ~~~~~ THE MAIN UPDATE METHOD ~~~~~

    /** The Automata
     * The big, messy, ugly thing which eats numbers and turns them to life .
     * Also exports to GIF when IGEX turned on.
     *
     */
    public void iterate() {
        /*System.out.println("Start");
        System.out.println("States: ");
        printArray(cellStates);             //debug purpose messages
        System.out.println("Moores: ");
        printArray(cellMoores);*/
        if(edited) updateMoores();
        // printArray(cellMoores);
        undoCellStates = cellStates;
        undoCellMoores = cellMoores;
        int[][] newStates = new int[size][size];        //TODO perhaps optimize as newStates = cellStates?
        for(int i = 0; i< size; i++){
            for(int j = 0; j< size;j++){
                newStates[i][j] = cellStates[i][j];
            }
        }
        int[][] newMoores = new int[size][size]; //TODO perhaps optimize as newMoores = cellMoores?
        for(int i = 0; i< size; i++){
            for(int j = 0; j< size;j++){
                newMoores[i][j] = cellMoores[i][j];
            }
        }
        for(int i = size; i<2* size;i++){
            for(int j = size; j<2* size;j++){
                if(cellStates[i% size][j% size]!=0){
                    boolean changed = false;
                    boolean survived = false;
                    boolean died = false;
                    liveloop: for(Integer l: rules.get(cellStates[i% size][j% size]-1).getLive()){
                        // System.out.print("l: "+l);            //debug purpose message
                        if((cellMoores[i% size][j% size]==l)){
                            newStates[i% size][j% size] = cellStates[i% size][j% size];
                            died = false;
                            break liveloop;
                        }
                        else{
                            newStates[i% size][j% size]= rules.get(cellStates[i% size][j% size]-1).getDieInto();
                            died = true;
                        }
                    }
                    if(died)changeMoore(newMoores,i,j,false);
                }
                else{
                    boolean changed = false;
                    int newRule = genetics(i,j);
                    reviveloop:for(Integer r : rules.get(newRule-1).getRevive()){
                        // System.out.print("r: "+r);            //debug purpose message
                        if(!changed){
                            if(cellMoores[i% size][j% size]==r){
                                newStates[i% size][j% size] = newRule;
                                changeMoore(newMoores,i,j,true);        //TODO FIX
                                changed = true;
                                break reviveloop;
                            }
                        }
                    }
                }
            }
        }
        cellMoores = newMoores;
        cellStates = newStates;
        edited=false;
        //if(gexState) gex.gifExport();     //TODO Implement IGEX to MultistateAutomaton.update()
        generation++;
    }

    private void rulesInitialization(){
        rules.add(new Rule(new ArrayList<Integer>(Arrays.asList(2,3)), new ArrayList<Integer>(Arrays.asList(3))));                   //Conway's Game of life sa Default rule
        rules.add(new Rule(new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5)), new ArrayList<Integer>(Arrays.asList(3)), 0, new Color(200, 255, 200)));   //Maze
        rules.add(new Rule(new ArrayList<Integer>(Arrays.asList(2, 3)), new ArrayList<Integer>(Arrays.asList(3, 6)), 0, new Color(255, 0, 0)));             //High Life
        rules.add(new Rule(new ArrayList<Integer>(Arrays.asList(3,5,8)), new ArrayList<Integer>(Arrays.asList(3, 7, 8)), 3, new Color(128,128, 55)));       //Dunno what
        rules.add(new Rule(new ArrayList<Integer>(Arrays.asList(3, 4, 5, 7, 8)), new ArrayList<Integer>(Arrays.asList(3, 6, 7, 8)), 0, Color.CYAN));        //Day and Night
        rules.add(new Rule(new ArrayList<Integer>(Arrays.asList(2, 3, 4, 5)), new ArrayList<Integer>(Arrays.asList(4, 5, 6, 7, 8)), 0, new Color(0, 0, 128)));  //Walled Cities

    }

    private void initialization(){
        rulesInitialization();
        clear();
        undoCellStates=cellStates;
        undoCellMoores=cellMoores;
    }

    public void clear(){
        cellMoores = new int[size][size];
        cellStates = new int[size][size];
     /*   for(int i = 0; i< size; i++){
            for(int j = 0; j< size; j++){
                cellMoores[i][j] = 0;
                cellStates[i][j] = 0;
            }
        }*/
    }

    public void undo(){
        cellStates = undoCellStates;
        cellMoores = undoCellMoores;
    }

    /** Returns the size of grid
     *
     * @return grid size
     */
    public int getSize(){
        return size;
    }
    /** Sets the size of grid
     *
     * @param r gridsize
     */
    public void setSize(int r){
        size = r;
    }



    public void wasEdited(){
        edited = true;
    }

    /** Genetics
     *  This will calculate new rule from it's parents. At this moments it only determines the dominant rule which is then used.
     * @param x
     * @param y
     * @return      rule
     */
    private int genetics(int x, int y){
        int[] parents = getDominant(x,y);
        //TODO Genetics - the MENDEL module :3

        return parents[0];
        //return 1;
    }

    private void updateMoores(){
        int[][] newMoores = new int[size][size];
        for(int i = 0; i< size; i++){
            for(int j = 0; j< size;j++){
                newMoores[i][j] = 0;
            }
        }

        for(int i = size; i<2* size;i++){
            for(int j = size; j<2* size;j++){
                if(cellStates[i% size][j% size]!=0) changeMoore(newMoores,i,j,true);
                //if(cellStates[i%size][j%size]==0) changeMoore(newMoores,i,j,false);
            }
        }

        cellMoores = newMoores;
        edited = false;
    }


    /** Does shenanigans to moore neighbourhood table (updates number of neighbours)
     *
     * @param _moores   table
     * @param x x coordinate of cell
     * @param y y coordinate of cell
     * @param adding true = we add; false = we subtract
     */
    public void changeMoore(int [][] _moores,int x, int y, boolean adding){
        int value = -1;
        if(adding) value = 1;
        //System.out.print(hodnota);            //debug purpose message

        if((_moores[(x-1)% size][(y-1)% size]!=0)||adding) _moores[(x-1)% size][(y-1)% size] += value;
        if((_moores[(x)% size][(y-1)% size]!=0)||adding) _moores[(x)% size][(y-1)% size] += value;
        if((_moores[(x+1)% size][(y-1)% size]!=0)||adding) _moores[(x+1)% size][(y-1)% size] += value;
        if((_moores[(x-1)% size][(y)% size]!=0)||adding) _moores[(x-1)% size][(y)% size] += value;
        if((_moores[(x+1)% size][(y)% size]!=0)||adding) _moores[(x+1)% size][(y)% size] += value;
        if((_moores[(x-1)% size][(y+1)% size]!=0)||adding) _moores[(x-1)% size][(y+1)% size] += value;
        if((_moores[(x)% size][(y+1)% size]!=0)||adding) _moores[(x)% size][(y+1)% size] += value;
        if((_moores[(x+1)% size][(y+1)% size]!=0)||adding) _moores[(x+1)% size][(y+1)% size] += value;
    }

    /** Aaaaaaand it's gone.
     *
     * @param which
     */
    public void vaporizeRule(int which){
        rules.remove(which-1);
        for(int i = 0; i< size; i++){
            for(int j = 0; j< size; j++){
                if(cellStates[i][j]==which) cellStates[i][j]=0;
            }
        }
        undoCellStates = cellStates;
        undoCellMoores = cellMoores;
        for(Rule r : rules){
            if(r.getDieInto()==which) r.setDieInto(0);
        }

    }

    /** Gets dominant rule
     *  It's messy. It's ugly. I am not even sure if it works. But the automata works with it so far.
     * @param x
     * @param y
     * @return
     */
    public int[] getDominant(int x, int y){
        int[] moores = new int[8];
        int index = 0;

        moores[0] = cellStates[(size +x-1)% size][(size +y-1)% size];
        moores[1] = cellStates[(size +x)% size][(size +y-1)% size];
        moores[2] = cellStates[(size +x+1)% size][(size +y-1)% size];
        moores[3] = cellStates[(size +x-1)% size][(size +y)% size];
        moores[4] = cellStates[(size +x+1)% size][(size +y)% size];
        moores[5] = cellStates[(size +x-1)% size][(size +y+1)% size];
        moores[6] = cellStates[(size +x)% size][(size +y+1)% size];
        moores[7] = cellStates[(size +x+1)% size][(size +y+1)% size];

        int[] ret = {0,0};
        int[] occur = {0,0};
        int[] hashmap = new int[rules.size()+1];
        for(int i =0; i<moores.length; i++) if(moores[i]!=0)hashmap[moores[i]]++;


        for(int i=0;i<hashmap.length;i++){
            if(occur[0]<hashmap[i]) ret[0] = i;
        }
        hashmap[ret[0]] = 0;
        for(int i=0;i<hashmap.length;i++){
            if(occur[1]<hashmap[i]) ret[1] = i;
        }

        if(ret[0]==0)ret[0]=1;
        if(ret[1]==0) ret[1]=ret[0];
        return ret ;



    }

    public int[][] getCellStates(){
        return cellStates;
    }
    public ArrayList<Color> getColorScheme() {
        ArrayList<Color> ret = new ArrayList<Color>();
        for(int i = 0; i<rules.size();i++){
            ret.add(rules.get(i).getColor());
        }
        return ret;
    }
}
