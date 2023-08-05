package org.example.lib;

import io.restassured.path.json.JsonPath;
import org.testng.annotations.DataProvider;

public class ReusableMethods {
    public static JsonPath rawToJson(String response){
        JsonPath js = new JsonPath(response);
        return js;
    }

    @DataProvider(name = "booksData")
    public Object[][] getData(){
        return new Object[][] {{"3","uiwh9"},{"90","hs6s3"}};
    }
}
