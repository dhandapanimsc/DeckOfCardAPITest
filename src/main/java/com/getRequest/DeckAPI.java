package com.getRequest;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import java.util.ArrayList;
import java.util.List;

public class DeckAPI {
	
	public static final String baseDeckURI = "https://deckofcardsapi.com";
	public static String deckID ;
	public static void createNewDeck()
	{
		RestAssured.baseURI = baseDeckURI;
		String response = given()
				.header("Content-Type", "application/json")
				.when().get("/api/deck/new/")
				.then().assertThat().statusCode(200).extract().asString();
	}

	public static List<String> addJokerToDeck()
	{
		createNewDeck();
		RestAssured.baseURI = baseDeckURI;
		String responsePost = given()
				.header("Content-Type", "application/json")
				.when().get("/api/deck/new/?jokers_enabled=true")
				.then().assertThat().statusCode(200).extract().response().asString();
		JsonPath jsPath = new JsonPath(responsePost);
		String suffle = jsPath.getString("shuffled");
		String success = jsPath.getString("success");
		String remainingCard = jsPath.getString("remaining");
		String deck_ID = jsPath.getString("deck_id");
		List<String> paramValues = new ArrayList<String>();
		paramValues.add(success);
		paramValues.add(suffle);
		paramValues.add(remainingCard);
		paramValues.add(deck_ID);

		return paramValues;
	}

	public static String drawCard(String deck_ID, int count)
	{
		String responseAfterRequest = given()
				.pathParam("deck_id", deck_ID)
				.queryParam("count", count)
				.header("Content-Type", "application/json")
				.when().get("/api/deck/{deck_id}/draw")
				.then().assertThat().statusCode(200).extract().asString();
		System.out.println(responseAfterRequest);
		JsonPath jsPath = new JsonPath(responseAfterRequest);
		String getCurrentDeckID = jsPath.getString("deck_id");
		String getRemaining = jsPath.getString("remaining");
		return getRemaining;
	}

}
