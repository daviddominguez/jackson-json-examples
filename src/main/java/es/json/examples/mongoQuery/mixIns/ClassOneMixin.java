package es.json.examples.mongoQuery.mixIns;

import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class ClassOneMixin {

	public ClassOneMixin(@JsonProperty("one") int oneProperty, @JsonProperty("anotherOne") int anotherOneProperty) {
	}

	@JsonProperty("one") abstract int getOneProperty();
	@JsonProperty("anotherOne") abstract int getAnotherOneProperty();
}
