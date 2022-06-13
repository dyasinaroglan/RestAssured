package restAssureCourse;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class RestAssuredTest {

    @Test
    public void singleBddTestUser(){
        given().when().get("https://reqres.in/api/users/2").then().statusCode(200).body("data.first_name",equalTo("Janet")).log().body().
                time(lessThan(2000L)); // 2 saniyeden daha az ise fail et demek

    }
    @Test
    public void singleUserTest(){
        RestAssured.baseURI = "https://reqres.in"; //base url
        RequestSpecification httrequest = given(); //request
        Response response = httrequest.get("/api/users/2"); //
        ResponseBody body = response.getBody(); //response body
        String bodyAsString = body.asString(); //
        System.out.println(bodyAsString);
        Assert.assertTrue(bodyAsString.contains("Janet"),"String did not found");
    }
}
