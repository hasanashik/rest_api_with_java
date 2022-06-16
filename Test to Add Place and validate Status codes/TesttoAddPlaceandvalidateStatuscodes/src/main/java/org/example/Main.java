package org.example;
import files.payload;
import io.restassured.RestAssured;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


public class Main {
    public static void main(String[] args) {
        //To do Auto-generated method stub
        //validate if Add place API is working as expected

        //given - all input details
        //when - submit the API
        //Then - validate the response
        RestAssured.baseURI = "https://rahulshettyacademy.com";
        given().log().all().queryParam("key","qaclick123")
                .header("Content-Type","application/json")
                .body(payload.AddPlace())
                .when().post("/maps/api/place/add/json")
                .then().log().all().assertThat().statusCode(200).body("scope",equalTo("APP"));
                //.header("server","Apache/2.4.18 (Ubuntu)");


    }
}