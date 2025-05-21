package lib;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.Test;

public class Books {

    public String BookID;


    @Test(priority = 1)
    public void AddBooks() {


        RestAssured.baseURI = "https://rahulshettyacademy.com";

        AddBookRequest bookRequest = new AddBookRequest();
        bookRequest.setName("RestAssured Concept");
        bookRequest.setAuthor("Ashish Kumar");
        bookRequest.setIsbn("RAC");
        bookRequest.setAisle(901);


        AddBookResponse responseAddBooks = RestAssured.given().log().all()
                .body(bookRequest)
                .when().post("/Library/Addbook.php")
                .then().log().all().extract().response().as(AddBookResponse.class);



        BookID = responseAddBooks.getID();
        System.out.println(BookID);

    }

    @Test(priority = 2)
    public void GetBook() {

        RestAssured.baseURI = "https://rahulshettyacademy.com";
        RestAssured.given().log().all().given().queryParam("ID", "" + BookID + "")
                .when().post("/Library/GetBook.php")
                .then().log().all().extract().response().asPrettyString();


    }

    @Test(priority = 3)
    public void DeleteBook() {
        RestAssured.baseURI = "https://rahulshettyacademy.com";
        String responseDelete = RestAssured.given().log().all().given().body("{\n" +
                        "    \"ID\": \"" + BookID + "\"\n" +
                        "}")
                .when().post("/Library/DeleteBook.php")
                .then().log().all().extract().response().asPrettyString();

        JsonPath jp = new JsonPath(responseDelete);
        String message = jp.getString("msg");
        System.out.println(message);
    }


}
