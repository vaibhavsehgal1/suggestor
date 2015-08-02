package com.example.suggestor.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.suggestor.aop.LogAction;
import com.example.suggestor.constants.SuggestionMessages;
import com.example.suggestor.service.SuggestionsService;

@RestController
@LogAction
public class SuggestionsController {

	@Autowired
	SuggestionsService suggestionsService;

	@RequestMapping(value = "/suggestions", method = RequestMethod.GET)
	public List<String> getSuggestions(@RequestParam String prefixText) {
		return suggestionsService.getSuggestions(prefixText);
	}

	@RequestMapping(value = "/suggestions", method = RequestMethod.POST)
	public String addSuggestions(@RequestBody List<String> acceptedSuggestions) {
		String addAcceptedSuggestions = suggestionsService.addAcceptedSuggestions(acceptedSuggestions);
		return addAcceptedSuggestions;
	}

	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	@ExceptionHandler(IllegalArgumentException.class)
	public String suggestionSubmitFailure() {
		return SuggestionMessages.SUGGESTION_SUBMIT_FAILURE_MESSAGE;
	}
}
