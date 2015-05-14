package com.timesinternet.suggestor.cache;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.timesinternet.suggestor.dao.SuggestionsDao;

@Component
public class SuggestionsCachePopluator implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	SuggestionsCache suggestionsCache;

	@Autowired
	SuggestionsDao suggestionsDao;

	public void onApplicationEvent(ContextRefreshedEvent event) {
		List<String> suggestionsFromDatabase = suggestionsDao.getAllSuggestionsFromDatabase();
		for (String suggestion : suggestionsFromDatabase) {
			suggestionsCache.load(suggestion);
		}

	}

}
