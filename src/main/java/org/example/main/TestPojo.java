package org.example.main;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.example.lib.ReusableMethods;
import org.example.pojoClasses.Message;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class TestPojo {

    @Test
    public void testPojoRequest(){
        RestAssured.baseURI = "http://google.com";

        Message msg = new Message();
        msg.setCount("12");
        msg.setMessage("Hello");

        String response = given().header("content-type","application/json")
                .body(msg)
                .when()
                .post("/message")
                .then().log().all()
                .statusCode(404).extract().response().asString();

        JsonPath js = ReusableMethods.rawToJson(response);
//        System.out.println(js.prettify());
    }
}
