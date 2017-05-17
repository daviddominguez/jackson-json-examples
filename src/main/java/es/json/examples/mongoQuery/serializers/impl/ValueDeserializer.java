package es.json.examples.mongoQuery.serializers.impl;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import es.json.examples.mongoQuery.model.OperatorFilter;
import es.json.examples.mongoQuery.model.Value;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.fasterxml.jackson.core.JsonToken.END_ARRAY;
import static com.fasterxml.jackson.core.JsonToken.FIELD_NAME;
import static java.util.Collections.singletonList;

public class ValueDeserializer extends StdDeserializer<Value> {

	public ValueDeserializer() {
		this(null);
	}

	protected ValueDeserializer(Class<OperatorFilter> vc) {
		super(vc);
	}

	@Override
	public Value deserialize(JsonParser jsonParser, DeserializationContext context) throws IOException {
		JsonToken currentToken;
		jsonParser.nextToken();
		while ((currentToken = jsonParser.nextValue()) != null) {
			switch (currentToken) {
				case VALUE_STRING:
					return new Value<>(jsonParser.getCurrentName(), singletonList(jsonParser.getText()));
				case VALUE_NUMBER_INT:
				case VALUE_NUMBER_FLOAT:
					return new Value<>(jsonParser.getCurrentName(), singletonList(jsonParser.getNumberValue()));
				case VALUE_TRUE:
				case VALUE_FALSE:
					return new Value<>(jsonParser.getCurrentName(), singletonList(jsonParser.getBooleanValue()));
				case START_ARRAY:
					return new Value<>(jsonParser.getCurrentName(), parseArray(jsonParser, context));
				default:
					throw context.wrongTokenException(jsonParser, FIELD_NAME, "field/value pair expected.");
			}
		}
		throw context.wrongTokenException(jsonParser, FIELD_NAME, "field/value pair expected.");
	}

	private List<String> parseArray(JsonParser parser, DeserializationContext context) throws IOException {
		List<String> array = new ArrayList<>();
		JsonToken currentToken = parser.getCurrentToken();
		if (currentToken != JsonToken.START_ARRAY)
			throw new IllegalArgumentException(
					"Current token not an array start token when attempting to parse an array: "
							+ currentToken);
		while ((currentToken = parser.nextValue()) != null) {
			switch (currentToken) {
				case VALUE_NUMBER_INT:
				case VALUE_NUMBER_FLOAT:
				case VALUE_STRING:
					array.add(parser.getText());
					break;
				case END_ARRAY:
					return array;
				default:
					throw context.wrongTokenException(parser, END_ARRAY, "string or array end expected.");
			}
		}
		throw context.wrongTokenException(parser, END_ARRAY, "array end expected.");
	}
}
