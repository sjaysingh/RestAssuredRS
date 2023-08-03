package org.example;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.example.lib.ResuableMethods;
import org.testng.Assert;
import java.util.HashMap;
import java.util.Map;

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

        String response2 = given().log().all().queryParam("key","qaclick123").header("content-type","application/json")
                .body(Payload.updatePlace().replace("<#>",placeId))
                .when().log().all().put("/maps/api/place/update/json")
                .then().log().all().assertThat().statusCode(200).body("msg",equalTo("Address successfully updated")).extract().response().asString();

        JsonPath js1 = ResuableMethods.rawToJson(response2);

        String message = js1.getString("msg");
        Assert.assertEquals(message,"Address successfully updated");

        //Get a Place

        Map<String,String> qParams = new HashMap<String, String>();
        qParams.put("key","qaclick123");
        qParams.put("placeId",placeId);

        Map<String,String> headers = new HashMap<String, String>();
        headers.put("content-type","application/json");

        String response3 = given().log().all().queryParams(qParams)
//                .headers(headers)                         /*Not required as no body is there for get request*/
                .when().log().all().get("/maps/api/place/get/json")
                .then().log().all().assertThat().statusCode(200)
//                .body("name",equalTo("Frontline house"))          /*Not required as no response body for this get request*/
                .extract().response().asString();


        System.out.println(response3.toString());

        JsonPath js2 = new JsonPath(response3);
        //System.out.println(js2.getString("name"));


    }
}