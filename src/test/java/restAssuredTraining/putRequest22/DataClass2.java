package restAssuredTraining.putRequest22;

import java.util.HashMap;

public class DataClass2 {

    public HashMap<String,Object> requestBody(){
        HashMap<String,Object> setupBody = new HashMap<>();
        setupBody.put("userId",21);
        setupBody.put("title","Wash the dishes");
        setupBody.put("completed",false);

        return setupBody;

    }

    public HashMap<String,Object> expectedBilgiler(){

        HashMap<String,Object> setupBody = new HashMap<>();
        setupBody.put("userId",21);
        setupBody.put("title","Wash the dishes");
        setupBody.put("completed",false);
        setupBody.put("id",198);

        return setupBody;


    }
}
