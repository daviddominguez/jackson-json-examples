package es.json.examples.mongoQuery.model;

/*
 * The abstract class that doesn't provide type information for its child implementations
 */
public abstract class AbstractClass {
	private final String common = "value";

	public String getCommon() {
		return common;
	}
}
