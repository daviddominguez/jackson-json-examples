package es.json.examples.mongoQuery.model;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.github.rutledgepaulv.qbuilders.nodes.AbstractNode;

import static com.github.rutledgepaulv.qbuilders.operators.ComparisonOperator.NIN;

public class NinOperatorFilter extends OperatorFilter {

	@JsonGetter("nin")
	@Override
	public Value getValue() {
		return super.getValue();
	}

	@Override
	public AbstractNode getTranslatedNode() {
		return getNode(NIN);
	}
}
