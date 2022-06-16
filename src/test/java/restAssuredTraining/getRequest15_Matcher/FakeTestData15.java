package restAssuredTraining.getRequest15_Matcher;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public class FakeTestData15 {

    public HashMap<String,Object> setupTestData(){

        HashMap<String,Object> onbirinci = new LinkedHashMap<>();
        onbirinci.put("id",11);
        onbirinci.put("employee_name","Jena Gaines");
        onbirinci.put("employee_salary",90560);
        onbirinci.put("employee_age",30);
        onbirinci.put("profile_image","");


        List<Integer> ages = new ArrayList<>();
        ages.add(40);
        ages.add(21);
        ages.add(19);

        HashMap<String,Object> expectedData = new HashMap<>();
        expectedData.put("statusCode",200);
        expectedData.put("besinciCalısanIsmi","Airi Satou");
        expectedData.put("sondan2calısanınMaası",106450);
        expectedData.put("arananYaslar",ages);
        expectedData.put("11CalısanBilgileri",onbirinci);

        return expectedData;
    }
}
