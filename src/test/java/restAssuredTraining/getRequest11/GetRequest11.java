package restAssuredTraining.getRequest11;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class GetRequest11 {

    /*
    http://dummy.restapiexample.com/api/v1/employees
    url ine bir istek gönderildiğinde
    Dönen response un
    Status kodunun 200,
       1)10’dan büyük tüm id’leri ekrana yazdırın ve
    10’dan büyük 14 id olduğunu,
       2)30’dan küçük tüm yaşları ekrana yazdırın ve
    bu yaşların içerisinde en büyük yaşın 23 olduğunu
       3)Maası 350000 den büyük olan tüm employee name’leri ekrana yazdırın ve
    bunların içerisinde “Charde Marshall” olduğunu test edin
     */

    private RequestSpecification xxx;

    @BeforeTest
    public void setUp(){
        String url = "http://dummy.restapiexample.com/api/v1";
        xxx = new RequestSpecBuilder().setBaseUri(url).build();

    }

    @Test
    public void test1(){

        xxx.pathParam("parametre1","employees");

        Response response =
                given()
                        .accept(ContentType.JSON)
                        .spec(xxx)
                        .when()
                        .get("/{parametre1}");

        JsonPath jsonPath = response.jsonPath();
        Assert.assertEquals(response.getStatusCode(),200);
        //10'dan büyük tüm id'leri yazdırın
        List<Integer> idList = jsonPath.getList("data.findAll{it.id>10}.id");
        System.out.println(idList);
        Assert.assertEquals(14,idList.size());

        //30'dan küçük tüm yaşlar
        List<Integer> ages = jsonPath.getList("data.findAll{it.employee_age<30}.employee_age");
        System.out.println(ages);
        Collections.sort(ages); //küçükten büyüğe sıralıyor sort
        Assert.assertEquals(23,(int)ages.get(ages.size()-1));

        //maaşı 350000 den büyük olanların isimleri
        List<String> names = jsonPath.getList("data.findAll{it.employee_salary>350000}.employee_name");
        System.out.println(names);
        Assert.assertTrue(names.contains("Charde Marshall"));
    }
}
