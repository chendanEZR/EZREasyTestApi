package utils;

import java.util.Locale;
import java.util.ResourceBundle;

public class ConUrlUtil {

    private static ResourceBundle bundle  = ResourceBundle.getBundle("application", Locale.CHINA);
    public static String getUrl(String key){
        String address = bundle.getString(key);
        return address;
    }
}
