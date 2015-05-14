package com.timesinternet.suggestor.dao;

import java.util.List;

public interface SuggestionsDao {

	public List<String> getSuggestionsFromDatabase(String prefixText) ;
	public List<String> getAllSuggestionsFromDatabase() ;
	public List<String> getSuggestionsFromCache(String prefixText);
	public void addAcceptedSuggestions(List<String> suggestions);
}
