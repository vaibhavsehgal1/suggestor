package com.example.suggestor.dao;

import java.util.List;

public interface SuggestionsDao {

	public List<String> getSuggestionsFromDatabase(String prefixText) ;
	public List<String> getAllSuggestionsFromDatabase() ;
	public List<String> getSuggestionsFromCache(String prefixText);
	public String addAcceptedSuggestions(List<String> suggestions);
}
