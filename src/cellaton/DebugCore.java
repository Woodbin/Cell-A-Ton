package cellaton;

import cellaton.util.ErrorCode;

/**
 * Created by Woodbin on 30.11.2014.
 */
public class DebugCore {

    private static DebugCore instance = new DebugCore();
    private static boolean consoleExists;
    private static MainWindow windowReference;


    public static DebugCore getInstance() { return instance;}

    public static void debugOut(String message) {
        processIn(message);
    }

    public static void debugIn(String command) {
        processOut(command);
    }

    public static void setWindowReference(MainWindow _mw){
        windowReference = _mw;
        consoleExists = true;
    }


    private static void processOut(String message){
        System.out.println(message);
        if(consoleExists)windowReference.forwardToConsole(message);


    }

    private static void processIn(String message){
        //TODO console command processing
    }

    public static String getErrorMessage (ErrorCode errorCode){
        String mes = new String();

        switch (errorCode){
            case OKAY: mes = "Everything went better than expected.";
                break;
            default: mes = "UNKNOWN ERRORCODE";
        }


        //TODO ErrorCode generation

        return mes;
    }





}
