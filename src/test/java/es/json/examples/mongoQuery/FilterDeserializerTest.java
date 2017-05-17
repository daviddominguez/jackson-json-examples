package es.json.examples.mongoQuery;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.github.rutledgepaulv.qbuilders.builders.QBuilder;
import com.github.rutledgepaulv.qbuilders.conditions.Condition;
import com.github.rutledgepaulv.qbuilders.nodes.AbstractNode;
import com.github.rutledgepaulv.qbuilders.visitors.MongoVisitor;
import es.json.examples.ResourceLoader;
import es.json.examples.mongoQuery.model.*;
import es.json.examples.mongoQuery.serializers.impl.ValueDeserializer;
import es.json.examples.mongoQuery.serializers.impl.CustomPropertyPolymorphicDeserializer;
import es.json.examples.mongoQuery.visitor.FilterVisitor;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

public class FilterDeserializerTest {

	private ObjectMapper mapper;

	private class NodeDeserializer extends CustomPropertyPolymorphicDeserializer<es.json.examples.mongoQuery.model.Filter> {

		public NodeDeserializer() {
			super(es.json.examples.mongoQuery.model.Filter.class);
		}
	}

	private class Filter extends QBuilder<Filter> {
	}

	@Before
	public void setUp() throws Exception {
		mapper = new ObjectMapper();

		NodeDeserializer deserializer = new NodeDeserializer();
		deserializer.register("and", AndFilter.class);
		deserializer.register("or", OrFilter.class);
		deserializer.register("eq", EqOperatorFilter.class);
		deserializer.register("gte", GteOperatorFilter.class);
		deserializer.register("gt", GtOperatorFilter.class);
		deserializer.register("like", RegexOperatorFilter.class);
		deserializer.register("lte", LteOperatorFilter.class);
		deserializer.register("lt", LtOperatorFilter.class);
		deserializer.register("neq", NeOperatorFilter.class);
		deserializer.register("in", InOperatorFilter.class);
		deserializer.register("nin", NinOperatorFilter.class);

		SimpleModule module = new SimpleModule("PolymorphicTestObjectDeserializer");
		module.addDeserializer(es.json.examples.mongoQuery.model.Filter.class, deserializer);
		module.addDeserializer(Value.class, new ValueDeserializer());
		mapper.registerModule(module);
	}

	@Test
	public void given_a_filter_when_deserialized_complete_object_is_returned () throws IOException, URISyntaxException {
		final String json = ResourceLoader.loadResourceInOneLine("filter.json");
		es.json.examples.mongoQuery.model.Filter filter = mapper.readValue(json, es.json.examples.mongoQuery.model.Filter.class);
		assertThat(filter, notNullValue());
		AbstractNode node = filter.getTranslatedNode();

		FilterVisitor<Filter> filterVisitor = new FilterVisitor<>();
		MongoVisitor mongoVisitor = new MongoVisitor();
		Condition<Filter> condition = filterVisitor.visitAny(node);
		assertThat(mongoVisitor.visitAny(node), notNullValue());
	}
}
