package com.timesinternet.suggestor.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.timesinternet.suggestor.dao.SuggestionsDao;
import com.timesinternet.suggestor.model.Record;

@Repository
public class SuggestionsDaoImpl implements SuggestionsDao {

	@Autowired
	private SessionFactory sessionfactory;

	@Transactional
	public List<String> getSuggestions(String inputText) {
		Criteria query = sessionfactory.getCurrentSession().createCriteria(
				Record.class);
		query.add(Restrictions.like("value", inputText, MatchMode.START));
		@SuppressWarnings("rawtypes")
		List list = query.list();
		return list;
	}

}
