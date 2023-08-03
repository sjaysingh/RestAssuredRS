package org.example;

import io.restassured.path.json.JsonPath;
import org.example.lib.ResuableMethods;

import java.sql.SQLOutput;

public class ComplexJsonParse {
    public static void main(String[] args) {
        JsonPath coursePriceJson = ResuableMethods.rawToJson(Payload.dummyCoursePriceResponse());
        System.out.println(coursePriceJson.prettify());

    }
}
