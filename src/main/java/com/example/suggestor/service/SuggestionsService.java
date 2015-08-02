package com.example.suggestor.service;

import java.util.List;

public interface SuggestionsService {

	public List<String> getSuggestions(String prefixText) ;
	public String addAcceptedSuggestions(List<String> suggestions);
}
