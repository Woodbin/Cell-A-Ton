package cellaton;

/**
 * Created by Woodbin on 12.12.2014.
 */
public class Core {
    private static Core ourInstance = new Core();
    
    public static Core getInstance() {
        return ourInstance;
    }

    private Core() {
    }
}
