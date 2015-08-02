package com.example.suggestor.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.suggestor.aop.LogAction;
import com.example.suggestor.dao.SuggestionsDao;
import com.example.suggestor.service.SuggestionsService;

@Service
@LogAction
public class SuggestionsServiceImpl implements SuggestionsService {

	@Autowired
	SuggestionsDao suggestionsDao;

	public List<String> getSuggestions(String prefixText) {
		return suggestionsDao.getSuggestionsFromCache(prefixText);
	}

	public String addAcceptedSuggestions(List<String> acceptedSuggestions) {
		return suggestionsDao.addAcceptedSuggestions(acceptedSuggestions);
	}

}
