package restAssureCourse;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import restAssureCourse.goRest.GoRestUserTest;

import java.util.HashMap;
import java.util.Map;

public class Example {

    private String baseURI;
    int userID;

    @Test
    public void getUsers() {

        baseURI = "https://gorest.co.in/public-api";
        //request object
        RequestSpecification httpRequest = RestAssured.given();
        //response object
        Response response = httpRequest.when().get("/users");
        //Response response = httpRequest.request(Method.GET,"https://gorest.co.in/public-api/users");

        String responseBody = response.getBody().asString();
        System.out.println(responseBody);

        //status code validation
        int statusCode = response.getStatusCode();
        System.out.println(statusCode);
        Assert.assertEquals(statusCode, 200);

        String statusLine = response.getStatusLine();
        System.out.println(statusLine);
        Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");

    }
    @Test
    public void createUser(){

        RequestSpecification httpRequest = RestAssured.given();
        //request payload
        GoRestUserTest goRestUserTest = new GoRestUserTest();

        JSONObject requestJson = new JSONObject();
        requestJson.put("name", goRestUserTest.getRandomNme());
        requestJson.put("gender","Male");
        requestJson.put("email", goRestUserTest.randomEmail());
        requestJson.put("status","active");

        httpRequest.header("Content-Type", ContentType.JSON);
        httpRequest.body(requestJson.toJSONString());

        Response response = httpRequest.when().post("https://gorest.co.in/public-api/users");
        String responseBody = response.getBody().asString();
        System.out.println(responseBody);

        int code = response.jsonPath().getInt("code");
        Assert.assertEquals(code, 201);

        userID = response.jsonPath().getInt("data.id");
        System.out.println(userID);
    }
}
