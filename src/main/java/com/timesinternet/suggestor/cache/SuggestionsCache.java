package com.timesinternet.suggestor.cache;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NavigableSet;
import java.util.TreeSet;

import org.springframework.stereotype.Component;

@Component
public class SuggestionsCache {

	private TreeSet<String> treeSet = new TreeSet<String>();;

	public void load(String text) {
		treeSet.add(text);
	}

	public List<String> getAutoCompletions(String prefix) {
		NavigableSet<String> allSuggestions = treeSet.subSet(prefix + "a", true, prefix + "z",
				true);

		Iterator<String> iterator = allSuggestions.iterator();

		List<String> fixedNumberOfSuggestions = new ArrayList<String>();

		for (int i = 0; i < 10; i++) {
			if (iterator.hasNext()) {
				fixedNumberOfSuggestions.add(iterator.next());
			} else {
				break;
			}
		}
		return fixedNumberOfSuggestions;
	}

	public boolean contains(String text) {
		return treeSet.contains(text);
	}

}