package deneme;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class Karalama1 {

    public static void main(String[] args) {

        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
        requestSpecBuilder.setBaseUri("https://restful-booker.herokuapp.com");
        requestSpecBuilder.setBasePath("/booking");

        RequestSpecification reqSpec = requestSpecBuilder.build();

        Response r1 = reqSpec.get();
        System.out.println(r1.asString());
        System.out.println("-----------------------");
        Response r2 = given(reqSpec).get();
        System.out.println(r2.asString());
        System.out.println("-----------------------");
        Response r3 = given(reqSpec).get();
        System.out.println(r3.asString());

    }
}
