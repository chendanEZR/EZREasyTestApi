package common.Busi;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import common.CaseRequestInfo;
import configs.HeadersCookiesConfig;
import io.restassured.http.Cookies;
import io.restassured.response.Response;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static io.restassured.RestAssured.given;

public class fullReduction {
    private static Cookies cookies = HeadersCookiesConfig.getPCCookies();
    private static Map headers = HeadersCookiesConfig.getPcHeaders();
    public static Response changefullReductionActId(int id, int actId){
        JSONObject requestInfo = CaseRequestInfo.getRequestInfo(id);
        String testURL = requestInfo.getString("testURL");
        String paramsStr=requestInfo.getString("requestParams");

        JSONObject requestParams = JSON.parseObject(paramsStr);
        requestParams.put("Id",actId);
       String WxOpenLink= requestParams.getString("WxOpenLink");
      //把actid值替换成输入的值
       WxOpenLink=changeActID(WxOpenLink,actId);
        requestParams.put("WxOpenLink",WxOpenLink);

      String WxLink =requestParams.getString("WxLink");
      WxLink =changeActID(WxLink,actId);
      requestParams.put("WxLink",WxLink);

      String Link =requestParams.getString("Link");
      Link = changeActID(Link,actId);
      requestParams.put("Link",Link);

        Map<String,?> paramsMap = JSON.parseObject(requestParams.toJSONString());
        Response response=given().headers(headers).cookies(cookies)
                .contentType("application/json")
                .body(paramsMap)
                .when().post(testURL)
                .then().extract().response();

        return response;
    }
    public static String changeActID(String value,int actId){

        String replace="actId="+actId;  //需要替换的值
        String reg = "actId=\\d+";  //正则表达式
        Pattern pattern =Pattern.compile(reg);
        Matcher matcher =pattern.matcher(value);
        value =matcher.replaceAll(replace); //替换所有匹配的值
        return  value;
    }
}
