package org.example;
import files.RestUsableMethods;
import files.payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;

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
        String response = given().log().all().queryParam("key", "qaclick123")
                .header("Content-Type", "application/json")
                .body(payload.AddPlace())
                .when().post("/maps/api/place/add/json")
                .then().assertThat().statusCode(200).body("scope", equalTo("APP"))
                .extract().response().asString();
        //.header("server","Apache/2.4.18 (Ubuntu)");
        System.out.println(response);
        JsonPath js = new JsonPath(response); //for parsing Jsom
        String placeId = js.getString("place_id");
        System.out.println("placeId = " + placeId);

        //Update Place
        String newAddress = "Summer Walk, Africa";

        given().log().all().queryParam("key", "qaclick123")
                .header("Content-Type", "application/json")
                .body("{\n" +
                        "\"place_id\":\""+ placeId + "\",\n" +
                        "\"address\":\""+ newAddress + "\",\n" +
                        "\"key\":\"qaclick123\"\n" +
                        "}")
                .when().put("maps/api/place/update/json")
                .then().assertThat().log().all().statusCode(200).body("msg", equalTo("Address successfully updated"));


        //Get Place
        String getPlaceResponse = given().log().all().queryParam("key", "qaclick123")
                .queryParam("place_id",placeId)
                .when().get("maps/api/place/get/json")
                .then().assertThat().log().all().statusCode(200).extract().asString();
        JsonPath js1 = RestUsableMethods.rawToJson(getPlaceResponse);
        String actualAddress = js1.getString("address");
        System.out.println(actualAddress);

        //check updated address equal to new address.
        Assert.assertEquals(actualAddress,newAddress);
        //Assert.assertEquals(actualAddress,"any address");

    }
}
