package org.example;

public class Payload {
    public static String addPlace(){
        return "{\n" +
                "  \"location\": {\n" +
                "    \"lat\": -39.223494,\n" +
                "    \"lng\": 33.427361\n" +
                "  },\n" +
                "  \"accuracy\": 50,\n" +
                "  \"name\": \"Supreme house\",\n" +
                "  \"phone_number\": \"(+91) 983 893 3937\",\n" +
                "  \"address\": \"29, side layout, cohen 09\",\n" +
                "  \"types\": [\n" +
                "    \"shoe park\",\n" +
                "    \"shop\"\n" +
                "  ],\n" +
                "  \"website\": \"http://google.com\",\n" +
                "  \"language\": \"French-IN\"\n" +
                "}";
    }

    public static String updatePlace(){
        return "{\n" +
                "\"place_id\":\"<#>\",\n" +
                "\"address\":\"70 Summer walk, USA\",\n" +
                "\"key\":\"qaclick123\"\n" +
                "}";
    }
}
