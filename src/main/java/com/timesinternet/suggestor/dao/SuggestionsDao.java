package com.timesinternet.suggestor.dao;

import java.util.List;

public interface SuggestionsDao {

	public List<String> getSuggestions(String inputText) ;
}
