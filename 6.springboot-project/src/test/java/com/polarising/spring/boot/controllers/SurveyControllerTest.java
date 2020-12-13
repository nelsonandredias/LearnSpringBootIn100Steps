package com.polarising.spring.boot.controllers;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.polarising.spring.boot.model.Question;
import com.polarising.spring.boot.services.SurveyService;

@ExtendWith(SpringExtension.class)
@WebMvcTest(SurveyController.class)
class SurveyControllerTest {

	// will allow us to make calls to the service
	@Autowired
	private MockMvc mockMvc;

	// @MockBean will autowire the surveyService into the SurveyController (which is
	// the class being tested)
	@MockBean
	private SurveyService surveyService;

	@Test
	public void retrieveDetailsForQuestionTest() throws Exception {

		Question mockQuestion = new Question("Question5", "What is the capital of Portugal", "Lisbon",
				Arrays.asList("Porto", "Coimbra", "Lisbon", "Faro"));

		// Setup - step1. when (surveyService.retrieveQuestion(surveyId, questionId))
		// then returnSomeMockData
		Mockito.when(surveyService.retrieveQuestion(Mockito.anyString(), Mockito.anyString())).thenReturn(mockQuestion);

		// Invokation - step2. make a call to the service

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/surveys/Survey1/questions/Question5")
				.accept(MediaType.APPLICATION_JSON);

		MvcResult mockResult = mockMvc.perform(requestBuilder).andReturn();
		System.out.println("mockResult -----------------------> " + mockResult);

		String actual = mockResult.getResponse().getContentAsString();
		System.out.println("actual -----------------------> " + actual);

		String expected = "{\"id\":\"Question5\",\"description\":\"What is the capital of Portugal\",\"correctAnswer\":\"Lisbon\"}";

		// Validation - step3. validation with assert
		JSONAssert.assertEquals(expected, actual, false);
	}

	@Test
	public void retrieveSurveyQuestions() throws Exception {

		List<Question> mockQuestionsList = Arrays.asList(
				new Question("Question1", "First Alphabet", "A", Arrays.asList("A", "B", "C", "D")),
				new Question("Question2", "Last Alphabet", "Z", Arrays.asList("A", "X", "Y", "Z")));

		Mockito.when(surveyService.retrieveQuestions(Mockito.anyString())).thenReturn(mockQuestionsList);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/surveys/Survey1/questions")
				.accept(MediaType.APPLICATION_JSON);

		MvcResult mockResult = mockMvc.perform(requestBuilder).andExpect(status().isOk()).andReturn();

		System.out.println("mockResult -----------------------> " + mockResult);

		String actual = mockResult.getResponse().getContentAsString();
		System.out.println("actual -----------------------> " + actual);

		String expected = "[{\"id\":\"Question1\",\"description\":\"First Alphabet\",\"correctAnswer\":\"A\",\"options\":[\"A\",\"B\",\"C\",\"D\"]},{\"id\":\"Question2\",\"description\":\"Last Alphabet\",\"correctAnswer\":\"Z\",\"options\":[\"A\",\"X\",\"Y\",\"Z\"]}]";

		JSONAssert.assertEquals(expected, actual, false);
	}

	@Test
	public void createSurveyQuestion() throws Exception {

		Question mockQuestion = new Question("1", "Smallest Number", "1", Arrays.asList("1", "2", "3", "4"));

		String questionJson = "{\"description\":\"Smallest Number\",\"correctAnswer\":\"1\",\"options\":[\"1\",\"2\",\"3\",\"4\"]}";

		// surveyService.addQuestion to respond back with mockQuestion
		Mockito.when(surveyService.addQuestion(Mockito.anyString(), Mockito.any(Question.class)))
				.thenReturn(mockQuestion);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/surveys/Survey1/questions")
				.accept(MediaType.APPLICATION_JSON)
					.content(questionJson)
						.contentType(MediaType.APPLICATION_JSON);

		MvcResult mockResult = mockMvc.perform(requestBuilder).andReturn();

		System.out.println("mockResult -----------------------> " + mockResult);
		
		MockHttpServletResponse response = mockResult.getResponse();
		System.out.println("response -----------------------> " + response);
		
		
		assertEquals(HttpStatus.CREATED.value(), response.getStatus());
		
		assertEquals("http://localhost/surveys/Survey1/questions/1", response.getHeader(HttpHeaders.LOCATION));
	}

}
