package es.json.examples.serializers.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import es.json.examples.model.Person;
import es.json.examples.serializers.PersonSerializer;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

public class PersonSerializerImpl implements PersonSerializer {

	private ObjectMapper mapper;

	@Autowired
	public PersonSerializerImpl(ObjectMapper mapper) {
		this.mapper = mapper;
	}

	public String serialize(Person person) throws IOException {
		return mapper.writeValueAsString(person);
	}
}
