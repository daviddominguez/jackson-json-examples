package es.json.examples.mongoQuery.model;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.github.rutledgepaulv.qbuilders.nodes.AbstractNode;

import static com.github.rutledgepaulv.qbuilders.operators.ComparisonOperator.EQ;

public class EqOperatorFilter extends OperatorFilter {

	@JsonGetter("eq")
	@Override
	public Value getValue() {
		return super.getValue();
	}

	@Override
	public AbstractNode getTranslatedNode() {
		return getNode(EQ);
	}
}
