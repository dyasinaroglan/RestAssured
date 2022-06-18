package restAssuredTraining.postRequest21;

import io.restassured.path.json.JsonPath;
import org.json.simple.JSONObject;

import java.util.HashMap;

public class DataClass1 {

    public HashMap<String,Object> requestBodyData(){

        HashMap<String,Object> setUpData = new HashMap<>();
        setUpData.put("userId",55);
        setUpData.put("title","Tidy your room");
        setUpData.put("completed",false);

        return setUpData;
    }

    public HashMap<String,Object> expectedData(){

        HashMap<String,Object> setUpData = new HashMap<>();
        setUpData.put("userId",55);
        setUpData.put("title","Tidy your room");
        setUpData.put("completed",false);

        HashMap<String,Object> expectedData = new HashMap<>();
        expectedData.put("userId",55);
        expectedData.put("title","Tidy your room");
        expectedData.put("completed",false);
        expectedData.put("id",201);

        return expectedData;
    }
}
