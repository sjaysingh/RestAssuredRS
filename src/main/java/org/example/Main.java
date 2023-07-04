package org.example;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        RestAssured.baseURI = "https://rahulshettyacademy.com";

        //Add a Place
        String response = given().queryParam("key","qaclick123").header("content-type","application/json")
                .body(Payload.addPlace())
                .when().post("/maps/api/place/add/json")
                .then().log().all().assertThat().statusCode(200).body("scope",equalTo("APP"))
                .header("Content-Length",equalTo("194")).extract().response().asString();

        System.out.println(response);

        JsonPath js = new JsonPath(response);
        String placeId = js.getString("place_id");

        System.out.println(placeId);

        //Update a Place
    }
}