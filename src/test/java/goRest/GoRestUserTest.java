package goRest;

import goRest.pojoModel.User;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import java.util.List;

import static com.sun.tools.xjc.reader.xmlschema.bindinfo.BindInfo.empty;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.not;

public class GoRestUserTest {

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
}
