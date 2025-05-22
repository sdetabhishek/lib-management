package libmanagement;

import io.restassured.RestAssured;
import org.testng.annotations.Test;

public class AddBook {

    public String ID_Name;


    //java.lang.RuntimeException: com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException


    @Test(priority = 1)
    public void validateAddBookAPI() {

        AddBookReq addBookReq = new AddBookReq();
        addBookReq.setName("RestAssured API Automation");
        addBookReq.setAuthor("Abhishek");
        addBookReq.setAisle("301");
        addBookReq.setIsbn("RSA301");


        RestAssured.baseURI = "https://rahulshettyacademy.com";
        AddBookRes res = RestAssured.given().log().all()
                .body(addBookReq)
                .when().post("/Library/Addbook.php")
                .then().log().all().extract().response().as(AddBookRes.class);

        /*
        JsonPath jp = new JsonPath(response);
        ID_Name = jp.getString("ID");
        */


        ID_Name = res.getID();
        String message_Name = res.getMsg();
        System.out.println(ID_Name);
        System.out.println(message_Name);

    }

    @Test(priority = 2)
    public void validateGetBookAPI() {

        RestAssured.baseURI = "https://rahulshettyacademy.com";
        RestAssured.given().log().all()
                .queryParam("ID", ID_Name)
                .when().get("/Library/GetBook.php")
                .then().log().all().extract().response().asPrettyString();

    }

    @Test(priority = 3)
    public void validateDeleteBookAPI() {

        RestAssured.baseURI = "https://rahulshettyacademy.com";
        RestAssured.given().log().all()
                .body("{\n" +
                        "    \"ID\": \"" + ID_Name + "\"\n" +
                        "}")
                .when().delete("/Library/DeleteBook.php")
                .then().log().all().extract().response().asPrettyString();

    }
}
