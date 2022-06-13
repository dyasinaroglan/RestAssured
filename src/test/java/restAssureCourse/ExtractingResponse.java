package restAssureCourse;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.*;

public class ExtractingResponse {

    //https://reqres.in/api/users

    @BeforeClass
    public void setUp() {
        baseURI = "https://reqres.in"; //buraya baseURI yazmamiz yeterli
        //enviroment veriables gibi düşünebiliriz
        //body nin içindeki bir değeri dışarı aktarmak istiyorum ve atama yapmak istiyorum

    }
    @Test
    public void extractingJsonPath(){

        String extractValue = given().when().get("/api/users").then().log().body().extract().path("data[0].first_name");
        System.out.println("Alınan değer : " + extractValue);
        Assert.assertEquals(extractValue, "George");
    }
    //List olarak alacağım
    @Test
    public void extractingJsonPathLıst(){

        List<String> list = given().when().get("/api/users").then().extract().path("data.first_name");
        System.out.println("Alınan değer : " + list);
        Assert.assertTrue(list.contains("George"));
    }
}
