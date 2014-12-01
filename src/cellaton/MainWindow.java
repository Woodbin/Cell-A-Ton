package cellaton;

import cellaton.util.AutomataActions;
import cellaton.util.VisualAutomata;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Woodbin on 30.11.2014.
 */
public class MainWindow {
    private JPanel mainPanel;
    private JFrame frame;
    private JToolBar statusBar;
    private JToolBar toolBar;
    private JPanel grid;
    private JScrollPane gridScrollPane;
    private JTabbedPane tabbedPane1;
    private JPanel tabProperties;
    private JPanel consolePanel;
    private JScrollPane consoleScrollPane;
    private JMenuBar menuBar;
    private VisualAutomata automaton;

    private IGEX igex;

    private int cellSize = 4;           //4pix default
    private boolean igexState = false; //gifAndImageExporter
    private boolean igexAnimation = false; //if animation export is on
    private int stateBrush = 1;



    private void create(){
        frame = new JFrame("MainWindow");
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        buildMenu();
        frame.setJMenuBar(menuBar);

    }

    public MainWindow(){
        create();
    }

    public void setAutomaton(VisualAutomata _va){
        automaton = _va;
    }

    private void draw(){
        int[][] cells = automaton.getCellStates();
        ArrayList<Color> colors = automaton.getColorScheme();
        Graphics g = gridScrollPane.getGraphics();
        int sizeX = cells[0].length;
        int sizeY = cells.length;
        Color c;
        for(int i = 0; i<sizeY;i++){
            for(int j = 0; j<sizeX;j++){
                if(cells[i][j]==0) c = Color.BLACK;
                else c = colors.get(cells[i][j]-1);
                g.setColor(c);
                g.fillRect(i*cellSize,j*cellSize,cellSize,cellSize);
               // if(igexAnimation)             //TODO IGEX shutter
            }
        }
        if(cellSize>8) drawLineGrid(g,cells.length);
    }

    private void drawLineGrid(Graphics g, int count){
        Color lineColor = Color.DARK_GRAY;
        g.setColor(lineColor);
        for(int i = 1; i < count-1; i++){
            g.drawLine(i*cellSize,0,i*cellSize,cellSize*count);
            g.drawLine(0,i*cellSize,cellSize*count,i*cellSize);
        }
    }

    /**
     *  Draws a single cell to graphics component
     * @param g graphics
     * @param colorId id of state
     * @param x coordinates in state table
     * @param y -''-
     */
    private void drawCell(Graphics g, int colorId, int x, int y){
        Color c = automaton.getColorScheme().get(colorId-1);
        g.setColor(c);
        g.fillRect(cellSize * x, cellSize * y, cellSize, cellSize);
    }

    private void mouseDraw(int mouseX, int mouseY){
        int[] pos = detectCoordinates(mouseX,mouseY);
        automaton.getCellStates()[pos[0]][pos[1]]=stateBrush;
        drawCell(gridScrollPane.getGraphics(), stateBrush,pos[0],pos[1]);
        //Néééé ono to takhle nejde :(

    }

    /**
     * Detects grid coordinates from mouse coordinates
     * @param mouseX self-explanatory
     * @param mouseY self-explanatory
     * @return       position vector
     */
    private int[] detectCoordinates(int mouseX, int mouseY) {
        int[] pos = new int[2];
        pos[0] = (int) Math.floor(mouseY/ cellSize);
        pos[1] = (int) Math.floor(mouseY/ cellSize);
        return pos;
    }


    private  void buildMenu(){
        //TODO Build menuBar here

        menuBar = new JMenuBar();



    }








}
