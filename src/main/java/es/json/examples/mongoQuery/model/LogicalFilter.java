package es.json.examples.mongoQuery.model;

import java.util.List;

public abstract class LogicalFilter extends Filter {

	private List<Filter> children;

	public List<Filter> getChildren() {
		return children;
	}

	public void setChildren(List<Filter> children) {
		this.children = children;
	}
}
