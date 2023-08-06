package org.example.main;

import io.restassured.path.json.JsonPath;
import org.example.pojoClasses.GetCourse;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.Test;
import org.openqa.selenium.*;

import static io.restassured.RestAssured.given;

public class TestCourses {

    @Test
    public void testCourseApi() throws InterruptedException {
        String[] courseTitles= { "Selenium Webdriver Java","Cypress","Protractor"};
        System.setProperty("webdriver.chrome.driver", "chromedriver");
        WebDriver driver= new ChromeDriver();

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setBrowserVersion("114.0");

//        WebDriver driver = new SafariDriver();

        driver.get("https://accounts.google.com/o/oauth2/v2/auth?scope=https://www.googleapis.com/auth/userinfo.email&auth_url=https://accounts.google.com/o/oauth2/v2/auth&client_id=692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com&response_type=code&redirect_uri=https://rahulshettyacademy.com/getCourse.php&state=verifyfjdss");
        driver.findElement(By.cssSelector("input[type='email']")).sendKeys("fdfd");
        driver.findElement(By.cssSelector("input[type='email']")).sendKeys(Keys.ENTER);
        Thread.sleep(3000);
        driver.findElement(By.cssSelector("input[type='password']")).sendKeys("fxfe");
        driver.findElement(By.cssSelector("input[type='password']")).sendKeys(Keys.ENTER);
        Thread.sleep(4000);
        String url=driver.getCurrentUrl();
        String partialcode=url.split("code=")[1];
        String code=partialcode.split("&scope")[0];
        System.out.println(code);

        String accessTokenResponse=	given().urlEncodingEnabled(false)
                .queryParams("code",code)
                .queryParams("client_id","692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
                .queryParams("client_secret","erZOWM9g3UtwNRj340YYaK_W")
                .queryParams("redirect_uri","https://rahulshettyacademy.com/getCourse.php")
                .queryParams("grant_type","authorization_code")
                .when().log().all()
                .post("https://www.googleapis.com/oauth2/v4/token").asString();
        JsonPath js=new JsonPath(accessTokenResponse);
        String accessToken=js.getString("access_token");


        GetCourse gc = given().queryParam("access_token",accessToken)
                .when()
                .get("\"https://rahulshettyacademy.com/getCourse.php\"")
                .as(GetCourse.class);

        System.out.println(gc.getInstructor());
    }




}
