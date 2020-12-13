package com.polarising.spring.boot.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.polarising.spring.boot.model.Question;
import com.polarising.spring.boot.services.SurveyService;

@RestController
public class SurveyController {

	@Autowired
	private SurveyService surveyService;
	
	//"surveys/{surveyId}/questions" GET
	@GetMapping("surveys/{surveyId}/questions")
	public List<Question> retrieveQuestionsForSurvey(@PathVariable String surveyId){
		return surveyService.retrieveQuestions(surveyId);
		
	}
	
	
	//"surveys/{surveyId}/questions" POST
	@PostMapping("surveys/{surveyId}/questions")
	public ResponseEntity<Void> addQuestionToSurvey(@PathVariable String surveyId,@RequestBody Question newQuestion){
		
		//getting new question back
		Question createdQuestion =  surveyService.addQuestion(surveyId, newQuestion);
		
		if(createdQuestion == null) {
			return ResponseEntity.noContent().build();
		}
		
		//Success - URI of the new resource in Response Header
			//Build of URI -> surveys/{surveyId}/questions/{questionId}  -> question.getQuestionId()
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                		.path("/{id}")
                			.buildAndExpand(createdQuestion.getId())
                				.toUri();
		
		//Status - created
		return ResponseEntity.created(location).build();
		
		
	}
	
	
	//"surveys/{surveyId}/questions/{questionId}" GET
	@GetMapping("surveys/{surveyId}/questions/{questionId}")
	public Question retrieveDetailsForQuestion(@PathVariable String surveyId, @PathVariable String questionId){
		return surveyService.retrieveQuestion(surveyId, questionId);
		
	}
	
}
