package com.timesinternet.suggestor.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.timesinternet.suggestor.dao.SuggestionsDao;
import com.timesinternet.suggestor.service.SuggestionsService;

@Service
public class SuggestionsServiceImpl implements SuggestionsService{

	@Autowired
	SuggestionsDao suggestionsDao ;
	
	public List<String> getSuggestions(String prefixText) {
		return suggestionsDao.getSuggestionsFromCache(prefixText);
	}

	public List<String> addAcceptedSuggestions(List<String> acceptedSuggestions) {
		suggestionsDao.addAcceptedSuggestions(acceptedSuggestions);
		return null ;
	}

}
