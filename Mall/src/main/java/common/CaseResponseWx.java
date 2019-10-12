package common;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import configs.HeadersCookiesConfig;
import io.restassured.response.Response;


import java.util.Map;

import static io.restassured.RestAssured.given;

public class CaseResponseWx {
    private static Map headers = HeadersCookiesConfig.getWxHeader();

    public static  Response wxGet(int id)  {
        JSONObject requestInfo =CaseRequestInfo.getRequestInfo(id);
        String testURL =requestInfo.getString("testURL");
        String paramsStr=requestInfo.getString("requestParams");

        JSONObject requestParams = JSON.parseObject(paramsStr);

        Map<String,?> paramsMap = JSON.parseObject(requestParams.toJSONString());
        Response response=given().headers(headers).queryParams(paramsMap)
                .get(testURL).then().extract().response();


        return response;
    }
}
