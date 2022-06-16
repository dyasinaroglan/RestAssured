package restAssuredTraining.getRequest14;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FakeTestData {

    public HashMap<String, Object> setupTestData() {

        HashMap<String, Object> onBirinci = new HashMap<>();
        onBirinci.put("id", 11);
        onBirinci.put("employee_name", "Jena Gaines");
        onBirinci.put("employee_salary", 90560);
        onBirinci.put("employee_age", 30);
        onBirinci.put("profile_image", "");

        List<Integer> ages = new ArrayList<>();
        ages.add(40);
        ages.add(21);
        ages.add(19);

        HashMap<String, Object> expectedData = new HashMap<>();
        expectedData.put("statusCode",200);
        expectedData.put("besinciCalısan","Airi Satou");
        expectedData.put("calisanSayısı",24);
        expectedData.put("sondanİkinciCalısanınMaası",106450);
        expectedData.put("arananYaslar",ages);
        expectedData.put("onbirinciCalısan",onBirinci);

        return expectedData;
    }
}
