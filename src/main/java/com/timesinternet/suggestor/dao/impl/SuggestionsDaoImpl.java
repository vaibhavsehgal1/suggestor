package com.timesinternet.suggestor.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.timesinternet.suggestor.aop.LogAction;
import com.timesinternet.suggestor.cache.SuggestionsCache;
import com.timesinternet.suggestor.constants.SuggestionMessages;
import com.timesinternet.suggestor.dao.SuggestionsDao;
import com.timesinternet.suggestor.model.AcceptedSuggestion;
import com.timesinternet.suggestor.model.Suggestion;

@Repository
@LogAction
public class SuggestionsDaoImpl implements SuggestionsDao {

	@Autowired
	private SessionFactory sessionfactory;

	@Autowired
	private SuggestionsCache suggestionsCache;

	@Transactional
	public List<String> getSuggestionsFromDatabase(String prefixText) {
		Criteria query = sessionfactory.getCurrentSession().createCriteria(Suggestion.class);
		query.add(Restrictions.like("value", prefixText, MatchMode.START)).setProjection(
				Projections.property("value"));
		@SuppressWarnings("rawtypes")
		List list = query.list();
		return list;
	}

	public List<String> getSuggestionsFromCache(String prefixText) {
		return suggestionsCache.getAutoCompletions(prefixText);
	}

	@Transactional
	public List<String> getAllSuggestionsFromDatabase() {
		Criteria query = sessionfactory.getCurrentSession().createCriteria(Suggestion.class);
		query.setProjection(Projections.property("value"));
		@SuppressWarnings("rawtypes")
		List list = query.list();
		return list;
	}

	@Transactional
	public String addAcceptedSuggestions(List<String> acceptedSuggestions) {
		Session currentSession = sessionfactory.getCurrentSession();
		Criteria query = currentSession.createCriteria(Suggestion.class);
		query.add(Restrictions.in("value", acceptedSuggestions));
		List<Suggestion> suggestionsReturnedFromDatabase = query.list();
		if (suggestionsReturnedFromDatabase.size() == acceptedSuggestions.size()) {
			for (Suggestion suggestion : suggestionsReturnedFromDatabase) {
				currentSession.saveOrUpdate(new AcceptedSuggestion(suggestion));
			}
			return SuggestionMessages.SUGGESTION_SUBMIT_SUCCESS_MESSAGE;
		} else {
			throw new IllegalArgumentException();
		}

	}
}
