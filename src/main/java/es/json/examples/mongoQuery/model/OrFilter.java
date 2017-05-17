package es.json.examples.mongoQuery.model;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.github.rutledgepaulv.qbuilders.nodes.AbstractNode;
import com.github.rutledgepaulv.qbuilders.nodes.OrNode;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class OrFilter extends LogicalFilter {

	@JsonGetter("or")
	@Override
	public List<Filter> getChildren() {
		return super.getChildren();
	}

	@Override
	public AbstractNode getTranslatedNode() {
		return new OrNode(null, getChildren().stream().map(Filter::getTranslatedNode).collect(toList()));
	}
}
