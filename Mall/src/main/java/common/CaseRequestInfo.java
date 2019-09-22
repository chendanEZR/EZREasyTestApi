package common;

import com.alibaba.fastjson.JSONObject;
import models.CommonModel;

public class CaseRequestInfo {
    public static JSONObject dealJsonResult(CommonModel commonModel){

        JSONObject info = new JSONObject();
        String baseURI = commonModel.getApiName();
        String requestParam = commonModel.getRequestParam();
        String requestMethod = commonModel.getRequestMethod();
        info.put("baseURI",baseURI);
        info.put("requestParam",requestParam);
        info.put("requestMethod",requestMethod);

        return info;
    }

    public static JSONObject getRequestInfo(Integer id){
        //获取请求信息，请求路径，请求参数，请求方法
        CommonModel commonModel = CaseSqlSentences.selectOne(id);
        JSONObject requestInfo = CaseJSonResult.dealJsonResult(commonModel);
        return requestInfo;

    }
/**
    public static void main(String[] args) {


        JSONObject jsonObject =getJsonResult(1);
        System.out.println(jsonObject.getString("baseURI"));

    }
 **/
}
