package restAssuredTraining.postRequest19;

import java.util.HashMap;

public class DataClass {

    public HashMap<String,Object> requestBody(){
        HashMap<String,Object> data =new HashMap<>();
        data.put("name","Ahmet Aksoy");
        data.put("salary","1000");                  //create oluşturmak için mutlaka request body göndermeliyiz.
        data.put("age","18");                    //bu ilk göndereceğimiz request body için

        return data;
    }

    public HashMap<String,Object> setupData(){

        HashMap<String,Object> data =new HashMap<>();
        data.put("name","Ahmet Aksoy");
        data.put("salary","1000");
        data.put("age","18");

        HashMap<String,Object> expectedData = new HashMap<>();
        expectedData.put("status","success");
        expectedData.put("data", data);
        expectedData.put("message","Successfully! Record has been added.");

        return expectedData;

        //bunu yapmamızın amacı sonucunu görmek bu bilgileri mi görüyoruz

    }

}
