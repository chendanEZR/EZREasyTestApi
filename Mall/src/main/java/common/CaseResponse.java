package common;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import configs.HeadersCookiesConfig;
import io.restassured.http.Cookies;
import io.restassured.http.Headers;
import io.restassured.response.Response;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class CaseResponse {
   private static Cookies cookies = HeadersCookiesConfig.getPCCookies();
   private static Map headers = HeadersCookiesConfig.getPcHeaders();


    public static Response pcGet(int id){
        JSONObject requestInfo =CaseRequestInfo.getRequestInfo(id);
        String testURL =requestInfo.getString("testURL");
        String paramsStr=requestInfo.getString("requestParams");

        JSONObject requestParams = JSON.parseObject(paramsStr);

        Map<String,?> paramsMap = JSON.parseObject(requestParams.toJSONString());
        Response response=given().headers(headers).cookies(cookies).queryParams(paramsMap)
                .get(testURL).then().extract().response();


        return response;
    }

    public static Response pcPos(int id){
        JSONObject requestInfo =CaseRequestInfo.getRequestInfo(id);
        String testURL =requestInfo.getString("testURL");
        String paramsStr=requestInfo.getString("requestParams");

        JSONObject requestParams = JSON.parseObject(paramsStr);

        Map<String,?> paramsMap = JSON.parseObject(requestParams.toJSONString());
        Response response=given().headers(headers).cookies(cookies)
                .contentType("application/json")
                .body(paramsMap)
                .when().post(testURL)
                .then().extract().response();

        return response;
    }




}
