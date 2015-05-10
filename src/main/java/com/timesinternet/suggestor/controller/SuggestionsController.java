package com.timesinternet.suggestor.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.timesinternet.suggestor.aop.TimeProfiler;
import com.timesinternet.suggestor.service.SuggestionsService;

@RestController
public class SuggestionsController {

	@Autowired
	SuggestionsService suggestionsService;

	@Autowired
	SetTrie setTrie;

	@RequestMapping(value = "/suggestions", method = RequestMethod.GET)
	public List<String> getSuggestions(@RequestParam String inputText) {
		// return suggestionsService.getSuggestions(inputText) ;

		List<String> completions = setTrie.findCompletions(inputText);

		if (completions.size() > 0) {
			return completions;
		} else {
			List<String> suggestions = suggestionsService
					.getSuggestions(inputText);
			
			
			for (String string : suggestions) {
				setTrie.load(string);
			}
			return suggestions ;
		}
	}
}
