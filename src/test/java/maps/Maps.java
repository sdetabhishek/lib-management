package maps;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.Test;

public class Maps {

    String placeID;

    @Test(priority = 1)

    public void AddPlace() {

        RestAssured.baseURI = "https://rahulshettyacademy.com";
        String response = RestAssured.given().log().all().queryParam("key", "qaclick123")
                .body("{\n" +
                        "    \"location\": {\n" +
                        "        \"lat\": -38.383494,\n" +
                        "        \"lng\": 33.427362\n" +
                        "    },\n" +
                        "    \"accuracy\": 50,\n" +
                        "    \"name\": \"Frontline house\",\n" +
                        "    \"phone_number\": \"(+91) 983 893 3937\",\n" +
                        "    \"address\": \"29, side layout, cohen 09\",\n" +
                        "    \"types\": [\n" +
                        "        \"shoe park\",\n" +
                        "        \"shop\"\n" +
                        "    ],\n" +
                        "    \"website\": \"http://google.com\",\n" +
                        "    \"language\": \"French-IN\"\n" +
                        "}")
                .when().post("/maps/api/place/add/json")
                .then().log().all().assertThat().statusCode(200).extract().response().asPrettyString();


        JsonPath jp = new JsonPath(response);
        placeID = jp.get("place_id");



    }


    @Test(priority = 2)
    public void getPlace(){


        RestAssured.baseURI = "https://rahulshettyacademy.com";
        RestAssured.given().log().all().queryParam("key", "qaclick123")
                .queryParam("place_id",""+placeID+"")
                .when().post("/maps/api/place/get/json")
                .then().log().all().extract().response().asPrettyString();



    }



    @Test(priority = 3)
    public void deletePlace(){

        RestAssured.baseURI = "https://rahulshettyacademy.com";
        RestAssured.given().log().all().queryParam("key", "qaclick123")
                .body("{\n" +
                        "    \"place_id\": \""+placeID+"\"\n" +
                        "}")
                .when().post("/maps/api/place/delete/json")
                .then().log().all().extract().response().asPrettyString();

    }
}
