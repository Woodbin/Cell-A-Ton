package cellaton.util;

import cellaton.DebugCore;

import java.awt.*;
import java.util.Dictionary;
import java.util.HashMap;

/**
 * Created by Woodbin on 1.12.2014.
 */
public class Content {
    private static Content ourInstance = new Content();
    private static HashMap<String,Image> imageContent;


    public static Content getInstance() {
        return ourInstance;
    }

    private Content() {
        imageContent = new HashMap<String, Image>();
    }

    public static Image getImage(String _name){
        return imageContent.get(_name);
    }

    public static boolean addImage(String _name, Image _image){
        if(imageContent.containsKey(_name)){
            DebugCore.debugOut(DebugCore.getErrorMessage(ErrorCode.IMAGENAMEEXISTS));
            return false;
        }
        imageContent.put(_name,_image);
        return true;
    }



}
