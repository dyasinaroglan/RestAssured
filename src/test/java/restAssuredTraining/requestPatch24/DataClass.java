package restAssuredTraining.requestPatch24;

import java.util.HashMap;

public class DataClass {

    public HashMap<String,Object> requestBody(){

        HashMap<String,Object> body = new HashMap<>();
        body.put("title","API calismaliyim");

        return body;
    }
    public HashMap<String,Object> expectedData(){
        HashMap<String,Object> request = new HashMap<>();
        request.put("userId",10);
        request.put("title","API calismaliyim");
        request.put("completed",true);
        request.put("id",198);

        return request;
    }
}
