package cellaton;

import cellaton.util.ErrorCode;

import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by Woodbin on 30.11.2014.
 */
public class DebugCore {

    private static DebugCore instance = new DebugCore();
    private static boolean consoleExists;
    private static MainWindow windowReference;


    public static DebugCore getInstance() { return instance;}

    public static void debugOut(String message) {
        processOut(message);
    }

    public static void debugOut(int[][] array){
        printArray(array);
    }

    public static void debugIn(String command) {
        processIn(command);
    }

    public static void setWindowReference(MainWindow _mw){
        windowReference = _mw;
        consoleExists = true;
    }

    private DebugCore(){};

    private static void processOut(String message){

        Date date = new Date();

        Timestamp timestamp = new Timestamp(date.getTime());

        System.out.println("["+ timestamp.toString()+"] "+message);
        if(consoleExists)windowReference.forwardToConsole("["+ timestamp.toString()+"] "+message);


    }

    private static void processIn(String message){
        //TODO console command processing
    }

    public static String getErrorMessage (ErrorCode errorCode){
        String mes = new String();

        switch (errorCode){
            case OKAY: mes = "Everything went better than expected.";
                break;
            case IMAGENAMEEXISTS: mes = "Image with given name already exists!";
                break;
            case FILEDOESNTEXIST: mes = "Specified file doesn't exist!: ";
                break;
            default: mes = "UNKNOWN ERRORCODE";
        }


        //TODO ErrorCode generation

        return mes;
    }

    private static void printArray(int[][] pole){
        for(int i=0; i<pole.length;i++) {
            String line = new String();
            for (int j = 0; j < pole[i].length; j++) {
                line += pole[i][j] + "-";
            }
            DebugCore.debugOut(line);
        }
    }



}
