package common;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import configs.HeadersCookiesConfig;
import io.restassured.http.Cookies;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import utils.ActDateUtil;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class CaseResponse {
   private static Cookies cookies = HeadersCookiesConfig.getPCCookies();
   private static Map headers = HeadersCookiesConfig.getPcHeaders();

   //通过ID获取get方法的，响应值
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
    //通过ID和活动名称，获取get的响应
    public static Response pcGetChangeActName(int id,String ActName){
        JSONObject requestInfo =CaseRequestInfo.getRequestInfo(id);
        String testURL =requestInfo.getString("testURL");
        String paramsStr=requestInfo.getString("requestParams");

        JSONObject requestParams = JSON.parseObject(paramsStr);
        requestParams.put("ActName",ActName);

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

    public static Response pcPosByActId(int id,int actId){
        JSONObject requestInfo =CaseRequestInfo.getRequestInfo(id);
        String testURL = requestInfo.getString("testIRL");
        String paramsStr=requestInfo.getString("requestParams");

        JSONObject requestParams = JSON.parseObject(paramsStr);
        requestParams.put("Id",actId);

        Map<String,?> paramsMap = JSON.parseObject(requestParams.toJSONString());
        Response response=given().headers(headers).cookies(cookies)
                .contentType("application/json")
                .body(paramsMap)
                .when().post(testURL)
                .then().extract().response();

        return response;
    }

    //创建活动时活动名称为指定的活动名称
    public static Response pcPosChangeActName(int id,String ActName){
        JSONObject requestInfo =CaseRequestInfo.getRequestInfo(id);
        String testURL = requestInfo.getString("testURL");
        String paramsStr=requestInfo.getString("requestParams");

        JSONObject requestParams = JSON.parseObject(paramsStr);

        requestParams.put("ActName",ActName);
        Map<String,?> paramsMap = JSON.parseObject(requestParams.toJSONString());
        Response response=given().headers(headers).cookies(cookies)
                .contentType("application/json")
                .body(paramsMap)
                .when().post(testURL)
                .then().extract().response();

        return response;
    }

}
