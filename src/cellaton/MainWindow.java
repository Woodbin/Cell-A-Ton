package cellaton;

import cellaton.util.Automaton;
import cellaton.util.Content;
import cellaton.util.ContentPipeline;
import cellaton.util.ErrorCode;

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
    private JButton clearButton;
    private JButton saveButton;
    private JButton loadButton;
    private JButton undoButton;
    private JButton iterateButton;
    private JButton iterateContinuousButton;
    private JButton settingsButton;
    private JButton closeButton;
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
                if (e.getKeyCode() == KeyEvent.VK_ENTER) DebugCore.debugIn(commandTextField.getText());

                super.keyPressed(e);
            }
        });


        buildMenu();
        frame.setJMenuBar(menuBar);

        assingIcons();

        frame.pack();
        frame.setVisible(true);
    }

    /**
     * Constructor
     */
    public MainWindow(){
        loadIcons();
        create();
        DebugCore.setWindowReference(this);
        setAutomaton(new MultistateAutomaton(16));
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

    /**
     * Draws line grid on graphical component
     * @param g Graphics
     * @param count number of lines
     */
    private void drawLineGrid(Graphics g, int count){
        Color lineColor = Color.DARK_GRAY;
        g.setColor(lineColor);
        for(int i = 1; i < count-1; i++){
            g.drawLine(i*cellSize,0,i*cellSize,cellSize*count);
            g.drawLine(0,i*cellSize,cellSize*count,i*cellSize);
        }
    }

    /**
     * Forwards text to consoleOutput
     * @param message
     */
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

    private void buildMenu(){
        //TODO Build jmenuBar here

        menuBar = new JMenuBar();
    }

    /**
     * Loads icons(images) to Content class
     */
    private void loadIcons(){
        ContentPipeline.loadImage("res/clear.png","clearButtonIcon");
        ContentPipeline.loadImage("res/close.png","closeButtonIcon");
        ContentPipeline.loadImage("res/color.png","colorButtonIcon");
        ContentPipeline.loadImage("res/export_gif.png","gifExportButtonIcon");
        ContentPipeline.loadImage("res/export_image.png","imageExportButtonIcon");
        ContentPipeline.loadImage("res/iterate.png","iterateButtonIcon");
        ContentPipeline.loadImage("res/iterate_cont.png","continuousIterateButtonIcon");
        ContentPipeline.loadImage("res/load.png","loadButtonIcon");
        ContentPipeline.loadImage("res/rule_add.png","addRuleButtonIcon");
        ContentPipeline.loadImage("res/rule_delete.png","deleteRuleButtonIcon");
        ContentPipeline.loadImage("res/rule_edit.png","editRuleButtonIcon");
        ContentPipeline.loadImage("res/save.png","saveButtonIcon");
        ContentPipeline.loadImage("res/settings.png","settingsButtonIcon");
        ContentPipeline.loadImage("res/undo.png","undoButtonIcon");
    }

    private void assingIcons(){
        clearButton.setIcon(new ImageIcon(Content.getImage("clearButtonIcon")));
        loadButton.setIcon(new ImageIcon(Content.getImage("loadButtonIcon")));
        saveButton.setIcon(new ImageIcon(Content.getImage("saveButtonIcon")));
        undoButton.setIcon(new ImageIcon(Content.getImage("undoButtonIcon")));
        iterateButton.setIcon(new ImageIcon(Content.getImage("iterateButtonIcon")));
        iterateContinuousButton.setIcon(new ImageIcon(Content.getImage("continuousIterateButtonIcon")));
        settingsButton.setIcon(new ImageIcon(Content.getImage("settingsButtonIcon")));
        closeButton.setIcon(new ImageIcon(Content.getImage("closeButtonIcon")));
    }

    private void closeButtonAction(){
        DebugCore.debugOut(DebugCore.getErrorMessage(ErrorCode.OKAY));
        System.exit(0);

    }


}
