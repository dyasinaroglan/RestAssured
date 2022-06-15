package restAssuredTraining.getRequest14;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TestDataClass {

    /*
    {
            "id": 11,
            "employee_name": "Jena Gaines",
            "employee_salary": 90560,
            "employee_age": 30,
            "profile_image": ""
        }
     */

    public HashMap<String,Object> setupTestData(){
        HashMap<String,Object> onbirinci = new HashMap<>();
        onbirinci.put("id",11);
        onbirinci.put("employee_name","Jena Gaines");
        onbirinci.put("employee_salary",90560);
        onbirinci.put("employee_age",30);
        onbirinci.put("profile_image","");

        List<Integer> yaslar = new ArrayList<>();
        yaslar.add(40);
        yaslar.add(21);
        yaslar.add(19);

        HashMap<String,Object> expectedData=new HashMap<String,Object>();
        expectedData.put("statusCode",2);
        expectedData.put("besinciCalisan","Airi Satou");
        expectedData.put("calisanSayısı",24);
        expectedData.put("sondan2calianMaasi",106450);
        expectedData.put("arananYaslari",yaslar);
        expectedData.put("onBirinciCalisan",onbirinci);

        return expectedData;
    }
}
