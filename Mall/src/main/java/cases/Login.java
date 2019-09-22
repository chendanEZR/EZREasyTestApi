package cases;


import com.alibaba.fastjson.JSONObject;
import common.CaseRequestInfo;
import configs.HeadersCookiesConfig;
import io.restassured.RestAssured;
import io.restassured.http.Cookie;
import io.restassured.http.Cookies;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.CookiesUtil;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import static io.restassured.RestAssured.given;
import static io.restassured.config.RedirectConfig.redirectConfig;

public class Login {
    Map headers = HeadersCookiesConfig.getPcHeaders();

   @BeforeClass
     public void setURL(){
       ResourceBundle bundle  = ResourceBundle.getBundle("application", Locale.CHINA);
       RestAssured.baseURI = bundle.getString("pc.url");
   }

    @Test(description = "id=1")
    public void getLoginCookie()  {

        JSONObject jsonResult = CaseRequestInfo.getRequestInfo(29);
        String testURL =jsonResult.getString("baseURI");
        Response response =given().headers(headers).when().get(testURL).then().statusCode(200).extract().response();

        Map<String,String> cookie = response.getCookies();

        Map map = CookiesUtil.getCookieAsMap();
        map.put("Login",cookie);
        CookiesUtil.setCookie(map);
    }


    @Test(description = "id=2")
    public void login()  {



        JSONObject jsonObject = CookiesUtil.getCookieAsJson();
        String value1 = jsonObject.getJSONObject("Login").getString("ASP.NET_SessionId");
        // System.out.println("value1:"+value1);
        Cookie cookie1 = new Cookie.Builder("ASP.NET_SessionId",value1).setComment("comment 1").build();
        String value2 = jsonObject.getJSONObject("Login").getString("login_uid");
        // System.out.println("value2:"+value2);
        Cookie cookie2 = new Cookie.Builder("login_uid",value2).setComment("comment 2").build();

        Cookies cookies = new Cookies(cookie1,cookie2);

        JSONObject jsonResult = CaseRequestInfo.getRequestInfo(30);
        String testURL =jsonResult.getString("baseURI");

        Response response = given().headers(headers)
                .cookies(cookies).contentType("application/x-www-form-urlencoded; charset=UTF-8").
                        param("LoginType","0").
                        param("UserCode","ezp_mall").
                        param("UserPwd","202cb962ac59075b964b07152d234b70").
                        param("ValiCode").
                        when().post(testURL).
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

    @Test(description = "id=3,登录品牌")
    public void loginWithBrandid(){
        headers.put("Referer","http://test.ezrpro.com:8088/");
        headers.put("Upgrade-Insecure-Requests","1");

//       CommonModel commonModel = CaseSqlSentences.selectOne(3);
//       JSONObject resultJson = CaseJSonResult.getJsonResult(commonModel);
        JSONObject jsonObject = CookiesUtil.getCookieAsJson();
        String value1 = jsonObject.getJSONObject("Login").getString("ASP.NET_SessionId");
        // System.out.println("value1:"+value1);
        Cookie cookie1 = new Cookie.Builder("ASP.NET_SessionId",value1).setComment("comment 1").build();
        String value2 = jsonObject.getJSONObject("Login").getString("login_uid");
        // System.out.println("value2:"+value2);
        Cookie cookie2 = new Cookie.Builder("login_uid",value2).setComment("comment 2").build();
        String value3 = jsonObject.getJSONObject("Login").getString("login_sid");
        // System.out.println("value3:"+value3);
        Cookie cookie3 = new Cookie.Builder("login_sid",value3).setComment("comment 3").build();

        Cookies cookies = new Cookies(cookie1,cookie2,cookie3);

        JSONObject jsonResult = CaseRequestInfo.getRequestInfo(31);
        String testURL =jsonResult.getString("baseURI");


        given().config(RestAssured.config().redirect(redirectConfig().followRedirects(false)))
                .headers(headers).cookies(cookies).get(testURL).then().statusCode(302).extract().response();
//     Map<String,String> cookies1 = response.cookies();
//       for(String key:cookies1.keySet()){
//           System.out.println("Barndidkey："+key+"value:"+cookies1.get(key));
//       }




    }
    @Test(description = "id=4")
    public void loginWithToken(){



        JSONObject jsonObject = CookiesUtil.getCookieAsJson();
        String tokenId= jsonObject.getJSONObject("Login").getString("login_sid");

        JSONObject jsonResult = CaseRequestInfo.getRequestInfo(32);
        String testURL =jsonResult.getString("baseURI");

        Response response= given().config(RestAssured.config().redirect(redirectConfig().followRedirects(false)))
                .headers(headers)

                .param("tokenId",tokenId).get(testURL).then().
                        statusCode(302).extract()
                .response();

        Map<String,String> cookies4 = response.getCookies();
//     for(String key:cookies4.keySet()){
//         System.out.println(key+":"+cookies4.get(key));
//     }
        Map map = CookiesUtil.getCookieAsMap();
        map.put("All",cookies4);
        CookiesUtil.setCookie(map);

    }


}
