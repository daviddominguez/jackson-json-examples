package es.json.examples.mongoQuery.visitor;

import com.github.rutledgepaulv.qbuilders.builders.QBuilder;
import com.github.rutledgepaulv.qbuilders.conditions.Condition;
import com.github.rutledgepaulv.qbuilders.nodes.AndNode;
import com.github.rutledgepaulv.qbuilders.nodes.ComparisonNode;
import com.github.rutledgepaulv.qbuilders.nodes.OrNode;
import com.github.rutledgepaulv.qbuilders.visitors.AbstractVoidContextNodeVisitor;

import java.util.List;
import java.util.stream.Collectors;

public class FilterVisitor<T extends QBuilder<T>> extends AbstractVoidContextNodeVisitor<Condition<T>> {

	private class ConditionFilter extends QBuilder<T> {
		Condition<T> condition(ComparisonNode node) {
			return condition(node.getField(), node.getOperator(), node.getValues());
		}
	}

	@Override
	protected Condition<T> visit(AndNode node) {
		List<Condition<T>> children = node.getChildren().stream().map(this::visitAny).collect(Collectors.toList());
		return new QBuilder<T>().and(children);
	}

	@Override
	protected Condition<T> visit(OrNode node) {
		List<Condition<T>> children = node.getChildren().stream().map(this::visitAny).collect(Collectors.toList());
		return new QBuilder<T>().or(children);
	}

	@Override
	protected Condition<T> visit(ComparisonNode node) {
		return new ConditionFilter().condition(node);
	}
}
