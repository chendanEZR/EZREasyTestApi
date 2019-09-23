package common;

import com.alibaba.fastjson.JSONObject;
import models.CommonModel;

public class CaseRequestInfo {
    //获取测试地址，请求参数
    public static JSONObject dealJsonResult(CommonModel commonModel){

        JSONObject info = new JSONObject();
        String baseURL = commonModel.getTestURL();
        String baseURI =commonModel.getTestURI();
        String testURL =baseURL+baseURI;
        String RequestParams = commonModel.getRequestParams();
        String requestMethod = commonModel.getRequestMethod();
        info.put("testURL",testURL);
        info.put("requestParams",RequestParams);
        info.put("requestMethod",requestMethod);

        return info;
    }

    public static JSONObject getRequestInfo(Integer id){
        //获取请求信息，请求路径，请求参数，请求方法
        CommonModel commonModel = CaseSqlSentences.selectOne(id);
        JSONObject requestInfo =dealJsonResult(commonModel);
        return requestInfo;

    }
/**
    public static void main(String[] args) {


        JSONObject jsonObject =getJsonResult(1);
        System.out.println(jsonObject.getString("baseURI"));

    }
 **/
}
