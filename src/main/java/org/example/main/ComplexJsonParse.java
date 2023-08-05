package org.example.main;

import io.restassured.path.json.JsonPath;
import org.example.lib.Payload;
import org.example.lib.ReusableMethods;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ComplexJsonParse {
//    public static void main(String[] args) {}

    @Test
    public void complexJsonValidations(){
        JsonPath coursePriceJson = ReusableMethods.rawToJson(Payload.dummyCoursePriceResponse());
        System.out.println(coursePriceJson.prettify());

        int countOfCourses = coursePriceJson.getInt("courses.size()");
        int purchaseAmount = coursePriceJson.getInt("dashboard.purchaseAmount");
        String titleofFirstCourse = coursePriceJson.getString("courses[0].title");

        System.out.println(countOfCourses +"\n"+ purchaseAmount +"\n"+ titleofFirstCourse);

        int copiesSoldByRPACourse=coursePriceJson.getInt("courses[2].copies");

        Assert.assertEquals(10,copiesSoldByRPACourse);

        int sumOfCourseCopySales=0;


        for (int i=0;i<countOfCourses;i++){
            String courseTitle = coursePriceJson.getString("courses["+i+"].title");

            System.out.println("Course title: "+ courseTitle);
            System.out.println("Course price: "+ coursePriceJson.getInt("courses["+i+"].price"));
            System.out.println("Course copies: "+ coursePriceJson.getInt("courses["+i+"].copies"));

            if(courseTitle.equalsIgnoreCase("RPA")){
                System.out.println("Course price for RPA course is: "+ coursePriceJson.getInt("courses["+i+"].price"));

            }

            sumOfCourseCopySales += coursePriceJson.getInt("courses["+i+"].copies")*coursePriceJson.getInt("courses["+i+"].price");
        }

        Assert.assertEquals(purchaseAmount,sumOfCourseCopySales);

    }
}
