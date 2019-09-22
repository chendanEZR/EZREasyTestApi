package common;

import com.alibaba.fastjson.JSONObject;
import models.CommonModel;

public class CaseJSonResult {

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

    public static JSONObject getJsonResult(Integer id){

         CommonModel commonModel = CaseSqlSentences.selectOne(id);
        JSONObject jsonResult = CaseJSonResult.dealJsonResult(commonModel);
        return jsonResult;

    }

    public static void main(String[] args) {


        JSONObject jsonObject =getJsonResult(1);
        System.out.println(jsonObject.getString("baseURI"));

    }

    }

