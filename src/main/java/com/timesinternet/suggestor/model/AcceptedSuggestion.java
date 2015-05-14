package com.timesinternet.suggestor.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "accepted_suggestions")
public class AcceptedSuggestion implements Serializable {

	private static final long serialVersionUID = 8106661253314276452L;

	@Id
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "suggestion_id")
	private Suggestion suggestion;

	public AcceptedSuggestion() {
	}

	public AcceptedSuggestion(Suggestion suggestion) {
		this.suggestion = suggestion;
	}

	public Suggestion getSuggestion() {
		return suggestion;
	}

	public void setSuggestion(Suggestion suggestion) {
		this.suggestion = suggestion;
	}

}
