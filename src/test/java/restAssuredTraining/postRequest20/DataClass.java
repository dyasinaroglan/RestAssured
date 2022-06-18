package restAssuredTraining.postRequest20;

import java.util.HashMap;

public class DataClass {

    public HashMap<String, Object> requestBody() {

        HashMap<String, Object> setupData = new HashMap<>();
        setupData.put("name", "Ahmet Aksoy");
        setupData.put("salary", 1000);
        setupData.put("age", 18);

        return setupData;
    }

    public HashMap<String, Object> setupExpectedData() {
        HashMap<String, Object> data = new HashMap<>();
        data.put("name", "Ahmet Aksoy");
        data.put("salary", 1000);  //bunu yazmamızın sebebi aşağıda bize data yı soruyor. bu bilgileri bizden istiyor
        data.put("age", 18);

        HashMap<String,Object> expectedData = new HashMap<>();
        expectedData.put("status","success");
        expectedData.put("data",data);
        expectedData.put("message","Successfully! Record has been added.");

        return expectedData;
    }
}
