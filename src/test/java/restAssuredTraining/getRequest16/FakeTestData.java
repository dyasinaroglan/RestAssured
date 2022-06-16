package restAssuredTraining.getRequest16;

import java.util.HashMap;

public class FakeTestData {

    public HashMap<String,Object> setupData(){
        HashMap<String,Object> expectedDataTest = new HashMap<>();
        expectedDataTest.put("statusCode",200);
        expectedDataTest.put("enyuksekMaas",725000);
        expectedDataTest.put("enKucukYas",19);
        expectedDataTest.put("ikinciEnYüksekMaas",675000);

        return expectedDataTest;
    }

}
