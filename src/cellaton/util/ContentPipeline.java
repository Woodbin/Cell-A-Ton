package cellaton.util;

import cellaton.DebugCore;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

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


    /**
     * Loads image from source file and puts it to Content class to imageContent
     * hashmap with specified name
     * @param filename
     * @param imageName
     * @return
     */
    public static boolean loadImage(String filename, String imageName){
        boolean result = false;
        try{
            Image im = ImageIO.read(new File(filename));
            if(Content.addImage(imageName,im))result=true;
        }catch (IOException e){
            DebugCore.debugOut(DebugCore.getErrorMessage(ErrorCode.FILEDOESNTEXIST)+filename);
        }
        return result;
    }

// TODO Loading text files
//TODO parsing images as State Arrays



}
