import io.restassured.http.ContentType;
import io.restassured.specification.Argument;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;


public class ZippoTest {

    @Test
    public void test1(){

        given().when().then();
        //given --> setup başlangıç ayarları
        //when --> yapılacak işlem
        //then --> kontrol
    }
    @Test
    public void statusCodeTest(){
        given().when().get("http://api.zippopotam.us/us/90210").then().statusCode(200);
        given().when().get("http://api.zippopotam.us/us/90210").then().log().all().statusCode(200);

    }
    @Test
    public void contentTypeTest(){
        given().when().get("http://api.zippopotam.us/us/90210").then().log().all().contentType("application/json");
        given().when().get("http://api.zippopotam.us/us/90210").then().log().all().contentType(ContentType.JSON);
        given().when().get("http://api.zippopotam.us/us/90210").then().log().body().contentType(ContentType.JSON); //body içindeki veriyi loglar
    }
    @Test
    public void logTest(){
        given().log().all().when().get("http://api.zippopotam.us/us/90210").then().log().body(); //log.all()
    }
    @Test
    public void checkCountry(){
        given().when().get("http://api.zippopotam.us/us/90210").then().statusCode(200).body("country", equalTo("United States"));
    }
    @Test
    public void checkState(){
        given().when().get("http://api.zippopotam.us/us/90210").then().statusCode(200).body("places[0].state", equalTo("California"));
    }
    @Test
    public void checkPlaceName(){
        given().when().get("http://api.zippopotam.us/us/90210").then().statusCode(200).body("places[0].'place name'", equalTo("Beverly Hills"));
        //ayrık bir değer için .'place name' şeklinde yazılır. tek tırnak içerisinde yazılır.
    }
    @Test
    public void checkPlacesArraySize(){
        given().when().get("http://api.zippopotam.us/us/90210").then().statusCode(200).body("places.size()", equalTo(1));
        //ya da
        given().when().get("http://api.zippopotam.us/us/90210").then().statusCode(200).body("places", hasSize(1)).log().body();

    }
    @Test
    public void multiBodyTest(){
        given().when().get("http://api.zippopotam.us/us/90210").then().statusCode(200).
                body("places[0].state", equalTo("California")).body("places[0].'place name'", equalTo("Beverly Hills")).
                body("places", hasSize(1)).log().body();
        //ard arda 3 tane çoklu body test ya da multi body test yapabilirsiniz.

    }
}
