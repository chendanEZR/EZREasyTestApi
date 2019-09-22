package common;

import com.alibaba.fastjson.JSONObject;
import configs.HeadersCookiesConfig;
import io.restassured.RestAssured;
import io.restassured.http.Cookie;
import io.restassured.http.Cookies;
import io.restassured.response.Response;
import utils.CookiesUtil;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import static io.restassured.RestAssured.given;

public class Login {
     Map headers = HeadersCookiesConfig.getPcHeaders();

    public void login(String username,String password){
        setLoginCookie();
        setAllCookie(username,password);

    }

    public void setLoginCookie()  {
        ResourceBundle bundle  = ResourceBundle.getBundle("application", Locale.CHINA);
        RestAssured.baseURI = bundle.getString("pc.url");
        Response response =given().headers(headers).when().get("/").then().statusCode(200).extract().response();

        Map<String,String> cookie = response.getCookies();

        Map map = CookiesUtil.getCookieAsMap();
        map.put("Login",cookie);
        CookiesUtil.setCookie(map);
    }

    public void setAllCookie(String username,String password){
        ResourceBundle bundle  = ResourceBundle.getBundle("application", Locale.CHINA);
        RestAssured.baseURI = bundle.getString("pc.url");
        JSONObject jsonObject = CookiesUtil.getCookieAsJson();
        String value1 = jsonObject.getJSONObject("Login").getString("ASP.NET_SessionId");
        // System.out.println("value1:"+value1);
        Cookie cookie1 = new Cookie.Builder("ASP.NET_SessionId",value1).setComment("comment 1").build();
        String value2 = jsonObject.getJSONObject("Login").getString("login_uid");
        // System.out.println("value2:"+value2);
        Cookie cookie2 = new Cookie.Builder("login_uid",value2).setComment("comment 2").build();

        Cookies cookies = new Cookies(cookie1,cookie2);

        Response response = given().headers(headers)
                .cookies(cookies).contentType("application/x-www-form-urlencoded; charset=UTF-8").
                        param("LoginType","0").
                        param("UserCode",username).
                        param("UserPwd",password).
                        param("ValiCode").
                        when().post("/Login/login").
                        then().statusCode(200).extract().response();


        Map<String,String> cookie3 = response.getCookies();
        Map  newmap  = new HashMap();
        //将获得的
        newmap.put("login_sid",cookie3.get("login_sid"));

        Map map = CookiesUtil.getCookieAsMap();
        Map map2 =(Map)map.get("Login");
        map2.put("login_sid",cookie3.get("login_sid"));
        map.put("Login",map2);
        CookiesUtil.setCookie(map);

    }
}
