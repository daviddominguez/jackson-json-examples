package es.json.examples.mongoQuery.model;

import com.github.rutledgepaulv.qbuilders.nodes.ComparisonNode;
import com.github.rutledgepaulv.qbuilders.operators.ComparisonOperator;
import com.github.rutledgepaulv.qbuilders.structures.FieldPath;

public abstract class OperatorFilter extends Filter {

	private Value value;

	public Value getValue() {
		return value;
	}

	public void setValue(Value value) {
		this.value = value;
	}

	ComparisonNode getNode(ComparisonOperator operator) {
		ComparisonNode node = new ComparisonNode(null);

		node.setField(new FieldPath(getValue().getField()));
		node.setOperator(operator);
		node.setValues(getValue().getValues());

		return node;
	}
}
