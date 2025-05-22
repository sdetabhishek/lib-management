package libmanagement;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class SpecBuilder {

    public RequestSpecification requestSpecification;
    public ResponseSpecification responseSpecification;

    public SpecBuilder() {


        requestSpecification = new RequestSpecBuilder()

                .setContentType(ContentType.JSON)
                .setBaseUri("https://rahulshettyacademy.com")
                .build();


        responseSpecification = new ResponseSpecBuilder()

                .expectContentType(ContentType.JSON)
                .expectStatusCode(200)
                .build();
    }


}
