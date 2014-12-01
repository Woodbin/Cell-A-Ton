package cellaton.util;

/**
 * Created by Woodbin on 1.12.2014.
 */
public class ContentPipeline {
    private static ContentPipeline ourInstance = new ContentPipeline();

    public static ContentPipeline getInstance() {
        return ourInstance;
    }

    private ContentPipeline() {
    }
}
