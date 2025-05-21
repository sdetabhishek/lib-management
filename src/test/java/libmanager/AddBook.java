package libmanager;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.Test;

public class AddBook {

    public String ID_Name;


    @Test(priority = 1)
    public void validateAddBookAPI() {


        RestAssured.baseURI = "https://rahulshettyacademy.com";
        String response = RestAssured.given().log().all()
                .body(Content.AddBookContent())
                .when().post("/Library/Addbook.php")
                .then().log().all().extract().response().asPrettyString();

        JsonPath jp = new JsonPath(response);
        ID_Name = jp.getString("ID");
        System.out.println(ID_Name);

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
