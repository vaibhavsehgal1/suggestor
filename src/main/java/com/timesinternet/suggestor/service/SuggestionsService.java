package com.timesinternet.suggestor.service;

import java.util.List;

public interface SuggestionsService {

	public List<String> getSuggestions(String prefixText) ;
	public List<String> addAcceptedSuggestions(List<String> suggestions);
}
