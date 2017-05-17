package es.json.examples.mongoQuery.model;

import java.util.Collection;

public class Value<T> {

	private String field;
	private Collection<T> values;

	public Value(String field, Collection<T> values) {
		this.field = field;
		this.values = values;
	}

	public String getField() {
		return field;
	}

	public Collection<T> getValues() {
		return values;
	}
}
