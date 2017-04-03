package es.json.examples.model;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;

import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import static es.json.examples.util.Maps.flatten;
import static es.json.examples.util.Maps.unflatten;

public class FlattenEntity {
	private SortedMap<String, Object> unknown = new TreeMap<>();

	@JsonAnyGetter
	public Map<String,Object> any() {
		return unflatten("", unknown);
	}

	@JsonAnySetter
	public void set(String property, Object value) {
		flatten(unknown, property, value);
	}

	SortedMap<String, Object> getUnknown() {
		return unknown;
	}

	void setUnknown(SortedMap<String, Object> unknown) {
		this.unknown = unknown;
	}
}
