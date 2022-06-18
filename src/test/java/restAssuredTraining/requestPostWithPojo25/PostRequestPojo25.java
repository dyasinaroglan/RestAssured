package restAssuredTraining.requestPostWithPojo25;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import restAssuredTraining.todosPojo.TodosPojo;

import static io.restassured.RestAssured.given;

public class PostRequestPojo25 {

    /*
    https://jsonplaceholder.typicode.com/todos url ‘ine bir request gönderildiğinde
 Request body {
                  "userId": 21,
                  "id": 201,
                  "title": "Tidy your room",
                  "completed": false
                }
Status kodun 201, response body ‘nin ise
 {
                  "userId": 21,
                  "id": 201,
                  "title": "Tidy your room",
                  "completed": false
                }
                */

    private RequestSpecification xxx;

    Response response;

    @BeforeTest
    public void setUp(){
        xxx = new RequestSpecBuilder().setBaseUri("https://jsonplaceholder.typicode.com").build();
    }
    @Test
    public void test1(){
        xxx.pathParam("parametre","todos");

        TodosPojo todosPojo = new TodosPojo(21,201,"Tidy your room",false);

        response =
                given()
                        .contentType(ContentType.JSON)
                        .spec(xxx)
                        .body(todosPojo)
                        .when().post("/{parametre}");
        TodosPojo actualData =response.as(TodosPojo.class);
        Assert.assertEquals(todosPojo.getUserId(),actualData.getUserId());
        Assert.assertEquals(todosPojo.getId(),actualData.getId());
    }
}
