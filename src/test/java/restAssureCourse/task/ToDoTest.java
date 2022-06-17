package restAssureCourse.task;

import org.testng.annotations.Test;
import restAssureCourse.task.pojoClass.PojoToDo;

import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;

public class ToDoTest {
    //https://jsonplaceholder.typicode.com/todos

    @Test
    public void testToDo() {

        PojoToDo[] toDoArray= given()
                .when()
                .get("https://jsonplaceholder.typicode.com/todos")
                .then()
                .statusCode(200)
                .extract()
                .as(PojoToDo[].class);

        System.out.println(Arrays.toString(toDoArray));

    }
    @Test
    public void testToDoList(){
        List<PojoToDo> toDoList= given()
                   .when()
                    .get("https://jsonplaceholder.typicode.com/todos")
                    .then()
                    .statusCode(200)
                    .extract()
                    .jsonPath().get();

        System.out.println(toDoList);
    }
    @Test
    public void test2(){
        List<PojoToDo> toDoList= Arrays.asList(given()
                .when()
                .get("https://jsonplaceholder.typicode.com/todos")
                .then()
                .statusCode(200)
                .extract()
                .as(PojoToDo[].class));

    }

}
