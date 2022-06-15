package goRest;

import goRest.pojoModel.User;
import io.restassured.http.ContentType;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import static com.sun.tools.xjc.reader.xmlschema.bindinfo.BindInfo.empty;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.not;

public class GoRestUserTest {

    int userID ;

    @Test
    public void goRestGetUsers(){

        List<User> userList = given()
                .when()
                .get("https://gorest.co.in/public-api/users")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("code", equalTo(200))
                .body("data", not(empty))
                .extract().jsonPath().getList("data", User.class);

        System.out.println(userList);

        for (User n:userList) {
            System.out.println(n.getEmail());
        }

    }
    @Test
    public void createTest(){
        //Bearer 6a72f07ad4685b1a298a2615c2a4683c5513b67a62991ac4f3e56fa1ebd113cb

        String name = getRandomNme();
        String email = randomEmail();

        Map<String,String> user = new HashMap<>();
        user.put("name", name);
        user.put("gender","Male");
        user.put("email", email);
        user.put("status","active");

        userID = given()
                .header("Authorization", "Bearer 6a72f07ad4685b1a298a2615c2a4683c5513b67a62991ac4f3e56fa1ebd113cb")
                .contentType(ContentType.JSON)
                .body(user)
                .when()
                .post("https://gorest.co.in/public-api/users")
                .then().log().body().statusCode(200).contentType(ContentType.JSON)
                .body("code", equalTo(201))
                .body("data.name", equalTo(name))
                .extract().jsonPath().getInt("data.id");
                ;
        System.out.println(userID);
    }





    private String randomEmail(){
        String userEmail = RandomStringUtils.randomAlphabetic(8).toLowerCase(Locale.ROOT)+ "@gmail.com";
        return userEmail;
    }
    private String getRandomNme(){
        String userName = RandomStringUtils.randomAlphabetic(8).toLowerCase(Locale.ROOT);
        return userName;
    }

    @Test(dependsOnMethods = "createTest")
    public void updateUserByID() {
        given()
                .header("Authorization", "Bearer 6a72f07ad4685b1a298a2615c2a4683c5513b67a62991ac4f3e56fa1ebd113cb")
                .contentType(ContentType.JSON)
                .pathParam("userID", userID)
                .body("{\"name\":\"ömer\", \"gender\":\"male\", \"email\":\""+randomEmail()+"\", \"status\":\"active\"}")
                .log().uri()
                .when()
                .put("https://gorest.co.in/public-api/users/{userID}")
                .then()
                .statusCode(200)
                .body("code", equalTo(200))
                .body("data.name", equalTo("ömer"))
                .log().body();
    }
    @Test(dependsOnMethods = "createTest")
    public void deleteUserID() {
        given()
                .header("Authorization", "Bearer 6a72f07ad4685b1a298a2615c2a4683c5513b67a62991ac4f3e56fa1ebd113cb")
                .pathParam("userID", userID)
                .log().uri()
                .when()
                .delete("https://gorest.co.in/public-api/users/{userID}")
                .then()
                .statusCode(200).contentType(ContentType.JSON);


    }
    @Test(dependsOnMethods = "deleteUserID")
    public void deleteUserNegative() {
        given()
                .header("Authorization", "Bearer 6a72f07ad4685b1a298a2615c2a4683c5513b67a62991ac4f3e56fa1ebd113cb")
                .pathParam("userID", userID)
                .log().uri()
                .when()
                .delete("https://gorest.co.in/public-api/users/{userID}")
                .then()
                .statusCode(200)
                .body("code", equalTo(404))
                .body("data.message", equalTo("Not Found"))
                .log().body()
                .contentType(ContentType.JSON);


    }
}
