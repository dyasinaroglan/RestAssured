package restAssuredTraining.getRequest10;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;

public class GetRequest10 {

    /*
		1) Create a class and name it as you wish :)
		2) When
		     I send a GET Request to https://jsonplaceholder.typicode.com/todos
		   Then
			 HTTP Status code should be "200"
			 And Content type should be in "JSON" format
			 And there should be 200 "title"
			 And "dignissimos quo nobis earum saepe" should be one of the "title"s
			 And 111, 121, and 131 should be among the "id"s
			 And 4th title is "et porro tempora"
			 And last title is "ipsam aperiam voluptates qui"
    */

    private RequestSpecification xxx;

    @BeforeTest
    public void setUP(){
        xxx = new RequestSpecBuilder().setBaseUri("https://jsonplaceholder.typicode.com").build();
    }
    @Test
    public void test1(){
        xxx.pathParams("parametre","todos");

        Response response = given()
                .accept(ContentType.JSON)
                .spec(xxx)
                .when()
                .get("/{parametre}");

        JsonPath jsonPath = response.jsonPath();
        response.then().statusCode(200).contentType(ContentType.JSON);

        Assert.assertTrue(jsonPath.getList("title").contains("dignissimos quo nobis earum saepe"));

        List<Integer> arananID= Arrays.asList(111,121,131);
        Assert.assertTrue(jsonPath.getList("id").containsAll(arananID));

        Assert.assertEquals("et porro tempora",jsonPath.getString("title[3]"));
        Assert.assertEquals("ipsam aperiam voluptates qui",jsonPath.getString("title[-1]"));
    }
}
