package es.json.examples.serializers.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import es.json.examples.model.Person;
import es.json.examples.serializers.PersonDeserializer;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

public class PersonDeserializerImpl implements PersonDeserializer {

	private ObjectMapper mapper;

	@Autowired
	public PersonDeserializerImpl(ObjectMapper mapper) {
		this.mapper = mapper;
	}

	public Person deserialize(String json) throws IOException {
		return mapper.readValue(json, Person.class);
	}
}
