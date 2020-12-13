package com.polarising.spring.boot.controllers;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.PageAttributes.MediaType;
import java.util.ArrayList;
import java.util.Arrays;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import com.polarising.spring.boot.Application;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SurveyControllerIntegrationTest {

	// get the generated random port to avoid port conflicts
	@LocalServerPort
	private int randomPort;
	
	@Test
	public void testJsonAssert() throws JSONException {
		JSONAssert.assertEquals("{id:1, role : Admin}","{id:1 , name:Nelson , role: Admin}", false);
	}
	
	@Test
	public void test() throws JSONException {
		
		System.out.println("randomPort -------------------> " + randomPort);
		
		//url to test
		String url = "http://localhost:"+randomPort+"/surveys/Survey1/questions/Question1";
		
		//invoke url and convert the response to string
		TestRestTemplate testRestTemplate = new TestRestTemplate();
		String outputTestResponse = testRestTemplate.getForObject(url, String.class);
		System.out.println("outputTestResponse -------------------> " + outputTestResponse);
		
		//in order to pass some header in the invocation, we should use testRestTemplate.exchange()
		
		//thus, the first is to create a header with the desired parameters (Accept = application/json)
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(org.springframework.http.MediaType.APPLICATION_JSON));
		
		//next, we should associate the new http header to the http entity
		HttpEntity entity = new HttpEntity<String>(null, headers);
		
		ResponseEntity<String> newOutputTestResponse = testRestTemplate.exchange(url, HttpMethod.GET, entity, String.class);
		System.out.println("newOutputTestResponse -------------------> " + newOutputTestResponse);
	
	
		//start validations
		String expectedId = "Question1";
		String expectedDescription = "Largest Country in the World";
		String expectedCorrect = "Russia";
		ArrayList<String> expectedOptions = new ArrayList<String>(Arrays.asList("India","Russia","United States","China"));
		
		JSONObject json = new JSONObject(newOutputTestResponse.getBody());
		ArrayList<String> actualOptions = new ArrayList<>();
		JSONArray actualOptionsJson = (JSONArray) json.get("options");
		for (int i = 0; i < actualOptionsJson.length(); i++) {
		actualOptions.add(actualOptionsJson.getString(i));
		}
		
		assertAll(() -> assertEquals(expectedId, json.get("id")),
				() -> assertEquals(expectedDescription, json.get("description")),
				() -> assertEquals(expectedCorrect, json.get("correctAnswer")),
				() -> assertEquals(expectedOptions, actualOptions));
		
		assertTrue(newOutputTestResponse.getBody().contains("\"id\":\"Question1\""));
		assertTrue(newOutputTestResponse.getBody().contains("\"description\":\"Largest Country in the World\""));
		
	}

}
