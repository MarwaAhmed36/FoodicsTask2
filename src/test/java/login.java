import io.goodforgod.http.common.HttpStatus;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import org.junit.*;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class login {
    @BeforeClass
    public static void createRequestSpecification() {

        requestSpecification = new RequestSpecBuilder().
                setBaseUri("https://pay2.foodics.dev/cp_internal/").
                build();
    }
    @Test
    public void login_checkStatusCode_expectHttp200() {

        given().auth()
                .basic("merchant@foodics.com", "123456").spec(requestSpecification)
                .when()
                .get("login")
                .then().assertThat().statusCode(200);
    }
    @Test
    public void invalidMaillogin_checkStatusCode_expectHttp400() {

        given().auth()
                .basic("m@foodics.co", "123456").spec(requestSpecification)
                .when()
                .get("login")
                .then().assertThat().statusCode(200);
    }
    @Test
    public void invalidPasslogin_checkStatusCode_expectHttp400() {

        given().auth()
                .basic("merchant@foodics.com", "1234").spec(requestSpecification)
                .when()
                .get("login")
                .then().assertThat().statusCode(400);
    }
    @Test
    public void whoami_checkStatusCode_expectHttp200() {

        given().auth()
                .basic("merchant@foodics.com", "123456").spec(requestSpecification)
                .when()
                .get("whoami")
                .then().assertThat().statusCode(200);
    }
    @Test
    public void invalidPassHhoami_checkStatusCode_expectHttp400() {

        given().auth()
                .basic("merchant@fo", "123456").spec(requestSpecification)
                .when()
                .get("whoami")
                .then().assertThat().statusCode(400);
    }

    public void invalidMailWhhoami_checkStatusCode_expectHttp400() {

        given().auth()
                .basic("merchant@foodics.com", "123").spec(requestSpecification)
                .when()
                .get("whoami")
                .then().assertThat().statusCode(400);
    }
}



