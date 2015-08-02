package com.example.suggestor.cache;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.example.suggestor.dao.SuggestionsDao;

@Component
public class SuggestionsCachePopluator implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	SuggestionsCache suggestionsCache;

	@Autowired
	SuggestionsDao suggestionsDao;

	public void onApplicationEvent(ContextRefreshedEvent event) {
		List<String> suggestionsFromDatabase = suggestionsDao.getAllSuggestionsFromDatabase();
		suggestionsCache.loadAll(suggestionsFromDatabase);
	}

}
