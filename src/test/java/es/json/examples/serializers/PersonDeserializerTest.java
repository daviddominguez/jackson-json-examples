package es.json.examples.serializers;

import es.json.examples.AbstractSpringTest;
import es.json.examples.model.Person;
import es.json.examples.serializers.impl.PersonDeserializerImpl;
import es.json.examples.util.ResourceLoader;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.net.URISyntaxException;

import static es.json.examples.serializers.PersonTestUtils.given_a_person;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class PersonDeserializerTest extends AbstractSpringTest {

	@Autowired
	private PersonDeserializerImpl deserializer;

	@Test
	public void given_a_person_json_structure_when_deserialized_complete_object_is_returned () throws IOException, URISyntaxException {
		final String json = ResourceLoader.loadResourceInOneLine("person.json");
		Person deserializedPerson = deserializer.deserialize(json);
		Person expectedPerson = given_a_person();
		assertThat(deserializedPerson, is(expectedPerson));
	}
}
