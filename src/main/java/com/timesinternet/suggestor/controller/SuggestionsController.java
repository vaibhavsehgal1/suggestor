package com.timesinternet.suggestor.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.timesinternet.suggestor.service.SuggestionsService;


@RestController
public class SuggestionsController {

	@Autowired
	SuggestionsService suggestionsService ;
	
	@RequestMapping(value="/suggestions", method=RequestMethod.GET)
	public List<String> getSuggestions(@RequestParam String inputText) {
		return suggestionsService.getSuggestions(inputText) ;
	}
}
