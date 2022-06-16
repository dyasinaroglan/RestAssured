package restAssuredTraining.postRequest17;

import java.util.HashMap;

public class FakeDataTest1 {

    public HashMap<String,Object> setUpExpectedData(){
        HashMap<String,Object> bodyRequest = new HashMap<>();
        bodyRequest.put("name","batch30");
        bodyRequest.put("salary","123000");
        bodyRequest.put("age","20");

        HashMap<String,Object> expectedData = new HashMap<>();
        expectedData.put("statusCode",200);
        expectedData.put("status","success");
        expectedData.put("data",bodyRequest);
        expectedData.put("message","Successfully! Record has been added.");
        return expectedData;
    }

    public HashMap<String,Object> setUpRequestBody(){
        HashMap<String,Object> setUpBodyRequest = new HashMap<>();
        setUpBodyRequest.put("name","batch30");
        setUpBodyRequest.put("salary","123000");
        setUpBodyRequest.put("age","20");

        return setUpBodyRequest;
    }
}
