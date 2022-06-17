package restAssuredTraining.testData;

import org.json.simple.JSONObject;

import java.util.HashMap;

public class HerOkuAppTestData {

    public HashMap<String,Object> setupTestData(){
        HashMap<String,Object> dates = new HashMap<>();
        dates.put("checkin","2020-09-09");
        dates.put("checkout","2021-01-10");

        HashMap<String,Object> expectedRequest = new HashMap<>();
        expectedRequest.put("firstname","SelimAq");
        expectedRequest.put("lastname","Akıp");
        expectedRequest.put("totalprice", 18114);
        expectedRequest.put("depositpaid",true);
        expectedRequest.put("bookingdates",dates);

        return expectedRequest;
    }
}
