package restAssuredTraining.requestDelete23;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class DeleteRequest23 {

    /*
    http://dummy.restapiexample.com/api/v1/delete/2 bir DELETE request gönderdiğimde

    Dönen response un status kodunun 200 ve body kısmının aşağıdaki gibi olduğunu test edin
   {
      "status": "success",
      "data": "2",
      "message": "Successfully! Record has been deleted"
   }
*/

    public HashMap<String,Object> deleteData(){
        HashMap<String,Object> delete = new HashMap<>();
        delete.put("status","success");
        delete.put("data","2");
        delete.put("message","Successfully! Record has been deleted");

        return delete;
    }
    private RequestSpecification xxx;
    Response response;

    @BeforeTest
    public void setUp(){
        xxx = new RequestSpecBuilder().setBaseUri("http://dummy.restapiexample.com/api/v1").build();
    }
    @Test
    public void test1(){
        xxx.pathParams("parametre1","delete","parametre2",2);

        response =
                given()
                        .contentType(ContentType.JSON)
                        .spec(xxx).auth().basic("admin","password123")
                        .when()
                        .delete("/{parametre1}/{parametre2}");

        JsonPath jsonPath = response.jsonPath();
        Assert.assertEquals(deleteData().get("status"),jsonPath.getString("status"));
        Assert.assertEquals(deleteData().get("data"),jsonPath.getString("2"));
        Assert.assertEquals(deleteData().get("message"),jsonPath.getString("message"));
    }
}
