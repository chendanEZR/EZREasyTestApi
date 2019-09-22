package configs;


import io.restassured.http.Cookie;
import io.restassured.http.Cookies;
import utils.CookiesUtil;
import com.alibaba.fastjson.JSONObject;
import java.util.HashMap;
import java.util.Map;

public class HeadersCookiesConfig {
   //获取微商城的请求头
    public static Map getH5Headers(){
        Map<String,String> headers = new HashMap<String, String>();
        headers.put("If-None-Match","269973864");
        headers.put("Accept-Language","1");
        headers.put("Upgrade-Insecure-Requests","zh-CN,zh;q=0.9");
        headers.put("Accept-Encoding","gzip, deflate");
        headers.put("User-Agent","Mozilla/5.0 (Linux; Android 7.1.1; OPPO R11s Build/NMF26X; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/57.0.2987.132 MQQBrowser/6.2 TBS/043906 Mobile Safari/537.36 micromessenger/6.6.1.1220(0x26060135) NetType/WIFI Language/zh_CN");
        headers.put("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/wxpic,image/sharpp,*/*;q=0.8");

        return headers;
    }
    //获取微商城Cookie
    public static Map getMicoMallCookies(){
        Map<String,String> cookies = new HashMap<String, String>();

        JSONObject jsonObject = CookiesUtil.getCookieAsJson();
        String appGUID = jsonObject.getJSONObject("H5").getString("appGUID");
        String connectSid = jsonObject.getJSONObject("H5").getString("connect.sid");
        cookies.put("appGUID",appGUID);
        cookies.put("connect.sid",connectSid);
        return cookies;
    }

    //获取小程序的请求头
    public static Map getWxHeader(){
        Map<String,String> headers = new HashMap<String, String>();
        headers.put("Referer","https://servicewechat.com/wx4835761ab511fbd7/devtools/page-frame.html");
        headers.put("UnionId","oBtnQt88HJ2nbbmiH4piqyBC_TfY");
        headers.put("Sign","a9ac7308443ccec6461235b7dced9f06");
        headers.put("User-Agent","Mozilla/5.0 (Linux; Android 7.1.1; OPPO R11s Build/NMF26X; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/55.0.2883.91 Mobile Safari/537.36 MicroMessenger/7.0.3.1400(0x27000334) Process/appbrand2 NetType/WIFI Language/zh_CN");
        return headers;
    }
    //获取PC请求头
    public static Map<String,String> getPcHeaders(){
        Map<String,String> headers = new HashMap<String, String>();
        headers.put("Accept","application/json, text/javascript, */*; q=0.01");
        headers.put("Accept-Encoding","gzip, deflate");
        headers.put("Accept-Language","zh-CN,zh;q=0.9");
        headers.put("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/68.0.3440.106 Safari/537.36");
        headers.put("X-Requested-With","XMLHttpRequest");
        return headers;
    }

    //获取后台登录cookie
    public  static Cookies getLoginCookies(){


        JSONObject jsonObject = CookiesUtil.getCookieAsJson();
        String login_uid = jsonObject.getJSONObject("Login").getString("login_uid");
        String ASPNET_SessionId = jsonObject.getJSONObject("Login").getString("ASP.NET_SessionId");
        Cookie cookie1 = new Cookie.Builder("login_uid",login_uid).setComment("comment 1").build();
        Cookie cookie2 = new Cookie.Builder("ASP.NET_SessionId",ASPNET_SessionId).setComment("comment 1").build();
        //Cookies cookies = new Cookies(cookie1,cookie2);
        return new Cookies(cookie1,cookie2);
    }

    //获取PC端登录后其他请求的cookie
    public static Cookies getPCCookies(){

        JSONObject jsonObject = CookiesUtil.getCookieAsJson();
        String pcl_sid = jsonObject.getJSONObject("All").getString("pcl_sid");
       // System.out.println("pcl_sid"+pcl_sid);
        Cookie cookie = new Cookie.Builder("pcl_sid",pcl_sid).setComment("comment1").build();
       // Cookies cookies = new Cookies(cookie);
        return new Cookies(cookie);
    }



    /**
    public static Map getCookies(String name){
        Map<String,String> cookies = new HashMap<String, String>();
        if(name.equals("H5")){
            JSONObject jsonObject = CookiesUtil.getCookieAsJson();
            String appGUID = jsonObject.getJSONObject("H5").getString("appGUID");
            String connectSid = jsonObject.getJSONObject("H5").getString("connect.sid");
        cookies.put("appGUID",appGUID);
        cookies.put("connect.sid",connectSid);

        }
        if(name.equals("All")){
            JSONObject jsonObject = CookiesUtil.getCookieAsJson();
            String pcl_sid = jsonObject.getJSONObject("All").getString("pcl_sid");
            cookies.put("pcl_sid",pcl_sid);
        }
        if(name.equals("Login")){
            JSONObject jsonObject = CookiesUtil.getCookieAsJson();
            String login_uid = jsonObject.getJSONObject("Login").getString("login_uid");
            String ASPNET_SessionId = jsonObject.getJSONObject("Login").getString("ASP.NET_SessionId");
            cookies.put("login_uid",login_uid);
            cookies.put("ASP.NET_SessionId",ASPNET_SessionId);
        }
        return cookies;
    }
     */
}
