package es.json.examples;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import es.json.examples.serializers.PersonDeserializer;
import es.json.examples.serializers.PersonSerializer;
import es.json.examples.serializers.impl.PersonDeserializerImpl;
import es.json.examples.serializers.impl.PersonSerializerImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JsonConfiguration {

	@Bean
	public PersonSerializer personSerializer(ObjectMapper mapper) {
		return new PersonSerializerImpl(mapper);
	}

	@Bean
	public PersonDeserializer personDeserializer(ObjectMapper mapper) {
		return new PersonDeserializerImpl(mapper);
	}

	@Bean
	public ObjectMapper objectMapper() {
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new JavaTimeModule());
		mapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
		mapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
		return mapper;
	}
}
