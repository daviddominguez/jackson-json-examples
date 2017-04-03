package es.json.examples.serializers;

import es.json.examples.AbstractSpringTest;
import es.json.examples.model.Person;
import es.json.examples.serializers.impl.PersonSerializerImpl;
import es.json.examples.ResourceLoader;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

public class PersonSerializerTest extends AbstractSpringTest {

	@Autowired
	private PersonSerializerImpl serializer;

	@Test
	public void given_a_person_json_structure_when_deserialized_complete_object_is_returned () throws IOException, URISyntaxException {
		Person person = PersonTestUtils.given_a_person();
		String expectedJson = given_a_json_string();
		String serializedJson = serializer.serialize(person);
		assertThat(serializedJson, is(expectedJson));
	}

	private String given_a_json_string() throws IOException, URISyntaxException {
		return ResourceLoader.loadResourceInOneLine("person.json");
	}
}
