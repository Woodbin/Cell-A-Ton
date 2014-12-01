package cellaton;

import cellaton.util.Automaton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
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
    private JTextArea consoleOutput;
    private JTextField commandTextField;
    private JMenuBar menuBar;
    private Automaton automaton;

    private IGEX igex;

    private int cellSize = 4;           //4pix default
    private boolean igexState = false; //gifAndImageExporter
    private boolean igexAnimation = false; //if animation export is on
    private int stateBrush = 1;


    /**
     * Creates Window and packs
     */
    private void create(){
        frame = new JFrame("MainWindow");
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        //TODO EVENTS
        commandTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode()==KeyEvent.VK_ENTER) DebugCore.debugIn(commandTextField.getText());

                super.keyPressed(e);
            }
        });

        DebugCore.setWindowReference(this);

        buildMenu();
        frame.setJMenuBar(menuBar);

        frame.pack();
        frame.setVisible(true);
    }

    /**
     * Constructor
     */
    public MainWindow(){
        create();

    }



    /**
     * Sets current automaton
     * @param _va class implementing
     */
    public void setAutomaton(Automaton _va){
        automaton = _va;
    }


    /**
     * Draws automaton grid to graphical element
     */
    private void draw(Graphics g, int size, boolean drawGrid){
        int[][] cells = automaton.getCellStates();
        ArrayList<Color> colors = automaton.getColorScheme();
        int sizeX = cells[0].length;
        int sizeY = cells.length;
        Color c;
        for(int i = 0; i<sizeY;i++){
            for(int j = 0; j<sizeX;j++){
                if(cells[i][j]==0) c = Color.BLACK;
                else c = colors.get(cells[i][j]-1);
                g.setColor(c);
                g.fillRect(i*size,j*size,size,size);
               // if(igexAnimation)             //TODO IGEX shutter
            }
        }
        if(drawGrid&&(size>8)) drawLineGrid(g,cells.length);
    }

    private void drawLineGrid(Graphics g, int count){
        Color lineColor = Color.DARK_GRAY;
        g.setColor(lineColor);
        for(int i = 1; i < count-1; i++){
            g.drawLine(i*cellSize,0,i*cellSize,cellSize*count);
            g.drawLine(0,i*cellSize,cellSize*count,i*cellSize);
        }
    }

    public void forwardToConsole(String message){
        consoleOutput.append(message + "\n");
    }

    /**
     *  Draws a single cell to graphics component
     * @param g graphics
     * @param colorId id of state
     * @param x coordinates in state table
     * @param y -''-
     */
    private void drawCell(Graphics g, int colorId, int x, int y){
        Color c = automaton.getColorScheme().get(colorId - 1);
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
