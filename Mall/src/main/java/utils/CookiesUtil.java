package utils;


import org.yaml.snakeyaml.Yaml;
import com.alibaba.fastjson.JSONObject;
import java.io.*;
import java.net.URL;
import java.util.Map;

public class CookiesUtil {
    static URL path = ClassLoader.getSystemResource("cookies.yaml");

    static String path_str = path.toString();
    static String[] str =path_str.split("file:/");
    static String filePath = str[1];
    public static Map getCookieAsMap()  {

        Yaml yaml = new Yaml();
        File file = new File(filePath);
        FileInputStream fileInputStream =null;
        try {
            fileInputStream = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        Map map = yaml.load(fileInputStream);
        try {
            fileInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return map;
    }


    public static JSONObject getCookieAsJson()  {

        Yaml yaml = new Yaml();
        File file = new File(filePath);
        FileInputStream fileInputStream =null;
        try {
            fileInputStream = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        Map map = yaml.load(fileInputStream);
        try {
            fileInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        JSONObject jsonObject = new JSONObject(map);
        return jsonObject;
    }

    public static void setCookie(Map map) {

        Yaml yaml = new Yaml();
        File file = new File(filePath);
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(file);

        fileWriter.write(yaml.dump(map));
        fileWriter.flush();
        fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
}


    }
}
