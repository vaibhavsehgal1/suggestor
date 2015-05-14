package com.timesinternet.suggestor.cache;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import org.springframework.stereotype.Component;

@Component
public class SuggestionsCache {

	private TreeSet<String> treeSet = new TreeSet<String>();;

	public void load(String text) {
		treeSet.add(text);
	}

	public List<String> getAutoCompletions(String prefix) {
		return new ArrayList<String>(treeSet.subSet(prefix + "a", true, prefix
				+ "z", true));
	}

	public boolean contains(String text) {
		return treeSet.contains(text);
	}

}