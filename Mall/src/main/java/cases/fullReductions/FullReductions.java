package cases.fullReductions;

import common.Busi.fullReduction;
import common.CaseResponse;
import common.CaseResponseWx;

import io.qameta.allure.Step;

import io.restassured.response.Response;
import org.testng.annotations.Test;
import utils.ActDateUtil;

import static org.hamcrest.CoreMatchers.equalTo;

public class FullReductions {
    int actId;
    String ActName;

    @Test(description = "创建满减",priority = 0)

    public void createFullReduction001(){
        //获取时间戳
        ActName= ActDateUtil.getCurrentTimeStamp();
        Response response = CaseResponse.pcPosChangeActName(7,ActName);
        response.then().statusCode(200).body("Data",equalTo(true));


    }

    @Test(priority = 1)
    public void searchFullReduction(){
        Response response = CaseResponse.pcGetChangeActName(10,ActName);
        //response.getBody().prettyPrint();
        actId =response.then().statusCode(200).body("Data.PagedList[0].ActName",equalTo(ActName))
                .extract().path("Data.PagedList[0].Id");

    }
    @Test(description = "启动满减活动",priority = 2)
    public void enableFullReduction(){

        Response response = fullReduction.changefullReductionActId(8,actId);

       // response.getBody().prettyPrint();
        response.then().statusCode(200).body("Data.IsDisabled",equalTo(false));

    }


    @Test(description = "商品详情页是否有满减标志",priority = 3)
    public void descFullReductionSignWX(){
        Response response = CaseResponseWx.wxGet(11);
        //response.getBody().prettyPrint();
        try {
            response.then().statusCode(200).body("Result[0].ActValues", equalTo("满100元减99元"));
        }catch (java.lang.AssertionError e){

          respondBody(response.getBody().asString());

          response.then().statusCode(200).body("Result[0].ActValues", equalTo("满100元减99元"));
        }

    }

    @Step
    public void respondBody(String Respond){
        //报告展现响应报文
    }
}
