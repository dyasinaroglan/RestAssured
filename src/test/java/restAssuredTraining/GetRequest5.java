package restAssuredTraining;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class GetRequest5 {

    /*
     http://dummy.restapiexample.com/api/v1/employees url'ine
     accept type'i "application/json" olan GET request'i yolladigimda
     gelen response'un
     status kodunun 200
     ve employees sayisinin 24
     ve employee'lerden birinin "Ashton Cox"
     ve gelen yaslar icinde 21, 61, ve 23 degerlerinden birinin oldugunu test edin

     */

    @Test
    public void test1(){

        String url = "http://dummy.restapiexample.com/api/v1/employees";

        Response response = given()
                .accept(ContentType.JSON)
                .when()
                .get(url);

        response.then().assertThat().statusCode(200).body("data.id",hasSize(24));
        // hashSize --> belirtilen nesnenin bize uzunluğunu veriyor. burada data'nın içerisinde kaç tane id var diyoruz.

        /*çok fazla sunucuya giriş yapmaya çalıştığımızda 429 too many request hatası verir. Siteye tekrar tekrar girdiğinizde ve
        sınırı aştığınızda görülür.
         */

        response.then().assertThat().statusCode(200).body("data.employee_name",hasItem("Ashton Cox"))
                .body("data.employee_age",hasItems(21,61,23));
        // hasItem --> belirtilen ögeye sahip mi
        // hasItems --> ögeler var mı  ıtem -->öge





    }
}
