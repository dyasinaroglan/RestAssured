package restAssureCourse;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;

public class ZippoTest3 {



    @BeforeClass
    public void setUp() {
        baseURI = "http://api.zippopotam.us"; //buraya baseURI yazmamiz yeterli
        //enviroment veriables gibi düşünebiliriz
        //response spesicafit

    //http://api.zippopotam.us/us/90210

        responseSpecification = new ResponseSpecBuilder().
                expectStatusCode(200).
                expectContentType(ContentType.JSON).
                log(LogDetail.BODY).
                build();

        requestSpecification = new RequestSpecBuilder().
               log(LogDetail.BODY).
                setAccept(ContentType.JSON).
                build();
    }

    @Test
    public void checkPlacesArraySize() {

        //http tipi ile başlamadığını görünce baseURI yi kullanıyor
        given().when().get("/us/90210").then().statusCode(200).body("places.size()", equalTo(1));
        //ya da
        given().when().get("/us/90210").then().statusCode(200).body("places", hasSize(1)).log().body();
    }
    @Test
    public void checkPlacesArraySizeSpecification(){
        given().when().get("/us/90210").then().statusCode(200).body("places", hasSize(1)).spec(responseSpecification);
    }
    @Test
    public void checkPlacesArraySizeRequestSpecification(){
        given().spec(requestSpecification).when().get("/us/90210").then().body("places", hasSize(1)).spec(responseSpecification);
    }

}
