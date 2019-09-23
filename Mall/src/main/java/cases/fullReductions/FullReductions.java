package cases.fullReductions;

import common.CaseResponse;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;

public class FullReductions {
    @Test(description = "创建倍数满减")
    public void createFullReduction001(){
        Response response = CaseResponse.pcPos(7);
        response.then().statusCode(200).body("Data",equalTo(true));
    }

    @Test(description = "启动满减活动")
    public void enableFullReduction(){
        Response response = CaseResponse.pcPos(8);
        response.then().statusCode(200).body("Data.IsDisabled",equalTo(false));

    }
}
