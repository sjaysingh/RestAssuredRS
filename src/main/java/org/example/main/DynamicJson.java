package org.example.main;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.example.lib.Payload;
import org.example.lib.ResuableMethods;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class DynamicJson {

    @Test(dataProvider = "booksData", dataProviderClass=ResuableMethods.class) //Using dataProvider from a different class
    public void addBook(String aisle, String isbn){
        RestAssured.baseURI = "http://216.10.245.166";

        String response = given().header("content-type","application/json").
                body(Payload.addBook("24","yu79s")).
                when().
                post("/Library/AddBook.php").
                then().log().all().assertThat().statusCode(404).
                extract().response().toString();

        JsonPath js = ResuableMethods.rawToJson(response);
        String bookId = js.getString("ID");
        System.out.println(bookId);

        System.out.printf(js.prettify());

    }

    @Test(dataProvider = "booksDataSame") //Using dataProvider from same class
    public void addBookSame(String aisle, String isbn){
        RestAssured.baseURI = "http://216.10.245.166";

        String response = given().header("content-type","application/json").
                body(Payload.addBook("24","yu79s")).
                when().
                post("/Library/AddBook.php").
                then().log().all().assertThat().statusCode(404).
                extract().response().toString();

        JsonPath js = ResuableMethods.rawToJson(response);
        String bookId = js.getString("ID");
        System.out.println(bookId);

        System.out.printf(js.prettify());

    }

    @DataProvider(name = "booksDataSame")
    public Object[][] getData(){
        return new Object[][] {{"21","yud6a"},{"7","98h2k"}};
    }
}
