package restAssuredTraining.getRequest14;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

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
    }
    @Test
    public void test1(){

        requestSpecification.pathParam("parametre","employees");
        Response response =
                given()
                        .accept(ContentType.JSON)
                        .spec(requestSpecification)
                        .when()
                        .get("/{parametre}");
        JsonPath jsonPath = response.jsonPath();

    }
}
