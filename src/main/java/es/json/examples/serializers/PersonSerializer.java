package es.json.examples.serializers;

import es.json.examples.model.Person;

import java.io.IOException;

public interface PersonSerializer {

	String serialize(Person person) throws IOException;
}
