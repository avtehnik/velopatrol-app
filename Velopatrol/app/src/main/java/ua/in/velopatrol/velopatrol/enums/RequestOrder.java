package ua.in.velopatrol.velopatrol.enums;

/**
 * Created by Anton on 4/17/2015.
 */
public enum RequestOrder {
	ASC ("asc"),
	DESC ("desc");
	private final String value;

	RequestOrder(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
