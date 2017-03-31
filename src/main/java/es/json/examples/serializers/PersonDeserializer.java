package es.json.examples.serializers;

import es.json.examples.model.Person;

import java.io.IOException;

public interface PersonDeserializer {

	Person deserialize(String json) throws IOException;
}
