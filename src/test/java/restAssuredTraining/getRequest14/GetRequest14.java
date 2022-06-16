package restAssuredTraining.getRequest14;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import junit.framework.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class GetRequest14 extends FakeTestData {

    /*
    http://dummy.restapiexample.com/api/v1/employees url ine bir istek gönderildiğinde
     Status kodun 200 olduğunu,
     5. Çalışan isminin "Airi Satou" olduğunu ,  çalışan sayısının 24 olduğunu,
     Sondan 2. çalışanın maaşının 106450 olduğunu
     40,21 ve 19 yaslarında çalışanlar olup olmadığını
     11. Çalışan bilgilerinin
          {
             “id”:”11”
             "employee_name": "Jena Gaines",
             "employee_salary": "90560",
             "employee_age": "30",
             "profile_image": "" }
          }
      gibi olduğunu test edin.

     */
    private RequestSpecification requestSpecification;  // birden fazla rest çağrısı için temel URL i yazabilmemiz için.
    //yani burada bizim bir testimiz var fakat birden çok testimiz de olabilir. ve bu testlrimizin ortak noktaları olabilir.
    //örnegin url gibi. DRY (Don't Repeat Yourself) ilkesine uygun davranmamız gerekir. yani kod tekrarlarından sakınılması en doğrusudur.
    //Bu arabirim, temel URL, temel yol, üstbilgiler vb. tanımlamak için hazır yöntemlere sahiptir.


    @BeforeTest
    public void setUp(){
        requestSpecification = new RequestSpecBuilder().setBaseUri("http://dummy.restapiexample.com/api/v1").build();

        //RequestSpecification, spesifikasyonları yeniden kullanmamıza yardımcı olur.
    }
    @Test
    public void test1(){

        requestSpecification.pathParam("parametre","employees");
        //url'leri dinamik, okunabilir hale getirmek için parametreleştirebiliriz. ortak olan base URL dışında tekrar değer yaratmak için
        //parametreli bir URL oluşturabiliriz
        //Bir URL'yi parametreli hale getirirsek, bunlar için değerleri iletmek bizim sorumluluğumuzdur.
        //pathParam() yöntemi iki dize parametresi alır. İlk parametre parametre adı, diğer parametre parametre değeridir.
        Response response =
                given()
                        .accept(ContentType.JSON)
                        .spec(requestSpecification)
                        .when()
                        .get("/{parametre}");

        FakeTestData fakeTestData = new FakeTestData();
        HashMap<String,Object> expectedDataMap = (HashMap<String, Object>) fakeTestData.setupTestData();
        System.out.println(expectedDataMap);
        JsonPath jsonPath = response.jsonPath();
                             //expected                            //actual
       // Assert.assertEquals(expectedDataMap.get("besinciCalisan"),jsonPath.getString("data[4].employee_name"));

        //list de kaç tane eleman olduğunu buluyoruz o yüzden getList() kullanıyoruz.
        Assert.assertEquals(expectedDataMap.get("calisanSayısı"),jsonPath.getList("data.id").size());

        //sondan ikinci çalışanın maaşı
        Assert.assertEquals(expectedDataMap.get("sondanİkinciCalısanınMaası"),jsonPath.getInt("data[-2].employee_salary"));

        Assert.assertTrue(jsonPath.getList("data.employee_age").containsAll((List)expectedDataMap.get("arananYaslar")));

        //11.çalışanın bilgileri
        Assert.assertEquals(((Map)expectedDataMap.get("onbirinciCalısan")).get("id"), jsonPath.getInt("data[10].id"));
        //burada 11. çalışanın bilgilerini istemiyorum 11.çalışanın 11. çalışan olduğunu kontrol ediyorum
        //Tekrardan get("id") yazmamızın sebebi sadece id numarasını istediğim için "onbirinciCalısan" ın içinde çalışanın tüm özellikleri var

        Assert.assertEquals(((Map<?, ?>) expectedDataMap.get("onbirinciCalısan")).get("employee_name"),
                jsonPath.getString("data[10].employee_name"));
        //(Map<?,?>) değeriyle ilgilenmediğim bir ifadeyi belirtir. Joker gibi String de olur Object de olur.Bunun içinde belirli bir şey var
        //ama ben bunun ne olduğunu bilmiyorum
        Assert.assertEquals(((Map<?,?>) expectedDataMap.get("onbirinciCalısan")).get("employee_name"),
                jsonPath.getString("data[10].employee_name"));

        Assert.assertEquals(((Map<?, ?>) expectedDataMap.get("onbirinciCalısan")).get("employee_salary"),
                jsonPath.getInt("data[10].employee_salary"));

        Assert.assertEquals(((Map<?, ?>) expectedDataMap.get("onbirinciCalısan")).get("employee_age"),
                jsonPath.getInt("data[10].employee_age"));






    }
}
