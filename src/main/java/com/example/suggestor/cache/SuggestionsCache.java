package com.example.suggestor.cache;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NavigableSet;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SuggestionsCache {

	private TreeSet<String> treeSet = new TreeSet<String>();;

	@Value("#{T (java.lang.Integer).parseInt('${max.suggestions.returned}')}")
	private Integer maxSuggestionsReturned;

	public void load(String suggestion) {
		treeSet.add(suggestion);
	}

	public void loadAll(List<String> suggestion) {
		treeSet.addAll(suggestion);
	}

	public List<String> getAutoCompletions(String prefix) {
		NavigableSet<String> allSuggestions = treeSet.subSet(prefix + " ", true, prefix + "~", true);

		Iterator<String> iterator = allSuggestions.iterator();

		List<String> fixedNumberOfSuggestions = new ArrayList<String>();

		for (int i = 0; i < maxSuggestionsReturned; i++) {
			if (iterator.hasNext()) {
				fixedNumberOfSuggestions.add(iterator.next());
			} else {
				break;
			}
		}
		return fixedNumberOfSuggestions;
	}
}