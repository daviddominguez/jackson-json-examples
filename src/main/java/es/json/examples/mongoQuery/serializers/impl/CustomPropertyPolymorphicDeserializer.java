package es.json.examples.mongoQuery.serializers.impl;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class CustomPropertyPolymorphicDeserializer<T> extends StdDeserializer<T> {

	private Map<String, Class<? extends T>> registry;

	public CustomPropertyPolymorphicDeserializer(Class<T> clazz) {
		super(clazz);
		registry = new HashMap<>();
	}

	public void register(String property, Class<? extends T> clazz) {
		registry.put(property, clazz);
	}

	@Override
	public T deserialize(JsonParser jsonParser, DeserializationContext context) throws IOException {
		ObjectMapper objectMapper = (ObjectMapper) jsonParser.getCodec();
		ObjectNode objectNode = objectMapper.readTree(jsonParser);
		Iterator<String> fieldNames = objectNode.fieldNames();
		while (fieldNames.hasNext()) {
			String fieldName = fieldNames.next();
			if (registry.containsKey(fieldName)) {
				return objectMapper.treeToValue(objectNode, registry.get(fieldName));
			}
		}

		throw context.mappingException("type not found");
	}
}
