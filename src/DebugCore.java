import util.ErrorCode;

/**
 * Created by Woodbin on 30.11.2014.
 */
public class DebugCore {

    private static DebugCore instance = new DebugCore();
    private static boolean consoleExists;


    public static DebugCore getInstance() { return instance;}

    public static void debugOut(String message) {
        processIn(message);
    }

    public static void debugIn(String command) {
        processOut(command);
    }

    public static void processOut(String message){
        //TODO console message processing
    }

    public static void processIn(String message){
        //TODO console command processing
    }

    public static String getErrorMessage (ErrorCode errorCode){
        String mes = new String();

        //TODO ErrorCode generation

        return mes;
    }





}
